package com.example.mobile_assignment6;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private EditText firstname;
    private EditText lastname;
    private EditText phone;
    private EditText edu;
    private EditText hobby;

    private AutoCompleteTextView searchFieldF;
    private AutoCompleteTextView searchFieldL;
    private AutoCompleteTextView searchFieldP;

    Button submitBtn;

    Vector<Contact> phoneCatalog = new Vector<Contact>();
    ArrayList<String> suggestionsF = new ArrayList<String>();
    ArrayList<String> suggestionsL = new ArrayList<String>();
    ArrayList<String> suggestionsP = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstname = (EditText) findViewById(R.id.firstName);
        lastname = (EditText) findViewById(R.id.lastname);
        phone = (EditText) findViewById(R.id.phone);
        edu = (EditText) findViewById(R.id.edu);
        hobby = (EditText) findViewById(R.id.hobby);
        searchFieldF = (AutoCompleteTextView) findViewById(R.id.searchFieldF);
        searchFieldL = (AutoCompleteTextView) findViewById(R.id.searchFieldL);
        searchFieldP = (AutoCompleteTextView) findViewById(R.id.searchFieldP);

        submitBtn = (Button) findViewById(R.id.submitBtn);

        searchFieldF.setThreshold(1);
        searchFieldL.setThreshold(1);
        searchFieldP.setThreshold(1);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean feedback = false;
                if (firstname.getText().length() == 0) {
                    firstname.setBackgroundColor(Color.rgb(254, 150, 150));
                    feedback = true;
                }
                if (lastname.getText().length() == 0) {
                    lastname.setBackgroundColor(Color.rgb(254, 150, 150));
                    feedback = true;
                }
                if (phone.getText().length() == 0) {
                    phone.setBackgroundColor(Color.rgb(254, 150, 150));
                    feedback = true;
                }
                if (edu.getText().length() == 0) {
                    edu.setBackgroundColor(Color.rgb(254, 150, 150));
                    feedback = true;
                }
                if (hobby.getText().length() == 0) {
                    hobby.setBackgroundColor(Color.rgb(254, 150, 150));
                    feedback = true;
                }
                if (feedback)
                    Toast.makeText(getBaseContext(), "Missing data!", Toast.LENGTH_SHORT).show();
                else {
                    Contact newContact = new Contact(firstname.getText().toString(),
                            lastname.getText().toString(),
                            phone.getText().toString(),
                            edu.getText().toString(),
                            hobby.getText().toString());
                    addToSuggestionFirstname(newContact);
                    addToSuggestionLastname(newContact);
                    addToSuggestionPhone(newContact);
                    phoneCatalog.add(newContact);
                    Toast.makeText(getBaseContext(), "Contact added", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    protected void addToSuggestionFirstname(Contact c) {
        suggestionsF.add(c.getFirstName() + " "+ c.getLastName() + " " + c.getPhoneNumber());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestionsF);
        searchFieldF.setAdapter(adapter);
    }
    protected void addToSuggestionLastname(Contact c) {
        suggestionsL.add(c.getLastName() + " "+ c.getFirstName() + " " + c.getPhoneNumber());
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestionsL);
        searchFieldL.setAdapter(adapter1);
    }
    protected void addToSuggestionPhone(Contact c) {
        suggestionsP.add(c.getPhoneNumber() + " "+ c.getFirstName() + " " + c.getLastName());
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestionsP);
        searchFieldP.setAdapter(adapter2);
    }
}
