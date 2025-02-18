package com.example.myapplication

object Constants {

    const val USER_NAME :String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORCT_ANS : String = "correct_answer"
    fun getQuestions() : ArrayList<question>{
        val questionnoList = ArrayList  <question>()

        val q1 = question(
            1,
            "What country does this flag belong to?",
            R.drawable.a,
            "India",
            "Kuwait",
            "USA",
            "Ecuador",
            1
        )
        questionnoList.add(q1)

        val q2 = question(
            2,
            "Which country's flag is this?",
            R.drawable.b,
            "Germany",
            "Afghanistan",
            "Italy",
            "Spain",
            2
        )
        questionnoList.add(q2)

        val q3 = question(
            3,
            "Identify the country based on this flag.",
            R.drawable.c,
            "Canada",
            "Andorra",
            "Australia",
            "Japan",
            1
        )
        questionnoList.add(q3)

        val q4 = question(
            4,
            "This flag belongs to which country?",
            R.drawable.d,
            "South Korea",
            "Barbados",
            "Vietnam",
            "Thailand",
            2
        )
        questionnoList.add(q4)

        val q5 = question(
            5,
            "Which nation is represented by this flag?",
            R.drawable.e,
            "Argentina",
            "Mexico",
            "Chile",
            "Bosnia and Herzegovina",
            4
        )
        questionnoList.add(q5)

        val q6 = question(
            6,
            "Guess the country from its flag.",
            R.drawable.f,
            "Russia",
            "Ukraine",
            "Cameroon",
            "Czech Republic",
            3
        )
        questionnoList.add(q6)

        val q7 = question(
            7,
            "This is the flag of which country?",
            R.drawable.g,
            "Columbia",
            "Norway",
            "Denmark",
            "Finland",
            1
        )
        questionnoList.add(q7)

        val q8 = question(
            8,
            "Which country's national flag is shown?",
            R.drawable.h,
            "Saudi Arabia",
            "Ireland",
            "Qatar",
            "Oman",
            2
        )
        questionnoList.add(q8)

        val q9 = question(
            9,
            "Identify the country by its flag.",
            R.drawable.i,
            "Portugal",
            "Belgium",
            "Germany",
            "Greece",
            3
        )
        questionnoList.add(q9)



        return questionnoList
    }
}