package com.example.dicodingevent.ui.finished

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
import com.example.dicodingevent.databinding.FragmentFinishedBinding

class FinishedEventFragment : Fragment() {

   private lateinit var binding: FragmentFinishedBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvFinished.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvFinished.addItemDecoration(itemDecoration)

        val finishedEventModel = ViewModelProvider(this)[FinishedEventModel::class.java]

        finishedEventModel.listEvents.observe(viewLifecycleOwner) { listEvents ->
            val adapter = UpcomingEventAdapter()
            adapter.submitList(listEvents)
            binding.rvFinished.adapter = adapter
        }

        finishedEventModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFinishedBinding.inflate(inflater, container, false)
        return binding.root

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }
}