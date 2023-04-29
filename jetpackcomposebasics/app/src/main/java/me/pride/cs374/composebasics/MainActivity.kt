package me.pride.cs374.composebasics

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.pride.cs374.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			ComposeBasicsTheme {
				ComposeApp(modifier = Modifier.fillMaxSize())
			}
		}
	}
}

@Composable
fun ComposeApp(modifier: Modifier = Modifier) {
	var boarding by rememberSaveable {
		mutableStateOf(true)
	}
	Surface(modifier, color = MaterialTheme.colorScheme.background) {
		if (boarding) {
			OnboardingScreen(clicked = {
				boarding = false
			})
		} else {
			GreetingList()
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String) {
	Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary), modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
		GreetingContent(name)
	}
}

@Composable
fun GreetingContent(name: String) {
	var expanded by rememberSaveable {
		mutableStateOf(false)
	}
	Row(modifier = Modifier
		.padding(30.dp)
		.animateContentSize(
			animationSpec = spring(
				dampingRatio = Spring.DampingRatioMediumBouncy,
				stiffness = Spring.StiffnessLow
			)
		)) {
		Column(modifier = Modifier
			.weight(1f)
			.padding(10.dp)) {
			Text(text = "Hello,", fontFamily = FontFamily.Monospace)
			Text(text = name, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Medium))

			if (expanded) {
				Text(text = "\nYou just expanded this card! Awesome!")
			}
		}
		ElevatedButton(onClick = {
			expanded = !expanded
		}) {
			Text(if (expanded) "Clicked" else "Click me")
		}
	}
}

@Composable
private fun GreetingList(modifier: Modifier = Modifier, names: List<String> = listOf("Pride", "Me", "Nobody", "Greetings!", "Card", "Lovely", "Christelle", "Dark")) {
	LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
		items(items = names) {
				name -> Greeting(name = name)
		}
	}
}

@Composable
fun OnboardingScreen(clicked: () -> Unit, modifier: Modifier = Modifier) {
	Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
		Text("Board on the Jetpack Compose App!")

		Button(modifier = Modifier.padding(vertical = 24.dp), onClick = clicked) {
			Text("BOARD")
		}
		Image(painter = painterResource(id = R.drawable.jetpack_shark), contentDescription = null)
	}
}

@Preview(showBackground = true, widthDp = 320, uiMode = UI_MODE_NIGHT_YES, name = "Dark")
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
	ComposeBasicsTheme {
		GreetingList()
	}
}

@Preview
@Composable
fun MyAppPreview() {
	ComposeBasicsTheme {
		ComposeApp(Modifier.fillMaxSize())
	}
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
	ComposeBasicsTheme {
		OnboardingScreen(clicked = {})
	}
}