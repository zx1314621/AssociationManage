package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public ModelAndView queryActivity() throws Exception {
		
		List<ActivityCustom> activityList = activityService.queryActivityList();
		for(int i=0; i<activityList.size(); i++) {
			AsCustom asCustom = new AsCustom();
			asCustom = asService.findAsById(activityList.get(i).getAsid());
			activityList.get(i).setAs_name(asCustom.getAsname());
		}
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("activityList",activityList);
		
		modelandview.setViewName("choose");
		
		return modelandview;
	}
	
	@RequestMapping("/signin.action")
	public ModelAndView signin(HttpSession session,String id, String password) throws Exception
	{
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
		AsCustom asCustom = new AsCustom();
		asCustom.setId(as_id);
		asCustom.setPassword(password);
		asCustom.setAsname(as_name);
		asCustom.setType(as_type);
		asCustom.setName(people_name);
		asCustom.setStatus(0);
		asService.addAs(asCustom);
		
		session.setAttribute("account_id",asCustom.getId());
		session.setAttribute("flag", 1); 
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("signupsuccess");
		return modelandview;
		
	}
}
