package com.example.monika.navdrawer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ReadingFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        HomeFragment HomeFragment=new HomeFragment();
        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.relativeLayout_for_fragment,HomeFragment,
                HomeFragment.getTag()).commit();
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.home) {
            // Handle the camera action
            //Toast.makeText(MainActivity.this, "Camera", Toast.LENGTH_SHORT).show();
         //   Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();

            HomeFragment HomeFragment=new HomeFragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().
                   // setCustomAnimations(R.anim.zoom_in,R.anim.zoom_out).
            replace(R.id.relativeLayout_for_fragment,HomeFragment,
                    HomeFragment.getTag()).commit();


        } else if (id == R.id.profile) {
            //Toast.makeText(MainActivity.this, "Gallary", Toast.LENGTH_SHORT).show();
           // Toast.makeText(MainActivity.this, "Your Profile", Toast.LENGTH_SHORT).show();
            ProfileFragment ProfileFragment=new ProfileFragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction()
                    //.setCustomAnimations(R.anim.zoom_in,R.anim.zoom_out)
                    .replace(R.id.relativeLayout_for_fragment,ProfileFragment,
                    ProfileFragment.getTag()).commit();
        }



        else if (id == R.id.history) {

            HistoryFragment History=HistoryFragment.newInstance("SomeOne","SomeTwo");
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction()
                    //setCustomAnimations(R.anim.zoom_in,R.anim.zoom_out)
                    .replace(R.id.relativeLayout_for_fragment,History,
                    History.getTag()).commit();


        } else if (id == R.id.logout) {

        } else if (id == R.id.measure) {

        } else if (id == R.id.readings) {
            ReadingFragment reading=ReadingFragment.newInstance(10);
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction()
            //setCustomAnimations(R.anim.zoom_in,R.anim.zoom_out)
                    .replace(R.id.relativeLayout_for_fragment,reading,
                    reading.getTag()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(String data) {
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }
}
