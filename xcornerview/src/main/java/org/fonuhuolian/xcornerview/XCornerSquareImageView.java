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

public class XCornerSquareImageView extends CardView {

    // 圆角大小
    private int cornerSquareImageRadius;
    // 是否保持比例不变
    private boolean cornerSquareImageAdjustViewBounds;
    // 资源
    private int cornerSquareImageSrc;
    // 图片缩放
    private int cornerSquareImageScaleType;

    private Context context;

    public XCornerSquareImageView(@NonNull Context context) {
        this(context, null);
    }

    public XCornerSquareImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XCornerSquareImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.XCornerSquareImageView, defStyle, 0);

        cornerSquareImageRadius = (int) array.getDimension(R.styleable.XCornerSquareImageView_cornerSquareImageRadius, 0);
        this.setRadius(cornerSquareImageRadius);
        cornerSquareImageSrc = array.getResourceId(R.styleable.XCornerSquareImageView_cornerSquareImageSrc, 0);
        cornerSquareImageAdjustViewBounds = array.getBoolean(R.styleable.XCornerSquareImageView_cornerSquareImageAdjustViewBounds, false);
        cornerSquareImageScaleType = array.getInt(R.styleable.XCornerSquareImageView_cornerSquareImageScaleType, 1);

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setImageResource(cornerSquareImageSrc);
        imageView.setAdjustViewBounds(cornerSquareImageAdjustViewBounds);

        switch (cornerSquareImageScaleType) {
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //让宽度的测量尺寸代替高度尺寸
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
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
