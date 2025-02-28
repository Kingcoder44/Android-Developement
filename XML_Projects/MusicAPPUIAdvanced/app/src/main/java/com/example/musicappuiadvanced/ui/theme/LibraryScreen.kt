package com.example.musicappuiadvanced.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicappuiadvanced.Lib
import com.example.musicappuiadvanced.R
import com.example.musicappuiadvanced.lib_item

@Composable
fun Library(){
    LazyColumn {
        items(lib_item){
            item ->
            LibItem(lib = item)
        }
    }
}
@Composable
fun LibItem(lib : Lib){
    Column{
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = Modifier.padding(8.dp)){
               Icon(painter= painterResource( id = lib.icon), contentDescription = lib.name, modifier = Modifier.padding(horizontal = 8.dp))
                Text(text = lib.name)
            }
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Open")
        }
        Divider()
    }
}