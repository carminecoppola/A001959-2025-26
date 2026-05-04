package com.example.l12fragmentsdemo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.l12fragmentsdemo.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        binding.btnGo.setOnClickListener {

            val name = binding.etName.text.toString()

            val fragment = DetailFragment.newInstance(name)

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null   // IMPORTANTISSIMO
    }
}