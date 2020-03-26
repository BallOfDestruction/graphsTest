package com.catsoft.charts.di.module

import android.app.Application
import android.content.res.Resources
import androidx.core.os.ConfigurationCompat
import com.catsoft.charts.services.CalendarReadableUtil
import com.catsoft.charts.services.CurrentLocaleProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServicesModule {

    @Singleton
    @Provides
    fun provideLocaleProvider(app: Application): CurrentLocaleProvider =
        CurrentLocaleProvider(
            ConfigurationCompat.getLocales(
                Resources.getSystem().configuration
            )[0]
        )


    @Singleton
    @Provides
    fun provideCalendarReadable(currentLocaleProvider: CurrentLocaleProvider): CalendarReadableUtil =
        CalendarReadableUtil(currentLocaleProvider)
}