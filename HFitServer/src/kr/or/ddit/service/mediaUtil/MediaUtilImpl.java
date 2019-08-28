package kr.or.ddit.service.mediaUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import kr.or.ddit.vo.FileVO;

public class MediaUtilImpl extends UnicastRemoteObject implements IMdeiaUtil {
	private static IMdeiaUtil mu;

	private MediaUtilImpl() throws RemoteException {
		super();
	}

	public static IMdeiaUtil getInstance() throws RemoteException {
		if (mu == null) {
			mu = new MediaUtilImpl();
		}
		return mu;
	}

	@Override
	public void imgSave(FileVO file) throws RemoteException {
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream("img/" + file.getFileName()));
			bos.write(file.getFileData());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public FileVO imgLoad(String url) throws RemoteException {
		
		FileVO file = new FileVO();
		try {
			
			byte[] data = new byte[(int) new File("D:\\A_TeachingMaterial\\3.HighJava\\workspace\\HFitServer\\img/" + url).length()];
//			byte[] data = new byte[(int) new File("C:\\Users\\dnfrk\\OneDrive\\바탕 화면\\workspace\\HFitServer\\img/" + url).length()];
//			byte[] data = new byte[(int) new File("D:/Util/workspace_java_oxygen/HFitServer/img/" + url).length()];
//			byte[] data = new byte[(int) new File("D:/A_TeachingMaterial/3.HighJava/workspace/HFitServer/img/" + url).length()];
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream("img/" + url));
			bis.read(data);		   
			file.setFileData(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	@Override
	public void videoSave(FileVO file) throws RemoteException {
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream("vedio/" + file.getFileName()));
			bos.write(file.getFileData());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public FileVO videoLoad(String url) throws RemoteException {
		FileVO file = new FileVO();
		try {
			byte[] data = new byte[(int) new File("C:\\Users\\dnfrk\\OneDrive\\바탕 화면\\workspace\\HFitServer\\video/" + url).length()];
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream("vedio/" + url));
			bis.read(data);
			file.setFileData(data);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return file;
	}

}
