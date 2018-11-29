package magic.numerology.ideas.turnup.numerologymagic;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import io.paperdb.Paper;

public class MobileResult extends AppCompatActivity
{
    public static final String PREFS = "examplePrefs";

    TextView mobileResult_bestDay;
    TextView mobileResult_bestMonth;
    TextView mobileResult_bestTime;
    TextView mobileResult_result;
    Button button_mobileResult;
    EditText edit_mobileResult;
    TextView home_text, karmicNumber_mobileResult;
    ImageView settings, question;
    ImageView toolbar_Back_mobileResult;
    RelativeLayout relativeLayout;

    ImageView mobileResult_displayResult ;

    @SuppressLint("ResourceAsColor")

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase , "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobileresult);

        relativeLayout = (RelativeLayout) findViewById(R.id.MobileResult);
        mobileResult_displayResult = (ImageView) findViewById(R.id.mobileResult_displayResult);

        question = (ImageView) findViewById(R.id.mobileNumber_question);
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String text = "Hello Numerology Magic need your help !!";// Replace with your message.
                    String toNumber = "918655990799"; // Replace with mobile phone number without +Sign or leading zeros.
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        home_text = (TextView) findViewById(R.id.mobileNumber_text);
        SharedPreferences example = getSharedPreferences(PREFS, 0);
        String userString = example.getString("userMessage","");
        home_text.setText(userString);

        karmicNumber_mobileResult = (TextView) findViewById(R.id.home_text_karmicNo_mobileNumber);
        SharedPreferences example1 = getSharedPreferences(PREFS, 0);
        String userString1 = example1.getString("userMessage1","");
        karmicNumber_mobileResult.setText(userString1);

        Paper.init(this);

        String language = Paper.book().read("language");
        if (language == null)

            Paper.book().write("language", "en");
        updateView((String)Paper.book().read("language"));

