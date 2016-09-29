package com.wj.study.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wj.study.R;
import com.wj.study.domain.Person;

import java.util.List;

public class CharacterViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_VIEW_TYPE_TITLE = 1;
    private static final int ITEM_VIEW_TYPE_CONTENT = 2;

    private List<Person> mPersons;

    public CharacterViewAdapter(List<Person> persons) {
        mPersons = persons;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_VIEW_TYPE_TITLE: {
                View titleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_view_title, null);
                titleView.setLayoutParams(new RecyclerView.LayoutParams(-1, parent.getContext().getResources().getDimensionPixelSize(R.dimen.size_25dp)));
                return new ViewCacheTitle(titleView);
            }
            case ITEM_VIEW_TYPE_CONTENT:
                View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_view_content, null);
                contentView.setLayoutParams(new RecyclerView.LayoutParams(-1, parent.getContext().getResources().getDimensionPixelSize(R.dimen.size_45dp)));
                return new ViewCacheContent(contentView);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Person person = mPersons.get(position);
        switch (person.getType()) {
            case Person.TYPE_TITLE: {
                ViewCacheTitle viewCacheTitle = (ViewCacheTitle) holder;
                viewCacheTitle.title.setText(person.getName());
                break;
            }
            case Person.TYPE_CONTENT: {
                ViewCacheContent viewCacheContent = (ViewCacheContent) holder;
                viewCacheContent.content.setText(person.getName());
                break;
            }
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (mPersons.get(position).getType()) {
            case Person.TYPE_TITLE:
                return ITEM_VIEW_TYPE_TITLE;
            case Person.TYPE_CONTENT:
                return ITEM_VIEW_TYPE_CONTENT;
            default:
                break;
        }
        return super.getItemViewType(position);
    }

    public static class ViewCacheTitle extends RecyclerView.ViewHolder {

        private TextView title;

        public ViewCacheTitle(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    public static class ViewCacheContent extends RecyclerView.ViewHolder {
        private TextView content;

        public ViewCacheContent(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }
}