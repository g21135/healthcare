package kr.or.ddit.service.chart;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.chart.ChartDaoImpl;
import kr.or.ddit.dao.chart.IChartDao;
import kr.or.ddit.vo.ChartVO;

public class ChartServiceImpl extends UnicastRemoteObject implements IChartService{
	
	private IChartDao chartDao;
	private static IChartService service; //싱글톤패턴
	
	protected ChartServiceImpl() throws RemoteException {
		chartDao = ChartDaoImpl.getInstance();
	}
	
	public static IChartService getInstance() throws RemoteException{
		if(service == null) {
			service = new ChartServiceImpl();
		}
		return service;
	}
	
	@Override
	public List<ChartVO> getAllChart(ChartVO cv) throws RemoteException{
		return chartDao.getAllChart(cv);
	}

	@Override
	public boolean insert(ChartVO cv) throws RemoteException {
		return chartDao.insert(cv);
	}

}
