package kr.or.ddit.dao.cartDetail;

import java.io.IOException;
import java.io.Reader;
import java.rmi.RemoteException;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;



public class CartDetailDaoImpl implements ICartDetailDao {

	private static SqlMapClient smc;
	
	private static ICartDetailDao dao;
	
	private CartDetailDaoImpl() throws RemoteException{
	}
	
	public static ICartDetailDao getInstance() throws RemoteException{
			if(dao == null) {
				dao = new CartDetailDaoImpl();
				Reader rd;
				try {
					rd = Resources.getResourceAsReader("SqlMapConfig.xml");
					smc = SqlMapClientFactory.getInstance();
					rd.close();
				} catch (IOException e) {
					System.out.println("[IBATIS 연결 실패]");
					e.printStackTrace();
				}
			}
			return dao;
	}
}
