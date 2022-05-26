package com.avtograv.weatherapp.ui.mainfragment.view

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avtograv.weatherapp.R
import com.avtograv.weatherapp.common.exhaustive
import com.avtograv.weatherapp.data.getLocationList
import com.avtograv.weatherapp.databinding.FragmentMainBinding
import com.avtograv.weatherapp.di.RepositoryProvider
import com.avtograv.weatherapp.ui.mainfragment.dbviewmodel.LocationUpdateViewModel
import com.avtograv.weatherapp.ui.mainfragment.viewmodel.WeatherFactoryViewModel
import com.avtograv.weatherapp.ui.mainfragment.viewmodel.WeatherOptionsViewState
import com.avtograv.weatherapp.ui.mainfragment.viewmodel.WeatherViewModelImpl


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var activityListener: CallbacksListener? = null
    private var pageNumber = 0
    private val weatherViewModel: WeatherViewModelImpl by viewModels {
        WeatherFactoryViewModel(
            (requireActivity() as RepositoryProvider).provideRepository()
        )
    }
    private val locationUpdateViewModel by lazy {
        ViewModelProvider(this)[LocationUpdateViewModel::class.java]
    }
    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                Log.d("DEBUG", "${it.key} = ${it.value}")
            }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CallbacksListener) {
            activityListener = context
        } else {
            throw RuntimeException("$context must implement MainFragment.CallbacksListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = if (arguments != null) requireArguments().getInt("num") else 0
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        requestMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        getLocation()
        setupUiComponents()
    }

    private fun setupToolbar() {
        val appCompatActivity = activity as AppCompatActivity
        appCompatActivity.setSupportActionBar(binding.toolbar)
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_plus_white)
            titleMarginStart = 200
            val list = getLocationList(requireContext())
            title = list.elementAt(pageNumber).locationName
            setTitleTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary))
            setNavigationOnClickListener {
                activityListener?.displayAddLocation()
            }
        }
    }

    private fun setupUiComponents() {
        val adapterRecyclerView = AdapterRecyclerView()
        binding.rvWeather.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = adapterRecyclerView
        }

        val list = getLocationList(requireContext())
        weatherViewModel.load(
            list.elementAt(pageNumber).latitude,
            list.elementAt(pageNumber).longitude
        )

        loadingCurrentWeather(adapterRecyclerView)
    }

    private fun loadingCurrentWeather(adapter: AdapterRecyclerView) {
        weatherViewModel.stateOutput.observe(viewLifecycleOwner) { state ->
            when (state) {
                is WeatherOptionsViewState.SuccessLoading ->
                    adapter.submitList(state.weatherList)
                is WeatherOptionsViewState.FailedLoading -> {
                    Log.e(TAG, "WeatherLocationException", state.exception)
                    Toast.makeText(
                        requireContext(),
                        R.string.error_network_failed,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.exhaustive
        }
    }

    private fun getLocation() {
        locationUpdateViewModel.startLocationUpdates()

        locationUpdateViewModel.locationListLiveData.observe(viewLifecycleOwner) { locations ->
            locations?.let {
                Log.d("locations", "Got ${locations.size} locations")
                if (locations.isEmpty()) {
                    Log.d("locations", "empty location database message")
                } else {
                    val outputStringBuilder = StringBuilder("")
                    for (location in locations) {
                        outputStringBuilder.append(location.toString() + "\n")
                    }
                    Log.d("locations", outputStringBuilder.toString())
                }
            }
        }
    }

    override fun onDetach() {
        activityListener = null
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface CallbacksListener {
        fun displayAddLocation()
    }

    companion object {
        fun newInstance(page: Int): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            args.putInt("num", page)
            fragment.arguments = args
            return fragment
        }
    }
}