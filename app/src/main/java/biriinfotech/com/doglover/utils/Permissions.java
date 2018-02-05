package biriinfotech.com.doglover.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class Permissions {

    public static final int MY_PERMISSIONS_REQUEST_ALL = 0;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_READ = 1;
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 3;

    public static Boolean checkPermissionStorage(Activity activity) {
        Boolean res = false;
        if (Build.VERSION.SDK_INT >= 23) {
            // Marshmallow+
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_READ);
            } else {
                res = true;
            }
        } else {
            // Pre-Marshmallow
            res = true;
        }


        return res;
    }

    public static Boolean  checkPermissionCamera(Activity activity) {
        Boolean res = false;
        if (Build.VERSION.SDK_INT >= 23) {
            // Marshmallow+
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
            } else {
                res = true;
            }
        } else {
            // Pre-Marshmallow
            res = true;
        }
        return res;
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

    public static boolean checkPermissionsAll(Activity activity) {
        try {
            int PERMISSION_ALL = 1;
            String[] PERMISSIONS = {Manifest.permission.INTERNET, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
            if (!hasPermissions(activity, PERMISSIONS)) {
                ActivityCompat.requestPermissions(activity, PERMISSIONS, PERMISSION_ALL);
                return false;
            }
            else
            {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkPermissionsStorage(Activity activity) {
        try {
            int PERMISSION_ALL = 1;
            String[] PERMISSIONS =  {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (!hasPermissions(activity, PERMISSIONS)) {
                ActivityCompat.requestPermissions(activity, PERMISSIONS, PERMISSION_ALL);
                return false;
            }
            else
            {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean checkPermissionsCameraStorage(Activity activity) {
        try {
            int PERMISSION_ALL = 1;
            String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
            if (!hasPermissions(activity, PERMISSIONS)) {
                ActivityCompat.requestPermissions(activity, PERMISSIONS, PERMISSION_ALL);
                return  false;
            }
            else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }



    public static Boolean checkPermissionReadContacts(Activity activity) {
        Boolean res = false;
        if (Build.VERSION.SDK_INT >= 23) {
            // Marshmallow+
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            } else {
                res = true;
            }
        } else {
            // Pre-Marshmallow
            res = true;
        }


        return res;
    }


    public static Boolean requestAllPermissions(Activity activity) {
        Boolean res = false;
        if (Build.VERSION.SDK_INT >= 23) {
            // Marshmallow+
            if ((ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
                    && (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
                activity.requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            } else {
                res = true;
            }
        } else {
            // Pre-Marshmallow
            res = true;
        }


        return res;
    }

//    @TargetApi(Build.VERSION_CODES.M)
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//
////            Internet 0
////            Read Contacts 1
////            Write Contacts 2
////            Read External Storage 3
////            Write External Storage 4
////            Camera 5
//            case 1: {
//                try {
//
//                    if (grantResults.length > 0
//                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                        getAllContacts();
//
//                        // permission was granted, yay! Do the
//                        // contacts-related task you need to do.
//                    } else {
//                        mProgressBar.setVisibility(View.INVISIBLE);
//                        // permission denied, boo! Disable the
//                        // functionality that depends on this permission.
////                        Toast.makeText(MyContactsActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
//                    }
//                    return;
//                } catch (Exception e) {
//                    CrashReportingManager.logException(e); e.printStackTrace();
//                }
//            }
//
//
//        }
//    }

}
