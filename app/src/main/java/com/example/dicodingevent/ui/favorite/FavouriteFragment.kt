package com.example.dicodingevent.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.adapter.EventAdapter
import com.example.data.ViewModelFactory
import com.example.data.response.ListEventsItem
import com.example.dicodingevent.R
import com.example.dicodingevent.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFavEvent.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvFavEvent.addItemDecoration(itemDecoration)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity().application)
        val viewModel: FavourtieViewModel by viewModels {
            factory
        }
        val adapter = EventAdapter()

        viewModel.getAllFavEvent().observe(viewLifecycleOwner) { users ->
            val item = arrayListOf<ListEventsItem>()
            users.map {
                val event = ListEventsItem(id = it.id.toInt(), name = it.name, imageLogo = it.mediaCover)
                item.add(event)
                adapter.submitList(item)
            }
            adapter.submitList(item)
            }
        binding.rvFavEvent.adapter = adapter
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

}