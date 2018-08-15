package com.luoromeo.tools.commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public interface FormatConstants extends Constants {
    String DATE_FORMAT_TEMPLATE = "yyyy-MM-dd";
    String TIME_FORMAT_TEMPLATE = "HH:mm:ss";
    String DATE_TIME_FORMAT_TEMPLATE = "yyyy-MM-dd HH:mm:ss";
    String DATE_TIME_FORMAT_IMAGE_TEMPLATE = "yyyyMMddHHmmss";
    String[] DEFAULT_INPUT_FORMATS = new String[]{"dd/mm/yyyy hh:mm:ss", "dd-mm-yyyy hh:mm:ss", "dd/mm/yy hh:mm:ss", "dd-mm-yy hh:mm:ss", "dd/mm/yyyy", "dd-mm-yyyy", "dd/mm/yy", "dd-mm-yy"};
    String[] DATE_FORMATS = new String[]{"yyyy-MM-dd", "HH:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"};
    DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
    DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    DateFormat DATE_TIME_FORMAT_IMAGE = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
}
