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

## 1-5 表达式链##

## 1-6 Lambda表达式##

## 1-7 动画##

## 1-8 测试##


# 第2章 DataBinding使用建议 #

### 2-1 Data Binding使用建议... ###





# 第3章 课程总结 #
## 3-1 Android Data Binding实... ##