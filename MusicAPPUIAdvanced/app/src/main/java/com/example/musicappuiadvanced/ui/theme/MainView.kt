package com.example.musicappuiadvanced.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musicappuiadvanced.MainVM
import com.example.musicappuiadvanced.R
import com.example.musicappuiadvanced.Screen
import com.example.musicappuiadvanced.screensInBottom
import com.example.musicappuiadvanced.screensInDrawer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainView() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val viewomdel : MainVM = viewModel()
    val isSheetFullScreen by remember { mutableStateOf(false) }
    val modifier = if(isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentScreen = remember {viewomdel.currentScreen.value }
    val title = remember{
        mutableStateOf(currentScreen.title)
    }
    val onDialog = remember { mutableStateOf(false) }
    val bottombar : @Composable () -> Unit = {

            BottomNavigation(Modifier.wrapContentSize()) {
                screensInBottom.forEach {
                    item ->
                    BottomNavigationItem(
                        selected = currentRoute==item.bRoute,
                        onClick = {
                            navController.navigate(item.bRoute)
                            title.value=item.bTitle
                        },
                        icon = {
                            Icon(contentDescription = item.bTitle, painter = painterResource(id = item.icon))
                        },
                        label = { Text(text = item.bTitle ) }
                        ,
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black
                    )
                }
            }
    }
    val modalSheetState =  rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {it!=ModalBottomSheetValue.HalfExpanded}
        )
    val roundedCornerRadius = if(isSheetFullScreen) 0.dp else 12.dp

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
        sheetContent = {
        MoreBottomSheet(modifier = modifier)
    }) {
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = {
                bottombar()
            },
            topBar = {
                TopAppBar(
                    title = { Text(title.value) },
                    actions={
                      IconButton(onClick = {
                          scope.launch {
                              if(modalSheetState.isVisible)
                                  modalSheetState.hide()
                              else
                                  modalSheetState.show()
                          }
                      }){
                           Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                      }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            drawerContent = {
                LazyColumn(Modifier.padding(16.dp)) {
                    items(screensInDrawer) { item ->
                        DrawerItem(
                            selected = currentRoute == item.dRoute,
                            item = item,
                            onDrawerItemClicked = {
                                scope.launch { scaffoldState.drawerState.close() }
                                if (item.dRoute != "add_account") {
                                    navController.navigate(item.dRoute)
                                    title.value = item.dTitle
                                }
                                else{
                                    //open dialog
                                    onDialog.value=true;

                                }
                            }
                        )
                    }
                }
            }
        ) {
            Navigation(navController=navController ,viewmodel = viewomdel,pd=it)
            AccountDialog(dialogOpen = onDialog)
        }
    }


    }


@Composable
fun MoreBottomSheet(modifier: Modifier) {
    Box(Modifier.fillMaxWidth().height(300.dp).background(MaterialTheme.colors.primarySurface))
    {
        Column(modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Row(modifier.padding(16.dp)){
                Icon(modifier = Modifier.padding(end=8.dp),
                    painter=painterResource(id= R.drawable.baseline_settings_24),
                    contentDescription = "Settings"
                )
                Text("Settings", fontSize = 20.sp, color = Color.White)

            }
        }
    }
}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit
) {
    val background = if (selected) Color.Gray else Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(background)
            .clickable { onDrawerItemClicked() }
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = item.dTitle,
            style = MaterialTheme.typography.h6
        )
    }
}
@Composable
fun Navigation(navController: NavController,viewmodel:MainVM,pd:PaddingValues){
    NavHost(navController=navController as NavHostController,
        startDestination = Screen.DrawerScreen.Account.route,
        modifier = Modifier.padding(pd)) {

        composable(Screen.DrawerScreen.Account.route){
            AccountView()
        }
        composable(Screen.DrawerScreen.Subscription.route){
            SubscibeVew()
        }

        composable(Screen.BottomScreen.Home.route){
            HomeScreen()
        }

        composable(Screen.BottomScreen.Browse.route){
            BrowseScreen()
        }

        composable(Screen.BottomScreen.Library.route){
            Library()
        }
    }
}