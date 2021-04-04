package com.example.android.politicalpreparedness.election

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.politicalpreparedness.network.models.Division
import com.example.android.politicalpreparedness.network.models.Election
import java.util.*


//TODO: Construct ViewModel and provide election datasource

class ElectionsViewModel: ViewModel() {

    private  val _upcomingElectionsList = MutableLiveData<List<Election>>()
    val upcomingElectionsList: LiveData<List<Election>>
        get() = _upcomingElectionsList

    private  val _savedElectionsList = MutableLiveData<List<Election>>()
    val savedElectionsList: LiveData<List<Election>>
        get() = _savedElectionsList

    private  val _navigateToSelectedElection = MutableLiveData<Election>()
    val navigateToSelectedElection: LiveData<Election>
        get() = _navigateToSelectedElection

    //TODO: Create val and functions to populate live data for upcoming elections from the API and saved elections from local database
    init {
        defineFakeData()
    }
    private fun defineFakeData(){
        _upcomingElectionsList.value = listOf(
                Election(1, "VIP Test 1", Date(2022-1900,1,24), Division("123","Sagres","Portugal")),
                Election(2, "Test 2", Date(2022-1900,2,24), Division("222","Tonel","Portugal")),
                Election(3, "Test 3", Date(2022-1900,3,24), Division("333","Beliche","Portugal"))
                )

        _savedElectionsList.value = listOf(
                Election(4, "Test 4", Date(2022-1900,4,24), Division("444","Mareta","Portugal")),
                Election(5, "Test 5", Date(2022-1900,5,24), Division("24442","Marthinal","Portugal")),
                Election(6, "Test 6", Date(2022-1900,6,24), Division("35553","Zavial","Portugal"))
        )
    }

    //TODO: Create functions to navigate to saved or upcoming election voter info
    fun displayElection(election: Election) {
        _navigateToSelectedElection.value = election
    }

}