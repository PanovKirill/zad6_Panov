package com.example.criminalintent

import android.app.Application

class CriminalIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }

}

private fun CrimeRepository.Companion.initialize(criminalIntentApplication: Application) {
    TODO("Not yet implemented")
}
