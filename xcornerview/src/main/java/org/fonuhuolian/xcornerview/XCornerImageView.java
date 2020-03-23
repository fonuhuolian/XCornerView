package org.fonuhuolian.xcornerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;

public class XCornerImageView extends CardView {

    // 圆角大小
    private int cornerImageRadius;
    // 是否保持比例不变
    private boolean cornerImageAdjustViewBounds;
    // 资源
    private int cornerImageSrc;
    // 图片缩放
    private int cornerImageScaleType;

    private Context context;

    public XCornerImageView(@NonNull Context context) {
        this(context, null);
    }

    public XCornerImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XCornerImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {

        this.removeAllViews();

        // 无阴影
        this.setCardElevation(0);
        // 不自动添加padding
        this.setPreventCornerOverlap(false);
        // 让padding生效
        this.setUseCompatPadding(true);

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.XCornerImageView, defStyle, 0);

        cornerImageRadius = (int) array.getDimension(R.styleable.XCornerImageView_cornerImageRadius, 0);
        this.setRadius(cornerImageRadius);
        cornerImageSrc = array.getResourceId(R.styleable.XCornerImageView_cornerImageSrc, 0);
        cornerImageAdjustViewBounds = array.getBoolean(R.styleable.XCornerImageView_cornerImageAdjustViewBounds, false);
        cornerImageScaleType = array.getInt(R.styleable.XCornerImageView_cornerImageScaleType, 1);

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setImageResource(cornerImageSrc);
        imageView.setAdjustViewBounds(cornerImageAdjustViewBounds);

        switch (cornerImageScaleType) {
            case 0:
                imageView.setScaleType(ImageView.ScaleType.MATRIX);
                break;
            case 1:
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                break;
            case 2:
                imageView.setScaleType(ImageView.ScaleType.FIT_START);
                break;
            case 3:
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;
            case 4:
                imageView.setScaleType(ImageView.ScaleType.FIT_END);
                break;
            case 5:
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case 6:
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                break;
            case 7:
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                break;
        }


        this.addView(imageView);
        array.recycle();
    }

    public void setRipple(boolean isRipple) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
        int[] attribute = new int[]{android.R.attr.selectableItemBackground};
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(typedValue.resourceId, attribute);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && isRipple) {
            this.setForeground(typedArray.getDrawable(0));
        } else {
            this.setForeground(getResources().getDrawable(android.R.color.transparent));
        }
    }
}
