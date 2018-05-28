package com.example.bailan.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.bailan.Const;
import com.example.bailan.widget.RecyclerBanner;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zzg on 2018/5/27.
 */

public class RecommendBean {


    /**
     * hasNextPage : 1
     * count : 26
     * marginTop : 4
     * layout : [{"layoutName":"immersivebannercard","maxRows":1,"layoutId":7100},
     * {"layoutName":"navispylinecard","maxRows":1,"layoutId":806840},
     * {"layoutName":"horizonhomecard","maxRows":-1,"layoutId":7120},
     * {"layoutName":"horizonhomecard","maxRows":-1,"layoutId":807136},
     * {"layoutName":"newentrancecard","maxRows":1,"layoutId":7101},
     * {"layoutName":"horizonhomecard","maxRows":-1,"layoutId":806978},
     * {"layoutName":"horizonhomecard","maxRows":-1,"layoutId":807240},
     * {"layoutName":"newentrancecard","maxRows":1,"layoutId":7102},
     * {"layoutName":"horizonhomecard","maxRows":1,"layoutId":806875},
     * {"layoutName":"horizonhomecard","maxRows":-1,"layoutId":899070}]
     * name : 推荐
     * isSupSearch : 1
     * contentType : 1
     * statKey : A01000
     * sortInfo : []
     * totalPages : 2
     * layoutData : [{"dataList":[{"icon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/e3f300d859b4435cbcb6ecae00e9a042
     * /1495088590600_1080x684.jpg","trace":"1cb9d75a1107447d87d331218b025093;__entrance9;
     * __e3f300d859b4435cbcb6ecae00e9a042","name":"作业帮","commendIcon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/e3f300d859b4435cbcb6ecae00e9a042
     * /1495088590600_1080x684.jpg","detailId":"app|C10113015|1495097563115"},
     * {"icon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/c0a050fcf4464b2ca199fda4ae94e0ab
     * /1495032248512_1080x684.png","trace":"1cb9d75a1107447d87d331218b025093;__entrance9;
     * __c0a050fcf4464b2ca199fda4ae94e0ab","name":"天龙八部","commendIcon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/c0a050fcf4464b2ca199fda4ae94e0ab
     * /1495032248512_1080x684.png","detailId":"app|C100005625|1495097563116"},
     * {"icon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/0c51b48e628c474bbbbba189f3d23506
     * /1494577728760_1080x684.png","trace":"1cb9d75a1107447d87d331218b025093;__entrance9;
     * __0c51b48e628c474bbbbba189f3d23506","name":"付费精品","commendIcon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/0c51b48e628c474bbbbba189f3d23506
     * /1494577728760_1080x684.png",
     * "detailId":"waplinkdetail|5058a9e3de4641c99185897f204f97bc__entrance|1495097563116"},
     * {"icon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/4f3a7f44e6d845f8b9d6f2ec6049a3bd
     * /1494647342134_1080x684.png","trace":"1cb9d75a1107447d87d331218b025093;__entrance9;
     * __4f3a7f44e6d845f8b9d6f2ec6049a3bd","name":"适合妈妈玩的游戏都在这了","commendIcon":"http://appimg
     * .hicloud.com/hwmarket/files/entranceIcon/4f3a7f44e6d845f8b9d6f2ec6049a3bd
     * /1494647342134_1080x684.png",
     * "detailId":"waplinkdetail|f3f085fca6824061b0387c17b9fb19d2__entrance|1495097563117"},
     * {"icon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/92b267f13e64449d9a797d6aa6a47642
     * /1494576365155_1080x684.png","trace":"1cb9d75a1107447d87d331218b025093;__entrance9;
     * __92b267f13e64449d9a797d6aa6a47642","name":"有道云笔记","commendIcon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/92b267f13e64449d9a797d6aa6a47642
     * /1494576365155_1080x684.png","detailId":"app|C31969|1495097563118"},{"icon":"http://appimg
     * .hicloud.com/hwmarket/files/entranceIcon/8d01f37f3b3e4de4a3e8b4755f40e396
     * /1494811085912_1080x684.png","trace":"1cb9d75a1107447d87d331218b025093;__entrance9;
     * __8d01f37f3b3e4de4a3e8b4755f40e396","name":"我爱你","commendIcon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/8d01f37f3b3e4de4a3e8b4755f40e396
     * /1494811085912_1080x684.png",
     * "detailId":"waplinkdetail|e93dfd719e8a48808ccabe48b841f5ad__entrance|1495097563118"}],
     * "dataList-type":2,"listId":"1","isUpdatableFilter":0,"layoutName":"immersivebannercard",
     * "layoutId":7100,"isInstalledFilter":0},
     * {"dataList":[{"trace":"1cb9d75a1107447d87d331218b025093","size":4}],"dataList-type":0,
     * "listId":"11","isUpdatableFilter":0,"layoutName":"navispylinecard","layoutId":806840,
     * "isInstalledFilter":0},{"dataList":[{"trace":"1cb9d75a1107447d87d331218b025093",
     * "name":"精品应用","list":[{"appVersionName":"7.5.0","dependentedApps":[],
     * "memo":"看视频直播网红明星上微博","operaType":null,"score":"4.7","package":"com.sina.weibo",
     * "versionCode":"3371","appid":"C7166","exIcons":{},"downCountDesc":"13.2亿次安装",
     * "btnDisable":0,"name":"微博","ID":"522110c8bbf745b0afb6768c59291b3c",
     * "downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/52/522110c8bbf745b0afb6768c59291b3c/com.sina
     * .weibo.1705121527.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=HiAd&listId=2&position=1&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr
     * =54d1dca28f48856c14947aa2f7353c27%3BcdrInfo%3A20170518165243aps15057695%5E%7BopType%7D
     * %5E18750%5EC7166%5E2%5E1%5E2a74fad8d97011e292cf101b543e3aa5%5E17263
     * %5E39ca7404dde3fc0458e03bf84ed0a0decefdb7397f1ad5f661ae79d679835a24%5E
     * %5EU0NFOn5TUkM6Mn5PTDp-U0w6flBUOn5UOn5QTDo1%5E2017-05-18+16%3A52%3A43%5E1%5E%E8%8D%A3%E8
     * %80%807%5E0.002449%5E0%5E6.79%5E6.79%5E3.34981%5E900086000000000827%5E20358%5E%5E%5E
     * %5E7.2.3%5E1495097561980%5E0%3BisAdTag%3A0%3B%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A7120&encryptType
     * =1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/522110c8bbf745b0afb6768c59291b3c.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"60.7M","intro":"60.7M",
     * "size":63665004,"describeType":"2","price":"0","exIcon":null,"listId":"2","stars":"5.0",
     * "videoFlag":0,"md5":"F5D2A866B8961BE7E30A992DFDA9E973","comefrom":null,
     * "detailId":"app|C7166__HiAd____2__1__54d1dca28f48856c14947aa2f7353c27%3BcdrInfo
     * %3A20170518165243aps15057695%5E%7BopType%7D%5E18750%5EC7166%5E2%5E1
     * %5E2a74fad8d97011e292cf101b543e3aa5%5E17263
     * %5E39ca7404dde3fc0458e03bf84ed0a0decefdb7397f1ad5f661ae79d679835a24%5E
     * %5EU0NFOn5TUkM6Mn5PTDp-U0w6flBUOn5UOn5QTDo1%5E2017-05-18+16%3A52%3A43%5E1%5E%E8%8D%A3%E8
     * %80%807%5E0.002449%5E0%5E6.79%5E6.79%5E3.34981%5E900086000000000827%5E20358%5E%5E%5E
     * %5E7.2.3%5E1495097561980%5E0%3BisAdTag%3A0%3B%3B"},{"appVersionName":"8.5.5",
     * "dependentedApps":[],"memo":"春季周边游门票立减！","operaType":null,"score":"4.5","package":"com
     * .Qunar","versionCode":"140","appid":"C262","exIcons":{},"downCountDesc":"4.4亿次安装",
     * "btnDisable":0,"name":"去哪儿旅行","ID":"207e1029535f4d30adc29a3c0a7413c4",
     * "downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/20/207e1029535f4d30adc29a3c0a7413c4/com
     * .Qunar.1705181522.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=HiAd&listId=2&position=2&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr
     * =54d1dca28f48856c14947aa2f7353c27%3BcdrInfo%3A20170518165243aps15057696%5E%7BopType%7D
     * %5E9451%5EC262%5E2%5E2%5E4c437759d97211e292cf101b543e3aa5%5E17263
     * %5E39ca7404dde3fc0458e03bf84ed0a0decefdb7397f1ad5f661ae79d679835a24%5E
     * %5EU0NFOn5TUkM6Mn5PTDp-U0w6flBUOn5UOn5QTDo1%5E2017-05-18+16%3A52%3A43%5E1%5E%E8%8D%A3%E8
     * %80%807%5E0.000486%5E0%5E16.88%5E16.88%5E16.22222%5E900086000000000376%5E20358%5E%5E%5E
     * %5E7.2.3%5E1495097561980%5E0%3BisAdTag%3A0%3B%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A7120&encryptType
     * =1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/207e1029535f4d30adc29a3c0a7413c4.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"37.6M","intro":"37.6M",
     * "size":39391607,"describeType":"2","price":"0","exIcon":null,"listId":"2","stars":"5.0",
     * "videoFlag":0,"md5":"A18BD48709E0683098291E7ED09DC91F","comefrom":null,
     * "detailId":"app|C262__HiAd____2__2__54d1dca28f48856c14947aa2f7353c27%3BcdrInfo
     * %3A20170518165243aps15057696%5E%7BopType%7D%5E9451%5EC262%5E2%5E2
     * %5E4c437759d97211e292cf101b543e3aa5%5E17263
     * %5E39ca7404dde3fc0458e03bf84ed0a0decefdb7397f1ad5f661ae79d679835a24%5E
     * %5EU0NFOn5TUkM6Mn5PTDp-U0w6flBUOn5UOn5QTDo1%5E2017-05-18+16%3A52%3A43%5E1%5E%E8%8D%A3%E8
     * %80%807%5E0.000486%5E0%5E16.88%5E16.88%5E16.22222%5E900086000000000376%5E20358%5E%5E%5E
     * %5E7.2.3%5E1495097561980%5E0%3BisAdTag%3A0%3B%3B"},{"appVersionName":"8.5.0",
     * "dependentedApps":[],"memo":"搜题快准，名师辅导，高效提分！","operaType":null,"score":"4.6",
     * "package":"com.baidu.homework","versionCode":"284","appid":"C10113015","exIcons":{},
     * "downCountDesc":"1.4亿次安装","btnDisable":0,"name":"作业帮",
     * "ID":"ac5447fa37c843b1ba7b7f2763e5da19","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/ac/ac5447fa37c843b1ba7b7f2763e5da19/com.baidu
     * .homework.1705172142.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3
     * &cno=4010001&source=interveneList&listId=2&position=3&hcrId
     * =0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=54d1dca28f48856c14947aa2f7353c27%3BserviceType
     * %3A0%3Bisshake%3A0%3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093
     * %3BlayoutId%3A7120&encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/ac5447fa37c843b1ba7b7f2763e5da19.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"40.1M","intro":"40.1M",
     * "size":42012429,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/3ddd7bd56e7b4d688aa5d5cff68bbefb_71.png?listId=2"],
     * "price":"0","exIcon":null,"listId":"2","stars":"5.0","videoFlag":0,
     * "md5":"BDE6DAE3307640E48711B04DE0F95C5D","comefrom":null,
     * "detailId":"app|C10113015__interveneList____2__3__54d1dca28f48856c14947aa2f7353c27%3B"},
     * {"appVersionName":"7.3.2","dependentedApps":[],"memo":"携程在手，说走就走","operaType":null,
     * "score":"4.5","package":"ctrip.android.view","versionCode":"151","appid":"C5157",
     * "exIcons":{},"downCountDesc":"4.7亿次安装","btnDisable":0,"name":"携程旅行",
     * "ID":"97d4d76bbef64ed2ae3161057f35a545","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/97/97d4d76bbef64ed2ae3161057f35a545/ctrip.android
     * .view.1704270947.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=HiAd&listId=2&position=4&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr
     * =54d1dca28f48856c14947aa2f7353c27%3BcdrInfo%3A20170518165243aps15057698%5E%7BopType%7D
     * %5E13351%5EC5157%5E2%5E6%5E1a3d639edc9511e292cf101b543e3aa5%5E17263
     * %5E39ca7404dde3fc0458e03bf84ed0a0decefdb7397f1ad5f661ae79d679835a24%5E
     * %5EU0NFOn5TUkM6Mn5PTDp-U0w6flBUOn5UOn5QTDo1%5E2017-05-18+16%3A52%3A43%5E1%5E%E8%8D%A3%E8
     * %80%807%5E0.000584%5E0%5E13.5%5E13.5%5E11.86918%5E900086000000034654%5E20358%5E%5E%5E
     * %5E7.2.3%5E1495097561980%5E0%3BisAdTag%3A0%3B%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A7120&encryptType
     * =1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/97d4d76bbef64ed2ae3161057f35a545.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"50.7M","intro":"50.7M",
     * "size":53159774,"describeType":"2","price":"0","exIcon":null,"listId":"2","stars":"4.5",
     * "videoFlag":0,"md5":"B8AC40C8124ADF4A1B826E72902A78FF","comefrom":null,
     * "detailId":"app|C5157__HiAd____2__4__54d1dca28f48856c14947aa2f7353c27%3BcdrInfo
     * %3A20170518165243aps15057698%5E%7BopType%7D%5E13351%5EC5157%5E2%5E6
     * %5E1a3d639edc9511e292cf101b543e3aa5%5E17263
     * %5E39ca7404dde3fc0458e03bf84ed0a0decefdb7397f1ad5f661ae79d679835a24%5E
     * %5EU0NFOn5TUkM6Mn5PTDp-U0w6flBUOn5UOn5QTDo1%5E2017-05-18+16%3A52%3A43%5E1%5E%E8%8D%A3%E8
     * %80%807%5E0.000584%5E0%5E13.5%5E13.5%5E11.86918%5E900086000000034654%5E20358%5E%5E%5E
     * %5E7.2.3%5E1495097561980%5E0%3BisAdTag%3A0%3B%3B"},{"appVersionName":"6.0.3",
     * "dependentedApps":[],"memo":"唯品会-欢乐颂2独家电商 同款热卖","operaType":null,"score":"4.2",
     * "package":"com.achievo.vipshop","versionCode":"663","appid":"C9136","exIcons":{},
     * "downCountDesc":"4.1亿次安装","btnDisable":0,"name":"唯品会",
     * "ID":"e05147cfc4f0403a97c7763b4a3b1712","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/e0/e05147cfc4f0403a97c7763b4a3b1712/com.achievo
     * .vipshop.1705170942.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=HiAd&listId=2&position=5&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr
     * =54d1dca28f48856c14947aa2f7353c27%3BcdrInfo%3A20170518165243aps15057699%5E%7BopType%7D
     * %5E18605%5EC9136%5E2%5E8%5E3b538373dc9b11e292cf101b543e3aa5%5E17263
     * %5E39ca7404dde3fc0458e03bf84ed0a0decefdb7397f1ad5f661ae79d679835a24%5E
     * %5EU0NFOn5TUkM6Mn5PTDp-U0w6flBUOn5UOn5QTDo1%5E2017-05-18+16%3A52%3A43%5E1%5E%E8%8D%A3%E8
     * %80%807%5E0.000559%5E0%5E12.4%5E12.4%5E9.9034%5E900086000000020151%5E20358%5E%5E%5E
     * %5E7.2.3%5E1495097561980%5E0%3BisAdTag%3A0%3B%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A7120&encryptType
     * =1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/e05147cfc4f0403a97c7763b4a3b1712.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"33.9M","intro":"33.9M",
     * "size":35512336,"describeType":"2","price":"0","exIcon":null,"listId":"2","stars":"4.5",
     * "videoFlag":0,"md5":"4F509F107E2CA190CC3F00A686EA44EB","comefrom":null,
     * "detailId":"app|C9136__HiAd____2__5__54d1dca28f48856c14947aa2f7353c27%3BcdrInfo
     * %3A20170518165243aps15057699%5E%7BopType%7D%5E18605%5EC9136%5E2%5E8
     * %5E3b538373dc9b11e292cf101b543e3aa5%5E17263
     * %5E39ca7404dde3fc0458e03bf84ed0a0decefdb7397f1ad5f661ae79d679835a24%5E
     * %5EU0NFOn5TUkM6Mn5PTDp-U0w6flBUOn5UOn5QTDo1%5E2017-05-18+16%3A52%3A43%5E1%5E%E8%8D%A3%E8
     * %80%807%5E0.000559%5E0%5E12.4%5E12.4%5E9.9034%5E900086000000020151%5E20358%5E%5E%5E
     * %5E7.2.3%5E1495097561980%5E0%3BisAdTag%3A0%3B%3B"},{"appVersionName":"7.0",
     * "dependentedApps":[],"memo":"学习、工作、出国必备翻译&词典软件","operaType":null,"score":"4.9",
     * "package":"com.baidu.baidutranslate","versionCode":"67","appid":"C10100747","exIcons":{},
     * "downCountDesc":"1799万次安装","btnDisable":0,"name":"百度翻译",
     * "ID":"6d73a14521894533a363df29b4a17d9d","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/6d/6d73a14521894533a363df29b4a17d9d/com.baidu
     * .baidutranslate.1705170002.apk?sign=c9d81011e610010520007000
     *
     * @7ED2A239A80FA54D27BEFAACCC1AB7F3&cno=4010001&source=interveneList&listId=2&position=6
     * &hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=54d1dca28f48856c14947aa2f7353c27
     * %3BserviceType%3A0%3Bisshake%3A0%3Bs%3A1495097561980%3Btrace
     * %3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A7120&encryptType=1","icon":"http://appimg
     * .hicloud.com/hwmarket/files/application/icon144/6d73a14521894533a363df29b4a17d9d.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"44.9M","intro":"44.9M",
     * "size":47062373,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/3ddd7bd56e7b4d688aa5d5cff68bbefb_71.png?listId=2"],
     * "price":"0","exIcon":null,"listId":"2","stars":"5.0","videoFlag":0,
     * "md5":"0366E7D227A07A55335594FCB140F808","comefrom":null,
     * "detailId":"app|C10100747__interveneList____2__6__54d1dca28f48856c14947aa2f7353c27%3B"},
     * {"appVersionName":"4.0.40","dependentedApps":[],"memo":"一键开启游戏直播新时代","operaType":null,
     * "score":"4.8","package":"com.meelive.ingkee","versionCode":"700","appid":"C10285679",
     * "exIcons":{},"downCountDesc":"3564万次安装","btnDisable":0,"name":"映客直播",
     * "ID":"32bc924d05404719b5cf08594ef19f71","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/32/32bc924d05404719b5cf08594ef19f71/com.meelive
     * .ingkee.1705101032.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=HiAd&listId=2&position=7&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr
     * =54d1dca28f48856c14947aa2f7353c27%3BcdrInfo%3A20170518165243aps15057701%5E%7BopType%7D
     * %5E13073%5EC10285679%5E2%5E11%5E6cf73b82dc9b11e292cf101b543e3aa5%5E17263
     * %5E39ca7404dde3fc0458e03bf84ed0a0decefdb7397f1ad5f661ae79d679835a24%5E
     * %5EU0NFOn5TUkM6Mn5PTDp-U0w6flBUOn5UOn5QTDo5%5E2017-05-18+16%3A52%3A43%5E1%5E%E8%8D%A3%E8
     * %80%807%5E0.000346%5E0%5E16.0%5E16.0%5E15.14014%5E900086000024285932%5E20358%5E%5E%5E
     * %5E7.2.3%5E1495097561980%5E0%3BisAdTag%3A0%3B%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A7120&encryptType
     * =1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/32bc924d05404719b5cf08594ef19f71.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"36.2M","intro":"36.2M",
     * "size":37917531,"describeType":"2","price":"0","exIcon":null,"listId":"2","stars":"5.0",
     * "videoFlag":0,"md5":"810FFD892F6421DC3F21523A36E3607C","comefrom":null,
     * "detailId":"app|C10285679__HiAd____2__7__54d1dca28f48856c14947aa2f7353c27%3BcdrInfo
     * %3A20170518165243aps15057701%5E%7BopType%7D%5E13073%5EC10285679%5E2%5E11
     * %5E6cf73b82dc9b11e292cf101b543e3aa5%5E17263
     * %5E39ca7404dde3fc0458e03bf84ed0a0decefdb7397f1ad5f661ae79d679835a24%5E
     * %5EU0NFOn5TUkM6Mn5PTDp-U0w6flBUOn5UOn5QTDo5%5E2017-05-18+16%3A52%3A43%5E1%5E%E8%8D%A3%E8
     * %80%807%5E0.000346%5E0%5E16.0%5E16.0%5E15.14014%5E900086000024285932%5E20358%5E%5E%5E
     * %5E7.2.3%5E1495097561980%5E0%3BisAdTag%3A0%3B%3B"},{"appVersionName":"6.2.2",
     * "dependentedApps":[],"memo":"高性价比飞机票酒店旅游门票预订","operaType":null,"score":"4.1",
     * "package":"com.china3s.android","versionCode":"622","appid":"C10174030","exIcons":{},
     * "downCountDesc":"356万次安装","btnDisable":0,"name":"春秋航空",
     * "ID":"c4a301593fd248a9bdde14b304c6db01","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/c4/c4a301593fd248a9bdde14b304c6db01/com.china3s
     * .android.1705181112.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=HiAd&listId=2&position=8&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr
     * =54d1dca28f48856c14947aa2f7353c27%3BcdrInfo%3A20170518165243aps15057702%5E%7BopType%7D
     * %5E11068%5EC10174030%5E2%5E12%5E76cc8906dc9b11e292cf101b543e3aa5%5E17263
     * %5E39ca7404dde3fc0458e03bf84ed0a0decefdb7397f1ad5f661ae79d679835a24%5E
     * %5EU0NFOn5TUkM6Mn5PTDp-U0w6flBUOn5UOn5QTDo5%5E2017-05-18+16%3A52%3A43%5E1%5E%E8%8D%A3%E8
     * %80%807%5E0.000349%5E0%5E15.01%5E15.01%5E14.4788%5E900086000021118531%5E20358%5E%5E%5E
     * %5E7.2.3%5E1495097561980%5E0%3BisAdTag%3A0%3B%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A7120&encryptType
     * =1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/c4a301593fd248a9bdde14b304c6db01.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"28.1M","intro":"28.1M",
     * "size":29424556,"describeType":"2","price":"0","exIcon":null,"listId":"2","stars":"4.5",
     * "videoFlag":0,"md5":"0BA96FA03055FBFA73C21FE4988864F9","comefrom":null,
     * "detailId":"app|C10174030__HiAd____2__8__54d1dca28f48856c14947aa2f7353c27%3BcdrInfo
     * %3A20170518165243aps15057702%5E%7BopType%7D%5E11068%5EC10174030%5E2%5E12
     * %5E76cc8906dc9b11e292cf101b543e3aa5%5E17263
     * %5E39ca7404dde3fc0458e03bf84ed0a0decefdb7397f1ad5f661ae79d679835a24%5E
     * %5EU0NFOn5TUkM6Mn5PTDp-U0w6flBUOn5UOn5QTDo5%5E2017-05-18+16%3A52%3A43%5E1%5E%E8%8D%A3%E8
     * %80%807%5E0.000349%5E0%5E15.01%5E15.01%5E14.4788%5E900086000021118531%5E20358%5E%5E%5E
     * %5E7.2.3%5E1495097561980%5E0%3BisAdTag%3A0%3B%3B"},{"appVersionName":"9.1.3",
     * "dependentedApps":[],"memo":"暑期预热，畅享优惠！","operaType":null,"score":"3.8","package":"com
     * .tuniu.app.ui","versionCode":"109","appid":"C30591","exIcons":{},
     * "downCountDesc":"5217万次安装","btnDisable":0,"name":"途牛旅游",
     * "ID":"59c8b7d6bdcc498e84cb3f2627f4b8dd","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/59/59c8b7d6bdcc498e84cb3f2627f4b8dd/com.tuniu.app
     * .ui.1705111347.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=HiAd&listId=2&position=9&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr
     * =54d1dca28f48856c14947aa2f7353c27%3BcdrInfo%3A20170518165243aps15057703%5E%7BopType%7D
     * %5E14789%5EC30591%5E2%5E15%5E8e0ac76fdc9b11e292cf101b543e3aa5%5E17263
     * %5E39ca7404dde3fc0458e03bf84ed0a0decefdb7397f1ad5f661ae79d679835a24%5E
     * %5EU0NFOn5TUkM6Mn5PTDp-U0w6flBUOn5UOn5QTDo5%5E2017-05-18+16%3A52%3A43%5E1%5E%E8%8D%A3%E8
     * %80%807%5E0.000299%5E0%5E16.9%5E16.9%5E16.45217%5E900086000000025111%5E20358%5E%5E%5E
     * %5E7.2.3%5E1495097561980%5E0%3BisAdTag%3A0%3B%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A7120&encryptType
     * =1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/59c8b7d6bdcc498e84cb3f2627f4b8dd.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"33.9M","intro":"33.9M",
     * "size":35592131,"describeType":"2","price":"0","exIcon":null,"listId":"2","stars":"4.0",
     * "videoFlag":0,"md5":"8E4D2F150FA151A504A8B5FA1CD4D5D6","comefrom":null,
     * "detailId":"app|C30591__HiAd____2__9__54d1dca28f48856c14947aa2f7353c27%3BcdrInfo
     * %3A20170518165243aps15057703%5E%7BopType%7D%5E14789%5EC30591%5E2%5E15
     * %5E8e0ac76fdc9b11e292cf101b543e3aa5%5E17263
     * %5E39ca7404dde3fc0458e03bf84ed0a0decefdb7397f1ad5f661ae79d679835a24%5E
     * %5EU0NFOn5TUkM6Mn5PTDp-U0w6flBUOn5UOn5QTDo5%5E2017-05-18+16%3A52%3A43%5E1%5E%E8%8D%A3%E8
     * %80%807%5E0.000299%5E0%5E16.9%5E16.9%5E16.45217%5E900086000000025111%5E20358%5E%5E%5E
     * %5E7.2.3%5E1495097561980%5E0%3BisAdTag%3A0%3B%3B"},{"appVersionName":"7.9.2",
     * "dependentedApps":[],"memo":"优质的买房卖房二手房新房交易平台","operaType":null,"score":"3.5",
     * "package":"com.homelink.android","versionCode":"7920","appid":"C22553","exIcons":{},
     * "downCountDesc":"1898万次安装","btnDisable":0,"name":"链家",
     * "ID":"e8a3405a41dd4778b44c59faa4a20ccd","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/e8/e8a3405a41dd4778b44c59faa4a20ccd/com.homelink
     * .android.1705101507.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=HiAd&listId=2&position=10&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr
     * =54d1dca28f48856c14947aa2f7353c27%3BcdrInfo%3A20170518165243aps15057704%5E%7BopType%7D
     * %5E8899%5EC22553%5E2%5E17%5E9a269067dc9b11e292cf101b543e3aa5%5E17263
     * %5E39ca7404dde3fc0458e03bf84ed0a0decefdb7397f1ad5f661ae79d679835a24%5E
     * %5EU0NFOn5TUkM6Mn5PTDp-U0w6flBUOn5UOn5QTDo5%5E2017-05-18+16%3A52%3A43%5E1%5E%E8%8D%A3%E8
     * %80%807%5E0.000286%5E0%5E17.2%5E17.2%5E16.16783%5E900086000000020645%5E20358%5E%5E%5E
     * %5E7.2.3%5E1495097561980%5E0%3BisAdTag%3A0%3B%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A7120&encryptType
     * =1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/e8a3405a41dd4778b44c59faa4a20ccd.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"31.9M","intro":"31.9M",
     * "size":33417494,"describeType":"2","price":"0","exIcon":null,"listId":"2","stars":"4.0",
     * "videoFlag":0,"md5":"4B3F5B043BAAD39E41048B5D79D39EE8","comefrom":null,
     * "detailId":"app|C22553__HiAd____2__10__54d1dca28f48856c14947aa2f7353c27%3BcdrInfo
     * %3A20170518165243aps15057704%5E%7BopType%7D%5E8899%5EC22553%5E2%5E17
     * %5E9a269067dc9b11e292cf101b543e3aa5%5E17263
     * %5E39ca7404dde3fc0458e03bf84ed0a0decefdb7397f1ad5f661ae79d679835a24%5E
     * %5EU0NFOn5TUkM6Mn5PTDp-U0w6flBUOn5UOn5QTDo5%5E2017-05-18+16%3A52%3A43%5E1%5E%E8%8D%A3%E8
     * %80%807%5E0.000286%5E0%5E17.2%5E17.2%5E16.16783%5E900086000000020645%5E20358%5E%5E%5E
     * %5E7.2.3%5E1495097561980%5E0%3BisAdTag%3A0%3B%3B"},{"appVersionName":"6.7.0.0",
     * "dependentedApps":[],"memo":"手绘自拍、贴纸、马赛克超多照片玩法！","operaType":null,"score":"4.8",
     * "package":"com.mt.mtxx.mtxx","versionCode":"6700","appid":"C6023","exIcons":{},
     * "downCountDesc":"4.0亿次安装","btnDisable":0,"name":"美图秀秀",
     * "ID":"4ea52730ab204016b0c14c68cf24738b","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/4e/4ea52730ab204016b0c14c68cf24738b/com.mt.mtxx
     * .mtxx.1705171657.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=interveneList&listId=2&position=11&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA
     * &extendStr=54d1dca28f48856c14947aa2f7353c27%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A7120&encryptType
     * =1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/4ea52730ab204016b0c14c68cf24738b.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"51.8M","intro":"51.8M",
     * "size":54311865,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/3ddd7bd56e7b4d688aa5d5cff68bbefb_71.png?listId=2"],
     * "price":"0","exIcon":null,"listId":"2","stars":"5.0","videoFlag":0,
     * "md5":"7BE712E52CA3501D5AE05D7F69ECA5E2","comefrom":null,
     * "detailId":"app|C6023__interveneList____2__11__54d1dca28f48856c14947aa2f7353c27%3B"},
     * {"appVersionName":"5.2.2","dependentedApps":[],"memo":"快乐男声、妈妈是超人2全网热播","operaType":null,
     * "score":"3.8","package":"com.hunantv.imgo.activity","versionCode":"522100",
     * "appid":"C10132452","exIcons":{},"downCountDesc":"1.3亿次安装","btnDisable":0,"name":"芒果TV",
     * "ID":"bc9429f675ff428f93217ade7132c226","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/bc/bc9429f675ff428f93217ade7132c226/com.hunantv.imgo
     * .activity.1705171527.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3
     * &cno=4010001&source=interveneList&listId=2&position=12&hcrId
     * =0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=54d1dca28f48856c14947aa2f7353c27%3BserviceType
     * %3A0%3Bisshake%3A0%3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093
     * %3BlayoutId%3A7120&encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/bc9429f675ff428f93217ade7132c226.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"36.5M","intro":"36.5M",
     * "size":38302234,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/3ddd7bd56e7b4d688aa5d5cff68bbefb_71.png?listId=2"],
     * "price":"0","exIcon":null,"listId":"2","stars":"4.0","videoFlag":0,
     * "md5":"19FC70649264A299961E486FE969B347","comefrom":null,
     * "detailId":"app|C10132452__interveneList____2__12__54d1dca28f48856c14947aa2f7353c27%3B"}],
     * "detailId":"9EFEE843A95311E5BE9B548998110001"}],"dataList-type":3,"listId":"1",
     * "isUpdatableFilter":0,"name":"精品应用","layoutName":"horizonhomecard","layoutId":7120,
     * "isInstalledFilter":1,"detailId":"9EFEE843A95311E5BE9B548998110001"},
     * {"dataList":[{"trace":"1cb9d75a1107447d87d331218b025093","name":"精品新游",
     * "list":[{"appVersionName":"1.3.0.1","dependentedApps":[],"memo":"端游原班人马匠心打造 金庸武学 原味再现",
     * "operaType":null,"score":"3.2","package":"com.tencent.tmgp.tstl","activityInfoCues":"",
     * "versionCode":"103","appid":"C100005625","exIcons":{},
     * "activityId":"f970f8cfbcf147888f689e16cb79919e","downCountDesc":"5万次安装","prizeState":1,
     * "btnDisable":0,"name":"天龙八部","ID":"4ffc29ac60ac420696c1975a57ed14a4",
     * "downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/4f/4ffc29ac60ac420696c1975a57ed14a4/com.tencent.tmgp
     * .tstl.1705171547.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=interveneList&listId=37&position=1&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA
     * &extendStr=58e8dece9aa8d5c050831440f15ae807%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807136
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/4ffc29ac60ac420696c1975a57ed14a4.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"707.2M","intro":"707.2M",
     * "size":741534970,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/3ddd7bd56e7b4d688aa5d5cff68bbefb_71.png?listId=37",
     * "http://appimg.hicloud.com/hwmarket/files/appLabel/f52f5e8e6f9b4d708ab6f9fcc49bd5b9_71.png
     * ?listId=37"],"price":"0","exIcon":null,"listId":"37","videoFlag":0,"stars":"3.5",
     * "md5":"1B0FA45A640AD928256CBD106E9982E0","isPrize":1,"activityName":"十年聚散再回天龙，下载赢P10",
     * "comefrom":null,
     * "detailId":"app|C100005625__interveneList____37__1__58e8dece9aa8d5c050831440f15ae807%3B"},
     * {"appVersionName":"1.2.3","dependentedApps":[],"memo":"探索地牢，收集武器，爆战小怪兽","operaType":null,
     * "score":"4.5","package":"yuanqiqishi.game.huawei","versionCode":"609","appid":"C10836373",
     * "exIcons":{},"downCountDesc":"115万次安装","btnDisable":0,"name":"元气骑士",
     * "ID":"f48f7360cad74ed9a073f0e84dce2bed","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/f4/f48f7360cad74ed9a073f0e84dce2bed/yuanqiqishi.game
     * .huawei.1705111858.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=interveneList&listId=37&position=2&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA
     * &extendStr=58e8dece9aa8d5c050831440f15ae807%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807136
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/f48f7360cad74ed9a073f0e84dce2bed.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"32.9M","intro":"32.9M",
     * "size":34506827,"describeType":"2","price":"0","exIcon":null,"listId":"37","stars":"4.5",
     * "videoFlag":0,"md5":"90E10ED6030377D9FDCE8D834141F72F","comefrom":null,
     * "detailId":"app|C10836373__interveneList____37__2__58e8dece9aa8d5c050831440f15ae807%3B"},
     * {"appVersionName":"10.1.0","dependentedApps":[],"memo":"千里楚地，还原战国狼烟，绚丽国风，独创门客玩法",
     * "operaType":null,"score":"3.5","package":"com.yh.simeirengame.huawei","versionCode":"10",
     * "appid":"C10836051","exIcons":{},"downCountDesc":"17万次安装","btnDisable":0,
     * "name":"思美人（下载赢P10）","ID":"4b610ed561b040739b92edb63bf7105d","downurl":"http://appdlc
     * .hicloud.com/dl/appdl/application/apk/4b/4b610ed561b040739b92edb63bf7105d/com.yh
     * .simeirengame.huawei.1705041208.apk?sign=c9d81011e610010520007000
     * @7ED2A239A80FA54D27BEFAACCC1AB7F3&cno=4010001&source=interveneList&listId=37&position=3
     * &hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=58e8dece9aa8d5c050831440f15ae807
     * %3BserviceType%3A0%3Bisshake%3A0%3Bs%3A1495097561980%3Btrace
     * %3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807136&encryptType=1",
     * "icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/4b610ed561b040739b92edb63bf7105d_2.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"391.2M","intro":"391.2M",
     * "size":410161390,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/f52f5e8e6f9b4d708ab6f9fcc49bd5b9_71.png?listId=37"],
     * "price":"0","exIcon":null,"listId":"37","stars":"3.5","videoFlag":0,
     * "md5":"08C2818C1E3D0E4872108376A6CCC630","comefrom":null,
     * "detailId":"app|C10836051__interveneList____37__3__58e8dece9aa8d5c050831440f15ae807%3B"},
     * {"appVersionName":"1.2.0","dependentedApps":[],"memo":"魔晶猎人正版授权跑酷手游!","operaType":null,
     * "score":"3.7","package":"com.idreamsky.mhrun.huawei","versionCode":"1020",
     * "appid":"C10905990","exIcons":{},"downCountDesc":"少于1万次安装","btnDisable":0,"name":"我的酷跑世界",
     * "ID":"85dc61b2650a4be0b082fcd4934930b9","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/85/85dc61b2650a4be0b082fcd4934930b9/com.idreamsky.mhrun
     * .huawei.1705151122.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=interveneList&listId=37&position=4&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA
     * &extendStr=58e8dece9aa8d5c050831440f15ae807%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807136
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/85dc61b2650a4be0b082fcd4934930b9.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"54.5M","intro":"54.5M",
     * "size":57108114,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/f52f5e8e6f9b4d708ab6f9fcc49bd5b9_71.png?listId=37"],
     * "price":"0","exIcon":null,"listId":"37","stars":"4.0","videoFlag":0,
     * "md5":"FE4E00D33F9E5E3E4629F7FFAA0E81D4","comefrom":null,
     * "detailId":"app|C10905990__interveneList____37__4__58e8dece9aa8d5c050831440f15ae807%3B"},
     * {"appVersionName":"1.6.5","dependentedApps":[],"memo":"技能搭配连招，无限悬空连击","operaType":null,
     * "score":"3.4","package":"com.woqutech.zws.android.huawei","versionCode":"1605",
     * "appid":"C10786514","exIcons":{},"downCountDesc":"38万次安装","btnDisable":0,"name":"最无双(激斗三国)
     * ","ID":"ae5679d8f2e94004a3b80038d5e6aae7","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/ae/ae5679d8f2e94004a3b80038d5e6aae7/com.woqutech.zws.android
     * .huawei.1705161034.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=interveneList&listId=37&position=5&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA
     * &extendStr=58e8dece9aa8d5c050831440f15ae807%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807136
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/ae5679d8f2e94004a3b80038d5e6aae7.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"195.3M","intro":"195.3M",
     * "size":204742792,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/f52f5e8e6f9b4d708ab6f9fcc49bd5b9_71.png?listId=37"],
     * "price":"0","exIcon":null,"listId":"37","stars":"3.5","videoFlag":0,
     * "md5":"D663818CE55DB25372623B997D5A3F05","comefrom":null,
     * "detailId":"app|C10786514__interveneList____37__5__58e8dece9aa8d5c050831440f15ae807%3B"},
     * {"appVersionName":"1.0","dependentedApps":[],"memo":"是一款3D考验操作的贪吃蛇游戏","operaType":null,
     * "score":"5.0","package":"com.magicbricktech.cn.gosnake.huawei","versionCode":"1",
     * "appid":"C10895449","exIcons":{},"downCountDesc":"少于1万次安装","btnDisable":0,"name":"爱豆贪吃蛇",
     * "ID":"7728a00517f14278a5e5e59b2b927521","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/77/7728a00517f14278a5e5e59b2b927521/com.magicbricktech.cn
     * .gosnake.huawei.1705081134.apk?sign=c9d81011e610010520007000
     * @7ED2A239A80FA54D27BEFAACCC1AB7F3&cno=4010001&source=interveneList&listId=37&position=6
     * &hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=58e8dece9aa8d5c050831440f15ae807
     * %3BserviceType%3A0%3Bisshake%3A0%3Bs%3A1495097561980%3Btrace
     * %3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807136&encryptType=1",
     * "icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/7728a00517f14278a5e5e59b2b927521_1.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"51.4M","intro":"51.4M",
     * "size":53915491,"describeType":"2","price":"0","exIcon":null,"listId":"37","stars":"5.0",
     * "videoFlag":0,"md5":"3B2FD55350C1B49DCA09912DECF035B5","comefrom":null,
     * "detailId":"app|C10895449__interveneList____37__6__58e8dece9aa8d5c050831440f15ae807%3B"},
     * {"appVersionName":"1.0.9","dependentedApps":[],"memo":"离线战斗、自动修仙、 轻松刷怪 、自由交易",
     * "operaType":null,"score":"3.0","package":"qyj.huawei","versionCode":"109",
     * "appid":"C10847213","exIcons":{},"downCountDesc":"23万次安装","btnDisable":0,"name":"青云诀",
     * "ID":"3455a1b60fc1407894ccaeb9322f6fdc","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/34/3455a1b60fc1407894ccaeb9322f6fdc/qyj
     * .huawei.1705091653.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=interveneList&listId=37&position=7&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA
     * &extendStr=58e8dece9aa8d5c050831440f15ae807%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807136
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/3455a1b60fc1407894ccaeb9322f6fdc.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"94.6M","intro":"94.6M",
     * "size":99218692,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/f52f5e8e6f9b4d708ab6f9fcc49bd5b9_71.png?listId=37"],
     * "price":"0","exIcon":null,"listId":"37","stars":"3.0","videoFlag":0,
     * "md5":"1C7D99E0C5586C6341E22891CB2787B7","comefrom":null,
     * "detailId":"app|C10847213__interveneList____37__7__58e8dece9aa8d5c050831440f15ae807%3B"},
     * {"appVersionName":"1.1.2","dependentedApps":[],"memo":"既幽默搞笑又略重口味的僵尸手游","operaType":null,
     * "score":"3.9","package":"com.jianyou.jszzj.huawei","versionCode":"10102",
     * "appid":"C10758665","exIcons":{},"downCountDesc":"129万次安装","btnDisable":0,"name":"僵尸榨汁机",
     * "ID":"8ccc1937166c496e9afb1ae885aa874c","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/8c/8ccc1937166c496e9afb1ae885aa874c/com.jianyou.jszzj
     * .huawei.1705041602.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=interveneList&listId=37&position=8&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA
     * &extendStr=58e8dece9aa8d5c050831440f15ae807%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807136
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/8ccc1937166c496e9afb1ae885aa874c.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"48.1M","intro":"48.1M",
     * "size":50460153,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/f52f5e8e6f9b4d708ab6f9fcc49bd5b9_71.png?listId=37"],
     * "price":"0","exIcon":null,"listId":"37","stars":"4.0","videoFlag":0,
     * "md5":"A9B903912F6DDF213DED04436FF3363F","comefrom":null,
     * "detailId":"app|C10758665__interveneList____37__8__58e8dece9aa8d5c050831440f15ae807%3B"},
     * {"appVersionName":"1.1.5.17042122","dependentedApps":[],"memo":"剧情向实时策略RPG",
     * "operaType":null,"score":"4.6","package":"com.zhankaigame.destiny.huawei",
     * "versionCode":"19","appid":"C10720493","exIcons":{},"downCountDesc":"4万次安装",
     * "btnDisable":0,"name":"天命传说","ID":"fa393df1b94842afacd0df2c7102f53e",
     * "downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/fa/fa393df1b94842afacd0df2c7102f53e/com.zhankaigame.destiny
     * .huawei.1705030127.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=interveneList&listId=37&position=9&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA
     * &extendStr=58e8dece9aa8d5c050831440f15ae807%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807136
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/fa393df1b94842afacd0df2c7102f53e.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"408.4M","intro":"408.4M",
     * "size":428276667,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/f52f5e8e6f9b4d708ab6f9fcc49bd5b9_71.png?listId=37"],
     * "price":"0","exIcon":null,"listId":"37","stars":"5.0","videoFlag":0,
     * "md5":"09459A316B3864C0ACDADFA50378FB9D","comefrom":null,
     * "detailId":"app|C10720493__interveneList____37__9__58e8dece9aa8d5c050831440f15ae807%3B"},
     * {"appVersionName":"3.8.2","dependentedApps":[],"memo":"厉害了这捕鱼！赢话费，一炮中大奖","operaType":null,
     * "score":"3.7","package":"com.szyp.goldfish6s2.huawei","versionCode":"163",
     * "appid":"C10842771","exIcons":{},"downCountDesc":"15万次安装","btnDisable":0,"name":"开心捕鱼千炮版",
     * "ID":"3102f32b3fba4bd785bd8a1e376b5474","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/31/3102f32b3fba4bd785bd8a1e376b5474/com.szyp
     * .goldfish6s2.huawei.1704181653.apk?sign=c9d81011e610010520007000
     * @7ED2A239A80FA54D27BEFAACCC1AB7F3&cno=4010001&source=interveneList&listId=37&position=10
     * &hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=58e8dece9aa8d5c050831440f15ae807
     * %3BserviceType%3A0%3Bisshake%3A0%3Bs%3A1495097561980%3Btrace
     * %3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807136&encryptType=1",
     * "icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/3102f32b3fba4bd785bd8a1e376b5474.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"63.8M","intro":"63.8M",
     * "size":66897442,"describeType":"2","price":"0","exIcon":null,"listId":"37","stars":"4.0",
     * "videoFlag":0,"md5":"EC5591AE57E75E48BA12734DAE468921","comefrom":null,
     * "detailId":"app|C10842771__interveneList____37__10__58e8dece9aa8d5c050831440f15ae807%3B"},
     * {"appVersionName":"3.0.2","dependentedApps":[],"memo":"热血民族3D空战手游大作《血战长空》",
     * "operaType":null,"score":"4.7","package":"cn.yx3x.xzck.huawei","versionCode":"302",
     * "appid":"C10711933","exIcons":{},"downCountDesc":"2万次安装","btnDisable":0,
     * "name":"血战长空-大国崛起","ID":"042d647154d6457491928f48cbcd472d","downurl":"http://appdlc
     * .hicloud.com/dl/appdl/application/apk/04/042d647154d6457491928f48cbcd472d/cn.yx3x.xzck
     * .huawei.1705091157.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=interveneList&listId=37&position=11&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA
     * &extendStr=58e8dece9aa8d5c050831440f15ae807%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807136
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/042d647154d6457491928f48cbcd472d_1.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"170.6M","intro":"170.6M",
     * "size":178843673,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/f52f5e8e6f9b4d708ab6f9fcc49bd5b9_71.png?listId=37"],
     * "price":"0","exIcon":null,"listId":"37","stars":"5.0","videoFlag":0,
     * "md5":"D57C44BDF9DF0F59592519A8D885226E","comefrom":null,
     * "detailId":"app|C10711933__interveneList____37__11__58e8dece9aa8d5c050831440f15ae807%3B"},
     * {"appVersionName":"1.0.4","dependentedApps":[],"memo":"铠甲勇士正版续作 热血战斗一触即发",
     * "operaType":null,"score":"3.7","package":"com.joym.armorbloodwar.huawei",
     * "versionCode":"6","appid":"C10882474","exIcons":{},"downCountDesc":"85万次安装",
     * "btnDisable":0,"name":"铠甲勇士热血战神","ID":"4bb1f61df6774010b2a0eba78dbde941",
     * "downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/4b/4bb1f61df6774010b2a0eba78dbde941/com.joym.armorbloodwar
     * .huawei.1705091127.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=interveneList&listId=37&position=12&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA
     * &extendStr=58e8dece9aa8d5c050831440f15ae807%3BserviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807136
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/4bb1f61df6774010b2a0eba78dbde941.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"76.8M","intro":"76.8M",
     * "size":80535487,"describeType":"2","price":"0","exIcon":null,"listId":"37","stars":"4.0",
     * "videoFlag":0,"md5":"DF39CB5ECDE9962BD2036ABB7272B148","comefrom":null,
     * "detailId":"app|C10882474__interveneList____37__12__58e8dece9aa8d5c050831440f15ae807%3B
     * "}],"detailId":"e102e2833ed544d7981265b3c742cab2"}],"dataList-type":3,"listId":"",
     * "isUpdatableFilter":0,"name":"精品新游","layoutName":"horizonhomecard","layoutId":807136,
     * "isInstalledFilter":1,"detailId":"e102e2833ed544d7981265b3c742cab2"},
     * {"dataList":[{"icon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/8f57ef18d7044f60b1b48c25f74a8bc3/1486609524763_80x160.png
     * ","trace":"1cb9d75a1107447d87d331218b025093;__entrance10;
     * __8f57ef18d7044f60b1b48c25f74a8bc3","landscapeIcon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/8f57ef18d7044f60b1b48c25f74a8bc3
     * /1486609524771_900x240.png","commendIcon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/8f57ef18d7044f60b1b48c25f74a8bc3/1486609524763_80x160.png
     * ","detailId":"gamecenter||1495097563302"},{"icon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/19d93e53ddba4d86894e2401830cec84/1494387687018_80x160.png
     * ","trace":"1cb9d75a1107447d87d331218b025093;__entrance10;
     * __19d93e53ddba4d86894e2401830cec84","name":"儿童应用精选","landscapeIcon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/19d93e53ddba4d86894e2401830cec84
     * /1494387687025_900x240.png","commendIcon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/19d93e53ddba4d86894e2401830cec84/1494387687018_80x160.png
     * ","detailId":"waplinkdetail|564a0693517944e1971997377f9a5f57__entrance|1495097563302"}],
     * "dataList-type":2,"listId":"1","isUpdatableFilter":0,"layoutName":"newentrancecard",
     * "layoutId":7101,"isInstalledFilter":0},
     * {"dataList":[{"trace":"1cb9d75a1107447d87d331218b025093","name":"大家都在用",
     * "list":[{"appVersionName":"7.5.1.3270","dependentedApps":[],"memo":"围观择天记，豪礼不停袭",
     * "operaType":null,"score":"3.2","package":"com.tencent.mtt","versionCode":"7513270",
     * "appid":"C20679","exIcons":{},"downCountDesc":"8.5亿次安装","btnDisable":0,"name":"QQ浏览器",
     * "ID":"b4e04fb605a540a1ad74028fc2f6825b","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/b4/b4e04fb605a540a1ad74028fc2f6825b/com.tencent
     * .mtt.1705181442.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=8b1cd991a1ca42d48edf91769b98e373&listId=waplink
     * &position=1&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A806978
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/b4e04fb605a540a1ad74028fc2f6825b.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"25.4M","intro":"25.4M",
     * "size":26605126,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"3.5","videoFlag":0,"md5":"B1D4CF6F6F6117BFBB2FD6E15870EB3D","comefrom":null,
     * "detailId":"app|C20679__autoList_null__8b1cd991a1ca42d48edf91769b98e373__waplink__1__"},
     * {"appVersionName":"5.3.60","dependentedApps":[],"memo":"国内领先的专业新闻平台","operaType":null,
     * "score":"3.7","package":"com.tencent.news","versionCode":"5360","appid":"C104688",
     * "exIcons":{},"downCountDesc":"4.7亿次安装","btnDisable":0,"name":"腾讯新闻",
     * "ID":"9c01d39de73440b6b418773d6fe444c7","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/9c/9c01d39de73440b6b418773d6fe444c7/com.tencent
     * .news.1705181507.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=8b1cd991a1ca42d48edf91769b98e373&listId=waplink
     * &position=2&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A806978
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/9c01d39de73440b6b418773d6fe444c7.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"25.8M","intro":"25.8M",
     * "size":27089478,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"4.0","videoFlag":0,"md5":"DCB49CA05DC056211ADCE97DA32B3956","comefrom":null,
     * "detailId":"app|C104688__autoList_null__8b1cd991a1ca42d48edf91769b98e373__waplink__2__"},
     * {"appVersionName":"8.5","dependentedApps":[],"memo":"个性化新闻阅读的中文搜索应用","operaType":null,
     * "score":"3.4","package":"com.baidu.searchbox","versionCode":"25494656","appid":"C31346",
     * "exIcons":{},"downCountDesc":"6.4亿次安装","btnDisable":0,"name":"手机百度",
     * "ID":"9f8619fb09a74ac584ab8ff6d7b252c8","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/9f/9f8619fb09a74ac584ab8ff6d7b252c8/com.baidu
     * .searchbox.1705021657.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3
     * &cno=4010001&source=autoList_null&subsource=8b1cd991a1ca42d48edf91769b98e373&listId
     * =waplink&position=3&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0
     * %3Bisshake%3A0%3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId
     * %3A806978&encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/9f8619fb09a74ac584ab8ff6d7b252c8.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"43M","intro":"43M",
     * "size":45091194,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"3.5","videoFlag":0,"md5":"94E6BC06F13A83EF195C3AD36A6862C5","comefrom":null,
     * "detailId":"app|C31346__autoList_null__8b1cd991a1ca42d48edf91769b98e373__waplink__3__"},
     * {"appVersionName":"5.6.2.12104","dependentedApps":[],"memo":"欢乐颂2、龙珠传奇全网首播",
     * "operaType":null,"score":"3.6","package":"com.tencent.qqlive","versionCode":"12104",
     * "appid":"C29156","exIcons":{},"downCountDesc":"8.9亿次安装","btnDisable":0,"name":"腾讯视频",
     * "ID":"42fbb9dbd9e74b38bd05752336c0a88a","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/42/42fbb9dbd9e74b38bd05752336c0a88a/com.tencent
     * .qqlive.1705181048.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=8b1cd991a1ca42d48edf91769b98e373&listId=waplink
     * &position=4&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A806978
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/42fbb9dbd9e74b38bd05752336c0a88a.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"31.6M","intro":"31.6M",
     * "size":33153034,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"4.0","videoFlag":0,"md5":"95B7D9AF52A18AE9A706E187CF4FBDF8","comefrom":null,
     * "detailId":"app|C29156__autoList_null__8b1cd991a1ca42d48edf91769b98e373__waplink__4__"},
     * {"appVersionName":"4.1.98","dependentedApps":[],"memo":"随时随地免费安全连WiFi","operaType":null,
     * "score":"3.6","package":"com.snda.wifilocating","versionCode":"3118","appid":"C36902",
     * "exIcons":{},"downCountDesc":"16.9亿次安装","btnDisable":0,"name":"WiFi万能钥匙",
     * "ID":"138a4986dd9d4ee2b609d0399ce74477","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/13/138a4986dd9d4ee2b609d0399ce74477/com.snda
     * .wifilocating.1705121947.apk?sign=c9d81011e610010520007000
     * @7ED2A239A80FA54D27BEFAACCC1AB7F3&cno=4010001&source=autoList_null&subsource
     * =8b1cd991a1ca42d48edf91769b98e373&listId=waplink&position=5&hcrId
     * =0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A806978
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/138a4986dd9d4ee2b609d0399ce74477.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"7.8M","intro":"7.8M",
     * "size":8227781,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"4.0","videoFlag":0,"md5":"533E9B777D71245D75B390ABEEB56403","comefrom":null,
     * "detailId":"app|C36902__autoList_null__8b1cd991a1ca42d48edf91769b98e373__waplink__5__"},
     * {"appVersionName":"8.8.0","dependentedApps":[],"memo":"超过4亿人使用的全能音乐播放器","operaType":null,
     * "score":"3.8","package":"com.kugou.android","versionCode":"8800","appid":"C3466",
     * "exIcons":{},"downCountDesc":"7.8亿次安装","btnDisable":0,"name":"酷狗音乐",
     * "ID":"13fd5defbf774b788b615704a459f81c","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/13/13fd5defbf774b788b615704a459f81c/com.kugou
     * .android.1705172027.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=8b1cd991a1ca42d48edf91769b98e373&listId=waplink
     * &position=6&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A806978
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/13fd5defbf774b788b615704a459f81c.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"41.3M","intro":"41.3M",
     * "size":43349696,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/3ddd7bd56e7b4d688aa5d5cff68bbefb_71.png?listId=waplink"],
     * "price":"0","exIcon":null,"listId":"waplink","stars":"4.0","videoFlag":0,
     * "md5":"44D42347DD1683F9B0D445C626FCEA70","comefrom":null,
     * "detailId":"app|C3466__autoList_null__8b1cd991a1ca42d48edf91769b98e373__waplink__6__"},
     * {"appVersionName":"7.5.0","dependentedApps":[],"memo":"看视频直播网红明星上微博","operaType":null,
     * "score":"4.7","package":"com.sina.weibo","versionCode":"3371","appid":"C7166",
     * "exIcons":{},"downCountDesc":"13.2亿次安装","btnDisable":0,"name":"微博",
     * "ID":"522110c8bbf745b0afb6768c59291b3c","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/52/522110c8bbf745b0afb6768c59291b3c/com.sina
     * .weibo.1705121527.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=8b1cd991a1ca42d48edf91769b98e373&listId=waplink
     * &position=7&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A806978
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/522110c8bbf745b0afb6768c59291b3c.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"60.7M","intro":"60.7M",
     * "size":63665004,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"5.0","videoFlag":0,"md5":"F5D2A866B8961BE7E30A992DFDA9E973","comefrom":null,
     * "detailId":"app|C7166__autoList_null__8b1cd991a1ca42d48edf91769b98e373__waplink__7__"},
     * {"appVersionName":"3.1.0","dependentedApps":[],"memo":"腾讯出品兴趣阅读APP","operaType":null,
     * "score":"3.6","package":"com.tencent.reading","versionCode":"3100","appid":"C10295678",
     * "exIcons":{},"downCountDesc":"1.1亿次安装","btnDisable":0,"name":"天天快报",
     * "ID":"29256530fa004d6cb92b88f8d3056f66","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/29/29256530fa004d6cb92b88f8d3056f66/com.tencent
     * .reading.1705111048.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=8b1cd991a1ca42d48edf91769b98e373&listId=waplink
     * &position=8&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A806978
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/29256530fa004d6cb92b88f8d3056f66.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"18.7M","intro":"18.7M",
     * "size":19660128,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"4.0","videoFlag":0,"md5":"B9FC9E3A7E99B482BF3AE7A6D9FC9989","comefrom":null,
     * "detailId":"app|C10295678__autoList_null__8b1cd991a1ca42d48edf91769b98e373__waplink__8__
     * "},{"appVersionName":"7.3.1.2","dependentedApps":[],"memo":"千万正版曲库，告别缺歌烦恼",
     * "operaType":null,"score":"3.3","package":"com.tencent.qqmusic","versionCode":"621",
     * "appid":"C10220136","exIcons":{},"downCountDesc":"4.6亿次安装","btnDisable":0,"name":"QQ音乐",
     * "ID":"ca2074a7834f4c80bea8e62e33084cf2","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/ca/ca2074a7834f4c80bea8e62e33084cf2/com.tencent
     * .qqmusic.1704251117.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=8b1cd991a1ca42d48edf91769b98e373&listId=waplink
     * &position=9&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A806978
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/ca2074a7834f4c80bea8e62e33084cf2.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"25M","intro":"25M",
     * "size":26233533,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"3.5","videoFlag":0,"md5":"896E7A2DEB4012DC0658971A40DDB9DA","comefrom":null,
     * "detailId":"app|C10220136__autoList_null__8b1cd991a1ca42d48edf91769b98e373__waplink__9__
     * "},{"appVersionName":"3.9.5.277","dependentedApps":[],"memo":"全民K歌，你其实很会唱歌！",
     * "operaType":null,"score":"4.0","package":"com.tencent.karaoke","versionCode":"93",
     * "appid":"C10183952","exIcons":{},"downCountDesc":"1.6亿次安装","btnDisable":0,"name":"全民K歌",
     * "ID":"b40f08eb6c6348d292acf576dc1fd955","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/b4/b40f08eb6c6348d292acf576dc1fd955/com.tencent
     * .karaoke.1705171027.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=8b1cd991a1ca42d48edf91769b98e373&listId=waplink
     * &position=10&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake
     * %3A0%3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A806978
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/b40f08eb6c6348d292acf576dc1fd955.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"26.2M","intro":"26.2M",
     * "size":27445370,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"4.0","videoFlag":0,"md5":"68F18F7E4DDF050099426C35F67A66CB","comefrom":null,
     * "detailId":"app|C10183952__autoList_null__8b1cd991a1ca42d48edf91769b98e373__waplink__10__
     * "},{"appVersionName":"6.6.2","dependentedApps":[],"memo":"择天记、花儿与少年3热播","operaType":null,
     * "score":"4.6","package":"com.youku.phone","versionCode":"124","appid":"C32569",
     * "exIcons":{},"downCountDesc":"7.3亿次安装","btnDisable":0,"name":"优酷",
     * "ID":"98d8cf9b92f944f5bc8875a742a15234","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/98/98d8cf9b92f944f5bc8875a742a15234/com.youku
     * .phone.1705111647.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=8b1cd991a1ca42d48edf91769b98e373&listId=waplink
     * &position=11&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake
     * %3A0%3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A806978
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/98d8cf9b92f944f5bc8875a742a15234.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"66.6M","intro":"66.6M",
     * "size":69813249,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"5.0","videoFlag":0,"md5":"674731E689A2FE0D88356074596FC472","comefrom":null,
     * "detailId":"app|C32569__autoList_null__8b1cd991a1ca42d48edf91769b98e373__waplink__11__"},
     * {"appVersionName":"4.56.2.4348","dependentedApps":[],"memo":"好玩接地气的7秒短视频社区！",
     * "operaType":null,"score":"4.8","package":"com.smile.gifmaker","versionCode":"4348",
     * "appid":"C33455","exIcons":{},"downCountDesc":"2.9亿次安装","btnDisable":0,"name":"快手",
     * "ID":"e4b47ee1a05540c4baa86e51daa20c79","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/e4/e4b47ee1a05540c4baa86e51daa20c79/com.smile
     * .gifmaker.1705171002.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3
     * &cno=4010001&source=autoList_null&subsource=8b1cd991a1ca42d48edf91769b98e373&listId
     * =waplink&position=12&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0
     * %3Bisshake%3A0%3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId
     * %3A806978&encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/e4b47ee1a05540c4baa86e51daa20c79.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"28.4M","intro":"28.4M",
     * "size":29822129,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"5.0","videoFlag":0,"md5":"11AB84A9F9E253A6FEB5C1333E0386AA","comefrom":null,
     * "detailId":"app|C33455__autoList_null__8b1cd991a1ca42d48edf91769b98e373__waplink__12__"}],
     * "detailId":"35486b5c35444ae8b48116ce49ba1603"}],"dataList-type":3,"listId":"",
     * "isUpdatableFilter":1,"name":"大家都在用","layoutName":"horizonhomecard","layoutId":806978,
     * "isInstalledFilter":1,"detailId":"35486b5c35444ae8b48116ce49ba1603"},
     * {"dataList":[{"trace":"1cb9d75a1107447d87d331218b025093","name":"这些最好玩",
     * "list":[{"appVersionName":"3.28.2","dependentedApps":[],"memo":"中世纪魔幻世界，新时代动作经典",
     * "operaType":null,"score":"2.9","package":"com.m37.yhjyj.HUAWEI","versionCode":"10328010",
     * "appid":"C10535832","exIcons":{},"downCountDesc":"769万次安装","btnDisable":0,
     * "name":"永恒纪元（抽荣耀8）","ID":"3b47826d80d3488d930c587573e14b3c","downurl":"http://appdlc
     * .hicloud.com/dl/appdl/application/apk/3b/3b47826d80d3488d930c587573e14b3c/com.m37.yhjyj
     * .HUAWEI.1704261237.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=324b3a9194bd4c498b89a1b989b15599&listId=adwaplink
     * &position=1&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807240
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/3b47826d80d3488d930c587573e14b3c.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"154.5M","intro":"154.5M",
     * "size":162032620,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/f52f5e8e6f9b4d708ab6f9fcc49bd5b9_71.png?listId=adwaplink"],
     * "price":"0","exIcon":null,"listId":"adwaplink","stars":"3.0","videoFlag":0,
     * "md5":"D385539DB5EBA195F6E80D3BFF75DA44","comefrom":null,
     * "detailId":"app|C10535832__autoList_null__324b3a9194bd4c498b89a1b989b15599__adwaplink__1__
     * "},{"appVersionName":"1.44","dependentedApps":[],"memo":"星星瓶新障碍来袭","operaType":null,
     * "score":"3.7","package":"com.happyelements.AndroidAnimal","versionCode":"44",
     * "appid":"C10134672","exIcons":{},"downCountDesc":"3.3亿次安装","btnDisable":0,"name":"开心消消乐?",
     * "ID":"326eb0f003e6442780ac16a61ad79c22","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/32/326eb0f003e6442780ac16a61ad79c22/com.happyelements
     * .AndroidAnimal.1705171807.apk?sign=c9d81011e610010520007000
     * @7ED2A239A80FA54D27BEFAACCC1AB7F3&cno=4010001&source=autoList_null&subsource
     * =324b3a9194bd4c498b89a1b989b15599&listId=adwaplink&position=2&hcrId
     * =0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807240
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/326eb0f003e6442780ac16a61ad79c22.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"87.9M","intro":"87.9M",
     * "size":92177559,"describeType":"2","price":"0","exIcon":null,"listId":"adwaplink",
     * "stars":"4.0","videoFlag":0,"md5":"155317E71BBA78F184F70D37766F7B2F","comefrom":null,
     * "detailId":"app|C10134672__autoList_null__324b3a9194bd4c498b89a1b989b15599__adwaplink__2__
     * "},{"appVersionName":"1.124.0","dependentedApps":[],"memo":"13年经典沉淀 回合制公平手游",
     * "operaType":null,"score":"3.3","package":"com.netease.my.huawei","versionCode":"11240",
     * "appid":"C10242764","exIcons":{},"downCountDesc":"1981万次安装","btnDisable":0,"name":"梦幻西游",
     * "ID":"baa909d16d234d019b53feb117e4bf82","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/ba/baa909d16d234d019b53feb117e4bf82/com.netease.my
     * .huawei.1704061537.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=324b3a9194bd4c498b89a1b989b15599&listId=adwaplink
     * &position=3&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807240
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/baa909d16d234d019b53feb117e4bf82.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"497.8M","intro":"497.8M",
     * "size":522019263,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/f52f5e8e6f9b4d708ab6f9fcc49bd5b9_71.png?listId=adwaplink"],
     * "price":"0","exIcon":null,"listId":"adwaplink","stars":"3.5","videoFlag":0,
     * "md5":"B20350E04226B6AF19DF45251173E3BC","comefrom":null,
     * "detailId":"app|C10242764__autoList_null__324b3a9194bd4c498b89a1b989b15599__adwaplink__3__
     * "},{"appVersionName":"3.71","dependentedApps":[],"memo":"吴宗宪鼎力推荐，玩捕鱼就来波克捕鱼！",
     * "operaType":null,"score":"3.2","package":"com.pokercity.bydrqp.huawei",
     * "versionCode":"171","appid":"C10281112","exIcons":{},"downCountDesc":"1911万次安装",
     * "btnDisable":0,"name":"波克捕鱼","ID":"d0bc75c5d746424998ceb6cb8016f692",
     * "downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/d0/d0bc75c5d746424998ceb6cb8016f692/com.pokercity.bydrqp
     * .huawei.1705081833.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=324b3a9194bd4c498b89a1b989b15599&listId=adwaplink
     * &position=4&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807240
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/d0bc75c5d746424998ceb6cb8016f692_1.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"28.5M","intro":"28.5M",
     * "size":29922515,"describeType":"2","price":"0","exIcon":null,"listId":"adwaplink",
     * "stars":"3.5","videoFlag":0,"md5":"61627AC757F2EDEF28995A8444E5B20B","comefrom":null,
     * "detailId":"app|C10281112__autoList_null__324b3a9194bd4c498b89a1b989b15599__adwaplink__4__
     * "},{"appVersionName":"1.2.0","dependentedApps":[],"memo":"微微一笑倾城","operaType":null,
     * "score":"3.3","package":"com.netease.l10.huawei","versionCode":"20","appid":"C10395198",
     * "exIcons":{},"downCountDesc":"1918万次安装","btnDisable":0,"name":"倩女幽魂",
     * "ID":"b5dcc0c5c89e428bbc5f6985c2bc8f3b","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/b5/b5dcc0c5c89e428bbc5f6985c2bc8f3b/com.netease
     * .l10.huawei.1705041653.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3
     * &cno=4010001&source=autoList_null&subsource=324b3a9194bd4c498b89a1b989b15599&listId
     * =adwaplink&position=5&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0
     * %3Bisshake%3A0%3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId
     * %3A807240&encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/b5dcc0c5c89e428bbc5f6985c2bc8f3b.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"593.8M","intro":"593.8M",
     * "size":622608323,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/f52f5e8e6f9b4d708ab6f9fcc49bd5b9_71.png?listId=adwaplink"],
     * "price":"0","exIcon":null,"listId":"adwaplink","stars":"3.5","videoFlag":0,
     * "md5":"94FA8C58C50874944C087218D752BF09","comefrom":null,
     * "detailId":"app|C10395198__autoList_null__324b3a9194bd4c498b89a1b989b15599__adwaplink__5__
     * "},{"appVersionName":"4.2.0","dependentedApps":[],"memo":"赵丽颖代言","operaType":null,
     * "score":"4.2","package":"com.mfp.jelly.huawei","versionCode":"81","appid":"C10189589",
     * "exIcons":{},"downCountDesc":"4491万次安装","btnDisable":0,"name":"宾果消消消（赵丽颖代言）",
     * "ID":"c14310800ad940f2a77f9a484121cec1","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/c1/c14310800ad940f2a77f9a484121cec1/com.mfp.jelly
     * .huawei.1705151122.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=324b3a9194bd4c498b89a1b989b15599&listId=adwaplink
     * &position=6&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807240
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/c14310800ad940f2a77f9a484121cec1.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"83.1M","intro":"83.1M",
     * "size":87134283,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/f52f5e8e6f9b4d708ab6f9fcc49bd5b9_71.png?listId=adwaplink"],
     * "price":"0","exIcon":null,"listId":"adwaplink","stars":"4.5","videoFlag":0,
     * "md5":"2BCC98E3740A39A96AEF0AD822F543F9","comefrom":null,
     * "detailId":"app|C10189589__autoList_null__324b3a9194bd4c498b89a1b989b15599__adwaplink__6__
     * "},{"appVersionName":"1.1.86","dependentedApps":[],"memo":"回合制手游鼻祖 回归月卡公平模式",
     * "operaType":null,"score":"3.0","package":"com.netease.dhxy.huawei","versionCode":"20674",
     * "appid":"C10315554","exIcons":{},"downCountDesc":"660万次安装","btnDisable":0,"name":"大话西游",
     * "ID":"8ea5564bb6ab47b8959d0693b42b1aaa","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/8e/8ea5564bb6ab47b8959d0693b42b1aaa/com.netease.dhxy
     * .huawei.1705121616.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=324b3a9194bd4c498b89a1b989b15599&listId=adwaplink
     * &position=7&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807240
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/8ea5564bb6ab47b8959d0693b42b1aaa.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"619.1M","intro":"619.1M",
     * "size":649161410,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/f52f5e8e6f9b4d708ab6f9fcc49bd5b9_71.png?listId=adwaplink"],
     * "price":"0","exIcon":null,"listId":"adwaplink","stars":"3.5","videoFlag":0,
     * "md5":"62A78EE271D7F4899D0D2BAAA5045411","comefrom":null,
     * "detailId":"app|C10315554__autoList_null__324b3a9194bd4c498b89a1b989b15599__adwaplink__7__
     * "},{"appVersionName":"2.0.1","dependentedApps":[],"memo":"重返侏罗纪，春季植物，耀眼登场！",
     * "operaType":null,"score":"3.7","package":"com.popcap.pvz2cthdhwct","versionCode":"800",
     * "appid":"C10076817","exIcons":{},"downCountDesc":"5835万次安装","btnDisable":0,
     * "name":"植物大战僵尸2高清版","ID":"77bef6292c874f9bb3b8716202065245","downurl":"http://appdlc
     * .hicloud.com/dl/appdl/application/apk/77/77bef6292c874f9bb3b8716202065245/com.popcap
     * .pvz2cthdhwct.1704241617.apk?sign=c9d81011e610010520007000
     * @7ED2A239A80FA54D27BEFAACCC1AB7F3&cno=4010001&source=autoList_null&subsource
     * =324b3a9194bd4c498b89a1b989b15599&listId=adwaplink&position=8&hcrId
     * =0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807240
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/77bef6292c874f9bb3b8716202065245.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"283.5M","intro":"283.5M",
     * "size":297262852,"describeType":"2","price":"0","exIcon":null,"listId":"adwaplink",
     * "stars":"4.0","videoFlag":0,"md5":"9BC7E0A219655B8B25E9317A7124A29C","comefrom":null,
     * "detailId":"app|C10076817__autoList_null__324b3a9194bd4c498b89a1b989b15599__adwaplink__8__
     * "},{"appVersionName":"1.0.20","dependentedApps":[],"memo":"极致日漫、情怀声优、精良之作",
     * "operaType":null,"score":"3.0","package":"com.netease.onmyoji.huawei","versionCode":"20",
     * "appid":"C10531189","exIcons":{},"downCountDesc":"1510万次安装","btnDisable":0,"name":"阴阳师",
     * "ID":"66fba9b5751043de815ea9a627eb5e3d","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/66/66fba9b5751043de815ea9a627eb5e3d/com.netease.onmyoji
     * .huawei.1705181141.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=324b3a9194bd4c498b89a1b989b15599&listId=adwaplink
     * &position=9&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807240
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/66fba9b5751043de815ea9a627eb5e3d.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"914.1M","intro":"914.1M",
     * "size":958532636,"describeType":"2","price":"0","exIcon":null,"listId":"adwaplink",
     * "stars":"3.0","videoFlag":0,"md5":"33B315296DEFC4BCF7BFB80954152B64","comefrom":null,
     * "detailId":"app|C10531189__autoList_null__324b3a9194bd4c498b89a1b989b15599__adwaplink__9__
     * "},{"appVersionName":"0.7.170509001.872","dependentedApps":[],"memo":"以假乱真的城市景观，由你一手打造！",
     * "operaType":null,"score":"3.1","package":"com.ea.simcitymobile.huawei",
     * "versionCode":"170516001","appid":"C10738963","exIcons":{},"downCountDesc":"393万次安装",
     * "btnDisable":0,"name":"模拟城市:我是市长","ID":"2ac097e36ab34b4d94a43aa613324ff1",
     * "downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/2a/2ac097e36ab34b4d94a43aa613324ff1/com.ea.simcitymobile
     * .huawei.1705161307.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=324b3a9194bd4c498b89a1b989b15599&listId=adwaplink
     * &position=10&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake
     * %3A0%3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807240
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/2ac097e36ab34b4d94a43aa613324ff1.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"94.3M","intro":"94.3M",
     * "size":98868955,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/f52f5e8e6f9b4d708ab6f9fcc49bd5b9_71.png?listId=adwaplink"],
     * "price":"0","exIcon":null,"listId":"adwaplink","stars":"3.5","videoFlag":0,
     * "md5":"8FA949F3BEF543C917F496012B2408E5","comefrom":null,
     * "detailId":"app
     * |C10738963__autoList_null__324b3a9194bd4c498b89a1b989b15599__adwaplink__10__"},
     * {"appVersionName":"20.0","dependentedApps":[],"memo":"十年经典 青春、明朗、快乐的江湖","operaType":null,
     * "score":"3.8","package":"com.longtugame.rxjh.huawei","activityInfoCues":"",
     * "versionCode":"20","appid":"C10669635","exIcons":{},
     * "activityId":"1bcfd2bf21ff4e1c81b392b55987fede","downCountDesc":"107万次安装","prizeState":1,
     * "btnDisable":0,"name":"热血江湖 ","ID":"b794ca0571cc431b952855582e65b790",
     * "downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/b7/b794ca0571cc431b952855582e65b790/com.longtugame.rxjh
     * .huawei.1705171109.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=324b3a9194bd4c498b89a1b989b15599&listId=adwaplink
     * &position=11&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake
     * %3A0%3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807240
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/b794ca0571cc431b952855582e65b790.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"482.5M","intro":"482.5M",
     * "size":505908684,"describeType":"2","labelUrl":["http://appimg.hicloud
     * .com/hwmarket/files/appLabel/f52f5e8e6f9b4d708ab6f9fcc49bd5b9_71.png?listId=adwaplink"],
     * "price":"0","exIcon":null,"listId":"adwaplink","videoFlag":0,"stars":"4.0",
     * "md5":"B3F9A0D773F01A32584CFEAEB9F95154","isPrize":1,"activityName":"下载《热血江湖》，赢P10手机",
     * "comefrom":null,
     * "detailId":"app
     * |C10669635__autoList_null__324b3a9194bd4c498b89a1b989b15599__adwaplink__11__"},
     * {"appVersionName":"3.9.0.0","dependentedApps":[],"memo":"官方推荐！赢取海量奖品！","operaType":null,
     * "score":"3.8","package":"com.bohaoo.fish2.huawei","versionCode":"3900",
     * "appid":"C10483346","exIcons":{},"downCountDesc":"217万次安装","btnDisable":0,"name":"捕鱼传奇",
     * "ID":"0862069caeef4026a1adc9aa0c20d26e","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/08/0862069caeef4026a1adc9aa0c20d26e/com.bohaoo
     * .fish2.huawei.1704211607.apk?sign=c9d81011e610010520007000
     * @7ED2A239A80FA54D27BEFAACCC1AB7F3&cno=4010001&source=autoList_null&subsource
     * =324b3a9194bd4c498b89a1b989b15599&listId=adwaplink&position=12&hcrId
     * =0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A807240
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/0862069caeef4026a1adc9aa0c20d26e.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"53.6M","intro":"53.6M",
     * "size":56248308,"describeType":"2","price":"0","exIcon":null,"listId":"adwaplink",
     * "stars":"4.0","videoFlag":0,"md5":"97711A3D34792DDFD24A0E4BC9DFC839","comefrom":null,
     * "detailId":"app
     * |C10483346__autoList_null__324b3a9194bd4c498b89a1b989b15599__adwaplink__12__"}],
     * "detailId":"e96e3443aeb6408b911a7a1712261c63"}],"dataList-type":3,"listId":"",
     * "isUpdatableFilter":1,"name":"这些最好玩","layoutName":"horizonhomecard","layoutId":807240,
     * "isInstalledFilter":1,"detailId":"e96e3443aeb6408b911a7a1712261c63"},
     * {"dataList":[{"icon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/63001972ae184ce6b89baf724bbbc4ba/1494552795334_80x160.png
     * ","trace":"1cb9d75a1107447d87d331218b025093;__entrance10;
     * __63001972ae184ce6b89baf724bbbc4ba","name":"人气应用","landscapeIcon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/63001972ae184ce6b89baf724bbbc4ba
     * /1494552795341_900x240.png","commendIcon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/63001972ae184ce6b89baf724bbbc4ba/1494552795334_80x160.png
     * ","detailId":"c42a6fe782cd479d9502c2323a37259b|1495097563474"},{"icon":"http://appimg
     * .hicloud.com/hwmarket/files/entranceIcon/b472c7bd4953483cbd46b1b8a38318ed
     * /1486609467323_80x160.png","trace":"1cb9d75a1107447d87d331218b025093;__entrance10;
     * __b472c7bd4953483cbd46b1b8a38318ed","name":"华为专辑","landscapeIcon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/b472c7bd4953483cbd46b1b8a38318ed
     * /1486609467331_900x240.png","commendIcon":"http://appimg.hicloud
     * .com/hwmarket/files/entranceIcon/b472c7bd4953483cbd46b1b8a38318ed/1486609467323_80x160.png
     * ","detailId":"waplinkdetail|3dbecd0423854797b690e5ad5e0dda73__entrance|1495097563474"}],
     * "dataList-type":2,"listId":"1","isUpdatableFilter":0,"layoutName":"newentrancecard",
     * "layoutId":7102,"isInstalledFilter":0},
     * {"dataList":[{"trace":"1cb9d75a1107447d87d331218b025093","name":"新游预约",
     * "list":[{"icon":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10906893_1/5a7f871585f044a2aa8364da8b8d02a7.png",
     * "orderVersionCode":10,"trace":"1cb9d75a1107447d87d331218b025093","package":"com.yodo1.tew
     * .huawei","state":0,"appid":"C10906893","intro":"5月25日","size":0,"title":"变形金刚：地球之战（抽P10）",
     * "description":"5月25日 6.7万人已预约","name":"变形金刚：地球之战（抽P10）","original":1,
     * "backgroundImg":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10906893_1/876e2a7d6d3146a085528b7072e8a315.png",
     * "downurl":"","detailId":"html|https://a.vmall.com/order/app?appId=C10906893&pkgName=com
     * .yodo1.tew.huawei"},{"icon":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/c100006019_1/3d5a6763cbd4493a83a1e663735b205b.png",
     * "orderVersionCode":1001000,"trace":"1cb9d75a1107447d87d331218b025093","package":"com
     * .longtugame.wdwm.huawei","state":0,"appid":"c100006019","intro":"6月22日","size":0,
     * "title":"我的文明","description":"6月22日 2.1万人已预约","name":"我的文明","original":1,
     * "backgroundImg":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/c100006019_1/9cbda795b3ee4179adfdf2b46aa304a5.png",
     * "downurl":"","detailId":"html|https://a.vmall.com/order/app?appId=c100006019&pkgName=com
     * .longtugame.wdwm.huawei"},{"icon":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C100004571_2/34a47c5e558d432d9eff1588cb4a0467.png",
     * "orderVersionCode":130,"trace":"1cb9d75a1107447d87d331218b025093","package":"com
     * .minglegames.CalmingLia.huawei","state":0,"appid":"C100004571","intro":"6月28日","size":0,
     * "title":"梦境奇缘：莉亚","description":"6月28日 0.5万人已预约","name":"梦境奇缘：莉亚","original":1,
     * "backgroundImg":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C100004571_2/ce171fea7b2c4d36b29adce05fd8aebc.png",
     * "downurl":"","detailId":"html|https://a.vmall.com/order/app?appId=C100004571&pkgName=com
     * .minglegames.CalmingLia.huawei"},{"icon":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10829644_4/443c332fdb2b45aca6dc156cc83e4ccc.png",
     * "orderVersionCode":200,"trace":"1cb9d75a1107447d87d331218b025093","package":"com.kaopu
     * .dsgl.huawei","state":0,"appid":"C10829644","intro":"5月25日","size":0,
     * "title":"西游记之大圣归来（抽P10）","description":"5月25日 6.3万人已预约","name":"西游记之大圣归来（抽P10）",
     * "original":1,"backgroundImg":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10829644_4/aa208bd9cf1540c19a9c060e6f9763dd.png",
     * "downurl":"","detailId":"html|https://a.vmall.com/order/app?appId=C10829644&pkgName=com
     * .kaopu.dsgl.huawei"},{"icon":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10891572/f785c6cbd55e49e4973f25cdbb5bf965.png",
     * "orderVersionCode":21003,"trace":"1cb9d75a1107447d87d331218b025093","package":"com
     * .aladinfun.petsisland.huawei","state":0,"appid":"C10891572","intro":"5月24日","size":0,
     * "title":"猪来了","description":"5月24日 0.3万人已预约","name":"猪来了","original":1,
     * "backgroundImg":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10891572/27b7f45d7e8e47fd8f12f53f9a8f5674.png",
     * "downurl":"","detailId":"html|https://a.vmall.com/order/app?appId=C10891572&pkgName=com
     * .aladinfun.petsisland.huawei"},{"icon":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10819641_12/372ae64f958e4bbfbbe4bf58970cc6dc.png",
     * "orderVersionCode":5000,"trace":"1cb9d75a1107447d87d331218b025093","package":"com.yunchang
     * .pisces.huawei","state":0,"appid":"C10819641","intro":"6月1日","size":0,
     * "title":"桃花源记（抽P10）","description":"6月1日 3.1万人已预约","name":"桃花源记（抽P10）","original":1,
     * "backgroundImg":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10819641_12/8e57e93eb5b642258e73242d570f92a2.png",
     * "downurl":"","detailId":"html|https://a.vmall.com/order/app?appId=C10819641&pkgName=com
     * .yunchang.pisces.huawei"},{"icon":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10825396/299103155a1f4c69b26d9ab7acbe488b.png",
     * "orderVersionCode":5,"trace":"1cb9d75a1107447d87d331218b025093","package":"com.fl.qzgs
     * .huawei","state":0,"appid":"C10825396","intro":"6月8日","size":0,"title":"全职高手",
     * "description":"6月8日 3.5万人已预约","name":"全职高手","original":1,"backgroundImg":"http://appimg
     * .hicloud.com/hwmarket/files//orderApp/C10825396/413e2c4e844d461ab7962662086e06aa.png",
     * "downurl":"","detailId":"html|https://a.vmall.com/order/app?appId=C10825396&pkgName=com.fl
     * .qzgs.huawei"},{"icon":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10897820_14/89d2d104d46b495d80eb5a2444bd2fe6.png",
     * "orderVersionCode":600100,"trace":"1cb9d75a1107447d87d331218b025093","package":"com.hoolai
     * .hmzqfy.huawei","state":0,"appid":"C10897820","intro":"6月6日","size":0,
     * "title":"豪门足球风云（抽P10）","description":"6月6日 0.6万人已预约","name":"豪门足球风云（抽P10）","original":1,
     * "backgroundImg":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10897820_14/dd7280df164c470da5cfdaa33ccfa098.png",
     * "downurl":"","detailId":"html|https://a.vmall.com/order/app?appId=C10897820&pkgName=com
     * .hoolai.hmzqfy.huawei"},{"icon":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10676898_4/b2d618e73fb74742a1f565279c920c27.png",
     * "orderVersionCode":105,"trace":"1cb9d75a1107447d87d331218b025093","package":"com.joygames
     * .onepiecefighting.huawei","state":0,"appid":"C10676898","intro":"5月26日","size":0,
     * "title":"航海王激战（抽Mate9）","description":"5月26日 6.7万人已预约","name":"航海王激战（抽Mate9）",
     * "original":1,"backgroundImg":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10676898_4//imgFile/ffdf03f3-a4ce-48dd-9eac-6ae12d55b75f
     * .png","downurl":"","detailId":"html|https://a.vmall
     * .com/order/app?appId=C10676898&pkgName=com.joygames.onepiecefighting.huawei"},
     * {"icon":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10817598_6/dadc903107004ff5a263d713cb953f7f.png",
     * "orderVersionCode":20,"trace":"1cb9d75a1107447d87d331218b025093","package":"com.zlongame
     * .fengshen.huawei","state":0,"appid":"C10817598","intro":"6月中","size":0,
     * "title":"封神召唤师（抽Mate9）","description":"6月中 6.2万人已预约","name":"封神召唤师（抽Mate9）","original":1,
     * "backgroundImg":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10817598_6/483c225cfedc4b9899e7f3487d8d3984.png",
     * "downurl":"","detailId":"html|https://a.vmall.com/order/app?appId=C10817598&pkgName=com
     * .zlongame.fengshen.huawei"},{"icon":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10868408_24/d6d54fdafa5a4b899242f04e3342682f.png",
     * "orderVersionCode":200,"trace":"1cb9d75a1107447d87d331218b025093","package":"com.ztgame
     * .fir.huawei","state":0,"appid":"C10868408","intro":"6月中","size":0,"title":"征途2（预约抽P10）",
     * "description":"6月中 1.5万人已预约","name":"征途2（预约抽P10）","original":1,
     * "backgroundImg":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10868408_24/ebbaf4b874534ad7a65d3f731ff7ba01.png",
     * "downurl":"","detailId":"html|https://a.vmall.com/order/app?appId=C10868408&pkgName=com
     * .ztgame.fir.huawei"},{"icon":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10737293_9/25bef547ff30413bbebddb0a56a032dd.png",
     * "orderVersionCode":10,"trace":"1cb9d75a1107447d87d331218b025093","package":"com.snailgames
     * .pandatd.huawei","state":0,"appid":"C10737293","intro":"6月底","size":0,
     * "title":"太极熊猫3：猎龙（抽Mate 9）","description":"6月底 5.8万人已预约","name":"太极熊猫3：猎龙（抽Mate 9）",
     * "original":1,"backgroundImg":"http://appimg.hicloud
     * .com/hwmarket/files//orderApp/C10737293_9/21fa5d8164aa4f5180c28493b7e99300.png",
     * "downurl":"","detailId":"html|https://a.vmall.com/order/app?appId=C10737293&pkgName=com
     * .snailgames.pandatd.huawei"}],"detailId":"992cae9bdec84575b452ef7e820dfa1c"}],
     * "dataList-type":34,"listId":"","isUpdatableFilter":1,"name":"新游预约",
     * "layoutName":"horizonhomecard","layoutId":806875,"isInstalledFilter":1,
     * "detailId":"992cae9bdec84575b452ef7e820dfa1c"},
     * {"dataList":[{"trace":"1cb9d75a1107447d87d331218b025093","name":"精品尝鲜，每周五上新",
     * "list":[{"appVersionName":"1.1.0","dependentedApps":[],"memo":"小公主索菲亚与与白雪公主的奇妙冒险",
     * "operaType":null,"score":"3.7","package":"com.pf.wkmofashengyan.huawei",
     * "versionCode":"110","appid":"C10903802","exIcons":{},"downCountDesc":"少于1万次安装",
     * "btnDisable":0,"name":"魔法盛宴宝贝童话","ID":"ed0af6bda66f4fddb90843312dd95648",
     * "downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/ed/ed0af6bda66f4fddb90843312dd95648/com.pf.wkmofashengyan
     * .huawei.1705041120.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=52a8851f3a5747db84c1f74a787028ac&listId=waplink
     * &position=1&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A899070
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/ed0af6bda66f4fddb90843312dd95648.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"23M","intro":"23M",
     * "size":24141454,"describeType":"2","productId":"83A243D3625A47B5A3F97B949A9D7FFF",
     * "price":"1.00","exIcon":null,"listId":"waplink","stars":"4.0","videoFlag":0,
     * "md5":"312C71601667BABD0A93AA77A6B79AEF","comefrom":null,
     * "detailId":"app|C10903802__autoList_null__52a8851f3a5747db84c1f74a787028ac__waplink__1__
     * "},{"appVersionName":"1.4.6","dependentedApps":[],"memo":"让宝宝主动认字的应用","operaType":null,
     * "score":"3.9","package":"com.tinmanarts.JoJoSherlock","versionCode":"14",
     * "appid":"C10631081","exIcons":{},"downCountDesc":"106万次安装","btnDisable":0,"name":"叫叫识字",
     * "ID":"d2e6f735a0d44783a8ccf6d6848a8c27","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/d2/d2e6f735a0d44783a8ccf6d6848a8c27/com.tinmanarts
     * .JoJoSherlock.1705111707.apk?sign=c9d81011e610010520007000
     * @7ED2A239A80FA54D27BEFAACCC1AB7F3&cno=4010001&source=autoList_null&subsource
     * =52a8851f3a5747db84c1f74a787028ac&listId=waplink&position=2&hcrId
     * =0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A899070
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/d2e6f735a0d44783a8ccf6d6848a8c27.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"65.5M","intro":"65.5M",
     * "size":68667420,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"4.0","videoFlag":0,"md5":"EA3BC6D7ECAE9D0C6E086934BBC16D7C","comefrom":null,
     * "detailId":"app|C10631081__autoList_null__52a8851f3a5747db84c1f74a787028ac__waplink__2__
     * "},{"appVersionName":"1.1.0","dependentedApps":[],"memo":"小马宝莉系列经典故事","operaType":null,
     * "score":"5.0","package":"com.pf.wkrongyubaoweizhan.huawei","versionCode":"110",
     * "appid":"C10903817","exIcons":{},"downCountDesc":"少于1万次安装","btnDisable":0,
     * "name":"荣誉保卫战宝贝童话","ID":"4d066203458c44d3b05407b82c185500","downurl":"http://appdlc
     * .hicloud.com/dl/appdl/application/apk/4d/4d066203458c44d3b05407b82c185500/com.pf
     * .wkrongyubaoweizhan.huawei.1705041852.apk?sign=c9d81011e610010520007000
     * @7ED2A239A80FA54D27BEFAACCC1AB7F3&cno=4010001&source=autoList_null&subsource
     * =52a8851f3a5747db84c1f74a787028ac&listId=waplink&position=3&hcrId
     * =0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A899070
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/4d066203458c44d3b05407b82c185500.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"26.5M","intro":"26.5M",
     * "size":27831975,"describeType":"2","productId":"138949E687D24F4BBFFE8FEDA7679124",
     * "price":"1.00","exIcon":null,"listId":"waplink","stars":"5.0","videoFlag":0,
     * "md5":"BFEE206070E55615935911EFA549DF18","comefrom":null,
     * "detailId":"app|C10903817__autoList_null__52a8851f3a5747db84c1f74a787028ac__waplink__3__
     * "},{"appVersionName":"7.0.6","dependentedApps":[],"memo":"24小时真人美女视频直播互动！",
     * "operaType":null,"score":"4.8","package":"com.entertainment.ivp.xiuroom",
     * "versionCode":"706","appid":"C10900997","exIcons":{},"downCountDesc":"少于1万次安装",
     * "btnDisable":0,"name":"艾米直播-华为版","ID":"f82e0c3a45f54941baaa57a735e7cd7e",
     * "downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/f8/f82e0c3a45f54941baaa57a735e7cd7e/com.entertainment.ivp
     * .xiuroom.1705101437.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=52a8851f3a5747db84c1f74a787028ac&listId=waplink
     * &position=4&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A899070
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/f82e0c3a45f54941baaa57a735e7cd7e.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"30.9M","intro":"30.9M",
     * "size":32391575,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"5.0","videoFlag":0,"md5":"1139B451AA2657725AFD3DC137023EAB","comefrom":null,
     * "detailId":"app|C10900997__autoList_null__52a8851f3a5747db84c1f74a787028ac__waplink__4__
     * "},{"appVersionName":"1.0","dependentedApps":[],"memo":"和莉比公主一起环游世界去吧！","operaType":null,
     * "score":"3.8","package":"com.libii.princessvacation.huawei","versionCode":"1",
     * "appid":"C10860198","exIcons":{},"downCountDesc":"13万次安装","btnDisable":0,
     * "name":"莉比小公主之环游世界","ID":"2dde227a7ee3451b96a261311b2905ff","downurl":"http://appdlc
     * .hicloud.com/dl/appdl/application/apk/2d/2dde227a7ee3451b96a261311b2905ff/com.libii
     * .princessvacation.huawei.1705041357.apk?sign=c9d81011e610010520007000
     * @7ED2A239A80FA54D27BEFAACCC1AB7F3&cno=4010001&source=autoList_null&subsource
     * =52a8851f3a5747db84c1f74a787028ac&listId=waplink&position=5&hcrId
     * =0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0%3Bs
     * %3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A899070
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/2dde227a7ee3451b96a261311b2905ff.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"69.9M","intro":"69.9M",
     * "size":73282301,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"4.0","videoFlag":0,"md5":"F448DF616858D3EB8AF171906425CB57","comefrom":null,
     * "detailId":"app|C10860198__autoList_null__52a8851f3a5747db84c1f74a787028ac__waplink__5__
     * "},{"appVersionName":"3.9.4.2","dependentedApps":[],"memo":"海量新闻头条，精准推荐一点！",
     * "operaType":null,"score":"2.7","package":"com.yidian.huawei","versionCode":"39402",
     * "appid":"C10894562","exIcons":{},"downCountDesc":"少于1万次安装","btnDisable":0,
     * "name":"一点资讯Pro","ID":"dbafd53246714600a6c6d64ebf1c32b7","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/db/dbafd53246714600a6c6d64ebf1c32b7/com.yidian
     * .huawei.1705111055.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=52a8851f3a5747db84c1f74a787028ac&listId=waplink
     * &position=6&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A899070
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/dbafd53246714600a6c6d64ebf1c32b7.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"18.3M","intro":"18.3M",
     * "size":19194321,"describeType":"2","productId":"BE7E41C2419046CD8456E8E5A650CEE5",
     * "price":"1.00","exIcon":null,"listId":"waplink","stars":"3.0","videoFlag":0,
     * "md5":"DB26E6E99AD5EBB3463620B41752D1C6","comefrom":null,
     * "detailId":"app|C10894562__autoList_null__52a8851f3a5747db84c1f74a787028ac__waplink__6__
     * "},{"appVersionName":"1.0","dependentedApps":[],"memo":"让孩子很快学会: 仁、爱、礼、智、信",
     * "operaType":null,"score":"4.7","package":"com.thousand.character","versionCode":"1",
     * "appid":"C10910547","exIcons":{},"downCountDesc":"少于1万次安装","btnDisable":0,"name":"千字文",
     * "ID":"5539b07fc7da4914b6402fd6af287365","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/55/5539b07fc7da4914b6402fd6af287365/com.thousand
     * .character.1705041557.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3
     * &cno=4010001&source=autoList_null&subsource=52a8851f3a5747db84c1f74a787028ac&listId
     * =waplink&position=7&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0
     * %3Bisshake%3A0%3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId
     * %3A899070&encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/5539b07fc7da4914b6402fd6af287365.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"45.2M","intro":"45.2M",
     * "size":47416390,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"5.0","videoFlag":0,"md5":"0C487A7259E2BE2F36FA29A86D03EDCE","comefrom":null,
     * "detailId":"app|C10910547__autoList_null__52a8851f3a5747db84c1f74a787028ac__waplink__7__
     * "},{"appVersionName":"1.0","dependentedApps":[],"memo":"小公主需要一个暖心有爱的人来照顾她。",
     * "operaType":null,"score":"3.8","package":"com.libii.babycare.huawei","versionCode":"1",
     * "appid":"C10860252","exIcons":{},"downCountDesc":"8万次安装","btnDisable":0,"name":"公主宝宝照顾",
     * "ID":"dce69b654d4b477aa1614e474cb6e508","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/dc/dce69b654d4b477aa1614e474cb6e508/com.libii.babycare
     * .huawei.1705041742.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=52a8851f3a5747db84c1f74a787028ac&listId=waplink
     * &position=8&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A899070
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/dce69b654d4b477aa1614e474cb6e508.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"73.1M","intro":"73.1M",
     * "size":76695575,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"4.0","videoFlag":0,"md5":"C8DD5CC9FA3D5119E38DA29772D192E8","comefrom":null,
     * "detailId":"app|C10860252__autoList_null__52a8851f3a5747db84c1f74a787028ac__waplink__8__
     * "},{"appVersionName":"1.0","dependentedApps":[],"memo":"让孩子很快学会: 仁、爱、礼、智、信",
     * "operaType":null,"score":"5.0","package":"com.deyu.qimeng","versionCode":"1",
     * "appid":"C10910566","exIcons":{},"downCountDesc":"少于1万次安装","btnDisable":0,"name":"德育启蒙",
     * "ID":"db8604d04be94440b4d3464a95dbab83","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/db/db8604d04be94440b4d3464a95dbab83/com.deyu
     * .qimeng.1705041557.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=52a8851f3a5747db84c1f74a787028ac&listId=waplink
     * &position=9&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake%3A0
     * %3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A899070
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/db8604d04be94440b4d3464a95dbab83.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"30M","intro":"30M",
     * "size":31473591,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"5.0","videoFlag":0,"md5":"63997A04FBA70404043ED8BC97431A90","comefrom":null,
     * "detailId":"app|C10910566__autoList_null__52a8851f3a5747db84c1f74a787028ac__waplink__9__
     * "},{"appVersionName":"4.6","dependentedApps":[],"memo":"新东方给小学霸们的全面英语启蒙","operaType":null,
     * "score":"4.9","package":"com.koolearn.english.donutabc","versionCode":"62",
     * "appid":"C10524481","exIcons":{},"downCountDesc":"85万次安装","btnDisable":0,"name":"多纳学英语",
     * "ID":"b12c69bc7d294ed5821a6822f98a0066","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/b1/b12c69bc7d294ed5821a6822f98a0066/com.koolearn.english
     * .donutabc.1704281643.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3
     * &cno=4010001&source=autoList_null&subsource=52a8851f3a5747db84c1f74a787028ac&listId
     * =waplink&position=10&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0
     * %3Bisshake%3A0%3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId
     * %3A899070&encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/b12c69bc7d294ed5821a6822f98a0066.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"102M","intro":"102M",
     * "size":106977271,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"5.0","videoFlag":0,"md5":"4BD546FA00A769C43AE72060FA1E456A","comefrom":null,
     * "detailId":"app|C10524481__autoList_null__52a8851f3a5747db84c1f74a787028ac__waplink__10__
     * "},{"appVersionName":"1.0","dependentedApps":[],"memo":"时空穿越故事难以置信？敬请看过来！",
     * "operaType":null,"score":"4.4","package":"com.libii.frozenparty.huawei","versionCode":"1",
     * "appid":"C10848050","exIcons":{},"downCountDesc":"34万次安装","btnDisable":0,"name":"冰雪派对",
     * "ID":"77ed6833dc0541169cdd6241db26e651","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/77/77ed6833dc0541169cdd6241db26e651/com.libii.frozenparty
     * .huawei.1704241503.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=52a8851f3a5747db84c1f74a787028ac&listId=waplink
     * &position=11&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake
     * %3A0%3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A899070
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/77ed6833dc0541169cdd6241db26e651.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"47.1M","intro":"47.1M",
     * "size":49411144,"describeType":"2","price":"0","exIcon":null,"listId":"waplink",
     * "stars":"4.5","videoFlag":0,"md5":"30B82DD9B241E9B0BF0D932036B68B65","comefrom":null,
     * "detailId":"app|C10848050__autoList_null__52a8851f3a5747db84c1f74a787028ac__waplink__11__
     * "},{"appVersionName":"1.0.1","dependentedApps":[],"memo":"来驾驶拖拉机和收割机，开始工作吧！",
     * "operaType":null,"score":"2.0","package":"com.yateland.dinofarm.huawei","versionCode":"1",
     * "appid":"C10899118","exIcons":{},"downCountDesc":"少于1万次安装","btnDisable":0,"name":"恐龙农场",
     * "ID":"3584c1c0c9fc4531b7cece24389fc928","downurl":"http://appdlc.hicloud
     * .com/dl/appdl/application/apk/35/3584c1c0c9fc4531b7cece24389fc928/com.yateland.dinofarm
     * .huawei.1704241522.apk?sign=c9d81011e610010520007000@7ED2A239A80FA54D27BEFAACCC1AB7F3&cno
     * =4010001&source=autoList_null&subsource=52a8851f3a5747db84c1f74a787028ac&listId=waplink
     * &position=12&hcrId=0D855BF76D854F5ABD8EF2C81BCF46AA&extendStr=serviceType%3A0%3Bisshake
     * %3A0%3Bs%3A1495097561980%3Btrace%3A1cb9d75a1107447d87d331218b025093%3BlayoutId%3A899070
     * &encryptType=1","icon":"http://appimg.hicloud
     * .com/hwmarket/files/application/icon144/3584c1c0c9fc4531b7cece24389fc928.png",
     * "trace":"1cb9d75a1107447d87d331218b025093","ctype":0,"sizeDesc":"31.8M","intro":"31.8M",
     * "size":33355823,"describeType":"2","productId":"4F178E4C54424EA39FD5B75A7BB6672C",
     * "price":"12.00","exIcon":null,"listId":"waplink","stars":"2.0","videoFlag":0,
     * "md5":"F9326C32AD88A3AC329155039B91F1F3","comefrom":null,
     * "detailId":"app|C10899118__autoList_null__52a8851f3a5747db84c1f74a787028ac__waplink__12__
     * "}],"detailId":"quickwaplinkdetail|52a8851f3a5747db84c1f74a787028ac"}],"dataList-type":3,
     * "listId":"","isUpdatableFilter":1,"name":"精品尝鲜，每周五上新","layoutName":"horizonhomecard",
     * "layoutId":899070,"isInstalledFilter":1,
     * "detailId":"quickwaplinkdetail|52a8851f3a5747db84c1f74a787028ac"}]
     * salt : 7900228811842457017
     * rspKey : GzuQ+01qWgyedoYSqTf+ymE7cxk=
     */

