package me.pride.cs374.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

	private TextView text;
	private static final String TEXT_KEY = "textKey";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.text = findViewById(R.id.awakeText);

		if (savedInstanceState != null) {
			this.text.setText(savedInstanceState.getString(TEXT_KEY));
		}
	}

	public void startTask(View view) {
		this.text.setText(R.string.zzz);
		new SimpleAsyncTask(this.text).execute();
	}

	@Override
	protected void onSaveInstanceState(Bundle bundle) {
		super.onSaveInstanceState(bundle);
		bundle.putString(TEXT_KEY, this.text.getText().toString());
	}

	class SimpleAsyncTask extends AsyncTask<Void, Void, String> {
		private WeakReference<TextView> textReference;

		public SimpleAsyncTask(TextView text) {
			this.textReference = new WeakReference<>(text);
		}

		@Override
		protected String doInBackground(Void... voids) {
			long sleep = ThreadLocalRandom.current().nextLong(12) * 200;

			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) { }
			return "Finally awakened from my nap after " + sleep + " ms!";
		}

		@Override
		public void onPostExecute(String result) { this.textReference.get().setText(result); }
	}
}