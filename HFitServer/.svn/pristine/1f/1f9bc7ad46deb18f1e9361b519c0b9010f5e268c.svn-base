package kr.or.ddit.service.prod;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.ProdVO;

public interface IProdService extends Remote {

	public List<ProdVO> ProdList() throws RemoteException;
	
	public List<ProdVO> ProdMagerment() throws RemoteException;
	
	public List<ProdVO> PayMagerment() throws RemoteException;
	
	public List<ProdVO> HealthProdList() throws RemoteException;
	
	public List<ProdVO> FoodProdList() throws RemoteException;
	
	public List<ProdVO> ProdSelectList(int pord_no) throws RemoteException;
	
	
	public int InsertProd(ProdVO pv) throws RemoteException;
	
	public int InsertRemain() throws RemoteException;
	
	
	public int DeleteProd() throws RemoteException;

	public int ProdSelectUpdate(ProdVO pv)throws RemoteException;

	public int ProdCheckUpdate(ProdVO pv)throws RemoteException;

}
