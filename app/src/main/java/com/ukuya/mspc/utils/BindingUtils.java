package com.ukuya.mspc.utils;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.ukuya.mspc.R;
import com.ukuya.mspc.model.Event;

import java.text.SimpleDateFormat;

public class BindingUtils {
    @BindingAdapter("dateText")
    public static void getDateTime(TextView view, Long datetime) {
        if (datetime == null)
            return;
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d h:mm a");
        view.setText(TimeUtils.millis2String(datetime, formatter));
    }

    @BindingAdapter("setImage")
    public static void setImage(ImageView imageView, String url) {
        if (url == null)
            url = "";
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.logo);
//        requestOptions.error(R.drawable.logo);

        Glide.with(imageView.getContext())
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(1000))
                .into(imageView);

    }

    @BindingAdapter("setRoundImagePerson")
    public static void setRoundImage(ImageView view, String url) {
        if (url == null)
            return;

        Glide.with(view.getContext())
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade(1000))
                .into(view);
    }

    @BindingAdapter({"setCurrentTime", "setEvent"})
    public static void setStatus(TextView textView, Long currentTime, Event event) {
        if (event != null) {
            if (currentTime < event.getStartTime()) {
                textView.setText("UPCOMING");
                textView.setBackgroundColor(textView.getResources().getColor(R.color.upcoming));
            } else if (currentTime > event.getStartTime() && currentTime < event.getEndTime()) {
                textView.setText("ONGOING");
                textView.setBackgroundColor(textView.getResources().getColor(R.color.ongoing));
            } else {
                textView.setText("ENDED");
                textView.setBackgroundColor(textView.getResources().getColor(R.color.ended));
            }
        }
    }
}
