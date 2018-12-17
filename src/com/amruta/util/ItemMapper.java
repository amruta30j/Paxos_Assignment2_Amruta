package com.amruta.util;

import com.amruta.model.Item;

import java.util.Objects;

/**
 * Created by amrutaj on 12/12/2018.
 */
public class ItemMapper {

    /** This is a utility class which maps the
     * row in the file to the Item domain class.
     * @param itemRow
     * @return
     */

    public static Item mapItems(String itemRow) {

        Item item = new Item();
        String[] tokens = itemRow.split(",");
        if (Objects.nonNull(tokens)) {
            item.setItemName(tokens[0].trim());
            item.setItemPrice(Integer.parseInt(tokens[1].trim()));
            return item;
        }
        return null;
    }
}
