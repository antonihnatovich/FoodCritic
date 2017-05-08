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
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.yoant.foodcritic.R;
public class CreateMenuProductFragment extends DialogFragment {


    public CreateMenuProductFragment() {
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.fragment_create_menu_product);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Spinner days = (Spinner) dialog.findViewById(R.id.spinner_dayname_create_menu);
        Spinner time = (Spinner)dialog.findViewById(R.id.spinner_timename_create_menu);

        ArrayAdapter<String> daysAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, dialog.getContext().getResources().getStringArray(R.array.day_name));
        ArrayAdapter<String> timesAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, dialog.getContext().getResources().getStringArray(R.array.time_name));
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        days.setAdapter(daysAdapter);
        time.setAdapter(timesAdapter);
        dialog.findViewById(R.id.create_menu_create).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                dismiss();
            }

        });

        dialog.findViewById(R.id.create_menu_cancel).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                dismiss();
            }

        });
        dialog.findViewById(R.id.create_menu_complex).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                dismiss();
            }
        });
        return dialog;
    }

}
