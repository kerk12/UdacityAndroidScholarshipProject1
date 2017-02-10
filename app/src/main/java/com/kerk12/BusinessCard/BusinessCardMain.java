package com.kerk12.BusinessCard;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BusinessCardMain extends AppCompatActivity {

    /**
     * This app was developed by Kyriakos Giannakis for the first Project of the Udacity Android Development Scholarship.
     */

    /**
     * Some notes:
     * 1. The method getResources().getString(String ID) is used to get a string from the resources (what usually happens with @string/... when editing XML).
     * As this app has been localized to Greek as well as English, the strings from the values-el folder get passed when working with Greek.
     * 2. An OnClickListener gets used to specify something for the button to do when it's pressed.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_card);

        Button emailButton = (Button) findViewById(R.id.email_button);

        //Set a new onClickListener for the button that launches the email client.
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Create a new intent with a SEND action.
                 * See: https://developer.android.com/reference/android/content/Intent.html
                 */
                Intent i = new Intent(Intent.ACTION_SEND);
                //Set the MIME type for the email.
                i.setType("message/rfc822");
                //Add the address for the email to be sent to.
                String[] address = {getResources().getString(R.string.email_address)};
                i.putExtra(Intent.EXTRA_EMAIL, address);
                //Start the email app.
                try {
                    startActivity(i);
                } catch (ActivityNotFoundException e){
                    //In case no email client was found.
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_email_client), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Overriden method that creates the options menu (the three dots on the top right).
     * See https://developer.android.com/guide/topics/ui/menus.html
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.about_menu_entry:
                //The class that builds alert dialogs is called a Builder.
                AlertDialog.Builder bob = new AlertDialog.Builder(this);
                bob.setMessage(R.string.about).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do nothing here
                    }
                }).setCancelable(true).show();
                return true;
            default:
                return true;
        }
    }
}
