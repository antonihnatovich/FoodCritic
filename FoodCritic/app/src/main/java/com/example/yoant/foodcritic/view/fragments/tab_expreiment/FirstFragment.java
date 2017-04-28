package com.example.yoant.foodcritic.view.fragments.tab_expreiment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.curentlyused.ProductAdapter;
import com.example.yoant.foodcritic.helper.sqlite.SQLiteDatabaseHelper;
import com.example.yoant.foodcritic.models.Product;
import com.example.yoant.foodcritic.view.activities.CreateProductActivity;

import java.util.Arrays;
import java.util.List;

public class FirstFragment extends Fragment {
    private static final String TAG = "Fruits";
    private RecyclerView mRecyclerView;
    private SQLiteDatabaseHelper mDatabase;
    private List<Product> mProducts;
    private Context mContext;
    private FloatingActionButton mFAB;
    private Intent mIntent;

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView1);
        mDatabase = SQLiteDatabaseHelper.getsInstance(getContext());
        mFAB = (FloatingActionButton)view.findViewById(R.id.floating_create_product);
        mIntent = new Intent(getActivity(), CreateProductActivity.class);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIntent.putExtra("type", TAG);
                startActivity(mIntent);
            }
        });
        setDataForAdapter();
        mContext = getContext();
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ProductAdapter adapter = new ProductAdapter(mProducts, mContext);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        setAnimationDecorator();
        setUpItemTouchHelper(mContext);

    }

    private void setDataForAdapter() {
        if (mDatabase.getAllProducts().isEmpty()) {
            Product[] products = Product.products;
            for (Product product : products)
                mDatabase.addProduct(product);
            mProducts = Arrays.asList(products);
        } else
            mProducts = mDatabase.getAllProducts();
    }

    private void setUpItemTouchHelper(final Context context) {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            Drawable background;
            Drawable deleteMark;
            int deleteMarkMargin;
            boolean isInitialised;

            private void initialise() {
                background = new ColorDrawable(Color.RED);
                deleteMark = ContextCompat.getDrawable(context, R.drawable.delete_24dp);
                deleteMark.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                deleteMarkMargin = (int) context.getResources().getDimension(R.dimen.delete_margin) + 20;
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
            public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
                if (!isInitialised)
                    initialise();

                if (parent.getItemAnimator().isRunning()) {
                    View firstViewUp = null;
                    View lastViewDown = null;

                    int left = 0;
                    int right = parent.getWidth();
                    int top = 0;
                    int bottom = 0;

                    int childCount = parent.getLayoutManager().getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View child = parent.getLayoutManager().getChildAt(i);
                        if (child.getTranslationY() < 0)
                            lastViewDown = child;
                        else if (child.getTranslationY() > 0)
                            if (firstViewUp == null)
                                firstViewUp = child;
                    }

                    if (lastViewDown != null && firstViewUp != null) {
                        top = lastViewDown.getBottom() + (int) lastViewDown.getTranslationY();
                        bottom = firstViewUp.getTop() + (int) firstViewUp.getTranslationY();
                    } else if (lastViewDown != null) {
                        top = lastViewDown.getBottom() + (int) lastViewDown.getTranslationY();
                        bottom = lastViewDown.getBottom();
                    } else if (firstViewUp != null) {
                        top = firstViewUp.getTop();
                        bottom = firstViewUp.getTop() + (int) firstViewUp.getTranslationY();
                    }
                    background.setBounds(left, top, right, bottom);
                    background.draw(canvas);
                }
                super.onDraw(canvas, parent, state);
            }
        });
    }


}
