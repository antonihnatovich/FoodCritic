package com.example.yoant.foodcritic.view.fragments;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yoant.foodcritic.R;

public class ProductDetailFragment extends DialogFragment {
    private String mProductName;
    private String mProductInfo;
    private ImageView mImageView;
    private String mType;

    @Override
    public void setArguments(Bundle bundle) {
        mType = bundle.getString("type");
        mProductName = bundle.getString("name");
        mProductInfo = bundle.getString("info");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.fragment_product_detail);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView textView = (TextView) dialog.findViewById(R.id.detail_textview);
        TextView textViewName = (TextView) dialog.findViewById(R.id.detail_name);
        textView.setText(mProductInfo);
        textViewName.setText(mProductName);
        mImageView = (ImageView) dialog.findViewById(R.id.detail_imageview);
        switch (mType) {
            case "FRUIT":
                mImageView.setImageResource(R.drawable.vitamins_fruit_logo);
                break;
            case "VEGETABLE":
                mImageView.setImageResource(R.drawable.vitamins_vegetable_logo);
                break;
            case "DRINK":
                mImageView.setImageResource(R.drawable.vitamins_drink_logo);
                break;
            case "BAKE":
                mImageView.setImageResource(R.drawable.vitamins_bake_logo);
                break;
            case "CEREAL":
                mImageView.setImageResource(R.drawable.vitamins_cereals_logo);
                break;
            case "DISH":
                mImageView.setImageResource(R.drawable.vitamins_dishes_logo);
                break;
            default:
                mImageView.setImageResource(R.drawable.delete_24dp);
                break;
        }
        dialog.findViewById(R.id.detail_button).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                dismiss();
            }

        });
        dialog.findViewById(R.id.detail_complex).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                dismiss();
            }
        });
        dialog.findViewById(R.id.detail_button).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                dismiss();
            }

        });
        return dialog;
    }
}
