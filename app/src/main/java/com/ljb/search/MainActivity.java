package com.ljb.search;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;

import com.ljb.search.adapter.ListviewItemAdapter;
import com.ljb.search.bean.UserInfo;
import com.ljb.search.http.HttpUtil;
import com.ljb.search.utils.UserUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends Activity implements SearchView.OnQueryTextListener{

    private final String TAG = MainActivity.class.getSimpleName();
    private final String URL = "https://api.github.com/search/users?q=test";
//    private final String REPOSURL = "https://api.github.com/users/test/repos";
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private SearchView searchView;
    private ListView listView;
    private ListviewItemAdapter adapter;
    private List<UserInfo> allUsers;
    private List<UserInfo> searchUsers;
    private boolean isGetData;
    private String newText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchUsers = new ArrayList<>();
        initView();

    }

    private void initView() {
        searchView = (SearchView) findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);
        listView = (ListView) findViewById(R.id.listView);
        adapter =new ListviewItemAdapter(this,searchUsers);
        listView.setAdapter(adapter);

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        searchUsers.clear();

        if (TextUtils.isEmpty(newText)){
            adapter.notifyDataSetChanged();
            return false;
        }

        this.newText = newText;

        if (allUsers != null && allUsers.size() > 0) {
            upUi(newText);
            return false;
        }

        getData();

        return false;
    }

    public void  getData() {

        if (isGetData){
            return;
        }

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                isGetData = true;
                String data = HttpUtil.getData(URL);
                allUsers = UserUtil.getUsers(data);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        upUi(newText);
                    }
                });
            }
        });

    }

    private void upUi(String newText){

        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getName().contains(newText)){
                searchUsers.add(allUsers.get(i));
            }
        }

        Log.d(TAG,"searchUsers : " + searchUsers.size());

        adapter.notifyDataSetChanged();
    }
}
