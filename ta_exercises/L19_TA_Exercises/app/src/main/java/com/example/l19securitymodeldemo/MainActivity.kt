package com.example.l19securitymodeldemo

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.l19securitymodeldemo.databinding.ActivityMainBinding

/**
 * MainActivity — Android Security Model Demo.
 *
 * This Activity introduces one of the core ideas of the Android Security Model:
 * applications do not automatically receive access to protected resources.
 *
 * The CAMERA permission is used as an academic example of a "dangerous"
 * permission. Dangerous permissions must be:
 *
 * 1. declared in AndroidManifest.xml;
 * 2. checked at runtime;
 * 3. requested explicitly from the user;
 * 4. handled correctly if the user grants or denies the request.
 */
class MainActivity : ComponentActivity() {

    /**
     * ViewBinding provides type-safe access to the views declared in activity_main.xml.
     * This avoids manual calls to findViewById and reduces runtime errors.
     */
    private lateinit var binding: ActivityMainBinding

    companion object {
        /**
         * Logcat tag used to filter messages related to this security demo.
         */
        private const val TAG = "L19_SECURITY"
    }

    /**
     * ActivityResultContracts.RequestPermission is the modern API for requesting
     * a single runtime permission.
     *
     * The launcher must be registered before the Activity reaches the STARTED state.
     */
    private val cameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->

            if (granted) {
                showPermissionGranted()
            } else {
                showPermissionDenied()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
            Inflate the XML layout through ViewBinding and attach it to the Activity.
        */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
            The button starts the permission-checking flow.
            This keeps the example explicit and easy to demonstrate in class.
        */
        binding.btnCheckPermission.setOnClickListener {
            checkCameraPermission()
        }
    }

    /**
     * Checks the current state of the CAMERA permission.
     *
     * This method follows the exact runtime-permission structure discussed
     * in the lesson:
     *
     * - if permission is already granted, use the protected resource;
     * - if rationale should be shown, explain why the permission is needed;
     * - otherwise, request the permission from the user.
     */
    private fun checkCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PERMISSION_GRANTED -> {
                showPermissionGranted()
            }

            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                showPermissionRationale()
            }

            else -> {
                requestCameraPermission()
            }
        }
    }

    /**
     * Launches the Android system permission dialog.
     *
     * The application does not grant itself the permission.
     * The final decision belongs to the user and is enforced by the Android system.
     */
    private fun requestCameraPermission() {
        Log.d(TAG, "Requesting CAMERA permission")
        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
    }

    /**
     * Updates the UI after the CAMERA permission has been granted.
     *
     * In a real application, this is the point where the app could safely
     * open the camera or continue with camera-dependent functionality.
     */
    private fun showPermissionGranted() {
        Log.d(TAG, "CAMERA permission granted")

        binding.tvStatus.text = "CAMERA permission granted."
        binding.tvExplanation.text =
            "The app is allowed to access the camera because the user explicitly granted the dangerous permission."
    }

    /**
     * Shows an educational explanation when Android recommends displaying
     * a permission rationale.
     *
     * This usually happens after the user has denied the permission once.
     */
    private fun showPermissionRationale() {
        Log.d(TAG, "Showing CAMERA permission rationale")

        binding.tvStatus.text = "Permission rationale required."
        binding.tvExplanation.text =
            "The camera permission is needed to demonstrate Android runtime permissions. Press the button again to request it."

        /*
            After explaining the reason, the same button is reused to request
            the permission again.
        */
        binding.btnCheckPermission.text = "Request CAMERA Permission"
        binding.btnCheckPermission.setOnClickListener {
            requestCameraPermission()
        }
    }

    /**
     * Updates the UI after the CAMERA permission has been denied.
     *
     * This demonstrates that protected resources remain inaccessible when
     * the user does not grant the dangerous permission.
     */
    private fun showPermissionDenied() {
        Log.d(TAG, "CAMERA permission denied")

        binding.tvStatus.text = "CAMERA permission denied."
        binding.tvExplanation.text =
            "Without this permission, the app cannot access protected camera resources. This demonstrates Android sandboxing and permission enforcement."
    }
}