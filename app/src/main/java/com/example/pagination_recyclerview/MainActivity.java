package com.example.pagination_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvuser;
    private UserAdapter mAdapter;
    private List<userobject> mList;

    private boolean isloading;
    private int currentpage=1;
    private boolean islastpage;
    private int totalpage=5;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvuser=(RecyclerView) findViewById(R.id.rcvuser);
        bar=(ProgressBar) findViewById(R.id.proces);
        mAdapter=new UserAdapter();


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rcvuser.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration decoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvuser.addItemDecoration(decoration);

        rcvuser.addOnScrollListener(new paginationScrollListener(linearLayoutManager) {
            @Override
            public void loadmoreItem() {
                isloading=true;
                bar.setVisibility(View.VISIBLE);
                currentpage+=1;
                loadnextpage();


            }

            @Override
            public boolean isloading() {
                return isloading;
            }

            @Override
            public boolean islastpage() {
                return islastpage;
            }
        });


        setlistdata();
        rcvuser.setAdapter(mAdapter);



    }

    private void loadnextpage() {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<userobject> list=getlistdata();
                mList.addAll(list);
                mAdapter.notifyDataSetChanged();

                isloading=false;
                bar.setVisibility(View.GONE);
                if(currentpage==totalpage){
                    islastpage=true;
                }

            }
        },2000);
    }

    public void setlistdata(){
        mList=getlistdata();
        mAdapter.setdata(mList);



    }
    public List<userobject> getlistdata(){
        List<userobject> list=new ArrayList<>();
        Toast.makeText(this, "load data page"+currentpage, Toast.LENGTH_SHORT).show();
        for(int i=1;i<=10;i++){
            list.add(new userobject("user name"));
        }

        return list;
    }
}