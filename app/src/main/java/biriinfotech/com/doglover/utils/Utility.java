package biriinfotech.com.doglover.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import biriinfotech.com.doglover.R;
import biriinfotech.com.doglover.ui.activities.LoginSignUpActivity;

public class Utility {

    private static Toast toast;
    static String LOG_TAG = "Utility";

    public static void showException(Context context, Exception e)
    {
        try {
            e.printStackTrace();
            Log.d(context.getClass().getName(), e.toString());
//            Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     * @param context
     * @return Returns true if there is network connectivity
     */
    public static boolean isInternetConnection(Context context) {
        boolean HaveConnectedWifi = false;
        boolean HaveConnectedMobile = false;

        try {

            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (ni != null) {
                if (ni.getType() == ConnectivityManager.TYPE_WIFI)
                    if (ni.isConnectedOrConnecting())
                        HaveConnectedWifi = true;
                if (ni.getType() == ConnectivityManager.TYPE_MOBILE)
                    if (ni.isConnectedOrConnecting())
                        HaveConnectedMobile = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return HaveConnectedWifi || HaveConnectedMobile;
    }

    /**
     * Display Toast Message
     **/
    public static void showToastMessageShort(Activity context, String message) {

        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context.getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        toast.show();
    }


    public static Uri getURI(Context context, File f) {
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".fileprovider", f);

        } else {
            uri = Uri.fromFile(f);
        }
        Log.e("Uri","Uri Utility- "+uri);
        return uri;
    }

    /**
     * Display Toast Message
     **/
    public static void showToastMessageShort(Activity context, String message, int statusCode) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context.getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        toast.show();
        if (statusCode == 1000) {
            clearSharedPreferneces(context);
            context.startActivity(new Intent(context, LoginSignUpActivity.class));
            context.finish();
            context.overridePendingTransition(R.anim.enter_translate, R.anim.exit_translate_navigation);
        }
    }

    /**
     * Display Toast Message
     **/
    public static void showToastMessageLong(Activity context, String message) {
        Toast.makeText(context.getApplicationContext(), message,
                Toast.LENGTH_LONG).show();
    }


    /**
     * Get IP address from first non-localhost interface
     *
     * @param useIPv4 true=return ipv4, false=return ipv6
     * @return address or empty string
     */
    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections
                    .list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf
                        .getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        // boolean isIPv4 =
                        // InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone
                                // suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr
                                        .substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
        } // for now eat exceptions
        return "";
    }

    public static void showAlertWithSingleButton(Context context, String positiveButton, String NegativeButton, String title, String message, final OnAlertOkClickListener onAlertOkClickListener) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(Html.fromHtml("<font color='#000000'>" + title + "</font>"));
        builder.setMessage(Html.fromHtml("<font color='#000000'>" + message + "</font>"));
        builder.setNegativeButton(NegativeButton, new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TOdo
                dialog.cancel();
            }
        });
        builder.setPositiveButton(positiveButton, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onAlertOkClickListener.onPositiveButtonClicked();
                dialog.dismiss();
            }
        });
//		builder.show();
        AlertDialog dialog = builder.create();
        dialog.show();
        Button nbutton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(context.getResources().getColor(R.color.black));
        Button pbutton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(context.getResources().getColor(R.color.black));
    }

    public static void showInternetAlertDialog(Context context, String positiveButton, String title, String message, final OnRetryButtonClicked onRetryButtonClicked) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(Html.fromHtml("<font color='#000000'>" + title + "</font>"));
        builder.setMessage(Html.fromHtml("<font color='#000000'>" + message + "</font>"));
        builder.setPositiveButton(positiveButton, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onRetryButtonClicked.onRetry();
                dialog.dismiss();
            }
        });
//		builder.show();
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
//		Button nbutton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
//		nbutton.setTextColor(context.getResources().getColor(R.color.black));
        Button pbutton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(context.getResources().getColor(R.color.colorAccent));
    }

//    builder_exit.setMessage(Html.fromHtml("<font color='#000000'>Are you sure you want to exit?</font>"))

    public static void onChangeClearButtonVisible(final Context context, final EditText editText, final TextInputLayout textInputLayout) {
        try {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (editText.getText().toString().trim().length() > 0) {

                        textInputLayout.setErrorEnabled(false);
//						linearLayout.setVisibility(View.VISIBLE);
                    } else {
//						linearLayout.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {


                }
            });
