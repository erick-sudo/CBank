package com.phanes.phanesbank

import android.app.Application
import com.phanes.phanesbank.repository.PreferencesRepository

class PhanesBankApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        PreferencesRepository.initialize(this)
    }
}