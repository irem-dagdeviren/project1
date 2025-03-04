package org.project.shared;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {

    public static String getMessageForLocale(String message, Locale locale) {
        return ResourceBundle.getBundle("messages", locale).getString(message);
    }
}
