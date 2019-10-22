package com.example.android.projectiii

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android.projectiii.databinding.ExpertContactViewBinding

class ExpertContactView: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ExpertContactViewBinding= DataBindingUtil.setContentView(this, R.layout.expert_contact_view)

        binding.textEpertEmail.text = "expertmail@expert.com"
        binding.textExpertPhone.text = "+00 00 00 00 00 00"
        binding.textExpertName.text = "Mr Expert"
    }
}