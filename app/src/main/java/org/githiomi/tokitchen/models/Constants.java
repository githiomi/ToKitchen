package org.githiomi.tokitchen.models;

import android.content.Context;

import java.io.InputStream;

public class Constants {

//    Keys
    // Key for the main category name being passed to the sub category activity
    public static final String MAIN_CATEGORY_NAME = "Main Category";

    // This is the key for the string fragments will use to unwrap drink type names
    public static final String DRINK_TYPE_NAME = "Drink Type Name";

//    Methods
    //    Method that will return data file location
    public static String loadJsonFromAssets(Context context) {

        String jsonLocation = null;

        try {

            // Getting the file location
            String fileName = "source.json";
            InputStream inputStream = context.getAssets().open(fileName);

            int size = inputStream.available();

            byte[] byteArray = new byte[size];
            inputStream.read(byteArray);

            inputStream.close();

            jsonLocation = new String(byteArray, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonLocation;
    }
}
