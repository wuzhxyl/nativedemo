#include <jni.h>
#include <string>
#include <iostream>

using namespace std;
extern "C" JNIEXPORT jstring JNICALL
Java_com_wuzh_nativelib_NativeLib_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {

    int num = 1;
    int *p = &num;
    *p = 1;

    cout << "num is " << *p << endl;
    printf("num is %d", *p);
    std::string hello = "Hello from C++";

    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_wuzh_nativelib_NativeLib_nativeAddFromJNI(JNIEnv *env, jobject thiz, jint a, jint b) {
    return a+b;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_wuzh_nativelib_NativeLib_modifyJavaStringAndReturnFromJNI(JNIEnv *env, jobject thiz,
                                                                   jstring source) {
    const char *src = (env->GetStringUTFChars(source, NULL));
    char *append = ", JNI";
    ::strcat((char *)src, append);

    env->ReleaseStringUTFChars(source, src);
    return env->NewStringUTF(src);
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_wuzh_nativelib_NativeLib_modifyCPPStringAndReturnFromJava(JNIEnv *env, jobject thiz) {
    char *source = "Hello from C++";

    jclass clazz = env->GetObjectClass(thiz);
    jmethodID modifyCPPString = env->GetMethodID(clazz, "modifyCPPString", "(Ljava/lang/String;)Ljava/lang/String;");
    return (jstring) env->CallObjectMethod(thiz, modifyCPPString, env->NewStringUTF(source));
}