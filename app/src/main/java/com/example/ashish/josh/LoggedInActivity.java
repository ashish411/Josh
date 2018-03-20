package com.example.ashish.josh;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class LoggedInActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button playVideoBtn,sendNotfctnBtn,sendToDbBtn;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        sendToDbBtn = (Button)findViewById(R.id.sendToDB);
        sendNotfctnBtn = (Button)findViewById(R.id.send_notification);
        mSpinner = (Spinner)findViewById(R.id.spinner);
        playVideoBtn = (Button)findViewById(R.id.playVideoBtn);
        String titleName = getIntent().getStringExtra(Constants.INTENT_TAG);
        setTitle(titleName);

        playVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApplicationContext().startActivity(new Intent(getApplicationContext(),YoutubePlayerActivity.class));
            }
        });

        sendNotfctnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = FirebaseInstanceId.getInstance().getToken();
                Toast.makeText(view.getContext(),"current token:"+token,Toast.LENGTH_SHORT).show();
            }
        });

        updateSpinner();

        sendToDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(Constants.TAG,mSpinner.getSelectedItem().toString());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("optionsValue");
                myRef.setValue(mSpinner.getSelectedItem().toString());
                Toast.makeText(view.getContext(),"Value saved to Firebase",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.options_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.signOut:
                logOutUser();
                break;
            default:
                return true;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you wish to log Out ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                logOutUser();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog =  builder.create();
        dialog.show();

    }

    private void logOutUser() {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        finish();

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
