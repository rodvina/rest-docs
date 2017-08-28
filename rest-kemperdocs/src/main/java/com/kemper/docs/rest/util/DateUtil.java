package com.kemper.docs.rest.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * <p>
 * Date utility class. Contains utility methods related to date processing.
 * </p>
 * <p>
 * <table cellspacing="1" cellpadding="1" width="75%" border="1">
 * <caption style="text-align:left;font-weight:bold;">Change Description </caption>
 * <tr class="TableHeadingColor">
 * <td style="font-weight:bold;">Date</td>
 * <td style="font-weight:bold;">User</td>
 * <td style="font-weight:bold;">Changes Made</td>
 * </tr>
 * <tr>
 * <td>08/07/2014</td>
 * <td>Warren smith (kahwgs)</td>
 * <td>Exceed Billing - KBW - Initial Release. </td>
 * </tr>
 * <tr>
 * <td>02/24/2015</td>
 * <td>Warren smith (kahwgs)</td>
 * <td>Exceed Billing - KBW - QC14600. Added the following methods to support retrieval and setting
 * the correct bill plan code when changing method of billing:<br/><code>getDifferenceInMonthsBetweenDates(Date, Date)</code>,
 * <code>getUtilDateFromString(String, String)</code>
 * </td>
 * </tr>
 * </table>
 * </p>
 * 
 * @author Warren Smith
 * @version $Revision: 1.0 $
 */
