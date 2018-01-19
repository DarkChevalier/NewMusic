package com.sunzhibin.newmusic.ui;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.sunzhibin.newmusic.R;
import com.sunzhibin.newmusic.base.BaseAbstractActivity;
import com.sunzhibin.newmusic.base.factory.CreatePresenter;
import com.sunzhibin.newmusic.base.presenter.BasePresenter;
import com.sunzhibin.newmusic.base.view.IBaseView;
import com.sunzhibin.newmusic.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: sunzhibin
 * @date: 2018/1/3.
 * @description:
 * @e-mail:
 */
@CreatePresenter(BasePresenter.class)
public class MainActivity extends BaseAbstractActivity implements IBaseView, View.OnClickListener {
    private ViewPager mViewPager;
    private MainPagerAdapter mMianPagerAdapter;
    private List<Fragment> mListData;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView iv_music_list, iv_music_online, iv_music_group, iv_title_menu;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mViewPager = findViewById(R.id.viewPager);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        iv_title_menu = findViewById(R.id.iv_title_menu);
        iv_music_list = findViewById(R.id.iv_music_list);
        iv_music_online = findViewById(R.id.iv_music_online);
        iv_music_group = findViewById(R.id.iv_music_group);

        mListData = new ArrayList<>();
        mMianPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), mListData);
        mViewPager.setAdapter(mMianPagerAdapter);

        drawerLayout.setScrimColor(Color.TRANSPARENT);
        navigationView.inflateHeaderView(R.layout.nav_header_main);
        View headView = navigationView.getHeaderView(0);
        headView.findViewById(R.id.iv_avatar).setOnClickListener(this);
        headView.findViewById(R.id.tv_username).setOnClickListener(this);
        headView.findViewById(R.id.tv_level).setOnClickListener(this);
        headView.findViewById(R.id.ll_nav_homepage).setOnClickListener(this);
        headView.findViewById(R.id.ll_nav_scan_download).setOnClickListener(this);
        headView.findViewById(R.id.ll_nav_feedback).setOnClickListener(this);
        headView.findViewById(R.id.ll_nav_about).setOnClickListener(this);
        headView.findViewById(R.id.ll_nav_login).setOnClickListener(this);
        headView.findViewById(R.id.ll_nav_exit).setOnClickListener(this);

        iv_title_menu.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_menu:
                drawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.iv_avatar:
                //头像
                ToastUtil.shortShow("头像点击了");
                break;
            case R.id.tv_username:
                ToastUtil.shortShow("用户名点击了");
                break;
            case R.id.tv_level:
                ToastUtil.shortShow("等级点击了");
                break;
            case R.id.ll_nav_homepage:
                //跳转页面
                break;
            case R.id.ll_nav_scan_download:
                break;
            case R.id.ll_nav_feedback:
                break;
            case R.id.ll_nav_about:
                break;
            case R.id.ll_nav_login:
                break;
            case R.id.ll_nav_exit:
                break;
            default:
                break;
        }
    }


    static class MainPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentLists;

        public MainPagerAdapter(FragmentManager fm, List<Fragment> fragmentLists) {
            super(fm);
            this.fragmentLists = fragmentLists;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentLists.get(position);
        }

        @Override
        public int getCount() {
            return fragmentLists.size();
        }

    }


}
