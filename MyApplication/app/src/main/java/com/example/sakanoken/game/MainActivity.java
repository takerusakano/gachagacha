package com.example.sakanoken.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCollectionButton;
    private ImageView mCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
        init();
        frameAnimation(this, mCoin);
    }

    void frameAnimation(Context con, View v) {
        AnimationDrawable anim = new AnimationDrawable();

        // 画像の読み込み //
        Drawable frame1 = con.getResources().getDrawable(R.drawable.coin1);
        Drawable frame2 = con.getResources().getDrawable(R.drawable.coin2);
        Drawable frame3 = con.getResources().getDrawable(R.drawable.coin3);

        // 画像をアニメーションのコマとして追加していく
        anim.addFrame(frame1, 600);
        anim.addFrame(frame2, 600);
        anim.addFrame(frame3, 600);


        // 繰り返し設定
        anim.setOneShot(false);

        // ビューの背景画像にアニメーションを設定
        v.setBackground(anim);

        // アニメーション開始
        anim.start();
    }

    private void findviews() {
        mCollectionButton = (Button) findViewById(R.id.collection_button);
        mCoin = (ImageView) findViewById(R.id.coin);
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
