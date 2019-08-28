package kr.or.ddit.dao.post;

import java.util.List;
import kr.or.ddit.vo.PostVO;

public interface IPostDao {
	
	/**
	 * postVO테이블의 해당 게시판의 전체 게시물 목록을 가져와 반환하는 메서드 
	 * @return
	 */
	public List<PostVO> allPostList(int num);
	
	/**
	 * PostVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param pv
	 * @return
	 */
	public int insertPost(PostVO pv);
	
	/**
	 * 게시물번호를 매개변수로 받아서 해당 게시글을 삭제하는 메서드
	 * @param num
	 * @return
	 */
	public int deletePost(int num);
	
	/**
	 * 하나의 PostVO자료를 이용하여 DB를 update하는 메서드
	 * @param pv
	 * @return
	 */
	public int updatePost(PostVO pv);
	
	/**
	 * 하나의 PostVO를 이용하여 게시물 번호를 가져와 반환하는 메서드
	 * @param pv
	 * @return
	 */
	public PostVO selectPostNum(PostVO pv);
	

	public List<PostVO> searchPost(PostVO search);

	public List<PostVO> searchTitle(PostVO search);

	public List<PostVO> searchContent(PostVO search);
	

}