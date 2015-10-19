package com.example.sakanoken.game;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCollectionButton;
    private ImageView mCoin;
    private ImageView mcap;
    private ImageView mrotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
        init();
        frameAnimation(mCoin);
        frameAnimation(mcap);
        frameAnimation(mrotate);
    }

    void frameAnimation(View v) {

        if(v == mCoin) {
            v.setBackgroundResource(R.drawable.coin_animation_item);
        } else if(v == mcap) {
            v.setBackgroundResource(R.drawable.cap_animation_item);
        } else {
          v.setBackgroundResource(R.drawable.rotate_animation_item);
        }

        AnimationDrawable anim = (AnimationDrawable) v.getBackground();

        // 繰り返し設定
        anim.setOneShot(false);

        // アニメーション開始
        anim.start();
    }

    private void findviews() {
        mCollectionButton = (Button) findViewById(R.id.collection_button);
        mCoin = (ImageView) findViewById(R.id.coin);
        mcap = (ImageView) findViewById(R.id.cap);
        mrotate = (ImageView) findViewById(R.id.rotate);
    }

    private void init() {
        mCollectionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v == mCollectionButton) {
            intent = new Intent(this, Collection.class);
            startActivity(intent);
        }
    }
}
