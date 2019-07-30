package com.example.test190726;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Xml;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_b);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        ViewGroup viewGroup = null;
        View childLayout1 = (View) inflater.inflate(R.layout.app_bar_main2, null);
        View childLayout2 = (View) inflater.inflate(R.layout.nav_header_main2, null);

        CoordinatorLayout coordinatorLayout = childLayout1.findViewById(R.id.app_bar);
        //System.out.println(String.valueOf(findViewById(R.id.app_bar)));

        Toolbar toolbar = new Toolbar(this);
        toolbar.setLayoutParams(new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150));
        toolbar.setBackground(getDrawable(R.color.colorPrimary));
        toolbar.setPopupTheme(R.style.AppTheme_PopupOverlay);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = new FloatingActionButton(this);
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM | Gravity.END;
        layoutParams.setMargins(16,16,16,16);
        fab.setImageDrawable(getDrawable(R.drawable.good));
        fab.setLayoutParams(layoutParams);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //System.out.println(String.valueOf(coordinatorLayout));
        coordinatorLayout.addView(toolbar);
        coordinatorLayout.addView(fab);

        LinearLayout linearLayout = childLayout2.findViewById(R.id.nav_header_main2);

        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 476);
        linearLayout.setBackground(getDrawable(R.drawable.side_nav_bar));
        linearLayoutParams.gravity = Gravity.BOTTOM | Gravity.START;
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(16,16,16,16);
        //setTheme(R.style.ThemeOverlay_AppCompat_Dark);
        linearLayout.setLayoutParams(linearLayoutParams);

        ImageView img1 = new ImageView(this);
        img1.setImageDrawable(getDrawable(R.mipmap.ic_launcher_round));
        img1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        img1.setContentDescription("Navigation Header");
        img1.setPadding(0,8,0,0);

        TextView tv1 = new TextView(this);
        tv1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv1.setPadding(0,8,0,0);
        tv1.setTextAppearance(R.style.TextAppearance_AppCompat_Body1);
        tv1.setText("Android Studio");

        TextView tv2 = new TextView(this);
        tv2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv2.setText(R.string.nav_header_subtitle);

        linearLayout.addView(img1);
        linearLayout.addView(tv1);
        linearLayout.addView(tv2);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        //drawerLayout.openDrawer(Gravity.END);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = new NavigationView(this);
        navigationView.setLayoutParams(new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        navigationView.setFitsSystemWindows(false);
        navigationView.addHeaderView(childLayout2);
        navigationView.inflateMenu(R.menu.activity_main2_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout.addView(childLayout1);
        //drawerLayout.addView(childLayout2);
        //drawerLayout.addView(navigationView);
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
        getMenuInflater().inflate(R.menu.main2, menu);
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
}
