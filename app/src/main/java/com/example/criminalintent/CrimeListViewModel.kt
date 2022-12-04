package com.example.criminalintent

import androidx.lifecycle.ViewModel

class CrimeListViewModel : ViewModel() {
    val crimes = mutableListOf<Crime>()

    private val crimeRepository = CrimeRepository.get()

    val crimeListViewData = crimeRepository.getCrimes()

    fun addCrime(crime:Crime){
        crimeRepository.addCrime(crime)
    }

}