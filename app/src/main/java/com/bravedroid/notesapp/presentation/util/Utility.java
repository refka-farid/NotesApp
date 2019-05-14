package com.bravedroid.notesapp.presentation.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
    public static String getCurrentTimestamp() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");//must be lowercase for api 23-
            String currentDateTime = dateFormat.format(new Date());

            return currentDateTime;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMonthFromNumber(String monthNumber) {
        switch (monthNumber) {
            case "01": {
                return "Jan";
            }
            case "02": {
                return "Jan";
            }
            case "03": {
                return "Jan";
            }
            case "04": {
                return "Jan";
            }
            default: {
                return "Error";
            }
        }
    }
}
