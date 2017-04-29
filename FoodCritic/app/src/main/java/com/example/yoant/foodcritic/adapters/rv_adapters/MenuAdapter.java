package com.example.yoant.foodcritic.adapters.rv_adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yoant.foodcritic.view.activities.MainActivity;
import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.models.MainMenuElement;

public class MenuAdapter extends BaseAdapter {
    private final int CATEGORIES_COUNT = 4;
    private MainMenuElement[] mCategoryList;
    private Context context;
    private LayoutInflater layoutInflater = null;

    private class ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView descriptionTextView;
    }

    public MenuAdapter(MainActivity mainActivity, MainMenuElement[] categoryList) {
        mCategoryList = categoryList;
        context = mainActivity;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mCategoryList.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        View mainView = layoutInflater.inflate(R.layout.main_menu_element, null);

        viewHolder.imageView = (ImageView) mainView.findViewById(R.id.menu_element_icon);
        viewHolder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewHolder.descriptionTextView = (TextView) mainView.findViewById(R.id.menu_element_description);
        viewHolder.nameTextView = (TextView) mainView.findViewById(R.id.menu_element_name);

        viewHolder.imageView.setImageResource(mCategoryList[position].getmElementLogoId());
        viewHolder.descriptionTextView.setText(mCategoryList[position].getmElementDescription());
        viewHolder.nameTextView.setText(mCategoryList[position].getmElementName());

        return mainView;
    }
}
