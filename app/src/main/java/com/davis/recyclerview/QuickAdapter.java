package com.davis.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class QuickAdapter <T> extends RecyclerView.Adapter<QuickAdapter.VH>{

    private Context mContext;
    private List<T> mDataList;
    public QuickAdapter(Context context) {
        this.mContext = context;
    }

    public void setDataList(List<T> dataList){
        this.mDataList = dataList;
    }

    public List<T> getDataList(){
        return this.mDataList;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return  VH.get(parent,getItemLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        convert(holder,mDataList.get(position),position);
    }

    @Override
    public int getItemCount() {
        if(mDataList==null){
            return 0;
        }
        return mDataList.size();
    }

    public abstract int getItemLayoutId(int viewType);

    public abstract void convert(VH holder, T data, int position);

    public static class VH extends RecyclerView.ViewHolder{
        private SparseArray<View> mViews;
        private VH(View itemView){
            super(itemView);
            mViews = new SparseArray<>();
        }

        public static VH get(ViewGroup parent, int layoutId){
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new VH(convertView);
        }

        public <T extends View> T getView(int id){
            View v = mViews.get(id);
            if(v == null){
                v = itemView.findViewById(id);
                mViews.put(id, v);
            }
            return (T)v;
        }
    }
}
