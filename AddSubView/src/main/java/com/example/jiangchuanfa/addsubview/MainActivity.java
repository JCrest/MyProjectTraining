package com.example.jiangchuanfa.addsubview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AddSubView.onChangeNumberListener {

    private AddSubView av_mAddSubView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        av_mAddSubView = (AddSubView) findViewById(R.id.av_mAddSubView);

        av_mAddSubView.setOnChangeNumberListener(this);
    }

    @Override
    public void onChangeNumber(int number) {
        Toast.makeText(MainActivity.this, "value==" + number, Toast.LENGTH_SHORT).show();
    }
}
