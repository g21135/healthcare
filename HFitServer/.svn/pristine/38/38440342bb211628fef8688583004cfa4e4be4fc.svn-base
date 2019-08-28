package kr.or.ddit.service.trainer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.trainer.ITrainerDao;
import kr.or.ddit.dao.trainer.TrainerDaoImpl;
import kr.or.ddit.vo.TrainerVO;

public class TrainerServiceImpl extends UnicastRemoteObject implements ITrainerService {

	private static ITrainerService service;
	private ITrainerDao trainerDao;

	private TrainerServiceImpl() throws RemoteException {
		trainerDao = TrainerDaoImpl.getInstance();
	}

	public static ITrainerService getInstance() throws RemoteException {
		if (service == null) {
			service = new TrainerServiceImpl();
		}
		return service;
	}

	@Override
	public TrainerVO login(String id, String pass) throws RemoteException {
		return trainerDao.login(id,pass);
	}
	
	@Override
	public List<TrainerVO> getAllTrainerList() throws RemoteException {
		return trainerDao.getAllTrainerList();
	}

	@Override
	public int updateTrainer(TrainerVO vo) throws RemoteException {
		return trainerDao.updateTrainer(vo);
	}

	@Override
	public int insertTrainer(TrainerVO vo) throws RemoteException {
		return trainerDao.insertTrainer(vo);
	}

	@Override
	public TrainerVO getTrainer(String name) throws RemoteException{
		return trainerDao.getTrainer(name);
	}


}
