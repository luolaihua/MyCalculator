package com.luo.matrixcaculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.luo.matrixcaculator.robo.ChatMainActivity;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                Toast.makeText(MyApplication.getContext(),"功能正在开发...",Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(MyApplication.getContext(),TwoCompute.class);
                startActivity(intent);finish();*/break;
            case R.id.author:
                Toast.makeText(MyApplication.getContext(),"功能正在开发...",Toast.LENGTH_SHORT).show();
                /*Intent intent1 = new Intent(MyApplication.getContext(),ThreeCompute.class);
                startActivity(intent1);finish();*/break;
            case R.id.more:
                Toast.makeText(MyApplication.getContext(),"功能正在开发...",Toast.LENGTH_SHORT).show();
                /*Intent intent2 = new Intent(MyApplication.getContext(),FourCompute.class);
                startActivity(intent2);finish();*/break;
            case R.id.robo:
                /*Intent intent3 = new Intent(MyApplication.getContext(), ChatMainActivity.class);
                startActivity(intent3);*/finish();break;

        }
        return super.onOptionsItemSelected(item);
    }

}
