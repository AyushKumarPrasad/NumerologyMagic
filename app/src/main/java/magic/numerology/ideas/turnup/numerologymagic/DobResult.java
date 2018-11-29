package magic.numerology.ideas.turnup.numerologymagic;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import io.paperdb.Paper;

public class DobResult extends AppCompatActivity
{
    public static final String PREFS = "examplePrefs";
    TextView Dob_bestDay;
    TextView Dob_bestMonth;
    TextView Dob_result;
    TextView Dob_result_1;
    TextView Dob_result_10;
    TextView Dob_result_2;
    TextView Dob_result_3;
    TextView Dob_result_4;
    TextView Dob_result_5;
    TextView Dob_result_6;
    TextView Dob_result_7;
    TextView Dob_result_8;
    TextView Dob_result_9;
    TextView bhagyaank;
    ImageView settings, question;
    ImageView toolbar_dob_result;

    TextView dateLord,   luckyColor, luckyGem, favDirection,   positiveTraits, negativeTraits, friendlyNumber, neutralNumber,
            enemyNumber, luckyMetal ;

    TextView home_lucky, home_mulank;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase , "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dob_result);

        question = (ImageView) findViewById(R.id.Dob_question);
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

        Paper.init(this);

        String language = Paper.book().read("language");
        if (language == null)

            Paper.book().write("language", "en");
        updateView((String)Paper.book().read("language"));

