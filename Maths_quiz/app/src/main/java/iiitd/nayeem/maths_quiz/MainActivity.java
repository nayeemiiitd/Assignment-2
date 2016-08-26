package iiitd.nayeem.maths_quiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Button true_button,false_button,next_button;
    private ImageButton mhint,mcheat;
    private TextView question_number,question_text,minfo;
    private int number,question_counter=1;
    private Boolean result;
    private int button_check;
    public final static String EXTRA_MESSAGE = "iiitd.nayeem.maths_quiz.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        true_button =(Button) findViewById(R.id.correct);
        false_button = (Button) findViewById(R.id.incorrect);
        next_button = (Button) findViewById(R.id.next);
        question_number = (TextView) findViewById(R.id.number);
        question_text = (TextView) findViewById(R.id.question);
        mhint = (ImageButton) findViewById(R.id.hint);
        mcheat = (ImageButton) findViewById(R.id.cheat);
        minfo = (TextView) findViewById(R.id.info);

        if(savedInstanceState!=null)       //getting question number and question on screen rotation
        {
            question_counter = (int) savedInstanceState.get("counter");
            set_question_number(question_counter);
            question_counter++;
            number = (int) savedInstanceState.get("question");
            set_question(number);
        }
        else
        {
            number = generateNumber();
            set_question_number(question_counter);
            question_counter++;
            set_question(number);
        }

        true_button.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);  //for incorrect answer phone will vibrate
                        result = check(number);
                        button_check=1;
                        if(result)
                        {
                            Toast.makeText(MainActivity.this, "Correct Answer",Toast.LENGTH_SHORT).show();
                            question_text.setTextColor(Color.GREEN);
                            false_button.setEnabled(false);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Incorrect Answer",Toast.LENGTH_SHORT).show();
                            question_text.setTextColor(Color.RED);
                            false_button.setEnabled(false);
                            vib.vibrate(500);
                        }
                    }
                }
        );

        false_button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    result = check(number);
                    button_check=0;
                    if(result)
                    {
                        Toast.makeText(MainActivity.this, "Incorrect Answer",Toast.LENGTH_SHORT).show();
                        question_text.setTextColor(Color.RED);
                        true_button.setEnabled(false);
                        vib.vibrate(500);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Correct Answer",Toast.LENGTH_SHORT).show();
                        question_text.setTextColor(Color.GREEN);
                        true_button.setEnabled(false);
                    }
                }
            }
        );

        next_button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    minfo.setVisibility(View.INVISIBLE);
                    if(button_check==0 || button_check==1)
                    {
                        true_button.setEnabled(true);
                        false_button.setEnabled(true);
                        question_text.setTextColor(Color.BLACK);
                        number = generateNumber();
                        set_question_number(question_counter);
                        question_counter++;
                        set_question(number);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Confused?? Take a hint",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        );

        mhint.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {  //calling hint activity
                minfo.setVisibility(View.VISIBLE);
                minfo.setText(R.string.hint_info);
                Intent hint_intent = new Intent(v.getContext(), hint.class);
                startActivity(hint_intent);
            }
        });

        mcheat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {  //calling cheat activity
                minfo.setVisibility(View.VISIBLE);
                minfo.setText(R.string.cheat_info);
                Intent cheat_intent = new Intent(v.getContext(),cheat.class);
                result = check(number);
                cheat_intent.putExtra(EXTRA_MESSAGE,result);
                startActivity(cheat_intent);
            }
        });

    }

    private int generateNumber()
    {
        return ((int)(Math.random()*1000));
    } // generating random number

    private void set_question_number(int question_counter)  //setting the question number
    {
        String Q_no = Integer.toString(question_counter);
        question_number.setText(Q_no);
    }

    private void set_question(int number)   //setting the question
    {
        String Question = "Is"+" "+number+" "+"a prime number ?";
        question_text.setText(Question);
    }

    private Boolean check(int number)   //checking whether a number is prime or not
    {
        if(number==2)
            return true;
        if(number==3)
            return true;
        if(number%2==0)
            return false;
        if(number%3==0)
            return false;
        int i=5,w=2;
        while(i*i<=number)
        {
            if((number%i)==0)
                return false;
            i+=w;
            w=6-w;
        }
        return true;
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putInt("counter",question_counter-1);
        savedInstanceState.putInt("question", number);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onStart()  //taking the intent values from both the activities
    {
        Intent Hintent = getIntent();
        int Hresult = Hintent.getIntExtra(hint.EXTRA_MESSAGE,0);
        if(Hresult!=0) {
            minfo.setVisibility(View.VISIBLE);
            minfo.setText(R.string.hint_info);
        }

        Intent Cintent = getIntent();
        int Cresult = Cintent.getIntExtra(cheat.EXTRA_MESSAGE,0);
        if(Cresult!=0)
            minfo.setText(R.string.cheat_info);

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
    public void onDestroy()
    {
        super.onDestroy();
    }

}
