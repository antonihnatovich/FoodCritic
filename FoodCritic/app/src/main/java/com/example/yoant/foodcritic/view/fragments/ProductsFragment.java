package com.example.yoant.foodcritic.view.fragments;


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
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoant.foodcritic.R;
import com.example.yoant.foodcritic.adapters.rv_adapters.ProductAdapter;
import com.example.yoant.foodcritic.adapters.rv_adapters.RecyclerViewItemClickListener;
import com.example.yoant.foodcritic.helper.graphic.WithLogoDividerItemDecoration;
import com.example.yoant.foodcritic.helper.sqlite.SQLiteDatabaseHelper;
import com.example.yoant.foodcritic.models.Product;
import com.example.yoant.foodcritic.view.activities.CreateProductActivity;

import java.lang.annotation.Annotation;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemLongClick;
import butterknife.Unbinder;

public class ProductsFragment extends Fragment implements OnItemLongClick{
    private static final String KEY_TYPE = "type";
    @BindView(R.id.products_recycler) RecyclerView mRecyclerView;
    private SQLiteDatabaseHelper mDatabase;
    private List<Product> mProducts;
    private Context mContext;
    @BindView(R.id.floating_create_product) FloatingActionButton mFAB;
    private Intent mIntent;
    private String mType;
    private Unbinder unbinder;
    @OnClick(R.id.floating_create_product)
    public void onFABClick(){
        mIntent.putExtra("type", mType);
        startActivity(mIntent);
    }

    public static ProductsFragment newInstance(String type) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    public ProductsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        unbinder = ButterKnife.bind(this, view);
        mDatabase = SQLiteDatabaseHelper.getsInstance(getContext());
        mIntent = new Intent(getActivity(), CreateProductActivity.class);
        mType = getArguments().getString(KEY_TYPE);
        setDataForAdapter();
        mContext = getContext();
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProductAdapter adapter = new ProductAdapter(mProducts, mContext);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new WithLogoDividerItemDecoration(mContext, OrientationHelper.VERTICAL));
        mRecyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(getContext(), mRecyclerView, new RecyclerViewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            //TODO Remove dis shit from fragment and move it to ProductAdapter
            }

            @Override
            public void onLongItemClick(View view, int position) {
                ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                Bundle bundle = setUpBundleDetailProduct(mProducts.get(position));
                productDetailFragment.setArguments(bundle);
                productDetailFragment.show(getFragmentManager(), "DialogFragment");
            }
        }));
        adapter.notifyDataSetChanged();
        setAnimationDecorator();
        setUpItemTouchHelper(mContext);

    }

    private Bundle setUpBundleDetailProduct(Product product) {
        Bundle bundle = new Bundle();
        bundle.putString("type", mType);
        bundle.putString("name", product.getName());
        StringBuilder builder = new StringBuilder();
        builder.append("Name: ").append(product.getName()).append('\n')
                .append("Protein: ").append(product.getProtein()).append('\n')
                .append("Fat: ").append(product.getFat()).append('\n')
                .append("Carb: ").append(product.getCarb()).append('\n')
                .append("Energy: ").append(product.getEnergeticValue());
        bundle.putString("info", builder.toString());
        return bundle;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setDataForAdapter() {
        mProducts = mDatabase.getAllProducts(mType);
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

    @Override
    public int[] value() {
        return new int[0];
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
