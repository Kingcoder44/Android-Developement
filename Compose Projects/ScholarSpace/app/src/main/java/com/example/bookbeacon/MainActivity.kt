package com.example.bookbeacon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookbeacon.domain.model.Subject
import com.example.bookbeacon.domain.model.Task
import com.example.bookbeacon.ui.presentation.Subject.SubjectScreen
import com.example.bookbeacon.ui.presentation.dashboard.DaboardScreen
import com.example.bookbeacon.ui.presentation.session.SessionScreen
import com.example.bookbeacon.ui.presentation.task.TaskScreen
import com.example.bookbeacon.ui.presentation.theme.StudySmartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudySmartTheme(darkTheme = isSystemInDarkTheme()) {
          SessionScreen()
            }

            }
        }
    }

val subjects = listOf(
    Subject(name = "English", goalHours = 10f, colors = Subject.subjectCardColors[0],0),
    Subject(name = "Physics", goalHours = 10f, colors = Subject.subjectCardColors[1],0),
    Subject(name = "Maths", goalHours = 10f, colors = Subject.subjectCardColors[2],0),
    Subject(name = "Geology", goalHours = 10f, colors = Subject.subjectCardColors[3],0),
    Subject(name = "Fine Arts", goalHours = 10f, colors = Subject.subjectCardColors[4],0)
)
val tasks = listOf(
    Task(title = "Prepare Notes", desc = "", dueDate = 0L, priority = 0, relatedToSubject = "",
        isComplete = false,0,1),
    Task(title = "Do Homework", desc = "", dueDate = 0L, priority = 1, relatedToSubject = "",
        isComplete = true,0,1),
    Task(title = "Play Game", desc = "", dueDate = 0L, priority = 0, relatedToSubject = "",
        isComplete = true,0,1),
    Task(title = "Go Coaching", desc = "", dueDate = 0L, priority = 2, relatedToSubject = "",
        isComplete = false,0,1),
    Task(title = "Assignment", desc = "", dueDate = 0L, priority = 1, relatedToSubject = "",
        isComplete = false,0,1),
)
