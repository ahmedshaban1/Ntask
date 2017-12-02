package mivors.ntaslapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mivors.ntaslapplication.R;
import mivors.ntaslapplication.helper.ItemViewHolder;
import mivors.ntaslapplication.helper.Utilities;
import mivors.ntaslapplication.listClickListener.ItemClickListener;
import mivors.ntaslapplication.model.item.Item;

/**
 * Created by Ahmed shaban on 11/30/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private  Context  context;
    private ArrayList<Item> items;
    private ItemClickListener mItemClickListener;
    public ItemAdapter(Context context,ArrayList<Item> items,ItemClickListener itemClickListener){
        this.context = context;
        this.items = items;
        mItemClickListener = itemClickListener;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final Item item = items.get(position);
        holder.tvName.setText(item.getName());
        holder.tvLike.setText(String.valueOf(Utilities.randInt(0,1000)));
        holder.tvDislike.setText(String.valueOf(Utilities.randInt(0,500)));
        Picasso.with(context).load(item.getPhotoUrl()).into(holder.imCover);
        holder.imCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.onItemClick(position,item,holder.itemView);
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
