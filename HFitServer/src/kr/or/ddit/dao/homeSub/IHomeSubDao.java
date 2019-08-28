package kr.or.ddit.dao.homeSub;

import java.util.List;

import kr.or.ddit.vo.HomeSubVO;

public interface IHomeSubDao {

	void insert(HomeSubVO hsv);

	List<HomeSubVO> selectAll();

	void delete(HomeSubVO hsv);

}
