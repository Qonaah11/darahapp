package com.qonaah.donor.app.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.qonaah.donor.app.R;
import com.qonaah.donor.app.fragment.DaruratFragment;
import com.qonaah.donor.app.fragment.DonorDarahFragment;
import com.qonaah.donor.app.fragment.PmiFragment;
import com.qonaah.donor.app.fragment.StokDarahFragment;
import com.qonaah.donor.app.fragment.TahukahFragment;
import com.qonaah.donor.app.fragment.TentangFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private boolean doubleBackClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /**
         * Tampilan awal
         * */
        getSupportFragmentManager().beginTransaction().add(R.id.content_main, new DonorDarahFragment(), "DonorDarahFragment").commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackClick) {
                super.onBackPressed();
                return;
            }

            this.doubleBackClick = true;
            Toast.makeText(this, R.string.text_back, Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackClick = false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            dialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        String title = null;
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_donor_darah) {
            fragment = new DonorDarahFragment();
            title = "Jadwal Donor Darah";
        } else if (id == R.id.nav_stok_darah) {
            fragment = new StokDarahFragment();
            title = "Stok Darah";
        } else if (id == R.id.nav_tahukah) {
            fragment = new TahukahFragment();
            title = "Tahukah Kamu?";
        } else if (id == R.id.nav_tentang) {
            fragment = new TentangFragment();
            title = "Tentang Aplikasi";
        } else if (id == R.id.nav_kontak) {
            fragment = new PmiFragment();
            title = "Kontak PMI";
        } else if (id == R.id.nav_panggilan_darurat) {
            fragment = new DaruratFragment();
            title = "Panggilan Darurat";
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_main, fragment);
            fragmentTransaction.commit();
            setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void dialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Peringatan")
                .setMessage("Yakin akan keluar aplikasi ini ?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }
}

