package biriinfotech.com.doglover.ui.customviews;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import biriinfotech.com.doglover.R;

public class CallProgressWheel {

    public static ProgressDialog progressDialog;

    /**
     * Displays custom loading dialog
     */
    public static void showLoadingDialog(Context context, String message) {
        try {
            if (isDialogShowing()) {
                dismissLoadingDialog();
            }

            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (activity.isFinishing()) {
                    return;
                }
            }

            progressDialog = new ProgressDialog(context, android.R.style.Theme_Translucent_NoTitleBar);
            progressDialog.show();
            WindowManager.LayoutParams layoutParams = progressDialog.getWindow().getAttributes();
            layoutParams.dimAmount = 0.5f;
            progressDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            progressDialog.setCancelable(false);

            progressDialog.setContentView(R.layout.progresswheel);
            RelativeLayout frameLayout = (RelativeLayout) progressDialog.findViewById(R.id.dlgProgress);


            if (context.getClass().getName().equals("com.igniva.emoodi.ui.activities.LoginActivity")) {
                ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel)).setVisibility(View.GONE);
                ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel_white)).setVisibility(View.VISIBLE);
                ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel_white)).spin();
//                Utility.showToastMessageShort((Activity) context,"White");
            } else if (context.getClass().getName().equals("com.igniva.emoodi.ui.activities.SignUpActivity")) {
                ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel)).setVisibility(View.GONE);
                ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel_white)).setVisibility(View.VISIBLE);
                ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel_white)).spin();
            } else if (context.getClass().getName().equals("com.igniva.emoodi.ui.activities.TrackMoodActivity")) {
                ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel)).setVisibility(View.GONE);
                ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel_white)).setVisibility(View.VISIBLE);
                ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel_white)).spin();
            } else {
                ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel_white)).setVisibility(View.GONE);
                ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel)).setVisibility(View.VISIBLE);
                ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel)).spin();
            }


            // Set Message below progress wheel
            //TextView messageText = (TextView) progressDialog.findViewById(R.id.tvProgress);
            //  messageText.setTypeface(DataLogin.getFont(context));
            //  messageText.setText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void showLoadingDialog(Context context, String message, int color) {


        try {
            if (isDialogShowing()) {
                dismissLoadingDialog();
            }

            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (activity.isFinishing()) {
                    return;
                }
            }

            progressDialog = new ProgressDialog(context, android.R.style.Theme_Translucent_NoTitleBar);
            progressDialog.show();
            WindowManager.LayoutParams layoutParams = progressDialog.getWindow().getAttributes();
            layoutParams.dimAmount = 0.5f;
            progressDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            progressDialog.setCancelable(false);

            progressDialog.setContentView(R.layout.progresswheel);
            RelativeLayout frameLayout = (RelativeLayout) progressDialog.findViewById(R.id.dlgProgress);


            ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel)).setVisibility(View.GONE);
            ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel_white)).setVisibility(View.VISIBLE);

            ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel)).spin();
            // Set Message below progress wheel
            //TextView messageText = (TextView) progressDialog.findViewById(R.id.tvProgress);
            //  messageText.setTypeface(DataLogin.getFont(context));
            //  messageText.setText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void dismissLoadingDialog() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception e) {
            Log.e("e", "=" + e);
        }
    }

    public static boolean isDialogShowing() {
        try {
            if (progressDialog == null) {
                return false;
            } else {
                return progressDialog.isShowing();
            }
        } catch (Exception e) {
            return false;
        }
    }


}
