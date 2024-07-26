package com.yasir.compose.assessment.ui.medicine

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yasir.compose.assessment.R
import com.yasir.compose.assessment.domain.model.Medicine
import com.yasir.compose.assessment.ui.component.AppProgressBar
import com.yasir.compose.assessment.ui.component.AppText

@Composable
fun MedicineListScreen(
    viewModel: MedicineViewModel = hiltViewModel(),
    username: String,
    onMedicineClick: (Medicine) -> Unit
) {

    val context = LocalContext.current
    val homeUiComponentState by viewModel.medicinesUiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 24.dp)
    ) {
        if (homeUiComponentState.isLoading) {
            AppProgressBar(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (homeUiComponentState.isError) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    val painter: Painter = painterResource(id = R.drawable.no_data_found)
                    Image(
                        painter = painter,
                        contentDescription = context.getString(R.string.text_generic_error),
                    )
                    AppText(
                        text = context.getString(R.string.text_generic_error),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AppText(
                text = "${homeUiComponentState.greetingMessage}, \n$username!",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            )

            if (homeUiComponentState.hasData()) {
                LazyColumn {
                    items(homeUiComponentState.medicinesViewItem) { medicine ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable { onMedicineClick(medicine) }
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                AppText(
                                    text = "Name: ${medicine.name}",
                                    fontWeight = FontWeight.Normal,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                AppText(
                                    text = "Dose: ${medicine.dose}",
                                    fontWeight = FontWeight.Normal,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                AppText(
                                    text = "Strength: ${medicine.strength}",
                                    fontWeight = FontWeight.Normal,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}