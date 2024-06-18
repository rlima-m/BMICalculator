package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import kotlin.math.pow
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BMILayout()
                }
            }
        }
    }
}

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
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = action
        ),
        modifier = modifier

    )
}

private fun calculateBMI(metric: Boolean, iWeight: Double, iHeight: Double): Int {
    var bmi = iWeight/ iHeight.pow(2.0)
    if (!metric) {
        bmi = (703*iWeight)/ iHeight.pow(2.0)
    }
    return bmi.toInt()
}

@Composable
fun MetricSystemRow(metric: Boolean,
                    onMetricChanged: (Boolean) -> Unit,
                    modifier: Modifier = Modifier){
    Row(modifier = modifier
        .fillMaxWidth()
        .size(48.dp),
        verticalAlignment = Alignment.CenterVertically){
        Text(text = stringResource(R.string.metric_system))
        Switch(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = metric,
            onCheckedChange = onMetricChanged
        )
    }
}

@Composable
fun BMILayout() {

    var metric by remember { mutableStateOf(true) }

    var inputAge by remember { mutableStateOf("") }
    //val iAge = inputAge.toIntOrNull() ?: 0

    var inputWeight by remember { mutableStateOf("") }
    val iWeight = inputWeight.toDoubleOrNull() ?: 0.0

    var inputHeight by remember { mutableStateOf("") }
    val iHeight = inputHeight.toDoubleOrNull() ?: 0.0

    val bmi = if(iHeight > 0.0 && iWeight > 0.0) calculateBMI(metric, iWeight, iHeight) else 0


    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState()) //Important
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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
            labelText = stringResource(R.string.weight),
            value = inputWeight,
            onValueChange = { inputWeight = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        EditNumberField(
            labelText = stringResource(R.string.height),
            value = inputHeight,
            onValueChange = { inputHeight = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        MetricSystemRow(
            metric = metric,
            onMetricChanged = { metric = it },
            modifier = Modifier.padding(bottom = 32.dp)
        )


        Text(
            text = stringResource(R.string.bmi_value_d, bmi),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BMICalculatorTheme {
        BMILayout()
    }
}
