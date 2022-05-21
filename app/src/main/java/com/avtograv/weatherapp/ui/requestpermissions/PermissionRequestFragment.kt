package com.avtograv.weatherapp.ui.requestpermissions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.avtograv.weatherapp.databinding.FragmentPermissionRequestBinding

class PermissionRequestFragment : Fragment() {

    private var permissionRequestType: PermissionRequestType? = null
    private var activityListener: CallbacksListener? = null
    private var _binding: FragmentPermissionRequestBinding? = null
    private val binding get() = _binding!!



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CallbacksListener) {
            activityListener = context
        } else {
            throw RuntimeException("$context must implement PermissionRequestFragment.Callbacks")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPermissionRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        activityListener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = PermissionRequestFragment()
    }

    interface CallbacksListener {}
}

enum class PermissionRequestType {
    FINE_LOCATION, BACKGROUND_LOCATION
}