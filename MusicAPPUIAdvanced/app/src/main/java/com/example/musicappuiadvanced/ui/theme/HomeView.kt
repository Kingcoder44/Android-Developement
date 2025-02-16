package com.example.musicappuiadvanced.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.musicappuiadvanced.R

//to setup home screen view
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(){
    val categories = listOf("Romance","Hits","Lo-Fi","Editor's Pick","Popular Albums","WorkOut")
    val grouped = listOf<String>("New Releases","Release","Favourites","Top Rated").groupBy { it[0] }
    LazyColumn {
        grouped.forEach{
            stickyHeader {//to keep heading fixed
                Text(text = it.value[0], modifier = Modifier.padding(16.dp))
                LazyRow {
                    items(categories){
                            item->
                        HomeItem(item, drawable = R.drawable.round_music_video_24)
                    }
                }
            }
        }
    }
}
@Composable
fun HomeItem(cat :String,drawable:Int){
    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(200.dp),
        border = BorderStroke(3.dp, color = Color.DarkGray)
    )
 {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(cat,fontWeight = FontWeight.Bold)
        Image(painter = painterResource(drawable), contentDescription = cat)
    }
}
}