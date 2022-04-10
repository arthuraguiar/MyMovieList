package com.example.mymovieslist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieslist.R
import com.example.mymovieslist.databinding.MovieCardItemBinding
import com.example.mymovieslist.domain.model.Movie

class MoviesAdapter : ListAdapter<Movie, MoviesAdapter.MovieViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieCardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(private val binding: MovieCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                movieTitleTv.text = movie.title
                movieReleaseDateTv.text = getReleaseDate(movie.releaseDate)
                inflateImage(movie.posterUrl)
            }
        }

        private fun getReleaseDate(releaseDate: String): String {
            return if (releaseDate.isEmpty())
                binding.root.context.resources.getString(R.string.not_available_yet)
            else
                releaseDate
        }

        private fun inflateImage(imageUrl: String) {
            Glide.with(binding.bannerImageView)
                .load(imageUrl)
                .centerCrop()
                .error(R.drawable.icon_film)
                .into(binding.bannerImageView)
        }
    }

    class DiffCallBack() : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
    }


}