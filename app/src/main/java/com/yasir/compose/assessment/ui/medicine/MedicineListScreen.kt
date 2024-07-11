package com.yasir.compose.assessment.ui.medicine

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yasir.compose.assessment.data.model.Medicine

@Composable
fun MedicineListScreen(
    viewModel: MedicineViewModel = hiltViewModel(),
    onMedicineClick: (Medicine) -> Unit) {

    val homeUiComponentState by viewModel.medicinesUiState.collectAsStateWithLifecycle()

    LazyColumn {
        items(homeUiComponentState) { medicine ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onMedicineClick(medicine) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Name: ${medicine.name}")
                    Text("Dose: ${medicine.dose}")
                    Text("Strength: ${medicine.strength}")
                }
            }
        }
    }
}