package kr.or.ddit.dao.post;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.PostVO;

public class PostDaoImpl implements IPostDao{

	private static IPostDao dao;
	private static SqlMapClient smc;
	
	protected PostDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
		
	}
	
	public static IPostDao getInstance(){
		if(dao == null) {
			dao = new PostDaoImpl();

		}
		return dao;	
	}

	@Override
	public List<PostVO> allPostList(int num) {
		List<PostVO> postList = new ArrayList<>();
		
		try {
			postList = smc.queryForList("post.allPostList", num);
		} catch (SQLException e) {
			System.out.println("에러네");
			e.printStackTrace();
		}
		return postList;
	}

	@Override
	public int insertPost(PostVO pv) {
		int cnt = 0;
		try {
			Object obj = smc.insert("post.insertPost", pv);
			if(obj == null) {
				cnt = 1;
			}	
		} catch (SQLException e) {
			System.out.println("insertPost 에러");
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deletePost(int num) {
		int cnt = 0;
		try {
			cnt = smc.delete("post.deletePost", num);
		} catch (SQLException e) {
			System.out.println("deletePost 에러");
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updatePost(PostVO pv) {
		int cnt = 0;
		try {
			cnt = smc.update("post.updatePost", pv);
		} catch (SQLException e) {
			System.out.println("updatePost 에러");
			e.printStackTrace();
		}
		return cnt;
		
	}

	@Override
	public PostVO selectPostNum(PostVO pv) {
		PostVO result = null;
		try {
			result = (PostVO) smc.queryForObject("post.selectPostNum", pv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<PostVO> searchPost(PostVO search) {
		List<PostVO> searchList = new ArrayList<>();
		
		try {
			searchList = smc.queryForList("post.searchPost", search);
		} catch (SQLException e) {
			System.out.println("검색에러네");
			e.printStackTrace();
		}
		return searchList;
	}

	@Override
	public List<PostVO> searchTitle(PostVO search) {
		List<PostVO> searchList = new ArrayList<>();
		
		try {
			searchList = smc.queryForList("post.searchTitle", search);
		} catch (SQLException e) {
			System.out.println("검색에러네");
			e.printStackTrace();
		}
		return searchList;
	}

	@Override
	public List<PostVO> searchContent(PostVO search) {
		List<PostVO> searchList = new ArrayList<>();
		
		try {
			searchList = smc.queryForList("post.searchContent", search);
		} catch (SQLException e) {
			System.out.println("검색에러네");
			e.printStackTrace();
		}
		return searchList;
	}


}