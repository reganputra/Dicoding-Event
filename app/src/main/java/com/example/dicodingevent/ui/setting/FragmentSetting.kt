package com.example.dicodingevent.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingevent.R
import com.example.dicodingevent.databinding.FragmentSettingBinding
import com.google.android.material.switchmaterial.SwitchMaterial

class FragmentSetting : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    private val viewModel: SettingViewModel by viewModels {
        SettingFactory(SettingDataStore.getInstance(requireContext().dataStore))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val switchThemeDark = view.findViewById<SwitchMaterial>(R.id.switch_theme)

        viewModel.getThemeSettings().observe(viewLifecycleOwner) { isDarkModeActive ->
            switchThemeDark.isChecked = isDarkModeActive
            AppCompatDelegate.setDefaultNightMode(
                if (isDarkModeActive) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }
        switchThemeDark.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            viewModel.saveThemeSettings(isChecked)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

}