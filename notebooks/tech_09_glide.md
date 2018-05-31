# glide v4使用手册
    https://blog.csdn.net/u013005791/article/details/74532091
    
##基本配置
配置内存缓存

Glide会自动合理分配内存缓存，但是也可以自己手动分配。

方法一

通过MemorySizeCalculator设置

    @GlideModule
    public class CustomGlideModule extends AppGlideModule {
        @Override
        public void applyOptions(Context context, GlideBuilder builder) {
            MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                    .setMemoryCacheScreens(2)
                    .build();
            builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));
        }
    }
setMemoryCacheScreens设置MemoryCache应该能够容纳的像素值的设备屏幕数，说白了就是缓存多少屏图片，默认值是2。

方法二

    @GlideModule
    public class CustomGlideModule extends AppGlideModule {
        @Override
        public void applyOptions(Context context, GlideBuilder builder) {
            int memoryCacheSizeBytes = 1024 * 1024 * 20; // 20mb
            builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));
        }
    }
方法三

    @GlideModule
    public class YourAppGlideModule extends AppGlideModule {
      @Override
      public void applyOptions(Context context, GlideBuilder builder) {
        builder.setMemoryCache(new CustomGlideMemoryCache());
      }
    }
    
自己实现MemoryCache接口。

清理内存缓存，在主线程调用：

GlideApp.get(context).clearMemory();
在使用的时候，可以跳过内存缓存：

     GlideApp.with(getActivity())
             .load(url)
             .skipMemoryCache(true)
             .dontAnimate()
             .centerCrop()
             .into(imageView);
             
8.2 磁盘缓存

Glide使用DiskLruCacheWrapper作为默认的磁盘缓存，默认大小是250M,缓存文件放在APP的缓存文件夹下。

    @GlideModule
    public class CustomGlideModule extends AppGlideModule {
        @Override
        public void applyOptions(Context context, GlideBuilder builder) {
            int diskCacheSizeBytes = 1024 * 1024 * 100; // 100 MB
            builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheSizeBytes));
    //        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "cacheFolderName", diskCacheSizeBytes));
    //        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context));
        }
    }
    
用法如上，可以指定缓存在内部存储或外部存储，也可以指定缓存大小和文件夹。

自定义磁盘缓存

    @GlideModule
    public class CustomGlideModule extends AppGlideModule {
      @Override
      public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
              return new YourAppCustomDiskCache();
            }
        });
      }
    }
    
自己实现DiskCache接口。

清理磁盘缓存，在子线程调用：

    GlideApp.get(context).clearDiskCache();
    
加载图片时设置磁盘缓存策略：

    GlideApp.with(getActivity())
             .load(url)
             .diskCacheStrategy(DiskCacheStrategy.ALL)
             .dontAnimate()
             .centerCrop()
             .into(imageView);
默认的策略是DiskCacheStrategy.AUTOMATIC 
DiskCacheStrategy有五个常量：

DiskCacheStrategy.ALL 使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
DiskCacheStrategy.NONE 不使用磁盘缓存
DiskCacheStrategy.DATA 在资源解码前就将原始数据写入磁盘缓存
DiskCacheStrategy.RESOURCE 在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
DiskCacheStrategy.AUTOMATIC 根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。

8.3 禁止解析Manifest文件

主要针对V3升级到v4的用户，可以提升初始化速度，避免一些潜在错误。

    @GlideModule
    public class CustomGlideModule extends AppGlideModule {
      @Override
      public boolean isManifestParsingEnabled() {
        return false;
      }
    }

8.4 View尺寸

Glide对ImageView的width和height属性是这样解析的：

如果width和height都大于0，则使用layout中的尺寸。
如果width和height都是WRAP_CONTENT，则使用屏幕尺寸。
如果width和height中至少有一个值<=0并且不是WRAP_CONTENT，那么就会在布局的时候添加一个OnPreDrawListener监听ImageView的尺寸
Glide对WRAP_CONTENT的支持并不好，所以尽量不要用。

那么如何在运行修改ImageView尺寸呢？

方法一 继承ImageViewTarget

