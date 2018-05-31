## as插件离线安装
    https://plugins.jetbrains.com/plugin/7654?pr=androidstudio
    在此界面中下载插件，然后复制到电脑中，选择本地安装即可
    
## recyclerview 嵌套使用的时候子recyclerview只显示一行
    将子recyclerview adapter里面使用的item根布局修改成wrap_content即可  
      
## recyclerview 嵌套使用的时候子recyclerview数据列表显示不完整（原来有10条只显示5条)
### 参考资料:https://blog.csdn.net/u011291377/article/details/73694862
    <?xml version="1.0" encoding="utf-8"?>
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                    android:layout_width="match_parent"
                    android:orientation="vertical"
                    android:descendantFocusability="blocksDescendants"
                    android:layout_height="wrap_content">
    
        <android.support.v7.widget.RecyclerView
                android:id="@+id/rv_item_category_menu"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
          />
    
    </RelativeLayout>
    注意套路哈: RelativeLayout+RecyclerView的高度都必须是wrap_content,要不然又他娘的显示不全了


## android 使用Glide加载图片闪动问题
### 1、RecyclerView 有自己默认的动画，去除默认动画
    ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
    并且设置对应的adapter，设置
    mRecyclerAdapter.setHasStableIds(true);
    
### 2、Glide同步加载图片的时候，给对应的imageview设置一个Tag
    if (!album.getCoverUrlSmall().equals(viewHolder.pic.getTag(R.id.aiItemPlaylistAlbum_iv_albumPic))) {
        GlideUtil.setRoundImage(mContext, viewHolder.pic, album.getCoverUrlSmall());
        viewHolder.pic.setTag(R.id.aiItemPlaylistAlbum_iv_albumPic, album.getCoverUrlSmall());
    } else {
    
    }
### 3、Glide去除其本身自带的动画
    GlideApp.with(context).load(resId).dontAnimate().centerCrop()
    .apply(RequestOptions.bitmapTransform(new GlideRoundTransformation(radius))).into(imageView);

### 4、Gldie异步加载图片的时候，recycleView滑动太快，会使图片发生错乱，此时最好设置两个Tag
    viewHolder.mIvPic.setTag(R.id.aiItemPlaylistAlbum_iv_customPic, mIAiMediaEntry.getEntryUniqueId());
    mIAiMediaEntry.getPic(IAiMediaEntry.PIC_SMALL, new IAiMediaEntry.IPictureCallback() {
        @Override
        public void onGetPictureSuccess(IAiMediaEntry entry, String imageUrl) {
            if (entry.getEntryUniqueId().equals(viewHolder.mIvPic.getTag(R.id.aiItemPlaylistAlbum_iv_customPic))) {
                if (!TextUtils.equals((String) (viewHolder.mIvPic.getTag(R.id.item_pic_view_tag)), imageUrl)) {
                    if (!TextUtils.isEmpty(imageUrl)) {
                        GlideUtil.setRoundImage(mContext, viewHolder.mIvPic, imageUrl);
                    } else {
                        GlideUtil.setRoundImage(mContext, viewHolder.mIvPic, R.drawable.label_demand_default, 7);
                    }
                    viewHolder.mIvPic.setTag(R.id.item_pic_view_tag, imageUrl);
                }
            }
        }
    
        @Override
        public void onGetPictureError(IAiMediaEntry entry, String errMsg) {
            if (entry.getEntryUniqueId().equals(viewHolder.mIvPic.getTag(R.id.aiItemPlaylistAlbum_iv_customPic))) {
                GlideUtil.setRoundImage(mContext, viewHolder.mIvPic, R.drawable.label_demand_default, 7);
            }
        }
    });
    
### 5、在RecyclerView 停止滑动的时候刷新列表，加载图片
    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if(newState == SCROLL_STATE_IDLE){
                mRecyclerAdapter.notifyDataSetChanged();
            }
        }
    });