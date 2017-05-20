package com.example.yoant.foodcritic.view.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.helper.sqlite.SQLiteDatabaseHelper;
import com.example.yoant.foodcritic.models.Product;

public class CreateMenuProductActivity extends AppCompatActivity {

    private String mType;
    private int mDayTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_menu_product);
        mType = getIntent().getExtras().getString("type");
        mDayTime = getIntent().getExtras().getInt("time");
        final Spinner days = (Spinner) findViewById(R.id.spinner_dayname_create_menu);
        final Spinner time = (Spinner) findViewById(R.id.spinner_timename_create_menu);
        final EditText editName = (EditText) findViewById(R.id.edit_text_product_name_create_menu);
        final EditText editProt = (EditText) findViewById(R.id.edit_text_product_protein_create_menu);
        final EditText editFat = (EditText) findViewById(R.id.edit_text_product_fat_create_menu);
        final EditText editCarb = (EditText) findViewById(R.id.edit_text_product_carbon_create_menu);
        final EditText editEnerg = (EditText) findViewById(R.id.edit_text_product_energetic_create_menu);
        final EditText editWeight = (EditText) findViewById(R.id.edit_text_product_weight_create_menu);

        ArrayAdapter<String> daysAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.getResources().getStringArray(R.array.day_name));
        ArrayAdapter<String> timesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.getResources().getStringArray(R.array.time_name));
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        days.setAdapter(daysAdapter);
        time.setAdapter(timesAdapter);
        days.setSelection(daysAdapter.getPosition(mType));
        time.setSelection(mDayTime);
        findViewById(R.id.create_menu_create).setOnClickListener(new View.OnClickListener() {

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
                SQLiteDatabaseHelper databaseHelper = SQLiteDatabaseHelper.getsInstance(getApplicationContext());
                databaseHelper.addMenuProduct(product);
                databaseHelper.close();
                finish();
            }

        });
        Typeface roboto = Typeface.createFromAsset(this.getAssets(), "font/Roboto-Medium.ttf");
        Button button = (Button) findViewById(R.id.create_menu_create);
        Button button1 = (Button) findViewById(R.id.create_menu_cancel);
        button1.setTypeface(roboto);
        button.setTypeface(roboto);

        findViewById(R.id.create_menu_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
