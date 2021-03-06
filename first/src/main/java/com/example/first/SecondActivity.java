package com.example.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mCurrentTv;
    private Button mNextBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testtask);
        init();
    }

    private void init() {
        mCurrentTv = (TextView) findViewById(R.id.currentTv);
        mNextBt = (Button) findViewById(R.id.nextBt);
        mNextBt.setOnClickListener(this);
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(this.getClass().getName());
        strBuilder.append("#task");
        strBuilder.append(this.getTaskId());
        mCurrentTv.setText(strBuilder.toString());
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.nextBt:
                Intent intent = new Intent(this,ThirdActivity.class);
                startActivity(intent);
                break;
        }
    }
}