        settings = (ImageView) findViewById(R.id.Dob_setting);
        settings.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (android.os.Build.VERSION.SDK_INT >= 19)
                {
                    final String[] listItems = new String[]{"English", "हिंदी", "मराठी"};
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(DobResult.this);
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

        Dob_result = (TextView) findViewById(R.id.Dob_result);
        bhagyaank = (TextView) findViewById(R.id.bhagyaank);
        Dob_bestDay = (TextView) findViewById(R.id.Dob_bestday);
        Dob_bestMonth = (TextView) findViewById(R.id.Dob_bestmonth);
//        Dob_bestTime = (TextView) findViewById(R.id.Dob_besttime);
        Dob_result_1 = (TextView) findViewById(R.id.Dob_result1);
        Dob_result_2 = (TextView) findViewById(R.id.Dob_result2);
        Dob_result_3 = (TextView) findViewById(R.id.Dob_result3);
        Dob_result_4 = (TextView) findViewById(R.id.Dob_result4);
        Dob_result_5 = (TextView) findViewById(R.id.Dob_result5);
        Dob_result_6 = (TextView) findViewById(R.id.Dob_result6);
        Dob_result_7 = (TextView) findViewById(R.id.Dob_result7);
        Dob_result_8 = (TextView) findViewById(R.id.Dob_result8);
        Dob_result_9 = (TextView) findViewById(R.id.Dob_result9);
        Dob_result_10 = (TextView) findViewById(R.id.Dob_result10);

        dateLord = (TextView) findViewById(R.id.Dob_dateLOrd);
//        element = (TextView) findViewById(R.id.Dob_element);
        luckyColor = (TextView) findViewById(R.id.Dob_luckyColor);
        luckyGem = (TextView) findViewById(R.id.Dob_luckyGem);
        favDirection = (TextView) findViewById(R.id.Dob_favourableDirection);
//        favTaste = (TextView) findViewById(R.id.Dob_favourableTaste);
        positiveTraits = (TextView) findViewById(R.id.Dob_positiveTraits);
        negativeTraits = (TextView) findViewById(R.id.Dob_negativeTraits);
        friendlyNumber = (TextView) findViewById(R.id.Dob_friendlyNumbers);
        neutralNumber = (TextView) findViewById(R.id.Dob_neutralNumbers);
        enemyNumber = (TextView) findViewById(R.id.Dob_enemyNumbers);
        luckyMetal = (TextView) findViewById(R.id.Dob_luckyMetal);

        toolbar_dob_result = (ImageView) findViewById(R.id.back_Dob_Result);
        toolbar_dob_result.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                String message = Dob_result.getText().toString();
                SharedPreferences examplePrefs = getSharedPreferences(PREFS,0);
                SharedPreferences.Editor editor = getSharedPreferences(PREFS, 0).edit();
                editor.putString("userMessage", message);
                editor.commit();

                String message1 = bhagyaank.getText().toString();
                SharedPreferences examplePrefs1 = getSharedPreferences(PREFS,0);
                SharedPreferences.Editor editor1 = getSharedPreferences(PREFS, 0).edit();
                editor.putString("userMessage1", message1);
                editor.commit();

                Intent intent = new Intent(DobResult.this,Home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        int resultFromMainScreen = getIntent().getIntExtra("result", RESULT_OK);

        int sum = 0 ,rem = 0 ;
        while(resultFromMainScreen >= 1)
        {
            rem = resultFromMainScreen % 10;
            resultFromMainScreen = resultFromMainScreen / 10;
            sum = sum + rem ;
        }

        if (sum == 10)
        {
            sum = 1;
        }
        Dob_result.setText(String.valueOf(sum));

        int result= getIntent().getIntExtra("bhagyank", RESULT_OK);

        int summ = 0 ,remm = 0 ;
        while(result >= 1)
        {
            remm = result % 10;
            result = result / 10;
            summ = summ + remm ;
        }

        if (summ == 10)
        {
            summ = 1;
        }
        bhagyaank.setText(String.valueOf(summ));

        switch (sum)
        {
            case 1:
                Dob_bestDay.setText(getResources().getString(R.string.bestday1));
                Dob_bestMonth.setText(getResources().getString(R.string.bestmonth1));
                //          Dob_bestTime.setText(getResources().getString(R.string.besttime1));
                Dob_result_1.setText(getResources().getString(R.string.result1a));
                Dob_result_2.setText(getResources().getString(R.string.result1b));
                Dob_result_3.setText(getResources().getString(R.string.result1c));
                Dob_result_4.setText(getResources().getString(R.string.result1d));
                Dob_result_5.setText(getResources().getString(R.string.result1e));
                Dob_result_6.setText(getResources().getString(R.string.result1f));
                Dob_result_7.setText(getResources().getString(R.string.result1g));
                Dob_result_8.setText(getResources().getString(R.string.result1h));
                Dob_result_9.setText(getResources().getString(R.string.result1i));
                Dob_result_10.setText(getResources().getString(R.string.result1j));

                dateLord.setText(getResources().getString(R.string.dateLord1));
                //         element.setText(getResources().getString(R.string.element1));
                luckyColor.setText(getResources().getString(R.string.luckyColor1));
                luckyGem.setText(getResources().getString(R.string.luckyGem1));
                favDirection.setText(getResources().getString(R.string.favourableDirection1));
                //           favTaste.setText(getResources().getString(R.string.favourableTatse1));
                positiveTraits.setText(getResources().getString(R.string.positiveTraits1));
                negativeTraits.setText(getResources().getString(R.string.negativeTraits1));
                friendlyNumber.setText(getResources().getString(R.string.friendlyNumbers1));
                neutralNumber.setText(getResources().getString(R.string.neutralNumbers1));
                enemyNumber.setText(getResources().getString(R.string.enemyNumbers1));
                luckyMetal.setText(getResources().getString(R.string.luckyMetal1));

                break;
            case 2:
                Dob_bestDay.setText(getResources().getString(R.string.bestday2));
                Dob_bestMonth.setText(getResources().getString(R.string.bestmonth2));
                //         Dob_bestTime.setText(getResources().getString(R.string.besttime2));
                Dob_result_1.setText(getResources().getString(R.string.result2a));
                Dob_result_2.setText(getResources().getString(R.string.result2b));
                Dob_result_3.setText(getResources().getString(R.string.result2c));
                Dob_result_4.setText(getResources().getString(R.string.result2d));
                Dob_result_5.setText(getResources().getString(R.string.result2e));
                Dob_result_6.setText(getResources().getString(R.string.result2f));
                Dob_result_7.setText(getResources().getString(R.string.result2g));
                Dob_result_8.setText(getResources().getString(R.string.result2h));
                Dob_result_9.setText(getResources().getString(R.string.result2i));
                Dob_result_10.setText(getResources().getString(R.string.result2j));

                dateLord.setText(getResources().getString(R.string.dateLord2));
                //          element.setText(getResources().getString(R.string.element2));
                luckyColor.setText(getResources().getString(R.string.luckyColor2));
                luckyGem.setText(getResources().getString(R.string.luckyGem2));
                favDirection.setText(getResources().getString(R.string.favourableDirection2));
                //         favTaste.setText(getResources().getString(R.string.favourableTatse2));
                positiveTraits.setText(getResources().getString(R.string.positiveTraits2));
                negativeTraits.setText(getResources().getString(R.string.negativeTraits2));
                friendlyNumber.setText(getResources().getString(R.string.friendlyNumbers2));
                neutralNumber.setText(getResources().getString(R.string.neutralNumbers2));
                enemyNumber.setText(getResources().getString(R.string.enemyNumbers2));
                luckyMetal.setText(getResources().getString(R.string.luckyMetal2));

                break;
            case 3:
                Dob_bestDay.setText(getResources().getString(R.string.bestday3));
                Dob_bestMonth.setText(getResources().getString(R.string.bestmonth3));
                //          Dob_bestTime.setText(getResources().getString(R.string.besttime3));
                Dob_result_1.setText(getResources().getString(R.string.result3a));
                Dob_result_2.setText(getResources().getString(R.string.result3b));
                Dob_result_3.setText(getResources().getString(R.string.result3c));
                Dob_result_4.setText(getResources().getString(R.string.result3d));
                Dob_result_5.setText(getResources().getString(R.string.result3e));
                Dob_result_7.setText(getResources().getString(R.string.result3f));
                Dob_result_8.setText(getResources().getString(R.string.result3g));
                Dob_result_9.setText(getResources().getString(R.string.result3h));


                dateLord.setText(getResources().getString(R.string.dateLord3));
                //        element.setText(getResources().getString(R.string.element3));
                luckyColor.setText(getResources().getString(R.string.luckyColor3));
                luckyGem.setText(getResources().getString(R.string.luckyGem3));
                favDirection.setText(getResources().getString(R.string.favourableDirection3));
                //         favTaste.setText(getResources().getString(R.string.favourableTatse3));
                positiveTraits.setText(getResources().getString(R.string.positiveTraits3));
                negativeTraits.setText(getResources().getString(R.string.negativeTraits3));
                friendlyNumber.setText(getResources().getString(R.string.friendlyNumbers3));
                neutralNumber.setText(getResources().getString(R.string.neutralNumbers3));
                enemyNumber.setText(getResources().getString(R.string.enemyNumbers3));
                luckyMetal.setText(getResources().getString(R.string.luckyMetal3));

                break;
            case 4:
                Dob_bestDay.setText(getResources().getString(R.string.bestday4));
                Dob_bestMonth.setText(getResources().getString(R.string.bestmonth4));
                //         Dob_bestTime.setText(getResources().getString(R.string.besttime4));
                Dob_result_1.setText(getResources().getString(R.string.result4a));
                Dob_result_2.setText(getResources().getString(R.string.result4b));
                Dob_result_3.setText(getResources().getString(R.string.result4c));
                Dob_result_4.setText(getResources().getString(R.string.result4d));
                Dob_result_5.setText(getResources().getString(R.string.result4e));
                Dob_result_6.setText(getResources().getString(R.string.result4f));
                Dob_result_7.setText(getResources().getString(R.string.result4g));
                Dob_result_8.setText(getResources().getString(R.string.result4h));
                Dob_result_9.setText(getResources().getString(R.string.result4i));
                Dob_result_10.setText(getResources().getString(R.string.result4j));

                dateLord.setText(getResources().getString(R.string.dateLord4));
                //        element.setText(getResources().getString(R.string.element4));
                luckyColor.setText(getResources().getString(R.string.luckyColor4));
                luckyGem.setText(getResources().getString(R.string.luckyGem4));
                favDirection.setText(getResources().getString(R.string.favourableDirection4));
                //        favTaste.setText(getResources().getString(R.string.favourableTatse4));
                positiveTraits.setText(getResources().getString(R.string.positiveTraits4));
                negativeTraits.setText(getResources().getString(R.string.negativeTraits4));
                friendlyNumber.setText(getResources().getString(R.string.friendlyNumbers4));
                neutralNumber.setText(getResources().getString(R.string.neutralNumbers4));
                enemyNumber.setText(getResources().getString(R.string.enemyNumbers4));
                luckyMetal.setText(getResources().getString(R.string.luckyMetal4));

                break;
            case 5:
                Dob_bestDay.setText(getResources().getString(R.string.bestday5));
                Dob_bestMonth.setText(getResources().getString(R.string.bestmonth5));
                //         Dob_bestTime.setText(getResources().getString(R.string.besttime5));
                Dob_result_1.setText(getResources().getString(R.string.result5a));
                Dob_result_2.setText(getResources().getString(R.string.result5b));
                Dob_result_3.setText(getResources().getString(R.string.result5c));
                Dob_result_4.setText(getResources().getString(R.string.result5d));
                Dob_result_5.setText(getResources().getString(R.string.result5e));


                dateLord.setText(getResources().getString(R.string.dateLord5));
                //          element.setText(getResources().getString(R.string.element5));
                luckyColor.setText(getResources().getString(R.string.luckyColor5));
                luckyGem.setText(getResources().getString(R.string.luckyGem5));
                favDirection.setText(getResources().getString(R.string.favourableDirection5));
                //         favTaste.setText(getResources().getString(R.string.favourableTatse5));
                positiveTraits.setText(getResources().getString(R.string.positiveTraits5));
                negativeTraits.setText(getResources().getString(R.string.negativeTraits5));
                friendlyNumber.setText(getResources().getString(R.string.friendlyNumbers5));
                neutralNumber.setText(getResources().getString(R.string.neutralNumbers5));
                enemyNumber.setText(getResources().getString(R.string.enemyNumbers5));
                luckyMetal.setText(getResources().getString(R.string.luckyMetal5));

                break;
            case 6:
                Dob_bestDay.setText(getResources().getString(R.string.bestday6));
                Dob_bestMonth.setText(getResources().getString(R.string.bestmonth6));
                //   Dob_bestTime.setText(getResources().getString(R.string.besttime6));
                Dob_result_1.setText(getResources().getString(R.string.result6a));
                Dob_result_2.setText(getResources().getString(R.string.result6b));
                Dob_result_3.setText(getResources().getString(R.string.result6c));
                Dob_result_4.setText(getResources().getString(R.string.result6d));
                Dob_result_5.setText(getResources().getString(R.string.result6e));
                Dob_result_6.setText(getResources().getString(R.string.result6f));
                Dob_result_7.setText(getResources().getString(R.string.result6g));


                dateLord.setText(getResources().getString(R.string.dateLord6));
                // element.setText(getResources().getString(R.string.element6));
                luckyColor.setText(getResources().getString(R.string.luckyColor6));
                luckyGem.setText(getResources().getString(R.string.luckyGem6));
                favDirection.setText(getResources().getString(R.string.favourableDirection6));
                //  favTaste.setText(getResources().getString(R.string.favourableTatse6));
                positiveTraits.setText(getResources().getString(R.string.positiveTraits6));
                negativeTraits.setText(getResources().getString(R.string.negativeTraits6));
                friendlyNumber.setText(getResources().getString(R.string.friendlyNumbers6));
                neutralNumber.setText(getResources().getString(R.string.neutralNumbers6));
                enemyNumber.setText(getResources().getString(R.string.enemyNumbers6));
                luckyMetal.setText(getResources().getString(R.string.luckyMetal6));

                break;
            case 7:
                Dob_bestDay.setText(getResources().getString(R.string.bestday7));
                Dob_bestMonth.setText(getResources().getString(R.string.bestmonth7));
                // Dob_bestTime.setText(getResources().getString(R.string.besttime7));
                Dob_result_1.setText(getResources().getString(R.string.result7a));
                Dob_result_2.setText(getResources().getString(R.string.result7b));
                Dob_result_3.setText(getResources().getString(R.string.result7c));
                Dob_result_4.setText(getResources().getString(R.string.result7d));
                Dob_result_5.setText(getResources().getString(R.string.result7e));
                Dob_result_6.setText(getResources().getString(R.string.result7f));
                Dob_result_7.setText(getResources().getString(R.string.result7g));
                Dob_result_8.setText(getResources().getString(R.string.result7h));
                Dob_result_9.setText(getResources().getString(R.string.result7i));
                Dob_result_10.setText(getResources().getString(R.string.result7j));

                dateLord.setText(getResources().getString(R.string.dateLord7));
                // element.setText(getResources().getString(R.string.element7));
                luckyColor.setText(getResources().getString(R.string.luckyColor7));
                luckyGem.setText(getResources().getString(R.string.luckyGem7));
                favDirection.setText(getResources().getString(R.string.favourableDirection7));
                // favTaste.setText(getResources().getString(R.string.favourableTatse7));
                positiveTraits.setText(getResources().getString(R.string.positiveTraits7));
                negativeTraits.setText(getResources().getString(R.string.negativeTraits7));
                friendlyNumber.setText(getResources().getString(R.string.friendlyNumbers7));
                neutralNumber.setText(getResources().getString(R.string.neutralNumbers7));
                enemyNumber.setText(getResources().getString(R.string.enemyNumbers7));
                luckyMetal.setText(getResources().getString(R.string.luckyMetal7));

                break;
            case 8:
                Dob_bestDay.setText(getResources().getString(R.string.bestday8));
                Dob_bestMonth.setText(getResources().getString(R.string.bestmonth8));
                //  Dob_bestTime.setText(getResources().getString(R.string.besttime8));
                Dob_result_1.setText(getResources().getString(R.string.result8a));
                Dob_result_2.setText(getResources().getString(R.string.result8b));
                Dob_result_3.setText(getResources().getString(R.string.result8c));
                Dob_result_4.setText(getResources().getString(R.string.result8d));
                Dob_result_5.setText(getResources().getString(R.string.result8e));
                Dob_result_6.setText(getResources().getString(R.string.result8f));
                Dob_result_7.setText(getResources().getString(R.string.result8g));
                Dob_result_8.setText(getResources().getString(R.string.result8h));


                dateLord.setText(getResources().getString(R.string.dateLord8));
                //      element.setText(getResources().getString(R.string.element8));
                luckyColor.setText(getResources().getString(R.string.luckyColor8));
                luckyGem.setText(getResources().getString(R.string.luckyGem8));
                favDirection.setText(getResources().getString(R.string.favourableDirection8));
                //      favTaste.setText(getResources().getString(R.string.favourableTatse8));
                positiveTraits.setText(getResources().getString(R.string.positiveTraits8));
                negativeTraits.setText(getResources().getString(R.string.negativeTraits8));
                friendlyNumber.setText(getResources().getString(R.string.friendlyNumbers8));
                neutralNumber.setText(getResources().getString(R.string.neutralNumbers8));
                enemyNumber.setText(getResources().getString(R.string.enemyNumbers8));
                luckyMetal.setText(getResources().getString(R.string.luckyMetal8));

                break;
            case 9:
                Dob_bestDay.setText(getResources().getString(R.string.bestday9));
                Dob_bestMonth.setText(getResources().getString(R.string.bestmonth9));
                //     Dob_bestTime.setText(getResources().getString(R.string.besttime9));
                Dob_result_1.setText(getResources().getString(R.string.result9a));
                Dob_result_2.setText(getResources().getString(R.string.result9b));
                Dob_result_3.setText(getResources().getString(R.string.result9c));
                Dob_result_4.setText(getResources().getString(R.string.result9d));
                Dob_result_5.setText(getResources().getString(R.string.result9e));
                Dob_result_6.setText(getResources().getString(R.string.result9f));
                Dob_result_7.setText(getResources().getString(R.string.result9g));


                dateLord.setText(getResources().getString(R.string.dateLord9));
                //      element.setText(getResources().getString(R.string.element9));
                luckyColor.setText(getResources().getString(R.string.luckyColor9));
                luckyGem.setText(getResources().getString(R.string.luckyGem9));
                favDirection.setText(getResources().getString(R.string.favourableDirection9));
                //      favTaste.setText(getResources().getString(R.string.favourableTatse9));
                positiveTraits.setText(getResources().getString(R.string.positiveTraits9));
                negativeTraits.setText(getResources().getString(R.string.negativeTraits9));
                friendlyNumber.setText(getResources().getString(R.string.friendlyNumbers9));
                neutralNumber.setText(getResources().getString(R.string.neutralNumbers9));
                enemyNumber.setText(getResources().getString(R.string.enemyNumbers9));
                luckyMetal.setText(getResources().getString(R.string.luckyMetal9));

                break;
        }
    }

    private void updateView(String lang)
    {
        Context context =LocaleHelper.setLocale(this, lang);
        Resources resources = context.getResources();
    }

    public void onBackPressed() {
        String message = Dob_result.getText().toString();
        SharedPreferences.Editor editor = getSharedPreferences("examplePrefs", 0).edit();
        editor.putString("userMessage", message);
        editor.commit();

        String karmicNumber = bhagyaank.getText().toString();
        SharedPreferences.Editor editor1 = getSharedPreferences("examplePrefs", 0).edit();
        editor1.putString("userMessage1", karmicNumber);
        editor1.commit();

        Intent intent = new Intent(DobResult.this,Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
