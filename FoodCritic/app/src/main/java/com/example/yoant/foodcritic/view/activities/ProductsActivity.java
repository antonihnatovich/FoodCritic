package com.example.yoant.foodcritic.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.ProductAdapter;
import com.example.yoant.foodcritic.adapters.RecyclerViewItemClickListener;
import com.example.yoant.foodcritic.models.Product;
import com.example.yoant.foodcritic.helper.DividerItemDecoration;
import com.example.yoant.foodcritic.view.fragments.ProductDetailFragment;

public class ProductsActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final FragmentManager fragmentManager = getSupportFragmentManager();

        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_products_activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        ProductAdapter adapter = new ProductAdapter(Product.products, getApplicationContext());
//        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(getApplicationContext(), recyclerView, new RecyclerViewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", "Arbuz");
                bundle.putString("info", "BLA \n BLA \n BLA \n BLA \n BLA \n BLA \n BLA \n BLA \n " +
                        "BLA \n BLA \n BLA \n BLA \n BLA \n BLA \n " +
                        "BLA \n BLA \n BLA \n BLA \n BLA \n BLA \n BLA \n BLA \n " +
                        "BLA \n BLA \n BLA \n BLA \n BLA \n BLA \n " +
                        "BLA \n BLA \n BLA \n BLA \n BLA \n BLA \n BLA \n BLA \n " +
                        "BLA \n BLA \n BLA \n BLA \n BLA \n BLA \n " +
                        "BLA \n BLA \n BLA \n BLA \n BLA \n BLA \n BLA \n BLA \n " +
                        "BLA \n BLA \n BLA \n BLA \n BLA \n BLA \n ");
                productDetailFragment.setArguments(bundle);
                productDetailFragment.show(fragmentManager, "DialogFragment");
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "Hello from long " + position, Toast.LENGTH_SHORT).show();
            }
        }));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateProductActivity.class);
                startActivity(intent);
            }
        });
    }
}
