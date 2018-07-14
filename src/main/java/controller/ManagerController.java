package controller;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import po.ActivityCustom;
import po.AsCustom;
import po.ManagerCustom;
import service.ActivityService;
import service.AsService;
import service.ManagerService;

@Controller
@RequestMapping("/jsp")
public class ManagerController {
	
	@Autowired
	public ManagerService managerService;
	
	@Autowired
	public AsService asService;
	
	@Autowired
	public ActivityService activityService;
	
	@RequestMapping("/managersignin.action")
	public ModelAndView managersignin(String username, String password) throws Exception {
		
		List<ManagerCustom> managerList = managerService.queryManagerList();
		for(int i=0; i<managerList.size(); i++) {
			if(managerList.get(i).getId().equals(username)) {
				if(managerList.get(i).getPassword().equals(password)){
					ManagerCustom managerCustom = new ManagerCustom();
					managerCustom = managerList.get(i);
					
					//跳转
					ModelAndView modelandview = new ModelAndView();
					modelandview.addObject("managerCustom",managerCustom);
					
					modelandview.setViewName("home_for_d");
					
					return modelandview;
				}
			}
		}
		
		
		ModelAndView modelandview = new ModelAndView();
		//modelandview.addObject("asList",asList);
		modelandview.addObject("signflag","1");
		modelandview.addObject("username",username);
		modelandview.addObject("password",password);
		modelandview.setViewName("main");
		
		return modelandview;
	}

	
	@RequestMapping("/approveAs.action")
	public ModelAndView approveAs() throws Exception {
		
		
		List<AsCustom> asList = new ArrayList<AsCustom>();
		asList = asService.queryAsList();
		for(int i=0; i<asList.size(); i++) {
			if(asList.get(i).getStatus()!=0) {
				asList.remove(i);
				i--;
			}
		}
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("asList", asList);
		modelAndView.setViewName("approveAs");
		
		
		return modelAndView;
		
	}
	
	@RequestMapping("/searchAsManager.action")
	public ModelAndView searchAsManager(String as_id) throws Exception {
		
		
		List<AsCustom> asList = new ArrayList<AsCustom>();
		asList = asService.queryAsList();
		for(int i=0; i<asList.size(); i++) {
			if(asList.get(i).getStatus()!=0) {
				asList.remove(i);
				i--;
			}
		}
		if(as_id.equals("")==false) {
		for(int i=0; i<asList.size(); i++) {
			if(asList.get(i).getId().equals(as_id)) {
				continue;
			}else {
				asList.remove(i);
				i--;
			}
		}
		}
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("asList", asList);
		modelAndView.setViewName("approveAs");
		
		
		return modelAndView;
		
	}
	
	@RequestMapping("/submitASRegister.action")
	public ModelAndView submitASRegister(String approve, String refuse) throws Exception {
		AsCustom asCustom = new AsCustom();
		ModelAndView modelAndView = new ModelAndView();
		if(approve!=null) {
			asCustom = asService.findAsById(approve);
			asCustom.setStatus(2);
			asService.updateAsByID(asCustom);
			modelAndView.addObject("flagapprove", "1");
		}
		else if(refuse!=null) {
			asService.deleteAsByid(refuse);
			modelAndView.addObject("flagrefuse", "1");
		}
		
		
		List<AsCustom> asList = new ArrayList<AsCustom>();
		asList = asService.queryAsList();
		for(int i=0; i<asList.size(); i++) {
			if(asList.get(i).getStatus()!=0) {
				asList.remove(i);
				i--;
			}
		}
		
		
		
		
		
		
		modelAndView.addObject("asList", asList);
		modelAndView.setViewName("approveAs");
		
		
		return modelAndView;
		
	}
	
	
	@RequestMapping("/approveActivity.action")
	public ModelAndView approveActivity() throws Exception {
		
		List<ActivityCustom> activityList = new ArrayList<ActivityCustom>();
		activityList = activityService.queryActivityList();
		
		
		for(int i=0; i<activityList.size(); i++) {
			if(activityList.get(i).getStatus()!=0) {
				activityList.remove(i);
				i--;
			}else {
				AsCustom asCustom = new AsCustom();
				asCustom = asService.findAsById(activityList.get(i).getAsid());
				activityList.get(i).setAs_name(asCustom.getAsname());
			}
		}
		
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("activityList", activityList);
		modelAndView.setViewName("approveActivity");
		
		
		return modelAndView;
	}
	
	@RequestMapping("/searchActivityManager.action")
	public ModelAndView searchActivityManager(String activity_id) throws Exception {
		
		
		List<ActivityCustom> activityList = new ArrayList<ActivityCustom>();
		activityList = activityService.queryActivityList();
		
		
		for(int i=0; i<activityList.size(); i++) {
			if(activityList.get(i).getStatus()!=0) {
				activityList.remove(i);
				i--;
			}else {
				AsCustom asCustom = new AsCustom();
				asCustom = asService.findAsById(activityList.get(i).getAsid());
				activityList.get(i).setAs_name(asCustom.getAsname());
			}
		}
		
		if(activity_id.equals("")==false) {
			for(int i=0; i<activityList.size(); i++) {
				if(activityList.get(i).getId().equals(activity_id)) {
					continue;
				}else {
					activityList.remove(i);
					i--;
				}
			}
			}
		
		
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("activityList", activityList);
		modelAndView.setViewName("approveActivity");
		
		
		return modelAndView;
		
	}
	
	@RequestMapping("/submitActivityRegister.action")
	public ModelAndView submitActivityRegister(String approve, String refuse) throws Exception {
		ActivityCustom activityCustom = new ActivityCustom();
		List<ActivityCustom> activityList = new ArrayList<ActivityCustom>();
		activityList = activityService.queryActivityList();
		ModelAndView modelAndView = new ModelAndView();
		if(approve!=null) {
			for(int i=0; i<activityList.size(); i++) {
				if(activityList.get(i).getId().equals(approve))
				{
					activityCustom = activityList.get(i);
				}
			}
			activityCustom.setStatus(1);
			activityService.updateActivityByID(activityCustom);
			modelAndView.addObject("flagapprove", "1");
		}
		else if(refuse!=null) {
			activityService.deleteActivityByid(refuse);
			modelAndView.addObject("flagrefuse", "1");
		}
		
		
		activityList = activityService.queryActivityList();

		for(int i=0; i<activityList.size(); i++) {
			if(activityList.get(i).getStatus()!=0) {
				activityList.remove(i);
				i--;
			}else {
				AsCustom asCustom = new AsCustom();
				asCustom = asService.findAsById(activityList.get(i).getAsid());
				activityList.get(i).setAs_name(asCustom.getAsname());
			}
		}
		
		
		
		
		
		
		modelAndView.addObject("activityList", activityList);
		modelAndView.setViewName("approveActivity");
		
		
		return modelAndView;
		
	}
	
}
