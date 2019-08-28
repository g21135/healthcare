package kr.or.ddit.service.board;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import kr.or.ddit.dao.board.BoardDaoImpl;
import kr.or.ddit.dao.board.IBoardDao;

public class BoardServiceImpl extends UnicastRemoteObject implements IBoardService{

	private IBoardDao boardDao;	//사용 할 dao 멤버변수 선언
	private static IBoardService service; //싱글톤패턴
	
	protected BoardServiceImpl() throws RemoteException {
		boardDao = BoardDaoImpl.getInstance();
	}
	
	public static IBoardService getInstance() throws RemoteException{
		if(service == null) {
			service = new BoardServiceImpl();
		}
		return service;
	}
	
}
