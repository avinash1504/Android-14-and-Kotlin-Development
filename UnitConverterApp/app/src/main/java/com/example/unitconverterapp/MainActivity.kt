package com.example.unitconverterapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverterapp.ui.theme.UnitConverterAppTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}
@Composable
fun UnitConverter(){



    var inputValue by remember{ mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Centimeter") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember {mutableStateOf(false)}
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember {mutableStateOf(1.0)}
    val oConversionFactor = remember {mutableStateOf(1.0)}


    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull()?:0.0
        val result = when {
            inputUnit == "Centimeters" && outputUnit == "Meters" -> inputValueDouble / 100
            inputUnit == "Centimeters" && outputUnit == "KiloMeters" -> inputValueDouble / 100000
            inputUnit == "Centimeters" && outputUnit == "millimeters" -> inputValueDouble * 10
            inputUnit == "Meters" && outputUnit == "Centimeters" -> inputValueDouble * 100
            inputUnit == "Meters" && outputUnit == "KiloMeters" -> inputValueDouble / 1000
            inputUnit == "Meters" && outputUnit == "millimeters" -> inputValueDouble * 1000
            inputUnit == "KiloMeters" && outputUnit == "Centimeters" -> inputValueDouble * 100000
            inputUnit == "KiloMeters" && outputUnit == "Meters" -> inputValueDouble * 1000
            inputUnit == "KiloMeters" && outputUnit == "millimeters" -> inputValueDouble * 1000000
            inputUnit == "millimeters" && outputUnit == "Centimeters" -> inputValueDouble / 10
            inputUnit == "millimeters" && outputUnit == "Meters" -> inputValueDouble / 1000
            inputUnit == "millimeters" && outputUnit == "KiloMeters" -> inputValueDouble / 1000000
            else -> inputValueDouble
        }
        outputValue = result.toString()
    }
    Column (
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
            Text(
                "Welcome to Unit Converter ",
                style = MaterialTheme.typography.headlineMedium,
            )
        Text(
            "By Avinash",
            style = MaterialTheme.typography.headlineSmall,
        )


        // UI elements will be stacked below each other

        
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange ={
            //What should happen when value is changed
                inputValue=it
                convertUnits()
             },
            label = {Text("Enter Value")}
        )
        Spacer(modifier = Modifier.height(25.dp))
        Row {
            //input box
            Box{
                Button(onClick = { iExpanded=true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("KiloMeters") }, onClick = {
                            iExpanded =false
                            inputUnit ="KiloMeters"
                            conversionFactor.value = 1000.0
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Meters") }, onClick = {
                            iExpanded =false
                            inputUnit ="Meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            iExpanded =false
                            inputUnit ="Centimeters"
                            conversionFactor.value = 0.01
                        convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("millimeters") }, onClick = {
                            iExpanded =false
                            inputUnit ="millimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(25.dp))

            // output box
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(
                        text = { Text("KiloMeters") }, onClick = {
                            oExpanded =false
                            outputUnit ="KiloMeters"
                            conversionFactor.value = 1000.0
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Meters") }, onClick = {
                            oExpanded =false

                            outputUnit ="Meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Centimeters") }, onClick = {
                            oExpanded =false
                            outputUnit ="Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits() })
                    DropdownMenuItem(
                        text = { Text("millimeters") }, onClick = {
                            oExpanded =false
                            outputUnit ="millimeters"
                            conversionFactor.value = 0.001
                            convertUnits() }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Text("Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}


//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

@Preview(showBackground= true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}