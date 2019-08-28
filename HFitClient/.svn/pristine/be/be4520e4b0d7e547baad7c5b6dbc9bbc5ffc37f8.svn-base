package kr.or.ddit.view.myReport.diary;


import java.time.LocalDate;
import java.time.LocalTime;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;

// this class will call when above button are press
// to add entry into Calendar
public class SaveDiet {

	
	public static void setDiet(Calendar diet)
	{
		
		 Entry<?> addentry = new Entry<>();
	         
	     addentry.setTitle("Diet");
	    
	     addentry.setFullDay(false);
	     
	     addentry.changeStartDate(LocalDate.now());
	     addentry.changeEndDate(LocalDate.now());
	     addentry.changeStartTime(LocalTime.now());
	     addentry.changeEndTime(LocalTime.now());
         
	     diet.addEntry(addentry);
            
	}
	
}
