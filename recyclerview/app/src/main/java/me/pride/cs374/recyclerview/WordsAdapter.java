package me.pride.cs374.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordsAdapter extends RecyclerView.Adapter<WordViewHolder> {
	private LinkedList<String> list;
	private LayoutInflater inflater;

	public WordsAdapter(Context context, LinkedList<String> list) {
		this.inflater = LayoutInflater.from(context);
		this.list = list;
	}

	@NonNull
	@Override
	public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = this.inflater.inflate(R.layout.words, parent, false);
		return new WordViewHolder(view, this);
	}

	@Override
	public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
		holder.view().setText(this.list.get(position));
	}

	@Override
	public int getItemCount() {
		return this.list.size();
	}
	public LinkedList<String> list() {
		return this.list;
	}
}

class WordViewHolder extends RecyclerView.ViewHolder {
	private TextView view;
	private WordsAdapter adapter;

	public WordViewHolder(View view, WordsAdapter adapter) {
		super(view);
		this.view = itemView.findViewById(R.id.word);
		this.adapter = adapter;

		this.view.setOnClickListener(clickView -> {
			String element = this.adapter.list().get(getLayoutPosition());

			this.adapter.list().set(getLayoutPosition(), "Clicked! " + element);
			this.adapter.notifyDataSetChanged();
		});
	}
	public TextView view() {
		return this.view;
	}
}