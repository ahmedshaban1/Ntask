package mivors.ntaslapplication.listClickListener;

import android.view.View;

import mivors.ntaslapplication.model.item.Item;

/**
 * Created by Ahmed shaban on 12/2/2017.
 */

public interface ItemClickListener {
    void onItemClick(int position, Item item, View view);
}
