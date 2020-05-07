package com.firsttask.dog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.Patterns;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidationRules {
    private static final int AGE = 18;
    private static final int SSN_LENGTH = 9;
    private static final int PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 32;
    private static final int CASE_NUMBER_LENGTH = 9;

    private ValidationRules() {}

    public static boolean isEmailValid(@Nullable CharSequence email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPasswordValid(@Nullable CharSequence password) {
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        String passwordReg = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=\\S+$).{8,}$";
        return isRegularMatches(passwordReg, passwordReg);
    }

    public static boolean isNewPasswordEqualsOld(String oldPassword, String newPassword) {
        return newPassword.equals(oldPassword);
    }

    public static boolean isNotEmptyValid(@Nullable CharSequence password) {
        return !TextUtils.isEmpty(password);
    }

    public static boolean isLengthPasswordValid(@NonNull CharSequence password) {
        return password.length() >= PASSWORD_LENGTH;
    }

    public static boolean isMaxLengthPasswordValid(@NonNull CharSequence password) {
        return password.length() <= MAX_PASSWORD_LENGTH;
    }

    public static boolean isContentPasswordValid(@NonNull CharSequence password) {
        String passwordReg = "(?=^.{8,}$)(?=.*\\d)(?=.*\\W*)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
        return isRegularMatches(passwordReg, password.toString());
    }

    public static boolean isConfirmPasswordValid(@NonNull String newPassword,
                                                 @NonNull String confirmPassword) {
        return confirmPassword.equals(newPassword);
    }

    public static boolean isFieldNotEmpty(@Nullable CharSequence value) {
        return !(value == null || TextUtils.isEmpty(value.toString().trim()));
    }

    public static boolean isRegularMatches(String regular, String value) {
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean isNameValid(@Nullable CharSequence name) {
        if (TextUtils.isEmpty(name)) {

            return false;
        }
        String nameReg = "^(?=.{1,256}$)[a-zA-Z]+(?:[-'\\s][a-zA-Z]+)*$";
        return isRegularMatches(nameReg, (String) name);
    }

    public static boolean isFieldHasDigitsOnly(@Nullable CharSequence value) {
        return !TextUtils.isEmpty(value) && TextUtils.isDigitsOnly(value);
    }

    public static boolean isSSNValid(@Nullable CharSequence ssn) {
        return isFieldHasDigitsOnly(ssn) && ssn.length() == SSN_LENGTH;
    }

    public static boolean isCaseNumberValid(@Nullable CharSequence caseNumber) {
        return isFieldHasDigitsOnly(caseNumber) && caseNumber.length() >= CASE_NUMBER_LENGTH;
    }

    public static boolean isBirthDateValid(long dateTime) {
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTimeInMillis(dateTime);
        if (Calendar.getInstance().before(birthDate)) {
            return false;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -AGE);

        return birthDate.before(calendar);
    }

    public static boolean isBirthDateValid(@Nullable Date date) {
        return date != null && isBirthDateValid(date.getTime());
    }

    public static boolean isDateInFuture(long date) {
        return date > new Date().getTime();
    }

    public static boolean isDateRangeCorrect(long from, long to) {
        return from <= to;
    }
}