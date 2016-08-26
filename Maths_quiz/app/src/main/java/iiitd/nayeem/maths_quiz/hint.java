package iiitd.nayeem.maths_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class hint extends AppCompatActivity
{
    private Button mhints;
    private TextView mhint_text;
    private int check_hint=0;
    public final static String EXTRA_MESSAGE = "iiitd.nayeem.maths_quiz.hint";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        mhints = (Button) findViewById(R.id.hints);
        mhint_text = (TextView) findViewById(R.id.hintText);

        if(savedInstanceState!=null)
        {
            if(savedInstanceState.getInt("HINT")==0)
                mhint_text.setVisibility(View.INVISIBLE);
            else
                mhint_text.setVisibility(View.VISIBLE);
            check_hint++;
        }
        else
            mhint_text.setVisibility(View.INVISIBLE);

        mhints.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                mhint_text.setVisibility(View.VISIBLE);
                check_hint++;
                Toast.makeText(hint.this, "Press back",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putInt("HINT",check_hint);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onStop()
    {
        super.onStop();
    }

    @Override
    public void onBackPressed()  //sending the data to parent activity
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE,check_hint);  //hint is called
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }


    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}
