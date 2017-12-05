package com.example.smartrefreshlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.header.DropboxHeader;
import com.scwang.smartrefresh.header.FlyRefreshHeader;
import com.scwang.smartrefresh.header.FunGameBattleCityHeader;
import com.scwang.smartrefresh.header.FunGameHitBlockHeader;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.header.StoreHouseHeader;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RefreshLayout refreshLayout;
    private List<String> list;
    private ArrayAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        listView = findViewById(R.id.listview);
        //初始化数据
        initData();
        /**
         *  设置 Header的风格如下：
         *  //DropboxHeader 掉落盒子     DeliveryHeader 气球传递
         //BezierRadarHeader 贝塞尔雷达   BezierCircleHeader 弹出圈圈
         //FlyRefreshHeader 纸飞机  ClassicsHeader 金典
         //PhoenixHeader 金色校园  TaurusHeader冲上云霄
         //FunGameBattleCityHeader 战争城市 FunGameHitBlockHeader 休闲砖块
         //WaterDropHeader 苹果水滴    StoreHouseHeader 闪光文字
         //WaveSwipeHeader 全屏水波    MaterialHeader 官方主题
         */

        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        //设置 Footer的风格
        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                for (int i = 3; i > 0; i--) {
                    list.add(0, "NEW DATA" + i);
                }
                adapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                for (int i = 0; i < 3; i++) {
                    list.add("LOAD MORE" + i);
                }
                adapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore(2000);
            }
        });
    }

    private void initData() {

        list = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            list.add("初始数据" + i);
        }
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

    }
}