public final class DateUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
	
	public static final String MM_DD_YYYY_TIME_PATTERN = "MM/dd/yyyy HH:mm:ss a z";
	public static final String ZULU_PATTERN = "yyyy-MM-dd'T'HHmmss'Z'";
	
	public static final String DT_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DT_FORMAT_MM_DD_YYYY_SLASH = "MM/dd/yyyy";

	
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
		if (date == null) {
			return null;
		}
		XMLGregorianCalendar calendar = null;
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(date);
		try {
			calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
		} catch (Exception e) {
			LOGGER.error("Error=FailedToConvertToXmlGregorian;date="+date);
		}
		
		return calendar;
	}
	
	public static XMLGregorianCalendar toXMLGregorianCalendarEndOfDay(Date date) {

		XMLGregorianCalendar calendar = toXMLGregorianCalendar(date);
		if (null != calendar) {
			calendar.setTime(23, 59, 59);
		}
		return calendar;
	}
	
	/**
	 * Convertes java.time.LocalDate to XMLGregorianCalendar
	 * @param localDate
	 * @return
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		XMLGregorianCalendar calendar = null;
		GregorianCalendar gCalendar = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		try {
			calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
		} catch (Exception e) {
			LOGGER.error("Error=FailedToConvertToXmlGregorian;localdate="+localDate);
		}
		
		return calendar;
	}
	
	/**
	 * Convertes java.time.LocalDateTime to XMLGregorianCalendar
	 * @param localDate
	 * @return
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		XMLGregorianCalendar calendar = null;
		GregorianCalendar gCalendar = GregorianCalendar.from(localDateTime.atZone(ZoneId.systemDefault()));
		try {
			calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
		} catch (Exception e) {
			LOGGER.error("Error=FailedToConvertToXmlGregorian;localDateTime="+localDateTime);
		}
		
		return calendar;
	}
	
    /**
     * Converts XMLGregorianCalendar to java.util.Date in Java
     */
    public static Date toDate(XMLGregorianCalendar calendar){
        if(calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }
    
    public static ZonedDateTime toZonedDateTime(LocalDateTime localDateTime) {
    	if (localDateTime == null) {
    		return null;
    	}
    	return ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
    }
    
    /**
     * Converts java.time.LocalDateTime to OffsetDateTime based on system default zoneId
     * 
     * ex: 2016-11-30T09:59:32.275-05:00
     * @param localDateTime
     * @return
     */
    public static OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime) {
    	if (localDateTime == null) {
    		return null;
    	}
    	
    	return localDateTime.atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }
    
    /**
     * Converts XMLGregorianCalendar to java.time.LocalDate in Java
     * @param calendar
     * @return
     */
    public static LocalDate toLocalDate(XMLGregorianCalendar calendar) {
        if(calendar == null) {
            return null;
        }
    	return LocalDateTime.ofInstant(toDate(calendar).toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Converts <code>Date</code> to <code>LocalDate</code>
     * @param date
     * @return
     */
    public static LocalDate toLocalDate(Date date) {
        if(date == null) {
            return null;
        }
    	return toLocalDateTime(date).toLocalDate();
    }
    
    /**
     * Converts <code>Date</code> to <code>LocalDateTime</code>
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if(date == null) {
            return null;
        }
    	return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
    
    /**
     * Converts <code>LocalDate</code> to <code>Date</code>
     * @param localDate
     * @return
     */
    public static Date toDate(LocalDate localDate){
        if(localDate == null) {
            return null;
        }
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    }

	/**
	 * Method getDifferenceInMonthsBetweenDates
	 * 
	 * @param startDate
	 * @param endDate
	 * @return int number of months difference between dates
	 */
	public static int getDifferenceInMonthsBetweenDates(final Date startDate, final Date endDate) {
		final Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(startDate);
		final Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(endDate);
		final int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		return diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
	}
	
	/**
	 * Method getUtilDateFromString with specified format.
	 * 
	 * @param dateStr
	 * @param format
	 * @return java.util.Date
	 * @throws ParseException
	 */
	public static Date getUtilDateFromString(final String dateStr, final String format) throws ParseException {
		final DateFormat formatter = new SimpleDateFormat(format, Locale.US);
		return formatter.parse(dateStr);
	}
	
	/**
	 * Method formatDateAsString.
	 * @param date
	 * @return String
	 */
	public String formatDateAsString(final Date date) {
		final DateFormat dateFormat = new SimpleDateFormat(DT_FORMAT_YYYY_MM_DD, Locale.US);
		return dateFormat.format(date);
	}
	
	/**
	 * Returns 4 digit string value for year based on Date passed and the 2 digit year.
	 * @param currentDate
	 * @param year2digit
	 * @return String
	 */
	public String get4DigitYearFrom2DigitYear(final Date currentDate, final String year2digit) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		final int year = calendar.get(Calendar.YEAR);
		final String strYear = String.valueOf(year);
		return StringUtils.left(strYear, 2).concat(year2digit);
	}
	
	/**
	 * <p>Method getDateForDayInMonth returns a date for the day supplied in the date month.</p>
	 * <p>e.g. If the date passed is 2015-01-08 and the dayInMonth passed is 15, then the date created and 
	 * returned by this method would be 2015-01-15. 
	 * 
	 * @param date is usually the Exceed current system date.
	 * @param dayInMonth an integer day (1-31, depending on how many calendar days belong in the month for the date).
	 * @return Date
	 */
	public Date getDateForDayOfMonth(final Date date, final int dayInMonth) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH, dayInMonth);
		return new Date(calendar.getTime().getTime());
	}
	
	/**
	 * <p>Method getLastDayOfMonth returns a date representing the last day of the month for the supplied date.</p>
	 * <p>e.g. if the date passed is 2015-01-08, then this method will return 2015-01-31.</p>
	 *
	 * @param date is usually the Exceed current system date.
	 * @return Date
	 */
	public Date getLastDayOfMonthForDate(final Date date) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return new Date(calendar.getTime().getTime());
	}
	
	/**
	 * <p>
	 * Method getNextDaysDate. Given a java date, return a new java date for the
	 * next day with time set to midnight.
	 * </p>
	 * <p>
	 * e.g. Given date representing <code>Sat Feb 28 00:00:00 CST 2015</code>,
	 * return date for <code>Sun Mar 01 00:00:00 CST 2015</code>.
	 * </p>
	 * @param date
	 * @return Date
	 */
	public Date getNextDaysDate(final Date date) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DATE, 1);
		return new Date(calendar.getTime().getTime());
	}
	
	/**
	 * Method reformatDate.
	 * @param fromTimeZone
	 * @param fromFormat
	 * @param toFormat
	 * @param dateTime
	 * @return String
	 * @throws Exception
	 */
	public String reformatDate(final String fromTimeZone, final String fromFormat, final String toFormat, final String dateTime) throws ParseException {
		final DateFormat format = new SimpleDateFormat(fromFormat, Locale.US);
		format.setTimeZone(TimeZone.getTimeZone(fromTimeZone));
		final Date value = format.parse(dateTime);
		return DateFormatUtils.format(value, toFormat, TimeZone.getDefault());
	}
}
