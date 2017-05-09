package com.example.yoant.foodcritic.view.fragments;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.helper.sqlite.SQLiteDatabaseHelper;
import com.example.yoant.foodcritic.models.Product;

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

        final Spinner days = (Spinner) dialog.findViewById(R.id.spinner_dayname_create_menu);
        final Spinner time = (Spinner) dialog.findViewById(R.id.spinner_timename_create_menu);
        final EditText editName = (EditText) dialog.findViewById(R.id.edit_text_product_name_create_menu);
        final EditText editProt = (EditText) dialog.findViewById(R.id.edit_text_product_protein_create_menu);
        final EditText editFat = (EditText) dialog.findViewById(R.id.edit_text_product_fat_create_menu);
        final EditText editCarb = (EditText) dialog.findViewById(R.id.edit_text_product_carbon_create_menu);
        final EditText editEnerg = (EditText) dialog.findViewById(R.id.edit_text_product_energetic_create_menu);
        final EditText editWeight = (EditText) dialog.findViewById(R.id.edit_text_product_weight_create_menu);

        ArrayAdapter<String> daysAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, dialog.getContext().getResources().getStringArray(R.array.day_name));
        ArrayAdapter<String> timesAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, dialog.getContext().getResources().getStringArray(R.array.time_name));
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        days.setAdapter(daysAdapter);
        time.setAdapter(timesAdapter);
        dialog.findViewById(R.id.create_menu_create).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                String name = editName.getText().toString();
                String dayName = days.getSelectedItem().toString();
                String timeName = time.getSelectedItem().toString();
                int weight = Integer.parseInt(editWeight.getText().toString());
                double prot = Double.parseDouble(editProt.getText().toString());
                double fat = Double.parseDouble(editFat.getText().toString());
                double carb = Double.parseDouble(editCarb.getText().toString());
                double energy = Double.parseDouble(editEnerg.getText().toString());
                Product product = new Product(0, 0, name, "", energy, carb, prot, fat, "", weight, dayName, timeName);
                SQLiteDatabaseHelper databaseHelper = SQLiteDatabaseHelper.getsInstance(getContext());
                databaseHelper.addMenuProduct(product);
                databaseHelper.close();
                dismiss();
            }

        });
        Typeface roboto = Typeface.createFromAsset(getContext().getAssets(), "font/Roboto-Medium.ttf");
        Button button = (Button) dialog.findViewById(R.id.create_menu_create);
        Button button1 = (Button) dialog.findViewById(R.id.create_menu_cancel);
        button1.setTypeface(roboto);
        button.setTypeface(roboto);

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
