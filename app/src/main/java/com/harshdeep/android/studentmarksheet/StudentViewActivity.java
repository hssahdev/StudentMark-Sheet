package com.harshdeep.android.studentmarksheet;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.harshdeep.android.studentmarksheet.data.StudentContract;

public class StudentViewActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private StudentCursorAdapter mAdapter;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem view = menu.findItem(R.id.delete_all);
        view.setVisible(LoginActivity.loginDone);
        MenuItem view1 = menu.findItem(R.id.action_settings);
        view1.setVisible(!LoginActivity.loginDone);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        if(LoginActivity.loginDone){
            ActionBar bar = getSupportActionBar();
            bar.setDisplayHomeAsUpEnabled(true);
            fab.setVisibility(View.VISIBLE);
            setTitle("Edit Marksheet");
            invalidateOptionsMenu();
        }else{
            setTitle(R.string.app_name);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentViewActivity.this,EnterDetailActivity.class);
                startActivity(intent);
            }
        });

        ListView listView = (ListView) findViewById(R.id.listView);

        mAdapter = new StudentCursorAdapter(this,null);
        getSupportLoaderManager().initLoader(0,null,this);
        listView.setAdapter(mAdapter);
        View emptyView = findViewById(R.id.emptyView);
        listView.setEmptyView(emptyView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        switch (id){
            case R.id.action_settings:
                Intent intent = new Intent(StudentViewActivity.this,LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.delete_all:
                showDeleteDialogBox();
                break;

            case android.R.id.home:
                LoginActivity.loginDone=false;
                startActivity(new Intent(this,StudentViewActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDeleteDialogBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete all pets?");
        builder.setNegativeButton("Delete all", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getContentResolver().delete(StudentContract.MarksEntry.CONTENT_URI,null,null);
            }
        });

        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LoginActivity.loginDone=false;
        startActivity(new Intent(this,StudentViewActivity.class));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, StudentContract.MarksEntry.CONTENT_URI,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
