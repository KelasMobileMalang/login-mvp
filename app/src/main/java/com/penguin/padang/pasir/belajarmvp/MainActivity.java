package com.penguin.padang.pasir.belajarmvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.penguin.padang.pasir.belajarmvp.com.penguin.padang.pasir.belajarmvp.presenter.LoginPresenter;
import com.penguin.padang.pasir.belajarmvp.com.penguin.padang.pasir.belajarmvp.presenter.LoginPresenterImp;
import com.penguin.padang.pasir.belajarmvp.com.penguin.padang.pasir.belajarmvp.views.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter presenter;
    private Button loginSubmit;
    private EditText loginUsername;
    private EditText loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginUsername = (EditText) findViewById(R.id.username);
        loginPassword = (EditText) findViewById(R.id.password);
        loginSubmit = (Button) findViewById(R.id.submit);

        presenter = new LoginPresenterImp(this);

        loginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(loginUsername.getText().toString(), loginPassword.getText().toString());
            }
        });

    }

    @Override
    public void showValidationError() {
        Toast.makeText(this, "Please Fill Username and Password!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(MainActivity.this, AppDashboard.class);
        startActivity(intent);
    }

    @Override
    public void loginError() {
        Toast.makeText(this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show();
    }
}
