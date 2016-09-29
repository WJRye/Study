package com.wj.study.modules.design.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wj.study.R;
import com.wj.study.adapter.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class ItemFragment extends Fragment {

    private static String TAG = "TAG";

    private static final String TYPE = "type";
    public static final int TYPE_MUSIC = 1;
    public static final int TYPE_MOVIE = 2;
    public static final int TYPE_NOVEL = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static Fragment getInstance(int type) {
        ItemFragment itemFragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        itemFragment.setArguments(args);
        return itemFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        if (getArguments() != null) {
            int type = getArguments().getInt(TYPE);
            recyclerView.setAdapter(new ItemAdapter(getDatas(type)));
        }
        return view;
    }

    private List<String> getDatas(int type) {
        List<String> datas = new ArrayList<>();
        if (type == TYPE_MUSIC) {
            datas.add("Hello");
            datas.add("Turning table");
            datas.add("Lose yourself");
            datas.add("Not afraid");
            datas.add("Yes");
            datas.add("No");
            datas.add("You don't you're beautiful");
            datas.add("You're my sunshine");
            datas.add("You raise me up");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
            datas.add("天气这么热");
        } else if (type == TYPE_MOVIE) {
            datas.add("肖生克的救赎");
            datas.add("阿甘正传");
            datas.add("肖生克的救赎");
            datas.add("阿甘正传");
            datas.add("肖生克的救赎");
            datas.add("阿甘正传");
            datas.add("乱世佳人");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
            datas.add("肖生克的救赎");
        } else if (type == TYPE_NOVEL) {
            datas.add("卡拉马佐夫兄弟");
            datas.add("罪与罚");
            datas.add("战争与和平");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
            datas.add("卡拉马佐夫兄弟");
        }


        return datas;
    }


}
