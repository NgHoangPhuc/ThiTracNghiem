package detai.android.Object.session;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hoang on 05-May-18.
 */

public class SessionManager {    // LogCat tag
    // Shared preferences file name
    private static final String PREF_NAME = "LoginSession";
    private static final String KEY_IS_LOGGED_IN = "Username";
    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void login(String username) {

        editor.putString(KEY_IS_LOGGED_IN, username);

        // commit changes
        editor.commit();
    }

    public void logout() {
        editor.remove(KEY_IS_LOGGED_IN);

        // commit changes
        editor.commit();
    }

    public String getUsername(){
        return pref.getString(KEY_IS_LOGGED_IN,null);
    }
}
