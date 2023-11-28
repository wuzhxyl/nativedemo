package com.wuzh.nativedemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wuzh.nativedemo.MainActivity.Companion.TAG
import com.wuzh.nativedemo.ui.theme.NativeDemoTheme
import com.wuzh.nativelib.NativeLib

class MainActivity : ComponentActivity() {
    companion object {
        val TAG = "MainActivity";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NativeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Jni")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        OutlinedButton(onClick = {
            nativeCall()
        },modifier=Modifier.fillMaxWidth(), ) {
            Text(
                text = "Hello $name!",
                modifier = modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NativeDemoTheme {
        Greeting("Jni")
    }
}

fun nativeCall() {
    Log.d(TAG, "nativeCall: call")
    val lib = NativeLib()
    var jniStr = lib.stringFromJNI()
    var sum = lib.nativeAddFromJNI(1,2)
    var modifyStrFromJNI = lib.modifyJavaStringAndReturnFromJNI("Hello")
    val modifyCPPStrFromJava = lib.modifyCPPStringAndReturnFromJava()

    Log.d(TAG, "nativeCall: $jniStr, sum: $sum, modifyStrFrmJNI: $modifyStrFromJNI, modifyCPPStrFromJava: $modifyCPPStrFromJava")

}