package io.tbib.composerequestpermission

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

class RequestPermission{
   @OptIn(ExperimentalPermissionsApi::class)
   lateinit var permissionState: PermissionState
    @SuppressLint("PermissionLaunchedDuringComposition", "NotConstructor")
    @OptIn(ExperimentalPermissionsApi::class)

    @Composable
    fun InitPermission(
        permission: String,
    ) {
          permissionState = rememberPermissionState(permission)




    }
    @OptIn(ExperimentalPermissionsApi::class)
    fun requestPermission(
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit,
        onPermissionFailed: () -> Unit
    ){
        when (permissionState.status)
        {
            PermissionStatus.Granted -> {
                onPermissionGranted()
            }
            PermissionStatus.Denied(false) -> {
                permissionState.launchPermissionRequest()
                onPermissionDenied()
            }

            else -> {
                permissionState.launchPermissionRequest()
                onPermissionFailed()

            }
        }
    }

}

