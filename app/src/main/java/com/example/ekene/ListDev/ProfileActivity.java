package com.example.ekene.ListDev;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.ekene.ListDev.R.layout.activity_profile);

        ImageView profileImageView = (ImageView) findViewById(com.example.ekene.ListDev.R.id.profileImageView);
        TextView userNameTextView = (TextView) findViewById(com.example.ekene.ListDev.R.id.usernameTextView);
        TextView shareProfile = (TextView) findViewById(com.example.ekene.ListDev.R.id.shareProfile);
        TextView developerUrl = (TextView) findViewById(com.example.ekene.ListDev.R.id.developerUrl);


        Intent intent = getIntent();
        final String userName = intent.getStringExtra(com.example.ekene.ListDev.DevelopersAdapter.KEY_NAME);
        String image = intent.getStringExtra(com.example.ekene.ListDev.DevelopersAdapter.KEY_IMAGE);
        final String profileUrl = intent.getStringExtra(com.example.ekene.ListDev.DevelopersAdapter.KEY_URL);


        Picasso.with(this)
                .load(image)
                .into(profileImageView);

        userNameTextView.setText(userName);
        developerUrl.setText(profileUrl);

        developerUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = profileUrl;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


        shareProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome developer " + userName +
                        ", " + profileUrl);
                Intent chooser = Intent.createChooser(shareIntent, "Share via");
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });


    }
}
