package ru.mirea.mitrofanov.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyProgressDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMax(100);
        progressDialog.setMessage("Идет загрузка...");
        progressDialog.setTitle("Example");
        progressDialog.setButton(Dialog.BUTTON_POSITIVE, "Oк", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ((MainActivity)getActivity()).onFinalClick();
                dialog.cancel();
            }
        });
        return progressDialog;
    }
}
