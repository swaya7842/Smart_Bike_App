package com.example.lironautomotivepvtltd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class ImageSliderAdapter extends SliderViewAdapter<SliderViewHolder> {

    Context context;
    List<ImageSliderModel> imageSliderModellist;

    public ImageSliderAdapter(Context context, List<ImageSliderModel> imageSliderModellist) {
        this.context = context;
        this.imageSliderModellist = imageSliderModellist;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item_layout ,parent,false);

        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {

        viewHolder.sliderimageView.setImageResource(imageSliderModellist.get(position).getImage());

    }

    @Override
    public int getCount() {
        return imageSliderModellist.size();
    }
}

class SliderViewHolder extends SliderViewAdapter.ViewHolder{

    ImageView sliderimageView;

    public SliderViewHolder(View itemView) {
        super(itemView);
        sliderimageView = itemView.findViewById(R.id.image_View);
    }
}