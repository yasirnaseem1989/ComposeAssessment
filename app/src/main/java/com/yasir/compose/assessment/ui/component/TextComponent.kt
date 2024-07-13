package com.yasir.compose.assessment.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yasir.compose.assessment.R
import com.yasir.compose.assessment.ui.theme.AppFont

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 16.sp,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    letterSpacing: TextUnit = TextUnit.Unspecified,
) {
    Text(
        text = text,
        fontFamily = AppFont,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color,
        modifier = modifier,
        textAlign = textAlign,
        lineHeight = lineHeight ?: fontSize,
        maxLines = maxLines,
        overflow = overflow,
        letterSpacing = letterSpacing
    )
}

@Composable
fun WelcomeTitle(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    text: String,
    fontSize: TextUnit = 24.sp
) {
    AppText(
        text = text,
        fontWeight = FontWeight.Medium,
        fontSize = fontSize,
        color = color,
        modifier = modifier,
        lineHeight = fontSize,
    )
}

@Composable
fun UserNameComponent(
    modifier: Modifier = Modifier,
    userName: String,
    borderColor: Color = Color.White,
    onUserName: (String) -> Unit
) {

    val context = LocalContext.current

    AppInputField(
        modifier = modifier
            .fillMaxWidth(),
        text = userName,
        placeholder = {
            AppText(
                text = context.getString(R.string.user_name),
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        borderColor = borderColor
    ) {
        onUserName.invoke(it)
    }
}

@Composable
fun AppInputField(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    textStyle: TextStyle = LocalTextStyle.current,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    borderColor: Color = Color.White,
    enabled: Boolean = true,
    disabledTextColor: Color = Color.Unspecified,
    singleLine: Boolean = false,
    onValueChange: ((String) -> Unit)? = null,
) {
    OutlinedTextField(
        value = text,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            disabledTextColor = disabledTextColor
        ),
        label = label,
        shape = RoundedCornerShape(10.dp),
        placeholder = placeholder,
        modifier = modifier,
        onValueChange = {
            onValueChange?.invoke(it)
        },
        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon,
        textStyle = textStyle,
        visualTransformation = visualTransformation,
        enabled = enabled,
        singleLine = singleLine
    )
}