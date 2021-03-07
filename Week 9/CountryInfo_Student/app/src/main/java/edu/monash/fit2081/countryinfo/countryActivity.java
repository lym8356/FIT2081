
package edu.monash.fit2081.countryinfo;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class countryActivity extends AppCompatActivity {
    private WebView wikipedia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Wikipedia Details");
        wikipedia = new WebView(this);
        setContentView(wikipedia);
        wikipedia.setWebViewClient(new WebViewClient());
        wikipedia.loadUrl(getIntent().getStringExtra("url"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
