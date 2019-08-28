package kr.or.ddit.view.myReport.diary;

import java.time.LocalDate;
import java.time.LocalTime;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;

//this class will call when above button are press
//to add entry into Calendar
public class SaveEtc {

	public static void setEtc(Calendar etc)
	{
		 Entry<?> addentry = new Entry<>();
	         
	     addentry.setTitle("Etc");
	    
	     addentry.setFullDay(false);
	     
	     addentry.changeStartDate(LocalDate.now());
	     addentry.changeEndDate(LocalDate.now());
	     addentry.changeStartTime(LocalTime.now());
	     System.out.println(LocalTime.now().getHour());
	     addentry.changeEndTime(LocalTime.now());
         
	     etc.addEntry(addentry);
            
	}
}
