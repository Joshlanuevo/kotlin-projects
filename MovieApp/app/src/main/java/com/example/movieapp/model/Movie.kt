package com.example.movieapp.model

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.movieapp.BR
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Movie : BaseObservable() {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @JvmField
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

    @get:Bindable
    @SerializedName("title")
    @Expose
    var title: String? = null
        set(title) {
            field = title
            notifyPropertyChanged(BR.title)
        }

    @get:Bindable
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null
        set(voteAverage) {
            field = voteAverage
            notifyPropertyChanged(BR.voteAverage)
        }

    companion object {
        @JvmStatic
        @BindingAdapter("posterPath")
        fun loadImg(imageView: ImageView, imageUrl: String) {
            // Base Url: "https://image.tmdb.org/t/p/w500/"
            val imagePath = "https://image.tmdb.org/t/p/w500/$imageUrl"

            Glide.with(imageView.context)
                .load(imagePath)
                .into(imageView)
        }
    }
}