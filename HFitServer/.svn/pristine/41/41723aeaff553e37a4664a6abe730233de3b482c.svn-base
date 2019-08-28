package kr.or.ddit.dao.chart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.ChartVO;

public class ChartDaoImpl implements IChartDao{
	
	private static IChartDao dao;
	private static SqlMapClient smc;
	
	protected ChartDaoImpl() {
		smc = SqlMapClientFactory.getInstance();	
	}
	
	public static IChartDao getInstance(){
		if(dao == null) {
			dao = new ChartDaoImpl();
		}
		return dao;	
	}

	@Override
	public List<ChartVO> getAllChart(ChartVO cv) {
		List<ChartVO> chartList = new ArrayList<>();
		
		try {
			chartList = smc.queryForList("chart.getAllChart", cv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chartList;
	}

	@Override
	public boolean insert(ChartVO cv) {
		boolean isExist = false;
		Object obj = null; 
		try {
			obj = smc.insert("chart.insert", cv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(obj == null) {
			isExist = true;
		}
		return isExist;
	}

}
