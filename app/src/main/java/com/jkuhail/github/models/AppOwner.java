package com.jkuhail.github.models;


import android.content.Context;
import android.widget.Toast;

/**In this model, I'm gonna implement 'Singleton Pattern' because we only need one instance of this class.
 * This Pattern improve our code by providing a global point of accessing its instance */

public class AppOwner implements Model {
    private  String name;

    //[Singleton] #1: create an object of AppOwner
    private static AppOwner appOwner;

    //[Singleton] #2:  private constructor to force use of getInstance() to create Singleton object
    private AppOwner() {}

    //[Singleton] #3: Implement getInstance() method to create Singleton object
    public static AppOwner getInstance() {
        if (appOwner==null)
            appOwner = new AppOwner();
        return appOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //[Facade] #3: implement the required methods
    @Override
    public void print(Context context, String msg) {
        Toast.makeText(context, "This owner name is: " + msg, Toast.LENGTH_SHORT).show();
    }
}
//[Singleton] see #4 in ManiActivity line 50
//[Facade] see #4 in ManiActivity line 53
