package iiitd.nayeem.maths_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class cheat extends AppCompatActivity {

    private Button mcheats;
    private TextView mcheatResult,mcheat_text;
    private int check_cheat=0;
    public final static String EXTRA_MESSAGE = "iiitd.nayeem.maths_quiz.cheat";
    private Boolean Mresult;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mcheats = (Button) findViewById(R.id.cheats);
        mcheatResult = (TextView) findViewById(R.id.cheat_result);
        mcheat_text = (TextView) findViewById(R.id.cheatText);

        if(savedInstanceState!=null)
        {
            if(savedInstanceState.getInt("CHEAT")==0)
            {
                mcheat_text.setVisibility(View.INVISIBLE);
                mcheatResult.setVisibility(View.INVISIBLE);
            }
            else
            {
                mcheat_text.setVisibility(View.VISIBLE);
                mcheatResult.setVisibility(View.VISIBLE);
                if(savedInstanceState.get("CHEAT_RESULT").toString()!=null)
                    mcheatResult.setText(savedInstanceState.get("CHEAT_RESULT").toString());
            }
            check_cheat++;
        }
        else
        {
            mcheat_text.setVisibility(View.INVISIBLE);
            mcheatResult.setVisibility(View.INVISIBLE);
        }

        Intent Mintent = getIntent();
        Mresult = Mintent.getBooleanExtra(MainActivity.EXTRA_MESSAGE,true);

        mcheats.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                mcheat_text.setVisibility(View.VISIBLE);
                mcheatResult.setVisibility(View.VISIBLE);
                mcheatResult.setText(Mresult.toString());
                check_cheat++;
                Toast.makeText(cheat.this, "Press back",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putInt("CHEAT",check_cheat);
        savedInstanceState.putBoolean("CHEAT_RESULT",Mresult);
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
    public void onBackPressed() //sending the data to parent activity
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE,check_cheat);  //cheat is called
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}
