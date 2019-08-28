package kr.or.ddit.service.diary;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.DiaryVO;

public interface IDiaryService extends Remote{
	public boolean insertDiary(DiaryVO dv) throws RemoteException;
	public boolean updateDiary(DiaryVO dv) throws RemoteException;
	public boolean deleteDiary(int id) throws RemoteException;
	public boolean selectIdDiary(int id) throws RemoteException;
	public List<DiaryVO> selectAll() throws RemoteException;
	public List<DiaryVO> selectAll(String mem_id) throws RemoteException;
}
  