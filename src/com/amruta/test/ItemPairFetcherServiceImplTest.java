package com.amruta.test;

import com.amruta.model.Item;
import com.amruta.service.ItemPairFetcherServiceImpl;
import com.amruta.util.ItemPriceFileReader;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by amrutaj on 17/12/2018.
 */
public class ItemPairFetcherServiceImplTest {

    private static final String DEFAULT_FILE_LOCATION = "src\\resources\\prices.txt";

    private ItemPairFetcherServiceImpl itemPairFetcherService;

    private ItemPriceFileReader reader;

    private Set<Item> testSet1;

    private Set<Item> testSet2;

    private Set<Item> testSet3;

    @Before
    public void setUp() throws Exception {
        itemPairFetcherService = new ItemPairFetcherServiceImpl(DEFAULT_FILE_LOCATION);
        reader = new ItemPriceFileReader(DEFAULT_FILE_LOCATION);

        testSet1 = new HashSet<>();
        testSet2 = new HashSet<>();
        testSet3 = new HashSet<>();

        Item item1 = new Item("Candy Bar", 500);
        Item item2 = new Item("Paperback Book", 700);
        Item item3 = new Item("Detergent", 1000);
        Item item4 = new Item("Headphones", 1400);
        Item item5 = new Item("Earmuffs", 2000);
        Item item6 = new Item("Bluetooth Stereo", 6000);

        testSet1.add(item1);
        testSet1.add(item5);

        testSet2.add(item2);
        testSet2.add(item4);

        testSet3.add(item6);
        testSet3.add(item5);
    }


    @Test
    public void findPairNull() throws Exception {
        Assert.assertNull(itemPairFetcherService.findPair(1100));
    }

    @Test
    public void findPairNotNullSet1() throws Exception {
        Assert.assertEquals(testSet1.stream().collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt(Item::getItemPrice))))
                , itemPairFetcherService.findPair(2500));
    }

    @Test
    public void findPairNotNullSet2() throws Exception {
        Assert.assertEquals(testSet2.stream().collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt(Item::getItemPrice))))
                , itemPairFetcherService.findPair(2300));
    }

    @Test
    public void findPairNotNullSet3() throws Exception {
        Assert.assertEquals(testSet3.stream().collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt(Item::getItemPrice))))
                , itemPairFetcherService.findPair(10000));
    }

    @Test
    public void readerNotNull() throws Exception {
       Assert.assertNotNull(reader);
    }


    @After
    public void tearDown() throws Exception {
        itemPairFetcherService = null;
        reader = null;

        testSet1.clear();
        testSet2.clear();
        testSet3.clear();
    }

}
