package kr.or.ddit.dao.card;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.vo.CardVO;

public interface ICardDao {
	List<CardVO> getCardList() throws RemoteException;

	CardVO getCardBalance(String card_name,String mem_bir) throws RemoteException;

	int updateBalance(CardVO cv) throws RemoteException;
//	int updateBalance(int card_Balance, String card_Name, String mem_Bir) throws RemoteException;
}
