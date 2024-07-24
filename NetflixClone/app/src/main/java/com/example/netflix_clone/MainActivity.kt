package com.example.netflix_clone

import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.sp
import com.example.netflix_clone.ui.theme.Netflix_CloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)//to remeember the state of scroll and continue from there we use verticalScroll()
                .verticalScroll(rememberScrollState())){
                    TopAppSection()
                CategoryTab()
                FeaturedMovieSection()
                addMovieList(movieType = "Watch It Again")
                addMovieList(movieType = "New Releases")
                addMovieList(movieType = "Recommended")
                addMovieList(movieType = "Drama")
                addMovieList(movieType = "Action")

            }
        }
    }

    @Composable
    fun TopAppSection(){
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(id = R.drawable.img),
                contentDescription ="Netflix Logo",
                modifier=Modifier.size(60.dp) )
            Row (){
                Image(painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search Icon",
                    modifier= Modifier
                        .padding(end = 12.dp)
                        .size(32.dp))
                Image(painter = painterResource(id = R.drawable.ic_profille),
                    contentDescription ="Netflix Logo",
                    modifier= Modifier
                        .padding(end = 6.dp)
                        .size(32.dp))
        }
    }
}
    @Composable

    fun CategoryTab()
    {
        Row (modifier= Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(horizontal = 30.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Text(text = "TV Shows",color=Color.LightGray,fontSize = 20.sp)

            Text(text = "Movies",color=Color.LightGray, fontSize = 20.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(text = "Categories",color=Color.LightGray,fontSize = 20.sp)
                Image(painter = painterResource(id = R.drawable.ic_drop), contentDescription ="Content Chooser" )
            }
        }
    }
    @Composable

    fun FeaturedMovieSection()
    {
        Column(modifier= Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.img_1),
                contentDescription = "",
                modifier= Modifier
                    .size(250.dp)
                    .padding(bottom = 16.dp))
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(start = 54.dp, end = 54.dp, top = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                ){
                Text(text = "Adventure", color = Color.White, fontSize = 16.sp)
                Text(text = ".", color = Color.White)
                Text(text = "Thriller", color = Color.White, fontSize = 16.sp)
                Text(text = ".", color = Color.White)
                Text(text = "Suspense", color = Color.White, fontSize = 16.sp)
                Text(text = ".", color = Color.White)
                Text(text = "Mystery", color = Color.White, fontSize = 16.sp)
            }
            Row(modifier = Modifier
                .padding(top = 40.dp, start = 70.dp, end = 70.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "")
                    Text(text = "My List", color = Color.LightGray)
                }
                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(text = "Play", color = Color.Black)
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.baseline_info_24),
                        contentDescription = "")
                    Text(text = "Info", color = Color.LightGray)
                }
            }
        }

    }
    @Composable
    fun addMovieList(movieType : String)
    {
        Column(modifier= Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(top = 24.dp)) {
            //Use .sp for font size in Text
            Text(text = movieType,
                color = Color.LightGray,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp, start = 12.dp)
                )
            LazyRow {
                itemsIndexed(getRandom()){
                    index, item ->
                    MovieItemUI(imgres = item.img)
                }
            }

        }
    }
    @Composable
    fun MovieItemUI(
        imgres : Int
    )
    {
        Image(painter = painterResource(id = imgres),
            contentDescription = "",modifier= Modifier
                .height(170.dp)
                .width(100.dp))
    }
    fun getRandom() : List<MovieItemModel>{
        val listOfMovie = mutableListOf<MovieItemModel>()
        listOfMovie.add(MovieItemModel(R.drawable.i1))
        listOfMovie.add(MovieItemModel(R.drawable.i2))
        listOfMovie.add(MovieItemModel(R.drawable.i3))
        listOfMovie.add(MovieItemModel(R.drawable.i4))
        listOfMovie.add(MovieItemModel(R.drawable.i5))
        listOfMovie.add(MovieItemModel(R.drawable.i6))
        listOfMovie.add(MovieItemModel(R.drawable.i7))
        listOfMovie.add(MovieItemModel(R.drawable.i8))

        listOfMovie.shuffle()
        return listOfMovie
    }
}
data class MovieItemModel(
    val img: Int
)