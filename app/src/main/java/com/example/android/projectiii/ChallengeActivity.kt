package com.example.android.projectiii

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android.projectiii.databinding.ActivityChallengeBinding

class ChallengeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityChallengeBinding = DataBindingUtil.setContentView(this, R.layout.activity_challenge)

        binding.textChallengeActivity.text = intent.getStringExtra("label")
        binding.descriptionChallengeActivity.text = intent.getStringExtra("description")
    }
}