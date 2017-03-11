package com.example.yoant.foodcritic.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yoant.foodcritic.MainActivity;
import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.core.Category;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends BaseAdapter {
    private final int CATEGORIES_COUNT = 4;
    private List<Category> categoryList = new ArrayList<>(CATEGORIES_COUNT);
    private Context context;
    private LayoutInflater layoutInflater = null;

    private class ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView countTextView;
    }

    public MenuAdapter(MainActivity mainActivity, List<Category> categoryList) {
        this.categoryList = categoryList;
        context = mainActivity;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return categoryList.size();
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
        View mainView = layoutInflater.inflate(R.layout.menu_item, null);

        viewHolder.imageView = (ImageView) mainView.findViewById(R.id.menu_image_view);
        viewHolder.countTextView = (TextView) mainView.findViewById(R.id.menu_count_view);
        viewHolder.nameTextView = (TextView) mainView.findViewById(R.id.menu_name_view);

        viewHolder.imageView.setImageResource(categoryList.get(position).getPicId());
        viewHolder.countTextView.setText("Count " + categoryList.get(position).getItemsCount());
        viewHolder.nameTextView.setText(categoryList.get(position).getName());

        return mainView;
    }
}
