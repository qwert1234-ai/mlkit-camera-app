package com.ira.mlkit_camera_app.camera

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import com.ira.mlkit_camera_app.R

class CameraScanActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_scan)
    }
}