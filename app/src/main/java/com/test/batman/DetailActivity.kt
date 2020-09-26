package com.test.batman

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.test.batman.Utilities.CheckNetwork
import com.test.batman.ViewModel.DetailViewModel
import com.test.batman.ViewModel.DetailViewModelFactory
import com.test.batman.model.VideoDetailModel
import kotlinx.android.synthetic.main.activity_detail.*

import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: DetailViewModelFactory
    private lateinit var viewModel: DetailViewModel
    private lateinit var id: String
    private lateinit var checkNetwork: CheckNetwork

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
                    if(it != null) {
                        showData(it)
                    }
                    else{
                        AlertDialog.Builder(this)
                            .setMessage("Video Detail Doesn't exist")
                            .setPositiveButton("OK"){dialog, which ->
                                finish()
                            }
                            .show()
                    }
                })
            }
            else{
                viewModel.getDetailPageOffline(application, id)?.observe(this, Observer {
                    if(it != null) {
                        showData(it)

                    }
                    else{
                        AlertDialog.Builder(this)
                            .setMessage("Video Detail Doesn't exist")
                            .setPositiveButton("OK"){dialog, which ->
                                finish()
                            }
                            .show()
                    }
                })
            }

//        detail_pb.visibility = View.VISIBLE
        detail_pb.visibility = View.GONE

    }

    private fun showData(videoDetailModel: VideoDetailModel){

        type.text = videoDetailModel.Type
        year.text = videoDetailModel.Year
        detail_title.text = videoDetailModel.Title
        release_date.text = videoDetailModel.Released
        runtime.text = videoDetailModel.Runtime
        genre.text = videoDetailModel.Genre
        language.text = videoDetailModel.Language
        country.text = videoDetailModel.Country
        director.text = videoDetailModel.Director
        actors.text = videoDetailModel.Actors
        writer.text = videoDetailModel.Writer
        awards.text = videoDetailModel.Awards
        plot.text = videoDetailModel.Plot
        imdb_rating.text = videoDetailModel.imdbRating
        imdb_votes.text = videoDetailModel.imdbVotes
        production.text = videoDetailModel.Production
        Glide.with(this)
            .load(videoDetailModel.Poster)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(detail_poster)
    }
}