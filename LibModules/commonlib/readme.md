## 暂时废弃，lib项目工程由ZYMobileSdk统一处理并发布

 模块原aar发布到组织仓库
    https://github.com/zydeveloperteam/zysdk
     maven { url "https://raw.githubusercontent.com/zydeveloperteam/zysdk/master" }
      compile 'com.szzynt:common_lib:1.0.0' 
 
 现项目会统一发布到jitpack上
github工程为https://github.com/syusikoku/ZYSdk

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    
    dependencies {
        compile 'com.github.syusikoku:ZYSdk:v1.0.0'
    }
    