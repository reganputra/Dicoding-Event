package com.example.dicodingevent.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.adapter.EventAdapter.Companion.EXTRA_EVENT
import com.bumptech.glide.Glide
import com.example.data.ViewModelFactory
import com.example.data.local.Event
import com.example.dicodingevent.R

import com.example.dicodingevent.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailBinding
    private var isFav = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(application)
        val detailViewModel: DetailViewModel by viewModels { factory }
        val detailId = intent.getIntExtra(EXTRA_EVENT, 0)

        detailViewModel.getFavEventByI(detailId.toString()).observe(this) { event ->
            isFav = event != null
            updateFavoriteButtonState(isFav)
        }

        detailViewModel.getDetailEvent(detailId)

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        detailViewModel.detailEvent.observe(this) { detailEvent ->
            if (detailEvent != null) {
                Glide.with(this)
                    .load(detailEvent.event.imageLogo)
                    .into(binding.ivDetailImage)
                binding.tvEventName.text = detailEvent.event.name
                binding.tvOwnerName.text = detailEvent.event.ownerName
                binding.tvBeginTime.text = detailEvent.event.beginTime
                binding.tvQuota.text =
                    "Sisa Kuota: ${detailEvent.event!!.quota - detailEvent.event!!.registrants}"

                binding.tvDescription.text = HtmlCompat.fromHtml(
                    detailEvent.event.description,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )

                binding.btnAction.setOnClickListener {
                    val openLink = Intent.createChooser(Intent().apply {
                        action = Intent.ACTION_VIEW
                        data = Uri.parse(detailEvent.event.link)
                    }, null)
                    startActivity(openLink)
                }

                binding.favBtn.setOnClickListener {
                    val events = Event(
                        id = detailEvent.event.id.toString(),
                        name = detailEvent.event.name,
                        description = detailEvent.event.description,
                        mediaCover = detailEvent.event.imageLogo

                    )
                    if (isFav){
                        detailViewModel.delete(events)
                    }else{
                        detailViewModel.insert(events)
                    }
                    isFav = !isFav
                    updateFavoriteButtonState(isFav)
                }
            }
        }

    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar3.visibility = View.VISIBLE
        } else {
            binding.progressBar3.visibility = View.GONE
        }
    }

    private fun updateFavoriteButtonState(isFav: Boolean) {
        if (isFav) {
            binding.favBtn.setImageResource(R.drawable.ic_fave_white) // assuming this icon for filled heart
        } else {
            binding.favBtn.setImageResource(R.drawable.ic_fav_border) // assuming this icon for empty heart
        }
    }
}