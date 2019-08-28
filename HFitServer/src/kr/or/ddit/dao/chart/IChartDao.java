package kr.or.ddit.dao.chart;

import java.util.List;

import kr.or.ddit.vo.ChartVO;

public interface IChartDao {
	
	public List<ChartVO> getAllChart(ChartVO cv);

	public boolean insert(ChartVO cv);

}
