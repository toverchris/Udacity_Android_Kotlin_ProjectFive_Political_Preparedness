package com.example.android.politicalpreparedness.representative

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RepresentativeViewModel: ViewModel() {

    //TODO: Establish live data for representatives and address
    private  val _representativesList = MutableLiveData<List<String>>()
    val representativesList: LiveData<List<String>>
        get() = _representativesList

    private  val _addressList = MutableLiveData<List<String>>()
    val addressList: LiveData<List<String>>
        get() = _addressList

    //TODO: Create val and functions to populate live data for upcoming elections from the API and saved elections from local database
    init {
        defineFakeData()
    }
    private fun defineFakeData(){
        _representativesList.value = listOf("bayern", "hessen", "berlin", "sagres")
        _addressList.value = listOf("bayern str", "hessen str", "berlin str", "sagres str")
    }

    //TODO: Create function to fetch representatives from API from a provided address

    /**
     *  The following code will prove helpful in constructing a representative from the API. This code combines the two nodes of the RepresentativeResponse into a single official :

    val (offices, officials) = getRepresentativesDeferred.await()
    _representatives.value = offices.flatMap { office -> office.getRepresentatives(officials) }

    Note: getRepresentatives in the above code represents the method used to fetch data from the API
    Note: _representatives in the above code represents the established mutable live data housing representatives

     */

    //TODO: Create function get address from geo location

    //TODO: Create function to get address from individual fields

}
