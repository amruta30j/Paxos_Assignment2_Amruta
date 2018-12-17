package com.amruta.model;

/**
 * Created by amrutaj on 12/12/2018.
 */
public class Item {

    private String itemName;

    private Integer itemPrice;

    public Item() {
    }

    public Item(String itemName, int itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (getItemPrice() != item.getItemPrice()) return false;
        return getItemPrice().equals(item.getItemPrice());
    }

    @Override
    public int hashCode() {
        int result = getItemName().hashCode();
        result = 31 * result + getItemPrice();
        return result;
    }
}
