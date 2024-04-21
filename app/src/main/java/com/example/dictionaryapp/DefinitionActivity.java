package com.example.dictionaryapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class DefinitionActivity extends AppCompatActivity {
    private DatabaseAccess databaseAccess;

    private Toolbar toolbar;

    private TextView tvDefinition, tvWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);


        WebView myWebView = findViewById(R.id.webview);
        tvWord = findViewById(R.id.tv_word);

        Intent intent = getIntent();
        String word = intent.getStringExtra("word");
        String definition = intent.getStringExtra("definition");
        tvWord.setText(word);

        String htmlContent = generateHtmlContent(definition);
        myWebView.loadDataWithBaseURL(null, htmlContent, "text/html", "utf-8", null);

        toolbar = findViewById(R.id.toolbar_def);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Definition");

    }

    private String generateHtmlContent(String definition) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html><head><title>");
        html.append(definition); // Use the passed definition
        html.append("</title></head><body>");
        html.append("<h1>");
        html.append(definition); // Use the passed definition
        html.append("</h1>");
        html.append("<p>");
        html.append(Html.fromHtml(definition, Html.FROM_HTML_MODE_LEGACY)); // Format definition for HTML display
        html.append("</p>");
        html.append("</body></html>");
        return html.toString();
    }

    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu);
        inflater.inflate(R.menu.menu_back, menu);
        menu.findItem(R.id.app_bar_back).setOnMenuItemClickListener(item -> {
            onBackPressed();
            return true;
        });
    }

}