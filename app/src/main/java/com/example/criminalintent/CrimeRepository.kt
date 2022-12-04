package com.example.criminalintent

import android.arch.lifecycle.LiveData
import android.content.Context
import androidx.room.Room
import androidx.room.Update
import com.example.criminalintent.database.CrimeDatabase
import java.util.*
import java.util.concurrent.Executors

class CrimeRepository private constructor(context: Context) {
    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        Companion.DATABASE_NAME
    )
        .build()



    private val crimeDao = database.crimeDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()

    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)


    fun UpdateCrime(crime: Crime){
        executor.execute{
            crimeDao.updateCrime(crime)
        }
    }
    fun addCrime(crime: Crime){
        executor.execute{
            crimeDao.addCrime(crime)
        }
    }


    companion object {
        private var INSTANCE: CrimeRepository? = null
        private const val DATABASE_NAME = "crimedatabase"
    }
    fun initialize(context: Context) {
        if (INSTANCE == null) {
            INSTANCE = CrimeRepository(context)
        }
    }
    fun get(): CrimeRepository {
        return INSTANCE?: throw IllegalStateException("CrimeRepository must be initialized")
    }
}