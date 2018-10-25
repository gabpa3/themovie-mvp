package com.gabcode.themovie_mvp.util;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

public class BindingUtils {

    @BindingAdapter({"image", "dimen"})
    public static void loadImageWithDimension(ImageView view, String imageThumbnail, String dimension) {
        ImageUtils.setOnHolderWithDimension(view, imageThumbnail, dimension);
    }

    @BindingAdapter("image")
    public static void loadImageOnLandscape(ImageView view, String imageThumbnail) {
        ImageUtils.setOnHolderOnLandscape(view, imageThumbnail);
    }

    @BindingAdapter("imagePortrait")
    public static void loadImageOnPortrait(ImageView view, String imageThumbnail) {
        ImageUtils.setOnHolderOnPortrait(view, imageThumbnail);
    }

    @BindingAdapter("imageRounded")
    public static void loadImageWithCorners(ImageView view, String imageThumbnail) {
        ImageUtils.setOnHolderWithCorners(view, imageThumbnail, ImageUtils.CORNER_RADIUS_DEFAULT);
    }

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, boolean visible) {
        view.setVisibility((visible) ?  View.VISIBLE : View.GONE);
    }
}
