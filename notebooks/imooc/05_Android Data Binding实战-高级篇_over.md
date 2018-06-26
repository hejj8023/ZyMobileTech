# 1章 DataBinding高级用法 #

## 1-3 自定义属性 ##

### Setter ###



- 自动寻找set方法

		app:scrimColor = "@{@color/scrimColor}"
	
		setScrimColor(int color)

- BindingMethods

> Binding方法

		@BindingMethods({
			@BindingMethod(
				type="andorid.widget.ImageView",
				attribute="android:tint",
				method="setImageTinList"),
		})
		android:tint -> setImageTinList

-  BindingAdapter

> Binding适配器

		@BindingAdapter("bind:imageUri")
			public static void loadImageFromUri(ImageView view,Uri uri){ ... // load
		}


> 多属性适配

		@BindingAdapter("bind:imageUri","bind:placeholder")
			public static void loadImageFromUri(ImageView view,Uri uri,Drawable placeholder){ ... // load
		}


-  BindingConversion

> 转换为setter需要的属性

	<View 
	   android:background="@{isError?@color/red:@color/white}" />
	
	@BindingConversion
	public static ColorDrawable covnertColorToDrawable(int color){
	return new ColorDrawable(color);
	}


## 1-4 双向绑定 ##

### 之前使用的方式:(老方式) 

- Listener

>
	<EditText android:text="@{user.name}"
	andorid:afterTextChanged="@{callback.change}" />	
	public void change(Editable s){
		String text = s.toString();
		if(!text.equals(name.get()){
			name.set(text);
		}
	}  

### 现在的方式
- @=
>
	<EditText android:text="@={user.name}"

> 内部实现

> InverseBindingListener(能用)
 
	 public interface InverseBindingLisener{
		 void onChage()
	 }

> 获取Vew数据
> 
> InverseBindingMethod

	@InverseBindingMethods({@InverseBindingMethod(
		type = andorid.widget.TextView.class,
	    attribute = "android:text",
	    method = "getText" // 默认会根据attribute name获取get
	    event="android:textAttrChanged"
	)}  // 默认会根据attribute增加AttributeChanged

>> 通知

> BindingAdapter

	@BindingAdapter(value={},requireAll=false)
	public static void setTextWatcher(TextView tv,final OnTextChagned on,final InverseBindingListener textAttrChanged,...){}

> 更复杂的getter

> inverseBindingAdapter

	@InverseBindingAdapter(attribute="android:text")
	public static String getTextString(TextView view){
		return view.getText().toString();
	}

> 循环
> 
>> 解决死循环

	@BindingAdapter(attribute="android:text")
	public static void setTextWatcher(TextView tv,CharSequence text){
		final CharSequence oldTxt = view.getText();
	    if(!haveContentsChanged(oldText,text){
			return;
		}
		view.setText(text);
	}

> 并不是所有属性都支持双向绑定

>> 只有那些带额外的属性事件才支持
> text,checked,year,month,hour,ratting,progress

-
> 监听属性变更(ViewModel中处理)

	addOnPropertyChangedCallback

## 1-5 表达式链##


- 重复的表达式

		<ImageView android:vibility="@{user.isAdult?
		View.VISIBLE:View.GONE}"/>
		<TextView android:vibility="@{user.isAdult?View.VISIBLE:View.GONE}"/>
		<CheckBox android:vibility="@{user.isAdult?View.VISIBLE:View.GONE}"/>

- 简化表达式

		<ImageView android:vibility="@{user.isAdult?
		View.VISIBLE:View.GONE}"/>
		<TextView android:vibility="@{user.isAdult}"/>
		<CheckBox android:vibility="@{user.isAdult}"/>

- 隐式更新

		<Checkbox andorid:id = "@+id/seeAds"/>
		<ImageView android:visibility="@{seeAds.checked?View.VISIBLE:View.GONE}"/>

## 1-6 Lambda表达式##

- 方法引用的替代者

		android:onClick="@{(view)->presenter.save(view,item)}"/>

- 省略参数，或申明所有参数（通常不需要view)

		android:onClick="@{()->presenter.save(item)}"/>

		android:onFourcsChange="@{(v,fcs)->presenter.refresh(item)}"/>


### Lambda vs 方法引用 ###

- Lambda中可以使用表达式，引用变量

- 特殊变量:view id(会另外一个view驼峰变量名)

- 包括context变量


## 1-7 动画##
### Transition ###

	binding.addOnRebindCallback(new OnRebindCallback(){
		@Override
		public boolean onPreBind(ViewDataBinding binding){
			ViewGroup sceneRoot = (ViewGroup)binding.getRoot();
			TransitionManager.beginDelayedTransition(sceneRoot);
			return true;
		}
	
	})

## 1-8 测试##
通过DataBinding帮我们做ui测试
### if/else ###

	public class MyBindingAdapters{
		@BindingAdapter("android:text")
		public static void setText(TextView view,String value){
			if(isTesting){
				doTesting(view,value);
			}else{
				TextViewBindingAdapter.setText(view,value);
			}
		}
	}

### implement ###

	public class TestBindingAdapter extends MyBindingAdapters{
		@Override
		public void setText(TextView view,String value){
			doTesting(view,value);
		}
	}

### DataBindingComponent 使用依赖注入 ###

	public interface DataBindingComponent{
		MyBindingAdapter getMyBindingAdatper();
	}

#### 实现Component ####

	public class TestComponent implements DataBindingComponent{
		private MyBindingAdapter mAdapter = new MyBindingAdapter();
	
		public MyBindingAdapter getMyBindingAdatper(){
			return mAdapter;
		}
	}

#### 注入Component ####

	DataBindingUtil.setDefaultComponent(new TestComponent());

### Static BindingAdapter ###

	@BindingAdapter("android:src)
	public static void loadImage(AppComponent component,ImageView view,String uri){
	
	}

# 第2章 DataBinding使用建议 #

### 2-1 Data Binding使用建议... ###

- 在项目中尝试
- 摸索xml和java界限
- Lambda表达式/测试注入等Data Binding功能

> Level1
> 
- 逐步替换findViewById
- 使用binding.name,binding.age直接访问view

> Level2
> 
- 引入Variable
- 手动set替换为xml直接引用variable
- binding.setUser(user);

> Level3
> 
- Callback
- android:onClick="@{handler::onNewPost} 函数替换"

> Level4 -Observable
> 
- extends BaseObservable
- @Bindable
- nofityPropertyChanged(BR.name)


> Level5 - 双向绑定
> 
- Form
- 将所有form data变成ObservableField


### 最佳实践 ###
### 注意边界 ###
	click函数只做事件传递，不做业务逻辑
### 保持表达式简单 ###
	不要做过于复杂的字符串、函数调用操作
