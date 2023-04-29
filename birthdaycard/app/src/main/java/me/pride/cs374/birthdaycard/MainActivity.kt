package me.pride.cs374.birthdaycard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import me.pride.cs374.birthdaycard.ui.theme.BirthdayCardTheme

class MainActivity : ComponentActivity() {

	/*
	Imported functions and instances from Jetpack Compose
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			BirthdayCardTheme {
				Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
					BirthdayGreeting(greeting = "Happy Birthday", name = "John", sub = "- from Pride")
				}
			}
		}
	}
}

@Composable
fun BirthdayGreeting(greeting : String, name : String, sub : String, modifier : Modifier = Modifier) {
	val background = painterResource(R.drawable.birthday_background)

	Box {
		Image(painter = background, contentDescription = null)

		Column {
			Text(text = "$greeting, $name", fontSize = 24.sp, fontFamily = FontFamily.Cursive)
			Text(text = sub, fontSize = 12.sp, fontFamily = FontFamily.SansSerif)
		}
	}
}

/*
@Composable
fun Greeting(name: String) {
	Text(text = "Hello $name!")
}
*/

@Preview(showBackground = false)
@Composable
fun DefaultBirthdayCardPreview() {
	BirthdayCardTheme {
		BirthdayGreeting(greeting = "Happy Birthday", name = "John", sub = "- from Pride")
	}
}