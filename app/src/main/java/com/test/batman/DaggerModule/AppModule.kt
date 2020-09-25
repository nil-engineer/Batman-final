package com.test.batman.DaggerModule

import android.app.Application
import android.content.Context
import com.test.batman.BatmanAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {
    private val context: Context
    private val httpClient = OkHttpClient.Builder()
//    var client: OkHttpClient? = null

    var mApplication: Application? = null

    fun AppModule(application: Application?) {
        mApplication = application
    }

    private val BASE_URL = "http://www.omdbapi.com/"
    constructor(context: Context, application: Application?) {
        this.context = context
        this.mApplication = application
    }

    @Provides //scope is not necessary for parameters stored within the module
    fun context(): Context? {
        return context
    }

    @Provides
    fun application(): Application?{
        return mApplication
    }

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): BatmanAPI {
        return retrofit.create(BatmanAPI::class.java)
    }

//    @Provides
//    @Singleton
//    fun providesCheckNetwork(context: Context): CheckNetwork{
//        return CheckNetwork()
//    }

}