package com.example.apppimagepicker25082020;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.apppimagepicker25082020.databinding.ActivityRandomBinding;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {

    ActivityRandomBinding mBinding;
    String[] mArrayNameImages;
    int mIdImageRandom = -1;
    int mIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRandomBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mArrayNameImages = getResources().getStringArray(R.array.array_name_images);
        mIndex = new Random().nextInt(mArrayNameImages.length);
        mIdImageRandom = getResources().getIdentifier(
                mArrayNameImages[mIndex],
                "drawable",
                getPackageName()
        );
        mBinding.imgRandom.setImageResource(mIdImageRandom);
    }
}