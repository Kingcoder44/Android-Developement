package com.example.lazyrc_impli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lazyrc_impli.ui.theme.LazyRC_ImpliTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyRC_ImpliTheme {
                CourseList()
            }
        }
    }
}

data class CourseDetail(
    val name: String,
    val imageRes: Int,
    val description: String
)

@Composable
fun CourseList() {
    val courses = listOf(
        CourseDetail("Java", R.drawable.ic_launcher_background, "Java course for beginners"),
        CourseDetail("Python", R.drawable.ic_launcher_background, "Python course for beginners"),
        CourseDetail("Kotlin", R.drawable.ic_launcher_background, "Kotlin course for Android development"),
                CourseDetail("Java", R.drawable.ic_launcher_background, "Java course for beginners"),
    CourseDetail("Python", R.drawable.ic_launcher_background, "Python course for beginners"),
    CourseDetail("Kotlin", R.drawable.ic_launcher_background, "Kotlin course for Android development") , CourseDetail("Java", R.drawable.ic_launcher_background, "Java course for beginners"),
    CourseDetail("Python", R.drawable.ic_launcher_background, "Python course for beginners"),
        CourseDetail("Kotlin", R.drawable.ic_launcher_background, "Kotlin course for Android development") , CourseDetail("Java", R.drawable.ic_launcher_background, "Java course for beginners"),
        CourseDetail("Python", R.drawable.ic_launcher_background, "Python course for beginners"),    CourseDetail("Kotlin", R.drawable.ic_launcher_background, "Kotlin course for Android development") , CourseDetail("Java", R.drawable.ic_launcher_background, "Java course for beginners"),
        CourseDetail("Python", R.drawable.ic_launcher_background, "Python course for beginners"),    CourseDetail("Kotlin", R.drawable.ic_launcher_background, "Kotlin course for Android development") , CourseDetail("Java", R.drawable.ic_launcher_background, "Java course for beginners"),
        CourseDetail("Python", R.drawable.ic_launcher_background, "Python course for beginners"),    CourseDetail("Kotlin", R.drawable.ic_launcher_background, "Kotlin course for Android development") , CourseDetail("Java", R.drawable.ic_launcher_background, "Java course for beginners"),
        CourseDetail("Python", R.drawable.ic_launcher_background, "Python course for beginners"),
        CourseDetail("Kotlin", R.drawable.ic_launcher_background, "Kotlin course for Android development")
    // Add more courses as needed
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyColumn {
            itemsIndexed(courses) { index, course ->
                CourseItem(course.name, course.imageRes, course.description)
            }
        }
    }
}

@Composable
fun CourseItem(name: String, imageRes: Int, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = description,
            modifier = Modifier
                .size(60.dp)
                .padding(6.dp)
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(text = name, color = Color.White, fontWeight = FontWeight.Bold)
            Text(text = description, color = Color.White, modifier = Modifier.padding(top = 4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseListPreview() {
    LazyRC_ImpliTheme {
        CourseList()
    }
}
