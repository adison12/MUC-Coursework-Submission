package uk.ac.gcu.mondayschild;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;


/**
 * Created by rla on 17/09/2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class mcAboutDialogue extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder mcAboutDialog = new AlertDialog.Builder(getActivity());
        mcAboutDialog.setMessage(R.string.dialog_About)
                .setPositiveButton(R.string.dialog_About_OK_btn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        mcAboutDialog.setTitle("About");
        mcAboutDialog.setIcon(R.mipmap.ic_menu_action_about);
        // Create the AlertDialog object and return it
        return mcAboutDialog.create();
    }

}
