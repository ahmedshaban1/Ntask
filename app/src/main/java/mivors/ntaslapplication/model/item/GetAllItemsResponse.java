package mivors.ntaslapplication.model.item;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Ahmed shaban on 11/30/2017.
 */

public class GetAllItemsResponse {

    @SerializedName("items")
    private ArrayList<Item> items;

    public ArrayList<Item> getItems() {
        return items;
    }
}
