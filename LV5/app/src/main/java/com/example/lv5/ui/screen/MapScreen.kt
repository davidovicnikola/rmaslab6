package com.example.lv5.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.lv5.model.Poi
import com.google.maps.android.compose.GoogleMapComposable
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    onMapLongClick:(LatLng)->Unit,
    list: List<Poi>,
    navigateToViewPoi: () -> Unit,
    setSelectedPoi: (Poi) -> Unit
) {
    //TODO: Postaviti centar mape na centar grada Niša i postaviti zoom na 20
    //Lat:43.321445, Lng:21.896104

    var uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = true)) }
    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        //cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings,
        onMapLongClick = {latLng -> onMapLongClick(latLng)}
    ){
        //TODO: dodati marker za svaki poi iz liste list
        //tako da se klikom na Poi sadrzaj poi-a vidi na ViewPoi ekranu

    }
}

