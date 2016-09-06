package com.share.learn.view.tab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

public class TabsAdapter extends FragmentPagerAdapter implements
        ViewPager.OnPageChangeListener, TabsActionBar.TabListener {
    private final Context mContext;
    private final ViewPager mViewPager;
    private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
    private TabsActionBar mTabsActionBar;
    private OnViewPagerChangeListener onViewPagerChangeListener;

    public interface OnViewPagerChangeListener {
        void onChange(int position);
    }

    public TabsAdapter(FragmentActivity activity, ViewPager pager,
                       TabsActionBar tabsActionBar) {
        // TODO Auto-generated constructor stub
        super(activity.getSupportFragmentManager());
        mContext = activity;
        mViewPager = pager;
        mTabsActionBar = tabsActionBar;
        mViewPager.setAdapter(this);
        mViewPager.setOnPageChangeListener(this);
    }

    public TabsAdapter(FragmentActivity activity, ViewPager pager,
                       TabsActionBar tabsActionBar, OnViewPagerChangeListener listener) {
        // TODO Auto-generated constructor stub
        super(activity.getSupportFragmentManager());
        mContext = activity;
        mViewPager = pager;
        mTabsActionBar = tabsActionBar;
        mViewPager.setAdapter(this);
        mViewPager.setOnPageChangeListener(this);
        onViewPagerChangeListener = listener;

    }

    static final class TabInfo {
        private final Class<?> clss;
        private final Bundle args;

        TabInfo(Class<?> _class, Bundle _args) {
            clss = _class;
            args = _args;
        }
    }

    public void addTab(TabsActionBar.Tab tab, Class<?> clss, Bundle args) {
        TabInfo info = new TabInfo(clss, args);
        tab.setTag(info);
        tab.setTabListener(this);
        mTabs.add(info);
        mTabsActionBar.addTab(tab);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        // TODO Auto-generated method stub
        TabInfo info = mTabs.get(position);

        return Fragment.instantiate(mContext, info.clss.getName(), info.args);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mTabs.size();
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int position) {
        // TODO Auto-generated method stub
        mTabsActionBar.setSelectedNavigationItem(position);
        if (null != onViewPagerChangeListener) {
            onViewPagerChangeListener.onChange(position);
        }
    }

    @Override
    public void onTabSelected(TabsActionBar.Tab tab) {
        // TODO Auto-generated method stub
        Object tag = tab.getTag();
        for (int i = 0; i < mTabs.size(); i++) {
            if (mTabs.get(i) == tag) {
                mViewPager.setCurrentItem(i);
            }
        }
    }

    @Override
    public void onTabUnselected(TabsActionBar.Tab tab) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTabReselected(TabsActionBar.Tab tab) {
        // TODO Auto-generated method stub

    }

}
