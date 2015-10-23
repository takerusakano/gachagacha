package com.example.sakanoken.game;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCollectionButton;
    private ImageView mCoin;
    private ImageView mCap;
    private ImageView mRotate;
    private ImageView mGacha;
    private TextView mTimerText;
    private Button mStartButton;
    private Timer timer = null;
    private Timer timer2 = null;
    private Handler handle = new Handler();
    private ArrayList<AnimationDrawable> anim = new ArrayList<>();
    private int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
        init();
        timer2 = new Timer();
        timer2.schedule(new MyTimer3(), 1000, 100);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer = null;
        timer2.cancel();
        timer2 = null;
        handle = null;
    }

    void scaleAnimation(View v) {
        AnimationSet set = new AnimationSet(true);
        ScaleAnimation scale = new ScaleAnimation(1, 2f, 1, 2f); // imgを1倍から2倍に拡大
        scale.setDuration(3000); // 3000msかけてアニメーションする
        set.addAnimation(scale);
        RotateAnimation rotate = new RotateAnimation(0, -180, v.getWidth() / 2, v.getHeight() / 2); // imgの中心を軸に、0度から180度にかけて回転
        rotate.setDuration(3000);
        set.addAnimation(rotate);
        set.setFillAfter(true);
        v.startAnimation(set); // アニメーション適用
    }

    void frameAnimation(View v) {

        if (v == mCoin) {
            v.setBackgroundResource(R.drawable.coin_animation_item);
        } else if (v == mCap) {
            v.setBackgroundResource(R.drawable.cap_animation_item);
        } else {
            v.setBackgroundResource(R.drawable.rotate_animation_item);
        }

        anim.add((AnimationDrawable) v.getBackground());

        // 繰り返し設定
        anim.get(anim.size() - 1).setOneShot(false);

        // アニメーション開始
        anim.get(anim.size() - 1).start();
    }

    private void findviews() {
        mCollectionButton = (Button) findViewById(R.id.collection_button);
        mCoin = (ImageView) findViewById(R.id.coin);
        mCap = (ImageView) findViewById(R.id.cap);
        mRotate = (ImageView) findViewById(R.id.rotate);
        mGacha = (ImageView) findViewById(R.id.gacha);
        mStartButton = (Button) findViewById(R.id.start_button);
        mTimerText = (TextView) findViewById(R.id.timer);
    }

    private void init() {
        mCollectionButton.setOnClickListener(this);
        mStartButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v == mCollectionButton) {
            intent = new Intent(this, Collection.class);
            startActivity(intent);
        } else if (v == mStartButton) {
            timer = new Timer();
            frameAnimation(mCoin);
            timer.schedule(new MyTimer2(), 300);
        }
    }

    class MyTimer extends TimerTask {

        @Override
        public void run() {
            handle.post(new Runnable() {
                @Override
                public void run() {
                    for (AnimationDrawable a : anim) {
                        a.stop();
                    }
                    mGacha.setImageResource(R.drawable.other_gacha02_01);
                    scaleAnimation(mGacha);
                }
            });
        }
    }

    class MyTimer2 extends TimerTask {

        @Override
        public void run() {
            handle.post(new Runnable() {
                @Override
                public void run() {
                    timer.schedule(new MyTimer(), 2590);
                    mCoin.setVisibility(View.INVISIBLE);
                    frameAnimation(mCap);
                    frameAnimation(mRotate);
                }
            });
        }
    }

    class MyTimer3 extends TimerTask {
        int s = 0;
        int m = 0;

        @Override
        public void run() {
            if (cnt++ > 10) {
                s++;
                cnt = 0;
            }
            if (s > 60) {
                m++;
                s = 0;
            }
            handle.post(new Runnable() {
                @Override
                public void run() {
                    mTimerText.setText(m + ":" + s + "." + cnt);
                }
            });
        }
    }
}
