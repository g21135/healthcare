package kr.or.ddit.view.card;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Time {
	public static void main(String[] args) {
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		int Month = calendar.get(calendar.MONTH);
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(date);
		System.out.println(str);
		
		
		
		
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = null;
		try {
			date2 = format2.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(date2);
		calendar.add(calendar.DATE, 30);
		System.out.println(date2);
		
		
		
		
		
	}
}
