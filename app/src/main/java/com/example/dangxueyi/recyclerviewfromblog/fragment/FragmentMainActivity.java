package com.example.dangxueyi.recyclerviewfromblog.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
    private PopupWindow popupWindow;
    private  LinearLayout popup;


    private CollectFragment collectFragment;
    private WrongNoteFragment wrongNoteFragment;

    private CollectLatestFragment collectLatestFragment;

    private WrongNoteTipsFragment wrongNoteTipsFragment;


    public static final int WRONG_LATEST = 0;
    public static final int Collect_LATEST = 1;
    public static final int WRONG_TIPS = 2;
    public static final int Collect_TIPS = 3;

    public int currentType = WRONG_LATEST;

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
        tvLatest.setOnClickListener(this);
    }

    private void initData() {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        wrongNoteFragment = new WrongNoteFragment();

        fragmentTransaction.replace(R.id.fragment, wrongNoteFragment);
        currentType = WRONG_LATEST;

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
                    if (collectFragment == null) {
                        collectFragment = new CollectFragment();

                    }
                    fragmentTransaction.replace(R.id.fragment, collectFragment);
                    currentType = Collect_LATEST;

                }

                break;

            case R.id.bt_wrong_note:
                if (btWrongNote.isEnabled()) {
                    btWrongNote.setEnabled(false);
                    btCollect.setEnabled(true);
                    if (wrongNoteFragment == null) {
                        wrongNoteFragment = new WrongNoteFragment();

                    }

                    fragmentTransaction.replace(R.id.fragment, wrongNoteFragment);
                    currentType = WRONG_LATEST;
                }
                break;

            case R.id.tv_latest:

                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    initPopWindow();
                }
                break;

            case R.id.tv_popup_latest:

                if (currentType == WRONG_TIPS) {
                    if (wrongNoteFragment == null) {
                        wrongNoteFragment = new WrongNoteFragment();

                    }

                    fragmentTransaction.replace(R.id.fragment, wrongNoteFragment);
                    currentType = WRONG_LATEST;
                }
                if (currentType == Collect_TIPS) {
                    if (collectFragment == null) {
                        collectFragment = new CollectFragment();

                    }
                    fragmentTransaction.replace(R.id.fragment, collectFragment);
                    currentType = Collect_LATEST;
                }
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }

                break;

            case R.id.tv_popup_tips:
                popup.setBackgroundResource(R.mipmap.popu_window_back_ground_tips);
                if (currentType == WRONG_LATEST) {
                    if (wrongNoteTipsFragment == null) {
                        wrongNoteTipsFragment = new WrongNoteTipsFragment();

                    }

                    fragmentTransaction.replace(R.id.fragment, wrongNoteTipsFragment);
                    currentType = WRONG_TIPS;

                }

                if (currentType == Collect_LATEST) {

                    if (collectLatestFragment == null) {
                        collectLatestFragment = new CollectLatestFragment();

                    }

                    fragmentTransaction.replace(R.id.fragment, collectLatestFragment);
                    currentType = Collect_TIPS;

                }

                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }


                break;


        }
        fragmentTransaction.commit();
    }

    private void initPopWindow() {
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());

        View view = inflater.inflate(R.layout.popuwindow_layout_item, null);

        popupWindow = new PopupWindow(findViewById(R.id.fragment), 300, 300);


        popupWindow.setContentView(view);

        popupWindow.setOutsideTouchable(true);

        popupWindow.setFocusable(false);

        popupWindow.showAsDropDown(tvLatest, 0, 30);

        view.findViewById(R.id.tv_popup_latest).setOnClickListener(this);

        view.findViewById(R.id.tv_popup_tips).setOnClickListener(this);

        popup = (LinearLayout) view.findViewById(R.id.ll_popup);


    }
}
