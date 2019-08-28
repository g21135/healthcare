package kr.or.ddit.service.card;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.dao.card.CardDaoImpl;
import kr.or.ddit.dao.card.ICardDao;
import kr.or.ddit.vo.CardVO;



public class CardServiceImpl extends UnicastRemoteObject implements ICardService {

	private ICardDao CardDao;	//사용 할 dao 멤버변수 선언
	private static ICardService service; //싱글톤패턴
	
	protected CardServiceImpl() throws RemoteException {
		CardDao = CardDaoImpl.getInstance();
	}
	
	public static ICardService getInstance() throws RemoteException{
		if(service == null) {
			service = new CardServiceImpl();
		}
		return service;
	}

	/*@Override
	public int RequestDealHistroyInsert() {
		// TODO Auto-generated method stub
		return 0;
	}*/

	@Override
	public List<CardVO> getCardList() throws RemoteException {
		return CardDao.getCardList();
	}

	@Override
	public CardVO getCardBalance(String card_name,String mem_bir) throws RemoteException {
		return CardDao.getCardBalance(card_name,mem_bir);
	}

	@Override
	public int updateBalance(CardVO cv)
			throws RemoteException {
		return CardDao.updateBalance(cv);
	}
//	@Override
//	public int updateBalance(int card_Balance, String card_Name, String mem_Bir)
//			throws RemoteException {
//		return CardDao.updateBalance(card_Balance, card_Name, mem_Bir);
//	}

	
}
