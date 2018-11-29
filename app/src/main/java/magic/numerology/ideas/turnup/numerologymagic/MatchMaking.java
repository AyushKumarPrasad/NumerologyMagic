package magic.numerology.ideas.turnup.numerologymagic;

import android.app.Activity;
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
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Locale;

import io.paperdb.Paper;

public class MatchMaking extends AppCompatActivity
{
    EditText e1 ;
    EditText e2 ;
    EditText e3, e4, e5, e6 ;
    Button b1;
    TextView t1, t2, t3, t4, common, percent, calculate, finalResult;
    int res ;
    ImageView new_matchMaking_back, new_matchMaking_whatsapp, new_matchMaking_settings;

    ImageView matchMaking_displayResult ;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase , "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchmaking);

        matchMaking_displayResult = (ImageView) findViewById(R.id.matchMaking_displayResult);

        new_matchMaking_back = (ImageView) findViewById(R.id.New_back_Match_Making);
        new_matchMaking_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent Shiv = new Intent(MatchMaking.this,Home.class);
                startActivity(Shiv);
            }
        });

        Paper.init(this);

        String language = Paper.book().read("language");
        if (language == null)

            Paper.book().write("language", "en");
        updateView((String)Paper.book().read("language"));

        new_matchMaking_settings = (ImageView) findViewById(R.id.New_matchMaking_setting);
        new_matchMaking_settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= 19)
                {
                    final String[] listItems = new String[]{"English", "हिंदी", "मराठी"};
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MatchMaking.this);
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

        new_matchMaking_whatsapp = (ImageView) findViewById(R.id.New_matchMaking_whatsapp);
        new_matchMaking_whatsapp.setOnClickListener(new View.OnClickListener() {
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

        e1 = (EditText) findViewById(R.id.edit1);
        e2 = (EditText) findViewById(R.id.edit2);
        e3 = (EditText) findViewById(R.id.edit3);

        e4 = (EditText) findViewById(R.id.edit4);
        e5 = (EditText) findViewById(R.id.edit5);
        e6 = (EditText) findViewById(R.id.edit6);

        e1.setFilters(new InputFilter[]{new InputFilterMinMax("0", "31")});
        e1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (s.length() == 2)
                {
                    e2.requestFocus();
                }
            }
        });

        e2.setFilters(new InputFilter[]{new InputFilterMinMax("0", "12")});
        e2.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (s.length() == 2)
                {
                    e3.requestFocus();
                }
            }
        });

        e3.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (s.length() == 4)
                {
                    e4.requestFocus();
                }
            }
        });

        e4.setFilters(new InputFilter[]{new InputFilterMinMax("0", "31")});
        e4.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (s.length() == 2)
                {
                    e5.requestFocus();
                }
            }
        });

        e5.setFilters(new InputFilter[]{new InputFilterMinMax("0", "12")});
        e5.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (s.length() == 2)
                {
                    e6.requestFocus();
                }
            }
        });

        t1 = (TextView) findViewById(R.id.result);
        t2 = (TextView) findViewById(R.id.resultPartner);

        t3 = (TextView) findViewById(R.id.first_nameArray);
        t4 = (TextView) findViewById(R.id.second_nameArray);

        common = (TextView) findViewById(R.id.common);
        percent = (TextView) findViewById(R.id.percentLove);
        calculate = (TextView) findViewById(R.id.percentCalculate);
        finalResult = (TextView) findViewById(R.id.finalResult);

        b1 = (Button) findViewById(R.id.show);

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String mynum1 = e1.getText().toString();
                String mynum2 = e2.getText().toString();
                String mynum3 = e3.getText().toString();

                String mynum4 = e4.getText().toString();
                String mynum5 = e5.getText().toString();
                String mynum6 = e6.getText().toString();

                if (TextUtils.isEmpty(mynum1) || TextUtils.isEmpty(mynum2) || TextUtils.isEmpty(mynum3) || TextUtils.isEmpty(mynum4) ||
                        TextUtils.isEmpty(mynum5) || TextUtils.isEmpty(mynum6) || mynum3.length() < 4 || mynum6.length() < 4)
                {
                    Toast.makeText(MatchMaking.this,"Enter valid details",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    int n = Integer.parseInt(mynum1)  +  Integer.parseInt(mynum2) + Integer.parseInt(mynum3)   ;
                    t1.setText(Integer.toString(digSum(n)));

                    int n1 = Integer.parseInt(mynum4) +  Integer.parseInt(mynum5) + Integer.parseInt(mynum6)   ;
                    t2.setText(Integer.toString(digSum(n1)));

                    int[] arr1 = {1,2,3,4,5,6,7};
                    int[] arr2 = {1,2,4,5,6,7,8};
                    int[] arr3 = {1,2,3,5,6,9};
                    int[] arr4 = {1,2,3,5,7};
                    int[] arr5 = {1,2,3,5,6};
                    int[] arr6 = {1,2,3,5,6,9};
                    int[] arr7 = {1,2,3,5,6};
                    int[] arr8 = {2,3,5,6};
                    int[] arr9 = {2,3,6,9};

                    int me = Integer.parseInt(t1.getText().toString());
                    int you = Integer.parseInt(t2.getText().toString());

                    if (me == 1 )
                    {
                        for(int i = 0; i < arr1.length; i++)
                        {
                            t3.setText(Arrays.toString(arr1));
                        }
                    }

                    if (me == 2 )
                    {
                        for(int i = 0; i < arr2.length; i++)
                        {
                            t3.setText(Arrays.toString(arr2));
                        }
                    }
                    if (me == 3 )
                    {
                        for(int i = 0; i < arr3.length; i++)
                        {
                            t3.setText(Arrays.toString(arr3));
                        }
                    }
                    if (me == 4 )
                    {
                        for(int i = 0; i < arr4.length; i++)
                        {
                            t3.setText(Arrays.toString(arr4));
                        }
                    }
                    if (me == 5 )
                    {
                        for(int i = 0; i < arr5.length; i++)
                        {
                            t3.setText(Arrays.toString(arr5));
                        }
                    }
                    if (me == 6)
                    {
                        for(int i = 0; i < arr6.length; i++)
                        {
                            t3.setText(Arrays.toString(arr6));
                        }
                    }
                    if (me == 7 )
                    {
                        for(int i = 0; i < arr7.length; i++)
                        {
                            t3.setText(Arrays.toString(arr7));
                        }
                    }
                    if (me == 8 )
                    {
                        for(int i = 0; i < arr8.length; i++)
                        {
                            t3.setText(Arrays.toString(arr8));
                        }
                    }
                    if (me == 9 )
                    {
                        for(int i = 0; i < arr9.length; i++)
                        {
                            t3.setText(Arrays.toString(arr9));
                        }
                    }

                    if (you == 1)
                    {
                        for(int i = 0; i < arr1.length; i++)
                        {
                            t4.setText(Arrays.toString(arr1));
                        }
                    }

                    if (you == 2)
                    {
                        for(int i = 0; i < arr2.length; i++)
                        {
                            t4.setText(Arrays.toString(arr2));
                        }
                    }
                    if (you == 3)
                    {
                        for(int i = 0; i < arr3.length; i++)
                        {
                            t4.setText(Arrays.toString(arr3));
                        }
                    }
                    if (you == 4)
                    {
                        for(int i = 0; i < arr4.length; i++)
                        {
                            t4.setText(Arrays.toString(arr4));
                        }
                    }
                    if (you == 5)
                    {
                        for(int i = 0; i < arr5.length; i++)
                        {
                            t4.setText(Arrays.toString(arr5));
                        }
                    }
                    if (you == 6)
                    {
                        for(int i = 0; i < arr6.length; i++)
                        {
                            t4.setText(Arrays.toString(arr6));
                        }
                    }
                    if (you == 7)
                    {
                        for(int i = 0; i < arr7.length; i++)
                        {
                            t4.setText(Arrays.toString(arr7));
                        }
                    }
                    if (you == 8)
                    {
                        for(int i = 0; i < arr8.length; i++)
                        {
                            t4.setText(Arrays.toString(arr8));
                        }
                    }
                    if (you == 9)
                    {
                        for(int i = 0; i < arr9.length; i++)
                        {
                            t4.setText(Arrays.toString(arr9));
                        }
                    }

                    String sb1 = t3.getText().toString();
                    String sb2 = t4.getText().toString();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    String ladka = String.valueOf(you);
                    String ladki = String.valueOf(me);

                    if (sb1.contains(ladka) && sb2.contains(ladki))
                    {
                        common.setText(R.string.matchMaking_result_Good);
                        matchMaking_displayResult.setImageResource(R.drawable.best);
                    }
                    else
                    {
                        common.setText(R.string.matchMaking_result_Bad);
                        matchMaking_displayResult.setImageResource(R.drawable.bad);
                    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*            Set<String> str = new HashSet<String>();
                for(int i=0;i<sb1.length();i++)
                {
                    for (int j = 0; j < sb2.length(); j++)
                    {
                        if(sb1.charAt(i) == sb2.charAt(j))
                        {
                            str.add(sb1.charAt(i)+"");
                        }
                    }
                }
                //        System.out.println(str);

                common.setText(String.valueOf(str));

                String ayush = String.valueOf(str);

                String love = ayush.replaceAll("[^A-Za-z0-9]","");
                percent.setText(love);

                int kumar = Integer.parseInt(love)   ;
                calculate.setText(Integer.toString(digSum(kumar)));

               String textFinal = percent.getText().toString();

               if (textFinal.length() == 1)
               {
                   finalResult.setText("15 %");
               }

                if (textFinal.length() == 2)
                {
                    finalResult.setText("30 %");
                }

                if (textFinal.length() == 3)
                {
                    finalResult.setText("45 %");
                }

                if (textFinal.length() == 4)
                {
                    finalResult.setText("60 %");
                }

                if (textFinal.length() == 5)
                {
                    finalResult.setText("75 %");
                }

                if (textFinal.length() == 6)
                {
                    finalResult.setText("90 %");
                }

                if (textFinal.length() == 7)
                {
                    finalResult.setText("100 %");
                }   */

                }
            }
        });
    }

    static int digSum(int n)
    {
        int sum = 0;

        while (n > 0 || sum > 9)
        {
            if (n == 0) {
                n = sum;
                sum = 0;
            }
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public void onBackPressed() {
        Intent intent = new Intent(MatchMaking.this,Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        e1.setText(null);
        e2.setText(null);
        e3.setText(null);
        e4.setText(null);
        e5.setText(null);
        e6.setText(null);
    }

    private void updateView(String lang)
    {
        Context context =LocaleHelper.setLocale(this, lang);
        Resources resources = context.getResources();
    }
}
