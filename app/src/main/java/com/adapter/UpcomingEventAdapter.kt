package com.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.ListEventsItem
import com.example.dicodingevent.databinding.EventRowBinding

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
                .load(event.mediaCover)
                .into(binding.ivEventImage)
                binding.tvDetailName.text = event.name
        }
    }

    companion object {

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