package mapper;

import java.util.List;

import po.ManagerCustom;

public interface ManagerDao {
	
	public List<ManagerCustom> queryManagerList()throws Exception;

}
