package com.example.internalstoragedemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etMessage;
    Button btnSaveMessage, btnDisplayMessage;
    SharedPreferences preferences;
    FileOutputStream fos;
    FileInputStream fis;
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMessage = (EditText) findViewById(R.id.et_message);
        btnSaveMessage = (Button) findViewById(R.id.bt_savemessage);
        btnDisplayMessage = (Button) findViewById(R.id.bt_displaymessage);
        tvDisplay = (TextView) findViewById(R.id.tv_display);

    }


    public void saveMessage (View view) {
        String message = etMessage.getText().toString();
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Message saved!", Toast.LENGTH_SHORT).show();
    }

    public void displayMessage (View view) {
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput("output.txt");
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            } fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvDisplay.setText(buffer.toString());
    }
}
