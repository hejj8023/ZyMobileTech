# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#指定压缩级别
-optimizationpasses 5

#包名不混合大小写
-dontusemixedcaseclassnames

#混淆时记录日志
-verbose

#关闭预校验
-dontpreverify

#不优化输入的类文件
#-dontoptimize

#保护注解
-keepattributes *Annotation*

#不跳过非公共的库的类成员
-dontskipnonpubliclibraryclassmembers

#混淆时采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#把混淆类中的方法名也混淆了
-useuniqueclassmembernames

#优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification

#将文件来源重命名为“SourceFile”字符串
-renamesourcefileattribute SourceFile

#保留行号
-keepattributes SourceFile,LineNumberTable

# 保留native的方法的方法名和包含native方法的类的类名不被混淆
-keepclasseswithmembers class * {
    native <methods>;
}

# 保留继承于View的类的set*和get*方法不被混淆
-keepclassmembers public class * extends android.view.View {
    *** get*();
    void set*(***);
}

# 保留继承于Activity的类中以View为参数的函数不被混淆
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keep class org.parceler.Parceler$$Parcels

-keepnames class * implements java.io.Serializable

#保持自定义View的get和set相关方法
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

#保持Activity中View及其子类入参的方法
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

#枚举
-keepclassmembers enum * {
    **[] $VALUES;
    public *;
}

#Parcelable
-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator CREATOR;
}

#R文件的静态成员
-keepclassmembers class **.R$* {
    public static <fields>;
}

-dontwarn android.support.**

#keep相关注解
-keep class android.support.annotation.Keep

-keep @android.support.annotation.Keep class * {*;}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <init>(...);
}


#保持所有实现 Serializable 接口的类成员
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#Fragment不需要在AndroidManifest.xml中注册，需要额外保护下
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment

-keep class **.R$* {*;}

# 保持测试相关的代码
-dontnote junit.framework.**
-dontnote junit.runner.**
-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**

#日志信息去除 dontoptimize这个一定要取消否则会不生效
# https://m.aliyun.com/jiaocheng/20774.html
-assumenosideeffects class android.util.Log{
      public static *** d(...);
      public static *** v(...);
      public static *** i(...);
      public static *** e(...);
      public static *** w(...);
}

-assumenosideeffects class com.example.imoocproguard.LogUtils{
      public static *** d(...);
      public static *** v(...);
      public static *** i(...);
      public static *** e(...);
      public static *** w(...);
}