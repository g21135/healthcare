package kr.or.ddit.service.post;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.PostVO;

public interface IPostService extends Remote{
	
	/**
	 * DB의 post테이블의 전체 레코드를 가져와 List에 담아서 반환하는 메서드
	 * @return
	 */
	public List<PostVO> allPostList(int num) throws RemoteException;
	
	/**
	 * PostVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param pv
	 * @return
	 */
	public int insertPost(PostVO pv) throws RemoteException;
	
	/**
	 * 게시물 번호를 매개변수로 받아서 그 게시물을 삭제하는 메서드
	 * @param num
	 * @return
	 */
	public int deletePost(int num) throws RemoteException;
	
	/**
	 * 하나의 PostVO를 이용하여 DB를 update하는 메서드
	 * @param pv
	 * @return
	 */
	public int updatePost(PostVO pv) throws RemoteException;
	
	public PostVO selectPostNum(PostVO pv) throws RemoteException;	
	
	public List<PostVO> searchPost(PostVO search) throws RemoteException;	
	
	public List<PostVO> searchTitle(PostVO search) throws RemoteException;

	public List<PostVO> searchContent(PostVO search) throws RemoteException;

}


