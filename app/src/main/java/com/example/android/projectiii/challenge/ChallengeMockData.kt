package com.example.android.projectiii.challenge

class ChallengeMockData {
    fun getChallengesList(): MutableList<Challenges> {
        val list = mutableListOf<Challenges>()

        val chal1 = Challenges("1", "Run 5km a day", "today is your last day", false, 25, false, "Try to run 5KM a day for the next 5 days, you can do this! So grab your running shoes and hit the track! A 5 kilometer run can burn around 400 Kcal. Remember to run on a soft surface to prevent impact injuries. Grass is a great option if you don't have access to a running track, but make sure the ground is even to prevent falls.", "running")
        val chal2 = Challenges("2", "Run 6km a day", "for 3 days", true, 30, false, "Run 6KM a day! I believe in you!", "running")
        val chal3 = Challenges("3", "Run 5km a day", "for 10 days", true, 50, false, "Run 5km a day", "running")
        val chal4 = Challenges("4", "Run 7km a day", "for 5 days", true, 90, false, "Run 7km a day", "running")
        val chal5 = Challenges("5", "Swim 250m a day", "for 3 days", true, 20, false, "Swim 250m a day", "swim")
        val chal6 = Challenges("6", "Don't smoke before 11AM", "9 days left", false, 90, false, "Don't smoke before 11AM", "smoke")
        val chal7 = Challenges("7", "Meditate for 10 minutes", "10 days left", false, 90, false, "Meditate for 10 minutes", "brain")
        val chal8 = Challenges("8", "Eat 3 pieces of fruit a day", "1 day left", false, 50, false, "Eat 3 pieces of fruit a day", "apple")

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