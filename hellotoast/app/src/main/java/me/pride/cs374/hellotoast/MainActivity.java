package me.pride.cs374.hellotoast;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private TextView toastCount;
	private int count;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.toastCount = (TextView) findViewById(R.id.toast_count);
	}

	public void showToast(View view) {
		Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_LONG);
		toast.show();
	}

	public void toastUp(View view) {
		this.count++;

		if (this.toastCount != null) {
			this.toastCount.setText("" + count);
		}
	}
}