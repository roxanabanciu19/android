package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.rate_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                TextView textView = findViewById(R.id.movie_title);
                textView.setText(getString(R.string.onClick));
            }
        });
        // set text for search view
        LinearLayout linearLayout1 = (LinearLayout) ((SearchView)findViewById(R.id.search_view)).getChildAt(0);
        LinearLayout linearLayout2 = (LinearLayout) linearLayout1.getChildAt(2);
        LinearLayout linearLayout3 = (LinearLayout) linearLayout2.getChildAt(1);
        AutoCompleteTextView autoComplete = (AutoCompleteTextView) linearLayout3.getChildAt(0);
        autoComplete.setTextSize(10);

        Spinner spinner = (Spinner) findViewById(R.id.movies_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.movie_spinner_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        ArrayList<String> movieList = new ArrayList<String>();
        final ListView mainListView = (ListView) findViewById(R.id.mainListView);

        //when movie category is selected
        final ArrayAdapter<String> movieListAdapter = new ArrayAdapter<String>(this,R.layout.simplerow);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                if (movieListAdapter.getCount() > 0) {
                    movieListAdapter.clear();
                }

                TextView textView = findViewById(R.id.movie_title);
                textView.setText(R.string.default_text);
                TextView movieCounter = findViewById(R.id.movie_counter);

                switch (pos) {
                    case 0: // comedy
                        movieListAdapter.addAll(getResources().getStringArray(R.array.comedy_category));
                        break;
                    case 1: //thriller
                        movieListAdapter.addAll(getResources().getStringArray(R.array.thriller_category));
                        break;
                    case 2: // science_fiction
                        movieListAdapter.addAll(getResources().getStringArray(R.array.science_fiction_category));
                        break;
                    case 3: // drama
                        movieListAdapter.addAll(getResources().getStringArray(R.array.drama_category));
                        break;

                    case 4: //musical
                        movieListAdapter.addAll(getResources().getStringArray(R.array.musical_category));
                        break;
                    case 5: //action
                         movieListAdapter.addAll(getResources().getStringArray(R.array.action_category));
                        break;
                }
                    // Set the ArrayAdapter as the ListView's adapter
                    mainListView.setAdapter( movieListAdapter );

                    movieCounter.setText(movieListAdapter.getCount() + " " + getString(R.string.movieCounterText));

            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback

            }
        });

        //when a movie is selected
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                TextView textView = findViewById(R.id.movie_title);
                textView.setText(adapterView.getItemAtPosition(index).toString());
            }
        });
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.messageText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}

