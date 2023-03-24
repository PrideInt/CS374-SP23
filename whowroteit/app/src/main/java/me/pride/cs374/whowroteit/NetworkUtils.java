package me.pride.cs374.whowroteit;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

	private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
	private static final String BOOK_BASE_URL =  "https://www.googleapis.com/books/v1/volumes?";
	private static final String QUERY_PARAM = "q";
	private static final String MAX_RESULTS = "maxResults";
	private static final String PRINT_TYPE = "printType";

	public static String getBookInfo(String query) {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String bookJSON = null;

		try {
			Uri uri = Uri.parse(BOOK_BASE_URL)
					.buildUpon()
					.appendQueryParameter(QUERY_PARAM, query)
					.appendQueryParameter(MAX_RESULTS, "15")
					.appendQueryParameter(PRINT_TYPE, "books")
					.build();

			URL url = new URL(uri.toString());

			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			// Get the InputStream.
			InputStream inputStream = connection.getInputStream();

			reader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuilder builder = new StringBuilder();

			String line = reader.readLine();
			while (line != null) {
				builder.append(line);
				builder.append("\n");
				line = reader.readLine();
			}
			if (builder.length() == 0) return null;

			bookJSON = builder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Log.d(LOG_TAG, bookJSON);
		return bookJSON;
	}
}
