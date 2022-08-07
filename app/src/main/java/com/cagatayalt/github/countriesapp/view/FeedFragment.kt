package com.cagatayalt.github.countriesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cagatayalt.github.countriesapp.adapter.CountryAdapter
import com.cagatayalt.github.countriesapp.databinding.FragmentFeedBinding
import com.cagatayalt.github.countriesapp.viewmodel.FeedViewModel

class FeedFragment : Fragment() {
    private var _binding: FragmentFeedBinding? = null
    val binding get() = _binding!!
    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater,container,false)


        return binding.root
    }


    // We added this
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        binding.countryRV.layoutManager= LinearLayoutManager(context)
        binding.countryRV.adapter = countryAdapter

        observeLiveData()

        /*binding.fragmentButton.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }*/
    }



    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            countries ->
                countries?.let{
                    binding.countryRV.visibility = View.VISIBLE
                    countryAdapter.updateCountryList(countries)
                }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {
            error ->
            error.let {
                if (it) {
                    // If there is an error
                    binding.countryError.visibility = View.VISIBLE
                } else {
                    binding.countryError.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let{
                 if (it) {
                     binding.countryLoadingPB.visibility = View.VISIBLE
                     binding.countryRV.visibility = View.GONE
                     binding.countryError.visibility = View.GONE
                 } else {
                     binding.countryLoadingPB.visibility = View.GONE
                 }
            }
        })

    }


}