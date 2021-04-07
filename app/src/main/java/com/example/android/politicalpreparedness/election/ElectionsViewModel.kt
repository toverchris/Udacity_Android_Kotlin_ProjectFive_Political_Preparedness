package com.example.android.politicalpreparedness.election

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.politicalpreparedness.database.ElectionDatabase
import com.example.android.politicalpreparedness.network.CivicsApi
import com.example.android.politicalpreparedness.network.models.Division
import com.example.android.politicalpreparedness.network.models.Election
import com.example.android.politicalpreparedness.network.models.ElectionResponse
import kotlinx.coroutines.*
import okhttp3.Callback
import okhttp3.Dispatcher
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.util.*


//TODO: Construct ViewModel and provide election datasource

class ElectionsViewModel: ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private  val _upcomingElectionsList = MutableLiveData<List<Election>>()
    val upcomingElectionsList: LiveData<List<Election>>
        get() = _upcomingElectionsList

    private  val _savedElectionsList = MutableLiveData<List<Election>>()
    val savedElectionsList: LiveData<List<Election>>
        get() = _savedElectionsList

    private  val _navigateToSelectedElection = MutableLiveData<Election>()
    val navigateToSelectedElection: LiveData<Election>
        get() = _navigateToSelectedElection

    init {
        getElectionsDataFromApi()
        getElectionsDataFromDatabase()
    }

    private fun getElectionsDataFromApi(){
        coroutineScope.launch {
            try {
                CivicsApi.retrofitService.getElections()
                        .enqueue(object : retrofit2.Callback<ElectionResponse> {
                            override fun onResponse(call: Call<ElectionResponse>, response: Response<ElectionResponse>) {
                                Log.i("Download Success", response.body().toString())
                                _upcomingElectionsList.value = response.body()!!.elections
                            }
                            override fun onFailure(call: Call<ElectionResponse>, t: Throwable) {
                                Log.i("Download Failure", t.message.toString())
                            }

                        })
            }catch (e: Exception) {
                Log.i("Download Failure", e.message.toString())
            }
        }
    }

    private fun getElectionsDataFromDatabase(){
        _savedElectionsList.value = listOf(
                Election(1, "VIP Test 1", Date(2022-1900,1,24), Division("123","Sagres","Portugal")),
                Election(2, "Test 2", Date(2022-1900,2,24), Division("222","Tonel","Portugal")),
                Election(3, "Test 3", Date(2022-1900,3,24), Division("333","Beliche","Portugal")),
                Election(4, "Test 4", Date(2022-1900,4,24), Division("444","Mareta","Portugal") ),
                Election(5, "Test 5", Date(2022-1900,5,24), Division("24442","Marthinal","Portugal"))
                )
    }

    fun displayElection(election: Election) {
        _navigateToSelectedElection.value = election
    }
    fun displayElectionDetailsComplete() {
        _navigateToSelectedElection.value = null
    }

}