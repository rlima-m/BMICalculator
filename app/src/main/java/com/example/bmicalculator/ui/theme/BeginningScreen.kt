package com.example.bmicalculator.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bmicalculator.R


@Composable
fun BMIBeginningLayout(navController : NavController) {

    Column(modifier = Modifier
        .fillMaxHeight()
        .background(color = Color(red = 215, green = 215, blue = 226, alpha = 168))
        .verticalScroll(rememberScrollState())
        .border(
            BorderStroke(width = 3.dp, color = Color.Black),
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
                .safeDrawingPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            Text(
                text = stringResource(R.string.disclaimer),
                fontFamily = FontFamily.Monospace,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)

            )
            Text(
                text = stringResource(R.string.BMI_disclaimer_text),
                fontFamily = FontFamily.Monospace,
                fontSize = 13.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 40.dp),
            )

            Button(
                onClick = {
                    navController.navigate("calculator") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 89, green = 88, blue = 143, alpha = 255)
                ),
                border = BorderStroke(width = 3.dp, color = Color.Black)
            ) {
                Text(
                    text = stringResource(R.string.head_to_calculator),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 15.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)

                )
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = stringResource(id = R.string.arrow),
                    Modifier.size(50.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    BMICalculatorTheme {
        BMIBeginningLayout(rememberNavController())
    }
}