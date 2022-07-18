package com.example.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private TextView mShowCount;
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.android.hellotoast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
//        if (savedInstanceState != null) {
//            mCount = savedInstanceState.getInt("count", 0);
//            if (mCount != 0) {
//                mShowCount.setText(String.format("%s", mCount));
//                Toast.makeText(this, mCount, Toast.LENGTH_SHORT).show();
//            }
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt("count", mCount);
        preferencesEditor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreData();
    }

    private void restoreData() {
        //read the data from the file
        SharedPreferences preferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        mCount = preferences.getInt("count", 0);
        //put it back into the edittext
        mShowCount.setText(String.valueOf(mCount));
        Toast.makeText(this, String.valueOf(mCount), Toast.LENGTH_SHORT).show();

    }

    public void showToast(View view) {
        Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show();
    }

    public void countUp(View view) {
        mCount++;
        if (mShowCount != null) {
            mShowCount.setText(String.valueOf(mCount));
        }
    }
}