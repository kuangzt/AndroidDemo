package com.davis.util;

import android.util.SparseArray;
import android.view.View;

public final class ViewHolder {
    public static <T > T getView(View view,int id){
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if(viewHolder==null){
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View idView = viewHolder.get(id);
        if(idView==null){
            idView = view.findViewById(id);
            viewHolder.put(id,idView);
        }
        return (T)idView;
    }
}
