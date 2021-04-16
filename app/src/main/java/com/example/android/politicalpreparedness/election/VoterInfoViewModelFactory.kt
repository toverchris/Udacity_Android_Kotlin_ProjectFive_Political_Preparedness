package com.example.android.politicalpreparedness.election

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.politicalpreparedness.database.ElectionDao
import kotlinx.coroutines.InternalCoroutinesApi

//TODO: Create Factory to generate VoterInfoViewModel with provided election datasource
class VoterInfoViewModelFactory(val application: Application): ViewModelProvider.Factory {

    @InternalCoroutinesApi
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VoterInfoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VoterInfoViewModel(application) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }

}

