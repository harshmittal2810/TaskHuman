package com.harsh.taskhuman.utils;

import android.text.format.DateUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;

import com.harsh.taskhuman.MainApplication;
import com.harsh.taskhuman.R;
import com.harsh.taskhuman.common.sharepref.PrefHelper;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String getDate(String serverFormatDate, String format) {
        String formattedTime = "";

        if (serverFormatDate != null && !serverFormatDate.equalsIgnoreCase("") && format != null && !format.equalsIgnoreCase("")) {
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat formatter2 = new SimpleDateFormat(format);
            formatter1.setTimeZone(TimeZone.getTimeZone(String.valueOf(TimeZone.getDefault())));
            try {

                Date date = formatter1.parse(serverFormatDate);
                formattedTime = formatter2.format(date);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return formattedTime;
    }

    public static long getMillis(String serverFormatDate) {
        long millis = 0;
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter1.setTimeZone(TimeZone.getTimeZone(String.valueOf(TimeZone.getDefault())));

        try {
            Date date = formatter1.parse(serverFormatDate);
            millis = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();

        }

        return millis;

    }

    public static String getFormattedString(String str) {
        return String.format(str, PrefHelper.Companion.getInstance(MainApplication.instance()).getPointsName());
    }


    public static long getMillisInBetweenCurrentDate(String inputFormatDate, String inputFormat) {
        long millis = 0;

        //SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat formatter1 = new SimpleDateFormat(inputFormat);
        if (inputFormat.equals("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")) {
            formatter1.setTimeZone(TimeZone.getTimeZone(String.valueOf(TimeZone.getDefault())));
        }
        //formatter1.setTimeZone(TimeZone.getTimeZone(String.valueOf(TimeZone.getDefault())));
        Date final_date = new Date();
        try {
            Date initial_date = formatter1.parse(inputFormatDate);
            Calendar initialDateInCalendar = new GregorianCalendar();
            Calendar finalDateInCalendar = new GregorianCalendar();
            initialDateInCalendar.setTime(initial_date);
            finalDateInCalendar.setTime(final_date);
            long initialDateInMillis = initialDateInCalendar.getTimeInMillis();
            long finalDateInMillis = finalDateInCalendar.getTimeInMillis();
            millis = finalDateInMillis - initialDateInMillis;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return millis;
    }

    public static int getMonthsPassed(String inputFormatDate, String inputFormat) {
        int months;
        int initial_day = 0;
        int initial_month = 0;
        int initial_year = 0;
        int final_day = 0;
        int final_month = 0;
        int final_year = 0;

        //SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat formatter1 = new SimpleDateFormat(inputFormat);
        if (inputFormat.equals("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")) {
            formatter1.setTimeZone(TimeZone.getTimeZone(String.valueOf(TimeZone.getDefault())));
        }

        if (inputFormatDate.equalsIgnoreCase("0000-00-00")) {
            Log.e("inputDate of the form", "0000-00-00");
            return 0;
        }

        //formatter1.setTimeZone(TimeZone.getTimeZone(String.valueOf(TimeZone.getDefault())));
        Date final_date = new Date();       // Current Date
        try {
            Date initial_date = formatter1.parse(inputFormatDate);

            Calendar cal_initial = new GregorianCalendar();
            Calendar cal_final = new GregorianCalendar();
            cal_initial.setTime(initial_date);
            cal_final.setTime(final_date);
            initial_day = cal_initial.get(Calendar.DAY_OF_MONTH);
            initial_month = cal_initial.get(Calendar.MONTH) + 1;     // As in date function, January is "0" and etc.
            initial_year = cal_initial.get(Calendar.YEAR);
            final_day = cal_final.get(Calendar.DAY_OF_MONTH);
            final_month = cal_final.get(Calendar.MONTH) + 1;         // As in date function, January is "0" and etc.
            final_year = cal_final.get(Calendar.YEAR);
            Log.e("TUMBO 1", "" + initial_day);
            Log.e("TUMBO 2", "" + initial_month);
            Log.e("TUMBO 3", "" + initial_year);
            Log.e("TUMBO 4", "" + final_day);
            Log.e("TUMBO 5", "" + final_month);
            Log.e("TUMBO 6", "" + final_year);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        months = (final_year - initial_year) * 12 + (final_month - initial_month) + (int) Math.floor(((float) final_day - (float) initial_day) / 31);
        Log.e("MONTHS value", "" + (int) Math.floor(((float) final_day - (float) initial_day) / 31));
        Log.e("TUMBO 7", "" + months);

        return months;
    }

    public static int getDaysPassed(String inputFormatDate, String inputFormat) {
        //LocalDateTime date1 = LocalDateTime.parse("2018-04-13T20:00:00.0400");
        if (inputFormatDate.equalsIgnoreCase("0000-00-00")) {
            Log.e("inputDate of the form", "0000-00-00");
            return 0;
        }

        long millis = getMillisInBetweenCurrentDate(inputFormatDate, inputFormat);
        Log.e("Input Date ", inputFormatDate);
        long days = millis / (1000 * 60 * 60 * 24);

        Log.e("Millis", "" + millis);
        Log.e("DAYS PASSED", "" + days);

        return (int) days;

    }

    public static String getDateFromMillis(long millis, String outputFormat) {
        SimpleDateFormat formatter1 = new SimpleDateFormat(outputFormat, Locale.getDefault());
        Date result = new Date(millis);
        return formatter1.format(result);
    }

    public static String getTimeElapsedFromMillis(long timeInMillis) {
        long difference = System.currentTimeMillis() - timeInMillis;
        if (difference < DateUtils.MINUTE_IN_MILLIS) {
            return MainApplication.instance().getString(R.string.just_now);
        } else if (difference < DateUtils.HOUR_IN_MILLIS) {
            return difference / DateUtils.MINUTE_IN_MILLIS + (difference / DateUtils.MINUTE_IN_MILLIS == 1 ? " " + MainApplication.instance().getString(R.string.short_min) : " " + MainApplication.instance().getString(R.string.mins));
        } else if (difference < DateUtils.DAY_IN_MILLIS) {
            return difference / DateUtils.HOUR_IN_MILLIS + (difference / DateUtils.HOUR_IN_MILLIS == 1 ? " " + MainApplication.instance().getString(R.string.hr) : " " + MainApplication.instance().getString(R.string.hrs));
        } else if (difference < DateUtils.YEAR_IN_MILLIS) {
            return Utils.getDateFromMillis(timeInMillis, "MMM dd");
        } else {
            return Utils.getDateFromMillis(timeInMillis, "MMM dd, yyyy");
        }
    }

    public static String getDateFromAnyFormat(String inputFormatDate, String inputFormat, String outputFormat) {
        String formattedTime = "";

        if (inputFormatDate == null || inputFormatDate.equals("") || inputFormatDate.equalsIgnoreCase("0000-00-00"))
            return "";

        if (!inputFormatDate.equalsIgnoreCase("") && outputFormat != null && !outputFormat.equalsIgnoreCase("")) {
            SimpleDateFormat formatter1 = new SimpleDateFormat(inputFormat);
            SimpleDateFormat formatter2 = new SimpleDateFormat(outputFormat);
            formatter1.setTimeZone(TimeZone.getTimeZone(String.valueOf(TimeZone.getDefault())));
            try {

                Date date = formatter1.parse(inputFormatDate);

                formattedTime = formatter2.format(date);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return formattedTime;
    }

    public static String addHttpsPrefixUrl(String s) {
        if (s == null || s.isEmpty()) return s;
        if (s.length() >= 8 && (s.startsWith("https://") || s.startsWith("http://"))) {
            return s;
        } else {
            return "https://" + s;
        }
    }

    public static String decodeBase64(String userId) {
        String user_id = "";

        if (userId != null && !userId.equalsIgnoreCase("")) {
            byte[] id = Base64.decode(userId, Base64.DEFAULT);
            user_id = new String(id, StandardCharsets.UTF_8);

        }
        return user_id;

    }

    public static String encodeBase64(String userId) {
        String user_id = "";
        if (userId != null && !userId.equalsIgnoreCase("")) {
            byte[] id = Base64.encode(userId.getBytes(), Base64.DEFAULT);
            user_id = new String(id, StandardCharsets.UTF_8);
        }
        return user_id.replaceAll("\n", "");
    }

    public static String getOrdinalNumber(int i) {
        String[] sufixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        if (i <= 10) {
            switch (i) {
                case 1:
                    return "FIRST";
                case 2:
                    return "SECOND";
                case 3:
                    return "THIRD";
                case 4:
                    return "FORTH";
                case 5:
                    return "FIFTH";
                case 6:
                    return "SIXTH";
                case 7:
                    return "SEVENTH";
                case 8:
                    return "EIGHTH";
                case 9:
                    return "NINTH";
                case 10:
                    return "TENTH";
                default:
                    break;
            }
        }

        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + sufixes[i % 10];
        }


    }

    public static String isLinkPresent(String link) {
        String l = "";
        Pattern p = Pattern.compile("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)" + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*" + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

        Matcher m = p.matcher(link);//replace with string to compare
        int counter = 0;

        while (m.find()) {
            counter++;
            int matchStart = m.start(1);
            int matchEnd = m.end();
            l = link.substring(matchStart, matchEnd);
        }

        return counter == 1 ? l : "";
    }

    public static int pxToDp(int px) {
        DisplayMetrics displayMetrics = MainApplication.instance().getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = MainApplication.instance().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static String getNumberWithoutDecimal(float number) {

        if (Math.floor(number) == Math.ceil(number)) {
            return String.valueOf((int) number);
        } else {
            return String.valueOf(number);
        }
    }
}
