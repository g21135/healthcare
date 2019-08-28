package kr.or.ddit.service.openTalk;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.openTalk.IOpenTalkDao;
import kr.or.ddit.dao.openTalk.OpenTalkDaoImpl;
import kr.or.ddit.vo.OpenTalkVO;



public class OpenTalkServiceImpl extends UnicastRemoteObject implements IOpenTalkService {
	
	private IOpenTalkDao OpenTalkDao;	//사용 할 dao 멤버변수 선언
	private static IOpenTalkService service; //싱글톤패턴
	
	protected OpenTalkServiceImpl() throws RemoteException {
		OpenTalkDao = OpenTalkDaoImpl.getInstance();
	}
	
	public static IOpenTalkService getInstance() throws RemoteException{
		if(service == null) {
			service = new OpenTalkServiceImpl();
		}
		return service;
	}

	@Override
	public List<OpenTalkVO> selectAll() throws RemoteException {
		return OpenTalkDao.selectAll();
	}

}
