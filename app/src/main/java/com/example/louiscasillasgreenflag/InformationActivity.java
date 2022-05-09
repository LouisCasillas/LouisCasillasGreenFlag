package com.example.louiscasillasgreenflag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.health.SystemHealthManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InformationActivity extends AppCompatActivity {

    private static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    private Button back_button;
    private AppCompatButton next_button;

    private EditText email_text;
    private EditText password1_text;
    private EditText password2_text;

    private TextView email_error_message;
    private TextView password_error_message;

    private boolean email_good;
    private boolean password_good;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        back_button = (Button)findViewById(R.id.create_account_back_button);
        next_button = (AppCompatButton)findViewById(R.id.next_button);

        email_text = (EditText)findViewById(R.id.email_address_text);
        password1_text = (EditText)findViewById(R.id.password1_text);
        password2_text = (EditText)findViewById(R.id.password2_text);

        email_error_message = (TextView)findViewById(R.id.email_error);
        password_error_message = (TextView)findViewById(R.id.password_error);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "You have been registered!", Toast.LENGTH_LONG).show();
            }
        });
        
        email_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                email_changed();
                validate_next_button();
            }
        });

        password1_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                password_changed();
                validate_next_button();
            }
        });

        password2_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                password_changed();
                validate_next_button();
            }
        });
    }

    private void email_changed()
    {
        String email_address = (String)email_text.getText().toString();

        if (email_address.matches(EMAIL_REGEX))
        {
            email_good = true;

            email_text.setBackgroundResource(R.drawable.thick_good_highlight);

            Drawable check_image = email_text.getContext().getResources().getDrawable( R.drawable.tick, null );
            email_text.setCompoundDrawablesWithIntrinsicBounds( null, null, check_image, null);

            email_error_message.setVisibility(View.INVISIBLE);
         }
        else
        {
            email_good = false;

            email_text.setBackgroundResource(R.drawable.thick_error_highlight);
            email_text.setCompoundDrawablesWithIntrinsicBounds( null, null, null, null);

            email_error_message.setVisibility(View.VISIBLE);
        }
    }

    private void password_changed() {
        String password1 = (String) password1_text.getText().toString();
        String password2 = (String) password2_text.getText().toString();

        password_good = true;

        if (! password1.equals(password2)) {
            password_good = false;

            password_error_message.setText("Your passwords don't match");
        }
        else
        {
            if ( (password1.length() < 8) ||
                    (! password1.matches(".*[0-9]+.*")) ||
                    (! password1.matches(".*[a-z]+.*")) ||
                    (! password1.matches(".*[A-Z]+.*")))
            {
                password_good = false;

                password_error_message.setText("One of the passwords you entered wasn't valid");
            }
        }

        if (password_good) {
            password_error_message.setVisibility(View.INVISIBLE);

            password1_text.setBackgroundResource(R.drawable.thick_good_highlight);
            password2_text.setBackgroundResource(R.drawable.thick_good_highlight);

            Drawable check_image = password1_text.getContext().getResources().getDrawable(R.drawable.tick, null);
            password1_text.setCompoundDrawablesWithIntrinsicBounds(null, null, check_image, null);
            password2_text.setCompoundDrawablesWithIntrinsicBounds(null, null, check_image, null);
        }
        else
        {
            password_error_message.setVisibility(View.VISIBLE);
            password1_text.setBackgroundResource(R.drawable.thick_error_highlight);
            password2_text.setBackgroundResource(R.drawable.thick_error_highlight);

            password1_text.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            password2_text.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    private void validate_next_button()
    {
        // if             email_good and password_good
        // enable next button and add click listener
        // send registration message

        if (email_good && password_good)
        {
            next_button.setEnabled(true);
        }
        else
        {
            next_button.setEnabled(false);
        }
    }
}
