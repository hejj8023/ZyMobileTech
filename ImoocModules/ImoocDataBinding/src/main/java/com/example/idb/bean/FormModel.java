package com.example.idb.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.idb.BR;

public class FormModel extends BaseObservable {

    private String name;

    private String pwd;


    public FormModel(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
        notifyPropertyChanged(BR.pwd);
    }
}
