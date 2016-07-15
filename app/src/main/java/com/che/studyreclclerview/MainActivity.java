package com.che.studyreclclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ImageView;
import android.widget.TextView;

import com.che.studyreclclerview.item.BigBt;
import com.che.studyreclclerview.item.CZS;
import com.che.studyreclclerview.item.Group;
import com.che.studyreclclerview.item.LayoutWrapper;
import com.che.studyreclclerview.item.SmallBt;
import com.che.studyreclclerview.util.MultiType;
import com.che.studyreclclerview.util.MyDivider;
import com.che.studyreclclerview.util.SuperAdapter;
import com.che.studyreclclerview.util.ViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.rv_content)
    RecyclerView rvContent;

    private List<LayoutWrapper> data;
    private final int TYPE_GROUP = 0;
    private final int TYPE_BIGBT = 1;
    private final int TYPE_SMALLBT = 2;
    private final int TYPE_CZS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();

//        testMyAdapter();
//        testSingleAdapter();
//        testMultiAdapter();
        testSuperAdapter();
    }

    private void initView() {
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return data.get(position).getSpanSize();
            }
        });
        rvContent.setLayoutManager(layoutManager);
        rvContent.addItemDecoration(new MyDivider(this, LinearLayoutManager.VERTICAL));
        rvContent.setItemAnimator(new DefaultItemAnimator());
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBack);
        itemTouchHelper.attachToRecyclerView(rvContent);
    }

    ItemTouchHelper.Callback callBack = new ItemTouchHelper.Callback() {

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
            int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
            if (data.get(fromPosition).getLayoutId() != data.get(toPosition).getLayoutId()) {
                return false;
            }
            if(data.get(fromPosition).getLayoutId()==R.layout.item_group){
                return false;
            }
            if (fromPosition < toPosition) {
                //分别把中间所有的item的位置重新交换
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(data, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(data, i, i - 1);
                }
            }
            rvContent.getAdapter().notifyItemMoved(fromPosition, toPosition);
            //返回true表示执行拖动
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            data.remove(position);
            rvContent.getAdapter().notifyItemRemoved(position);
        }
    };
