package com.test.batman

import android.app.Application
import com.test.batman.DaggerModule.AppModule
import com.test.batman.DaggerModule.RepositoryModule
import com.test.batman.DaggerModule.ViewModelModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
    fun application(): Application?

}