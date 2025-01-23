package com.vancoding.notetakingapp.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: Application) {

    @Provides
    fun provideApplication() = application
}