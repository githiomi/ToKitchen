package org.githiomi.tokitchen.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import org.githiomi.tokitchen.R;
import org.githiomi.tokitchen.models.Constants;

public class SubCategoryActivity extends AppCompatActivity {

//    TAG
    private static final String TAG = SubCategoryActivity.class.getSimpleName();

//    Local Variables
    // The category name passed from main activity
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        // Getting the category name from the intent
        Intent fromMainActivity = getIntent();
        categoryName = fromMainActivity.getStringExtra(Constants.MAIN_CATEGORY_NAME);

        Toast.makeText(this, "New Activity: " + categoryName, Toast.LENGTH_SHORT).show();
    }
}