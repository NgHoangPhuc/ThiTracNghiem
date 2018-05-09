package detai.android.Object.message;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by hoang on 05-May-18.
 */

public class Message {
    public static void showDialogYN(Context context, String message){
        AlertDialog.Builder alertbuild = new AlertDialog.Builder(context);
        alertbuild.setMessage(message);
        alertbuild.setCancelable(true);
        alertbuild.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        alertbuild.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = alertbuild.create();
        alert11.show();
    }
    public static void showDialog(Context context, String message){
        AlertDialog.Builder alertbuild = new AlertDialog.Builder(context);
        alertbuild.setMessage(message);
        alertbuild.setCancelable(true);
        alertbuild.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = alertbuild.create();
        alert11.show();
    }
}
