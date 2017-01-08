package com.davis.recyclerview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.davis.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDemo extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private List<AdBean> mDataList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        initData();
        init();
    }

    private void init(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        AdAdapter adAdapter = new AdAdapter(this);
        adAdapter.setDataList(mDataList);
        mRecyclerView.setAdapter(adAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        final Paint paint = new Paint();
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                c.save();
                String tv = "onDrawOver test";
                Rect textBounds = new Rect();
                paint.setTextSize(100);
                paint.setColor(Color.BLUE);
                paint.getTextBounds(tv, 0, tv.length(), textBounds);
                c.drawText(tv,0,textBounds.height(),paint);

                c.restore();

            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
            }
        });
    }

    private void initData(){
        mDataList = new ArrayList<>();
        for(int i=0;i<100;i++){
            AdBean adBean = new AdBean();
            adBean.title = "title"+i;
            adBean.content = "content"+i;
            if(i%2==0){
                adBean.style = AdBean.STYLE2;
            }
            mDataList.add(adBean);
        }

    }
}
