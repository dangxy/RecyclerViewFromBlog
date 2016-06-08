package com.example.dangxueyi.recyclerviewfromblog.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.dangxueyi.recyclerviewfromblog.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FragmentMainActivity extends FragmentActivity implements View.OnClickListener {

    @Bind(R.id.bt_wrong_note)
    Button btWrongNote;
    @Bind(R.id.bt_collect)
    Button btCollect;
    @Bind(R.id.tv_latest)
    TextView tvLatest;
    @Bind(R.id.fragment)
    FrameLayout fragment;


    private  CollectFragment collectFragment;
    private  WrongNoteFragment wrongNoteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);
        ButterKnife.bind(this);

        initView();

        initData();

    }


    private void initView() {

        btWrongNote.setOnClickListener(this);
        btCollect.setOnClickListener(this);
    }

    private void initData(){

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        wrongNoteFragment = new WrongNoteFragment();

        fragmentTransaction.replace(R.id.fragment,wrongNoteFragment);

        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        switch (v.getId()) {

            case R.id.bt_collect:
                if (btCollect.isEnabled()) {
                    btCollect.setEnabled(false);
                    btWrongNote.setEnabled(true);
                    if(collectFragment==null){
                        collectFragment= new CollectFragment();

                    }
                    fragmentTransaction.replace(R.id.fragment,collectFragment);

                }

                break;

            case R.id.bt_wrong_note :
                if (btWrongNote.isEnabled()) {
                    btWrongNote.setEnabled(false);
                    btCollect.setEnabled(true);
                    if(wrongNoteFragment==null){
                        wrongNoteFragment= new WrongNoteFragment();

                    }

                    fragmentTransaction.replace(R.id.fragment,wrongNoteFragment);

                }
                break;

            case R.id.tv_latest:

                break;

        }
        fragmentTransaction.commit();
    }





}
