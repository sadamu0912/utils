
package com.xjx.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private static SimpleDateFormat dateFormat     = new SimpleDateFormat("dd-MM-yyyy");
    public static final String[]    DATE_FORMATS   = new String[] { "yyyyMMdd", "yyyy", "yyyy-MM", "yyyy-MM-dd",
            "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm", "yyyyMMddHH:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-ddHHmmss",
            "yyyy-MM-dd HHmmss", "yyyy-MM-dd HH:mm:ss.S", "yyyyMMddHH:mm" };

    public static String getToday() {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        return fmt.format(today);
    }

    public static String getDateDMY(Date date) {
        return (date == null) ? "" : dateFormat.format(date);
    }

    public static String getDateTimeDMYHms(Date date) {
        return (date == null) ? "" : dateTimeFormat.format(date);
    }

   
    public static String formatDate(Date dt) {
        if (dt == null) return null;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        return fmt.format(dt);
    }

    public static String formatDate(String format, Date dt) {
        if (dt == null) return null;
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        return fmt.format(dt);
    }

    public static String formatDateYMDHMS(Date dt) {
        if (dt == null) return "";
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(dt);
    }

    public static String formatDateYMDHM(Date dt) {
        SimpleDateFormat fmt = new SimpleDateFormat("yy��M��d�� HH:mm");
        return fmt.format(dt);
    }

    public static String formatDateYMDHMe(Date dt) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return fmt.format(dt);
    }

    

    @SuppressWarnings("static-access")
    public static String getPeriodDate(int intervalDay) {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.DAY_OF_MONTH, 1 - intervalDay);
        Date date = cal.getTime();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        return fmt.format(date);
    }

   
    public static Date getAddDayDate(Date dt, int days) {
        if (dt == null) dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + days);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

   
    public static Date getAddHourDate(Date dt, int hours) {
        if (dt == null) dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.HOUR, hours);

        return cal.getTime();
    }
    
   
    public static Date getAddSecondDate(Date dt, int seconds) {
        if (dt == null) dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.SECOND, seconds);

        return cal.getTime();
    }

   
    public static Date getDayBegin(Date date) {
        if (date == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    
    public static Date getDayEnd(Date date) {
        if (date == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

   
    public static Date getDaySomeTime(Date date, int hour, int min, int second, int mill) {
        if (date == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, mill);
        return cal.getTime();
    }

   
    public static java.sql.Date toSqlDate(Date dt) {
        if (dt == null) dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    
    public static Date getMonthBeginTime(Date dt) {
        if (dt == null) dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getMonthEndTime(Date dt) {
        if (dt == null) dt = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, -1);
        return cal.getTime();
    }

    public static Timestamp dateToTimestamp(Date dt) {
        if (dt == null) return new Timestamp(System.currentTimeMillis());
        else return new Timestamp(dt.getTime());
    }

   
    public static int[] getTimeArray(Date dt) {
        if (dt == null) dt = new Date(System.currentTimeMillis());
        int[] timeArray = new int[3];
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        timeArray[0] = cal.get(Calendar.YEAR);
        timeArray[1] = cal.get(Calendar.MONTH) + 1;
        timeArray[2] = cal.get(Calendar.DAY_OF_MONTH);
        return timeArray;
    }

    public static int[] timeArray(Date dt) {
        if (dt == null) dt = new Date(System.currentTimeMillis());
        int[] timeArray = new int[5];
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        timeArray[0] = cal.get(Calendar.YEAR);
        timeArray[1] = cal.get(Calendar.MONTH) + 1;
        timeArray[2] = cal.get(Calendar.DAY_OF_MONTH);
        timeArray[3] = cal.get(Calendar.HOUR_OF_DAY);
        timeArray[4] = cal.get(Calendar.MINUTE);
        return timeArray;
    }

   
    public static Date getTime(Integer year, Integer month, Integer day) {
        Calendar cal = Calendar.getInstance();
        if (year != null) cal.set(Calendar.YEAR, year);
        if (month != null) cal.set(Calendar.MONTH, month - 1);
        if (day != null) cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    

   
    public static String getStringFromPattern(String parrern, Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat(parrern);
        try {
            return fmt.format(date);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

   
    public static int getDayBetween(java.util.Date d1, java.util.Date d2) {
        // return (int)(d1.getTime()-d2.getTime())/(1000*60*60*24);
        Date[] d = new Date[2];
        d[0] = d1;
        d[1] = d2;
        Calendar[] cal = new Calendar[2];
        for (int i = 0; i < cal.length; i++) {
            cal[i] = Calendar.getInstance();
            cal[i].setTime(d[i]);
            // cal[i].set(Calendar.HOUR_OF_DAY,0);
            cal[i].set(Calendar.MINUTE, 0);
            cal[i].set(Calendar.SECOND, 0);
        }
        long m = cal[0].getTime().getTime();
        long n = cal[1].getTime().getTime();
        return (int) ((m - n) / 3600000);
    }

   
    public static int getDateBetween(Date t1, Date t2) {
        Date t3;
       
        if (t1.getTime() > t2.getTime()) {
            t3 = t2;
            t2 = t1;
            t1 = t3;
        }
        return (int) ((t2.getTime() - t1.getTime()) / (1000 * 60 * 60 * 24));
    }

   
    public static int getDateBetweenByDate(Date t1, Date t2) {
        Date t3;
       
        if (t1.getTime() > t2.getTime()) {
            t3 = t2;
            t2 = t1;
            t1 = t3;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(t1);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        t1 = c1.getTime();

        Calendar c2 = Calendar.getInstance();
        c2.setTime(t2);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        t2 = c2.getTime();

        return (int) ((t2.getTime() - t1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static double getTimesBetween(java.util.Date d1, java.util.Date d2) {
        Date[] d = new Date[2];
        d[0] = d1;
        d[1] = d2;
        Calendar[] cal = new Calendar[2];
        for (int i = 0; i < cal.length; i++) {
            cal[i] = Calendar.getInstance();
            cal[i].setTime(d[i]);
            cal[i].set(Calendar.HOUR_OF_DAY, 0);
            cal[i].set(Calendar.MINUTE, 0);
            cal[i].set(Calendar.SECOND, 0);
            cal[i].set(Calendar.MILLISECOND, 0);
        }
        long m = cal[0].getTime().getTime();
        long n = cal[1].getTime().getTime();
        return (double) ((m - n));
    }

   
   

    

   
    public static int getDaysBetween(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        int days = (int) ((c2.getTimeInMillis() - c1.getTimeInMillis()) / 86400000);
        if (days == 0) return 1;
        else return days;
    }

    public static Date getNowWithoutMS() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getNowWithoutSecond() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getTimeWithoutMS(Date inputTime) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(inputTime);
        c1.set(Calendar.MILLISECOND, 0);
        return c1.getTime();
    }

    public static Date getTimeWithoutSecond(Date inputTime) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(inputTime);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        return c1.getTime();
    }

    public static boolean isTimeEquals(Date d1, Date d2) {
        if (d1 == null || d2 == null) return false;
        return Math.abs(d1.getTime() - d2.getTime()) < 50;
    }

   
  


  

    public static Date getDefaultTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1900);
        c.set(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getMinMaxTime(Date inputDate) {
        if (inputDate == null) return null;
        Calendar c = Calendar.getInstance();
        c.setTime(inputDate);
        if (c.get(Calendar.YEAR) < 1900) {
            return getDefaultTime();
        }
        if (c.get(Calendar.YEAR) > 2078) {
            Calendar cl = Calendar.getInstance();
            cl.set(Calendar.YEAR, 2078);
            cl.set(Calendar.MONTH, 1);
            cl.set(Calendar.DAY_OF_MONTH, 1);
            cl.set(Calendar.MINUTE, 0);
            cl.set(Calendar.SECOND, 0);
            cl.set(Calendar.MILLISECOND, 0);
            return cl.getTime();
        }
        return inputDate;
    }
    
   
    public static boolean isToday(Date inTime,Date compareTime){
    	if(DateUtil.getDayBegin(compareTime).getTime()<=inTime.getTime()&&
    			inTime.getTime()<=DateUtil.getDayEnd(compareTime).getTime()){
    		return true;
    	}
    	return false;
    }

   
    public static Date getMaxTime() {
        Calendar cl = Calendar.getInstance();
        cl.set(Calendar.YEAR, 2078);
        cl.set(Calendar.MONTH, 1);
        cl.set(Calendar.DAY_OF_MONTH, 1);
        cl.set(Calendar.MINUTE, 0);
        cl.set(Calendar.SECOND, 0);
        cl.set(Calendar.MILLISECOND, 0);
        return cl.getTime();
    }

    public static void main(String[] args) {
       
    }
}
