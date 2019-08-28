package kr.or.ddit.view.myReport.diary;

import java.time.LocalDate;
import java.time.LocalTime;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;

//this class will call when above button are press
//to add entry into Calendar
public class SaveExercise {

	public static void setExercise(Calendar exercise)
	{
		 Entry<?> addentry = new Entry<>();
	         
	     addentry.setTitle("Exercise");
	    
	     addentry.setFullDay(false);
	     
	     addentry.changeStartDate(LocalDate.now());
	     addentry.changeEndDate(LocalDate.now());
	     addentry.changeStartTime(LocalTime.now());
	     addentry.changeEndTime(LocalTime.now());
         
	     exercise.addEntry(addentry);
            
	}
}
