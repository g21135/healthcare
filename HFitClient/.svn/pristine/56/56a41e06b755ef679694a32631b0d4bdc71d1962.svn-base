package kr.or.ddit.view.myReport.diary;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.calendarfx.model.Entry;

import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.diary.IDiaryService;
import kr.or.ddit.view.trainer.TrainerReportController;
import kr.or.ddit.vo.DiaryVO;

public class SaveEntry {

	static int i;

	// to save entry in your database
	public static void saveEntry(HashMap<String, List<Object>> hm) {
		IDiaryService diary = null;
		try {
			Registry res = LocateRegistry.getRegistry(8888);
			diary = (IDiaryService) res.lookup("diary");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		for (Map.Entry<String, List<Object>> entrys : hm.entrySet()) {
			
			String key = entrys.getKey();
			List<Object> lValues = entrys.getValue();

			ListIterator<Object> litrs = lValues.listIterator();

			while (litrs.hasNext()) {
				Entry<?> en1 = (Entry<?>) litrs.next();
				try {

					if (!en1.getCalendar().getName().equals(key)) {
						litrs.remove(); // it will remove entry if it is duplicate in array list
					}

				} catch (NullPointerException e) {
					e.printStackTrace();
				}

			}
		}

		// to travel in Map collection
		for (Map.Entry<String, List<Object>> entrys : hm.entrySet()) {

			String key = entrys.getKey(); // get entry key

			List<Object> values = entrys.getValue(); // get entry value

			try {
				for (Object en : values) {
					Entry<?> en1 = (Entry<?>) en;
				        
					// it check if the entry is existed in database or not
					// if it is existed it will update data else it will insert new data in database
					if (diary.selectIdDiary(Integer.valueOf(en1.getId()))) {
						
						
						LocalDate date;
						date = en1.getStartDate();
						Date date1 = java.sql.Date.valueOf(date);

						date = en1.getEndDate();
						Date date2 = java.sql.Date.valueOf(date);

						LocalTime time;
						time = en1.getStartTime();
						Time t1 = java.sql.Time.valueOf(time);

						time = en1.getEndTime();
						Time t2 = java.sql.Time.valueOf(time);
						
						String isFullday = "1";
						if(en1.isFullDay()) {
							isFullday = "0";
						}

						String isRecurring = "1";
						if(en1.isRecurring()) {
							isRecurring = "0";
						}
						
						String isRecurrence = "1";
						if(en1.isRecurrence()) {
							isRecurrence = "0";
						}
						
						
						DiaryVO dv = new DiaryVO(Integer.parseInt(en1.getId()),
								LoginSession.memSession.getMem_id(),
								en1.getTitle(),
								isFullday, 
								date1, 
								date2, 
								t1, 
								t2,
								en1.getZoneId().toString(),
								isRecurring,
								en1.getRecurrenceRule(),
								isRecurrence,
								key);
						diary.updateDiary(dv);
					}

					else {
						LocalDate date;

						date = en1.getStartDate();
						Date date1 = java.sql.Date.valueOf(date);

						date = en1.getEndDate();
						Date date2 = java.sql.Date.valueOf(date);

						LocalTime time;
						time = en1.getStartTime();
						Time t1 = java.sql.Time.valueOf(time);

						time = en1.getEndTime();
						Time t2 = java.sql.Time.valueOf(time);
						
						String isFullday = "1";
						if(en1.isFullDay()) {
							isFullday = "0";
						}

						String isRecurring = "1";
						if(en1.isRecurring()) {
							isRecurring = "0";
						}
						
						String isRecurrence = "1";
						if(en1.isRecurrence()) {
							isRecurrence = "0";
						}
						
						if (LoginSession.memSession == null) {
							DiaryVO dv = new DiaryVO(Integer.parseInt(en1.getId()),
									TrainerReportController.mem_id,
									en1.getTitle(),
									isFullday, 
									date1, 
									date2, 
									t1, 
									t2,
									en1.getZoneId().toString(),
									isRecurring,
									en1.getRecurrenceRule(),
									isRecurrence,
									key);
							diary.insertDiary(dv);
						} else {
							DiaryVO dv = new DiaryVO(Integer.parseInt(en1.getId()),
									LoginSession.memSession.getMem_id(),
									en1.getTitle(),
									isFullday, 
									date1, 
									date2, 
									t1, 
									t2,
									en1.getZoneId().toString(),
									isRecurring,
									en1.getRecurrenceRule(),
									isRecurrence,
									key);
							diary.insertDiary(dv);
						}
						
					}
				}
			}catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} 

		}
		// if data is inserted successfully then it will return 1 and then it will show
		// alert message to user.
		if (i == 1) {
			AlertShow.setAlert();
		}
	}
}
