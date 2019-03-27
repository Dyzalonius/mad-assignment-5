package com.example.gamebacklog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddGameActivity extends AppCompatActivity {

    private TextInputEditText title, platform;
    private Spinner status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        setSupportActionBar((Toolbar)(findViewById(R.id.toolbar)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title = findViewById(R.id.textInputEditTextTitle);
        platform = findViewById(R.id.textInputEditTextPlatform);
        status = findViewById(R.id.spinnerStatus);

        FloatingActionButton button = findViewById(R.id.floatingActionButtonAddItem);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("title", title.getText().toString());
                returnIntent.putExtra("platform", platform.getText().toString());
                returnIntent.putExtra("status", status.getSelectedItem().toString());

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String today = simpleDateFormat.format(Calendar.getInstance().getTime());
                returnIntent.putExtra("date", today);

                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        // Add game status to spinner
        Spinner spinner = findViewById(R.id.spinnerStatus);
        String[] stati = getResources().getStringArray(R.array.add_item_status_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_layout, R.id.spinnerText, stati);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
