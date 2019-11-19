package com.example.android.projectiii.expert

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android.projectiii.R
import com.example.android.projectiii.databinding.ExpertContactViewBinding

class ExpertContactView: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ExpertContactViewBinding= DataBindingUtil.setContentView(this,
            R.layout.expert_contact_view
        )

        binding.textEpertEmail.text = intent.getStringExtra("email")
        binding.textExpertPhone.text = intent.getStringExtra("phone")
        binding.textExpertName.text = intent.getStringExtra("name")
    }
}