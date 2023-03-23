package me.pride.cs374.whowroteit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private EditText bookInput;
	private TextView titleText;
	private TextView authorText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// this.bookInput = (EditText) findViewById(R.id)
	}
}