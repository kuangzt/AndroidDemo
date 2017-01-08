package com.davis.recyclerview;

import android.content.Context;
import android.widget.TextView;

import com.davis.R;

import java.util.List;

public class AdAdapter extends QuickAdapter<AdBean> {

    public AdAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        if(viewType==AdBean.STYLE1){
            return R.layout.item_ad_recyclerview;
        }else {
            return R.layout.item2_ad_recyclerview;
        }

    }

    @Override
    public void convert(VH holder, AdBean data, int position) {
        TextView titleTV = holder.getView(R.id.title);
        titleTV.setText(data.title);
        TextView contentTV = holder.getView(R.id.content);
        contentTV.setText(data.content);
    }

    @Override
    public int getItemViewType(int position) {
        List<AdBean> dataList = getDataList();
        AdBean adBean = dataList.get(position);
        return adBean.style;
    }
}
