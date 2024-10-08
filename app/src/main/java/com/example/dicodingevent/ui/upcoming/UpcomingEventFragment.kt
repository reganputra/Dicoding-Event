package com.example.dicodingevent.ui.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.adapter.EventAdapter
import com.example.dicodingevent.databinding.FragmentUpcomingBinding
import com.example.dicodingevent.ui.detail.DetailViewModel

class UpcomingEventFragment : Fragment() {

    private lateinit var binding: FragmentUpcomingBinding
    private val upcomingEventModel: UpcomingEventModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvUpcoming.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvUpcoming.addItemDecoration(itemDecoration)


        upcomingEventModel.listEvents.observe(viewLifecycleOwner) { listEvents ->
            val adapter = EventAdapter()
            adapter.submitList(listEvents)
            binding.rvUpcoming.adapter = adapter
        }

        upcomingEventModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbLoading.visibility = View.VISIBLE
        } else {
            binding.pbLoading.visibility = View.GONE
        }
    }
}