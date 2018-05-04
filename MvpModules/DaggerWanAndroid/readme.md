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