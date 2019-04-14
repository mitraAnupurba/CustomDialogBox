package com.example.customdialogbox;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

public class MainActivity extends AppCompatActivity {

    TextView dummyText;
    SmileRating smileRating;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dummyText = findViewById(R.id.text_dummy_text);
    }

    //button in the main activity on click button:
    public void showDialog(View view) {

        final AlertDialog.Builder rating = new AlertDialog.Builder(MainActivity.this);
        View v = getLayoutInflater().inflate(R.layout.customdialoglayout,null);


        //mistake, since it is an inner class, we have to write v.findView.Id, that is why the app was
        //crashing on clicking the show buttoon on main activity, based on null poiter exception        smileRating = v.findViewById(R.id.smile_rating);
        Button doneButton = v.findViewById(R.id.rate_button);
        Button cancelButton = v.findViewById(R.id.cancel_button);
        smileRating = v.findViewById(R.id.smile_rating);
        rating.setView(v);
        alertDialog = rating.create();
        alertDialog.setCanceledOnTouchOutside(false);



        //smiley rating:
        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.

                switch (smiley) {
                    case SmileRating.BAD:

                        Toast.makeText(MainActivity.this ,"bad" , Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:

                        Toast.makeText(MainActivity.this ,"good" , Toast.LENGTH_SHORT).show();
                         break;
                    case SmileRating.GREAT:

                        Toast.makeText(MainActivity.this ,"great" , Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:

                        Toast.makeText(MainActivity.this ,"okay" , Toast.LENGTH_SHORT).show();
                         break;
                    case SmileRating.TERRIBLE:

                        Toast.makeText(MainActivity.this ,"terrible", Toast.LENGTH_SHORT).show();
                         break;
                }
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String message = Integer.toString(smileRating.getRating());
                    dummyText.setText(message);
                    alertDialog.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = Integer.toString(smileRating.getRating());
                dummyText.setText(message);
                alertDialog.dismiss();
            }
        });



        alertDialog.show();

    }
}
