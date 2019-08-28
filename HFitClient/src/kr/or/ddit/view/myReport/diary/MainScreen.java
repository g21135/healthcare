package kr.or.ddit.view.myReport.diary;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;

import javafx.event.EventHandler;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.diary.IDiaryService;
import kr.or.ddit.view.trainer.TrainerReportController;
import kr.or.ddit.vo.DiaryVO;

public class MainScreen {

	static Calendar diet = new Calendar("Diet");
	static Calendar exercise = new Calendar("Exercise");
	static Calendar etc = new Calendar("Etc");

	// HashMap is used to store the one key and multiple values like key is Entry
	// name and Values are multiple entry
	static HashMap<String, List<Object>> hm = new HashMap<String, List<Object>>();
	static List<Object> valuesOne = new ArrayList<Object>();
	static List<Object> valuesTwo = new ArrayList<Object>();
	static List<Object> valuesThree = new ArrayList<Object>();

	public static void setWeekPage(CalendarView cl) {
		// add colors to Entry
		diet.setStyle(Style.STYLE2);
		exercise.setStyle(Style.STYLE5);
		etc.setStyle(Style.STYLE1);

		// make bunch of Calendars
		CalendarSource calendarSource = new CalendarSource();
		calendarSource.getCalendars().add(diet);
		calendarSource.getCalendars().add(exercise);
		calendarSource.getCalendars().add(etc);

		cl.getCalendarSources().setAll(calendarSource);
		cl.getCalendars(); // add into calendar view

		// Event on every entry to store the every entry in Array List and then store in
		// HashMap with key
		diet.addEventHandler(new EventHandler<CalendarEvent>() {

			@Override
			public void handle(CalendarEvent cv) {
				try {
					valuesOne.add(cv.getEntry());

					hm.put(cv.getCalendar().getName(), valuesOne);
				} catch (NullPointerException n) {
					try {
						// if some entry will delete it will call delete entry method from
						// DeleteEntry class and here we are passing entry id,List object,whole entry
						DeleteEntry.deleteEntry(cv.getEntry().getId(), valuesOne, cv.getEntry());
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}
			}
		});

		exercise.addEventHandler(new EventHandler<CalendarEvent>() {

			@Override
			public void handle(CalendarEvent cv) {

				try {
					valuesTwo.add(cv.getEntry());
					hm.put(cv.getCalendar().getName(), valuesTwo);
				} catch (NullPointerException n) {
					try {
						DeleteEntry.deleteEntry(cv.getEntry().getId(), valuesTwo, cv.getEntry());
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}
			}
		});

		etc.addEventHandler(new EventHandler<CalendarEvent>() {

			@Override
			public void handle(CalendarEvent cv) {

				try {
					valuesThree.add(cv.getEntry());
					hm.put(cv.getCalendar().getName(), valuesThree);
				} catch (NullPointerException n) {
					try {
						DeleteEntry.deleteEntry(cv.getEntry().getId(), valuesThree, cv.getEntry());
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}

			}

		});

		try {
			IDiaryService diary = null;
			Registry res = LocateRegistry.getRegistry(8888);
			diary = (IDiaryService) res.lookup("diary");
			
			List<DiaryVO> list;
			if (LoginSession.memSession == null) {
				list = diary.selectAll(TrainerReportController.mem_id);
			} else {
				list = diary.selectAll(LoginSession.memSession.getMem_id());
			}
			for (DiaryVO dv : list) {
				Entry<?> addentry = new Entry<>();

				addentry.setTitle(dv.getTitle());
				addentry.setId(Integer.toString(dv.getId()));
				if (dv.getFullDay().equals("1")) {
					addentry.setFullDay(true);
				}
				Date date1;
				LocalDate ldate;

				date1 = dv.getStartDate();
				ldate = date1.toLocalDate();
				addentry.changeStartDate(ldate);

				date1 = dv.getEndDate();
				ldate = date1.toLocalDate();
				addentry.changeEndDate(ldate);

				Time t;
				LocalTime ltime;

				t = dv.getStartTime();
				ltime = t.toLocalTime(); 	
				addentry.changeStartTime(ltime);

				t = dv.getEndTime();
				ltime = t.toLocalTime();
				addentry.changeEndTime(ltime);

				addentry.setRecurrenceRule(dv.getRrule());
				String calendarName = dv.getEntryType();

				if (calendarName.equals("Diet")) {
					diet.addEntry(addentry);
				} else if (calendarName.equals("Exercise")) {
					exercise.addEntry(addentry);
				}

				else if (calendarName.equals("Etc")) {
					etc.addEntry(addentry);
				}
			}
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

	// This methods return all entry
	public static void saveEntry() {
		SaveEntry.saveEntry(hm);

	}
	public static Calendar getDiet() {
		return MainScreen.diet;
	}

	public static Calendar getExercise() {
		return MainScreen.exercise;
	}

	public static Calendar getEtc() {
		return MainScreen.etc;
	}

}
