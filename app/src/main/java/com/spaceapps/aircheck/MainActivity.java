package com.spaceapps.aircheck;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.spaceapps.aircheck.Fragments.MapFragment;
import com.spaceapps.aircheck.Fragments.TravelFragment;
import com.spaceapps.aircheck.Fragments.ViewPagerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

/*
    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
*/

    private DrawerLayout drawerLayout;
    private String drawerTitle;


    @InjectView(R.id.viewpager) ViewPager _viewPager;
    private TextView _usernameHeader;
    private TextView _emailHeader;
    @InjectView(R.id.tabs) TabLayout _tabLayout;

    private int[] tabIcons = {
            R.drawable.ic_map,
            R.drawable.ic_stats
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //checkLoginVariables();

        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        /*
        Set toolbar, viewpager and tablayout
         */
        setToolbar(); // Setear Toolbar como action bar

        _viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(_viewPager);

        _tabLayout = (TabLayout) findViewById(R.id.tabs);
        _tabLayout.setupWithViewPager(_viewPager);

        View header = (View)getLayoutInflater().inflate(R.layout.nav_header, null);
        _usernameHeader = (TextView) header.findViewById(R.id.usernameHeader);
        _emailHeader = (TextView) header.findViewById(R.id.emailHeader);

        /*
        Set Navigation Drawer
         */
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }

    /**
     * ViewPager setup
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),MainActivity.this);

        /**
         * Add Fragments which are in the viewpager's tab
         */

        adapter.addFragment(new MapFragment(), "Map");
        adapter.addFragment(new TravelFragment(), "Travel");
        viewPager.setAdapter(adapter);
    }

    /**
     * Toolbar Setup
     */
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {

            ab.setDisplayShowTitleEnabled(false);
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Navigation Drawer Setup
     * @param navigationView
     */
    private void setupDrawerContent(final NavigationView navigationView) {
        _usernameHeader.setText("lalala");
        _emailHeader.setText("usuario@fcirce.es");
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Marcar item presionado
                        menuItem.setChecked(true);
                        // Crear nuevo fragmento
                        String title = menuItem.getTitle().toString();
                        Log.d("ITEM", title);
//                        selectItem(title);
                        switch (title) {
                            case "Home":
                                drawerLayout.closeDrawers();
                                return true;
                            case "Ayuda":
                                return true;
                            case "Información Personal":
                                Intent next;
                                next = new Intent(getApplicationContext(), PersonalInfoActivity.class);
                                startActivity(next);
                            case "Ajustes":
                                return true;
                            case "Cerrar Sesión":
                                finish();
                            default:
                        }
                        return true;
                    }
                }
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        //checkLoginVariables();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("DBG", "Resume");

    }

    @Override
    public void onRestart() {
        super.onRestart();

    }
    @Override
    public void onDestroy() {
        Log.d("DBG","Destroy");
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getMenuInflater().inflate(R.menu.menu_qr, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Salir")
                .setMessage("¿Está seguro de que quiere abandonar la aplicación?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                }).create().show();
    }
}