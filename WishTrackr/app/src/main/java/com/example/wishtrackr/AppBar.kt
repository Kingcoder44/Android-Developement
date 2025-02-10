package com.example.wishtrackr

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
//import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

//To add and modify the top bar of app
@Composable
fun AppBar(
    title : String,
    onBackNavClicked : ()->Unit = {}
){
    //to design navgion button on appbar top view
    val navigationIcon : (@Composable ()->Unit)?=
        if(!title.contains("Wish Tracker")){{
            IconButton(onClick ={ onBackNavClicked()}) {
                Icon(
                    imageVector =Icons.AutoMirrored.Rounded.ArrowBack,
                    tint =Color.White,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                }
            }
        }
        else
        {
            null
        }






    TopAppBar(
        title = {
            Text(
                text = title,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .padding(start = 8.dp) // Add padding to move the title away from the back button
                    .heightIn(24.dp)
            )
        },
        elevation = 12.dp,
        backgroundColor = colorResource(R.color.app_bar_color),
        navigationIcon = navigationIcon // Ensure this is passed
    )

}