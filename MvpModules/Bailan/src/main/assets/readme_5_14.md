#未完成的工作清单

##1. 资讯列表
    资讯列表： 最新资讯垂直滚动

##2. 左侧滑菜单 公司名称自动滚动

##3.日期范围选择
   选择时间 控件



#备用记事
  apk在发给别人的时候要打签名包

筛选列表中的checkbox已经修复


# 消息推送-信鸽
需要使用签名包才能检验，目前未做保活


#未修复的问题
你用这个账号登录后！你多点几次！有时会闪退


接口解析错误
http://www.frigate-iot.com/MonitoringCentre/Data/DevDataHistory.php?ID=243555
[]
是由服务器返回的数据为空导致

# 日期范围选择
  选择日期的控件正在调试中

#已修复的问题
图表- 单线的时候 ，有的时候会显示坐标的数据
5.统计和详情界面，电流和温度的回路曲线弄反了                                                已修复
6.设置界面“账号安全”、“隐私”等的背景色改为标题栏一样，                                       已修复
7. “警告”改为"报警日志"                                                                   已修复
8. 日期范围选择-默认选 中当前天       已修复
http://www.frigate-iot.com/MonitoringCentre/Data/DevStatus.php?ID=243546
{"baseInf":{"ID":"243546","CRCID":"000024354602031","Name":"ORT\u67dc\u5b506#","CustomerID":"9567","CustomerName":"\u4f1f\u521b\u529b\u7535\u5b50\u8bbe\u5907\uff08\u6df1\u5733\uff09\u6709\u9650\u516c\u53f8","GroupID":"11687","GroupName":"\u914d\u7535\u67dc","UpdataDate":"2018-05-14 22:41:12","Online":"1","Alarm":"\u6b63\u5e38","AlarmDate":null},"loopData":[{"IntegerVal":0,"DecimalVal":0,"LoopNum":"1","LimitHigh":300,"LimitLow":0,"LoopType":"v52","Name":"\u6f0f\u7535","Unit":"mA","Scope":false},{"IntegerVal":0,"DecimalVal":0,"LoopNum":"1","LimitHigh":250,"LimitLow":0,"LoopType":"v58","Name":"\u7535\u6d41","Unit":"A","Scope":false},{"IntegerVal":0,"DecimalVal":0,"LoopNum":"2","LimitHigh":250,"LimitLow":0,"LoopType":"v58","Name":"\u7535\u6d41","Unit":"A","Scope":false},{"IntegerVal":0,"DecimalVal":0,"LoopNum":"3","LimitHigh":250,"LimitLow":0,"LoopType":"v58","Name":"\u7535\u6d41","Unit":"A","Scope":false},{"IntegerVal":28,"DecimalVal":0,"LoopNum":"1","LimitHigh":70,"LimitLow":0,"LoopType":"v54","Name":"\u6e29\u5ea6","Unit":"\u00b0C","Scope":false},{"IntegerVal":33,"DecimalVal":0,"LoopNum":"2","LimitHigh":70,"LimitLow":0,"LoopType":"v54","Name":"\u6e29\u5ea6","Unit":"\u00b0C","Scope":false},{"IntegerVal":32,"DecimalVal":0,"LoopNum":"3","LimitHigh":70,"LimitLow":0,"LoopType":"v54","Name":"\u6e29\u5ea6","Unit":"\u00b0C","Scope":false},{"IntegerVal":32,"DecimalVal":0,"LoopNum":"4","LimitHigh":70,"LimitLow":0,"LoopType":"v54","Name":"\u6e29\u5ea6","Unit":"\u00b0C","Scope":false}]}

http://www.frigate-iot.com/MonitoringCentre/Data/DevDataHistory.php?ID=243546


# 5.-16 优化日志
3.超过6位时，登录按钮变蓝色
1.登录页面账号提示不对，(正确；输入账号)
2.登录闪退，(可能数据处理问题，报了空指针或者数组越界) 账号:kefu02   密码:88888888
4.点击两次退出应用，再次打开应用，应该保留账号和密码，
  点击退出登录后回到登录界面，应该保留账号名

登录,设备注册密码校验


# 5-16新发现的问题
从筛选对应的界面，上拉加载和下拉刷新，数据错误的问题需要解决
loading效果修改
密码修改同步修改操作


# 5-26
解决push的时候通知栏推送的时候，点击先到主界面然后再到欢迎界面的问题(取消原有的展示,统一设置)

    XGPushManager.setNotifactionCallback(new XGPushNotifactionCallback() {
    
        @Override
        public void handleNotify(XGNotifaction xGNotifaction) {
            LoggerUtils.loge("处理信鸽通知：" + xGNotifaction);
    //                // 获取标签、内容、自定义内容
            String title = xGNotifaction.getTitle();
            String content = xGNotifaction.getContent();
    //                String customContent = xGNotifaction.getCustomContent();
    //                // 其它的处理
    //                // 如果还要弹出通知，可直接调用以下代码或自己创建Notifaction，否则，本通知将不会弹出在通知栏中。
    //                xGNotifaction.doNotify();
            CommonUtil.notifyPushMsg(mContext, title, content);
        }
    });
    
## 信鸽推送问题的处理
    将初始化放到app中
    将绑定用户放到登录之后
    注销时解除绑定
    