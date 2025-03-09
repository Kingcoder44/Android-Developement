package com.example.bookbeacon.domain.model

import androidx.compose.foundation.MutatePriority

data class Task(

    val title :String,
    val desc : String,
    val dueDate : Long,
    val priority: Int,
    val relatedToSubject : String,
    val isComplete : Boolean,
    val taskId : Int,
    val taskSubjectId : Int
)
