package kr.or.ddit.dao.card;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.CardVO;

public class CardDaoImpl implements ICardDao{
	private static ICardDao dao;
	private static SqlMapClient smc;
	
	private CardDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ICardDao getInstance() {
			if(dao == null) {
				dao = new CardDaoImpl();
			}
			return dao;
	}

	@Override
	public List<CardVO> getCardList() throws RemoteException {
		List<CardVO> getList = new ArrayList<>();
		
		try {
			getList = smc.queryForList("card.getList");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getList;
	}

	@Override
	public CardVO getCardBalance(String card_name, String mem_bir) throws RemoteException {
//		int cnt = 0;
		CardVO vo = new CardVO();
		
		vo.setCard_name(card_name);
		vo.setMem_bir(mem_bir);
		
		try {
			vo = (CardVO) smc.queryForObject("card.getBalance", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public int updateBalance(CardVO cv)
			throws RemoteException {
		int update = 0;
		try {
			update = smc.update("card.updateBalance", cv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return update;
	}
//	@Override
//	public int updateBalance(int card_Balacnce, String card_Name, String mem_Bir)
//			throws RemoteException {
////		Object obj = null;
//		int update = 0 ;
//		Map<String, Object> map = new HashMap<>();
//		
//		map.put("card_Balance", card_Balacnce);
//		map.put("card_Name", card_Name);
//		map.put("mem_Bir", mem_Bir);
//		
//		try {
//			update = smc.update("card.updateBalance", map);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return update;
//	}

	

}
