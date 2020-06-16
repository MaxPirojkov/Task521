package com.example.task521;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText editLogin, editPass;
    Button btnLogin, btnRegis;

    String filenameLog = "Login.txt";
    String filenamePass = "Password.txt";
    String txtLogin;
    String txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editLogin = findViewById(R.id.editLogin);
        editPass = findViewById(R.id.editPass);

        btnLogin = findViewById(R.id.btnLog);
        btnRegis = findViewById(R.id.btnReg);

        tapBtnRegis();


        tapBtnLog();

    }

    private void tapBtnLog() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileInputStream = openFileInput(filenameLog);
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    txtLogin = reader.readLine();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    FileInputStream fileInputStream = openFileInput(filenamePass);
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    txtPass = reader.readLine();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                if (txtLogin == null && txtPass == null){
                    Toast.makeText(MainActivity.this, R.string.userUknowReg, Toast.LENGTH_LONG).show();
                } else if (editLogin.getText().toString().equals(txtLogin) && editPass.getText().toString().equals(txtPass) ) {

                    Toast.makeText(MainActivity.this, getString(R.string.wealcome) + txtLogin.toString(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(MainActivity.this, R.string.userUnknow, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void tapBtnRegis() {
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editLogin.length() < 1 && editPass.length() < 1) {
                    Toast.makeText(MainActivity.this, R.string.emptyText, Toast.LENGTH_LONG).show();
                } else {
                        String fileContentsLog = editLogin.getText().toString();
                        FileOutputStream fos;

                        try {
                        fos = openFileOutput(filenameLog, Context.MODE_PRIVATE);
                        fos.write(fileContentsLog.getBytes());
                        fos.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                        String fileContentsPass = editPass.getText().toString();

                        try {
                            fos = openFileOutput(filenamePass, Context.MODE_PRIVATE);
                            fos.write(fileContentsPass.getBytes());
                            fos.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    Toast.makeText(MainActivity.this, R.string.succesReg, Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
