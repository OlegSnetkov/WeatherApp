package com.avtograv.weatherapp.presentetion.mainscreen.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avtograv.weatherapp.R
import com.avtograv.weatherapp.databinding.FragmentMainScreenBinding
import com.avtograv.weatherapp.getLocationList


class MainScreenFragment : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private var pageNumber = 0
    private var _context: ClickListener? = null

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
        val mainAdapter = RvAdapter()
        binding.rvWeather.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = mainAdapter
        }
        mainAdapter.submitList(getLocationList(requireContext()))
    }

    override fun onDetach() {
        _context = null
        super.onDetach()
    }

    interface ClickListener {
        fun letAddLocation()
    }

    companion object {
        fun newInstance(page: Int): MainScreenFragment {
            val fragment = MainScreenFragment()
            val args = Bundle()
            args.putInt("num", page)
            fragment.arguments = args
            return fragment
        }
    }
}