package com.ira.mlkit_camera_app.camera

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import com.ira.mlkit_camera_app.R
import com.ira.mlkit_camera_app.helpers.FrameAnalyzer

@ExperimentalGetImage class CameraScanActivity : AppCompatActivity() {
    private lateinit var cameraFuture: ListenableFuture<ProcessCameraProvider>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        cameraFuture = ProcessCameraProvider.getInstance(this)
        cameraFuture.addListener({
             val provider = cameraFuture.get()
            settingCamera(provider)
            }, ContextCompat.getMainExecutor(this))
    }
    private fun settingCamera(cameraProvider: ProcessCameraProvider){
        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
        val preview = Preview.Builder()
            .build()
        val cameraPreview = findViewById<PreviewView>(R.id.preview_view)
        preview.setSurfaceProvider(cameraPreview.surfaceProvider)

        val imageAnalysis = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
        val viewPoint = findViewById<LandMarkView>(R.id.land_mark_view)
        imageAnalysis.setAnalyzer(mainExecutor, FrameAnalyzer(viewPoint))

        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis)
    }
}