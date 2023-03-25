package me.pride.cs374.conversionprideyin3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

/*
Euros to dollars conversion tool created by Pride Y.
Version 3
 */
public class MainActivity extends AppCompatActivity {

	private EditText input;
	private TextView eurosText, dollarsText;
	private TextView validate;

	private double currency;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.input = findViewById(R.id.input);
		this.eurosText = findViewById(R.id.eurosText);
		this.dollarsText = findViewById(R.id.dollarsText);
		this.validate = findViewById(R.id.validate);
	}

	/**
	 * The convert method uses the given input of the EditText input and displays
	 * the given input currency (in euros) and the converted currency (usd, by multiplying by 1.13).
	 * The results are rounded to the nearest hundredth place.
	 * If the input is NOT a number (e.g., integer, decimal/double), then it will display a red
	 * "INVALID" above the EditText box, as well as N/A for the euros and dollars TextViews.
	 *
	 * - Pride Y.
	 * @param view
	 */
	public void convert(View view) {
		try {
			this.currency = Double.valueOf(this.input.getText().toString());

			DecimalFormat format = new DecimalFormat("#.00");

			this.eurosText.setText("Euros: €" + format.format(this.currency));
			this.dollarsText.setText("Dollars: $" + format.format(this.currency * 1.13));

			this.validate.setText("");
		} catch (Exception e) {
			this.validate.setText("INVALID");
			this.eurosText.setText("Euros: N/A");
			this.dollarsText.setText("Dollars: N/A");
		}
	}
}