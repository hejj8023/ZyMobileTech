Android提供了Camera来控制拍照，步骤如下：
（1）调用Camera的open()方法打开相机。
（2）调用Camera的getParameters（）获取拍照参数，该方法返回一个Cmera.Parameters对象。
（3）调用Camera.Parameters对象对照相的参数进行设置。
（4）调用Camera的setParameters（），并将Camera.Parameters对象作为参数传入，这样就可以对拍照进行参数控制，Android2.3.3以后不用设置。
（5）调用Camerade的startPreview()的方法开始预览取景，在之前需要调用Camera的setPreviewDisplay(SurfaceHolder holder)设置使用哪个SurfaceView来显示取得的图片。
（6）调用Camera的takePicture()方法进行拍照。
（7）程序结束时，要调用Camera的stopPreview()方法停止预览，并且通过Camera.release()来释放资源。

链接：https://www.jianshu.com/p/9b40e903b4a0