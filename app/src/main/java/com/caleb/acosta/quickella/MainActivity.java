package com.caleb.acosta.quickella;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    EditText textPhone, textMessage;
    Button send;
    CountryCodePicker cpp;
    FloatingActionButton fab;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext(),null);
        textPhone = (EditText) findViewById(R.id.textPhone);
        textMessage = (EditText) findViewById(R.id.textMessage);
        send = (Button)  findViewById(R.id.sendMain);
        cpp = (CountryCodePicker) findViewById(R.id.ccp);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = textPhone.getText().toString();
                String areaCode = cpp.getSelectedCountryCode();
                String message = textMessage.getText().toString();

                if(!phoneNumber.isEmpty()) {
                    Contact contact = new Contact();

                    if(db.contactExists(areaCode, phoneNumber)) {
                        Toast.makeText(getApplicationContext(),"Numero en db", Toast.LENGTH_SHORT).show();
                        contact = db.getContactByPhone(areaCode, phoneNumber);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Numero no en db", Toast.LENGTH_SHORT).show();
                        contact = new Contact(areaCode, phoneNumber);
                        db.addContact(contact);
                    }



                    Link link = new Link(contact,message);

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(link.getLink()));
                    startActivity(i);

                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HistoryActivity.class);
                startActivity(intent);
            }
        });

    }


}

