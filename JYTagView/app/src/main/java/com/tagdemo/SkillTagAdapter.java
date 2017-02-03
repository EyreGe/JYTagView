package com.tagdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flynn on 16/12/26.
 */

public class SkillTagAdapter<T> extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private List<SkillBean.SkillsBean> mDataList;
    private int typeItem;
    private TextView textView;

    public SkillTagAdapter(Context context, int type) {
        this.mContext = context;
        mDataList = new ArrayList<>();
        this.typeItem = type;
    }

    @Override
    public int getCount() {
        return mDataList==null?0:mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.comment_tag_item, null);
        if (typeItem != 0 && typeItem == 1){
            textView = (TextView) view.findViewById(R.id.tv_tag);
        } else if (typeItem != 0 && typeItem == 2){
            textView = (TextView) view.findViewById(R.id.tv_questions_item);
        } else if (typeItem != 0 && typeItem == 3){
            if (position==0)
                textView = (TextView) view.findViewById(R.id.add_skill_tag);
            else
                textView = (TextView) view.findViewById(R.id.tv_skill_tag);
        }
        textView.setVisibility(View.VISIBLE);
        SkillBean.SkillsBean t = mDataList.get(position);
//        if (position!=0){
//            if(!mDataList.get(position).isChoosed()){
//                textView.setSelected(false);
//            } else {
//                textView.setSelected(true);
//            }
//        }
        if (t instanceof SkillBean.SkillsBean) {
            textView.setText(t.getName());
        }
        return view;
    }

    public void onlyAddAll(List<SkillBean.SkillsBean> datas) {
        mDataList.clear();
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void addData(SkillBean.SkillsBean data) {
        mDataList.add(data);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<SkillBean.SkillsBean> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

    @Override
    public boolean isSelectedPosition(int position) {
//        if (position % 2 == 0) {
//            return true;
//        }

        //之前是全部返回的是false
//        return false;
        //尝试更改返回的选中项
        return mDataList.get(position).isChoosed();
    }
}
