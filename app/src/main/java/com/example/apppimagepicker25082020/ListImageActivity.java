package com.example.apppimagepicker25082020;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;

import com.example.apppimagepicker25082020.databinding.ActivityHomeBinding;
import com.example.apppimagepicker25082020.databinding.ActivityListImageBinding;

import java.util.Random;

public class ListImageActivity extends AppCompatActivity {

    ActivityListImageBinding mBinding;
    String[] mArrayNameImages;
    int mColumn = 3;
    int mRow = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityListImageBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mArrayNameImages = getResources().getStringArray(R.array.array_name_images);

        for (int i = 1 ; i <= mRow ; i++){
            TableRow tableRow = new TableRow(this);
            for (int y = 1 ; y <= mColumn ; y++){
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.drawable.bo);
                tableRow.addView(imageView);
            }
            mBinding.tableLayout.addView(tableRow);
        }
    }
}