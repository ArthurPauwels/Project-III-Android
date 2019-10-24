package com.example.android.projectiii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var challengeList: ChallengeList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottom_nav)

        bottomNavigation.setOnNavigationItemSelectedListener { item ->

            when (item.itemId){

                R.id.all -> {
                    challengeList = ChallengeList()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, challengeList)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

            }
            true
        }
    }
}
