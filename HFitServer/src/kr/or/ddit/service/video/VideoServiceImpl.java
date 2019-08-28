package kr.or.ddit.service.video;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.video.IVideoDao;
import kr.or.ddit.dao.video.VideoDaoImpl;
import kr.or.ddit.vo.VideoVO;


public class VideoServiceImpl extends UnicastRemoteObject implements IVideoService {
	
	private IVideoDao VideoDao;	//사용 할 dao 멤버변수 선언
	private static IVideoService service; //싱글톤패턴
	
	protected VideoServiceImpl() throws RemoteException {
		VideoDao = VideoDaoImpl.getInstance();
	}
	
	public static IVideoService getInstance() throws RemoteException{
		if(service == null) {
			service = new VideoServiceImpl();
		}
		return service;
	}

	@Override
	public List<VideoVO> selectAll(int channel) throws RemoteException {
		return VideoDao.selectAll(channel);
	}

}
