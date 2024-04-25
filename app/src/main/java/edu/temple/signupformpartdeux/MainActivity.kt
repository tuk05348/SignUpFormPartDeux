package edu.temple.signupformpartdeux

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.temple.signupformpartdeux.ui.theme.SignUpFormPartDeuxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignUpFormPartDeuxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignUpForm("Android")
                }
            }
        }
    }
}

@Composable
fun SignUpForm(name: String, modifier: Modifier = Modifier) {
    Column (horizontalAlignment = Alignment.CenterHorizontally) {

        var userName by rememberSaveable { mutableStateOf("") }
        var userNameError by rememberSaveable { mutableStateOf(false) }
        
        var userEmail by rememberSaveable { mutableStateOf("") }
        var userAge by rememberSaveable { mutableStateOf("") }
        val context = LocalContext.current

        Text(
            text = "Sign Up Form",
            modifier = modifier.padding(8.dp),
            fontSize = 28.sp
        )

        TextField(
            value = userName,
            isError = userNameError,
            onValueChange = {
                userName = it
                userNameError = false},
            label = {Text("Name")}
        )

        TextField(
            value = userEmail,
            onValueChange = {userEmail = it},
            label = {Text("Email")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        TextField(
            value = userAge,
            onValueChange = {userAge = it},
            label = {Text("Age")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Button(onClick = {
            var message: String
            if (userName.isNotBlank())
                message = "Thank you for signing up!"
            else {
                message = "Please complete the form"
                userNameError = true
            }

            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

        }) {
            Text("Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SignUpFormPartDeuxTheme {
        SignUpForm("Android")
    }
}