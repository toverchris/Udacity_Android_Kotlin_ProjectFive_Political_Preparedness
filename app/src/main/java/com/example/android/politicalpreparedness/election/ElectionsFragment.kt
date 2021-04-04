package com.example.android.politicalpreparedness.election

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.android.politicalpreparedness.MainActivity
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.databinding.FragmentElectionBinding
import com.example.android.politicalpreparedness.election.adapter.ElectionListAdapter
//import com.example.android.politicalpreparedness.election.adapter.ElectionListener
import com.example.android.politicalpreparedness.launch.LaunchFragmentDirections
import com.example.android.politicalpreparedness.network.models.Division
import kotlinx.coroutines.InternalCoroutinesApi

// TODO: 3/25/21 has been an abstract class before  
class ElectionsFragment: Fragment() {

    //TODO: Declare ViewModel
    //private val _viewModel: ElectionsViewModel by viewModels()
    @InternalCoroutinesApi
    private val _viewModel: ElectionsViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        //ViewModelProvider(this).get(ElectionsViewModel::class.java)
        ViewModelProviders.of(this, ElectionsViewModelFactory())
                .get(ElectionsViewModel::class.java)
    }

    //private lateinit var binding: FragmentElectionBinding

    @InternalCoroutinesApi
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //TODO: Add ViewModel values and create ViewModel


        //TODO: Add binding values
        /*
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_election,
                container,
                false)

         */
        val binding = FragmentElectionBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = _viewModel


        binding.recyclerViewUpcomingElections.adapter = ElectionListAdapter(ElectionListAdapter.ElectionListener{
            Log.i("ElectionView", "new upcoming election name ${it.name}")
            _viewModel.displayElection(it)
        })

        binding.recyclerViewSavedElections.adapter = ElectionListAdapter(ElectionListAdapter.ElectionListener{
            Log.i("ElectionView", "new upcoming election name ${it.name}")
            _viewModel.displayElection(it)
        })


        /*
        // TODO: 4/3/21 POINT TO START!! MAKE THE RECYCLERVIEW WORK
        //_viewModel = ViewModelProvider(this, ElectionsViewModelFactory()).get(ElectionsViewModel::class.java)
        binding.recyclerViewUpcomingElections.contentDescription = _viewModel.upcomingElectionsList.toString()

        //TODO: Link elections to voter info
        binding.recyclerViewUpcomingElections.setOnClickListener { navToVoterInfo() }
        binding.recyclerViewSavedElections.setOnClickListener { navToVoterInfo() }

        //TODO: Initiate recycler adapters
        //binding.recyclerViewUpcomingElections.addView(binding.recyclerViewUpcomingElections)

        binding.recyclerViewUpcomingElections.adapter = ElectionListAdapter(ElectionListAdapter.ElectionListener {
            Log.i("Elections View", "new upcoming election name ${it.name}")
        })
        binding.recyclerViewSavedElections.adapter = ElectionListAdapter(ElectionListAdapter.ElectionListener {
            Log.i("Elections View", "new saved election name ${it.name}")
        })
        //TODO: Populate recycler adapters

         */



        return binding.root
    }

    //TODO: Refresh adapters when fragment loads


    private fun navToVoterInfo() {
        this.findNavController().navigate(ElectionsFragmentDirections.actionElectionsFragmentToVoterInfoFragment(1, Division("id string", "country string", "state string")))
    }

}