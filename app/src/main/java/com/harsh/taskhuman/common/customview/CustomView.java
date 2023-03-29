package com.harsh.taskhuman.common.customview;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.harsh.taskhuman.R;

import java.lang.ref.WeakReference;

/**
 * Created by Harsh Mittal on 19/07/22.
 */
public class CustomView {
    private static final Handler sMainHandler = new Handler(Looper.getMainLooper());
    private static final String TAG = "CustomView";
    private static WeakReference<Toast> toast = null;
    private static Toast singleInstanceToast = null;

    private static Toast getSafeToast(Context context, String message, int duration) {
        return ToastCompat.makeText(context, message, duration);
    }

    public static void showNeutralToast(Context context, String message, int duration) {
        if (toast != null && toast.get() != null) {
            toast.get().cancel();
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_toast_neutral, null);
        TextView toast_msg = layout.findViewById(R.id.textMessage);
        toast_msg.setText(message);
        toast_msg.setTextSize(13);
        toast_msg.setTypeface(getMediumTypeFace(context));
        toast = new WeakReference<>(getSafeToast(context, message, duration));
        toast.get().setView(layout);
        toast.get().show();
    }

    public static void ShowToast(Context context, int stringResId) {
        String message = context.getString(stringResId);
        if (!TextUtils.isEmpty(message))
            runOnUiThread(() -> showNeutralToast(context, message, Toast.LENGTH_LONG));
    }

    public static void ShowToast(Context context, String message) {
        if (!TextUtils.isEmpty(message))
            runOnUiThread(() -> showNeutralToast(context, message, Toast.LENGTH_LONG));
    }

    public static void ShowShortToast(Context context, String message) {
        if (context != null)
            runOnUiThread(() -> showNeutralToast(context, message, Toast.LENGTH_SHORT));
    }

    public static void showToastAtPosition(LayoutInflater inflater, Context context, String message, int gravity, Boolean showOkButton, int backgroundColor) {
        runOnUiThread(() -> {
            View layout = inflater.inflate(R.layout.toast_layout, null);
            TextView text = layout.findViewById(R.id.textMessage);
            Button ok = layout.findViewById(R.id.buttonOk);
            text.setText(message);
            Toast toast = getSafeToast(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.FILL_HORIZONTAL | gravity, 0, 200);
            if (gravity == Gravity.BOTTOM) {
                CardView card = layout.findViewById(R.id.toast_layout_root);
                card.setCardBackgroundColor(backgroundColor);
                text.setGravity(Gravity.START);
                text.setTextColor(context.getResources().getColor(R.color.white));
                toast.setGravity(Gravity.FILL_HORIZONTAL | gravity, 0, 100);
                if (showOkButton) ok.setVisibility(View.VISIBLE);
            }
            toast.setView(layout);
            try {
                toast.show();
            } catch (WindowManager.BadTokenException e) {
                /* ignore */
            }
        });
    }

    public static void showErrorToast(Context context, String text) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int resource = R.layout.custom_toast_red;
        View layout = inflater.inflate(resource, null);

        TextView textView = layout.findViewById(R.id.textMessage);
        textView.setText(text);

