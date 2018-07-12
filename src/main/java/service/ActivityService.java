package service;

import java.util.List;

import po.ActivityCustom;

public interface ActivityService {
	
	public List<ActivityCustom> queryActivityList()throws Exception;
	
	public void addActivity(ActivityCustom activityCustom)throws Exception;
	
	public void deleteActivityByid(String id)throws Exception;
	
	public void updatedeleteActivityStatusByID(String id)throws Exception;
	
	public void updateActivityByID(ActivityCustom activityCustom)throws Exception;

}