//
//    private void testMyAdapter() {
//        MyAdapter adapter = new MyAdapter(this);
//        rvContent.setAdapter(adapter);
//        adapter.setData(data);
//    }
//
//    private void testSingleAdapter() {
//        SingleAdapter<MyBean> adapter = new SingleAdapter<MyBean>(this, R.layout.item_text) {
//            @Override
//            protected void bindData(ViewHolder holder, MyBean item) {
//                TextView tvName = holder.getView(R.id.tv_name);
//                tvName.setText(item.getName());
//            }
//        };
//        rvContent.setAdapter(adapter);
//        adapter.setData(data);
//    }
//
//    private void testMultiAdapter() {
//        MultiAdapter<MyBean> adapter = new MultiAdapter<MyBean>(this) {
//            @Override
//            public int setViewType(MyBean myBean) {
//                if (myBean.getIcon() != 0) {
//                    return 1;
//                }
//                return 0;
//            }
//
//            @Override
//            public int getLayoutId(int viewType) {
//                if (viewType == 1) {
//                    return R.layout.item_multi;
//                }
//                return R.layout.item_text;
//            }
//
//            @Override
//            protected void bindData(ViewHolder holder, MyBean item, int viewType) {
//                if (item.getIcon() == 0) {
//                    TextView tvName = holder.getView(R.id.tv_name);
//                    tvName.setText(item.getName());
//                } else {
//                    ImageView ivIcon = holder.getView(R.id.iv_icon);
//                    TextView tvName = holder.getView(R.id.tv_name);
//                    ivIcon.setImageResource(R.drawable.youdao);
//                    tvName.setText(item.getName());
//                }
//            }
//        };
//        rvContent.setAdapter(adapter);
//        adapter.setData(data);
//    }

    private void testSuperAdapter() {
        MultiType<LayoutWrapper> multiType = new MultiType<LayoutWrapper>() {
            @Override
            public int setViewType(LayoutWrapper layoutWrapper) {
                switch (layoutWrapper.getLayoutId()) {
                    case R.layout.item_group:
                        return TYPE_GROUP;
                    case R.layout.item_bigbt:
                        return TYPE_BIGBT;
                    case R.layout.item_smallbt:
                        return TYPE_SMALLBT;
                    case R.layout.item_czs:
                        return TYPE_CZS;
                }
                return 0;
            }

            @Override
            public int getLayoutId(int viewType) {
                switch (viewType) {
                    case TYPE_GROUP:
                        return R.layout.item_group;
                    case TYPE_BIGBT:
                        return R.layout.item_bigbt;
                    case TYPE_SMALLBT:
                        return R.layout.item_smallbt;
                    case TYPE_CZS:
                        return R.layout.item_czs;
                }
                return R.layout.item_group;
            }
        };
        SuperAdapter<LayoutWrapper> adapter = new SuperAdapter<LayoutWrapper>(this, multiType) {
            @Override
            protected void bindData(ViewHolder holder, LayoutWrapper item) {
                switch (item.getLayoutId()) {
                    case R.layout.item_group:
                        updateGroup(holder, item);
                        break;
                    case R.layout.item_bigbt:
                        updateBigbt(holder, item);
                        break;
                    case R.layout.item_smallbt:
                        updateSmallbt(holder, item);
                        break;
                    case R.layout.item_czs:
                        updateCzs(holder, item);
                        break;
                }
            }
        };
        rvContent.setAdapter(adapter);
        adapter.setData(data);
    }

    private void updateGroup(ViewHolder holder, LayoutWrapper item) {
        TextView tvName = holder.getView(R.id.tv_name);

        Group group = (Group) item.getData();
        tvName.setText(group.getName());
    }

    private void updateBigbt(ViewHolder holder, LayoutWrapper item) {
        ImageView ivIcon = holder.getView(R.id.iv_icon);
        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvDesc = holder.getView(R.id.tv_desc);

        BigBt bigBt = (BigBt) item.getData();
        ivIcon.setImageResource(bigBt.getIcon());
        tvTitle.setText(bigBt.getName());
        tvDesc.setText(bigBt.getDesc());
    }

    private void updateSmallbt(ViewHolder holder, LayoutWrapper item) {
        ImageView ivIcon = holder.getView(R.id.iv_icon);
        TextView tvName = holder.getView(R.id.tv_name);

        SmallBt smallBt = (SmallBt) item.getData();
        ivIcon.setImageResource(smallBt.getIcon());
        tvName.setText(smallBt.getName());
    }

    private void updateCzs(ViewHolder holder, LayoutWrapper item) {
        ImageView ivIcon = holder.getView(R.id.iv_icon);
        TextView tvName = holder.getView(R.id.tv_name);

        CZS czs = (CZS) item.getData();
        ivIcon.setImageResource(czs.getIcon());
        tvName.setText(czs.getName());
    }

    private void initData() {
        data = new ArrayList<>();

        data.add(new LayoutWrapper(R.layout.item_group, 4, new Group("车城服务")));
        data.add(new LayoutWrapper(R.layout.item_bigbt, 2, new BigBt(R.drawable.icon_buycar, "帮您买车", "特价新车 二手车帮买")));
        data.add(new LayoutWrapper(R.layout.item_bigbt, 2, new BigBt(R.drawable.icon_salecar, "帮您卖车", "在线估价 上门检测")));
        data.add(new LayoutWrapper(R.layout.item_smallbt, 1, new SmallBt(R.drawable.icon_renzheng, "认证服务")));
        data.add(new LayoutWrapper(R.layout.item_smallbt, 1, new SmallBt(R.drawable.icon_zhihuan, "置换")));
        data.add(new LayoutWrapper(R.layout.item_smallbt, 1, new SmallBt(R.drawable.icon_fenqi, "分期购车")));
        data.add(new LayoutWrapper(R.layout.item_smallbt, 1, new SmallBt(R.drawable.icon_chezhushou, "车城设置")));


        data.add(new LayoutWrapper(R.layout.item_group, 4, new Group("车助手")));
        data.add(new LayoutWrapper(R.layout.item_czs, 1, new CZS(R.drawable.icon_weizhang, "违章查询")));
        data.add(new LayoutWrapper(R.layout.item_czs, 1, new CZS(R.drawable.icon_gujia, "卖车估价")));
        data.add(new LayoutWrapper(R.layout.item_czs, 1, new CZS(R.drawable.icon_jiance, "帮您检测")));
        data.add(new LayoutWrapper(R.layout.item_czs, 1, new CZS(R.drawable.icon_xianqian, "限迁查询")));

    }

}
