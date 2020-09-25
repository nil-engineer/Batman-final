package com.test.batman

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.test.batman.model.SearchItemModel
import kotlinx.android.synthetic.main.video_item.view.*

class VideoListAdapter(var videoList: List<SearchItemModel>): RecyclerView.Adapter<VideoListAdapter.VideoViewHolder>() {
    private var context: Context? = null
    private lateinit var searchItemModel: SearchItemModel
    class VideoViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
        val v: View = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false)
        context = parent.context
        return VideoViewHolder(v)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        searchItemModel = videoList.get(position)
        holder.view.title.text = searchItemModel.Title
        holder.view.type.text = searchItemModel.Type
        holder.view.year.text = searchItemModel.Year
        Glide.with(context)
            .load(searchItemModel.Poster)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(holder.view.poster)
        holder.view.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, DetailActivity:: class.java)
            intent.putExtra("VIDEO_ID", videoList.get(position).imdbID)
            Log.d("vid", videoList.get(position).imdbID)
            context?.startActivity(intent)
        })
//        holder.view.searchItemModel = videoList[position]
//        holder.view.listener = this
        Log.d("bindview", videoList.size.toString())
    }

//    override fun onVideoClicked(v: View) {
//        val id = v.videoId.text
//        val intent = Intent(context, DetailActivity:: class.java)
//        intent.putExtra("VIDEO_ID", id)
//        Log.d("vid", id.toString())
//        context?.startActivity(intent)
//    }

    fun setVideos(videos: List<SearchItemModel>) {
        videoList = videos
        notifyDataSetChanged()
    }

}