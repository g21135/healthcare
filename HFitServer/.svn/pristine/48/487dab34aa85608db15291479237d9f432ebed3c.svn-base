package kr.or.ddit.dao.cart;

import java.io.IOException;
import java.io.Reader;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.CartVO;

public class CartDaoImpl implements ICartDao {
	
	private static SqlMapClient smc;
	
	private static ICartDao dao;
	
	private CartDaoImpl() throws RemoteException{
	}
	
	public static ICartDao getInstance() throws RemoteException{
			if(dao == null) {
				dao = new CartDaoImpl();
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

	@Override
	public int InsertCart(CartVO cv) {
		int cnt = 0;
		try {
			cnt = smc.update("cart.InsertCart",cv);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public List<CartVO> AllCartListget(CartVO cv) {
		List<CartVO> cartlist =null;
		try {
			cartlist = smc.queryForList("cart.AllCartListGet",cv);
		} catch (SQLException e) {
			cartlist = null;
			e.printStackTrace();
		}
		
		return cartlist;
	}

	@Override
	public int DeleteCart(CartVO cv) {
		int cnt = 0;
		try {
			cnt = smc.delete("cart.CartListDelete",cv);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int CartSelectUpdate(CartVO cv) {
		int cnt = 0;
		try {
			cnt = smc.update("cart.CartSelectUpdate",cv);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
		
		
	}

	@Override
	public int CartCheckUpdate(CartVO cv) {
		int cnt = 0;
		try {
			cnt = smc.update("cart.CartCheckUpdate",cv);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int DeleteOrder() {
		int cnt = 0;
		try {
			cnt = smc.delete("cart.OrderListDelete");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<CartVO> CheckCartListget(CartVO cv) {
		List<CartVO> cartlist =null;
		try {
			cartlist = smc.queryForList("cart.CheckCartListGet",cv);
		} catch (SQLException e) {
			cartlist = null;
			e.printStackTrace();
		}
		
		return cartlist;
	}

	

}
