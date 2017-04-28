package com.example.yoant.foodcritic.adapters.curentlyused;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.helper.sqlite.SQLiteDatabaseHelper;
import com.example.yoant.foodcritic.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private static final int REMOVE_TIMEOUT = 3000;

    private List<Product> mProductList;
    private List<Product> mItemsPendingRemove;
    private Context mContext;
    private List<Image> images;
    private String imgURL = "http://hitgid.com/images/%D1%84%D1%80%D1%83%D0%BA%D1%82%D1%8B-7.jpg";

    private Handler handler = new Handler();
    HashMap<Product, Runnable> pendingRunnables = new HashMap<>();

    public ProductAdapter(List<Product> products, Context context) {
        mProductList = new ArrayList<>();
        mItemsPendingRemove = new ArrayList<>();
        mProductList = products;
        this.notifyDataSetChanged();
        mContext = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_product_representation, parent, false);
        final ProductViewHolder holder = new ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        ProductViewHolder viewHolder = holder;
        final Product product = mProductList.get(position);


        if (mItemsPendingRemove.contains(product)) {
            viewHolder.itemView.setBackgroundColor(Color.RED);
            viewHolder.productName.setVisibility(View.GONE);
            viewHolder.productImage.setVisibility(View.GONE);
            viewHolder.productCarb.setVisibility(View.GONE);
            viewHolder.carbDesc.setVisibility(View.GONE);
            viewHolder.productFat.setVisibility(View.GONE);
            viewHolder.fatDesc.setVisibility(View.GONE);
            viewHolder.productProt.setVisibility(View.GONE);
            viewHolder.protDesc.setVisibility(View.GONE);
            viewHolder.productEnergy.setVisibility(View.GONE);
            viewHolder.energyDesc.setVisibility(View.GONE);
            viewHolder.undoButton.setVisibility(View.VISIBLE);

            viewHolder.undoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Runnable runnable = pendingRunnables.get(product);
                    pendingRunnables.remove(product);
                    if (pendingRunnables != null)
                        handler.removeCallbacks(runnable);
                    mItemsPendingRemove.remove(product);
                    notifyItemChanged(mProductList.indexOf(product));
                }
            });
        } else {
            viewHolder.itemView.setBackgroundColor(Color.WHITE);
            viewHolder.productName.setVisibility(View.VISIBLE);
            viewHolder.productImage.setVisibility(View.VISIBLE);
            viewHolder.productCarb.setVisibility(View.VISIBLE);
            viewHolder.carbDesc.setVisibility(View.VISIBLE);
            viewHolder.productFat.setVisibility(View.VISIBLE);
            viewHolder.fatDesc.setVisibility(View.VISIBLE);
            viewHolder.productProt.setVisibility(View.VISIBLE);
            viewHolder.protDesc.setVisibility(View.VISIBLE);
            viewHolder.productEnergy.setVisibility(View.VISIBLE);
            viewHolder.energyDesc.setVisibility(View.VISIBLE);
            viewHolder.undoButton.setVisibility(View.GONE);
            viewHolder.undoButton.setOnClickListener(null);
            holder.productImage.setImageResource(R.drawable.vitamins_fruit_logo); //TODO check without it
            //Glide.with(mContext).load(imgURL).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.productImage);
            holder.productName.setText(product.getName());
            holder.productFat.setText("" + product.getFat());
            holder.productProt.setText("" + product.getProtein());
            holder.productEnergy.setText("" + product.getEnergeticValue());
            holder.productCarb.setText("" + product.getCarb());
        }
    }

    public void pendingRemoval(int position) {
        final Product product = mProductList.get(position);
        if (!mItemsPendingRemove.contains(product)) {
            mItemsPendingRemove.add(product);
            notifyItemChanged(position);

            Runnable pendingRemove = new Runnable() {
                @Override
                public void run() {
                    remove(mProductList.indexOf(product));
                }
            };
            handler.postDelayed(pendingRemove, REMOVE_TIMEOUT);
            pendingRunnables.put(product, pendingRemove);
        }
    }

    public void remove(int position) {
        Product product = mProductList.get(position);
        SQLiteDatabaseHelper helper = SQLiteDatabaseHelper.getsInstance(mContext);
        if (mItemsPendingRemove.contains(product))
            mItemsPendingRemove.remove(product);
        if (mProductList.contains(product)) {
            boolean flag = helper.deleteProductFromDatabaseByName(
                    mProductList.get(position).getName());
            if(flag) {
                mProductList.remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    public boolean isPendingRemoval(int position) {
        Product product = mProductList.get(position);
        return mItemsPendingRemove.contains(product);
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public ImageView productImage;
        public TextView productName;
        public TextView fatDesc;
        public TextView productFat;
        public TextView carbDesc;
        public TextView productCarb;
        public TextView energyDesc;
        public TextView productEnergy;
        public TextView protDesc;
        public TextView productProt;
        public Button undoButton;

        public ProductViewHolder(View view) {
            super(view);
            productImage = (ImageView) view.findViewById(R.id.single_product_representation_image);
            productName = (TextView) view.findViewById(R.id.single_product_representation_name);
            carbDesc = (TextView) view.findViewById(R.id.single_product_representation_carb);
            productCarb = (TextView) view.findViewById(R.id.single_product_representation_carb_value);
            energyDesc = (TextView) view.findViewById(R.id.single_product_representation_energy);
            productEnergy = (TextView) view.findViewById(R.id.single_product_representation_energy_value);
            fatDesc = (TextView) view.findViewById(R.id.single_product_representation_fat);
            productFat = (TextView) view.findViewById(R.id.single_product_representation_fat_value);
            protDesc = (TextView) view.findViewById(R.id.single_product_representation_prot);
            productProt = (TextView) view.findViewById(R.id.single_product_representation_prot_value);
            undoButton = (Button) view.findViewById(R.id.undo_button);
        }
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public void updateData(Product[] products){
        mProductList.addAll(Arrays.asList(products));
    }

}
