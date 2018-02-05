package biriinfotech.com.doglover.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import biriinfotech.com.doglover.R;

/**
 * Common Utility to Validate any type of user input
 */
public class Validation {

    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String passsword) {
        boolean isValid = false;
        //TODO to check the validation of a password
        return isValid;
    }

    public static boolean isValidMobile(Context context, EditText countryCode, EditText mobileNumber) {
        boolean isValid = false;
        if (!isNullorEmpty(countryCode) && !isNullorEmpty(mobileNumber)) {
            String mCountryCode = countryCode.getText().toString();
            String mMobileNumber = mobileNumber.getText().toString();
            return true;
        } else {
            return isValid;
        }
    }



    /**
     * To check whether a given edittext is null or empty
     *
     * @param editText
     * @return status of edittext
     */
    public static boolean isNullorEmpty(EditText editText) {
        if (editText == null || editText.length() < 1)
            return true;
        else
            return false;
    }

    /**
     *
     * @param activity          Activity context
     * @param mEtFirstName      First Name
     * @param mEtLastName       Last Name
     * @param mEtEmail          Email
     * @param mEtPhoneNumber    Phone Number
     * @param mTilFirstName
     * @param mTilLastName
     * @param mTilEmail
     * @param mTilPhoneNumber
     * @return
     */

    public static String isValidSignUpSocial(Activity activity, EditText mEtFirstName, TextInputLayout mTilFirstName, EditText mEtLastName, TextInputLayout mTilLastName, EditText mEtEmail, TextInputLayout mTilEmail, EditText mEtPhoneNumber, TextInputLayout mTilPhoneNumber, ScrollView mSvSignup) {

        if (FieldValidators.isNullOrEmpty(mEtFirstName)
                && FieldValidators.isNullOrEmpty(mEtLastName) && FieldValidators.isNullOrEmpty(mEtEmail) && FieldValidators.isNullOrEmpty(mEtPhoneNumber) ) {
            Utility.showSnack(activity,mEtFirstName,"Please enter all fields");
            focusOnView(mSvSignup,mEtFirstName);
            mEtFirstName.setFocusable(true);
            mEtFirstName.requestFocus();
            return null;

        } else if (FieldValidators.isNullOrEmpty(mEtFirstName)) {
            mTilFirstName.setError("Please enter first name");
            focusOnView(mSvSignup,mEtFirstName);
            mEtFirstName.setFocusable(true);
            openKeyBoard(activity, mEtFirstName);
            mEtFirstName.requestFocus();
        }
        else if (FieldValidators.isValidLength(mEtFirstName,Constants.FIRST_NAME)) {
            mTilFirstName.setError("First name should have 3 to 30 characters");
            focusOnView(mSvSignup,mEtFirstName);
            mEtFirstName.setFocusable(true);
            openKeyBoard(activity, mEtFirstName);
            mEtFirstName.requestFocus();
        }
        else if (FieldValidators.isNullOrEmpty(mEtLastName)) {
            mTilLastName.setError("Please enter last name");
            focusOnView(mSvSignup,mEtLastName);
            mEtLastName.setFocusable(true);
            openKeyBoard(activity, mEtLastName);
            mEtLastName.requestFocus();
        }
        else if (FieldValidators.isValidLength(mEtLastName,Constants.LAST_NAME)) {
            mTilLastName.setError("Last name should have 3 to 30 characters");
            focusOnView(mSvSignup,mEtLastName);
            mEtLastName.setFocusable(true);
            openKeyBoard(activity, mEtLastName);
            mEtLastName.requestFocus();
        }

        else if (FieldValidators.isNullOrEmpty(mEtEmail)) {
            mTilEmail.setError("Please enter email address");
            focusOnView(mSvSignup,mEtEmail);
            mEtEmail.setFocusable(true);
            openKeyBoard(activity, mEtEmail);
            mEtEmail.requestFocus();

        } else if (!isValidEmail(mEtEmail.getText().toString().trim())) {
            mTilEmail.setError("Please enter valid email address");
            focusOnView(mSvSignup,mEtEmail);
            mEtEmail.setFocusable(true);
            openKeyBoard(activity, mEtEmail);
            mEtEmail.requestFocus();

        }
        else if (FieldValidators.isValidLength(mEtEmail,Constants.EMAIL)) {
            mTilEmail.setError("Email should have 2 to 50 characters");
            focusOnView(mSvSignup,mEtEmail);
            mEtEmail.setFocusable(true);
            openKeyBoard(activity, mEtEmail);
            mEtEmail.requestFocus();
        }

        else if (FieldValidators.isNullOrEmpty(mEtPhoneNumber)) {
            mTilPhoneNumber.setError("Please enter phone number");
//            focusOnView(mSvSignup,mEtPhoneNumber);
            mEtPhoneNumber.setFocusable(true);
            openKeyBoard(activity, mEtPhoneNumber);
            mEtPhoneNumber.requestFocus();
        }
        else if (FieldValidators.isValidLength(mEtPhoneNumber,Constants.PHONE_NUMBER)) {
            mTilPhoneNumber.setError("Phone number should have 8 to 15 digits");
            focusOnView(mSvSignup,mEtPhoneNumber);
            mEtPhoneNumber.setFocusable(true);
            openKeyBoard(activity, mEtPhoneNumber);
            mEtPhoneNumber.requestFocus();
        }


        else {
            return Constants.VALIDATED;
        }
        return null;

    }

    /**
     *
     * @param activity          Activity context
     * @param mEtName           Name
     * @param mEtEmail          Email
     * @param mEtPassword       Password
     * @param mTilName
     * @param mTilEmail
     * @param mTilPassword
     * @return
     */

    public static String isValidSignUp(Activity activity, EditText mEtName, TextInputLayout mTilName, EditText mEtEmail, TextInputLayout mTilEmail, EditText mEtPassword, TextInputLayout mTilPassword) {

        if (FieldValidators.isNullOrEmpty(mEtName)
                && FieldValidators.isNullOrEmpty(mEtEmail) && FieldValidators.isNullOrEmpty(mEtPassword) ) {
            Utility.showSnack(activity,mEtName,"Please enter all fields");
            mEtName.setFocusable(true);
            mEtName.requestFocus();
            return null;

        } else if (FieldValidators.isNullOrEmpty(mEtName)) {
            mEtName.setError("Please enter name");
            mEtName.setFocusable(true);
            openKeyBoard(activity, mEtName);
            mEtName.requestFocus();
        }
        else if (FieldValidators.isValidLength(mEtName,Constants.FIRST_NAME)) {
            mTilName.setError("First name should have 3 to 30 characters");
            mEtName.setFocusable(true);
            openKeyBoard(activity, mEtName);
            mEtName.requestFocus();
        }

        else if (FieldValidators.isNullOrEmpty(mEtEmail)) {
            mTilEmail.setErrorEnabled(true);
            mTilEmail.setError("Please enter email address");
            mEtEmail.setFocusable(true);
            openKeyBoard(activity, mEtEmail);
            mEtEmail.requestFocus();

        } else if (!isValidEmail(mEtEmail.getText().toString().trim())) {
            mTilEmail.setError("Please enter valid email address");
            mEtEmail.setFocusable(true);
            openKeyBoard(activity, mEtEmail);
            mEtEmail.requestFocus();

        }
        else if (FieldValidators.isValidLength(mEtEmail,Constants.EMAIL)) {
            mTilEmail.setError("Email should have 2 to 50 characters");
            mEtEmail.setFocusable(true);
            openKeyBoard(activity, mEtEmail);
            mEtEmail.requestFocus();
        }


        else if (FieldValidators.isNullOrEmpty(mEtPassword)) {
            mTilPassword.setError("Please enter password");
//            focusOnView(mSvSignup,mEtPassword);
            mEtPassword.setFocusable(true);
            openKeyBoard(activity, mEtPassword);
            mEtPassword.requestFocus();
        }
        else
        if (!validatePassword(activity, mEtPassword,mTilPassword,activity.getString(R.string.err_msg_password))) {
            mEtPassword.setFocusable(true);
            openKeyBoard(activity, mEtPassword);
            mEtPassword.requestFocus();
        }
        else {
            return Constants.VALIDATED;
        }
        return null;

    }

    public static String isValidForgotPassword(Activity activity, EditText mEtEmail, TextInputLayout mTilEmail) {

       if (FieldValidators.isNullOrEmpty(mEtEmail)) {
            mTilEmail.setError("Please enter email address");
            mEtEmail.setFocusable(true);
            openKeyBoard(activity, mEtEmail);
            mEtEmail.requestFocus();

        } else if (!isValidEmail(mEtEmail.getText().toString().trim())) {
            mTilEmail.setError("Please enter valid email address");
            mEtEmail.setFocusable(true);
            openKeyBoard(activity, mEtEmail);
            mEtEmail.requestFocus();

        }
        else if (FieldValidators.isValidLength(mEtEmail,Constants.EMAIL)) {
            mTilEmail.setError("Email should have 2 to 50 characters");
            mEtEmail.setFocusable(true);
            openKeyBoard(activity, mEtEmail);
            mEtEmail.requestFocus();
        }
               else {
            return Constants.VALIDATED;
        }
        return null;

    }


    public static  void focusOnView(final ScrollView mSvSignup, final EditText editText){
        mSvSignup.post(new Runnable() {
            @Override
            public void run() {
                mSvSignup.scrollTo(0, editText.getBottom());
            }
        });
    }


    public static boolean validatePassword(Activity applicationContext, View view,TextInputLayout mTilPassword, String data) {
        if (((EditText) view).getText().toString().isEmpty()) {
            mTilPassword.setError(data);
            openKeyBoard(applicationContext, (EditText) view);
            return false;
        }
		/*if (!isPasswordSpace(((EditText) view).getText().toString())) {
			((EditText) view).setError(applicationContext.getString(R.string.err_msg_space));
			requestFocus(applicationContext, ((EditText) view));
			return false;
		}*/
        if (!isLegalPassword(((EditText) view).getText().toString())) {
            mTilPassword.setError(applicationContext.getString(R.string.err_msg_password_not_valid));
            openKeyBoard(applicationContext, (EditText) view);
            return false;
        }
//        if(!validateAlphaNumericPassword(((EditText) view).getText().toString().trim())){
//            mTilPassword.setError(applicationContext.getString(R.string.err_msg_password_alpha_numeric));
//            openKeyBoard(applicationContext, (EditText) view);
//            return false;
//        }
        return true;
    }
    public static boolean isLegalPassword(String pass) {
        if (pass.length()<6) return false;

        return true;
    }

    /**
     * Validate password with regular expression
     * @param password password for validation
     * @return true valid password, false invalid password
     */
    public static boolean validateAlphaNumericPassword(final String password){
		/*String PASSWORD_PATTERN =
				"((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";*/
        String PASSWORD_PATTERN =
                "((?=.*\\d)(?=.*[A-Z])(?=.*[!@#$%&*()_+=|:;<>?{}\\[\\]~-]).{6,15})";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

    }


    /**
     * Validation for SignIn
     *
     * @param activity   reference of calling class
     * @param etEmail    Edittext of email input
     * @param etPassword Edittext of password input
     * @return if the validation is success or fail
     */
    public static String isValidSignIn(Activity activity,EditText etEmail,TextInputLayout mTilEmail, EditText etPassword,TextInputLayout mTilPassword) {

        if (FieldValidators.isNullOrEmpty(etEmail) && FieldValidators.isNullOrEmpty(etPassword)) {
//            Utility.showToastMessageLong(activity, "Please enter all fields");
            Utility.showSnack(activity,etEmail,"Please enter all fields");
            etEmail.setFocusable(true);
            etEmail.requestFocus();
            return null;

        }else if (FieldValidators.isNullOrEmpty(etEmail)) {
            mTilEmail.setError("Please enter email address");
            etEmail.setFocusable(true);
            openKeyBoard(activity, etEmail);
            etEmail.requestFocus();

        } else if (!isValidEmail(etEmail.getText().toString().trim())) {
            mTilEmail.setError("Please enter valid email address");
            etEmail.setFocusable(true);
            openKeyBoard(activity, etEmail);
            etEmail.requestFocus();

        } else if (FieldValidators.isValidLength(etEmail,Constants.EMAIL)) {
            mTilEmail.setError("Email should have 2 to 50 characters");
            etEmail.setFocusable(true);
            openKeyBoard(activity, etEmail);
            etEmail.requestFocus();
        }
        else if (FieldValidators.isNullOrEmpty(etPassword)) {
            mTilPassword.setError("Please enter password");
            etPassword.setFocusable(true);
            openKeyBoard(activity, etPassword);
            etPassword.requestFocus();
        }

        else
        if (!validatePassword(activity, etPassword,mTilPassword,activity.getString(R.string.err_msg_password))) {
//            mTilPassword.setError("Please enter password");
            etPassword.setFocusable(true);
            openKeyBoard(activity, etPassword);
            etPassword.requestFocus();
        }

        else {
            return Constants.VALIDATED;
        }
        return null;

    }


    /**
     *
     * @param activity
     * @param etCurrentPassword
     * @param mTilCurrentPassword
     * @param etNewPassword
     * @param mTilNewPassword
     * @param etConfirmPassword
     * @param mTilConfirmPassword
     * @return
     */

    public static String isValidChangePassword(Activity activity,EditText etCurrentPassword,TextInputLayout mTilCurrentPassword, EditText etNewPassword,TextInputLayout mTilNewPassword, EditText etConfirmPassword,TextInputLayout mTilConfirmPassword) {

        if (FieldValidators.isNullOrEmpty(etCurrentPassword) && FieldValidators.isNullOrEmpty(etNewPassword)) {
//            Utility.showToastMessageLong(activity, "Please enter all fields");
            Utility.showSnack(activity,etCurrentPassword,"Please enter all fields");
            etCurrentPassword.setFocusable(true);
            etCurrentPassword.requestFocus();
            return null;

        }else if (FieldValidators.isNullOrEmpty(etCurrentPassword)) {
            mTilCurrentPassword.setError("Please enter current password");
            etCurrentPassword.setFocusable(true);
            openKeyBoard(activity, etCurrentPassword);
            etCurrentPassword.requestFocus();

        }

        else
        if (!validatePassword(activity, etCurrentPassword,mTilCurrentPassword,activity.getString(R.string.err_msg_password))) {
//            mTilPassword.setError("Please enter password");
            etCurrentPassword.setFocusable(true);
            openKeyBoard(activity, etCurrentPassword);
            etCurrentPassword.requestFocus();
        }

        else if (FieldValidators.isNullOrEmpty(etNewPassword)) {
            mTilNewPassword.setError("Please enter new password");
            etNewPassword.setFocusable(true);
            openKeyBoard(activity, etNewPassword);
            etNewPassword.requestFocus();
        }

        else
        if (!validatePassword(activity, etNewPassword,mTilNewPassword,activity.getString(R.string.err_msg_password))) {
//            mTilPassword.setError("Please enter password");
            etNewPassword.setFocusable(true);
            openKeyBoard(activity, etNewPassword);
            etNewPassword.requestFocus();
        }

        else if (FieldValidators.isNullOrEmpty(etConfirmPassword)) {
            mTilConfirmPassword.setError("Please enter confirm password");
            etConfirmPassword.setFocusable(true);
            openKeyBoard(activity, etConfirmPassword);
            etConfirmPassword.requestFocus();
        }

        else
        if (!validatePassword(activity, etConfirmPassword,mTilConfirmPassword,activity.getString(R.string.err_msg_password))) {
            etConfirmPassword.setFocusable(true);
            openKeyBoard(activity, etConfirmPassword);
            etConfirmPassword.requestFocus();
        }


        else {
            return Constants.VALIDATED;
        }
        return null;

    }

    /**
     *
     * @param activity          Activity context
     * @param mEtFirstName      First Name
     * @param mEtLastName       Last Name
     * @param mEtEmail          Email
     * @param mEtAge       Password
     * @param mEtPhoneNumber    Phone Number
     * @param mTilFirstName
     * @param mTilLastName
     * @param mTilEmail
     * @param mTilAge
     * @param mTilPhoneNumber
     * @return
     */

    public static String isValidEditProile(Activity activity, EditText mEtFirstName, TextInputLayout mTilFirstName, EditText mEtLastName, TextInputLayout mTilLastName, EditText mEtEmail, TextInputLayout mTilEmail, EditText mEtPhoneNumber, TextInputLayout mTilPhoneNumber, EditText mEtAge, TextInputLayout mTilAge,EditText mEtCountry, TextInputLayout mTilCountry, ScrollView mSvSignup) {

        if (FieldValidators.isNullOrEmpty(mEtFirstName)
                && FieldValidators.isNullOrEmpty(mEtLastName) && FieldValidators.isNullOrEmpty(mEtEmail) && FieldValidators.isNullOrEmpty(mEtAge) && FieldValidators.isNullOrEmpty(mEtPhoneNumber) ) {
            Utility.showSnack(activity,mEtFirstName,"Please enter all fields");
            focusOnView(mSvSignup,mEtFirstName);
            mEtFirstName.setFocusable(true);
            mEtFirstName.requestFocus();
            return null;

        } else if (FieldValidators.isNullOrEmpty(mEtFirstName)) {
            mTilFirstName.setError("Please enter first name");
            focusOnView(mSvSignup,mEtFirstName);
            mEtFirstName.setFocusable(true);
            openKeyBoard(activity, mEtFirstName);
            mEtFirstName.requestFocus();
        }
        else if (FieldValidators.isValidLength(mEtFirstName,Constants.FIRST_NAME)) {
            mTilFirstName.setError("First name should have 3 to 30 characters");
            focusOnView(mSvSignup,mEtFirstName);
            mEtFirstName.setFocusable(true);
            openKeyBoard(activity, mEtFirstName);
            mEtFirstName.requestFocus();
        }
        else if (FieldValidators.isNullOrEmpty(mEtLastName)) {
            mTilLastName.setError("Please enter last name");
            focusOnView(mSvSignup,mEtLastName);
            mEtLastName.setFocusable(true);
            openKeyBoard(activity, mEtLastName);
            mEtLastName.requestFocus();
        }
        else if (FieldValidators.isValidLength(mEtLastName,Constants.LAST_NAME)) {
            mTilLastName.setError("Last name should have 3 to 30 characters");
            focusOnView(mSvSignup,mEtLastName);
            mEtLastName.setFocusable(true);
            openKeyBoard(activity, mEtLastName);
            mEtLastName.requestFocus();
        }

        else if (FieldValidators.isNullOrEmpty(mEtEmail)) {
            mTilEmail.setError("Please enter email address");
            focusOnView(mSvSignup,mEtEmail);
            mEtEmail.setFocusable(true);
            openKeyBoard(activity, mEtEmail);
            mEtEmail.requestFocus();

        } else if (!isValidEmail(mEtEmail.getText().toString().trim())) {
            mTilEmail.setError("Please enter valid email address");
            focusOnView(mSvSignup,mEtEmail);
            mEtEmail.setFocusable(true);
            openKeyBoard(activity, mEtEmail);
            mEtEmail.requestFocus();

        }
        else if (FieldValidators.isValidLength(mEtEmail,Constants.EMAIL)) {
            mTilEmail.setError("Email should have 2 to 50 characters");
            focusOnView(mSvSignup,mEtEmail);
            mEtEmail.setFocusable(true);
            openKeyBoard(activity, mEtEmail);
            mEtEmail.requestFocus();
        }


        else if (FieldValidators.isNullOrEmpty(mEtAge)) {
            mTilAge.setError("Please enter your age");
//            focusOnView(mSvSignup,mEtPassword);
            mEtAge.setFocusable(true);
            openKeyBoard(activity, mEtAge);
            mEtAge.requestFocus();
        }
        else
        if (FieldValidators.isNullOrEmpty(mEtPhoneNumber)) {
            mTilPhoneNumber.setError("Please enter phone number");
//            focusOnView(mSvSignup,mEtPhoneNumber);
            mEtPhoneNumber.setFocusable(true);
            openKeyBoard(activity, mEtPhoneNumber);
            mEtPhoneNumber.requestFocus();
        }
        else if (FieldValidators.isValidLength(mEtPhoneNumber,Constants.PHONE_NUMBER)) {
            mTilPhoneNumber.setError("Phone number should have 8 to 15 digits");
            focusOnView(mSvSignup,mEtPhoneNumber);
            mEtPhoneNumber.setFocusable(true);
            openKeyBoard(activity, mEtPhoneNumber);
            mEtPhoneNumber.requestFocus();
        }


        else {
            return Constants.VALIDATED;
        }
        return null;

    }




    /**
     * Open keyboard for specific edittext
     *
     * @param editText
     */
    private static void openKeyBoard(Activity activity, EditText editText) {
        // Open keyboard
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }


    /**
     * Validation for SignIn
     *
     * @param activity   reference of calling class
     * @param etEmail    Edittext of email input
     * @param etname     Edittext of name input
     * @return if the validation is success or fail
     */
    public static String isValidContactUs(Activity activity,EditText etEmail,TextInputLayout mTilEmail, EditText etname,TextInputLayout mTilName, EditText mEtAddDescription, ScrollView mSvSignup) {

        if (FieldValidators.isNullOrEmpty(etEmail) && FieldValidators.isNullOrEmpty(etname) && FieldValidators.isNullOrEmpty(mEtAddDescription)) {
//            Utility.showToastMessageLong(activity, "Please enter all fields");
            Utility.showSnack(activity,etEmail,"Please enter all fields");
            etEmail.setFocusable(true);
            etEmail.requestFocus();
            return null;

        }else if (FieldValidators.isNullOrEmpty(etEmail)) {
            mTilEmail.setError("Please enter email address");
            focusOnView(mSvSignup,etEmail);
            etEmail.setFocusable(true);
            openKeyBoard(activity, etEmail);
            etEmail.requestFocus();

        } else if (!isValidEmail(etEmail.getText().toString().trim())) {
            mTilEmail.setError("Please enter valid email address");
            focusOnView(mSvSignup,etEmail);
            etEmail.setFocusable(true);
            openKeyBoard(activity, etEmail);
            etEmail.requestFocus();

        } else if (FieldValidators.isValidLength(etEmail,Constants.EMAIL)) {
            mTilEmail.setError("Email should have 2 to 50 characters");
            focusOnView(mSvSignup,etEmail);
            etEmail.setFocusable(true);
            openKeyBoard(activity, etEmail);
            etEmail.requestFocus();
        }
        else if (FieldValidators.isNullOrEmpty(etname)) {
            mTilName.setError("Please enter name");
            focusOnView(mSvSignup,etname);
            etname.setFocusable(true);
            openKeyBoard(activity, etname);
            etname.requestFocus();
        }

        else if (FieldValidators.isValidLength(etname,Constants.LAST_NAME)) {
            mTilName.setError("Name should have 3 to 30 characters");
            etname.setFocusable(true);
            openKeyBoard(activity, etname);
            etname.requestFocus();
        }
        else if (FieldValidators.isNullOrEmpty(mEtAddDescription)) {
            Utility.showSnack(activity,etEmail,"Please enter description");
            focusOnView(mSvSignup,mEtAddDescription);
            mEtAddDescription.setFocusable(true);
//            openKeyBoard(activity, mEtAddDescription);
            mEtAddDescription.requestFocus();
        }


        else {
            return Constants.VALIDATED;
        }
        return null;

    }


}
