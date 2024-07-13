package com.yasir.compose.assessment.ui.medicine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.yasir.compose.assessment.R
import com.yasir.compose.assessment.ui.component.AppProgressBar
import com.yasir.compose.assessment.ui.component.AppText

@Composable
fun MedicineDetailScreen(
    navController: NavController,
    medicineId: Int,
    viewModel: MedicineDetailViewModel = hiltViewModel(),
) {
    viewModel.setMedicineId(medicineId = medicineId)

    val homeUiComponentState by viewModel.medicineDetailUiState.collectAsStateWithLifecycle()

    LaunchedEffect(LocalLifecycleOwner.current) {
        viewModel.fetchMedicineDetails()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 24.dp)
    ) {
        if (homeUiComponentState.isLoading)
            AppProgressBar(
                modifier = Modifier.align(Alignment.Center)
            )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TrackingTopBar(navController)

            if (homeUiComponentState.hasData()) {
                val medicine = requireNotNull(homeUiComponentState.medicine)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(16.dp)
                    ) {
                        AppText(
                            text = "Name: ${medicine.name}",
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        AppText(
                            text = "Dose: ${medicine.dose}",
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
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

@Composable
private fun TrackingTopBar(
    navController: NavController,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(top = 24.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                        contentDescription = "",
                        tint = Color.White
                    )
                }

                AppText(
                    text = context.getString(R.string.screen_medicine_detail),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
