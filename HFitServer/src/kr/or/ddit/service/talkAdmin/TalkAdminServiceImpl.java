package kr.or.ddit.service.talkAdmin;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.talkAdmin.ITalkAdminDao;
import kr.or.ddit.dao.talkAdmin.TalkAdminDaoImpl;
import kr.or.ddit.vo.TalkAdminVO;


public class TalkAdminServiceImpl extends UnicastRemoteObject implements ITalkAdminService {
	
	private ITalkAdminDao TalkAdminDao;	//사용 할 dao 멤버변수 선언
	private static ITalkAdminService service; //싱글톤패턴
	
	protected TalkAdminServiceImpl() throws RemoteException {
		TalkAdminDao = TalkAdminDaoImpl.getInstance();
	}
	
	public static ITalkAdminService getInstance() throws RemoteException{
		if(service == null) {
			service = new TalkAdminServiceImpl();
		}
		return service;
	}

	@Override
	public List<TalkAdminVO> selectNum(int num) throws RemoteException {
		return TalkAdminDao.selectNum(num);
	}

	@Override
	public void insert(TalkAdminVO tav) throws RemoteException {
		TalkAdminDao.insert(tav);
	}

	@Override
	public List<TalkAdminVO> selectId(String mem_id) throws RemoteException {
		return TalkAdminDao.selectId(mem_id);
	}

	@Override
	public void delete(TalkAdminVO tav) throws RemoteException {
		TalkAdminDao.delete(tav);
	}

	@Override
	public List<TalkAdminVO> selectAll() throws RemoteException {
		return TalkAdminDao.selectAll();
	}

}
