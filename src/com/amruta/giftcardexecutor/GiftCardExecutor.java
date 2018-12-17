package com.amruta.giftcardexecutor;

import com.amruta.exception.FileReaderException;
import com.amruta.model.Item;
import com.amruta.service.ItemPairFetcherServiceImpl;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by amrutaj on 12/12/2018.
 */
public class GiftCardExecutor {

    /** This is the main execution point for this application.
     * User can place the files in the location given below.
     *
     */

    private static final String DEFAULT_FILE_LOCATION = "src\\resources\\prices.txt";

    public static void main(String args[]) throws Exception{

        try{
          Set<Item> giftItems = new ItemPairFetcherServiceImpl(DEFAULT_FILE_LOCATION).findPair(1100);

            //There is definitely scope for improvement here. This can be even done in the service itself
            if (Objects.nonNull(giftItems) && giftItems.size() > 1) {
                for (Item item : giftItems) {
                    System.out.println(item.getItemName() + " " + item.getItemPrice());
                }
            } else {
                System.out.println("Not possible");
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }


    }
}
