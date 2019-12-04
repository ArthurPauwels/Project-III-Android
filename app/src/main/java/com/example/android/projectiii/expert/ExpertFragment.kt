package com.example.android.projectiii.expert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.projectiii.R
import com.example.android.projectiii.databinding.FragmentExpertBinding

class ExpertFragment:Fragment() {
    private lateinit var binding: FragmentExpertBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = ExpertFragmentArgs.fromBundle(arguments!!)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_expert,
            container,
            false
        )

        binding.textExpertName.text = args.name
        binding.textExpertProfession.text = args.profession
        binding.textEpertEmail.text = args.email

        return binding.root
    }
}