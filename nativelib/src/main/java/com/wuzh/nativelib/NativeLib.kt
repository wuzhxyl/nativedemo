package com.wuzh.nativelib

public class NativeLib {

    /**
     * A native method that is implemented by the 'nativelib' native library,
     * which is packaged with this application.
     */

    // 从C++中获取字符串
    external fun stringFromJNI(): String
    // 从c++中修改数据并返回
    external fun nativeAddFromJNI(a:Int, b:Int): Int
    // 在c++中修改java传递的字符串并返回
    external fun modifyJavaStringAndReturnFromJNI(source:String): String
    // 在kt文件中修改c++中的字符串并返回给java侧
    external fun modifyCPPStringAndReturnFromJava(): String

    fun modifyCPPString(source:String):String = "$source Java"

    companion object {
        // Used to load the 'nativelib' library on application startup.
        init {
            System.loadLibrary("nativelib")
        }
    }
}