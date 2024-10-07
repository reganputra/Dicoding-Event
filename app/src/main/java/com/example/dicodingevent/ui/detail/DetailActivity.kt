package com.example.dicodingevent.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat

import androidx.lifecycle.ViewModelProvider
import com.adapter.UpcomingEventAdapter.Companion.EXTRA_EVENT
import com.bumptech.glide.Glide

import com.example.dicodingevent.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        val detailId = intent.getIntExtra(EXTRA_EVENT, 0)
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
                binding.tvQuota.text = detailEvent.event.quota.toString()

                binding.tvDescription.text = HtmlCompat.fromHtml(
                    detailEvent.event.description,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )

                binding.btnAction.setOnClickListener {
                    val redirect = Intent.createChooser(Intent().apply {
                        action = Intent.ACTION_VIEW
                        data = Uri.parse(detailEvent.event.link)
                    }, null)
                    startActivity(redirect)
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
}