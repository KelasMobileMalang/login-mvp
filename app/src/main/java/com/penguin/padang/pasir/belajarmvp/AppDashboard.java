package com.penguin.padang.pasir.belajarmvp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.penguin.padang.pasir.belajarmvp.model.Result;
import com.penguin.padang.pasir.belajarmvp.services.RetrofitAPIEndpointInterface;
import com.penguin.padang.pasir.belajarmvp.util.Const;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppDashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Retrofit retrofit;
    private ProgressDialog dialog;
    Button btnAsModel;
    Button btnAsJSON;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnAsModel = (Button) findViewById(R.id.bt_getasmodel);
        btnAsJSON = (Button) findViewById(R.id.bt_getasjson);
        textView = (TextView) findViewById(R.id.tv_title);


        dialog  = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        initializeRetrofit();

        btnAsModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                getDataAsModel();
            }
        });

        btnAsJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                getDataAsJSON();

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initializeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void getDataAsModel(){
        RetrofitAPIEndpointInterface apiService = retrofit.create(RetrofitAPIEndpointInterface.class);
        Call<Result> result = apiService.getResultInfo();

        result.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                dialog.dismiss();
                try {
                    String isiText = "Response version" + response.body()
                            .getInfo().getVersion() + "\n response seed " + response.body()
                            .getInfo().getSeed();

                    textView.setText(isiText);

//                    Toast.makeText(AppDashboard.this, "Response version" + response.body()
//                            .getInfo().getVersion() + "\n response seed " + response.body()
//                            .getInfo().getSeed(),
//                            Toast.LENGTH_LONG)
//                            .show();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();

            }
        });


    }

    private void getDataAsJSON(){
        RetrofitAPIEndpointInterface apiService =   retrofit.create(RetrofitAPIEndpointInterface.class);
        Call<ResponseBody> result = apiService.getResultAsJSON();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dialog.dismiss();
                try {
                    Toast.makeText(AppDashboard.this, "Response version " + response.body().string(), Toast.LENGTH_LONG).show();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });

    }
}
