package service;

import java.util.List;

import po.ActivityCustom;

public interface ActivityService {
	
	public List<ActivityCustom> queryActivityList()throws Exception;
	
	public void addActivity(ActivityCustom activityCustom)throws Exception;

}
