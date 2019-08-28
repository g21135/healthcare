package kr.or.ddit.service.post;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.post.IPostDao;
import kr.or.ddit.dao.post.PostDaoImpl;
import kr.or.ddit.vo.PostVO;

public class PostServiceImpl extends UnicastRemoteObject implements IPostService{

	private static IPostService service;
	private IPostDao postDao;
	
	private PostServiceImpl() throws RemoteException{
		postDao = PostDaoImpl.getInstance();
	}
	public static IPostService getInstance() throws RemoteException {
		if(service == null) {
				service = new PostServiceImpl();
		}
		return service;
	}
	
	@Override
	public List<PostVO> allPostList(int num) throws RemoteException {
		return postDao.allPostList(num);
	}
	@Override
	public int insertPost(PostVO pv) throws RemoteException {
		return postDao.insertPost(pv);
	}
	@Override
	public int deletePost(int num) throws RemoteException {
		return postDao.deletePost(num);
	}
	@Override
	public int updatePost(PostVO pv) throws RemoteException {
		return postDao.updatePost(pv);
	}
	@Override
	public PostVO selectPostNum(PostVO pv) throws RemoteException{
		return postDao.selectPostNum(pv);
	}

	@Override
	public List<PostVO> searchPost(PostVO search) throws RemoteException {
		return postDao.searchPost(search);
	}
	@Override
	public List<PostVO> searchTitle(PostVO search) throws RemoteException {
		return  postDao.searchTitle(search);
	}
	@Override
	public List<PostVO> searchContent(PostVO search) throws RemoteException {
		return  postDao.searchContent(search);
	}
	

}
