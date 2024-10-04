package com.example.dicodingevent.ui.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.adapter.UpcomingEventAdapter
import com.example.dicodingevent.databinding.FragmentUpcomingBinding

class UpcomingEventFragment : Fragment() {

    private lateinit var binding: FragmentUpcomingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvUpcoming.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvUpcoming.addItemDecoration(itemDecoration)

        val upcomingEventModel = ViewModelProvider(this)[UpcomingEventModel::class.java]

        upcomingEventModel.listEvents.observe(viewLifecycleOwner) { listEvents ->
            val adapter = UpcomingEventAdapter()
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

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}