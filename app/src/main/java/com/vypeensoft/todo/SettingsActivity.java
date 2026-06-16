package com.vypeensoft.todo;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    private EditText editPath;
    private AppSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Settings");
        }

        editPath = findViewById(R.id.editPath);
        Button btnSave = findViewById(R.id.btnSaveSettings);

        settings = AppSettings.load();
        editPath.setText(settings.masterListPath);

        btnSave.setOnClickListener(v -> {
            String newPath = editPath.getText().toString().trim();
            if (!newPath.isEmpty()) {
                if (!newPath.endsWith("/")) {
                    newPath += "/";
                }
                settings.masterListPath = newPath;
                settings.save();
                Toast.makeText(this, "Settings saved to settings.json", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Path cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
