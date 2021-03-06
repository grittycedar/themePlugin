package com.imesong.themedemo.fragement;

import com.imesong.themedemo.R;
import com.imesong.themedemo.activity.SettingActivity;
import com.imesong.themedemo.entity.News;
import com.imesong.themeplugin.BaseFragment;
import com.imesong.themeplugin.entity.AttrFactory;
import com.imesong.themeplugin.entity.DynamicAttr;
import com.imesong.themeplugin.util.CommonBaseAdapter;
import com.imesong.themeplugin.util.CommonViewHolder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ArticleListFragment extends BaseFragment {

    private TextView titleText;
    private TextView settingBtn;
    private ListView newsList;
    private RelativeLayout titleBarLayout;
    private NewsAdapter adapter;
    private List<News> datas;

    public static ArticleListFragment newInstance() {
        return new ArticleListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_article_list, container, false);
        initView(v);
        return v;
    }

    private void initData() {
        datas = new ArrayList<News>();
        for (int i = 0; i < 10; i++) {
            News news = new News();
            news.content = "Always listen to your heart because even though it's on your left side, it's " +
                    "always right.Remember, Hope is a good thing, maybe the best of things and no good " +
                    "thing ever dies!";
            news.title = "Tilte -------Title------ " + i;
            datas.add(news);
        }
    }

    private void initView(View v) {
        titleBarLayout = (RelativeLayout) v.findViewById(R.id.title_bar_layout);
        newsList = (ListView) v.findViewById(R.id.news_list_view);
        adapter = new NewsAdapter(this.getActivity(), datas);

        newsList.setAdapter(adapter);

        titleText = (TextView) v.findViewById(R.id.title_text);
        settingBtn = (TextView) v.findViewById(R.id.title_bar_setting_btn);

        titleText.setText("Small Article");

        settingBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

        // test for dynamicAddTitle()
        dynamicAddTitleView();
    }

    private void dynamicAddTitleView() {
        TextView textView = new TextView(getActivity());
        textView.setText("Small Article (动态new)");
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        param.addRule(RelativeLayout.CENTER_IN_PARENT);
        textView.setLayoutParams(param);
        textView.setTextColor(getActivity().getResources().getColor(R.color.color_title_bar_text));
        textView.setTextSize(20);
        titleBarLayout.addView(textView);

        List<DynamicAttr> mDynamicAttr = new ArrayList<DynamicAttr>();
        mDynamicAttr.add(new DynamicAttr(AttrFactory.TEXT_COLOR, R.color.color_title_bar_text));
        dynamicAddView(textView, mDynamicAttr);
    }

    private class NewsAdapter extends CommonBaseAdapter<News> {

        public NewsAdapter(Context context, List<News> mDatas) {
            super(context, mDatas, new int[]{R.layout.item_news_title});
        }

        @Override
        public void convertItemView(CommonViewHolder holder, News item, int position) {
            holder.setText(R.id.item_news_title, item.title);
            holder.setText(R.id.item_news_synopsis, item.content);
        }
    }
}
