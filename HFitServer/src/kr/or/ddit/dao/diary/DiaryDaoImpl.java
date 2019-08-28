package kr.or.ddit.dao.diary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.DiaryVO;

public class DiaryDaoImpl implements IDiaryDao {
	private static IDiaryDao dao;
	private static SqlMapClient smc;

	private DiaryDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static IDiaryDao getInstance() {
		if (dao == null) {
			dao = new DiaryDaoImpl();
		}
		return dao;
	}

	@Override
	public boolean insertDiary(DiaryVO dv) {
		boolean result = false;
		Object obj = null;
		try {
			obj = smc.insert("diary.insert", dv);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (obj == null) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public boolean updateDiary(DiaryVO dv) {
		boolean result = false;
		int cnt = 0;
		try {
			cnt = smc.update("diary.update", dv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (cnt > 0) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public boolean deleteDiary(int id) {
		boolean result = false;
		int cnt = 0;
		try {
			cnt = smc.delete("diary.delete", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (cnt > 0) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public boolean selectIdDiary(int id) {
		boolean result = false;
		Object obj = null;
		try {
			obj = smc.queryForObject("diary.selectId", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (obj != null) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public List<DiaryVO> selectAll() {
		List<DiaryVO> diaryList = new ArrayList<DiaryVO>();
		try {
			diaryList = smc.queryForList("diary.selectAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return diaryList;
	}

	@Override
	public List<DiaryVO> selectAll(String mem_id) {
		List<DiaryVO> diaryList = new ArrayList<DiaryVO>();
		try {
			diaryList = smc.queryForList("diary.selectIdAll", mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return diaryList;
	}
}