//			linearLayout.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View view) {
//					editText.setText("");
//
//				}
//			});


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSnack(Context mContext, View view, String msg) {
        try {
//			Snackbar snackbar = Snackbar
//					.make(view, msg, Snackbar.LENGTH_LONG)
//					.setAction("", new View.OnClickListener() {
//						@Override
//						public void onClick(View view) {
//						}
//					});
//			snackbar.setActionTextColor(Color.RED);
//			snackbar.show();


            // Create the Snackbar
            Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);
// Get the Snackbar's layout view
            Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
// Hide the text
            TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
            textView.setVisibility(View.INVISIBLE);

            LayoutInflater mInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

// Inflate our custom view
            View snackView = mInflater.inflate(R.layout.item_snackbar, null);
// Configure the view
//			ImageView imageView = (ImageView) snackView.findViewById(R.id.image);
//			imageView.setImageBitmap(image);
            TextView textViewTop = (TextView) snackView.findViewById(R.id.tv_msg);
            textViewTop.setText(msg);
            textViewTop.setTextColor(Color.WHITE);

// Add the view to the Snackbar's layout
            layout.addView(snackView, 0);
// Show the Snackbar
            snackbar.show();

        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    public static String getTime(long timeStamp) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timeStamp);
        String time = DateFormat.format("hh:mm a", cal).toString();
        return time;
    }

    public static String getDate(long timeStamp) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timeStamp);
        String date = DateFormat.format("EEEE, dd MMM", cal).toString();
        return date;
    }

    public static String getDateEDMY(long timeStamp) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timeStamp);
        String date = DateFormat.format("EEE, dd MMM, yyyy", cal).toString();
        return date;
    }

    public static String getFullDateTime(long timeStamp) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timeStamp);
        String dateTime = DateFormat.format("EEEE MMMM dd yyyy, hh:mm a", cal).toString();
        return dateTime;
    }

    public static void clearSharedPreferneces(Context mContext) {
        try {

            String tut_pref = PreferenceHandler.readString(mContext, PreferenceHandler.PREF_KEY_FIRST_TIME_LOGIN, "");
            PreferenceHandler.getEditor(mContext).clear().commit();
            PreferenceHandler.writeString(mContext, PreferenceHandler.PREF_KEY_FIRST_TIME_LOGIN, tut_pref);

        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (activity.getCurrentFocus() != null && inputManager != null) {
                inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
//                inputManager.showSoftInputFromInputMethod(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }


    public static String toUpperCase(Context context, String name) {
        name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        return name;
    }

    public static String toUpperCase(Context context, EditText name) {
        String text = "";
        text = Character.toUpperCase(name.getText().charAt(0)) + name.getText().toString().substring(1);
        return text;
    }

    public static void setTypeface(Context context, EditText editText, boolean isBoldWanted) {
        try {
            Typeface face = Typeface.createFromAsset(context.getAssets(), Constants.TYPEFACE);
            editText.setTypeface(face, Typeface.BOLD);
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    public static void setTypeface(Context context, View view, boolean isBoldWanted) {
        try {
            Typeface face = Typeface.createFromAsset(context.getAssets(), Constants.TYPEFACE);
            if (isBoldWanted) {
                if (view instanceof TextView) {
                    ((TextView)view).setTypeface(face, Typeface.BOLD);
                } else if (view instanceof EditText){
                    ((EditText)view).setTypeface(face, Typeface.BOLD);
                }
            } else {
                if (view instanceof TextView) {
                    ((TextView)view).setTypeface(face);
                } else if (view instanceof EditText){
                    ((EditText)view).setTypeface(face);
                }
            }

        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    /**
     * @param context           Activity Context
     * @param arrayListTextView ArrayList of TextViews
     * @param isBoldWanted      set it true if you want bold text
     */
    public static void setTypeface(Context context, TextView textView, ArrayList<TextView> arrayListTextView, boolean isBoldWanted) {
        try {
            Typeface face = Typeface.createFromAsset(context.getAssets(), Constants.TYPEFACE);
            if (textView == null && arrayListTextView != null) {
                for (TextView value : arrayListTextView) {
                    if (isBoldWanted) {
                        value.setTypeface(face, Typeface.BOLD);
                    } else {
                        value.setTypeface(face);
                    }
                }
            } else {
                if (isBoldWanted) {
                    textView.setTypeface(face, Typeface.BOLD);
                } else {
                    textView.setTypeface(face);
                }
            }
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", new Locale((Locale.US).toString()));
        return df.format(c.getTime());
    }

    static int dpToPx(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * scale);
    }

    static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * @param format "yyyy-MM-dd"
     */
    public static String getCurrentDate(String format) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(format, new Locale("TR"));
        return df.format(c.getTime());
    }

    public static String getTomorrow() {
        long oneDay = 86400000; // 86400000 miliseconds equals 1 day
        long tomorrow = Utility.dateToLong(Utility.getCurrentDate()) + oneDay;

        Calendar c = Calendar.getInstance();
        c.setTime(new Date(tomorrow));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", getLocale());
        return df.format(c.getTime());
    }

    public static String getOnlyDate(long timeStamp) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timeStamp);
        String date = DateFormat.format("dd", cal).toString();
        return date;
    }

    /**
     * Herhangi bir tarih bilgisini Long'a çevirir
     *
     * @param date like 2013-12-17
     */
    public static long dateToLong(String date) {


        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
        try {
            Date mDate = sdf.parse(date);
            long timeInMilliseconds = mDate.getTime();
            return timeInMilliseconds;
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        return 0;

    }

    public static Locale getLocale() {
        return Locale.getDefault();
    }

    public void showNoInternetDialog(final Activity mContext) {
        try {

            AlertDialog.Builder builder = new AlertDialog.Builder(mContext,
                    R.style.AppTheme);
            builder.setTitle(mContext.getResources().getString(R.string.no_internet_title));
            builder.setMessage(mContext.getResources().getString(R.string.no_internet));
            builder.setPositiveButton("OK", new OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    //((Activity) mContext).finish();

                }
            });

            builder.show();
        } catch (Exception e) {
            showToastMessageLong(mContext,
                    mContext.getResources().getString(R.string.no_internet));
        }
    }


    public interface OnAlertOkClickListener {
        void onPositiveButtonClicked();
    }

    public interface OnRetryButtonClicked {
        void onRetry();
    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


}
