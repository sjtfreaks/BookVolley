package com.sjtfreaks.bookvolley.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjtfreaks.bookvolley.R;
import com.sjtfreaks.bookvolley.bean.FuliData;
import com.sjtfreaks.bookvolley.utils.PicassoUtils;

import java.util.List;

public class FuliAdapter extends BaseAdapter {
    private Context mContext;
    private List<FuliData> mList;
    private FuliData data;
    private LayoutInflater inflater;

    private WindowManager windowManager;
    private int width;

    public FuliAdapter (Context mContext, List<FuliData> mList){
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //get系统服务
        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        //获取屏幕宽度
        width = windowManager.getDefaultDisplay().getWidth();
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_fuli,null);
            viewHolder.tv_desc = view.findViewById(R.id.tv_desc_time);
            viewHolder.iv = view.findViewById(R.id.iv_fuli);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        data = mList.get(i);
        viewHolder.tv_desc.setText(data.getDesc());
        String Image = data.getUrl();
        //指定大小
        PicassoUtils.loadImageViewSize(mContext,Image,viewHolder.iv,width,1400);
        return view;
    }

    class ViewHolder{
        private TextView tv_desc;
        private ImageView iv;
    }
}
