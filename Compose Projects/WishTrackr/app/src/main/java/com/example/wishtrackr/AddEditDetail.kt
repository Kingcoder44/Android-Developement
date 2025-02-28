package com.example.wishtrackr

import android.widget.NumberPicker.OnValueChangeListener
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold

import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishtrackr.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetail(
    id : Long,
    viewModel: WishViewModel,
    navController: NavController
){

    val snackMsg = remember{
        mutableStateOf("")
    }
    //to display msg
    val scope = rememberCoroutineScope()
    val scaffoldState =  rememberScaffoldState()

//to initially assign value
if(id!=0L) {
    val wish = viewModel.getWishbyId(id).collectAsState(initial = Wish(0L, "", ""))
    viewModel.wishTitleState = wish.value.title
    viewModel.wishDescriptionState = wish.value.descrip
}
    else{
        viewModel.wishTitleState = ""
    viewModel.wishDescriptionState=""
    }


    Scaffold (
        scaffoldState = scaffoldState
        ,topBar = {
        AppBar(
            title = if (id != 0L) "Update Wish" else "Add Wish",
            onBackNavClicked = { navController.navigateUp() } // Navigate up on back button click
        )
    }
    )

    {
        Column(modifier = Modifier.padding(it).wrapContentSize(),
            horizontalAlignment =  Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Spacer(modifier = Modifier.heightIn(10.dp))

            WishTextField(label = "Title",
                value = viewModel.wishTitleState,
                onValueChanged = {viewModel.onWishTitleChange(it)})


            WishTextField(label = "Description",
                value = viewModel.wishDescriptionState,
                onValueChanged = {viewModel.onWishDescriptionChange(it)})

            Spacer(modifier = Modifier.heightIn(10.dp))
            Button(onClick = {
                if(viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()){
                    //upadte wish

                    if(id!=0L)
                    {
                        //update wish
                        viewModel.updateWish(
                            Wish(
                                id=id,
                                title = viewModel.wishTitleState.trim(),
                                descrip = viewModel.wishDescriptionState.trim()
                            )
                        )
                    }
                    else{
                        //add wish
                        viewModel.addWish(Wish(
                            title = viewModel.wishTitleState.trim(),
                            descrip = viewModel.wishDescriptionState.trim()
                        ))
                        snackMsg.value = "Wish has been created"
                    }

                }
                else{
                    snackMsg.value = "Enter fields to create a Wish"
                }
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(snackMsg.value)
                    navController.navigateUp()
                }
            }) {
                Text(
                    if(id!=0L) stringResource(R.string.update_wish) else stringResource(R.string.add_wish),
                    style = TextStyle(
                         fontSize = 16.sp
                    )
                )

            }

        }
    }

}

//Text box for edit screen
@Composable
fun WishTextField(
    label :String,
    value : String,
    onValueChanged: (String)->Unit
)
{
    OutlinedTextField(value =value,
        onValueChange = onValueChanged,
        label = {Text(text=label, color = Color.Black)}
    ,modifier=Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        //to deine colours of textfield
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            focusedBorderColor = colorResource(id = R.color.app_bar_color),
            unfocusedBorderColor = Color.Black,
            cursorColor = Color.Magenta
            )
        )
}
@Preview(showBackground = true)
@Composable
fun TextTest(){
    WishTextField("Text","Text",{})
}