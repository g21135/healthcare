package kr.or.ddit.service.talkAdmin;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.TalkAdminVO;

public interface ITalkAdminService extends Remote{
	public List<TalkAdminVO> selectNum(int num) throws RemoteException;
	public void insert(TalkAdminVO tav) throws RemoteException;
	public List<TalkAdminVO> selectId(String mem_id) throws RemoteException;
	public void delete(TalkAdminVO tav) throws RemoteException;
	public List<TalkAdminVO> selectAll() throws RemoteException;
	
}
