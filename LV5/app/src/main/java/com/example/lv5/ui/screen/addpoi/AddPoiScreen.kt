package com.example.lv5.ui.screen.addpoi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lv5.ui.screen.PoiViewModel

@Composable
fun AddPoiScreen(editViewModel: EditViewModel = viewModel(),
                 poiViewModel: PoiViewModel,
                 navigateToMap: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val modifier = Modifier.padding(6.dp)
        OutlinedTextField(
            modifier = modifier,
            value = editViewModel.name,
            onValueChange = { editViewModel.name = it },
            label = { Text("Name") })
        OutlinedTextField(
            modifier = modifier,
            value = editViewModel.address,
            onValueChange = {editViewModel.address = it },
            label = { Text("Address") })
        Row {
            Text(
                text = "Latitude:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = editViewModel.lat.toString(),
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        Row {
            Text(
                text = "Longitude:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = editViewModel.lng.toString(),
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        Row {
            Button(modifier = modifier,
                onClick = {
                    navigateToMap()
                }) {
                Text(text = "Back")
            }
            Button(modifier = modifier, onClick = {
                poiViewModel.addPoi(
                    editViewModel.name,
                    editViewModel.address,
                    editViewModel.lat,
                    editViewModel.lng
                )
                editViewModel.reset()
                navigateToMap()
            }) {
                Text(text = "Add Poi")
            }
        }
    }
}