我这里指定的View的类型是ImageView,资源类型是Bitmap，可根据需要修改，onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition)方法中可以通过bitmap获取图片的尺寸。

    public class CustomImageViewTarget extends ImageViewTarget<Bitmap> {
    
        private int width, height;
    
        public CustomImageViewTarget(ImageView view) {
            super(view);
        }
    
        public CustomImageViewTarget(ImageView view, int width, int height) {
            super(view);
            this.width = width;
            this.height = height;
        }
    
        @Override
        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
            super.onResourceReady(bitmap,transition);
        }
    
        @Override
        protected void setResource(@Nullable Bitmap resource) {
            view.setImageBitmap(resource);
        }
    
        @Override
        public void getSize(SizeReadyCallback cb) {
            if (width > 0 && height > 0) {
                cb.onSizeReady(width, height);
                return;
            }
            super.getSize(cb);
        }
    }
    
使用：

    GlideApp.with(context)
            .asBitmap()
            .load(url)
            .dontAnimate()
            .placeholder(R.drawable.img_default)
            .into(new CustomImageViewTarget(imageview, 300, 300));
            
方法二 使用override()

    GlideApp.with(mContext)
        .load(url)
        .override(width,height)
        .into(view);
获取bitmap

如果只想用Glide解析url获取一个bitmap，然后自己对其进行处理，可以使用SimpleTarget<Z>，

    /**
       * Constructor for the target that uses {@link Target#SIZE_ORIGINAL} as the target width and
       * height.
       */
      public SimpleTarget() {
        this(SIZE_ORIGINAL, SIZE_ORIGINAL);
      }
    
      /**
       * Constructor for the target that takes the desired dimensions of the decoded and/or transformed
       * resource.
       *
       * @param width  The width in pixels of the desired resource.
       * @param height The height in pixels of the desired resource.
       */
      public SimpleTarget(int width, int height) {
        this.width = width;
        this.height = height;
      }
SimpleTarget也可以指定宽和高，用法示例：

    Glide.with(itemView.getContext()).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
    
        }
    });    
    
## 占位符
    
    占位符就是请求的图片没加载出来时显示的默认图片。 
    Glide支持三种不同情况下的占位符：
    
    Placeholder 请求图片加载中
    Error 请求图片加载错误
    Fallback 请求url/model为空
###  RequestOptions
### Glide中的大多请求参数都可以通过RequestOptions类和apply()方法来设置。
    
    Glide中的请求参数主要有：
    
    Placeholders 占位符
    Transformations 变换
    Caching Strategies 缓存策略
    组件特定参数：编码质量，解码参数等。    
    
    比如，要将图片的显示方式设为CenterCrop，你可以这么做：
    
    import static com.bumptech.glide.request.RequestOptions.centerCropTransform;
    
    Glide.with(fragment)
        .load(url)
        .apply(centerCropTransform(context))
        .into(imageView);
    apply方法可以调用多次，但是如果两次apply存在冲突的设置，会以最后一次为准。
            
####  TransitionOptions
    TransitionOptions决定图片加载完成如何从占位符图片(或者之前的图片)过渡。
    
    淡入
    交叉淡入
    不过渡
    Glide.with(fragment)
       .load(url)
       .transition(DrawableTransitionOptions.withCrossFade())
       .into(view);
                
    注意
    
    TransitionOptions是和你要加载的资源的类型绑定的，也就是说，如果你请求一张位图(Bitmap),你就需要使用BitmapTransitionOptions，而不是DrawableTransitionOptions。因此，你请求的这张位图，你需要用简单的淡入，而不能用 
    交叉淡入(DrawableTransitionOptions.withCrossFade())。 
    如果既不是Bitmap也不是Drawable可以使用GenericTransitionOptions
                    
### RequestBuilder
      1.指定加载类型。asBitmap()、asGif()、asDrawable()、asFile()。
      2.指定要加载url/model。
      3.指定要加载到那个View。
      4.指定要应用的RequestOption
      5.指定要应用的TransitionOption
      6.指定要加载的缩略图                  
