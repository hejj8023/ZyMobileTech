# 入门

## 原理
## 基础的用法


## 基本概念

- MVVM

    MVC-> C层换成ViewModel
    
    ViewModel 更偏向于数据，偏向于业务
    
>
    Databinding就是将ViewModel绑定到xml中
 
    xml的数据来源都是来自于viewmodel

## 启用Data Binding    
    dataBinding {
            enabled = true
    }
    
    
### 使用:

#### for xml:

    <?xml version="1.0" encoding="utf-8"?>
    <layout xmlns:android="http://schemas.android.com/apk/res/android">
    
        <data>
    
            <!--<import type="com.example.idb.User"/>-->
    
            <variable
                    name="userData"
                    type="com.example.idb.User"/>
        </data>
    
        <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical"
                android:gravity="center">
    
            <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="@{userData.firstName}"
                    android:textSize="20sp"/>
    
        </LinearLayout>
    </layout>  

#### for java
    
    ActivityDemoBinding demoBinding = DataBindingUtil.setContentView(this, R.layout.activity_demo);
    User user = new User("baby", "tiaopi");
    # 第一种方式
    demoBinding.setUserData(user);
    # 第二种方式
    demoBinding.setVariable(BR.userData, user);
    
    
### UI/事件绑定

##### Bind UI

>    setVariable,setXXX

#####  事件 
         
1. android:onClick
1. android:onLongClick
1. android:onTextChanged
    
### 事件绑定

1. 方法引用


> for xml:
 
    <?xml version="1.0" encoding="utf-8"?>
    <layout xmlns:android="http://schemas.android.com/apk/res/android">
    
        <data>
    
            <import type="com.example.idb.User"/>
    
            <variable
                    name="userData"
                    type="com.example.idb.User"/>
    
            <variable
                    name="dPresenter"
                    type="com.example.idb.MainActivity.MethodCiteBindPresenter"/>
        </data>
        
        <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:orientation="vertical">
        
                <EditText
                        android:id="@+id/et_demo"
                        android:hint="请输入数据"
                        android:layout_width="match_parent"
                        android:onTextChanged="@{dPresenter.onTextChanged}"
                        android:layout_height="wrap_content"/>
        
                <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:onClick="@{dPresenter.onClick}"
                        android:text="@{userData.firstName}"
                        android:textSize="20sp"/>
                        
        </LinearLayout>
    </layout>  
    
> for java
    
    public class MethodCiteBindPresenter {
    
        /**
         * 方法必须和原view的签名相同，方法必须是公开方法，否则会报错
         */
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO: 2018/6/13 更新textview数据
            user.setFirstName(s.toString());
            demoBinding.setUserData(user);
        }
    
        public void onClick(View v) {
            ToastUtils.showShort("小样,你点了我...");
        }
    } 
    
    MethodCiteBindPresenter methodCiteBindPresenter = new MethodCiteBindPresenter();
    demoBinding.setVariable(BR.dPresenter, methodCiteBindPresenter);




1. 监听器绑定
 
#### for java     

     // 监听器绑定
     public void onClickListenerBinding(User user) {
         ToastUtils.showShort("小样,你点了 " + user.getLastName() + " ...");
     }
     
#### for xml:
     
     android:onClick="@{() -> dPresenter.onClickListenerBinding(userData)}"
     
## 基本原理

    开始编译->处理xml文件->解析表达式->java 编译 -> 依赖解析 ->setter函数查找

## 主要性能

1. 0反射
1. findViewById需要遍历整个viewgroup,现在只需要做一次，而且做了缓存
1. 使用位标记来检验更新   
1. 数据改变在下一次批量更新才会触发操作
1. 缓存表达式:使用过的表达式会进行缓存
    

## 表达式

1. 二元 &|^
1. 一元 +-!~   
1. >> >>> <<     
1. 比较: == < > >= <=  
1. Instanceof
1. Grouping(分组)
1. 文字(character,String,numeric,null)
1. cast
1. 方法调用
1. Field 访问
1. Array访问[]
1. 三元 ?:

## 缺省(没有办法在xml中访问的)
1. this
1. super
1. new
1. 显示泛型调用


## 表达式-空合并运算符

	取非空表达式
	
	  android:text="@{user.displayName??use.lastName}"
	    
	等效于
	  android:text="@{user.displayName!=null?user.displayName:use.lastName}"
  
## 表达式例子

- Magrin @dimen+@dimen
- android:text="@{String.valueOf(index+1)}"
- visibility = "@{age&lt;13?View.GONE:View.VISIBLE}"
- transitionName = "@{"image_"+id}" 

## 空指针检查

### 自动空指针检查
- {user.name} -> null
- {user.age} -> 0

### 数组越界(data binding没有办法处理)

# include

### bind ###

	<include "layout=@laout/name"
	  bind:user=@{user}" />

### 不支持direct child,如root 为merge ###

### 示例 ###


# viewstub

第一次的时候并不会viewstub只有findviewbyid找到控件显示调用的时候才会viewstub
只有当view inflate成功之后，才会调用viewstub,才能获取里面的内容

- ViewStub proxy final field
- ViewStubProxy
- OnInflateListener

### 示例 ###

