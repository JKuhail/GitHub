package com.jkuhail.github.Strategy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

//[Strategy] #4: we implemented the interface and modify our method.
public class GitHubClick implements OnClick {

    @Override
    public boolean click(Context context) {
        String url = "https://github.com/";
        Uri webPage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        context.startActivity(intent);
        return true;
    }
}

//[Strategy] #5: See Context.java