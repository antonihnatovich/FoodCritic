package com.example.yoant.foodcritic.helper;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;

public class ItemDecorationRecyclerViewColumns extends RecyclerView.ItemDecoration {
    private int mSizeGridSpacing;
    private int mGridSize;
    private boolean mRequireLeftSpacing;

    public ItemDecorationRecyclerViewColumns(int sizeGridSpacing, int gridSize){
        mSizeGridSpacing = sizeGridSpacing;
        mGridSize = gridSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        int frameWidth = (int)((parent.getWidth() - (float)mSizeGridSpacing * (mGridSize - 1)) / mGridSize);
        int padding = parent.getWidth() / mGridSize - frameWidth;
        int itemPosition = ((RecyclerView.LayoutParams)view.getLayoutParams()).getViewAdapterPosition();


        if(itemPosition < mGridSize)
            outRect.top = 0;
        else
            outRect.top = mSizeGridSpacing;

        if(itemPosition % mGridSize == 0){
            outRect.left = 0;
            outRect.right = padding;
            mRequireLeftSpacing = true;
        } else if((itemPosition + 1) % mGridSize == 0){
            mRequireLeftSpacing = false;
            outRect.right = 0;
            outRect.left = padding;
        } else if(mRequireLeftSpacing){
            mRequireLeftSpacing = false;
            outRect.left = mSizeGridSpacing - padding;
            if((itemPosition + 2) % mGridSize == 0)
                outRect.right = mSizeGridSpacing - padding;
                else
                    outRect.right = mSizeGridSpacing / 2;
        } else if((itemPosition + 2) % mGridSize == 0){
            mRequireLeftSpacing = false;
            outRect.left = mSizeGridSpacing / 2;
            outRect.right = mSizeGridSpacing / padding;
        } else {
            mRequireLeftSpacing = false;
            outRect.left = mSizeGridSpacing / 2;
            outRect.right = mSizeGridSpacing / 2;
        }
        outRect.bottom = 0;
    }
}
