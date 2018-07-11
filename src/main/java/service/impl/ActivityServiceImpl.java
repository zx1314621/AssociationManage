package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.ActivityDao;
import po.ActivityCustom;
import service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService{

	
	@Autowired
	private ActivityDao activityDao;
	
	public List<ActivityCustom> queryActivityList() throws Exception {
		// TODO Auto-generated method stub
		return activityDao.queryActivityList();
	}

}
