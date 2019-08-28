package kr.or.ddit.dao.prod;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.ProdVO;

	public class ProdDaoImpl implements IProdDao{
	private static IProdDao dao;
	private static SqlMapClient smc;
	
	private ProdDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IProdDao getInstance() {
			if(dao == null) {
				dao = new ProdDaoImpl();
			}
			return dao;
	}

	
	@Override
	public List<ProdVO> ProdList() {
		List<ProdVO> List = new ArrayList<ProdVO>();
		try {
			List =  smc.queryForList("prod.prodlist");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return List;
	}

	@Override
	public List<ProdVO> ProdSelectList(int prod_no) {
		List<ProdVO> List = null;
		try {
			List = smc.queryForList("prod.prodselectlist",prod_no);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return List;
	}

	@Override
	public List<ProdVO> FoodProdList() {
		List<ProdVO> List = new ArrayList<ProdVO>();
		try {
			List =  smc.queryForList("prod.foodprodlist");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return List;
	}

	@Override
	public List<ProdVO> HealthProdList() {
		List<ProdVO> List = new ArrayList<ProdVO>();
		try {
			List =  smc.queryForList("prod.healthprodlist");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return List;
	}

	@Override
	public List<ProdVO> ProdMagerment() {
		List<ProdVO> List = new ArrayList<ProdVO>();
		try {
			List =  smc.queryForList("prod.prodmagerment");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return List;
	}

	


	@Override
	public int InsertProd(ProdVO pv) {
		int cnt = 0;
		try {
			cnt = smc.update("prod.prodadd",pv);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public int InsertRemain() {
		int cnt = 0;
		try {
			cnt = smc.update("prod.remainadd");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<ProdVO> PayMagerment() {
		List<ProdVO> List = new ArrayList<ProdVO>();
		try {
			List =  smc.queryForList("prod.paymagerment");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return List;
	}

	@Override
	public int DeleteProd() {
		int cnt = 0;
		try {
			cnt = smc.delete("prod.ProdListDelete");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int ProdSelectUpdate(ProdVO pv) {
		int cnt = 0;
		try {
			cnt = smc.update("prod.ProdSelectUpdate",pv);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int ProdCheckUpdate(ProdVO pv) {
		int cnt = 0;
		try {
			cnt = smc.update("prod.ProdCheckUpdate",pv);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
}