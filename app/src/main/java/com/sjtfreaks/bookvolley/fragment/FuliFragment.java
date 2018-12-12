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
import com.sjtfreaks.bookvolley.adapter.FuliAdapter;
import com.sjtfreaks.bookvolley.bean.FuliData;
import com.sjtfreaks.bookvolley.utils.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FuliFragment extends Fragment {
    private ListView mListView;
    private List<FuliData> mList = new ArrayList<>();
    private List<String> mListTitle = new ArrayList<>();
    private List<String> mListUrl = new ArrayList<>();

    public static FuliFragment newInstance(String name){
        Bundle args = new Bundle();
        args.putString("name", name);
        FuliFragment fragment = new FuliFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fuli,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.mListView);
        mList.clear();
        String url = "https://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1";
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
            JSONArray jsonList = jsonObject.getJSONArray("results");

            for (int i = 0; i<jsonList.length();i++){
                JSONObject json = (JSONObject) jsonList.get(i);

                FuliData data = new FuliData();


                data.setDesc(json.getString("desc"));
                data.setUrl(json.getString("url"));


                mList.add(data);
            }
            FuliAdapter adapter = new FuliAdapter(getActivity(),mList);
            mListView.setAdapter(adapter);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
