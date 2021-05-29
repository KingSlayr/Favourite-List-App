package com.mystartup.favlistapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements CategoryRecyclerAdapter.CategoryIsClickedInterface {

    private RecyclerView categoryRecyclerView;
    private CategoryManager mCategoryManager;

    public CategoryManager getmCategoryManager() {
        return mCategoryManager;
    }

    interface OnCategoryInteractionListener {
        void categoryIsTapped(Category category);
    }

    private OnCategoryInteractionListener listenerObject;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnCategoryInteractionListener) {

            listenerObject = (OnCategoryInteractionListener) context;
            mCategoryManager = new CategoryManager(context);

        } else {
            throw  new RuntimeException("Hey Developer. The context or activity must implement the OnCategoryInteractionListener interface");
        }
    }

    public CategoryFragment() {
        // Required empty public constructor
    }
    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Category> categories = mCategoryManager.retrieveCategories();
        if (getView() != null) {
            categoryRecyclerView = getView().findViewById(R.id.category_recyclerview);
            categoryRecyclerView.setAdapter(new CategoryRecyclerAdapter(categories, this));
            categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listenerObject = null;
    }


    // Interface method


    @Override
    public void categoryIsClicked(Category category) {

        listenerObject.categoryIsTapped(category);

    }

    // Helpful methods

    public void giveCategoryToManager(Category category) {


        mCategoryManager.saveCategory(category);

        CategoryRecyclerAdapter categoryRecyclerAdapter = (CategoryRecyclerAdapter) categoryRecyclerView.getAdapter();
        categoryRecyclerAdapter.addCategory(category);

    }

    public void saveCategory(Category category) {


        mCategoryManager.saveCategory(category);


        updateRecyclerView();

    }

    private void updateRecyclerView() {

        ArrayList<Category> categories = mCategoryManager.retrieveCategories();

        categoryRecyclerView.setAdapter(new CategoryRecyclerAdapter(categories, this));


    }

}