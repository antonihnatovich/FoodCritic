package com.example.yoant.foodcritic.view.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.helper.sqlite.SQLiteDatabaseHelper;
import com.example.yoant.foodcritic.models.Product;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateMenuProductActivity extends AppCompatActivity {

    private String mType;
    private int mDayTime;

    @BindView(R.id.spinner_dayname_create_menu)
    Spinner days;
    @BindView(R.id.spinner_timename_create_menu)
    Spinner time;
    @BindView(R.id.edit_text_product_name_create_menu)
    EditText editName;
    @BindView(R.id.edit_text_product_protein_create_menu)
    EditText editProt;
    @BindView(R.id.edit_text_product_fat_create_menu)
    EditText editFat;
    @BindView(R.id.edit_text_product_carbon_create_menu)
    EditText editCarb;
    @BindView(R.id.edit_text_product_energetic_create_menu)
    EditText editEnerg;
    @BindView(R.id.edit_text_product_weight_create_menu)
    EditText editWeight;
    @BindView(R.id.create_menu_create)
    Button button;
    @BindView(R.id.create_menu_cancel)
    Button button1;

    @OnClick(R.id.create_menu_create)
    public void onClickCreate() {
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

    @OnClick(R.id.create_menu_cancel)
    public void onClickCancel() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_menu_product);
        ButterKnife.bind(this);

        mType = getIntent().getExtras().getString("type");
        mDayTime = getIntent().getExtras().getInt("time");
        ArrayAdapter<String> daysAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.getResources().getStringArray(R.array.day_name));
        ArrayAdapter<String> timesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.getResources().getStringArray(R.array.time_name));
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        days.setAdapter(daysAdapter);
        time.setAdapter(timesAdapter);
        days.setSelection(daysAdapter.getPosition(mType));
        time.setSelection(mDayTime);
        Typeface roboto = Typeface.createFromAsset(this.getAssets(), "font/Roboto-Medium.ttf");
        button1.setTypeface(roboto);
        button.setTypeface(roboto);
    }
}