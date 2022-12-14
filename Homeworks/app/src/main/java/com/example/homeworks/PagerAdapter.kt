package com.example.homeworks

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.homeworks.databinding.PagerLayoutBinding
import com.example.homeworks.models.ImageModel
import com.example.homeworks.models.ImageStatus
import kotlinx.coroutines.*
import kotlin.math.ceil

class PagerAdapter(private val glide: RequestManager, private val scope: CoroutineScope) :
    RecyclerView.Adapter<PagerAdapter.ImageHolder>() {

    override fun getItemCount(): Int = Repository.images.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.pager_layout, parent, false)
        return ImageHolder(PagerLayoutBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(position)
    }

    inner class ImageHolder(private val binding: PagerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            binding.pb.visibility = View.VISIBLE
            binding.textView.text = "Image: " + (position + 1)
            binding.image.setImageResource(0)

            val model = Repository.images[position]
            scope.launch {
                if (model.status == ImageStatus.NOT_LOADED) {
                    preloadImage(model, position)
                }
                glide.load(model.url).centerCrop().into(binding.image)
                binding.pb.visibility = View.INVISIBLE
            }
        }
    }

    fun preloadAll(parts: Int = 3) {
        scope.launch {
            val length = Repository.images.size
            val n = ceil(length.toDouble() / parts).toInt()
            for (i in 0 until length step n) {
                val end = if (i + n > length) length else i + n
                launch { preloadImages(i, end - 1) }
                Log.e("launch part", "${i} ${end - 1}")
            }
        }
    }

    private suspend fun preloadImages(start: Int, end: Int) {
        for (i in start..end) {
            preloadImage(Repository.images[i], i)
        }
    }

    private suspend fun preloadImage(model: ImageModel, a: Int) = withContext(Dispatchers.IO) {
        if (model.status == ImageStatus.LOADED || model.status == ImageStatus.LOADING)
            return@withContext
        model.status = ImageStatus.LOADING
        Log.e("loading", (a + 1).toString())
        delay(3000)
        glide.load(model.url).preload()
        model.status = ImageStatus.LOADED
    }
}