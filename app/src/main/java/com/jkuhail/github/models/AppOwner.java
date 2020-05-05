package com.jkuhail.github.models;


/**In this model, I'm gonna implement 'Singleton Pattern' because we only need one instance of this class.
 * This Pattern improve our code by providing a global point of accessing its instance */
public class AppOwner {
    private  String name;

    //#1: create an object of AppOwner
    private static AppOwner appOwner;

    //#2:  private constructor to force use of getInstance() to create Singleton object
    private AppOwner() {}

    //#3: Implement getInstance() method to create Singleton object
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
}
