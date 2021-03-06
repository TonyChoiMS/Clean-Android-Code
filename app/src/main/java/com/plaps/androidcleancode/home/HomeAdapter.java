package com.plaps.androidcleancode.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.plaps.androidcleancode.R;
import com.plaps.androidcleancode.models.CityListData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView Adapter Class
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private List<CityListData> data;
    private Context context;

    // Constructor
    public HomeAdapter(Context context, List<CityListData> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }


    /**
     * Create RecyclerView row item view
     * @param parent        RecyclerView Parent
     * @param viewType      RecyclerView Type
     * @return              RecyclerView Row
     */
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }


    /**
     * Set RecyclerView row data
     * @param holder        ViewHolder Class
     * @param position      RecyclerView item position
     */
    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        holder.click(data.get(position), listener);
        holder.tvCity.setText(data.get(position).getName());
        holder.tvDesc.setText(data.get(position).getDescription());

        String images = data.get(position).getBackground();

        //Set Image on Glide Library
        Glide.with(context)
                .load(images)
                .into(holder.background);

    }


    /**
     * RecyclerView item count
     * @return  Total Item Count
     */
    @Override
    public int getItemCount() {
        return data.size();
    }


    /**
     * RecyclerView ItemClickListener Interface
     * Used In HomeActivity
     */
    public interface OnItemClickListener {
        void onClick(CityListData Item);
    }

    // ViewHolder Class
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.city)
        TextView tvCity;
        @BindView(R.id.hotel)
        TextView tvDesc;
        @BindView(R.id.image)
        ImageView background;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


        public void click(final CityListData cityListData, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(cityListData);
                }
            });
        }
    }
}
