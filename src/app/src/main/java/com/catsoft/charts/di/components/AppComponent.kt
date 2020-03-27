package com.catsoft.charts.di.components

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton
import android.app.Application
import com.catsoft.charts.CustomApplication
import com.catsoft.charts.di.module.*
import com.catsoft.charts.mapper.charts.PointsMapperModule
import dagger.BindsInstance

@Singleton
@Component(modules = [
    FragmentBuilderModule::class,
    AndroidInjectionModule::class,
    RepoModule::class,
    PointsMapperModule::class,
    MainActivityModule::class,
    ViewModelModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app : CustomApplication)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
