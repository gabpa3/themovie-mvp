package com.gabcode.themovie_mvp.util;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.widget.CircularProgressDrawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.gabcode.themovie_mvp.R;
import com.gabcode.themovie_mvp.data.remote.ApiService;


public class ImageUtils {

    public static final int CORNER_RADIUS_DEFAULT = 4;

    enum WidthDimension {
        PORTRAIT("w300"),
        LANDSCAPE("w780");

        String dimen;

        WidthDimension(String dimen) {
            this.dimen = dimen;
        }

        public String getDimen() {
            return dimen;
        }
    }

    public static void setOnHolderOnLandscape(ImageView view, String imagePath) {
        Glide.with(view)
                .load(ApiService.IMAGE_URL + WidthDimension.LANDSCAPE.getDimen() + imagePath)
                .apply(new RequestOptions()
                        .placeholder(getCircularProgress(view.getContext()))
                        .error(R.drawable.image_no_available_w780)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .into(view);
    }

    public static void setOnHolderOnPortrait(ImageView view, String imagePath) {
        Glide.with(view)
                .asBitmap().thumbnail(0.5f)
                .load(ApiService.IMAGE_URL + WidthDimension.PORTRAIT.getDimen() + imagePath)
                .apply(new RequestOptions()
                        .placeholder(getCircularProgress(view.getContext()))
                        .error(R.drawable.image_no_available_w300)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .into(view);
    }

    public static void setOnHolderWithDimension(ImageView view, String imagePath, String dimension) {
        Glide.with(view)

                .load(ApiService.IMAGE_URL + dimension + imagePath)
                .apply(new RequestOptions()
                        .placeholder(getCircularProgress(view.getContext()))
                        .error(R.drawable.image_no_available_w300)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .into(view);
    }

    public static void setOnHolderWithCorners(ImageView view, String imagePath, int corners) {
        Glide.with(view)
                .load(ApiService.IMAGE_URL + WidthDimension.PORTRAIT.getDimen() + imagePath)
                .apply(new RequestOptions()
                        .transform(new RoundedCorners(corners))
                        .placeholder(getCircularProgress(view.getContext()))
                        .error(R.drawable.image_no_available_w300)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .into(view);
    }

    private static CircularProgressDrawable getCircularProgress(Context context) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.setColorFilter(context.getResources().getColor(R.color.orangeLight), PorterDuff.Mode.SRC_ATOP);
        circularProgressDrawable.start();

        return circularProgressDrawable;
    }
}
