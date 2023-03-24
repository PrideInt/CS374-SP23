package me.pride.cs374.whowroteit;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class BookLoader extends AsyncTaskLoader<String> {

	private String query;

	public BookLoader(Context context, String query) {
		super(context);
		this.query = query;
	}

	@Override
	protected void onStartLoading() {
		super.onStartLoading();
		forceLoad();
	}

	@Nullable
	@Override
	public String loadInBackground() {
		return NetworkUtils.getBookInfo(this.query);
	}
}