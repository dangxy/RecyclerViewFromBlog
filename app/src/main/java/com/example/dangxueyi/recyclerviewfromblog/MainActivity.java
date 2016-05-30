package com.example.dangxueyi.recyclerviewfromblog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dangxueyi.recyclerviewfromblog.adapter.RecycleViewAdapter;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {



    private RecyclerView mRecyclerView;
    private ArrayList<String> arrayList;

    private Context mContext;
    private  RecycleViewAdapter mRecycleviewAdapter;

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

        mRecycleviewAdapter = new RecycleViewAdapter(mContext, arrayList);
        mRecyclerView.setAdapter(mRecycleviewAdapter);


        mRecycleviewAdapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {

                Toast.makeText(mContext,position+"click",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(mContext,position+"remove ",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_staggered,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.id_action_add:

                mRecycleviewAdapter.addItem(1);
                break;
            case R.id.id_action_delete:
                mRecycleviewAdapter.deleteItem(1);

                       break;

        }
        return super.onOptionsItemSelected(item);
    }
}
