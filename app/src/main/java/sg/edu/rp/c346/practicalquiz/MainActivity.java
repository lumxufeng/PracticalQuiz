package sg.edu.rp.c346.practicalquiz;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edName;
    EditText edAge;
    Spinner spn;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edAge = findViewById(R.id.editTextAge);
        edName = findViewById(R.id.editTextName);
        spn = findViewById(R.id.spinner2);
        btnSave = findViewById(R.id.button);
        edAge.requestFocus();

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor preEdit = prefs.edit();
                preEdit.putInt("spinner", spn.getSelectedItemPosition()-1);

                preEdit.commit();
                Toast toast = Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG);
                toast.show();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String Name = prefs.getString("name", "");
        int Age = prefs.getInt("age",0);
        int spin = prefs.getInt("spinner",0);
        spn.setSelection(spin);

        edName.setText(Name);
        edAge.setText(Age+"");


    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preEdit = prefs.edit();
        if (!edName.getText().toString().isEmpty() || !edAge.getText().toString().isEmpty()) {
            preEdit.putString("name", edName.getText().toString());
            preEdit.putInt("age", Integer.parseInt(edAge.getText().toString()));

            preEdit.commit();
        }
        else{
            preEdit.clear();
            preEdit.commit();

        }
    }



}
