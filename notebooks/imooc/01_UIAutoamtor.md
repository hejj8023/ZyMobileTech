#### 资料地址
https://www.imooc.com/video/17034

### python使用
https://www.cnblogs.com/fnng/p/8486863.html

## Android自动化测试发展
-   MonkeyRunner 随机测试 
-   Instrumentation(Activity测试，没办法距进程)
-   UIAutoamtor 只能测试ui
-   ATSL(安卓测试支持库) -> Espresso UA 2.0

## 黑盒UI自动化测试套路

      通过通过条件查找组件然后操作组件

  

## 入门资料
https://www.cnblogs.com/sunada2005/p/6433471.html

## 示例代码

    @RunWith(AndroidJUnit4.class)
    public class MKTest {
        public Instrumentation instrumentation;
    
        public UiDevice uiDevice;
    
        /**
         * 在用例运行之前执行
         */
        @Before
        public void setUp() {
            instrumentation = InstrumentationRegistry.getInstrumentation();
            uiDevice = UiDevice.getInstance(instrumentation);
        }
    
    }    
    
## 重点
- 核心定位元素
>   uiautomatorviewer工具
   抓取应用的层级信息
   
>   核心定位元素
    
    resource-id 资源id，核心需要操作组件都会有id
    
    text        文字属性，一般显示在组件上的文字
    
    content-desc  描述，常用于提示操作
    
    checked       选择属性，常见开关，单选，筛选的开与关的状态
    
    focused      焦点属性
    
>   搜索对象-BySelector

- 基础模拟动作    