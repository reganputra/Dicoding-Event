package com.example.dicodingevent.ui.finished

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingevent.databinding.FragmentFinishedBinding

class FinishedEventFragment : Fragment() {

   private lateinit var binding: FragmentFinishedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFinishedBinding.inflate(inflater, container, false)
        return binding.root

    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }
}