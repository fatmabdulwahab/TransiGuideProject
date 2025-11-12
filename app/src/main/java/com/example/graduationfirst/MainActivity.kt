package com.example.graduationfirst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graduationfirst.ui.theme.GraduationFirstTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GraduationFirstTheme {
                JournyScreen()
            }
        }
    }
}

@Composable
fun JournyScreen() {

    var fromText by remember { mutableStateOf("") }
    var toText by remember { mutableStateOf("") }

    val busList = List(5) { i ->
        Bus("Bus $i", 42 + i, "Bus", "Station $i")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF59b484)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // الجزء العلوي الثابت
        Column(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(0.9f)
                .height(280.dp)
                .padding(10.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(15.dp)
                ),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            OutlinedTextField(
                value = fromText,
                onValueChange = { fromText = it },
                label = { Text("From") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.96f)
                    .padding(start = 25.dp, top = 10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                )
            )

            Image(
                painter = painterResource(id = R.drawable.arrowtransfer),
                contentDescription = "Arrow",
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)
            )

            OutlinedTextField(
                value = toText,
                onValueChange = { toText = it },
                label = { Text("To") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(start = 25.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // العمود الأبيض القابل للتمرير
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(650.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(top = 10.dp)
        ) {
            lazylazy(busList)
        }
    }
}

@Composable
fun lazylazy(listInfo: List<Bus>) {
    LazyColumn {
        items(listInfo) { bus ->
            card(bus)
        }
    }
}

@Composable
fun card(bus: Bus) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = bus.busNumber, color = Color.LightGray)
                Text(text = bus.ticketPrice.toString(), color = Color.LightGray, modifier = Modifier
                    .background(Color(0xFFFF9800),
                        shape = RoundedCornerShape(3.dp)
                    ))
                Text(text = bus.vehicleType, color = Color.LightGray)
                Text(text = bus.lastStation, color = Color.LightGray)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewUi() {
    JournyScreen()
}
