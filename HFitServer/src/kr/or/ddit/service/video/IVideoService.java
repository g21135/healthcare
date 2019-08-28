package kr.or.ddit.service.video;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.VideoVO;

public interface IVideoService extends Remote{
	public List<VideoVO> selectAll(int channel) throws RemoteException;
}