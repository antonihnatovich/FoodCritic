package com.example.yoant.foodcritic.view.fragments;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.yoant.foodcritic.R;

public class CreateProductFragment extends DialogFragment {


    public CreateProductFragment() {
    }


    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {



        Dialog dialog = new Dialog(getActivity());



        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        dialog.setContentView(R.layout.fragment_create_product);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        return dialog;

    }

}
