package com.example.lv5.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lv5.model.Poi

@Composable
fun ViewPoiScreen(poiViewModel: PoiViewModel,
                    navigateToList: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        PoiCard(poi = poiViewModel.selectedPoi)
        Row {
            Button(modifier = Modifier.padding(12.dp),
                onClick = {
                    navigateToList()
                }) {
                Text(text = "Back")
            }
            Button(modifier = Modifier.padding(12.dp),
                onClick = {
                    poiViewModel.deletePoi(poiViewModel.selectedPoi.id)
                    navigateToList()
                }) {
                Text(text = "Delete Poi")
            }
        }
    }
}

@Composable
fun PoiCard(poi: Poi) {
    ElevatedCard(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth()) {
        Column() {
            Text(
                modifier = Modifier.padding(6.dp),
                text = poi.name,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize
            )
            Text(modifier = Modifier.padding(6.dp), text = "Address: ${poi.address}")
            Row(modifier = Modifier.padding(6.dp)) {
                Text(text = "Latitude:")
                Text(text = poi.lat.toString())
            }
            Row(modifier = Modifier.padding(6.dp)) {
                Text(text = "Longitude:")
                Text(text = poi.lng.toString())
            }
        }

    }
}