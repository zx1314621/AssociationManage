package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.ManagerDao;
import po.ManagerCustom;
import service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService{

	@Autowired
	ManagerDao managerDao;
	
	public List<ManagerCustom> queryManagerList() throws Exception {
		// TODO Auto-generated method stub
		return managerDao.queryManagerList();
	}

}
