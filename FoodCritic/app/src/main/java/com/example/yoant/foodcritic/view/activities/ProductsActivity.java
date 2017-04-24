package com.example.yoant.foodcritic.view.activities;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.ProductAdapter;
import com.example.yoant.foodcritic.adapters.RecyclerViewItemClickListener;
import com.example.yoant.foodcritic.helper.DividerItemDecoration;
import com.example.yoant.foodcritic.helper.sqlite.SQLiteDatabaseHelper;
import com.example.yoant.foodcritic.models.Product;
import com.example.yoant.foodcritic.view.fragments.ProductDetailFragment;

import java.util.List;

public class ProductsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Product[] mProducts1;
    private SQLiteDatabaseHelper mDatabase;
    private FloatingActionButton mFloatButton;

    /*
    The values of protein, fat, carb, energy must be double and with MAX 2 digits after dot
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_products_activity);
        mFloatButton = (FloatingActionButton) findViewById(R.id.fab);

        mDatabase = SQLiteDatabaseHelper.getsInstance(getApplicationContext());
        setSupportActionBar(toolbar);
        setDataForAdapter();
        setUpRecyclerView();

        mRecyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(getApplicationContext(), mRecyclerView, new RecyclerViewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                Product product = mProducts1[position];
                Bundle bundle = setUpBundleDetailProduct(product);
                productDetailFragment.setArguments(bundle);
                productDetailFragment.show(fragmentManager, "DialogFragment");
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "Tipo Editing, Aga " + position, Toast.LENGTH_SHORT).show();
            }

            private Bundle setUpBundleDetailProduct(Product product) {
                Bundle bundle = new Bundle();
                bundle.putString("name", product.getName());
                StringBuilder builder = new StringBuilder();
                builder.append("Name: ").append(product.getName()).append('\n')
                        .append(" Protein: ").append(product.getProtein()).append('\n')
                        .append(" Fat: ").append(product.getFat()).append('\n')
                        .append(" Carb: ").append(product.getCarb());
                bundle.putString("info", builder.toString());
                return bundle;
            }
        }));

        mFloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateProductActivity.class);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

    private void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        ProductAdapter adapter = new ProductAdapter(mProducts1, getApplicationContext());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
        setAnimationDecorator();
        setUpItemTouchHelper();

    }

    private void setDataForAdapter() {
        if (mDatabase.getAllProducts().isEmpty()) {
            Product[] products2 = Product.products;
            for (Product product : products2)
                mDatabase.addProduct(product);
        }
        List<Product> products = mDatabase.getAllProducts();
        mProducts1 = new Product[products.size()];
        for (Product product : products)
            mProducts1[products.indexOf(product)] = product;
    }

    private void setUpItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            Drawable background;
            Drawable deleteMark;
            int deleteMarkMargin;
            boolean isInitialised;

            private void initialise() {
                background = new ColorDrawable(Color.RED);
                deleteMark = ContextCompat.getDrawable(ProductsActivity.this, R.drawable.delete_24dp);
                deleteMark.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                deleteMarkMargin = (int) ProductsActivity.this.getResources().getDimension(R.dimen.delete_margin)+20;
                isInitialised = true;
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                ProductAdapter productAdapter = (ProductAdapter) recyclerView.getAdapter();
                if (productAdapter.isPendingRemoval(position)) //TODO do this method
                    return 0;
                return super.getSwipeDirs(recyclerView, viewHolder);
            }


            @Override
            public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isActiveNow) {
                View itemView = viewHolder.itemView;

                if (viewHolder.getAdapterPosition() == -1)
                    return;

                if (!isInitialised)
                    initialise();

                background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                background.draw(canvas);

                int itemHeight = itemView.getBottom() - itemView.getTop();
                int intrinsicWidth = deleteMark.getIntrinsicWidth();
                int intrinsicHeight = deleteMark.getIntrinsicWidth();

                int deleteMarkLeft = itemView.getRight() - intrinsicWidth - deleteMarkMargin;
                int deleteMarkRight = itemView.getRight() - deleteMarkMargin;
                int deleteMarkTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
                int deleteMarkBottom = deleteMarkTop + intrinsicHeight;
                deleteMark.setBounds(deleteMarkLeft, deleteMarkTop, deleteMarkRight, deleteMarkBottom);

                deleteMark.draw(canvas);

                super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isActiveNow);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                ProductAdapter productAdapter = (ProductAdapter) mRecyclerView.getAdapter();
                productAdapter.pendingRemoval(position);
            }
        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void setAnimationDecorator() {
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {

            Drawable background;
            boolean isInitialised;

            private void initialise() {
                background = new ColorDrawable(Color.RED);
                isInitialised = true;
            }

            @Override
            public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state){
                if(!isInitialised)
                    initialise();

                if(parent.getItemAnimator().isRunning()){
                    View firstViewUp = null;
                    View lastViewDown = null;

                    int left = 0;
                    int right = parent.getWidth();
                    int top = 0;
                    int bottom = 0;

                    int childCount = parent.getLayoutManager().getChildCount();
                    for(int i = 0; i < childCount; i++){
                        View child = parent.getLayoutManager().getChildAt(i);
                        if(child.getTranslationY() < 0)
                            lastViewDown = child;
                        else if(child.getTranslationY() > 0)
                            if(firstViewUp == null)
                                firstViewUp = child;
                    }

                    if(lastViewDown != null && firstViewUp != null){
                        top = lastViewDown.getBottom() + (int)lastViewDown.getTranslationY();
                        bottom = firstViewUp.getTop() + (int)firstViewUp.getTranslationY();
                    } else if(lastViewDown != null){
                        top = lastViewDown.getBottom() + (int)lastViewDown.getTranslationY();
                        bottom = lastViewDown.getBottom();
                    } else if(firstViewUp != null){
                        top = firstViewUp.getTop();
                        bottom = firstViewUp.getTop() + (int)firstViewUp.getTranslationY();
                    }
                    background.setBounds(left, top, right, bottom);
                    background.draw(canvas);
                }
                super.onDraw(canvas, parent, state);
            }
        });
    }

}