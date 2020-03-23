package org.fonuhuolian.xcornerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.TypedValue;

public class XCornerFrameLayout extends CardView {

    // 圆角大小
    private int cornerLayoutRadius;
    // 背景色
    private int cornerLayoutBackgroundColor;
    // 内部padding
    private int cornerLayoutContentPadding;
    private int cornerLayoutContentPaddingTop;
    private int cornerLayoutContentPaddingBottom;
    private int cornerLayoutContentPaddingLeft;
    private int cornerLayoutContentPaddingRight;

    private Context context;

    public XCornerFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public XCornerFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XCornerFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {

        // 无阴影
        this.setCardElevation(0);
        // 不自动添加padding
        this.setPreventCornerOverlap(false);
        // 让padding生效
        this.setUseCompatPadding(true);

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.XCornerFrameLayout, defStyle, 0);

        cornerLayoutRadius = (int) array.getDimension(R.styleable.XCornerFrameLayout_cornerLayoutRadius, 0);
        this.setRadius(cornerLayoutRadius);
        cornerLayoutBackgroundColor = array.getColor(R.styleable.XCornerFrameLayout_cornerLayoutBackgroundColor, getResources().getColor(android.R.color.transparent));
        this.setCardBackgroundColor(cornerLayoutBackgroundColor);
        cornerLayoutContentPadding = (int) array.getDimension(R.styleable.XCornerFrameLayout_cornerLayoutContentPadding, 0);
        cornerLayoutContentPaddingTop = (int) array.getDimension(R.styleable.XCornerFrameLayout_cornerLayoutContentPaddingTop, 0);
        cornerLayoutContentPaddingBottom = (int) array.getDimension(R.styleable.XCornerFrameLayout_cornerLayoutContentPaddingBottom, 0);
        cornerLayoutContentPaddingLeft = (int) array.getDimension(R.styleable.XCornerFrameLayout_cornerLayoutContentPaddingLeft, 0);
        cornerLayoutContentPaddingRight = (int) array.getDimension(R.styleable.XCornerFrameLayout_cornerLayoutContentPaddingRight, 0);

        if (cornerLayoutContentPaddingTop == 0 && cornerLayoutContentPaddingBottom == 0 && cornerLayoutContentPaddingLeft == 0 && cornerLayoutContentPaddingRight == 0) {
            this.setContentPadding(cornerLayoutContentPadding, cornerLayoutContentPadding, cornerLayoutContentPadding, cornerLayoutContentPadding);
        } else {
            this.setContentPadding(cornerLayoutContentPaddingLeft, cornerLayoutContentPaddingTop, cornerLayoutContentPaddingRight, cornerLayoutContentPaddingBottom);
        }

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
