package com.example.dicodingevent.ui.finished

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.adapter.EventAdapter
import com.example.dicodingevent.databinding.FragmentFinishedBinding

class FinishedEventFragment : Fragment() {

   private lateinit var binding: FragmentFinishedBinding
    private val finishedEventModel: FinishedEventModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvFinished.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvFinished.addItemDecoration(itemDecoration)

        finishedEventModel.listEvents.observe(viewLifecycleOwner) { listEvents ->
            val adapter = EventAdapter()
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

}