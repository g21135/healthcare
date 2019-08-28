package kr.or.ddit.service.reply;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.reply.IReplyDao;
import kr.or.ddit.dao.reply.ReplyDaoImpl;
import kr.or.ddit.vo.ReplyVO;

public class ReplyServiceImpl extends UnicastRemoteObject implements IReplyService{
	
	private static IReplyService service;
	private IReplyDao replyDao;

	protected ReplyServiceImpl() throws RemoteException {
		replyDao = ReplyDaoImpl.getInstance();
	}
	
	public static IReplyService getInstance() throws RemoteException {
		if(service == null) {
				service = new ReplyServiceImpl();
		}
		return service;
	}

	@Override
	public List<ReplyVO> allReplyList(int num) throws RemoteException{
		return replyDao.allReplyList(num);
	}

	@Override
	public int insertReply(ReplyVO rv) throws RemoteException{
		return replyDao.insertReply(rv);
	}

	@Override
	public int deleteReply(int num) throws RemoteException{
		return replyDao.deleteReply(num);
	}

	@Override
	public int updateReply(ReplyVO rv) throws RemoteException{
		return replyDao.updateReply(rv);
	}
	

}
