package com.example.nestedscrollingdemo.ui.nested;


import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.nestedscrollingdemo.R;
import com.example.nestedscrollingdemo.adapter.BaseFragmentItemAdapter;
import com.example.nestedscrollingdemo.ui.TabFragment;
import com.example.nestedscrollingdemo.view.StickyNavLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  andy.xwt
 * Date:    2019-07-08 22:11
 * Description:
 */

public class NestedScrolling2DemoActivity extends AppCompatActivity {


    private TabLayout mTabLayout;

    private ViewPager mViewPager;
    private StickyNavLayout mStickyNavLayout;
    private ImageView mBackImageView;
    private TextView mTitleView;
    private LinearLayout ll;
    public static final int FRAGMENT_COUNT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scrolling2_demo);
        findView();
        initData();
    }

    private void findView() {
        mTabLayout = findViewById(R.id.sl_tab);
        mViewPager = findViewById(R.id.sl_viewpager);
        mStickyNavLayout = findViewById(R.id.sick_layout);
        mBackImageView = findViewById(R.id.iv_back);
      mTitleView = findViewById(R.id.tv_title);
      ll = findViewById(R.id.ll);
        initToolBar(R.drawable.ic_action_back_black, 0);
    }

    private void initData() {
        mViewPager.setAdapter(new BaseFragmentItemAdapter(getSupportFragmentManager(), initFragments(), initTitles()));
        mTabLayout.setupWithViewPager(mViewPager);
        mStickyNavLayout.setScrollChangeListener(new StickyNavLayout.ScrollChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onScroll(float moveRatio) {


               if ( mTitleView!=null){
                   Log.d("点击","点击事件来了"+moveRatio*500);
                   RelativeLayout.LayoutParams param= (RelativeLayout.LayoutParams) ll.getLayoutParams();
                   param.bottomMargin = (int) (250-moveRatio*500);
                   ll.setLayoutParams(param);
                 // ll.setBottom((int) (250-moveRatio*500));
               //  mTitleView.mar;
                   LinearLayout.LayoutParams params =(LinearLayout.LayoutParams) mTitleView.getLayoutParams();
                   params.gravity = Gravity.CENTER_HORIZONTAL;

                //   params.topMargin = (int) (250-moveRatio*500);
                mTitleView.setLayoutParams(params);
               }

               // params =(int) (250-moveRatio*500); //params.height-10;
             //   Log.d("点击","点击事件来了"+params.height);
//               mTitleView.setLayoutParams(params);
               // initToolBar(R.drawable.ic_action_back_white, 1);
            }
        });
    }

    private void initToolBar(@DrawableRes int backResId, float moveRatio) {
        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // finish();
//                Log.d("点击","点击事件来了");
//                ViewGroup.LayoutParams params = mTitleView.getLayoutParams();
//                params.height = params.height-10;
//                mTitleView.setLayoutParams(params);
            }
        });

//        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
//        int color = (int) argbEvaluator.evaluate(moveRatio, Color.WHITE, Color.BLACK);
//        Drawable wrapDrawable = DrawableCompat.wrap(getResources().getDrawable(backResId));
//        DrawableCompat.setTint(wrapDrawable, color);
//
//        mBackImageView.setImageDrawable(wrapDrawable);
//        mTitleView.setAlpha(moveRatio);

    }



    private List<Fragment> initFragments() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < FRAGMENT_COUNT; i++) {
            fragments.add(TabFragment.newInstance("NestedScrolling2Demo"));
        }
        return fragments;
    }

    private List<String> initTitles() {
        List<String> titles = new ArrayList<>();
        titles.add("首页");
        titles.add("全部");
        titles.add("作者");
        titles.add("专辑");
        return titles;
    }

}
