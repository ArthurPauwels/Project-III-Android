package com.example.android.projectiii.Level

import com.example.android.projectiii.Challenge.Challenges

class LevelMockData {
    fun getLevelList(): MutableList<Levels> {
        val list = mutableListOf<Levels>()


        val chal = Challenges("id", "Label", "description")
        val chal2 =
            Challenges("id2", "Label2", "description2")
        val chal3 =
            Challenges("id3", "Label3", "description3")
        val chalList = ArrayList<Challenges>()

        chalList.add(chal)
        chalList.add(chal2)
        chalList.add(chal3)
        chalList.add(chal)

        list.add(Levels("1", chalList))
        list.add(Levels("2", chalList))
        list.add(Levels("3", chalList))
        return list
    }
}