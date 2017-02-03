package com.tagdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends Activity {

    private SkillFlowTagLayout flow_skill_tag;
    private SkillTagAdapter mTagAdapter;
    private List<SkillBean.SkillsBean> list;
    private String inputStr;
    private StringBuilder sb, sb2;
    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flow_skill_tag = ((SkillFlowTagLayout) findViewById(R.id.flow_skill_tag));

        String string = "{\"skills\":[{\"name\":\"技术合伙人\",\"choosed\":false},{\"name\":\"APP开发\",\"choosed\":true},{\"name\":\"开发\",\"choosed\":true},{\"name\":\"市场销售\",\"choosed\":false},{\"name\":\"团队管理\",\"choosed\":false},{\"name\":\"资源整合\",\"choosed\":false},{\"name\":\"战略咨询\",\"choosed\":false},{\"name\":\"商业模式研究\",\"choosed\":false},{\"name\":\"领导执行力\",\"choosed\":false},{\"name\":\"渠道合作\",\"choosed\":false},{\"name\":\"风控审计\",\"choosed\":false},{\"name\":\"律师法务\",\"choosed\":false},{\"name\":\"项目融资\",\"choosed\":false},{\"name\":\"数据分析\",\"choosed\":false},{\"name\":\"软件开发\",\"choosed\":false},{\"name\":\"硬件研发\",\"choosed\":false},{\"name\":\"交互设计\",\"choosed\":false},{\"name\":\"测试运维\",\"choosed\":false},{\"name\":\"品牌公关\",\"choosed\":false},{\"name\":\"营销策划\",\"choosed\":false},{\"name\":\"运营推广\",\"choosed\":false},{\"name\":\"智能硬件\",\"choosed\":false},{\"name\":\"实体经营\",\"choosed\":false}]}\n";

        list = new ArrayList<>();

        SkillBean skillTagBean = new Gson().fromJson(string, SkillBean.class);
        if (skillTagBean != null && skillTagBean.getSkills() != null && skillTagBean.getSkills().size() > 0) {
            list.addAll(skillTagBean.getSkills());
            SkillBean.SkillsBean skillsBean = new SkillBean.SkillsBean();
            skillsBean.setName("+  添加");
            skillsBean.setChoosed(false);
            list.add(0, skillsBean);
            mTagAdapter = new SkillTagAdapter<>(this, 3);
            flow_skill_tag.setTagCheckedMode(SkillFlowTagLayout.FLOW_TAG_CHECKED_MULTI);
            flow_skill_tag.setAdapter(mTagAdapter);
            flow_skill_tag.setOnTagSelectListener(new OnSkillTagSelectListener() {
                @Override
                public void onItemSelect(SkillFlowTagLayout parent, List<Integer> selectedList) {
//                                if (selectedList != null && selectedList.size() > 0) {
//                                    sb = new StringBuilder();
////                                    sb2 = new StringBuilder();
//                                    for (int i : selectedList) {
//                                        Log.e("选择的position","i："+parent.getAdapter().getItem(i)+"，i："+i);
//                                        list.get(i).setChoosed(true);
//                                        sb.append(parent.getAdapter().getItem(i));
//                                        sb.append(",");
////                                        sb2.append(i);
////                                        sb2.append(",");
//                                    }
//                                    Log.e("选择的position","StringBuilder："+sb.toString());
//                                }
                }

                @Override
                public void onItemSelects(SkillFlowTagLayout parent, List<Map<Integer, Boolean>> selectedList) {
                    if (selectedList != null && selectedList.size() == 1) {
//                        for (Map map : selectedList) {
//                            sb = new StringBuilder();
//                            sb2 = new StringBuilder();
//                            Set set = map.keySet();
//                            Iterator iterator = set.iterator();
//                            while (iterator.hasNext()){
//                                Object key = iterator.next();
//                                Object value = map.get(key);
//                                list.get((Integer) key).setChoosed((Boolean) value);
//                                sb.append(list.get((Integer) key).getName()+",");
//                                sb2.append(value+",");
//                            }
//                        }
                        Map<Integer, Boolean> map = selectedList.get(0);
                        Set set = map.keySet();
                        Iterator iterator = set.iterator();
                        if (!iterator.hasNext()) {
                            Log.e("MainActivity","map中没有数据");
                            return;
                        }
                        int position = (int) iterator.next();
                        list.get(position).setChoosed(map.get(position));

                        Log.e("选择的position", "key值：" + position);
                        Log.e("选择的position", "value值：" + map.get(position));
                    } else {
                        Log.e("MainActivity", "点击事件返回的集合不正确");
                    }
                }

                @Override
                public void onShowInput(String show) {
                    if ("show".equals(show))
                        setDialog();
                }
            });
            mTagAdapter.onlyAddAll(list); //标签内容
            Log.e(TAG, "mTagAdapter.onlyAddAll(list)");
        }
    }

    /**
     * 手动输入标签
     */
    private void setDialog() {

        new MaterialDialog.Builder(this).content("添加技能标签")
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME | InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .inputRange(1, 20)
                .positiveText("添加")
                .negativeText("取消")
                .alwaysCallInputCallback()
                .input(null, null, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        inputStr = input.toString();
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (!TextUtils.isEmpty(inputStr)) {
                            SkillBean.SkillsBean skillsBean = new SkillBean.SkillsBean();
                            skillsBean.setName(inputStr);
                            skillsBean.setChoosed(true);

                            //不管是使用第一种还是第二种方式都必须要添加到MainActivity的集合中，
                            // 否则在onclick的时候会报IndexOutOfBoundException
                            list.add(skillsBean);

//                            mTagAdapter.onlyAddAll(list);//第一种方式
                            mTagAdapter.addData(skillsBean);//第二种方式
                        }
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                }).show();
    }
}