###  那么如何得到RequestBuilder呢？
  
      RequestBuilder<Drawable> requestBuilder = Glide.with(fragment);
      默认得到一个Drawable RequestBuilder，如果要指定类型为Bitmap,可以这样写：
      
      RequestBuilder<Bitmap> requestBuilder = Glide.with(fragment).asBitmap();
      应用RequestOptions
      
      RequestBuilder<Drawable> requestBuilder = Glide.with(fragment);
      requestBuilder.apply(requestOptions);
      requestBuilder.transition(transitionOptions);
      RequestBuilder也可以重复使用：
      
      RequestBuilder<Drawable> requestBuilder =
              Glide.with(fragment)
                  .asDrawable()
                  .apply(requestOptions);
      
      for (int i = 0; i < numViews; i++) {
         ImageView view = viewGroup.getChildAt(i);
         String url = urls.get(i);
         requestBuilder.load(url).into(view);
      }
        
###  Transformations
      Glide会自动读取ImageView的缩放类型，所以一般在layout文件指定scaleType即可。
      
      CenterCrop, CenterInside, CircleCrop, FitCenter, RoundedCorners
      
      Glide支持在java代码中设置这些缩放类型：
      
      CenterCrop 缩放宽和高都到达View的边界，有一个参数在边界上，另一个参数可能在边界上，也可能超过边界
      CenterInside 如果宽和高都在View的边界内，那就不缩放，否则缩放宽和高都进入View的边界，有一个参数在边界上，另一个参数可能在边界上，也可能在边界内
      CircleCrop 圆形且结合了CenterCrop的特性
      FitCenter 缩放宽和高都进入View的边界，有一个参数在边界上，另一个参数可能在边界上，也可能在边界内
      RoundedCorners 圆角
  
#### 1 使用RequestOptions
    RequestOptions options = new RequestOptions();
    options.centerCrop();
    
    Glide.with(fragment)
      .load(url)
      .apply(options)
      .into(imageView);
      
####  2 使用RequestOptions中的transform方法
    Glide.with(fragment)
      .load(url)
      .apply(RequestOptions.fitCenterTransform())
      .into(imageView);
      
#### 3 V4特性
    GlideApp.with(fragment)
    .load(url)
    .fitCenter()
    .into(imageView);

##### 多个变换
    Glide.with(fragment)
      .load(url)
      .transform(new MultiTransformation(new FitCenter(), new YourCustomTransformation())
      .into(imageView);

##  Transitions(动画)
### 普通动画
Glide中的过渡动画是指占位符到请求图片或缩略图到完整尺寸请求图片的动画。过渡动画只能针对单一请求，不能跨请求执行。
过渡动画执行时机：
    1.图片在磁盘缓存 
    2.图片在本地 
    3.图片在远程
如果图片在内存缓存上是不会执行过渡动画的。如果需要在内存缓存上加载动画，可以这样：

    GlideApp.with(this).load(R.drawable.img_default).listener(new RequestListener(){
    
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
            return false;
        }
    
        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
            if (dataSource == DataSource.MEMORY_CACHE) {
                //当图片位于内存缓存时，glide默认不会加载动画
                imageView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
            }
            return false;
        }
    }).fitCenter().transition(GenericTransitionOptions.with(R.anim.fade_in)).into(imageView);

通常的用法如下：

    Glide.with(fragment)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view);

TransitionOptions的介绍：TransitionOptions。有三种TransitionOptions：

    GenericTransitionOptions 通用型
    DrawableTransitionOptions
    BitmapTransitionOptions

如果要使用自定义的动画，可以使用GenericTransitionOptions.with(int viewAnimationId)或者BitmapTransitionOptions.withCrossFade(int animationId, int duration)或者DrawableTransitionOptions.withCrossFade(int animationId, int duration)。
出于性能考虑，最好不要在ListView,GridView,RecycleView中使用过渡动画,使用TransitionOptions.dontTransition()可以不加载动画，也可以使用dontAnimate不加载动画

    GlideApp.with(mContext)
        .load(imgUrl)
        .placeholder(R.drawable.img_default)
        .dontAnimate()
        .into(holder.imageview);        
        
### 自定义过渡动画
    1.实现TransitionFactory 
    2.重写build() 
可以控制图片在内存缓存上是否执行动画。
具体写法参考DrawableCrossFadeFactory，然后调用TransitionOptions的with(TransitionFactory transitionFactory)加载。

        