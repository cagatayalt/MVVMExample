package com.cagatayalt.github.countriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cagatayalt.github.countriesapp.R
import com.cagatayalt.github.countriesapp.databinding.FragmentCountryBinding
import com.cagatayalt.github.countriesapp.databinding.FragmentFeedBinding
import com.cagatayalt.github.countriesapp.viewmodel.CountryViewModel


class CountryFragment : Fragment() {
    private var _binding: FragmentCountryBinding? = null
    val binding get() = _binding!!

    private var countryUuid = 0
    private lateinit var viewModel : CountryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom()

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                binding.countryNameTv.text = country.countryName
                binding.countryCapitalTv.text = country.countryCapital
                binding.countryCurrencyTv.text = country.countryCurrency
                binding.countryRegionTv.text = country.countryRegion
                // and there is image but the setup is not ready yet:
                // binding.countryFlagIV


            }

        })

    }


}