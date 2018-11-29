package magic.numerology.ideas.turnup.numerologymagic;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Locale;

import io.paperdb.Paper;


public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String PREFS = "examplePrefs";
    Button b1;
    EditText e1;
    EditText e2;
    EditText e3;
    TextView home_text, karmic_Number;
    ImageView settings, question;
    private ImageView facebook, youtube, twitter, googlePlus;
    TextView turnup_email;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase , "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (
                        this,
                        drawer, toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close
                );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator((int) R.drawable.navigation_menu);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        MenuItem item = menu.findItem(R.id.social_media);

        Menu menu1 = navigationView.getMenu();
        MenuItem item1 = menu1.findItem(R.id.turnup_idea);

        Menu menus = navigationView.getMenu();

        MenuItem babyname = menus.findItem(R.id.babyName);
        babyname.setTitle(R.string.babyname_language);

        MenuItem address = menus.findItem(R.id.address);
        address.setTitle(R.string.address_language);

        MenuItem companyname = menus.findItem(R.id.companyName);
        companyname.setTitle(R.string.companyname_language);

        MenuItem mobilenumber = menus.findItem(R.id.mobileNumber);
        mobilenumber.setTitle(R.string.mobilenumber_language);

        MenuItem vehiclenumber = menus.findItem(R.id.vehicleNumber);
        vehiclenumber.setTitle(R.string.vehiclenumber_language);

        MenuItem matchmaking = menus.findItem(R.id.matchMaking);
        matchmaking.setTitle(R.string.matchmaking_language);

        MenuItem yourname = menus.findItem(R.id.yourName);
        yourname.setTitle(R.string.yourname_language);

        MenuItem contactus = menus.findItem(R.id.contact);
        contactus.setTitle(R.string.contactUs_language);

        MenuItem website = menus.findItem(R.id.website);
        website.setTitle(R.string.ourwebsite_language);

        question = (ImageView) findViewById(R.id.home_question);
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String text = "Hello Numerology Magic need your help !!";// Replace with your message.

                    String toNumber = "918655990799"; // Replace with mobile phone number without +Sign or leading zeros.


                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        turnup_email = (TextView) item1.getActionView().findViewById(R.id.turnupidea);
        turnup_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://turnupidea.com/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        facebook = (ImageView) item.getActionView().findViewById(R.id.facebook_button);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/vastoshapati/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        youtube = (ImageView) item.getActionView().findViewById(R.id.youtube_button);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/channel/UCh9W9Ha_HJ5TQtt0rHfdFFg";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        twitter = (ImageView) item.getActionView().findViewById(R.id.twitter_button);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://twitter.com/vastoshapati";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        googlePlus = (ImageView) item.getActionView().findViewById(R.id.googleplus_button);
        googlePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Send email", "");
                String[] TO = {"vastoshapati@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Need Your Help ?");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Write to me for more details");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.i("Email Send Complete.. ", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Home.this,
                            "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        home_text = (TextView) findViewById(R.id.home_text);
        SharedPreferences example = getSharedPreferences(PREFS, 0);
        String userString = example.getString("userMessage", "");
        home_text.setText(userString);

        karmic_Number = (TextView) findViewById(R.id.home_text_karmicNo);
        SharedPreferences karmic = getSharedPreferences(PREFS, 0);
        String userKarmic = karmic.getString("userMessage1", "");
        karmic_Number.setText(userKarmic);

        Paper.init(this);

        String language = Paper.book().read("language");
        if (language == null)

            Paper.book().write("language", "en");
            updateView((String)Paper.book().read("language"));


        settings = (ImageView) findViewById(R.id.home_setting);
        settings.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (android.os.Build.VERSION.SDK_INT >= 19)
                {
                    final String[] listItems = new String[]{"English", "हिंदी", "मराठी"};
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(Home.this);
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

        e1 = (EditText) findViewById(R.id.edit1_home);
        e2 = (EditText) findViewById(R.id.edit2_home);
        e3 = (EditText) findViewById(R.id.edit3_home);
        b1 = (Button) findViewById(R.id.button1_home);

        e1.setFilters(new InputFilter[]{new InputFilterMinMax("0", "31")});
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 2) {
                    e2.requestFocus();
                }
            }
        });

        e2.setFilters(new InputFilter[]{new InputFilterMinMax("0", "12")});
        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 2) {
                    e3.requestFocus();
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                // TODO Auto-generated method stub

                String mynum1 = e1.getText().toString();
                String mynum2 = e2.getText().toString();
                String mynum3 = e3.getText().toString();

                if (TextUtils.isEmpty(mynum1) || TextUtils.isEmpty(mynum2) || TextUtils.isEmpty(mynum3)) {
                    Toast.makeText(Home.this, "Enter Date of Birth", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mynum3.length() < 4) {
                    Toast.makeText(Home.this, "Enter Valid Year", Toast.LENGTH_SHORT).show();
                    return;
                }

                int n = (Integer.parseInt(mynum1) + Integer.parseInt(mynum2)) + Integer.parseInt(mynum3);
                System.out.println(Home.digSum(n));

                int nn = Integer.parseInt(mynum1);
                System.out.println(Home.digSum(nn));

                Intent myIntent = new Intent(v.getContext(), DobResult.class);
                myIntent.putExtra("result", Home.digSum(n));
                myIntent.putExtra("bhagyank", Home.digSum(nn));
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    private void updateView(String lang)
    {
        Context context =LocaleHelper.setLocale(this, lang);
        Resources resources = context.getResources();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.babyName) {
            Intent intent = new Intent(this, BabyName.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.address) {
            Intent intent = new Intent(this, Address.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.companyName) {
            Intent intent = new Intent(this, CompanyName.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.mobileNumber) {
            Intent intent = new Intent(this, MobileResult.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.matchMaking) {
            Intent intent = new Intent(this, MatchMaking.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.vehicleNumber) {
            Intent intent = new Intent(this, VehicleNumber.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.yourName) {
            Intent intent = new Intent(this, YourName.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.website) {
            String url = "http://vastoshapati.com/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        } else if (id == R.id.contact) {
            Intent intent = new Intent(this, ContactUs.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    static int digSum(int n) {
        int sum = 0;
        while (true) {
            if (n <= 0) {
                if (sum <= 9) {
                    return sum;
                }
            }
            if (n == 0) {
                n = sum;
                sum = 0;
            }
            sum += n % 10;
            n /= 10;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Close Vastoshapati ?")
                    .setIcon(R.mipmap.appicon)
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            Home.super.onBackPressed();
                        }
                    }).create().show();
        }
    }
}
