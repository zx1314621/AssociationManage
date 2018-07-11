package mapper;

import java.util.List;

import po.ActivityCustom;

public interface ActivityDao {
	
	public List<ActivityCustom> queryActivityList()throws Exception;

}
