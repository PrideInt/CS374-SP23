package me.pride.cs374.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

	private TextView prideWasHere;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.prideWasHere = (TextView) findViewById(R.id.pridewashere);
	}

	public void clickMe(View view) {
		if (this.prideWasHere != null) {
			this.prideWasHere.setText(generateRandomText());
		}
	}

	/**
	 * Generates an anagram of the word "pride" using both upper and lettercases
	 * @return
	 */
	private String generateRandomText() {
		String pride = "pride", prideUp = pride.toUpperCase();
		StringBuilder build = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < pride.length(); i++) {
			build.append(random.nextBoolean() ? pride.charAt(i) : prideUp.charAt(i));
		}
		return build.toString() + " was here";
	}
}