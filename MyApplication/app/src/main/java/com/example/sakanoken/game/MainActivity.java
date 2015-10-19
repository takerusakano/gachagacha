package com.example.sakanoken.game;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCollectionButton;
    private ImageView mCoin;
    private ImageView mCap;
    private ImageView mRotate;
    private ImageView mGacha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
        init();
        scaleAnimation(mGacha);
        frameAnimation(mCoin);
        frameAnimation(mCap);
        frameAnimation(mRotate);
    }

    void scaleAnimation(View v) {
        AnimationSet set = new AnimationSet(true);
        ScaleAnimation scale = new ScaleAnimation(1, 2f, 1, 2f); // imgを1倍から2倍に拡大
        scale.setDuration(3000); // 3000msかけてアニメーションする
        set.addAnimation(scale);
        RotateAnimation rotate = new RotateAnimation(0, -360, v.getWidth()/2, v.getHeight()/2); // imgの中心を軸に、0度から360度にかけて回転
        rotate.setDuration(3000);
        set.addAnimation(rotate);
        v.startAnimation(set); // アニメーション適用
    }

    void frameAnimation(View v) {

        if(v == mCoin) {
            v.setBackgroundResource(R.drawable.coin_animation_item);
        } else if(v == mCap) {
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
        mCap = (ImageView) findViewById(R.id.cap);
        mRotate = (ImageView) findViewById(R.id.rotate);
        mGacha = (ImageView) findViewById(R.id.gacha);
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
