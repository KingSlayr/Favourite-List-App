package com.mystartup.favlistapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.print.PrinterId;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryItemsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryItemsFragment extends Fragment {

    private RecyclerView itemsRecyclerView;

    Category category;

    private static final String CATEGORY_ARGS = "categoryargs";


    public CategoryItemsFragment() {
        // Required empty public constructor
    }

    public static CategoryItemsFragment newInstance(Category category) {

        CategoryItemsFragment categoryItemsFragment = new CategoryItemsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CATEGORY_ARGS, category);
        categoryItemsFragment.setArguments(bundle);
        return categoryItemsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            category = (Category) getArguments().getSerializable(CATEGORY_ARGS);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_category_items, container, false);

        if (view != null) {

            itemsRecyclerView = view.findViewById(R.id.items_recycler_view);
            itemsRecyclerView.setAdapter(new ItemsRecyclerAdapter(category));
            itemsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        }


        return view;
    }

    public void addItemToCategory(String item) {


        category.getItems().add(item);

        ItemsRecyclerAdapter itemsRecyclerAdapter = (ItemsRecyclerAdapter) itemsRecyclerView.getAdapter();
        itemsRecyclerAdapter.setCategory(category);
        itemsRecyclerAdapter.notifyDataSetChanged();




    }

}