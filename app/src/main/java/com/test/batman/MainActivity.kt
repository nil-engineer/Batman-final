package com.test.batman

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.batman.DaggerModule.AppModule
import com.test.batman.Utilities.CheckNetwork
import com.test.batman.model.SearchResponseModel
import com.test.batman.ViewModel.MainViewModel
import com.test.batman.ViewModel.MainViewModelFactory
import com.test.batman.model.SearchItemModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var glm: GridLayoutManager? = null
    private lateinit var videoListAdapter: VideoListAdapter

//    @Inject
//    lateinit var api: BatmanAPI

    @Inject
    lateinit var factory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var videoList: List<SearchItemModel>
    private lateinit var checkNetwork: CheckNetwork

//    @Inject
//    val checkNetwork: CheckNetwork? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component = DaggerAppComponent
            .builder()
            .appModule(AppModule(this, application))
            .build()
        component.inject(this)

        setSupportActionBar(main_toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)


        main_pb.visibility = VISIBLE
        videoList = ArrayList()
        checkNetwork = CheckNetwork()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        if(checkNetwork.isOnline(this)){
            viewModel.getMainPage(this, application).observe(this, Observer {
                videoListAdapter.setVideos(it)
                videoList = it
                Log.d("title", it.get(0).Title)
            })
        }
        else{
            viewModel.getMainPageOffline(application)?.observe(this, Observer {
                videoListAdapter.setVideos(it)
                videoList = it
                Log.d("title", it.get(0).Title)
            })
        }


        glm = GridLayoutManager(this, 2)
        main_rv.layoutManager = glm
        main_rv.setHasFixedSize(true)
        videoListAdapter = VideoListAdapter(videoList)
        main_rv.adapter = videoListAdapter
        main_pb.visibility = GONE

    }
}