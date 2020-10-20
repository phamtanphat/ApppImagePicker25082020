package com.example.apppimagepicker25082020;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.apppimagepicker25082020.databinding.ActivityRandomBinding;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {

    ActivityRandomBinding mBinding;
    String[] mArrayNameImages;
    int mIdImageRandom = -1;
    int mIndex = -1;
    int mScore = 0;
    int REQUEST_CODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRandomBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initView();
        randomImage();
        countDownTime();
        event();
    }

    private void event() {
        mBinding.imgPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RandomActivity.this,ListImageActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }

    private void initView() {
        mBinding.tvScore.setText("Score : " + mScore);
    }

    private void countDownTime() {
        MyCountDown.getInstance().startTimeOut(4200 , 1000);
        MyCountDown.getInstance().setOnListenerCountDown(new OnListenerCountDown() {
            @Override
            public void onTick(long countDownInterval) {
                mBinding.tvTime.setText("Time : " + (countDownInterval / 1000));
            }

            @Override
            public void onFinish() {

            }
        });
    }

    private void randomImage() {
        mArrayNameImages = getResources().getStringArray(R.array.array_name_images);
        mIndex = new Random().nextInt(mArrayNameImages.length);
        mIdImageRandom = getResources().getIdentifier(
                mArrayNameImages[mIndex],
                "drawable",
                getPackageName()
        );
        mBinding.imgRandom.setImageResource(mIdImageRandom);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        MyCountDown.getInstance().stopCountDown();
        mBinding.tvTime.setText("Time : 0");
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            int idImageChoose = data.getIntExtra("idImage",-1);
            mBinding.imgPick.setImageResource(idImageChoose);
            if (idImageChoose == mIdImageRandom){
                Toast.makeText(this, "Chinh xac", Toast.LENGTH_SHORT).show();
                mBinding.tvScore.setText("Score : " + ++mScore);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        randomImage();
                        countDownTime();
                    }
                },2000);
            }else{
                Toast.makeText(this, "Ban da thua", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQUEST_CODE && resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Ket thuc : ", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}