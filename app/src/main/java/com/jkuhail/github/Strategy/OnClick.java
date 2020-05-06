package com.jkuhail.github.Strategy;

import android.content.Context;

/** Here I'm gonna implement 'Strategy design pattern' because it allows us to
 *  indirectly alter the object’s behavior at runtime by associating it with different
 *  sub-objects which can perform specific sub-tasks in different ways.
 *
 *  The purpose of using this pattern in our project is defining different behaviors for
 *  click() method in order to use it in tow buttons in the main UI.
 *
 *  this pattern allows us to use the same method to do different tasks in different
 *  instances of different types.*/

//[Strategy] #1: Create an interface
public interface OnClick {
    boolean click(Context context);
}

//[Strategy] #2: Make all model classes implements this interface
//[Strategy] #3: See GitHubClick.java and ProjectClick.java
