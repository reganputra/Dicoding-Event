package com.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.ListEventsItem
import com.example.dicodingevent.databinding.EventRowBinding
import com.example.dicodingevent.ui.detail.DetailActivity

class UpcomingEventAdapter: ListAdapter<ListEventsItem, UpcomingEventAdapter.ViewHolder>(
    DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):ViewHolder {
        val binding = EventRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

    class ViewHolder(private val binding: EventRowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(event: ListEventsItem){
            Glide.with(binding.root)
                .load(event.imageLogo)
                .into(binding.ivEventImage)
                binding.tvDetailName.text = event.name

            binding.ivEventImage.setOnClickListener {
                val detail = binding.root.context
                val toDetail = Intent(detail, DetailActivity::class.java)
                toDetail.putExtra(EXTRA_EVENT, event.id)
                detail.startActivity(toDetail)

            }
        }
    }

    companion object {

        const val EXTRA_EVENT = "extra_event"
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListEventsItem>() {
            override fun areItemsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}