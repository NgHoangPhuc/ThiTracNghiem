package detai.android.Object.session;

import android.content.Context;
import android.content.SharedPreferences;

<<<<<<< HEAD
=======
import java.util.ArrayList;

import detai.android.Object.CauHoi;

>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
/**
 * Created by hoang on 05-May-18.
 */

public class SessionManager {    // LogCat tag
    // Shared preferences file name
    private static final String PREF_NAME = "LoginSession";
    private static final String KEY_IS_LOGGED_IN = "Username";
<<<<<<< HEAD
=======
    private static final String LIST_DE = "DanhSachDeChinhSua";
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
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

<<<<<<< HEAD
    public String getUsername(){
        return pref.getString(KEY_IS_LOGGED_IN,null);
    }
=======
    public String getUsername() {
        return pref.getString(KEY_IS_LOGGED_IN, null);
    }


//    ArrayList<CauHoi> listcauhoi = new ArrayList<>();
//    public void addQuestion(CauHoi cauHoi) {
//        pref.getClass().asSubclass()
//    }
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
}
