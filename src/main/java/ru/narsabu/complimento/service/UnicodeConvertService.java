package ru.narsabu.complimento.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UnicodeConvertService {

    private static final String UNICODE_PATTERN_EXP = "\\\\u[0-9a-zA-Z]{4}";

    public String unicodeToString(String unicode) {

        if (!isContainsUnicodeChar(unicode)) {
            return unicode;
        }

        // Create a capture match expression
        Pattern compile = Pattern.compile("(" + UNICODE_PATTERN_EXP + ")");
        Matcher matcher = compile.matcher(unicode);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(sb, String.valueOf(unicodeToChar(matcher.group())));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private boolean isContainsUnicodeChar(String str) {
        if (null == str || str.isEmpty()) {
            return false;
        } else {
            Pattern pattern = Pattern.compile(UNICODE_PATTERN_EXP);
            return pattern.matcher(str).find();
        }
    }

    private boolean isUnicodeChar(String str) {
        if (isContainsUnicodeChar(str)) {
            return str.length() == 6;
        }
        return false;
    }

    private char unicodeToChar(String unicodeChar) {
        if (!isUnicodeChar(unicodeChar)) return '?';

        return (char) Integer.parseInt(unicodeChar.substring(2), 16);
    }
}
