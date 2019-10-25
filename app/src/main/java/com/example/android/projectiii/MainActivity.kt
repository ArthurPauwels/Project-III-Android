package com.example.android.projectiii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var challengeList: ChallengeListFragment
    lateinit var expertList: ExpertListFragment
    lateinit var completedChallenges: CompletedChallengeFragment
    private val fragmentManager: FragmentManager = supportFragmentManager
    lateinit var active: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottom_nav)
        challengeList = ChallengeListFragment()
        expertList = ExpertListFragment()
        completedChallenges = CompletedChallengeFragment()

        fragmentManager.beginTransaction().add(R.id.fragment_container, expertList, "3").hide(expertList).commit()
        fragmentManager.beginTransaction().add(R.id.fragment_container, challengeList, "2").hide(challengeList).commit()
        fragmentManager.beginTransaction().add(R.id.fragment_container, completedChallenges, "1").commit()

        active = completedChallenges

        bottomNavigation.setOnNavigationItemSelectedListener { item ->

            when (item.itemId){
                R.id.completed -> {
                    fragmentManager.beginTransaction().hide(active).show(completedChallenges).commit()
                    active = completedChallenges
                }

                R.id.all -> {
                    fragmentManager.beginTransaction().hide(active).show(challengeList).commit()
                    active = challengeList
                }

                R.id.expert -> {
                    fragmentManager.beginTransaction().hide(active).show(expertList).commit()
                    active = expertList
                }
            }
            true
        }
    }
}
