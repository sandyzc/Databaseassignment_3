package com.sandyz.databaseassignment3;

import android.graphics.Bitmap;

/**
 * Created by santosh on 05-02-2017.
 */

public class beans {

    private Bitmap bmp;
    private String name;
    private String age;

    public beans(Bitmap b, String n, String k) {
        bmp = b;
        name = n;
        age = k;
    }

    public Bitmap getBitmap() {
        return bmp;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

}
