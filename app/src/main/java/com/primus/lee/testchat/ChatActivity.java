package com.primus.lee.testchat;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.primus.lee.testchat.R.id.Send_button;
import static com.primus.lee.testchat.R.id.linearLayout;


public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
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

    @Override
    public void onClick(View v) {
        switch ( v.getId() )
        {
            case Send_button:
                EditText editText;
                editText = (EditText) findViewById( R.id.editText);
                Toast.makeText( this.getApplicationContext(),  editText.getText(), Toast.LENGTH_SHORT ).show();


                LinearLayout topRL = (LinearLayout) findViewById( R.id.dynamicArea  );

                TextView topTV1 = new TextView( ChatActivity.this);

                topTV1.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, android.support.v7.app.ActionBar.LayoutParams.WRAP_CONTENT));
                topTV1.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                topTV1.setPadding(20, 10, 10, 10);
                topTV1.setTextColor(Color.parseColor("#FF7200"));
                topTV1.setTextSize(13);
                topTV1.setText( editText.getText() );
                topRL.addView(topTV1);



                break;
        }

    }


}