        settings = (ImageView) findViewById(R.id.mobileNumber_setting);
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= 19)
                {
                    final String[] listItems = new String[]{"English", "हिंदी", "मराठी"};
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MobileResult.this);
                    mBuilder.setIcon(R.mipmap.appicon);
                    mBuilder.setTitle((CharSequence) "Choose Language");
                    mBuilder.setItems(listItems, new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int i)
                        {
                            if (i == 0)
                            {
                                Paper.book().write("language","en");
                                updateView((String) Paper.book().read("language"));
                                finish();
                                startActivity(getIntent());
                            }
                            if (i == 1)
                            {
                                Paper.book().write("language","hi");
                                updateView((String) Paper.book().read("language"));
                                finish();
                                startActivity(getIntent());
                            }
                            if (i == 2)
                            {
                                Paper.book().write("language","mr");
                                updateView((String) Paper.book().read("language"));
                                finish();
                                startActivity(getIntent());
                            }
                            dialog.dismiss();
                        }

                    });
                    mBuilder.show();
                }
                else
                {
                    Intent languageIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                    startActivity(languageIntent);
                }
            }
        });

        mobileResult_result = (TextView) findViewById(R.id.mobileNumber_result);
        mobileResult_bestDay = (TextView) findViewById(R.id.mobileNumber_bestday);
        mobileResult_bestMonth = (TextView) findViewById(R.id.mobileNumber_bestmonth);
        mobileResult_bestTime = (TextView) findViewById(R.id.mobileNumber_besttime);
        edit_mobileResult = (EditText) findViewById(R.id.edit1_mobileNumber);
        toolbar_Back_mobileResult = (ImageView) findViewById(R.id.back_mobileNumber);
        toolbar_Back_mobileResult.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(MobileResult.this,Home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        final String bhagyank = home_text.getText().toString();
        final String mulank = karmicNumber_mobileResult.getText().toString();

        button_mobileResult = (Button) findViewById(R.id.button1_mobileNumber);

        if (bhagyank.isEmpty() || mulank.isEmpty())
        {
            button_mobileResult.setEnabled(false);
            edit_mobileResult.setEnabled(false);

            Snackbar snackbar = Snackbar.make(relativeLayout, "Calculate lucky and mulank number first.",
                    Snackbar.LENGTH_INDEFINITE).setAction("Ok", new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(MobileResult.this,Home.class);
                    startActivity(intent);
                }
            }).setActionTextColor(ContextCompat.getColor(MobileResult.this, R.color.textColor));
            snackbar.show();

            View snackView = snackbar.getView();
            TextView textView = snackView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(ContextCompat.getColor(MobileResult.this, R.color.textColor));
            textView.setTypeface( textView.getTypeface(), Typeface.BOLD);
        }


        button_mobileResult.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                // TODO Auto-generated method stub
                String str1;
                int result,i;
                result = 0;
                str1 = edit_mobileResult.getEditableText().toString();

                if (TextUtils.isEmpty(str1))
                {
                    Toast.makeText(MobileResult.this,"Enter Mobile Number",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    if (str1.compareTo("") != 0)
                    {
                        for (i = 0; i < str1.length(); i++)
                        {
                            if (str1.substring(i, i + 1).compareTo("A") == 0 ||
                                    str1.substring(i, i + 1).compareTo("I") == 0 ||
                                    str1.substring(i, i + 1).compareTo("J") == 0 ||
                                    str1.substring(i, i + 1).compareTo("Q") == 0 ||
                                    str1.substring(i, i + 1).compareTo("Y") == 0 ||
                                    str1.substring(i, i + 1).compareTo("a") == 0 ||
                                    str1.substring(i, i + 1).compareTo("i") == 0 ||
                                    str1.substring(i, i + 1).compareTo("j") == 0 ||
                                    str1.substring(i, i + 1).compareTo("q") == 0 ||
                                    str1.substring(i, i + 1).compareTo("y") == 0 ||
                                    str1.substring(i, i + 1).compareTo("1") == 0)
                            {
                                result = result + 1;
                            }
                            else if (str1.substring(i, i + 1).compareTo("B") == 0 ||
                                    str1.substring(i, i + 1).compareTo("K") == 0 ||
                                    str1.substring(i, i + 1).compareTo("R") == 0 ||
                                    str1.substring(i, i + 1).compareTo("b") == 0 ||
                                    str1.substring(i, i + 1).compareTo("k") == 0 ||
                                    str1.substring(i, i + 1).compareTo("r") == 0 ||
                                    str1.substring(i, i + 1).compareTo("2") == 0
                                    )
                            {
                                result = result + 2;
                            }
                            else if (str1.substring(i, i + 1).compareTo("C") == 0 ||
                                    str1.substring(i, i + 1).compareTo("G") == 0 ||
                                    str1.substring(i, i + 1).compareTo("L") == 0 ||
                                    str1.substring(i, i + 1).compareTo("S") == 0 ||
                                    str1.substring(i, i + 1).compareTo("c") == 0 ||
                                    str1.substring(i, i + 1).compareTo("g") == 0 ||
                                    str1.substring(i, i + 1).compareTo("l") == 0 ||
                                    str1.substring(i, i + 1).compareTo("s") == 0 ||

                                    str1.substring(i, i + 1).compareTo("3") == 0
                                    )
                            {
                                result = result + 3;
                            }
                            else if (str1.substring(i, i + 1).compareTo("D") == 0 ||
                                    str1.substring(i, i + 1).compareTo("M") == 0 ||
                                    str1.substring(i, i + 1).compareTo("T") == 0 ||
                                    str1.substring(i, i + 1).compareTo("d") == 0 ||
                                    str1.substring(i, i + 1).compareTo("m") == 0 ||
                                    str1.substring(i, i + 1).compareTo("t") == 0 ||

                                    str1.substring(i, i + 1).compareTo("4") == 0
                                    )
                            {
                                result = result + 4;
                            }
                            else if (str1.substring(i, i + 1).compareTo("E") == 0 ||
                                    str1.substring(i, i + 1).compareTo("H") == 0 ||
                                    str1.substring(i, i + 1).compareTo("N") == 0 ||
                                    str1.substring(i, i + 1).compareTo("X") == 0 ||
                                    str1.substring(i, i + 1).compareTo("e") == 0 ||
                                    str1.substring(i, i + 1).compareTo("h") == 0 ||
                                    str1.substring(i, i + 1).compareTo("n") == 0 ||
                                    str1.substring(i, i + 1).compareTo("x") == 0 ||

                                    str1.substring(i, i + 1).compareTo("5") == 0
                                    )
                            {
                                result = result + 5;
                            }
                            else if (str1.substring(i, i + 1).compareTo("U") == 0 ||
                                    str1.substring(i, i + 1).compareTo("V") == 0 ||
                                    str1.substring(i, i + 1).compareTo("W") == 0 ||
                                    str1.substring(i, i + 1).compareTo("u") == 0 ||
                                    str1.substring(i, i + 1).compareTo("v") == 0 ||
                                    str1.substring(i, i + 1).compareTo("w") == 0 ||

                                    str1.substring(i, i + 1).compareTo("6") == 0
                                    )
                            {
                                result = result + 6;
                            }
                            else if (str1.substring(i, i + 1).compareTo("O") == 0 ||
                                    str1.substring(i, i + 1).compareTo("Z") == 0 ||
                                    str1.substring(i, i + 1).compareTo("o") == 0 ||
                                    str1.substring(i, i + 1).compareTo("z") == 0 ||

                                    str1.substring(i, i + 1).compareTo("7") == 0
                                    )
                            {
                                result = result + 7;
                            }
                            else if (str1.substring(i, i + 1).compareTo("F") == 0 ||
                                    str1.substring(i, i + 1).compareTo("P") == 0 ||
                                    str1.substring(i, i + 1).compareTo("f") == 0 ||
                                    str1.substring(i, i + 1).compareTo("p") == 0 ||

                                    str1.substring(i, i + 1).compareTo("8") == 0
                                    )
                            {
                                result = result + 8;
                            }
                        }
                    }
                }

                int resultFromMainScreen = result;

                int sum = 0 ,rem = 0 ;
                while(resultFromMainScreen>=1)
                {
                    rem = resultFromMainScreen % 10;
                    resultFromMainScreen = resultFromMainScreen / 10;
                    sum = sum + rem ;
                }

                if (sum == 10)
                {
                    sum = 1;
                }

                mobileResult_result.setText(String.valueOf(sum));

                String sumResult = mobileResult_result.getText().toString();

                if (sumResult.equals(home_text.getText().toString()))
                {
                    mobileResult_bestDay.setText(R.string.mobileNumber_result_Lucky);
                    mobileResult_displayResult.setImageResource(R.drawable.best);
                }

                else if ( sumResult.equals(karmicNumber_mobileResult.getText().toString()))
                {
                    mobileResult_bestDay.setText(R.string.mobileNumber_result_Mulank);
                    mobileResult_displayResult.setImageResource(R.drawable.good);
                }

                else
                {
                    mobileResult_bestDay.setText(R.string.mobileNumber_result_Bad);
                    mobileResult_displayResult.setImageResource(R.drawable.bad);
                }
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(MobileResult.this,Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        edit_mobileResult.setText(null);
    }

    private void updateView(String lang)
    {
        Context context =LocaleHelper.setLocale(this, lang);
        Resources resources = context.getResources();
    }
}