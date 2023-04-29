package me.pride.cs374.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.pride.cs374.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			BusinessCardTheme {
				Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    BusinessCard(full_name = "Pride Full", title = "Intro to Mobile App Dev", number = "1234567890", handle = "@pride", email = "pride@app.com")
				}
			}
		}
	}
}

@Composable
fun BusinessCard(full_name: String, title: String, number: String, handle: String, email: String) {
	Column(modifier = Modifier.fillMaxSize().padding(vertical = 100.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.mail_icon), contentDescription = null)
			Text(text = "$full_name", style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Black), fontFamily = FontFamily.Cursive)
			Text(text = "$title", modifier = Modifier.padding(vertical = 12.dp), fontFamily = FontFamily.SansSerif)
		}
		Column(modifier = Modifier.padding(vertical = 125.dp), horizontalAlignment = Alignment.CenterHorizontally) {
			Row {
				Icon(painter = painterResource(id = R.drawable.telephone_icon), modifier = Modifier.size(24.dp), contentDescription = null)
				Text(text = "$number", modifier = Modifier.padding(horizontal = 16.dp), fontFamily = FontFamily.Monospace)
			}
			Row {
				Icon(painter = painterResource(id = R.drawable.at_sign_icon), modifier = Modifier.size(24.dp), contentDescription = null)
				Text(text = "$handle", modifier = Modifier.padding(horizontal = 16.dp), fontFamily = FontFamily.Monospace)
			}
			Row {
				Icon(painter = painterResource(id = R.drawable.email_icon), modifier = Modifier.size(24.dp), contentDescription = null)
				Text(text = "$email", modifier = Modifier.padding(horizontal = 16.dp), fontFamily = FontFamily.Monospace)
			}
		}
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	BusinessCardTheme {
		BusinessCard(full_name = "Pride Full", title = "Intro to Mobile App Dev", number = "1234567890", handle = "@pride", email = "pride@app.com")
	}
}