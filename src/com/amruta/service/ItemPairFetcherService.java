package com.amruta.service;

import com.amruta.exception.ItemPairFetcherException;
import com.amruta.model.Item;

import java.util.Set;

/**
 * Created by amrutaj on 12/12/2018.
 */
public interface ItemPairFetcherService {

    Set<Item> findPair(int price) throws ItemPairFetcherException;

}
