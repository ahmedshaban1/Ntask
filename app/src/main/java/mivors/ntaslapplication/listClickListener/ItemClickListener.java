package mivors.ntaslapplication.listClickListener;

import android.view.View;

import mivors.ntaslapplication.model.item.Item;

/**
 * Created by Ahmed shaban on 12/2/2017.
 */
//this interface class for communication between Adapter and View
public interface ItemClickListener {
    void onItemClick(int position, Item item, View view);
}
