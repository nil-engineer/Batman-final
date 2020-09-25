package com.test.batman

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.test.batman.Utilities.CheckNetwork
import com.test.batman.ViewModel.DetailViewModel
import com.test.batman.ViewModel.DetailViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: DetailViewModelFactory
    private lateinit var viewModel: DetailViewModel
    private lateinit var id: String
    private lateinit var checkNetwork: CheckNetwork

    //    private lateinit var videoDetail: MutableLiveData<>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        databinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        setContentView(R.layout.activity_detail)
        component.inject(this)
        setSupportActionBar(detail_toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        checkNetwork = CheckNetwork()

        val b = intent.extras
        id = b!!.getString("VIDEO_ID")!!
        viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
        if(checkNetwork.isOnline(this)) {
            viewModel.getDetailPage(this, id, application).observe(this, Observer {
//                detail_toolbar.title = it.Title
                type.text = it.Type
                year.text = it.Year
                detail_title.text = it.Title
                release_date.text = it.Released
                runtime.text = it.Runtime
                genre.text = it.Genre
                language.text = it.Language
                country.text = it.Country
                director.text = it.Director
                actors.text = it.Actors
                writer.text = it.Writer
                awards.text = it.Awards
                plot.text = it.Plot
                imdb_rating.text = it.imdbRating
                imdb_votes.text = it.imdbVotes
                production.text = it.Production
                Glide.with(this)
                    .load(it.Poster)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(detail_poster)
            })
        }
        else{
            viewModel.getDetailPageOffline(application, id)?.observe(this, Observer {
                type.text = it.Type
                year.text = it.Year
                detail_title.text = it.Title
                release_date.text = it.Released
                runtime.text = it.Runtime
                genre.text = it.Genre
                language.text = it.Language
                country.text = it.Country
                director.text = it.Director
                actors.text = it.Actors
                writer.text = it.Writer
                awards.text = it.Awards
                plot.text = it.Plot
                imdb_rating.text = it.imdbRating
                imdb_votes.text = it.imdbVotes
                production.text = it.Production
                Glide.with(this)
                    .load(it.Poster)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(detail_poster)
            })
        }


//        detail_pb.visibility = View.VISIBLE
        detail_pb.visibility = View.GONE

    }
}