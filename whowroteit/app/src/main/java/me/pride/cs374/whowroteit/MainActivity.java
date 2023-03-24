package me.pride.cs374.whowroteit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
// import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

	private EditText bookInput;
	private TextView titleText;
	private TextView authorText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.bookInput = findViewById(R.id.bookInput);
		this.titleText = findViewById(R.id.titleText);
		this.authorText = findViewById(R.id.authorText);
	}

	public void searchBooks(View view) {
		String query = this.bookInput.getText().toString();

		InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

		if (inputManager != null) {
			inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
		ConnectivityManager connectManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = null;

		if (connectManager != null) {
			networkInfo = connectManager.getActiveNetworkInfo();
		}
		if (networkInfo != null && networkInfo.isConnected() && query.length() != 0) {
			Bundle bundle = new Bundle();
			bundle.putString("queryString", query);
			getSupportLoaderManager().restartLoader(0, bundle, this);

			this.authorText.setText("");
			this.titleText.setText(R.string.loading); this.titleText.setTextSize(20);
		} else if (query.length() == 0) {
			this.authorText.setText("");
			this.titleText.setText(R.string.no_search_term);
		} else {
			this.authorText.setText("");
			this.titleText.setText(R.string.no_network);
		}
	}

	@NonNull
	@Override
	public Loader<String> onCreateLoader(int id, @Nullable Bundle bundle) {
		String query = "";

		if (bundle != null) {
			query = bundle.getString("queryString");
		}
		return new BookLoader(this, query);
	}

	@Override
	public void onLoadFinished(@NonNull Loader<String> loader, String data) {
		try {
			JSONObject json = new JSONObject(data);
			JSONArray jsonArray = json.getJSONArray("items");

			String title = getString(R.string.no_results), authors = "";

			this.authorText.setTextSize(22);
			this.titleText.setTextSize(22);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject book = jsonArray.getJSONObject(i);
				JSONObject volumeInfo = book.getJSONObject("volumeInfo");

				try {
					title = volumeInfo.getString("title");
					authors = volumeInfo.getString("authors");
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (!title.equalsIgnoreCase(getString(R.string.no_results)) && !authors.isEmpty()) {
					title = "\"" + title + "\"";
					authors = "by " + authors.substring(2, authors.length() - 2);
					break;
				}
			}
			this.titleText.setText(title);
			this.authorText.setText(authors);
		} catch (JSONException e) {
			this.titleText.setText(R.string.no_results);
			this.authorText.setText("");
			e.printStackTrace();
		}
	}

	@Override
	public void onLoaderReset(@NonNull Loader<String> loader) { }

	/*
	class FetchBook extends AsyncTask<String, Void, String> {
		private WeakReference<TextView> titleText, authorText;

		public FetchBook(TextView titleText, TextView authorText) {
			this.titleText = new WeakReference<>(titleText);
			this.authorText = new WeakReference<>(authorText);
		}

		@Override
		protected String doInBackground(String... strings) {
			return NetworkUtils.getBookInfo(strings[0]);
		}

		@Override
		protected void onPostExecute(String string) {
			super.onPostExecute(string);

			try {
				JSONObject json = new JSONObject(string);
				JSONArray jsonArray = json.getJSONArray("items");

				String title = getString(R.string.no_results), authors = "";

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject book = jsonArray.getJSONObject(i);
					JSONObject volumeInfo = book.getJSONObject("volumeInfo");

					try {
						title = volumeInfo.getString("title");
						authors = volumeInfo.getString("authors");
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (!title.equalsIgnoreCase(getString(R.string.no_results)) && !authors.isEmpty()) {
						break;
					}
				}
				this.titleText.get().setText(title);
				this.authorText.get().setText(authors);
			} catch (JSONException e) {
				this.titleText.get().setText(R.string.no_results);
				this.authorText.get().setText("");
				e.printStackTrace();
			}
		}
	}
	 */
}