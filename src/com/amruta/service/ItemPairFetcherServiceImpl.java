package com.amruta.service;

import com.amruta.exception.FileReaderException;
import com.amruta.exception.ItemPairFetcherException;
import com.amruta.model.Item;
import com.amruta.util.ItemPriceFileReader;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by amrutaj on 12/12/2018.
 */
public class ItemPairFetcherServiceImpl implements ItemPairFetcherService {

    private ItemPriceFileReader reader;

    private String fileLocation;

    private Map<Integer, Set<Item>> giftMap = new TreeMap<>();

    public ItemPairFetcherServiceImpl(String fileLocation) {
        this.fileLocation = fileLocation;
        reader = new ItemPriceFileReader(fileLocation);
    }

    /**
     * This method writes the file data in the
     * treemap in order to preserve the ascending
     * order of the items in the file
     *
     * @return TreeMap<Integer, Item>
     */

    private TreeMap<Integer, Item> getItemPriceMap() {

        try {
            TreeMap<Integer, Item> items = reader.readFile();
            return items;
            //Map<Integer, List<Item>> giftCardItems = fetchTheItem(items, price);
        } catch (IOException | FileReaderException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method finds the pair of gift items
     * provided the total amount in the giftcard
     * 1. first it obtains the tree map
     * 2. get all the keys(an Integer value which is an item price) in the map to the list
     * 3. Iterats over the list(o(n)--complexity and passes the each value to a function call fetchTheItem(),
     * which takes an item price and the total price
     *
     * @param price
     */

    @Override
    public Set<Item> findPair(int price) {

        TreeMap<Integer, Item> items = getItemPriceMap();

        try {
            List<Integer> itemPriceList = items.keySet().stream()
                    .collect(Collectors.toList());
            if (isSufficientBalance(itemPriceList.get(0), price)) {
                for (int i = 0; i < itemPriceList.size(); i++) {
                    fetchTheItem(itemPriceList.get(i), price);
                }
                Map.Entry<Integer, Set<Item>> entry = giftMap.entrySet().iterator().next();
                Set<Item> itemSet = entry.getValue();
                if (Objects.nonNull(giftMap) && itemSet.size() > 1) {
                    itemSet = itemSet.stream().collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt(Item::getItemPrice))));
                    return itemSet;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method determines if the there is
     * sufficient balance in the giftcard to buy
     * gifts.
     *
     * @param firstItemPrice
     * @param totalGiftCardprice
     * @return
     */
    private boolean isSufficientBalance(int firstItemPrice, int totalGiftCardprice) {
        if (totalGiftCardprice >= firstItemPrice) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * This method takes an item price and total gift card amount in account.
     * Firstly it obtains the TreeMap for the mapped file values
     * 1. gets the Value that is an Item for each itemKey
     * 2. calculates the remaining amount
     * 3. gets the floor key based on the remaining amount
     * 4. Obtain the Item and ultimately the price for that item from floor key
     * 5.calculates the difference for this combinations
     * 6. puts the diff and the set<Item> in the Map Map<Integer, Set<Item>>
     *
     * @param itemPrice
     * @param price
     * @return
     */

    private Map<Integer, Set<Item>> fetchTheItem(int itemPrice, int price) {

        TreeMap<Integer, Item> priceMap = getItemPriceMap();

        int remainingAmount = 0;
        int diff = 0;
        Set<Item> itemSet = new HashSet<>();
        //if(isSufficientBalance(priceMap,price)){
        Item item = priceMap.get(itemPrice);
        remainingAmount = price - item.getItemPrice();
        Integer anticipatedFloorKey = priceMap.floorKey(remainingAmount);
        if (Objects.nonNull(anticipatedFloorKey)) {
            diff = remainingAmount - anticipatedFloorKey;
            itemSet.add(item);
            itemSet.add(priceMap.get(anticipatedFloorKey));
            giftMap.put(diff, itemSet);
        }
        //}
        return giftMap;
    }


}


