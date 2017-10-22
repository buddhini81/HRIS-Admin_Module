/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.util;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.*;

/**
 *
 * @author Buddhini
 */
public class Util {

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String) {
            return ((String) obj).trim().length() <= 0;
        } else if (obj instanceof Date) {
            return ((Date) obj).getTime() <= 0;
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) <= 0;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).size() <= 0;
        } else if (obj instanceof Iterator) {
            return !((Iterator) obj).hasNext();
        } else if (obj instanceof Map) {
            return ((Map) obj).entrySet().size() <= 0;
        } else if (obj instanceof Enumeration) {
            return !((Enumeration) obj).hasMoreElements();
        }
        return false; // Object not null!
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static Date parseStringToDate(String stringToParse) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            sdf.setLenient(false);
            return sdf.parse(stringToParse);
        } catch (ParseException pe) {
            return null;
        }
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return sdf.format(date);
        } catch(Exception e) {
            return null;
        }
    }

    public static Date floor(Date d)
    {
        DateHolder dh = new DateHolder(d);

        Calendar calendar = Calendar.getInstance();
        calendar.set(dh.getYear(),
                     dh.getMonth(),
                     dh.getDay(),
                     0,
                     0,
                     0);
        return calendar.getTime();
    }

    public static Date roof(Date d)
    {
        DateHolder dh = new DateHolder(d);

        Calendar calendar = Calendar.getInstance();
        calendar.set(dh.getYear(),
                     dh.getMonth(),
                     dh.getDay(),
                     23,
                     59,
                     59);
        return calendar.getTime();
    }

        private static class DateHolder
    {
        protected Date date = null;
        protected int year;
        protected int month;
        protected int day;

        public DateHolder(Date date)
        {
            this.date = new Date(date.getTime());
            initialize();
        }

        private void initialize()
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DATE);
        }

        public int getYear()
        {
            return year;
        }

        public int getMonth()
        {
            return month;
        }

        public int getDay()
        {
            return day;
        }
    }

    public static boolean isValidDate(Date date, String preferedFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(preferedFormat);
        sdf.setLenient(false);
        try {
              Date d = sdf.parse(sdf.format(date));
              return d != null ? true : false;
        } catch(ParseException pe) {
            return false;
        }
        
    }

    public static boolean isValidNIC(String value) {

        if(value.length() < 10 || value.length() > 10) {
            return false;
        } else {
        	char chZero = value.charAt(0);
            char chNine = value.charAt(9);
            
            String pat = "^[0-9]+$";
        	Pattern p = Pattern.compile(pat);
        	Matcher m = p.matcher(value.substring(0,9));
        	
        	if(m.matches() == true) {
        		if((String.valueOf(chNine).equals("V")) && (Integer.valueOf(String.valueOf(chZero)) > 0)) {
        			return true;
        		} else {
        			return false;
        		}
        	} else {
        		return false;
        	}
     
        }
    }

    public static boolean isNumeric(String value) {

        try {
            Long l = Long.valueOf(value);
            return (l != null) ? true : false;
        } catch (NumberFormatException ne) {
            return false;
        }
    }

    public static boolean isValidEmail(String value) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern p = Pattern.compile(EMAIL_PATTERN);

        Matcher m = p.matcher(value);

        boolean isValid = m.matches();

        return isValid;
    }
    
    
    public static String wrapInWildcards(Object value) {
        return "%" + value + "%";
    }
}
