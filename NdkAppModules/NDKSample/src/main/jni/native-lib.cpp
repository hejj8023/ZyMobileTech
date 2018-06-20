#include <jni.h>
#include <string>
extern "C" JNIEXPORT jstring

JNICALL
Java_com_example_ndksample_MainActivity_stringFromJNI(JNIEnv *env, jobject instance) {

    std::string hello = "hello world";
    return env->NewStringUTF(hello.c_str());
}