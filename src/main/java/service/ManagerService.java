package service;

import java.util.List;

import po.ManagerCustom;

public interface ManagerService {
	
	public List<ManagerCustom> queryManagerList()throws Exception;

}
