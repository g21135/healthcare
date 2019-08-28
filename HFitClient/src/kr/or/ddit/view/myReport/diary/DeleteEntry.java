package kr.or.ddit.view.myReport.diary;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import com.calendarfx.model.Entry;

import kr.or.ddit.service.diary.IDiaryService;

public class DeleteEntry {

	public static void deleteEntry(String id, List<Object> values, Entry<?> cv) throws SQLException {

		IDiaryService diary = null;
		try {
			Registry res = LocateRegistry.getRegistry(8888);
			diary = (IDiaryService) res.lookup("diary");
			
		} catch (AccessException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}

		ListIterator<Object> litrs = values.listIterator(); // to delete entry from List<Object> array lists

		while (litrs.hasNext()) {
			Entry<?> en1 = (Entry<?>) litrs.next();

			if (en1.equals(cv)) {
				litrs.remove();

				try {
					if (diary.selectIdDiary(Integer.valueOf(en1.getId()))) {
						diary.deleteDiary(Integer.valueOf(en1.getId()));
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
