package com.example.shoppinglistapp

import android.media.browse.MediaBrowser.ItemCallback
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


data class ShoppingItem(
    val id: Int,
    var Item_Name : String ="",
    var qnt : Int,
    var isEditing : Boolean = false
)
@Composable
fun shopListApp()
{
    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    //we can also use shoppingList  = muableListOf<ShoopingItem>()
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQty by remember { mutableStateOf("") }

    Column ( modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center)
    {
        //click on button to add item and open alert dialog
        Button(onClick = { showDialog=true}, modifier = Modifier.align(Alignment.CenterHorizontally))
        {
            Text(text = "Add Item")
        }
        //to store infinte list of items
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp))
        {
            items(sItems){
                item->
                if(item.isEditing)
                {//IMP : Required when we need to modifiy our items in list
                    ListEditor(item = item, onEditComplete = {
                        editedName,editedQty ->
                        sItems = sItems.map { it.copy(isEditing = false) }
                        val editedItem = sItems.find { it.id == item.id }
                        editedItem?.let{
                            it.Item_Name = editedName
                            it.qnt = editedQty
                        }
                    })
                }
                else{
                    ShoppingListView(item = item ,
                        onEditClick = { sItems = sItems.map { it.copy(isEditing = it.id == item.id) } },
                        onDeleteClick = {
                        sItems = sItems - item
                        })
                }
            }
        }

    }
    if(showDialog)
    {
        //display alert dialog to add item
      AlertDialog(onDismissRequest = { showDialog=false}, confirmButton = { /*TODO*/ }, title = { Text(
          text = "Add Shopping Items"
      )},
          text = {
              Column {
                  OutlinedTextField(value = itemName,
                      onValueChange = {itemName = it},
                      singleLine = true,
                      modifier = Modifier
                          .fillMaxWidth()
                          .padding(8.dp))
                  OutlinedTextField(value = itemQty,
                      onValueChange = {itemQty = it},
                      singleLine = true,
                      modifier = Modifier
                          .fillMaxWidth()
                          .padding(8.dp))

                  Row (modifier = Modifier
                      .fillMaxWidth()
                      .padding(8.dp),
                      horizontalArrangement = Arrangement.SpaceBetween){

                      Button(onClick = {
                          if(itemName.isNotBlank())
                          {
                              val newItem = ShoppingItem(
                                  id=sItems.size+1,
                                  Item_Name = itemName,
                                  qnt = itemQty.toInt()
                              )
                              //to append the remember list
                              sItems = sItems+newItem
                              showDialog = false
                              itemName = ""
                              itemQty = ""
                          }
                      }) {
                          Text(text = "Add")
                      }

                      Button(onClick = { showDialog=false }) {
                          Text(text = "Cancel")
                      }
                  }
              }

          })
    }

}
@Composable
fun ListEditor(
    item : ShoppingItem,
    onEditComplete : (String,Int)->Unit
){
    var editName by remember { mutableStateOf(item.Item_Name) }
    var editQty by remember { mutableStateOf(item.qnt.toString()) }
    var isEditing by remember { mutableStateOf(item.isEditing) }
    Row (modifier= Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly){
        Column {
            BasicTextField(value = editName, onValueChange = {editName=it}, singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp))

            BasicTextField(value = editQty, onValueChange = {editQty=it}, singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp))


        }
        Button(onClick = { isEditing =false
        onEditComplete(editName, editQty.toIntOrNull() ?: 1)


        }) {
            Text(text = "Save")
        }

    }
}
@Composable
fun ShoppingListView(
    item : ShoppingItem,
    onEditClick : ()->Unit,
    onDeleteClick : ()->Unit
){
    Row (modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .border(
            border = BorderStroke(2.dp, Color(0XFF018786)),
            shape = RoundedCornerShape(20)
        ),
        horizontalArrangement = Arrangement.SpaceBetween
        )
    {
        Text(text = item.Item_Name, modifier = Modifier.padding(8.dp))
        Text(text = "Qty : ${item.qnt}", modifier = Modifier.padding(8.dp))
        Row (modifier = Modifier.padding(8.dp),
            ){
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit , contentDescription = null)

            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete , contentDescription = null)

            }
        }
    }

}