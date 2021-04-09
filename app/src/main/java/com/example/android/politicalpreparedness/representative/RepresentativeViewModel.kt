package com.example.android.politicalpreparedness.representative

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.politicalpreparedness.network.CivicsApi
import com.example.android.politicalpreparedness.network.models.Division
import com.example.android.politicalpreparedness.network.models.RepresentativeResponse
import com.example.android.politicalpreparedness.network.models.VoterInfoResponse
import com.example.android.politicalpreparedness.representative.model.Representative
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class RepresentativeViewModel: ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    //TODO: Establish live data for representatives and address
    private  val _representativesList = MutableLiveData<List<Representative>>()
    val representativesList: LiveData<List<Representative>>
        get() = _representativesList

    private  val _addressList = MutableLiveData<List<String>>()
    val addressList: LiveData<List<String>>
        get() = _addressList

    //TODO: Create val and functions to populate live data for upcoming elections from the API and saved elections from local database
    init {
       // defineFakeData()
    }
    private fun defineFakeData(){
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

    fun getRepresentativesFromApi(){

        coroutineScope.launch {
            try {
                val address = "georgia"
                CivicsApi.retrofitService.getRepresentatives(address)
                        .enqueue(object : retrofit2.Callback<RepresentativeResponse> {
                            override fun onResponse(call: Call<RepresentativeResponse>, response: Response<RepresentativeResponse>) {
                                Log.i("Download Success", response.body().toString())
                                val (offices, officials) = response.body()!!
                                _representativesList.value = offices.flatMap { office -> office.getRepresentatives(officials) }

                            }
                            override fun onFailure(call: Call<RepresentativeResponse>, t: Throwable) {
                                Log.i("Download Failure", t.message.toString())
                            }
                        })
            }catch (e: Exception) {
                Log.i("Download Failure", e.message.toString())
            }
        }
    }

    //TODO: Create function get address from geo location

    //TODO: Create function to get address from individual fields

}
