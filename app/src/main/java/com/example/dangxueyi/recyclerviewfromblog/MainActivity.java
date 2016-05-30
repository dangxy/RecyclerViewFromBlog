package com.example.dangxueyi.recyclerviewfromblog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.dangxueyi.recyclerviewfromblog.adapter.RecycleViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private RecyclerView mRecyclerView;
    private ArrayList<String> arrayList;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mRecyclerView = (RecyclerView)findViewById(R.id.rv_base_function);
        initData();

    }

    private void initData() {

        arrayList = new ArrayList<>();
        for (int i = 'A'; i < 'Z'; i++) {
            arrayList.add("" + (char) i);
        }
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(new RecycleViewAdapter(mContext, arrayList));

    }


}
