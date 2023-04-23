package com.ira.mlkit_camera_app.startScan

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment

import com.ira.mlkit_camera_app.R
import com.ira.mlkit_camera_app.camera.CameraScanActivity

class ScanFragment : Fragment(){
    private  val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
        {isGranted:Boolean ->
            if(isGranted == true){
                startScan()
            } else {
                //домашнее задание. Скрыть кнопку и показаать сообщение.
            }
        }
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.start_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val startButton = view.findViewById<Button>(R.id.scan_button)
        //startButton.isVisible
        startButton.setOnClickListener {
            val permission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            when (permission) {
                PackageManager.PERMISSION_GRANTED -> {
                    startScan()
                }
                else -> {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
        }
    }
    private fun startScan() {
        val intent = Intent(requireContext(), CameraScanActivity::class.java)
        startActivity(intent)
    }
}
