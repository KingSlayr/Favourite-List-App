package com.mystartup.favlistapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoryItemsActivity extends AppCompatActivity {

    private RecyclerView itemsRecyclerView;
    private FloatingActionButton addItemFloatingActionButton;

    Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_items);

        category = (Category) getIntent().getSerializableExtra(MainActivity.CATEGORY_OBJECT_KEY);

        setTitle(category.getName());
        itemsRecyclerView = findViewById(R.id.items_recycler_view);

        itemsRecyclerView.setAdapter(new ItemsRecyclerAdapter(category));
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addItemFloatingActionButton = findViewById(R.id.add_item_button);

        addItemFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayItemCreationDialog();

            }
        });

    }



    private void displayItemCreationDialog() {


        final EditText itemEditText = new EditText(this);
        itemEditText.setInputType(InputType.TYPE_CLASS_TEXT);

        new AlertDialog.Builder(this)
                .setTitle(R.string.item_dialog_title)
                .setView(itemEditText)
                .setPositiveButton(R.string.positive_button_item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String itemName = itemEditText.getText().toString();
                        category.getItems().add(itemName);
                        ItemsRecyclerAdapter itemsRecyclerAdapter = (ItemsRecyclerAdapter) itemsRecyclerView.getAdapter();
                        itemsRecyclerAdapter.notifyItemInserted(category.getItems().size() - 1);
                        dialogInterface.dismiss();

                    }
                })
                .create()
                .show();


    }


    @Override
    public void onBackPressed() {

        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.CATEGORY_OBJECT_KEY, category);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }
}