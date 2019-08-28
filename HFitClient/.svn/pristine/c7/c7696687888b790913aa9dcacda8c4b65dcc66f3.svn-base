package kr.or.ddit.view.myReport.diary;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.print.PrintView;
import javafx.stage.Stage;

// to print page
public class PrintPage {
    
	public static void setPrintView(Calendar diet,Calendar exercise,Calendar etc)
	{
		 // it will add all entry object which come from HomePageController class into calendar source
		 CalendarSource Diary= new CalendarSource("H&F PRINT");
		 Diary.getCalendars().addAll(diet, exercise, etc);
		
		 PrintView printView = new PrintView();
	     printView.setPrefWidth(1200);
	     printView.setPrefHeight(950);
	     printView.getCalendarSources().addAll(Diary);
	    
	     Stage newWindow = new Stage();
       
         newWindow.setMaxWidth(600);
         newWindow.setMaxHeight(400);
	     printView.show(newWindow); // add print view in new window
	   	     
	}
}
