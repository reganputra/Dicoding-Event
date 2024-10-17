package com.example.dicodingevent.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingevent.R
import com.example.dicodingevent.databinding.FragmentSettingBinding
import com.google.android.material.switchmaterial.SwitchMaterial

class FragmentSetting : Fragment() {

    private lateinit var binding: FragmentSettingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val switchThemeDark = view.findViewById<SwitchMaterial>(R.id.switch_theme)
        val dataStore = SettingDataStore.getInstance(requireContext().dataStore)
        val viewModel = ViewModelProvider(this, SettingFactory(dataStore))[SettingViewModel::class.java]

        viewModel.getThemeSettings().observe(viewLifecycleOwner) { isDarkModeActive ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchThemeDark.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchThemeDark.isChecked = false

            }
        }
        switchThemeDark.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            viewModel.saveThemeSettings(isChecked)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

}