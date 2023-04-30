package com.ira.mlkit_camera_app.helpers

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class FrameAnalyzer : ImageAnalysis.Analyzer {
    override fun analyze(image: ImageProxy) {
        println("ANALIZE")
        image.close()
    }
}