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

import java.util.Locale;

import io.paperdb.Paper;

public class ContactUs extends AppCompatActivity
{
    ImageView ContactUs_back, ContactUs_whatsapp, contactUs_setting ;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase , "en"));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactus);
        Paper.init(this);

        String language = Paper.book().read("language");
        if (language == null)

            Paper.book().write("language", "en");
        updateView((String)Paper.book().read("language"));

        ContactUs_back = (ImageView) findViewById(R.id.back_contactUs);
        ContactUs_whatsapp = (ImageView) findViewById(R.id.contactUs_question);
        contactUs_setting = (ImageView) findViewById(R.id.contactUs_setting);

        ContactUs_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactUs.this, Home.class);
                startActivity(intent);
            }
        });

        ContactUs_whatsapp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
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

        contactUs_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= 19)
                {
                    final String[] listItems = new String[]{"English", "हिंदी", "मराठी"};
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(ContactUs.this);
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
    }

    private void updateView(String lang)
    {
        Context context =LocaleHelper.setLocale(this, lang);
        Resources resources = context.getResources();
    }
}
