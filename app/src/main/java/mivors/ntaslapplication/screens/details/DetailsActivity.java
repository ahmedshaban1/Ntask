package mivors.ntaslapplication.screens.details;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mivors.ntaslapplication.R;
import mivors.ntaslapplication.dagger.DaggerApplication;
import mivors.ntaslapplication.helper.Constants;
import mivors.ntaslapplication.model.item.Item;

public class DetailsActivity extends AppCompatActivity implements DetailsView{
    // get views references
    @BindView(R.id.imCover)
    ImageView imCover;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvDescription)
    TextView tvDescription;



    //initial values
    Item item;
    String imageTransitionName;

    //injections
    @Inject
    DetailsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //enable back button in action bar with icon
        if(getSupportActionBar()!=null){

            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //register main activity waiting fro injections
        ((DaggerApplication)getApplication()).getAppComponent().inject(this);
        mPresenter.onAttach(this);
        bindViews(item);
        setImageTransitionName(imageTransitionName);





    }

    //this function created for binding view in xml
    private void bindViews(Item item) {
        setTitle(item.getName());
        tvDescription.setText(item.getDescription());
        tvName.setText(item.getName());
        Picasso.with(this).load(item.getPhotoUrl()).into(imCover);
    }

    //initializing required data for activity
    @Override
    public void onAttache() {
        item = getIntent().getParcelableExtra(Constants.ITEM_KEY);
        imageTransitionName = getIntent().getStringExtra(Constants.SHARED_VIEW_IMAGE_KEY);
    }

    @Override
    public void onDetach() {

    }

    // set  TransitionName for cover
    private void setImageTransitionName(String imageTransitionName){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imCover.setTransitionName(imageTransitionName);
        }
    }


    // handler back button to match with standard back button of mobile phone
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
           onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
