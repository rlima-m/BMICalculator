package com.example.bmicalculator.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bmicalculator.R
import kotlin.math.pow

@Composable
fun EditNumberField(
    modifier: Modifier = Modifier,
    labelText: String,
    value: String,
    onValueChange: (String) -> Unit,
    action: ImeAction = ImeAction.Next,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text (text = labelText) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(red = 154, green = 152, blue = 204, alpha = 173),
            focusedContainerColor = Color(red = 186, green = 184, blue = 228, alpha = 255),
            focusedIndicatorColor = Color(red = 10, green = 7, blue = 97, alpha = 255)
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = action
        ),
        modifier = modifier

    )
}

fun calculateBMI(metric: Boolean, iWeight: Double, iHeight: Double): Int {
    var bmi = iWeight/ iHeight.pow(2.0)
    if (!metric) {
        bmi = (703*iWeight)/ iHeight.pow(2.0)
    }
    return bmi.toInt()
}

@Composable
fun MetricSystemRow(metric: Boolean,
                    onMetricChanged: (Boolean) -> Unit,
                    modifier: Modifier = Modifier
){
    Row(modifier = modifier
        .fillMaxWidth()
        .size(60.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
        Text(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.Start),
            text = stringResource(R.string.metric_system),
            fontFamily = FontFamily.Monospace,
            fontSize = 15.sp,
            fontWeight = FontWeight.Light,
        )
        Switch(
            modifier = modifier
                .fillMaxWidth(),
            checked = metric,
            onCheckedChange = onMetricChanged,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(red = 10, green = 7, blue = 97, alpha = 255),
                checkedTrackColor = Color(red = 89, green = 88, blue = 143, alpha = 255),
                checkedBorderColor = Color.Black,
                uncheckedThumbColor = Color(red = 10, green = 7, blue = 97, alpha = 255),
                uncheckedTrackColor = Color(red = 89, green = 88, blue = 143, alpha = 255),
                uncheckedBorderColor = Color.Black,
            )
        )
    }
}

var bmi : Int = 0
var age: Int = 0

@Composable
fun BMICalculatorLayout(navController : NavController) {

    var metric by remember { mutableStateOf(true) }

    var inputAge by remember { mutableStateOf("") }
    val got_age = inputAge.toIntOrNull() ?:0
    age = got_age

    var inputWeight by remember { mutableStateOf("") }
    val iWeight = inputWeight.toDoubleOrNull() ?: 0.0

    var inputHeight by remember { mutableStateOf("") }
    val iHeight = inputHeight.toDoubleOrNull() ?: 0.0

    bmi = if(iHeight > 0.0 && iWeight > 0.0) calculateBMI(metric, iWeight, iHeight) else 0

    Column(modifier = Modifier
        .border(
            BorderStroke( width = 3.dp, color = Color.Black),
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeightIn(min = 40.dp, max = 55.dp)
                .background(
                    color = Color(red = 89, green = 88, blue = 143, alpha = 255),
                )
                .border(
                    BorderStroke(width = 3.dp, color = Color.Black),
                )
        ) {
            Text(
                text = stringResource(R.string.bmi_calculator),
                fontFamily = FontFamily.Monospace,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
            )
        }
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 40.dp, vertical = 60.dp)
                .verticalScroll(rememberScrollState()) //Important
                .safeDrawingPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            EditNumberField(
                labelText = stringResource(R.string.age_in_years),
                value = inputAge,
                onValueChange = { inputAge = it },
                modifier = Modifier
                    .padding(top = 32.dp)
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
            )
            EditNumberField(
                labelText = stringResource(R.string.weight_kg_lbs),
                value = inputWeight,
                onValueChange = { inputWeight = it },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
            )
            EditNumberField(
                labelText = stringResource(R.string.height_m_in),
                value = inputHeight,
                onValueChange = { inputHeight = it },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth(),
            )

            MetricSystemRow(
                metric = metric,
                onMetricChanged = { metric = it },
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Button(
                onClick = { navController.navigate("results") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 89, green = 88, blue = 143, alpha = 255)
                ),
                border = BorderStroke(width = 3.dp, color = Color.Black)
            ) {
                Text(
                    text = "Calculate now",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)

                )
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "Arrow",
                    Modifier.size(50.dp)
                )
            }
        }
    }
}

fun getAge(): Number {
    return age
}
fun getBMI(): Int {
    return bmi
}


@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    BMICalculatorTheme {
        BMICalculatorLayout(rememberNavController())
    }
}