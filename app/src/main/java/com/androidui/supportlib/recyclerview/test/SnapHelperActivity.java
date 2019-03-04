package com.androidui.supportlib.recyclerview.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;

import com.androidui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView item 居中对齐 的 Activity
 *
 * 关键字：SnapHelper
 *
 * Created by Tnno Wu on 2018/03/07.
 */

public class SnapHelperActivity extends AppCompatActivity {

    private static final String TAG = SnapHelperActivity.class.getSimpleName();

    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snaphelper_activity);

        initSnapHelperData();

        initView();
    }

    private void initSnapHelperData() {
        mList.add("亚特兰大老鹰");
        mList.add("夏洛特黄蜂");
        mList.add("迈阿密热火");
        mList.add("奥兰多魔术");
        mList.add("华盛顿奇才");
        mList.add("波士顿凯尔特人");
        mList.add("布鲁克林篮网");
        mList.add("纽约尼克斯");
        mList.add("费城76人");
        mList.add("多伦多猛龙");
        mList.add("芝加哥公牛");
        mList.add("克里夫兰骑士");
        mList.add("底特律活塞");
        mList.add("印第安纳步行者");
        mList.add("密尔沃基雄鹿");
        mList.add("达拉斯独行侠");
        mList.add("休斯顿火箭");
        mList.add("孟菲斯灰熊");
        mList.add("新奥尔良鹈鹕");
        mList.add("圣安东尼奥马刺");
        mList.add("丹佛掘金");
        mList.add("明尼苏达森林狼");
        mList.add("俄克拉荷马城雷霆");
        mList.add("波特兰开拓者");
        mList.add("犹他爵士");
        mList.add("金州勇士");
        mList.add("洛杉矶快船");
        mList.add("洛杉矶湖人");
        mList.add("菲尼克斯太阳");
        mList.add("萨克拉门托国王");
    }

    private void initView() {
        SnapHelperAdapter adapter = new SnapHelperAdapter(this);

        RecyclerView rcvSnapHelper = findViewById(R.id.rcv_snaphelper);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        rcvSnapHelper.setLayoutManager(manager);
        rcvSnapHelper.setHasFixedSize(true);
        rcvSnapHelper.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        rcvSnapHelper.setAdapter(adapter);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rcvSnapHelper);

        adapter.setSnapHelperDataList(mList);
    }
}