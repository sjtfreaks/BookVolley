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
import com.sjtfreaks.bookvolley.bean.Books;
import com.sjtfreaks.bookvolley.utils.PicassoUtils;

import java.util.List;

public class BooksAdapter extends BaseAdapter {

    private Context mContext;
    private List<Books> mList;
    private Books data;
    private LayoutInflater inflater;
    private WindowManager windowManager;
    private int width;

    public BooksAdapter (Context mContext, List<Books> mList){
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
            view = inflater.inflate(R.layout.item_books,null);
            viewHolder.tv_title = view.findViewById(R.id.tv_title);
            viewHolder.tv_author = view.findViewById(R.id.tv_author);
            viewHolder.tv_desc = view.findViewById(R.id.tv_desc);
            viewHolder.tv_last = view.findViewById(R.id.tv_last);
            viewHolder.iv_book = view.findViewById(R.id.iv_book);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        data = mList.get(i);
        viewHolder.tv_title.setText(data.getTitle());
        viewHolder.tv_author.setText(data.getAuthor());
        viewHolder.tv_desc.setText(data.getDesc());
        viewHolder.tv_last.setText(data.getLast());
        String Image = data.getCover();
        //指定大小
        PicassoUtils.loadImageViewSize(mContext,Image,viewHolder.iv_book,width,1500);

        return view;
    }


    class ViewHolder{
        private TextView tv_title;//标题
        private TextView tv_author;//作者
        private TextView tv_desc;
        private TextView tv_last;
        private ImageView iv_book;
    }
}
