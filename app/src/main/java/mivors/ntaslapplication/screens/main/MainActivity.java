package mivors.ntaslapplication.screens.main;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mivors.ntaslapplication.R;
import mivors.ntaslapplication.adapters.ItemAdapter;
import mivors.ntaslapplication.dagger.DaggerApplication;
import mivors.ntaslapplication.helper.ConnectivityReceiver;
import mivors.ntaslapplication.helper.Constants;
import mivors.ntaslapplication.listClickListener.ItemClickListener;
import mivors.ntaslapplication.model.item.Item;
import mivors.ntaslapplication.screens.details.DetailsActivity;

public class MainActivity extends AppCompatActivity implements MainView,ConnectivityReceiver.ConnectivityReceiverListener , ItemClickListener{

    // holding view with butterKnife
    @BindView(R.id.menuRecyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.loader)
    ProgressBar mLoader;

    //inject required objects
    @Inject
    MainPresenter mPresenter;

    // reference from item adapter
    ItemAdapter  mItemAdapter;

    //create list of item to hold items data
    ArrayList<Item> mItems;

    //reference from item Snackbar
    private  Snackbar mSnackbar;
    private ActivityOptionsCompat options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //register main activity waiting fro injections
        ((DaggerApplication)getApplication()).getAppComponent().inject(this);
        mPresenter.onAttach(this);
        mPresenter.loadItems();

    }

    @Override
    public void onAttache() {
        //RecyclerView configs
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mItems = new ArrayList<>();
        mItemAdapter = new ItemAdapter(this , mItems,this);
        mRecyclerView.setAdapter(mItemAdapter);
    }

    @Override
    public void onDetach() {
        //destroy presenter
        mPresenter = null;
    }

    @Override
    public void showLoading() {
        //show  loader
        mLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        //hide loader
        mLoader.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message,int mColor) {
        mSnackbar = Snackbar
                .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        View sbView = mSnackbar.getView();
        TextView textView =  sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(mColor);
        mSnackbar.show();
    }

    @Override
    public void updateList(ArrayList<Item> items) {
        mItems.clear();
        mItems.addAll(items);
        mItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        mPresenter.checkConnection(isConnected);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DaggerApplication.getDaggerApplication().setConnectivityListener(this);
    }

    @Override
    public void onItemClick(int position, Item item, View view) {
        Intent intent = new Intent(this,DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.ITEM_KEY,item);
        bundle.putString(Constants.SHARED_VIEW_IMAGE_KEY, ViewCompat.getTransitionName(view.findViewById(R.id.imCover)));
        intent.putExtras(bundle);

         options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                 view.findViewById(R.id.imCover),
                ViewCompat.getTransitionName(view.findViewById(R.id.imCover)));

        startActivity(intent, options.toBundle());
    }
}
