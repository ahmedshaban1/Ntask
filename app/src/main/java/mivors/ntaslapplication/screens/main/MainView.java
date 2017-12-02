package mivors.ntaslapplication.screens.main;

import java.util.ArrayList;

import mivors.ntaslapplication.baseClass.BaseView;
import mivors.ntaslapplication.model.item.Item;

/**
 * Created by Ahmed shaban on 11/30/2017.
 */

//class created for register function for main view
public interface MainView extends BaseView {
    void showLoading();
    void hideLoading();
    void showMessage(String message,int mColor);
    void updateList(ArrayList<Item> items);
}
