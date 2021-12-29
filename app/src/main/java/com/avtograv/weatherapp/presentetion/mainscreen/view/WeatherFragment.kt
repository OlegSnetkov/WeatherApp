package com.avtograv.weatherapp.presentetion.mainscreen.view

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avtograv.weatherapp.R
import com.avtograv.weatherapp.common.exhaustive
import com.avtograv.weatherapp.common.getLocationList
import com.avtograv.weatherapp.databinding.FragmentMainScreenBinding
import com.avtograv.weatherapp.di.RepositoryProvider
import com.avtograv.weatherapp.presentetion.mainscreen.viewmodel.FactoryViewModel
import com.avtograv.weatherapp.presentetion.mainscreen.viewmodel.OptionsViewState
import com.avtograv.weatherapp.presentetion.mainscreen.viewmodel.WeatherViewModelImpl
import kotlin.properties.Delegates

class WeatherFragment : Fragment() {



    private lateinit var binding: FragmentMainScreenBinding
    private var pageNumber by Delegates.notNull<Int>()
    private var _context: ClickListener? = null
    private val weatherViewModel: WeatherViewModelImpl by viewModels {
        FactoryViewModel(
            (requireActivity() as RepositoryProvider).provideRepository(),
            //(getLocationList(requireContext()).first())
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickListener)
            _context = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = if (arguments != null) requireArguments().getInt("num") else 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupUiComponents()
    }

    private fun setupToolbar() {
        val appCompatActivity = activity as AppCompatActivity
        appCompatActivity.setSupportActionBar(binding.toolbar)
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_plus_white)
            titleMarginStart = 200
            title = "Fragment - ${pageNumber + 1}"
            setTitleTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary))
            setNavigationOnClickListener {
                _context?.letAddLocation()
            }
        }
    }

    private fun setupUiComponents() {
        val adapterRecyclerView = AdapterRecyclerView()
        binding.rvWeather.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = adapterRecyclerView

        }
        loadCurrentWeather(adapterRecyclerView)
    }

    private fun loadCurrentWeather(adapter: AdapterRecyclerView) {
        weatherViewModel.stateOutput.observe(viewLifecycleOwner, { state ->
            when (state) {
                is OptionsViewState.SuccessLoading -> adapter.submitList(state.weatherList)
                is OptionsViewState.FailedLoading -> {
                    Log.e(TAG, "MainTag", state.exception)
                    Toast.makeText(
                        requireContext(),
                        R.string.error_network_failed,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.exhaustive
        })
    }

    override fun onDetach() {
        _context = null
        super.onDetach()
    }

    interface ClickListener {
        fun letAddLocation()
    }

    companion object {
        fun newInstance(page: Int): WeatherFragment {
            val fragment = WeatherFragment()
            val args = Bundle()
            args.putInt("num", page)
            fragment.arguments = args
            return fragment
        }
    }
}