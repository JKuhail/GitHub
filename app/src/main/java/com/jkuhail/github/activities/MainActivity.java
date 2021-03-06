package com.jkuhail.github.activities;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;
import com.jkuhail.github.R;
import com.jkuhail.github.Strategy.Context;
import com.jkuhail.github.Strategy.GitHubClick;
import com.jkuhail.github.Strategy.ProjectClick;
import com.jkuhail.github.app.Constants;
import com.jkuhail.github.models.AppOwner;


public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	private EditText etName, etGithubRepoName, etLanguage, etGithubUser;
	private TextInputLayout inputLayoutName, inputLayoutRepoName, inputLayoutGithubUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		etName			 = findViewById(R.id.etName);
		etGithubRepoName = findViewById(R.id.etRepoName);
		etLanguage 		 = findViewById(R.id.etLanguage);
		etGithubUser 	 = findViewById(R.id.etGithubUser);

		inputLayoutName   	  = findViewById(R.id.inputLayoutName);
		inputLayoutRepoName   = findViewById(R.id.inputLayoutRepoName);
		inputLayoutGithubUser = findViewById(R.id.inputLayoutGithubUser);
	}

	/** Save app username in SharedPreferences */
	public void saveName(View view) {

		if (isNotEmpty(etName, inputLayoutName)) {
			String personName = etName.getText().toString();

			//[Singleton Pattern] #4: Get the only object available
			AppOwner appOwner = AppOwner.getInstance();
			appOwner.setName(personName);
			//[Facade] #4: call the method to a particular object
			appOwner.print(this , personName);

		}
	}

	/** Search repositories on github */
	public void listRepositories(View view) {

		if (isNotEmpty(etGithubRepoName, inputLayoutRepoName)) {

			String queryRepo = etGithubRepoName.getText().toString();
			String repoLanguage = etLanguage.getText().toString();

			Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
			intent.putExtra(Constants.KEY_QUERY_TYPE, Constants.SEARCH_BY_REPO);
			intent.putExtra(Constants.KEY_REPO_SEARCH, queryRepo);
			intent.putExtra(Constants.KEY_LANGUAGE, repoLanguage);
			startActivity(intent);
		}
	}

	/** Search repositories of a particular github user */
	public void listUserRepositories(View view) {

		if (isNotEmpty(etGithubUser, inputLayoutGithubUser)) {

			String githubUser = etGithubUser.getText().toString();

			Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
			intent.putExtra(Constants.KEY_QUERY_TYPE, Constants.SEARCH_BY_USER);
			intent.putExtra(Constants.KEY_GITHUB_USER, githubUser);
			startActivity(intent);
		}
	}

	/** Validation */
	private boolean isNotEmpty(EditText editText, TextInputLayout textInputLayout) {
		if (editText.getText().toString().isEmpty()) {
			textInputLayout.setError("Cannot be blank !");
			return false;
		} else {
			textInputLayout.setErrorEnabled(false);
			return true;
		}
	}
	//[Strategy] #5: here we gonna use our methods
	public void gitHubWeb(View view){
		Context context = new Context(new GitHubClick());
		context.executeClick(this);
	}

	public void projectWeb(View view){
		Context context = new Context(new ProjectClick());
		context.executeClick(this);
	}

}
