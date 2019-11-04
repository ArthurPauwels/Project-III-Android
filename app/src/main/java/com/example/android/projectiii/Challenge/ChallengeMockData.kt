package com.example.android.projectiii.Challenge

class ChallengeMockData {
    fun getChallengesList(): MutableList<Challenges> {
        val list = mutableListOf<Challenges>()

        val chal1 = Challenges("1", "Run 5km a day", "today is your last day", false, 25, false, "A long description")
        val chal2 = Challenges("2", "Run 6km a day", "for 3 days", true, 30, false, "A long description")
        val chal3 = Challenges("3", "Run 5km a day", "for 10 days", true, 50, false, "A long description")
        val chal4 = Challenges("4", "Run 7km a day", "for 5 days", true, 90, false, "A long description")
        val chal5 = Challenges("5", "Swim 250m a day", "for 3 days", true, 20, false, "A long description")
        val chal6 = Challenges("6", "Don't smoke before 11AM", "9 days left", false, 90, true, "A long description")
        val chal7 = Challenges("7", "Meditate for 10 minutes", "10 days left", false, 90, false, "A long description")
        val chal8 = Challenges("8", "Eat 3 pieces of fruit a day", "1 day left", false, 50, false, "A long description")

        list.add(chal1)
        list.add(chal2)
        list.add(chal3)
        list.add(chal4)
        list.add(chal5)
        list.add(chal6)
        list.add(chal7)
        list.add(chal8)
        return list
    }
}