package com.dmss.nytnews.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dmss.nytnews.Model.Adapters.ArticlesListAdapter;
import com.dmss.nytnews.Model.Common;
import com.dmss.nytnews.Model.Pojo.NewsModel;
import com.dmss.nytnews.Model.TransparentProgressDialog;
import com.google.android.material.navigation.NavigationView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dmss.nytnews.Controller.Controller;
import com.dmss.nytnews.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements Controller.ApiCallBack, NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    Controller controller;
    int selectedSection = Common.VIEWED_SECTION, selectedDuration = Common.DURATION_ONE;
    int selectedId,selectedDurationId;

    ArrayList<NewsModel> newsModels = new ArrayList<>();
    ArticlesListAdapter articlesListAdapter;
    RecyclerView articlesRecyclerView;
    TextView noDataTextView;
    Button retryButton;


    TransparentProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        controller = new Controller(MainActivity.this);

        initializeUI();
        initializeDrawerLayout(toolbar);
        callApi();
    }

    public void retry(View view) {
        callApi();
    }

    public void initializeUI() {
        dialog = new TransparentProgressDialog(MainActivity.this, R.drawable.ic_loading);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        articlesRecyclerView = (RecyclerView) findViewById(R.id.articlesRecyclerView);
        articlesRecyclerView.setHasFixedSize(true);
        articlesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        noDataTextView = (TextView) findViewById(R.id.noDataTextView);
        retryButton = (Button) findViewById(R.id.retryButton);
    }

    public void initializeDrawerLayout(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setCheckedItem(R.id.nav_view);
        selectedId = R.id.nav_view;
        selectedDurationId = R.id.action_one_day;
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void callApi() {
        UpdateHeading();

        articlesRecyclerView.setVisibility(View.GONE);
        dialog.show();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                controller.startFetching(selectedDuration, selectedSection);
            }
        });
        t.start();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        // searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(MainActivity.this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(selectedDurationId != id){
            selectedDurationId = id;
            switch (id) {
                case R.id.action_one_day:
                    selectedDuration = Common.DURATION_ONE;
                    callApi();
                    return true;
                case R.id.action_seven_days:
                    selectedDuration = Common.DURATION_SEVEN;
                    callApi();
                    return true;
                case R.id.action_thirty_days:
                    selectedDuration = Common.DURATION_THIRTY;
                    callApi();
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();
        if (selectedId != id) {
            selectedId = id;
            if (id == R.id.nav_view) {
                selectedSection = Common.VIEWED_SECTION;
            } else if (id == R.id.nav_email) {
                selectedSection = Common.EMAILED_SECTION;
            } else if (id == R.id.nav_share) {
                selectedSection = Common.SHARED_SECTION;
            }
            callApi();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onErrorApiCall(String error) {
        String test = error;
        Log.e("NYT Api Error", test);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateNoDataUI(getString(R.string.fetchError));
            }
        });
    }

    @Override
    public void onSuccessApiCall(String result) {
        String test = result;
        Log.v("NYT Api Success", test);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                newsModels = controller.parseData(result);
                if (newsModels == null) {
                    updateNoDataUI(getString(R.string.parseError));
                } else if (newsModels.size() == 0) {
                    updateNoDataUI(getString(R.string.noData));
                } else {
                    retryButton.setVisibility(View.GONE);
                    noDataTextView.setVisibility(View.GONE);
                    articlesRecyclerView.setVisibility(View.VISIBLE);
                    articlesListAdapter = new ArticlesListAdapter(newsModels, MainActivity.this, MainActivity.this);
                    articlesRecyclerView.setAdapter(articlesListAdapter);
                    dialog.cancel();
                }
            }
        });
    }

    public void updateNoDataUI(String error) {
        if (error.contains("No")) {
            retryButton.setVisibility(View.GONE);
        } else {
            retryButton.setVisibility(View.VISIBLE);
        }
        noDataTextView.setText(error);
        noDataTextView.setVisibility(View.VISIBLE);
        articlesRecyclerView.setVisibility(View.GONE);

        dialog.cancel();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filter(newText);
        return false;
    }

    public void UpdateHeading() {
        switch (selectedSection) {
            case Common.VIEWED_SECTION:
                getSupportActionBar().setTitle(getString(R.string.app_name) + " - " + getString(R.string.view));
                break;
            case Common.EMAILED_SECTION:
                getSupportActionBar().setTitle(getString(R.string.app_name) + " - " + getString(R.string.email));
                break;
            case Common.SHARED_SECTION:
                getSupportActionBar().setTitle(getString(R.string.app_name) + " - " + getString(R.string.shared));
                break;
        }
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<NewsModel> filteredModels = new ArrayList<>();

        //looping through existing elements
        for (NewsModel news : newsModels) {
            //if the existing elements contains the search input
            if (news.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                    news.getByline().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filteredModels.add(news);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        articlesListAdapter.filterList(filteredModels);
    }
}