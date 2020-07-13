package org.githiomi.tokitchen.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.githiomi.tokitchen.R;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

//    TAG
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Method that will  the json file
        readJsonFile();
    }

//    Method to perform the call to read JSON data
    public void readJsonFile() {

        try {
//            Locating the file to read the data
            JSONObject jsonObject = new JSONObject(loadJsonFromAssets());

//            The key for the content I want to get
            String key = "meals";
            JSONArray mealCategories = jsonObject.getJSONArray(key);

            int mealCategoriesCount = mealCategories.length();
            for ( int i = 0; i < mealCategoriesCount; i += 1 ){

                JSONObject mealCategory = (JSONObject) mealCategories.get(i);
                String categoryName = mealCategory.getString("name");
                Log.d(TAG, "readJsonFile: One of the categories is: ---------- " + categoryName);


                    String mealCategoriesKey = "categories";
                    JSONArray breakfastCategories = mealCategory.getJSONArray(mealCategoriesKey);
                    int length = breakfastCategories.length();

                    for ( int d = 0; d < length; d += 1 ){

                        JSONObject breakfastCategory = (JSONObject) breakfastCategories.get(d);
                        String bCategoryName = breakfastCategory.getString("categoryName");
                        Log.d(TAG, "Breakfast categories: " + bCategoryName );

                    }


            }



        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

//    Method that will return data file location
    public String loadJsonFromAssets(){

        String jsonLocation = null;

        try {

            // Getting the file location
            String fileName = "source.json";
            InputStream inputStream = this.getAssets().open(fileName);

            int size = inputStream.available();

            byte[] byteArray = new byte[size];
            inputStream.read(byteArray);

            inputStream.close();

            jsonLocation = new String(byteArray, "UTF-8");

        }catch (Exception e){
            e.printStackTrace();
        }

        return jsonLocation;
    }
}