package com.example.idb;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.idb.adapter.ListSampleAdapter;
import com.example.idb.databinding.ActivityListSampleBinding;
import com.zysdk.vulture.clib.utils.UiUtils;
import com.zysdk.vulture.clib.widget.recyclerview.divider.LinearDivider;

import java.util.ArrayList;
import java.util.List;

public class ListSampleActivity extends AppCompatActivity {

    ActivityListSampleBinding listSampleBinding = null;
    private Context mContext;
    private ListSampleAdapter adapter;

    public class ListSamplePresenter {
        public void onAddMenuClick(View view) {
            User user = new User("test-last", "kefu0-last");
            adapter.add(user);
        }

        public void onRemoveMenuClick(View view) {
            adapter.remove();
        }

        public void onBindMenuClick(View view) {
            List<User> userList = new ArrayList<>();
            User user = new User("test01", "kefu01");
            userList.add(user);
            user = new User("test02", "kefu02");
            userList.add(user);
            user = new User("test03", "kefu03");
            userList.add(user);
            user = new User("test04", "kefu04");
            user.setbIsFired(true);
            userList.add(user);
            user = new User("test05", "kefu05");
            userList.add(user);
            user = new User("test06", "kefu06");
            user.setbIsFired(true);
            userList.add(user);
            adapter.addAll(userList);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listSampleBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_sample);

        this.mContext = UiUtils.getContext();
        listSampleBinding.setListsamplepresenter(new ListSamplePresenter());

        listSampleBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listSampleBinding.recyclerView.addItemDecoration(new LinearDivider(mContext, LinearLayoutManager.VERTICAL,
                5, R.color.red));

        adapter = new ListSampleAdapter(mContext);
        listSampleBinding.recyclerView.setAdapter(adapter);
    }
}
