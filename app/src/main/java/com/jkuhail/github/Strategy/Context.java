package com.jkuhail.github.Strategy;


//[Strategy] #5: we created our Context class, here we gonna gonna implement the main method of our pattern
// we use it to call our methods in other classes
//check gitHubWeb(View view) and projectWeb(View view) in MainActivity.java (line 103-107)
public class Context {
    private OnClick onClick;

    public Context(OnClick onClick){
        this.onClick = onClick;
    }

    public boolean executeClick(android.content.Context context){
        return onClick.click(context);
    }
}
