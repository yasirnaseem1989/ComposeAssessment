package com.yasir.compose.assessment.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yasir.compose.assessment.R
import com.yasir.compose.assessment.ui.component.AppText
import com.yasir.compose.assessment.ui.component.UserNameComponent
import com.yasir.compose.assessment.ui.component.WelcomeTitle

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: (String) -> Unit
) {
    val context = LocalContext.current
    var userName by remember {
        mutableStateOf("")
    }

    val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 24.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            WelcomeTitle(
                text = context.getString(R.string.welcome_back),
                fontSize = 40.sp,
                modifier = Modifier.padding(top = 24.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppText(
                text = context.getString(R.string.login_message),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 18.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            UserNameComponent(userName = userName) {
                userName = it
            }

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    viewModel.login(userName)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(context.getString(R.string.button_label_login))
            }
        }
    }

    if (loginUiState.isSuccess) onLoginSuccess.invoke(userName)
}
