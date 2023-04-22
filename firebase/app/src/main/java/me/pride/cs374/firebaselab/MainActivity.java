package me.pride.cs374.firebaselab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
	private EditText firstNameInput, lastNameInput;
	private ListView employeesView;
	private DatabaseReference reference;

	private Set<Character> alphabet = new HashSet<>(Arrays.asList(
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.firstNameInput = findViewById(R.id.firstname);
		this.lastNameInput = findViewById(R.id.lastname);
		this.employeesView = findViewById(R.id.employees);

		FirebaseDatabase db = FirebaseDatabase.getInstance();
		this.reference = db.getReference().child("employees");

		List<String> employees = new ArrayList<>();
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employees);
		this.employeesView.setAdapter(adapter);

		this.reference.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
				employees.add(snapshot.getValue(String.class));
				adapter.notifyDataSetChanged();
			}
			@Override
			public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
				adapter.notifyDataSetChanged();
			}
			@Override
			public void onChildRemoved(@NonNull DataSnapshot snapshot) { }
			@Override
			public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
			@Override
			public void onCancelled(@NonNull DatabaseError error) { }
		});
	}

	public void onClick(View view) {
		String firstName = this.firstNameInput.getText().toString(), lastName = this.lastNameInput.getText().toString();
		boolean valid = !firstName.isEmpty() && !lastName.isEmpty();

		if (!valid) {
			Toast.makeText(MainActivity.this, "Invalid inputs.", Toast.LENGTH_LONG).show();
		} else {
			for (int f = 0; f < firstName.length(); f++) {
				if (!this.alphabet.contains(firstName.charAt(f))) {
					valid = false;
					break;
				}
			}
			for (int l = 0; l < lastName.length(); l++) {
				if (!this.alphabet.contains(lastName.charAt(l))) {
					valid = false;
					break;
				}
			}
			if (valid) {
				this.reference.push().setValue(new Employee(firstName, lastName).toString()).addOnFailureListener(this, e -> {
					Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
				});
			} else {
				Toast.makeText(MainActivity.this, "Invalid inputs.", Toast.LENGTH_LONG).show();
			}
		}
	}

	class Employee {
		private String first, last;

		public Employee(String first, String last) {
			this.first = first;
			this.last = last;
		}
		public String firstName() { return this.first; }
		public String lastName() { return this.last; }

		@Override
		public String toString() { return this.last + ", " + this.first; }
	}
}