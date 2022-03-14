package com.avtograv.weatherapp.ui.permissionRequestFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.avtograv.weatherapp.databinding.FragmentPermissionRequestBinding


class PermissionRequestFragment : Fragment() {


    private var permissionRequestType: PermissionRequestType? = null
    private lateinit var binding: FragmentPermissionRequestBinding
    private var activityListener: Callbacks? = null



    interface Callbacks {
        fun displayLocationUI()
    }

    companion object {
        private const val ARG_PERMISSION_REQUEST_TYPE =
            "com.avtograv.weatherapp.ui.permissionRequestFragment.PERMISSION_REQUEST_TYPE"

        private const val REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE = 34
        private const val REQUEST_BACKGROUND_LOCATION_PERMISSIONS_REQUEST_CODE = 56

        @JvmStatic
        fun newInstance(permissionRequestType: PermissionRequestType) =
            PermissionRequestFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PERMISSION_REQUEST_TYPE, permissionRequestType)
                }
            }
    }
}

enum class PermissionRequestType {
    FINE_LOCATION, BACKGROUND_LOCATION
}