package com.jkuhail.github.models;



import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import io.realm.RealmObject;


public class Owner extends RealmObject {

	private int id;

	private String login;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}



}
