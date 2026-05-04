package com.example.l12fragmentsdemo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.l12fragmentsdemo.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_NAME = "arg_name"

        fun newInstance(name: String): DetailFragment {
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putString(ARG_NAME, name)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDetailBinding.bind(view)

        val name = arguments?.getString(ARG_NAME) ?: "Unknown"

        binding.tvWelcome.text = "Hello $name"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}