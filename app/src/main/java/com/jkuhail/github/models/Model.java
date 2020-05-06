package com.jkuhail.github.models;


import android.content.Context;

/**I'm gonna implement 'facade design pattern' because it allows us to provide
 *  a shortcut to the most-used features of the system by defining an entry point to each subsystem.
 *  I'm gonna use this pattern to create a print() method which will show a Toast message in the UI
 *  to notify the user about the class owner of each instance in the screen*/
//[Facade] #1: Create an interface
public interface Model {
    void print(Context context, String msg);
}

//[Facade] #2: Make all model classes implements this interface
//[Facade] #3: See AppOwner.java and Repository.java