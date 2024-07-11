package com.yasir.compose.assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yasir.compose.assessment.ui.login.LoginScreen
import com.yasir.compose.assessment.ui.login.LoginViewModel
import com.yasir.compose.assessment.ui.medicine.MedicineDetailScreen
import com.yasir.compose.assessment.ui.medicine.MedicineListScreen
import com.yasir.compose.assessment.ui.medicine.MedicineViewModel
import com.yasir.compose.assessment.ui.theme.ComposeAssessmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAssessmentTheme {
                MedicineApp()
            }
        }
    }
}

@Composable
fun MedicineApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "login") {
        composable("login") {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = { navController.navigate("medicines") }
            )
        }
        composable("medicines") {
            val medicineViewModel = hiltViewModel<MedicineViewModel>()
            MedicineListScreen(
                viewModel = medicineViewModel,
                onMedicineClick = { medicine ->
                    navController.navigate("medicineDetail/${medicine.id}")
                }
            )
        }
        composable(
            "medicineDetail/{medicineId}",
            arguments = listOf(navArgument("medicineId") { type = NavType.IntType })
        ) { backStackEntry ->
            val medicineId = backStackEntry.arguments?.getInt("medicineId")
            val medicineViewModel = hiltViewModel<MedicineViewModel>()
            /*val medicine = medicineViewModel.medicines.value.first { it.id == medicineId }
            MedicineDetailScreen(medicine = medicine)*/
        }
    }
}