package magic.numerology.ideas.turnup.numerologymagic;


import android.app.Application;
import android.content.Context;

import magic.numerology.ideas.turnup.numerologymagic.LocaleHelper;

public class MainApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
}