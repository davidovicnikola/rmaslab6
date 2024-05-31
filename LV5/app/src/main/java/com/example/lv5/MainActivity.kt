package com.example.lv5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lv5.model.service.StorageService
import com.example.lv5.ui.screen.MapScreen
import com.example.lv5.ui.screen.PoiViewModel
import com.example.lv5.ui.screen.PoiViewModelFactory
import com.example.lv5.ui.screen.ViewPoiScreen
import com.example.lv5.ui.screen.addpoi.AddPoiScreen
import com.example.lv5.ui.screen.addpoi.EditViewModel
import com.example.lv5.ui.theme.LV5Theme

class MainActivity : ComponentActivity() {
    private val poiViewModel: PoiViewModel by viewModels {
        PoiViewModelFactory(StorageService((application as PoiApplication).db))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LV5Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LV5App(poiViewModel)
                }
            }
        }
    }
}

@Composable
fun LV5App(poiViewModel: PoiViewModel) {
    val navController = rememberNavController()
    val editViewModel: EditViewModel = viewModel()
    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = Screens.PoiList.name) {
            composable(Screens.PoiList.name) {
                val poiList = poiViewModel.pois.collectAsState(initial = listOf())
                MapScreen(
                    onMapLongClick = { latLng ->
                        //TODO: postaviti latitudu i logitudu i editViewModelu

                        navController.navigate(Screens.AddPoi.name)
                    },
                    list = poiList.value, navigateToViewPoi = {
                        navController.navigate(
                            Screens.ViewPoi.name
                        )},
                    setSelectedPoi = { poiViewModel.setCurrentPoi(it) }
                )
            }
            composable(Screens.AddPoi.name) {
                AddPoiScreen(
                    editViewModel = editViewModel,
                    poiViewModel = poiViewModel,
                    navigateToMap = { navController.popBackStack() }
                )
            }
            composable(Screens.ViewPoi.name) {
                ViewPoiScreen(
                    poiViewModel = poiViewModel,
                    navigateToList = { navController.popBackStack() }
                )
            }
        }
    }
}


enum class Screens {
    PoiList,
    AddPoi,
    ViewPoi
}
