package com.example.assii2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assii2.R;
import com.example.assii2.RegisterActivity;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;
    private Button registerButton;
    public static String DATA="DATA";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private CheckBox checkBox;
    private EditText name,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.buttonLogin);
        registerButton = findViewById(R.id.buttonRegister);
        checkBox = findViewById(R.id.checkBoxRememberMe);
        name=findViewById(R.id.textInputLayoutUsername);
        pass=findViewById(R.id.textInputLayoutPassword);
        setupSharedprefs();
        String x = prefs.getString(DATA, "");
        Gson gson=new Gson();
            if (!x .equals("")) {
                user x1 =gson.fromJson(x,user.class);
                name.setText(x1.getName());
                pass.setText(x1.getPass());
            }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = name.getText().toString();
                String password = pass.getText().toString();

                // Check if "Remember Me" is checked
                if (checkBox.isChecked()) {

                    // Save username and password to SharedPreferences
                    user x=new user(username,password);
                    String p=gson.toJson(x);
                    editor.putString(DATA,p);
                    editor.commit();
                }
                startActivity(new Intent(MainActivity.this, api1.class));


            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));

            }
        });



    }
    public void setupSharedprefs(){
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor=prefs.edit();
    }
    private void saveCredentials(String username, String password) {

        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }
}
