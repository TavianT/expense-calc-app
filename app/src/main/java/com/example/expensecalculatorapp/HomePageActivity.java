package com.example.expensecalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView linkedInButton;
    private ImageView githubButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        linkedInButton = findViewById(R.id.linkedInLogo);
        githubButton = findViewById(R.id.githubLogo);

        linkedInButton.setOnClickListener(this);
        githubButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent browserIntent;
        if (v == linkedInButton) {
            browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/tavian-taylor-86ba8b18a/"));
            startActivity(browserIntent);
        } else if (v == githubButton) {
            browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/TavianT/expense-calc-app"));
            startActivity(browserIntent);
        }
    }
}