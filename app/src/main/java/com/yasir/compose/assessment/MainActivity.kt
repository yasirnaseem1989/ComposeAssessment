package com.yasir.compose.assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yasir.compose.assessment.ui.login.LoginScreen
import com.yasir.compose.assessment.ui.medicine.MedicineDetailScreen
import com.yasir.compose.assessment.ui.medicine.MedicineListScreen
import com.yasir.compose.assessment.ui.theme.ComposeAssessmentTheme
import com.yasir.compose.assessment.utils.ext.orZero
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            LoginScreen(
                onLoginSuccess = {
                    navController.popBackStack()
                    navController.navigate("medicines/${it}")
                }
            )
        }
        composable(
            "medicines/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            MedicineListScreen(
                username = username,
                onMedicineClick = { medicine ->
                    navController.navigate("medicineDetail/${medicine.id}")
                }
            )
        }
        composable(
            "medicineDetail/{medicineId}",
            arguments = listOf(navArgument("medicineId") { type = NavType.IntType })
        ) { backStackEntry ->
            val medicineId = backStackEntry.arguments?.getInt("medicineId").orZero()
            MedicineDetailScreen(
                navController = navController,
                medicineId = medicineId
            )
        }
    }
}