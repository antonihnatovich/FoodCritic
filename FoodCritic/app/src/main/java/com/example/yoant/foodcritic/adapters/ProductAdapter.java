package com.example.yoant.foodcritic.adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.core.Product;
import com.example.yoant.foodcritic.core.ProductHeh;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ProductHeh[] mProductList;
    private Context mContext;
    private List<Image> images;
    private String imgURL = "http://hitgid.com/images/%D1%84%D1%80%D1%83%D0%BA%D1%82%D1%8B-7.jpg";

    public ProductAdapter(ProductHeh[] products, Context context){
        mProductList = products;
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

        ProductHeh product = mProductList[position];
        holder.productImage.setImageResource(R.drawable.vitamins_fruit_logo);
        Glide.with(mContext).load(imgURL).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.productImage);

        holder.productName.setText(product.getName());
        holder.productFat.setText("" + product.getFat());
        holder.productProt.setText(""+product.getProtein());
        holder.productEnergy.setText(""+product.getEnergeticValue());
        holder.productCarb.setText(""+product.getCarb());
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder{
        public ImageView productImage;
        public TextView productName, productFat, productCarb, productEnergy, productProt;

        public ProductViewHolder(View view){
            super(view);
            productImage = (ImageView)view.findViewById(R.id.single_product_representation_image);
            productName = (TextView)view.findViewById(R.id.single_product_representation_name);
            productCarb = (TextView)view.findViewById(R.id.single_product_representation_carb_value);
            productEnergy = (TextView)view.findViewById(R.id.single_product_representation_energy_value);
            productFat = (TextView)view.findViewById(R.id.single_product_representation_fat_value);
            productProt = (TextView)view.findViewById(R.id.single_product_representation_prot_value);
        }
    }

    @Override
    public int getItemCount() {
        return mProductList.length;
    }

}
