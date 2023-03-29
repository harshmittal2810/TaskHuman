package com.harsh.taskhuman.common.customview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.harsh.taskhuman.R;
import com.harsh.taskhuman.common.util.AppExceptionHandler;


public class ProgressAnimDialog extends Dialog {

    private static final String TAG = "ProgressAnimDialog";

    public ProgressAnimDialog(Context context) {
        super(context);
    }

    public ProgressAnimDialog(Context context, int theme) {
        super(context, theme);
    }

    public static ProgressAnimDialog show(Context context, CharSequence message, boolean cancelable, OnCancelListener cancelListener) {
        ProgressAnimDialog dialog = null;
        try {
            if (context instanceof Activity) {
                if (((Activity) context).isFinishing()) {
                    Log.d(TAG, "Context Finished");
                    return null;
                }
            }

            dialog = new ProgressAnimDialog(context, R.style.ProgressSpinner);
            dialog.setTitle("");
            dialog.setContentView(R.layout.progress_spinner_dialog);
            if (message == null || message.length() == 0) {
                dialog.findViewById(R.id.message).setVisibility(View.GONE);
            } else {
                TextView txt = dialog.findViewById(R.id.message);
                txt.setText(message);
            }
            dialog.setCancelable(cancelable);
            dialog.setOnCancelListener(cancelListener);
            dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.dimAmount = 0.1f;
            dialog.getWindow().setAttributes(lp);
            dialog.show();
        } catch (Exception e) {
            AppExceptionHandler.handle(e);
        }
        return dialog;
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        ImageView imageView = findViewById(R.id.spinnerImageView);
        AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
        spinner.start();
    }

    public void setMessage(CharSequence message) {
        if (message != null && message.length() > 0) {
            findViewById(R.id.message).setVisibility(View.VISIBLE);
            TextView txt = findViewById(R.id.message);
            txt.setText(message);
            txt.invalidate();
        }
    }
}