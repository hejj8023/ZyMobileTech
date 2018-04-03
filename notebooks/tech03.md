# glide load bitmap的问题
需要先将bitmap对象转换为字节,在加载;

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
    byte[] bytes=baos.toByteArray();
    
    Glide.with(mContext)
    .load(bytes)
    .centerCrop()
    //                    .thumbnail(0.1f)   //缩略图为原来的十分之一
    .override(Utils.px2dip(mContext,130),Utils.px2dip(mContext,130)) //设置大小
    .placeholder(me.iwf.photopicker.R.drawable.__picker_ic_photo_black_48dp)
    .error(me.iwf.photopicker.R.drawable.__picker_ic_broken_image_black_48dp)
    .into(holder.ivPhoto);
    
    
# bitmap图片处理思路
    1.将图片缩放至手机屏幕大小或Camera的预览区域大小 
    
    2.将缩放之后的图片进行4/3的尺寸进行缩放，也可以第一步缩放的时候就操作
    
          横屏: height不变,width*0.75
          竖屏: width不变,height*0.75
    
    3.4.若是上面的图片还是大了，可以使用width/1.5,height/1.5这个值要自己测试
    
    4.bitmap中心区域按x*x区域截取
    
          x = bitmap.getwidth()/2-(x/2)
          y = bitmap.getheight()/2-(x/2)
          
    1920*1080相机拍摄出来的照片:4208*3120
       4320*3120 -> 1920*1080(手机屏幕)
       4320*3120 -> 1920*942(SufaceView)
       1920*942  -> (1920*0.75)*942 -> 1440 *942
       1440*912  -> (1440/1.5) * (942/1.5) = 960*628
    
# BitmapRegionDecoder对bitmap进行区域截取
    
    Bitmap bitmap = BitmapFactory.decodeFile(filepath);
    if (bitmap != null) {
        int h = bitmap.getHeight() / 2;
        int w = bitmap.getWidth() / 2;
        Rect react = new Rect();
        react.left = w - (sv / 2);
        react.top = h - (sv / 2);
        react.right = w + (sv / 2);
        react.bottom = h + (sv / 2);
        BitmapRegionDecoder regionDecoder = null;
        try {
            regionDecoder = BitmapRegionDecoder.newInstance(sourceFile.getAbsolutePath(), true);
            Bitmap bitmap1 = regionDecoder.decodeRegion(react, null);
            saveFile(bitmap1, firDir.getPath(), "test_brd_source_700x700.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }