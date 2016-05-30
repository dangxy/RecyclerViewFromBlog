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

    private OnItemClickListener mOnItemClickListener;
    public RecycleViewAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        mHeights = new ArrayList<>();
        for(int i = 0 ; i<arrayList.size();i++){
            mHeights.add((int)(100+Math.random()*300));
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view ,int position);
        void onItemLongClickListener(View view ,int position);
    }

    public  void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener= onItemClickListener;
    }
    @Override
    public MyBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyBaseViewHolder myBaseViewHolder = new MyBaseViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_view,parent,false));

        return myBaseViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyBaseViewHolder holder, final int position) {

       ViewGroup.LayoutParams lp = holder.textView.getLayoutParams();
        lp.height= mHeights.get(position);
        holder.textView.setLayoutParams(lp);

        holder.textView.setText(arrayList.get(position));

        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos  = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClickListener(holder.itemView,pos);
                    deleteItem(position);
                    return false;
                }
            });
        }
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
   public void addItem (int position){
        arrayList.add(position,"insertData");
       notifyItemInserted(position);

    }

    public void deleteItem(int position){
        arrayList.remove(position);
        notifyItemRemoved(position);
    }

}
