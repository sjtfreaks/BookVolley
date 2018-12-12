package com.sjtfreaks.bookvolley.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.sjtfreaks.bookvolley.R;
import com.sjtfreaks.bookvolley.adapter.BooksAdapter;
import com.sjtfreaks.bookvolley.bean.Books;
import com.sjtfreaks.bookvolley.utils.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends Fragment {
    private ListView mListView;
    private List<Books> mList = new ArrayList<>();
    private List<String> mListTitle = new ArrayList<>();
    private List<String> mListUrl = new ArrayList<>();

    public static BookFragment newInstance(String name){
        Bundle args = new Bundle();
        args.putString("name", name);
        BookFragment fragment = new BookFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.mListView);
        mList.clear();
        String url = "https://novel.juhe.im/recommend/53115e30173bfacb4904897e";
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                //Toast.makeText(getActivity(),t, Toast.LENGTH_SHORT).show();
                parsingJson(t);
                L.i("json:"+t);
            }
        });
    }

    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonList = jsonObject.getJSONArray("books");

            for (int i = 0; i<jsonList.length();i++){
                JSONObject json = (JSONObject) jsonList.get(i);

                Books data = new Books();

                String Image = "http://statics.zhuishushenqi.com"+json.getString("cover");
                String title = json.getString("title");

                data.setTitle(json.getString("title"));
                data.setAuthor(json.getString("author"));
                data.setCover(Image);
                data.setDesc(json.getString("shortIntro"));
                data.setLast(json.getString("lastChapter"));

                mList.add(data);
                mListTitle.add(title);
            }
            BooksAdapter adapter = new BooksAdapter(getActivity(),mList);
            mListView.setAdapter(adapter);
        }catch (JSONException e){
            e.printStackTrace();
        }

    }



}
