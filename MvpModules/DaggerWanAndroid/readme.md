# string中使用html

    <string name="tip_no_account">
        <Data><![CDATA[
        还没账号哦,请点
        <font color="#ff0000">这里</font>
        注册]]></Data>
        </string>
        
# 数据解析异常处理

    @POST(Const.API_URL_CONFIG.API_LOGIN)
    @FormUrlEncoded
    Observable<ResponseBody> login(@Field("username") String userName, @Field("password")
            String pwd);
            
    @POST(Const.API_URL_CONFIG.API_LOGIN)
    @FormUrlEncoded
    Observable<BaseBean<LoginData>> login(@Field("username") String userName, @Field("password")
            String pwd);
    
    // {"data":null,"errorCode":-1,"errorMsg":"账号密码不匹配！"}
    // {"data":{"collectIds":[2835],"email":"","icon":"","id":4642,"password":"12345678",
    // "type":0,"username":"xfgczzg"},"errorCode":0,"errorMsg":""}
    mLoginModel.login(mUserName, mPwd, new AbsBaseObserver<ResponseBody>(this, LoginModel
            .class.getName()) {
        @Override
        public void onNext(ResponseBody responseBody) {
            try {
                String result = responseBody.string();
                LoggerUtils.loge(result);
                if (result.startsWith("<html><head>")) {
                    mLoginView.showFail("无网络访问权限");
                    mLoginView.loginFailure();
                    return;
                }
                mLoginView.loginSucess();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        @Override
        public void onError(Throwable e) {
            mLoginView.showFail("登录失败");
            mLoginView.loginFailure();
        }
    });
    
    mLoginModel.login(mUserName, mPwd, new RxBaseObserver<LoginData>(this, LoginModel.class
            .getName()) {
        @Override
        public void onNext(BaseBean<LoginData> loginDataBaseBean) {
            if (loginDataBaseBean != null && loginDataBaseBean.errorCode == 0) {
                LoginData data = loginDataBaseBean.data;
                // 登录成功
                mLoginView.showFail("登录成功");
                mLoginView.loginSucess();
            } else {
                mLoginView.showFail("登录失败");
                mLoginView.loginFailure();
            }
        }
    });        
    
#  BottomNavigationView 其它条目的文字不显示
    BottomNavigationViewHelper.disableShiftMode(mNavigationView);


# DrawerLayout添加滑动动画

    ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
            this,
            mDrawerLayout,
            mToolbar,
            R.string.open_drawer,
            R.string.close_drawer) {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            MainActivity listener = MainActivity.this;
            LoggerUtils.loge(listener, "onDrawerSlide slideOffset = " + slideOffset);

            // 获取Drawlayout中的第一个子布局
            // 获取抽屉的view
            View mContent = mDrawerLayout.getChildAt(0);
            float scale = 1 - slideOffset;
            LoggerUtils.loge(listener, "scale = " + scale);
            float endScale = 0.8f + scale * 0.2f;
            LoggerUtils.loge(listener, "endScale = " + endScale);
            float startScale = 1 - 0.3f * scale;
            LoggerUtils.loge(listener, "startScale = " + startScale);

            // 设置左边菜单滑动后占据屏幕大小
            drawerView.setScaleX(startScale);
            drawerView.setScaleY(startScale);
            // 设置菜单透明度
            float alpha = 0.6f + 0.4f * (1 - scale);
            LoggerUtils.loge(listener, "alpha = " + alpha);
            drawerView.setAlpha(alpha);

            // 设置内容界面水平和垂直方向偏移量
            // 在滑动时内容界面的宽度为: 屏幕的宽度-菜单界面所占的宽度
            float rotationX = drawerView.getMeasuredWidth() * (1 - scale);
            LoggerUtils.loge(listener, "rotationX = " + rotationX);
            mContent.setRotationX(rotationX);
            // 设置内容界面操作无效(如果有buttom就会点击无效)
            mContent.invalidate();
            // 设置右边菜单滑动后的占据屏幕大小
            mContent.setScaleX(endScale);
            mContent.setScaleY(endScale);

        }
    };
    drawerToggle.syncState();


# TypedValue.applyDimension()用法

        TypedValue.applyDimension()方法的功能就是把非标准尺寸转换成标准尺寸, 如:

        dp->px:  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, context.getResources().getDisplayMetrics());

        in->px: TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_IN, 20, context.getResources().getDisplayMetrics());

        mm->px: TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, 20, context.getResources().getDisplayMetrics());

        pt->px: TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 20, context.getResources().getDisplayMetrics());

        sp->px: TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, context.getResources().getDisplayMetrics());