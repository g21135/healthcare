package kr.or.ddit.service.diary;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.diary.DiaryDaoImpl;
import kr.or.ddit.dao.diary.IDiaryDao;
import kr.or.ddit.vo.DiaryVO;


public class DiaryServiceImpl extends UnicastRemoteObject implements IDiaryService {
	
	private static IDiaryService service; //싱글톤패턴
	private IDiaryDao DiaryDao;	//사용 할 dao 멤버변수 선언
	
	protected DiaryServiceImpl() throws RemoteException {
		DiaryDao = DiaryDaoImpl.getInstance();
	}
	public static IDiaryService getInstance() throws RemoteException{
		if(service == null) {
			service = new DiaryServiceImpl();
		}
		return service;
	}
	@Override
	public boolean insertDiary(DiaryVO dv){
		return DiaryDao.insertDiary(dv);
	}
	@Override
	public boolean updateDiary(DiaryVO dv){
		return DiaryDao.updateDiary(dv);
	}
	@Override
	public boolean deleteDiary(int id){
		return DiaryDao.deleteDiary(id);
	}
	@Override
	public boolean selectIdDiary(int id){
		return DiaryDao.selectIdDiary(id);
	}
	@Override
	public List<DiaryVO> selectAll(){
		return DiaryDao.selectAll();
	}
	@Override
	public List<DiaryVO> selectAll(String mem_id) throws RemoteException {
		return DiaryDao.selectAll(mem_id) ;
	}

}
