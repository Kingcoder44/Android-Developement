package com.example.a7minutesworkout

object Constants {
    fun defaultExercise() : ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val jumping_jack = ExerciseModel(1,
            "Jumping Jack",R.drawable.jumping_jack,
            false,false)
        exerciseList.add(jumping_jack)
        val plank = ExerciseModel(2, "Plank", R.drawable.plank, false, false)
        exerciseList.add(plank)

        val abCrunch = ExerciseModel(3, "Ab Crunch", R.drawable.ab_crunch, false, false)
        exerciseList.add(abCrunch)

        val lunge = ExerciseModel(4, "Lunge", R.drawable.lunge, false, false)
        exerciseList.add(lunge)

        val pushupAndRotation = ExerciseModel(5, "Push-up and Rotation", R.drawable.pushup_and_rotation, false, false)
        exerciseList.add(pushupAndRotation)

        val pushups = ExerciseModel(6, "Push-ups", R.drawable.pushups, false, false)
        exerciseList.add(pushups)

        val sidePlank = ExerciseModel(7, "Side Plank", R.drawable.side_plank, false, false)
        exerciseList.add(sidePlank)

        val squat = ExerciseModel(8, "Squat", R.drawable.squat, false, false)
        exerciseList.add(squat)

        val stepUp = ExerciseModel(9, "Step Up", R.drawable.stepup, false, false)
        exerciseList.add(stepUp)

        val tricepsDip = ExerciseModel(10, "Triceps Dip", R.drawable.triceps_dip, false, false)
        exerciseList.add(tricepsDip)

        val wallSit = ExerciseModel(11, "Wall Sit", R.drawable.wall_sit, false, false)
        exerciseList.add(wallSit)
        return exerciseList
    }
}