package com.example.sakanoken.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class TitleActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mTitleButton;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        findviews();
        init();
    }

    private void releaseLinearLayout(){
        if(linearLayout != null){
            // background に画像を設定した場合はこっち
            linearLayout.setBackgroundDrawable(null);
        }
    }

    private void findviews() {
        mTitleButton = (Button) findViewById(R.id.title_button);
    }

    private void init() {
        mTitleButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }
}