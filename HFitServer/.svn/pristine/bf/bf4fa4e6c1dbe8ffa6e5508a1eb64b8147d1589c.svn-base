package kr.or.ddit.service.mediaUtil;

import java.io.BufferedInputStream;
import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import kr.or.ddit.vo.FileVO;

public interface IMdeiaUtil extends Remote{
	public void imgSave(FileVO file) throws RemoteException;
	public FileVO imgLoad(String url) throws RemoteException;
	public void videoSave(FileVO file) throws RemoteException;
	public FileVO videoLoad(String url) throws RemoteException;
}
