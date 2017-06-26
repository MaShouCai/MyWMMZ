package com.example.hasee.mywmmz.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;


import com.example.hasee.mywmmz.Fragment.BeautifulFragment;
import com.example.hasee.mywmmz.Fragment.CartFragment;
import com.example.hasee.mywmmz.Fragment.ClassifyFragment;
import com.example.hasee.mywmmz.Fragment.MineFragment;
import com.example.hasee.mywmmz.Fragment.ShopingFragment;
import com.example.hasee.mywmmz.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;

    private Fragment fragment;

    private List<Fragment> fragments = new ArrayList<>();
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, SplshActivity.class));
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(this);
        fragments.add(new ShopingFragment());
        fragments.add(new ClassifyFragment());
        fragments.add(new BeautifulFragment());
        fragments.add(new CartFragment());
        fragments.add(new MineFragment());
        currentFragment = fragments.get(0);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_fragment, currentFragment).commit();
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_shoping:
                fragment = fragments.get(0);
                break;
            case R.id.rb_classify:
                fragment = fragments.get(1);
                break;
            case R.id.rb_beautiful:
                fragment = fragments.get(2);
                break;
            case R.id.rb_cart:
                fragment = fragments.get(3);
                break;
            case R.id.rb_mine:
                fragment = fragments.get(4);
                break;
            default:
                break;

        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //第一种切换方式
        //使用replace方法，每次都会将之前的fragment替换掉，这样省内存（内存中始终只有一个fragment实例），但是fragment加载速度略慢
//                transaction.replace(R.id.ll, fragment).commit();

        //第二种切换方式，所有的fragment都添加到内存中，每次切换时，都将当前的fragment隐藏，将点击的fragment显示
        //首先判断即将要加载的fragment是否已经加载进来
        if (fragment.isAdded()) {
            transaction.hide(currentFragment).show(fragment);
        } else {
            transaction.hide(currentFragment).add(R.id.fl_fragment, fragment);
        }
        currentFragment = fragment;
        transaction.commit();
    }
}