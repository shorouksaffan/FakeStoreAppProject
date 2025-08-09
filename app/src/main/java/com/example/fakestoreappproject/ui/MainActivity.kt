package com.example.fakestoreappproject.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresPermission
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.fakestoreappproject.ui.utils.NetworkMonitor
import com.example.fakestoreappproject.ui.themes.FakeStoreAppProjectTheme
private lateinit var networkMonitor: NetworkMonitor


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkMonitor = NetworkMonitor(this)
        setContent {

        }
    }
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun onStart() {
        super.onStart()
        networkMonitor.startMonitoring()
    }

    override fun onStop() {
        super.onStop()
        networkMonitor.stopMonitoring()
    }
}


