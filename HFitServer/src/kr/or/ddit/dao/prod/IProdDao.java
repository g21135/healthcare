package kr.or.ddit.dao.prod;

import java.util.List;

import kr.or.ddit.vo.ProdVO;

public interface IProdDao {
	
	public List<ProdVO> ProdList();
	
	public List<ProdVO> ProdMagerment();
	
	public List<ProdVO> FoodProdList();
	
	public List<ProdVO> HealthProdList();

	public List<ProdVO> ProdSelectList(int prod_no);

	public int InsertProd(ProdVO pv);

	public int InsertRemain();

	public List<ProdVO> PayMagerment();

	public int DeleteProd();

	public int ProdSelectUpdate(ProdVO pv);

	public int ProdCheckUpdate(ProdVO pv);

}