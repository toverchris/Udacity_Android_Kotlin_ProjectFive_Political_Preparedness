package com.example.android.politicalpreparedness.representative

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.politicalpreparedness.network.CivicsApi
import com.example.android.politicalpreparedness.network.models.Address
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

    private  val _address = MutableLiveData<Address>()
    val address: LiveData<Address>
        get() = _address

    fun getRepresentativesFromApi(address : String){
        coroutineScope.launch {
            try {
                CivicsApi.retrofitService.getRepresentatives(address)
                        .enqueue(object : retrofit2.Callback<RepresentativeResponse> {
                            override fun onResponse(call: Call<RepresentativeResponse>, response: Response<RepresentativeResponse>) {
                                Log.i("Download Success", response.body().toString())
                                if(response.body()!=null){
                                    val (offices, officials) = response.body()!!
                                    _representativesList.value = offices.flatMap { office -> office.getRepresentatives(officials) }
                                }
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
    fun getAddressFromGeoLocation(): String{
        var address: String = "georgia"

        return address
    }

    //TODO: Create function to get address from individual fields
    fun getAddressFromIndividualFields(): String{
        var decodedAddress : String = "georgia"

        return decodedAddress
    }

}