        Toast toast = getSafeToast(context, text, Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.BOTTOM, 20, 50);
        toast.setView(layout);
        try {
            toast.show();
        } catch (WindowManager.BadTokenException e) {
            /* ignore */
        }
    }

    public static void showSuccessToast(Context context, String text) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int resource = R.layout.custom_toast_green;
        View layout = inflater.inflate(resource, null);

        TextView textView = layout.findViewById(R.id.textMessage);
        textView.setText(text);

        toast = new WeakReference<>(getSafeToast(context, text, Toast.LENGTH_SHORT));
        toast.get().setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.get().setView(layout);

        try {
            toast.get().show();
        } catch (WindowManager.BadTokenException e) {
            /* ignore */
        }
    }

    public static void showNetworkErrorToast(Context context) {
        runOnUiThread(() -> {
            showErrorToast(context, context.getString(R.string.no_internet_connection));
        });
    }

    public static void showErrorToastOnUI(Context context, String message) {
        runOnUiThread(() -> {
            if (!message.isEmpty()) showErrorToast(context, message);
        });
    }

    private static void showCustomToastWithIcon(Context context, String message, int duration, Drawable drawable) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_toast_with_icon, null);
        TextView toast_msg = layout.findViewById(R.id.toast_msg);
        ImageView toast_icon = layout.findViewById(R.id.toast_icon);
        toast_msg.setText(message);
        toast_msg.setTextSize(13);
        toast_msg.setTypeface(getMediumTypeFace(context));
        toast_icon.setImageDrawable(drawable);
        toast = new WeakReference<>(getSafeToast(context, message, Toast.LENGTH_LONG));
        toast.get().setGravity(Gravity.BOTTOM, 0, 0);
        toast.get().setView(layout);
        toast.get().show();
    }

    private static void ShowToastWithIcon(Context context, String message, int imageId) {
        runOnUiThread(() -> showCustomToastWithIcon(context, message, Toast.LENGTH_LONG, ContextCompat.getDrawable(context, imageId)));
    }

    public static Snackbar showSnackBar(Context context, ViewGroup viewGroup, int duration, String message, String btnText, View.OnClickListener onClickListener) {
        Snackbar snackbar = Snackbar.make(viewGroup, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.white));

        final View snackView = snackbar.getView();
        final TextView tv = snackView.findViewById(R.id.snackbar_text);
        final TextView tvAction = snackView.findViewById(R.id.snackbar_action);
        tv.setTextSize(12);
        tv.setTypeface(getLightTypeFace(context));
        tvAction.setTextSize(12);
        tvAction.setTypeface(getMediumTypeFace(context)); // setting snack bar text size and typeface
        if (btnText != null && !btnText.trim().isEmpty()) {
            snackbar.setAction(btnText, view -> {
                snackbar.dismiss();
                if (onClickListener != null) onClickListener.onClick(view);
            });
        }
        if (duration != -1) {
            snackbar.setDuration(duration);
        }
        return snackbar;
    }

    public static Typeface getMediumTypeFace(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/inter_medium.ttf");
    }

    private static Typeface getLightTypeFace(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/inter_light.ttf");
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Thread.currentThread() == sMainHandler.getLooper().getThread()) {
            runnable.run();
        } else {
            sMainHandler.post(runnable);
        }
    }

    public static Toast getSingleInstanceToast() {
        return singleInstanceToast;
    }

    // If used from activities pass fragment as null
    public static void ShowShortToastSingleInstance(Context context, String message, Fragment fragment) {
        Log.d("singleInstanceToast : ", message);
        if (context != null) {
            runOnUiThread(() -> {
                if (singleInstanceToast != null) singleInstanceToast.cancel();
                initSingleInstanceToast(context, message);
                try {
                    if (checkForValidContext(context, fragment)) singleInstanceToast.show();
                } catch (WindowManager.BadTokenException e) {
                    Log.e(TAG, e.getMessage());
                }
            });
        }
    }

    public static boolean checkForValidContext(Context context, Fragment fragment) {
        if (context == null) return false;
        if (context instanceof Activity) {
            return !((Activity) context).isFinishing() || !((Activity) context).isDestroyed();
        }
        return fragment != null && (fragment.isAdded() || !fragment.isDetached());
    }

    public static AlertDialog.Builder showMessage(Context context, String message) {
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(context);
        builder.setMessage(message);
        builder.setPositiveButton(context.getString(R.string.ok), (dialogInterface, i) -> dialogInterface.dismiss());
        builder.create();
        builder.setCancelable(false);
        return builder;
    }

    private static void initSingleInstanceToast(Context context, String message) {
        singleInstanceToast = getSafeToast(context, message, Toast.LENGTH_SHORT);
        singleInstanceToast.setGravity(Gravity.BOTTOM, 0, 0);
        ViewGroup group = (ViewGroup) singleInstanceToast.getView();
        if (group != null) {
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(13);
            messageTextView.setTypeface(getMediumTypeFace(context));
        }
    }

    public static void clearContext() {
        if (singleInstanceToast != null) {
            singleInstanceToast.cancel();
            singleInstanceToast = null;
        }
        if (toast != null && toast.get() != null) {
            toast.get().cancel();
            toast = null;
        }
    }

    public static void showDialogWithTitleOkButton(Context context, String message, String title) {
        new AlertDialog.Builder(context).setMessage(message).setTitle(title).setPositiveButton(context.getString(R.string.ok), (dialog, which) -> dialog.dismiss()).setNegativeButton("", null).create().show();
    }

    public static void showMessageOKCancel(Context context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context).setMessage(message).setPositiveButton(context.getString(R.string.ok), okListener).setNegativeButton(context.getString(R.string.cancel), null).create().show();
    }
}
