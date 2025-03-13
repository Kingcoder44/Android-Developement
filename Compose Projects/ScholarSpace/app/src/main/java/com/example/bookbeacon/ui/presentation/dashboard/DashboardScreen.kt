    package com.example.bookbeacon.ui.presentation.dashboard

    import androidx.compose.foundation.Image
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.PaddingValues
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.layout.width
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.LazyRow
    import androidx.compose.foundation.lazy.items
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.Add
    import androidx.compose.material3.Button
    import androidx.compose.material3.CenterAlignedTopAppBar
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text

    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.collectAsState
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateMapOf
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.saveable.rememberSaveable
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.unit.dp
    import androidx.hilt.navigation.compose.hiltViewModel
    import androidx.lifecycle.compose.collectAsStateWithLifecycle
    import com.example.bookbeacon.R
    import com.example.bookbeacon.ui.presentation.component.CountCard
    import com.example.bookbeacon.domain.model.Subject
    import com.example.bookbeacon.domain.model.Task
    import com.example.bookbeacon.subjects
    import com.example.bookbeacon.tasks
    import com.example.bookbeacon.ui.presentation.Subject.SubjectScreenNavArgs
    import com.example.bookbeacon.ui.presentation.Subject.SubjectScreenRoute
    import com.example.bookbeacon.ui.presentation.component.AddSubjectDialog
    import com.example.bookbeacon.ui.presentation.component.DeleteDialog
    import com.example.bookbeacon.ui.presentation.component.studySessionsList
    import com.example.bookbeacon.ui.presentation.component.tasksList
    import com.example.bookbeacon.ui.presentation.destinations.SessionScreenRouteDestination
    import com.example.bookbeacon.ui.presentation.destinations.SubjectScreenRouteDestination
    import com.example.bookbeacon.ui.presentation.destinations.TaskScreenRouteDestination
    import com.example.bookbeacon.ui.presentation.task.TaskScreenNavArgs
    import com.example.studysmart.presentation.components.SubjectCard
    import com.ramcosta.composedestinations.annotation.Destination
    import com.ramcosta.composedestinations.navigation.DestinationsNavigator

    @Destination(start = true)
    @Composable
    fun DashBoardScreenRoute(
        navigator : DestinationsNavigator
    ){
        val viewModel : DashboardVM = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        DaboardScreen(
            onSubjectCardClick = {
                subjectId->
                subjectId?.let{
                    val navArg = SubjectScreenNavArgs(subjectId = subjectId)
                    navigator.navigate(SubjectScreenRouteDestination(navArg))
                }
            },
            onTaskCardClick = {
                taskId ->
                val navArg = TaskScreenNavArgs(
                    TaskId = taskId,
                    subjectId = null
                )
                navigator.navigate(TaskScreenRouteDestination(navArgs = navArg))
            }   ,
            onStartSessionButtonClick = {
                navigator.navigate(SessionScreenRouteDestination())
            }
        )
    }

    @Composable
    private fun DaboardScreen(
        onSubjectCardClick : (Int?)->Unit,
        onTaskCardClick : (Int?)->Unit,
        onStartSessionButtonClick: ()->Unit
    ){
        var isAddSubjectOpen by rememberSaveable { mutableStateOf(false) }
        var isDeleteDialogOpen by rememberSaveable { mutableStateOf(false) }
        var subjectName by remember{ mutableStateOf("") }
        var goalHours by remember{ mutableStateOf("") }
        var seelctedColor by remember { mutableStateOf(Subject.subjectCardColors.random()) }
        AddSubjectDialog(
            isopen = isAddSubjectOpen,
            onDismissRequest = { isAddSubjectOpen = false },
            onConfirmButton = { isAddSubjectOpen = false },
            selectedColor = seelctedColor,
            onColorChange = { seelctedColor = it},
            subjectName = subjectName,
            goalHours = goalHours,
            onSubjectNameChange = {subjectName = it},
            onGoalHourChange = {goalHours = it},
        )
        DeleteDialog(
            isopen = isDeleteDialogOpen,
            title = "Delete Session?",
            bodyText = "Are you sure, you want to delete this session?",
            onDismissRequest = { isDeleteDialogOpen = false},
            onConfirmButton = {isDeleteDialogOpen = false}
        )

        Scaffold(
            topBar = { DashboardScreenTopBar() }
        ) {
            paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                item {
                    countCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        subjectCount = 5,
                        studiedHour = "10",
                        GoalHour = "15"
                    )
                }
                item{
                    subjectCardSection(
                        modifier = Modifier.fillMaxWidth(),
                        subjectList = subjects,
                        onAddIconClicked = {
                            isAddSubjectOpen = true
                        },
                        onSubjectCardClick = onSubjectCardClick
                    )
                }
                item{
                    Button(
                        onClick = onStartSessionButtonClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 48.dp, vertical = 20.dp)
                    ) {
                        Text(text = "Start Study Session");
                    }
                }
                tasksList(
                    sectionTitle = "UPCOMING TASKS", tasks = tasks,
                    emptyListText = "You don't have any upcoming tasks.\nClick the + button to add new task.",
                    onCheckboxClick = {},
                    onTaskCardClick = onTaskCardClick

                )
                studySessionsList(
                    sectionTitle = "RECENT STUDY SESSOIONS",
                    emptyListText = "You don't have any recent study sessions.\nStart a study session to start recording your progress.",
                    session = emptyList(),
                    onDeleteIconCLick = {isDeleteDialogOpen = true}
                )

            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun DashboardScreenTopBar(){
        CenterAlignedTopAppBar(
            title = {Text(
                text = "Scholar Space",
                style = MaterialTheme.typography.headlineLarge
            )}
        )
    }

    @Composable
    private fun countCard(
        modifier: Modifier,
        subjectCount : Int,
        studiedHour : String,
        GoalHour : String
    ){
        Row(modifier = modifier) {
            CountCard(
                modifier = Modifier.weight(1f),
                headingText = "Subject Count",
                count = "$subjectCount"
            )
            Spacer(Modifier.width(10.dp))
            CountCard(
                modifier = Modifier.weight(1f),
                headingText = "Studied Hour",
                count = studiedHour
            )
            Spacer(Modifier.width(10.dp))
            CountCard(
                modifier = Modifier.weight(1f),
                headingText = "Goal Study Hour",
                count = GoalHour
            )
        }
    }

    @Composable
    private fun subjectCardSection(
        modifier: Modifier,
        subjectList : List<Subject>,
        emptyListText : String = "You have not chosen any subjects.\nClick + button to add new subject.",
        onAddIconClicked : ()->Unit,
        onSubjectCardClick: (Int?) -> Unit
    ) {
         Column {

             Row (modifier = modifier.fillMaxWidth(),
                 verticalAlignment = Alignment.CenterVertically,
                 horizontalArrangement = Arrangement.SpaceBetween)
             {
                Text(
                    "SUBJECTS",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 12.dp)
                )
                 IconButton(onClick = onAddIconClicked) {
                     Icon(imageVector = Icons.Default.Add,
                         contentDescription = "Add Subject")
                 }
             }
             if(subjectList.isEmpty())
             {
                 Image(
                     modifier = Modifier
                         .size(120.dp)
                         .align(Alignment.CenterHorizontally),
                     painter = painterResource(R.drawable.books),
                     contentDescription = emptyListText
                      )
                 Text(
                     modifier = Modifier.fillMaxWidth(),
                     text = emptyListText,
                     color = Color.Gray,
                     style = MaterialTheme.typography.bodySmall,
                     textAlign = TextAlign.Center
                 )
             }
             LazyRow (
                 horizontalArrangement = Arrangement.spacedBy(12.dp),
                 contentPadding = PaddingValues(start = 12.dp,end = 12.dp)
             ){
                 items(subjectList){
                     subject -> SubjectCard(
                        subjectName =  subject.name,
                         gradientColors = subject.colors.map{Color(it)},
                         onClick = {onSubjectCardClick(subject.subjectId)}
                     )
                 }
             }
         }
    }