package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    when(currentStep){
        1 -> {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ){
                Text(text = stringResource(R.string.lemon_tree))
                Spacer(modifier = Modifier.height(32.dp))
                Image(
                    painter = painterResource(R.drawable.lemon_tree),
                    contentDescription = stringResource(R.string.tap_lemon),
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            currentStep = 2
                            squeezeCount = (2..4).random()
                        }
                )
            }
        }
        2 -> {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ){
                Text(text = stringResource(R.string.lemon))
                Spacer(modifier = Modifier.height(32.dp))
                Image(
                    painter = painterResource(R.drawable.lemon_squeeze),
                    contentDescription = stringResource(R.string.keep_lemon),
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                currentStep = 3
                            }
                        }
                )
            }
        }
        3 -> {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ){
                Text(text = stringResource(R.string.glass_lemonade))
                Spacer(modifier = Modifier.height(32.dp))
                Image(
                    painter = painterResource(R.drawable.lemon_drink),
                    contentDescription = stringResource(R.string.glass_lemonade),
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            currentStep = 4
                        }
                )
            }
        }
        else -> {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ){
                Text(text = stringResource(R.string.empty_glass))
                Spacer(modifier = Modifier.height(32.dp))
                Image(
                    painter = painterResource(R.drawable.lemon_restart),
                    contentDescription = stringResource(R.string.tap_glass),
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            currentStep = 1
                        }
                )
            }
        }
    }
}

@Composable
fun WelcomeScreen(name: String, onStartClicked: () -> Unit) {
    Column {
        Text(text = "Welcome $name!")
        Button(
            onClick = onStartClicked
        ) {
            Text("Start")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}