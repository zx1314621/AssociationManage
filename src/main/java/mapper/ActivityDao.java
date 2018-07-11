package mapper;

import java.util.List;

import po.ActivityCustom;
import po.AsCustom;

public interface ActivityDao {
	
	public List<ActivityCustom> queryActivityList()throws Exception;
	
	public void addActivity(ActivityCustom activityCustom)throws Exception;

}
