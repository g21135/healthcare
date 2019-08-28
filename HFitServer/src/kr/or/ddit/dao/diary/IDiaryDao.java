package kr.or.ddit.dao.diary;

import java.util.List;

import kr.or.ddit.vo.DiaryVO;

public interface IDiaryDao {

	boolean insertDiary(DiaryVO dv);

	boolean updateDiary(DiaryVO dv);

	boolean deleteDiary(int id);

	boolean selectIdDiary(int id);

	List<DiaryVO> selectAll();

	List<DiaryVO> selectAll(String mem_id);

}
