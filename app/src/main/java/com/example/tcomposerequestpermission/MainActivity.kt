package com.example.tcomposerequestpermission

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tcomposerequestpermission.ui.theme.TComposeRequestPermissionTheme
import io.tbib.composerequestpermission.RequestPermission

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TComposeRequestPermissionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val imagePermission = RequestPermission().apply {
        InitPermission(
            permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Manifest.permission.READ_MEDIA_IMAGES
            } else {
                Manifest.permission.READ_EXTERNAL_STORAGE
            },

        )


    }
  Column {
      ElevatedButton(onClick = {
          imagePermission.requestPermission(
              onPermissionGranted = {
                  Log.d("Permission Request xxx", "Permission Granted")
              },
              onPermissionDenied = {
                  Log.d("Permission Request xxx", "Permission Denied")
                                   },
              onPermissionFailed = {
                  Log.d("Permission Request xxx", "Permission Failed")
              }
          )
      }) {
          Text("Request Permission")
      }
  }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TComposeRequestPermissionTheme {
        Greeting("Android")
    }
}