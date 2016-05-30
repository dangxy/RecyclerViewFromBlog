package com.example.dangxueyi.recyclerviewfromblog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dangxueyi.recyclerviewfromblog.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dangxueyi on 16/5/30.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyBaseViewHolder> {

    private Context context;
    private ArrayList<String>  arrayList;

    private List<Integer> mHeights;

    public RecycleViewAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        mHeights = new ArrayList<>();
        for(int i = 0 ; i<arrayList.size();i++){
            mHeights.add((int)(100+Math.random()*300));
        }
    }

    @Override
    public MyBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyBaseViewHolder myBaseViewHolder = new MyBaseViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_view,parent,false));

        return myBaseViewHolder;
    }

    @Override
    public void onBindViewHolder(MyBaseViewHolder holder, int position) {

       ViewGroup.LayoutParams lp = holder.textView.getLayoutParams();
        lp.height= mHeights.get(position);
        holder.textView.setLayoutParams(lp);

        holder.textView.setText(arrayList.get(position));
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyBaseViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyBaseViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.tv_textview);
        }

    }
}
