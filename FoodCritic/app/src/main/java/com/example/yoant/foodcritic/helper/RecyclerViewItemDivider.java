package com.example.yoant.foodcritic.helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class RecyclerViewItemDivider extends RecyclerView.ItemDecoration {

    private static final int[] ATTRIBUTES = new int[]{
            android.R.attr.listDivider
    };

    private static final int AS_HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    private static final int AS_VERTICAL = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private int mOrientation;

    public RecyclerViewItemDivider(Context context, int orientation) {
        final TypedArray typedArray = context.obtainStyledAttributes(ATTRIBUTES);
        typedArray.recycle();
        mDivider = typedArray.getDrawable(0);
        setOrientation(orientation);
    }

    private void setOrientation(int orientation) {
        if (orientation != AS_HORIZONTAL && orientation != AS_VERTICAL)
            throw new IllegalArgumentException("Check out screen orientation. Current invalid.");
        mOrientation = orientation;
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == AS_HORIZONTAL)
            drawHorizontal(canvas, parent);
        else
            drawVertical(canvas, parent);
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        final int paddingLeft = parent.getPaddingLeft();
        final int paddingRight = parent.getPaddingRight();

        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int paddingTop = child.getBottom() + params.bottomMargin;
            final int paddingBottom = paddingTop + mDivider.getIntrinsicHeight();
            mDivider.setBounds(paddingLeft, paddingTop, paddingRight, paddingBottom);
            mDivider.draw(canvas);
        }
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        final int paddingTop = parent.getPaddingTop();
        final int paddingBottom = parent.getPaddingBottom();

        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int paddingLeft = child.getRight() + params.rightMargin;
            final int paddingRight = paddingLeft + mDivider.getIntrinsicHeight();
            mDivider.setBounds(paddingLeft, paddingTop, paddingRight, paddingBottom);
            mDivider.draw(canvas);
        }
    }

    @Override
    public void getItemOffsets(Rect rect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == AS_HORIZONTAL)
            rect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        else
            rect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    }
}
