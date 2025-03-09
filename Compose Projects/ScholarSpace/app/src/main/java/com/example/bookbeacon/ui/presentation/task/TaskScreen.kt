package com.example.bookbeacon.ui.presentation.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bookbeacon.subjects
import com.example.bookbeacon.ui.presentation.component.DeleteDialog
import com.example.bookbeacon.ui.presentation.component.SubjectListBottomSheet
import com.example.bookbeacon.ui.presentation.component.TaskCheckBox
import com.example.bookbeacon.ui.presentation.component.TaskDatePicker
import com.example.bookbeacon.ui.presentation.theme.Red
import com.example.bookbeacon.util.Priority
import com.example.bookbeacon.util.changeMillisToDateString
import kotlinx.coroutines.launch
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen()
{
    var deleteDialog by rememberSaveable {mutableStateOf(false)}

    var isdatePickerDialogOpen by rememberSaveable {mutableStateOf(false)}

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli() //to selecet current date
    )

    var isBottomSheetOpen by rememberSaveable {mutableStateOf(false)}
    val bottomSheetState = rememberModalBottomSheetState()

    val scope  = rememberCoroutineScope()

    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var taskTitleError by rememberSaveable { mutableStateOf<String?>(null) }

    taskTitleError = when{
        title.isBlank() ->"Please enter task title"
        title.length<4 -> "Task title too short"
        title.length>30 -> "Task title too long"
        else->null
    }

    DeleteDialog(
        isopen = deleteDialog,
        title = "Delete Task?",
        bodyText = "Are you sure you want to delete this task?",
        onDismissRequest = { },
        onConfirmButton = {deleteDialog = false}

    )
    TaskDatePicker(
        state = datePickerState,
        isOpen = isdatePickerDialogOpen,
        onConfirmButtonClicked = {isdatePickerDialogOpen = false},
        onDismissButtonClicked = {isdatePickerDialogOpen  =false}
    )
    SubjectListBottomSheet(
        sheetState = bottomSheetState,
        isOpen = isBottomSheetOpen,
        subjects = subjects,
        onSubjectClicked = {
            scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                if(!bottomSheetState.isVisible)
                    isBottomSheetOpen =false
            }
        },
        onDismissRequest = {isBottomSheetOpen = false}
    )
    Scaffold (
        topBar = {
            taskScreenTopBar(
                isTaskExist = true,
                isComplete = false,
                checkBoxBorderColor = Red,
                onBackButtonClick = {},
                onCheckBoxClick = {},
                onDeleteButtonClick = {deleteDialog=true}
            )
        }
    ){
        paddingValue->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .padding(paddingValue)
                .padding(horizontal = 12.dp)
        ){
            OutlinedTextField(
                value = title,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {title = it},
                label ={ Text(text = "Title") },
                singleLine = true,
                isError = taskTitleError!=null && title.isNotBlank(),
                supportingText = {
                    Text(text = taskTitleError.orEmpty())
                }
            )
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = desc,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {desc = it},
                label ={ Text(text = "Description") },

            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Due Date"
                ,style = MaterialTheme.typography.bodySmall
            )
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = datePickerState.selectedDateMillis.changeMillisToDateString(),
                    style = MaterialTheme.typography.bodyLarge
                )
                IconButton(onClick = {isdatePickerDialogOpen = true}){
                    Icon(imageVector = Icons.Default.DateRange,
                        contentDescription = "Select Due Date")
                }
            }
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Priority"
                ,style = MaterialTheme.typography.bodySmall
            )
            Spacer(Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()){
                Priority.entries.forEach { priority ->

                        PriorityButton(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            label = priority.title,
                            bgColor = priority.color,
                            borderColor = if(priority==Priority.MEDIUM){
                                Color.White
                            }
                            else{
                                Color.Transparent
                            },
                            labelColor = if(priority==Priority.MEDIUM){
                                Color.White
                            }
                            else{
                                Color.White.copy(alpha = 0.7f)
                            },
                            onCLick = {}
                        )
                }
            }
            Spacer(Modifier.height(30.dp))
            Text(
                text = "Related to Subject"
                ,style = MaterialTheme.typography.bodySmall
            )
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "English",
                    style = MaterialTheme.typography.bodyLarge
                )
                IconButton(onClick = {isBottomSheetOpen = true}){
                    Icon(imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select Subject")
                }
            }
            Button(enabled = taskTitleError==null, onClick = {}, modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)) {
                Text("Save")
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun taskScreenTopBar(
    isTaskExist : Boolean,
    isComplete : Boolean,
    checkBoxBorderColor : Color,
    onBackButtonClick : ()->Unit,
    onCheckBoxClick : ()->Unit,
    onDeleteButtonClick : ()->Unit,
    ){
    TopAppBar(
        title = {
            Text(
                text = "Task",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackButtonClick )
            {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Navigate back")
            }
        },
        actions = {
            if(isTaskExist){
                TaskCheckBox(
                    isComplete = isComplete,
                    borderColor = checkBoxBorderColor,
                    onCheckBoxClick = onCheckBoxClick
                )

                IconButton(onClick = onDeleteButtonClick )
                {
                    Icon(imageVector = Icons.Default.Delete,
                        contentDescription = "Delete task")
                }
            }
        }
    )
}
@Composable
private fun PriorityButton(
    modifier: Modifier = Modifier,
    label : String,
    bgColor : Color,
    borderColor : Color,
    labelColor : Color,
    onCLick : ()->Unit
){
    Box(
        modifier = modifier
            .background(bgColor)
            .clickable { onCLick() }
            .padding(5.dp)
            .border(1.dp, borderColor, RoundedCornerShape(5.dp))
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ){
        Text(text = label, color = labelColor)
    }
}