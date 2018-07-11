package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import po.ActivityCustom;
import po.AsCustom;
import service.ActivityService;
import service.AsService;


@Controller
@RequestMapping("/jsp")
public class AsController {
	
	@Autowired
	public AsService asService;
	@Autowired
	public ActivityService activityService;

	@RequestMapping("/queryAs.action")
	public ModelAndView queryAs() throws Exception {
		
		List<AsCustom> asList = asService.queryAsList();
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("asList",asList);
		
		modelandview.setViewName("index");
		
		return modelandview;
	}
	
	@RequestMapping("/queryActivity.action")
	public ModelAndView queryActivity(HttpSession session,String activity_id,String as_name,String add) throws Exception {
		
		String as_id = (String) session.getAttribute("as_id");
		if(as_id==null&&add!=null) {
			ModelAndView modelandview1 = new ModelAndView();
			String flag1 = "1";
			modelandview1.addObject("flag1",flag1);
			modelandview1.setViewName("choose");
			
			return modelandview1;
			
		}
		
		
		if(add!=null&&add.equals("1")) {
        	String activity_id1 = "";
    		String random1 = String.valueOf((int)(1+Math.random()*(9-0+1)));
    		String random2 = String.valueOf((int)(1+Math.random()*(9-0+1)));
    		String random3 = String.valueOf((int)(1+Math.random()*(9-0+1)));
    		activity_id1 = "a"+random1+random2+random3;
    		ModelAndView modelandview = new ModelAndView();
    		modelandview.addObject("activity_id",activity_id1);
    		
    		modelandview.setViewName("launchactivity");
    		
    		return modelandview;
		}
		List<ActivityCustom> activityList = activityService.queryActivityList();
		for(int i=0; i<activityList.size(); i++) {
			AsCustom asCustom = new AsCustom();
			asCustom = asService.findAsById(activityList.get(i).getAsid());
			activityList.get(i).setAs_name(asCustom.getAsname());
		}
        if(activity_id!="") {
        	for(int i=0; i<activityList.size(); i++) {
        		if(activityList.get(i).getId().equals(activity_id)) {
        			continue;
        		}else {
        			activityList.remove(i);
        			i--;
        		}
        	}
			
		}
		if(as_name!="") {
			for(int i=0; i<activityList.size(); i++) {
        		if(activityList.get(i).getAs_name().equals(as_name)) {
        			continue;
        		}else {
        			activityList.remove(i);
        			i--;
        		}
        	}
			
		}
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("activityList",activityList);
		
		modelandview.setViewName("choose");
		
		return modelandview;
	}
	
	@RequestMapping("/launchActivity.action")
	public ModelAndView launchActivity(HttpSession session,String check,String activity_id,String activity_name,String day,String start_time,String end_time, String place) throws Exception {
		
		if(check!=null&&check.equals("123")){
			List<ActivityCustom> activityList = new ArrayList<ActivityCustom>();
			activityList = activityService.queryActivityList();
			String enable = "1";
			for(int i=0; i<activityList.size(); i++) {
				if(activityList.get(i).getDay().equals(day)&&activityList.get(i).getPlace().equals(place)) {
					int time1 = Integer.valueOf(activityList.get(i).getStart_time());
					int time2 = Integer.valueOf(start_time);
					int time3 = Integer.valueOf(activityList.get(i).getEnd_time());
					int time4 = Integer.valueOf(end_time);
					if(time1<time2&&time4<time3) {
						enable = "0";
					}
				}
			}
			ModelAndView modelandview = new ModelAndView();
			modelandview.addObject("enable",enable);
			
			modelandview.setViewName("launchactivity");
			
			return modelandview;
		}
		ActivityCustom activityCustom = new ActivityCustom();
		activityCustom.setId(activity_id);
		activityCustom.setName(activity_name);
		activityCustom.setDay(day);
		String[] aa = start_time.split(":");
		String[] bb = end_time.split(":");
		activityCustom.setStart_time(aa[0]);
		 
		activityCustom.setEnd_time(bb[0]);
		activityCustom.setPlace(place);
		String as_id = (String) session.getAttribute("as_id");
		activityCustom.setAsid(as_id);
		activityCustom.setStatus(0);
		activityService.addActivity(activityCustom);
		ModelAndView modelandview = new ModelAndView();
		
		modelandview.setViewName("success");
		
		return modelandview;
	}
	
	
	@RequestMapping("/signin.action")
	public ModelAndView signin(HttpSession session,String id, String password) throws Exception
	{
		session.setAttribute("f1", 1);
		session.setAttribute("f2", 1);
		session.setAttribute("f3", 1);
		session.setAttribute("f4", 1);
		session.setAttribute("f5", 1);
		session.setAttribute("f6", 1);
		session.setAttribute("f7", 1);
		session.setAttribute("f8", 1);
		AsCustom asCustom = asService.findAsById(id);
		if(asCustom==null||asCustom.getStatus()==0)
		{
			ModelAndView modelandview = new ModelAndView();
			modelandview.setViewName("signupfailed");
			return modelandview;
		}
		
		if(asCustom.getPassword().equals(password)) {
			
			int flag = 1;
			session.setAttribute("flag", flag); 
			session.setAttribute("as_id",asCustom.getId());
			ModelAndView modelandview = new ModelAndView();
			modelandview.setViewName("signinsuccess");
			return modelandview;
		}else {
			ModelAndView modelandview = new ModelAndView();
			modelandview.setViewName("signinfailed");
			return modelandview;
		}			
	}
	@RequestMapping("/signup.action")
	public ModelAndView signup(HttpSession session,String as_id, String password,String as_name,String as_type,String people_name) throws Exception
	{
		session.setAttribute("f1", 1);
		session.setAttribute("f2", 1);
		session.setAttribute("f3", 1);
		session.setAttribute("f4", 1);
		session.setAttribute("f5", 1);
		session.setAttribute("f6", 1);
		session.setAttribute("f7", 1);
		session.setAttribute("f8", 1);
		AsCustom asCustom = new AsCustom();
		asCustom.setId(as_id);
		asCustom.setPassword(password);
		asCustom.setAsname(as_name);
		asCustom.setType(as_type);
		asCustom.setName(people_name);
		asCustom.setStatus(0);
		asService.addAs(asCustom);
		
		session.setAttribute("as_id",asCustom.getId());
		session.setAttribute("flag", 1); 
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("signupsuccess");
		return modelandview;
		
	}
}