    private int hasNextPage;
    private int count;
    private int marginTop;
    private String name;
    private int isSupSearch;
    private int contentType;
    private String statKey;
    private int totalPages;
    private String salt;
    private String rspKey;
    private List<LayoutBean> layout;
    private List<?> sortInfo;
    private List<LayoutDataBean> layoutData;

    public int getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(int hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsSupSearch() {
        return isSupSearch;
    }

    public void setIsSupSearch(int isSupSearch) {
        this.isSupSearch = isSupSearch;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public String getStatKey() {
        return statKey;
    }

    public void setStatKey(String statKey) {
        this.statKey = statKey;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRspKey() {
        return rspKey;
    }

    public void setRspKey(String rspKey) {
        this.rspKey = rspKey;
    }

    public List<LayoutBean> getLayout() {
        return layout;
    }

    public void setLayout(List<LayoutBean> layout) {
        this.layout = layout;
    }

    public List<?> getSortInfo() {
        return sortInfo;
    }

    public void setSortInfo(List<?> sortInfo) {
        this.sortInfo = sortInfo;
    }

    public List<LayoutDataBean> getLayoutData() {
        return layoutData;
    }

    public void setLayoutData(List<LayoutDataBean> layoutData) {
        this.layoutData = layoutData;
    }

    public static class LayoutBean {
        /**
         * layoutName : immersivebannercard
         * maxRows : 1
         * layoutId : 7100
         */

        private String layoutName;
        private int maxRows;
        private int layoutId;

        public String getLayoutName() {
            return layoutName;
        }

        public void setLayoutName(String layoutName) {
            this.layoutName = layoutName;
        }

        public int getMaxRows() {
            return maxRows;
        }

        public void setMaxRows(int maxRows) {
            this.maxRows = maxRows;
        }

        public int getLayoutId() {
            return layoutId;
        }

        public void setLayoutId(int layoutId) {
            this.layoutId = layoutId;
        }
    }

    public static class LayoutDataBean implements MultiItemEntity {
        /**
         * dataList : [{"icon":"http://appimg.hicloud
         * .com/hwmarket/files/entranceIcon/e3f300d859b4435cbcb6ecae00e9a042
         * /1495088590600_1080x684.jpg","trace":"1cb9d75a1107447d87d331218b025093;__entrance9;
         * __e3f300d859b4435cbcb6ecae00e9a042","name":"作业帮","commendIcon":"http://appimg.hicloud
         * .com/hwmarket/files/entranceIcon/e3f300d859b4435cbcb6ecae00e9a042
         * /1495088590600_1080x684.jpg","detailId":"app|C10113015|1495097563115"},
         * {"icon":"http://appimg.hicloud
         * .com/hwmarket/files/entranceIcon/c0a050fcf4464b2ca199fda4ae94e0ab
         * /1495032248512_1080x684.png","trace":"1cb9d75a1107447d87d331218b025093;__entrance9;
         * __c0a050fcf4464b2ca199fda4ae94e0ab","name":"天龙八部","commendIcon":"http://appimg.hicloud
         * .com/hwmarket/files/entranceIcon/c0a050fcf4464b2ca199fda4ae94e0ab
         * /1495032248512_1080x684.png","detailId":"app|C100005625|1495097563116"},
         * {"icon":"http://appimg.hicloud
         * .com/hwmarket/files/entranceIcon/0c51b48e628c474bbbbba189f3d23506
         * /1494577728760_1080x684.png","trace":"1cb9d75a1107447d87d331218b025093;__entrance9;
         * __0c51b48e628c474bbbbba189f3d23506","name":"付费精品","commendIcon":"http://appimg.hicloud
         * .com/hwmarket/files/entranceIcon/0c51b48e628c474bbbbba189f3d23506
         * /1494577728760_1080x684.png",
         * "detailId":"waplinkdetail|5058a9e3de4641c99185897f204f97bc__entrance|1495097563116"},
         * {"icon":"http://appimg.hicloud
         * .com/hwmarket/files/entranceIcon/4f3a7f44e6d845f8b9d6f2ec6049a3bd
         * /1494647342134_1080x684.png","trace":"1cb9d75a1107447d87d331218b025093;__entrance9;
         * __4f3a7f44e6d845f8b9d6f2ec6049a3bd","name":"适合妈妈玩的游戏都在这了","commendIcon":"http://appimg
         * .hicloud.com/hwmarket/files/entranceIcon/4f3a7f44e6d845f8b9d6f2ec6049a3bd
         * /1494647342134_1080x684.png",
         * "detailId":"waplinkdetail|f3f085fca6824061b0387c17b9fb19d2__entrance|1495097563117"},
         * {"icon":"http://appimg.hicloud
         * .com/hwmarket/files/entranceIcon/92b267f13e64449d9a797d6aa6a47642
         * /1494576365155_1080x684.png","trace":"1cb9d75a1107447d87d331218b025093;__entrance9;
         * __92b267f13e64449d9a797d6aa6a47642","name":"有道云笔记","commendIcon":"http://appimg
         * .hicloud.com/hwmarket/files/entranceIcon/92b267f13e64449d9a797d6aa6a47642
         * /1494576365155_1080x684.png","detailId":"app|C31969|1495097563118"},
         * {"icon":"http://appimg.hicloud
         * .com/hwmarket/files/entranceIcon/8d01f37f3b3e4de4a3e8b4755f40e396
         * /1494811085912_1080x684.png","trace":"1cb9d75a1107447d87d331218b025093;__entrance9;
         * __8d01f37f3b3e4de4a3e8b4755f40e396","name":"我爱你","commendIcon":"http://appimg.hicloud
         * .com/hwmarket/files/entranceIcon/8d01f37f3b3e4de4a3e8b4755f40e396
         * /1494811085912_1080x684.png",
         * "detailId":"waplinkdetail|e93dfd719e8a48808ccabe48b841f5ad__entrance|1495097563118"}]
         * dataList-type : 2
         * listId : 1
         * isUpdatableFilter : 0
         * layoutName : immersivebannercard
         * layoutId : 7100
         * isInstalledFilter : 0
         * name : 精品应用
         * detailId : 9EFEE843A95311E5BE9B548998110001
         */

        @SerializedName("dataList-type")
        private int dataListtype;
        private String listId;
        private int isUpdatableFilter;
        private String layoutName;
        private int layoutId;
        private int isInstalledFilter;
        private String name;
        private String detailId;
        private List<DataListBean> dataList;

        public int getDataListtype() {
            return dataListtype;
        }

        public void setDataListtype(int dataListtype) {
            this.dataListtype = dataListtype;
        }

        public String getListId() {
            return listId;
        }

        public void setListId(String listId) {
            this.listId = listId;
        }

        public int getIsUpdatableFilter() {
            return isUpdatableFilter;
        }

        public void setIsUpdatableFilter(int isUpdatableFilter) {
            this.isUpdatableFilter = isUpdatableFilter;
        }

        public String getLayoutName() {
            return layoutName;
        }

        public void setLayoutName(String layoutName) {
            this.layoutName = layoutName;
        }

        public int getLayoutId() {
            return layoutId;
        }

        public void setLayoutId(int layoutId) {
            this.layoutId = layoutId;
        }

        public int getIsInstalledFilter() {
            return isInstalledFilter;
        }

        public void setIsInstalledFilter(int isInstalledFilter) {
            this.isInstalledFilter = isInstalledFilter;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDetailId() {
            return detailId;
        }

        public void setDetailId(String detailId) {
            this.detailId = detailId;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        @Override
        public int getItemType() {
            int itemType = -1;
            switch (dataListtype) {
                case 3:
                case 34:
                    itemType = Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_COMMON;
                    break;
                case 2:
                    itemType = Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_AD;
                    break;
                case 0:
                    itemType = Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_BANNER;
                    break;
            }
            return itemType;
        }

        public static class DataListBean implements RecyclerBanner.BannerEntity {
            /**
             * icon : http://appimg.hicloud
             * .com/hwmarket/files/entranceIcon/e3f300d859b4435cbcb6ecae00e9a042
             * /1495088590600_1080x684.jpg
             * trace : 1cb9d75a1107447d87d331218b025093;__entrance9;
             * __e3f300d859b4435cbcb6ecae00e9a042
             * name : 作业帮
             * commendIcon : http://appimg.hicloud
             * .com/hwmarket/files/entranceIcon/e3f300d859b4435cbcb6ecae00e9a042
             * /1495088590600_1080x684.jpg
             * detailId : app|C10113015|1495097563115
             */

            private String icon;
            private String trace;
            private String name;
            private String commendIcon;
            private String detailId;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getTrace() {
                return trace;
            }

            public void setTrace(String trace) {
                this.trace = trace;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCommendIcon() {
                return commendIcon;
            }

            public void setCommendIcon(String commendIcon) {
                this.commendIcon = commendIcon;
            }

            public String getDetailId() {
                return detailId;
            }

            public void setDetailId(String detailId) {
                this.detailId = detailId;
            }

            @Override
            public String getUrl() {
                return icon;
            }
        }
    }

}
