package mivors.ntaslapplication.screens.main;

import android.content.Context;
import android.graphics.Color;

import java.util.ArrayList;

import javax.inject.Inject;

import mivors.ntaslapplication.R;
import mivors.ntaslapplication.apiClient.ApiInterface;
import mivors.ntaslapplication.baseClass.BasePresenter;
import mivors.ntaslapplication.dagger.DaggerApplication;
import mivors.ntaslapplication.helper.Utilities;
import mivors.ntaslapplication.model.item.GetAllItemsResponse;
import mivors.ntaslapplication.model.item.Item;
import mivors.ntaslapplication.sqlLite.ItemDbHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ahmed shaban on 11/30/2017.
 */

public class MainPresenter implements BasePresenter<MainView> {
    MainView mView;
    boolean isLoaded = false;


    //inject api interface object

    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;
    // create sqllit reference
    @Inject
    ItemDbHelper  mItemDbHelper;
    @Override
    public void onAttach(MainView view) {
        mView = view;
        mView.onAttache();
    }



    @Override
    public void onDetach() {
        mView = null;
    }
    //create Constructor to get reference of api interface object
    public MainPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    //this function created to load items from specific endpoint
    public void loadItems() {
        if(!Utilities.checkConnection(mContext)){
            mView.showMessage(mContext.getString(R.string.internet_check),Color.RED);
            checkConnection(false);
            return;
        }

        mView.showLoading();
         mApiInterface.getAllItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetAllItemsResponse>() {
                    @Override
                    public final void onCompleted() {

                        mView.hideLoading();
                    }

                    @Override
                    public final void onError(Throwable e) {
                        mView.showMessage(mContext.getString(R.string.internet_check),Color.RED);
                        mView.hideLoading();
                    }

                    @Override
                    public final void onNext(GetAllItemsResponse response) {
                        isLoaded = true;
                        mView.updateList(response.getItems());
                        mItemDbHelper.deleteSavedData();
                        SaveLocalData(response.getItems());
                    }
                });
    }

    //store Realm data
    private void SaveLocalData(ArrayList<Item>  items) {
        mItemDbHelper.addItems(items);
    }

    void checkConnection(boolean isConnected) {
        //check internet and  data not loaded
        if(isConnected  && !isLoaded){
            loadItems();
            isLoaded = false;
            mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }if(!isConnected && isLoaded){
            //offline check and  data loaded
            mView.showMessage(mContext.getString(R.string.offline),Color.WHITE);

        }else if(!isConnected && !isLoaded){
            //get offline  data using realm
            mView.showMessage(mContext.getString(R.string.get_data_from_local),Color.WHITE);
            mView.updateList(mItemDbHelper.getAllItems());
        }else if(isConnected && isLoaded){
            mView.showMessage(mContext.getString(R.string.connect_to_internet),Color.GREEN);
        }
    }



}
