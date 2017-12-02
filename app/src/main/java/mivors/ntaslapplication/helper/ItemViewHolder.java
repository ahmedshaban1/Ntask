package mivors.ntaslapplication.helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mivors.ntaslapplication.R;

/**
 * Created by Ahmed shaban on 11/30/2017.
 */
// create view holder for single menu single item
public class ItemViewHolder  extends RecyclerView.ViewHolder {
    @BindView(R.id.tvName)
    public TextView tvName;

    @BindView(R.id.tvLike)
    public TextView tvLike;


    @BindView(R.id.tvDislike)
    public TextView tvDislike;

    @BindView(R.id.imCover)
    public ImageView imCover;


    public ItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}