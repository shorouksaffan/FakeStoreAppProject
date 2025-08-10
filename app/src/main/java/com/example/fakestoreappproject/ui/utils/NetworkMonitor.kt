package com.example.fakestoreappproject.ui.utils
import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkMonitor(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val isOnline = MutableStateFlow(false)

    private val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            isOnline.value = true
        }

        override fun onLost(network: Network) {
            isOnline.value = false
        }
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun startMonitoring() {
        connectivityManager.registerDefaultNetworkCallback(callback)
    }

    fun stopMonitoring() {
        connectivityManager.unregisterNetworkCallback(callback)
    }
}
