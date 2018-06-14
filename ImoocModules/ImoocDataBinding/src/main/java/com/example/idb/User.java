package com.example.idb;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableBoolean;

public class User extends BaseObservable {
    private String firstName;
    private String lastName;

    private String avatorUri;

    /**
     * 是否被注销了
     */
    private boolean isFire;

    public ObservableBoolean bIsFired = new ObservableBoolean();


    public ObservableArrayMap<String, String> lables = new ObservableArrayMap<>();

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        bIsFired.set(false);

        lables.put("one", "东风-3");
        lables.put("two", "东风-18");
        lables.put("three", "东风-21");
        lables.put("four", "东风-41");
    }

    @Bindable
    public String getFirstName() {
        return this.firstName;
    }

    @Bindable
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(com.example.idb.BR.lastName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(com.example.idb.BR.firstName);
    }

    @Bindable
    public boolean isFire() {
        return isFire;
    }

    public void setFire(boolean fire) {
        isFire = fire;
        // notifyPropertyChanged(com.example.idb.BR.fire);
        // 更新所有的域，可以不在要更新的域上加@Bindable
        notifyChange();
    }

    public ObservableBoolean getbIsFired() {
        return bIsFired;
    }

    public void setbIsFired(boolean isPFire) {
        bIsFired.set(isPFire);
    }

    public String getAvatorUri() {
        return avatorUri;
    }

    public void setAvatorUri(String avatorUri) {
        this.avatorUri = avatorUri;
    }
}