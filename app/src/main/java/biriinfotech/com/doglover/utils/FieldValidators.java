package biriinfotech.com.doglover.utils;

import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class FieldValidators {
	/**
	 * @param editText
	 *            - EditText field which need to be validated
	 * @return - Returns true if editText is Null or empty
	 *
			 */
	public static boolean isNullOrEmpty(EditText editText) {
		return editText.getText() == null
				|| editText.getText().toString().trim().length() == 0;
	}

	public static boolean isValidLength(EditText editText, String type) {

		if (type.equals(Constants.FIRST_NAME))
		{
			return editText.getText().toString().trim().length()<3
					|| editText.getText().toString().trim().length() > 30;
		}
		else if (type.equals(Constants.LAST_NAME))
		{
			return editText.getText().toString().trim().length()<3
					|| editText.getText().toString().trim().length() > 30;
		}
		else if (type.equals(Constants.EMAIL))
		{
			return editText.getText().toString().trim().length()<2
					|| editText.getText().toString().trim().length() > 50;
		}
		else if (type.equals(Constants.PASSWORD))
		{
			return editText.getText().toString().trim().length()<6
					|| editText.getText().toString().trim().length() > 20;
		}
		else if (type.equals(Constants.PHONE_NUMBER))
		{
			return editText.getText().toString().trim().length()<8
					|| editText.getText().toString().trim().length() > 15;
		}
		else
		if (type.equals(Constants.ACTIVITY_NAME))
		{
			return editText.getText().toString().trim().length()<3
					|| editText.getText().toString().trim().length() > 15;
		}
		else {}

		return editText.getText().toString().trim().length()<6
				|| editText.getText().toString().trim().length() > 20;
	}

	/**
	 * @param autoCompleteTextView
	 *            - AutoCompleteTextView field which need to be validated
	 * @return Returns true if AutoCompleteTextView is Null or empty
	 */
	public static boolean isNullOrEmpty(
			AutoCompleteTextView autoCompleteTextView) {
		return autoCompleteTextView.getText() == null
				|| autoCompleteTextView.getText().toString().length() == 0;
	}

}
