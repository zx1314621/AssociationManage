package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.core.appender.rewrite.MapRewritePolicy.Mode;
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
			
			if(activityList.get(i).getStatus()==3) {
				String [] name = activityList.get(i).getName().split(",");
				String [] day = activityList.get(i).getDay().split(",");
				String [] start = activityList.get(i).getStart_time().split(",");
				String [] end = activityList.get(i).getEnd_time().split(",");
				String [] place = activityList.get(i).getPlace().split(",");
				activityList.get(i).setName(name[0]);
				activityList.get(i).setDay(day[0]);
				activityList.get(i).setStart_time(start[0]);
				activityList.get(i).setEnd_time(end[0]);
				activityList.get(i).setPlace(place[0]);
			}
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
		
		String[] aa = start_time.split(":");
		String[] bb = end_time.split(":");
		
		
		//点击检测按钮
		if(check!=null&&check.equals("123")){
			List<ActivityCustom> activityList = new ArrayList<ActivityCustom>();
			activityList = activityService.queryActivityList();
			String enable = "1";
			for(int i=0; i<activityList.size(); i++) {
				if(activityList.get(i).getDay().equals(day)&&activityList.get(i).getPlace().equals(place)) {
					int time1 = Integer.valueOf(activityList.get(i).getStart_time());
					int time2 = Integer.valueOf(aa[0]);
					int time3 = Integer.valueOf(activityList.get(i).getEnd_time());
					int time4 = Integer.valueOf(bb[0]);
					for(int j=time2; j<=time4; j++) {
						if(time1<j&&j<time3)
							enable = "0";
					}
				}
			}
			
			ModelAndView modelandview = new ModelAndView();
			if(enable.equals("0")) {
				session.setAttribute("flag8", "0");
			}
			if(enable.equals("1")) {
				session.setAttribute("flag8", "1");
			}
			session.setAttribute("enableflag", "1");
			modelandview.addObject("enable",enable);
			modelandview.addObject("activity_id",activity_id);
			modelandview.addObject("flagname",1);
			modelandview.addObject("activity_name",activity_name);
			modelandview.addObject("day",day);
			modelandview.setViewName("launchactivity");
			
			//保存活动室选择信息
			
			if(place.equals("活动室1")) {
				modelandview.addObject("flagplace",1);
			}else if(place.equals("活动室2")) {
				modelandview.addObject("flagplace",2);
			}else if(place.equals("活动室3")) {
				modelandview.addObject("flagplace",3);
			}else if(place.equals("活动室4")) {
				modelandview.addObject("flagplace",4);
			}else if(place.equals("活动室5")) {
				modelandview.addObject("flagplace",5);
			}else if(place.equals("活动室6")) {
				modelandview.addObject("flagplace",6);
			}else if(place.equals("活动室7")) {
				modelandview.addObject("flagplace",7);
			}else if(place.equals("活动室8")) {
				modelandview.addObject("flagplace",8);
			}
			
			//保持出发日期信息
			modelandview.addObject("flagstart",aa[0]);
			modelandview.addObject("flagend",bb[0]);
			return modelandview;
		}
		
		//检测之后，若检测失败
		String flag8 = String.valueOf(session.getAttribute("flag8")) ;
		if(flag8==null||flag8.equals("0")) {
			ModelAndView modelandview = new ModelAndView();
			modelandview.addObject("activity_id",activity_id);
			modelandview.addObject("flagname",1);
			modelandview.addObject("flag8",flag8);
			modelandview.addObject("activity_name",activity_name);
			modelandview.addObject("day",day);
			modelandview.setViewName("launchactivity");
			
			//保存活动室选择信息
			if(place.equals("活动室1")) {
				modelandview.addObject("flagplace",1);
			}else if(place.equals("活动室2")) {
				modelandview.addObject("flagplace",2);
			}else if(place.equals("活动室3")) {
				modelandview.addObject("flagplace",3);
			}else if(place.equals("活动室4")) {
				modelandview.addObject("flagplace",4);
			}else if(place.equals("活动室5")) {
				modelandview.addObject("flagplace",5);
			}else if(place.equals("活动室6")) {
				modelandview.addObject("flagplace",6);
			}else if(place.equals("活动室7")) {
				modelandview.addObject("flagplace",7);
			}else if(place.equals("活动室8")) {
				modelandview.addObject("flagplace",8);
			}
			
			//保持出发日期信息
			modelandview.addObject("flagstart",aa[0]);
			modelandview.addObject("flagend",bb[0]);
			return modelandview;
		}
		
		//判断是否检测且是否检测成功
		String enableflag = String.valueOf(session.getAttribute("enableflag"));
		if(enableflag.equals("1")&&flag8.equals("1"))
		{ActivityCustom activityCustom = new ActivityCustom();
		activityCustom.setId(activity_id);
		activityCustom.setName(activity_name);
		activityCustom.setDay(day);
		
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
		}else {
			ModelAndView modelandview = new ModelAndView();
			modelandview.addObject("activity_id",activity_id);
			modelandview.addObject("flagname",1);
			modelandview.addObject("enableflag",enableflag);
			modelandview.addObject("activity_name",activity_name);
			modelandview.addObject("day",day);
			modelandview.setViewName("launchactivity");
			
			//保存活动室选择信息
			if(place.equals("活动室1")) {
				modelandview.addObject("flagplace",1);
			}else if(place.equals("活动室2")) {
				modelandview.addObject("flagplace",2);
			}else if(place.equals("活动室3")) {
				modelandview.addObject("flagplace",3);
			}else if(place.equals("活动室4")) {
				modelandview.addObject("flagplace",4);
			}else if(place.equals("活动室5")) {
				modelandview.addObject("flagplace",5);
			}else if(place.equals("活动室6")) {
				modelandview.addObject("flagplace",6);
			}else if(place.equals("活动室7")) {
				modelandview.addObject("flagplace",7);
			}else if(place.equals("活动室8")) {
				modelandview.addObject("flagplace",8);
			}
			
			//保持出发日期信息
			modelandview.addObject("flagstart",aa[0]);
			modelandview.addObject("flagend",bb[0]);
			return modelandview;
		}
	}
		
	@RequestMapping("/signin.action")
	public ModelAndView signin(HttpSession session,String id, String password) throws Exception
	{
		
		session.setAttribute("enableflag", "0");
		AsCustom asCustom = asService.findAsById(id);
		if(asCustom==null||asCustom.getStatus()==0)
		{
			ModelAndView modelandview = new ModelAndView();
			modelandview.setViewName("signupfailed");
			return modelandview;
		}
		if(asCustom.getStatus()==1)
		{
			String aa[] = asCustom.getPassword().split(",");
			asCustom.setPassword(aa[0]);
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
		session.setAttribute("enableflag", "0");
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

	@RequestMapping("/queryOwnActivity.action")
	public ModelAndView queryOwnActivity(HttpSession session) throws Exception {
		
		List<ActivityCustom> activityList = activityService.queryActivityList();
		AsCustom asCustom  = new AsCustom();
		
		String as_id = (String) session.getAttribute("as_id");
		asCustom = asService.findAsById(as_id);
		for(int i=0; i<activityList.size(); i++) {
			if(activityList.get(i).getAsid().equals(as_id)) {
				if(asCustom.getStatus()==1) {
					String[] aa = asCustom.getAsname().split(",");
					asCustom.setAsname(aa[0]);
				}
				activityList.get(i).setAs_name(asCustom.getAsname());
				if(activityList.get(i).getStatus()==0) {
					activityList.get(i).setActivity_status("还未审批");
				}
				if(activityList.get(i).getStatus()==1) {
					activityList.get(i).setActivity_status("还未完成");
				}
				if(activityList.get(i).getStatus()==2) {
					activityList.get(i).setActivity_status("已完成");
				}
				if(activityList.get(i).getStatus()==3) {
					activityList.get(i).setActivity_status("申请修改中");
					String [] name = activityList.get(i).getName().split(",");
					String [] day = activityList.get(i).getDay().split(",");
					String [] start = activityList.get(i).getStart_time().split(",");
					String [] end = activityList.get(i).getEnd_time().split(",");
					String [] place = activityList.get(i).getPlace().split(",");
					activityList.get(i).setName(name[0]);
					activityList.get(i).setDay(day[0]);
					activityList.get(i).setStart_time(start[0]);
					activityList.get(i).setEnd_time(end[0]);
					activityList.get(i).setPlace(place[0]);
				}
				if(activityList.get(i).getStatus()==4) {
					activityList.get(i).setActivity_status("申请取消中");
				}
				if(activityList.get(i).getStatus()==5) {
					activityList.get(i).setActivity_status("已取消");
				}
				continue;
			}else {
				activityList.remove(i);
				i--;
			}
		}
		
		
		
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("activityList",activityList);
		
		modelandview.setViewName("showorder");
		
		return modelandview;
	} 
	
	@RequestMapping("/editactivity.action")
	public ModelAndView editactivity(HttpSession session,String edit, String delete) throws Exception {
		
		if(delete!=null) {
			ModelAndView modelandview = new ModelAndView();
			List<ActivityCustom> activityList = activityService.queryActivityList();
			
		    ActivityCustom activityCustom = new ActivityCustom();
		    for(int i=0; i<activityList.size(); i++) {
		    	if(activityList.get(i).getId().equals(delete)) {
		    		activityCustom = activityList.get(i);
		    	}
		    }
		    
		    if(activityCustom.getStatus() == 3) {
		    	String [] name = activityCustom.getName().split(",");
				String [] day = activityCustom.getDay().split(",");
				String [] start = activityCustom.getStart_time().split(",");
				String [] end = activityCustom.getEnd_time().split(",");
				String [] place = activityCustom.getPlace().split(",");
				activityCustom.setName(name[0]);
				activityCustom.setDay(day[0]);
				activityCustom.setStart_time(start[0]);
				activityCustom.setEnd_time(end[0]);
				activityCustom.setPlace(place[0]);
				activityCustom.setStatus(1);
				activityService.updateActivityByID(activityCustom);
				modelandview.addObject("deleteflag","2");
		    }else {
		    	activityService.updatedeleteActivityStatusByID(delete);
		    	modelandview.addObject("deleteflag","1");
		    }
			
			
			AsCustom asCustom  = new AsCustom();
			
			String as_id = (String) session.getAttribute("as_id");
			asCustom = asService.findAsById(as_id);
			for(int i=0; i<activityList.size(); i++) {
				if(activityList.get(i).getAsid().equals(as_id)) {
					activityList.get(i).setAs_name(asCustom.getAsname());
					if(activityList.get(i).getStatus()==0) {
						activityList.get(i).setActivity_status("还未审批");
					}
					if(activityList.get(i).getStatus()==1) {
						activityList.get(i).setActivity_status("还未完成");
					}
					if(activityList.get(i).getStatus()==2) {
						activityList.get(i).setActivity_status("已完成");
					}
					if(activityList.get(i).getStatus()==3) {
						activityList.get(i).setActivity_status("申请修改中");
					}
					if(activityList.get(i).getStatus()==4) {
						activityList.get(i).setActivity_status("申请取消中");
					}
					if(activityList.get(i).getStatus()==5) {
						activityList.get(i).setActivity_status("已取消");
					}
					continue;
				}else {
					activityList.remove(i);
					i--;
				}
			}
			modelandview.addObject("activityList",activityList);
			
			modelandview.setViewName("showorder");
			
			return modelandview;
		}
		if(edit!=null) {
			ActivityCustom activityCustom = new ActivityCustom();
			List<ActivityCustom> activityList = activityService.queryActivityList();
			for(int i=0; i<activityList.size(); i++) {
				if(activityList.get(i).getId().equals(edit)) {
					activityCustom = activityList.get(i);
				}
			}
			ModelAndView modelandview = new ModelAndView();
			String[] aa = activityCustom.getStart_time().split(":");
			String[] bb = activityCustom.getEnd_time().split(":");
			String place = activityCustom.getPlace();
			if(place.equals("活动室1")) {
				modelandview.addObject("flagplace",1);
			}else if(place.equals("活动室2")) {
				modelandview.addObject("flagplace",2);
			}else if(place.equals("活动室3")) {
				modelandview.addObject("flagplace",3);
			}else if(place.equals("活动室4")) {
				modelandview.addObject("flagplace",4);
			}else if(place.equals("活动室5")) {
				modelandview.addObject("flagplace",5);
			}else if(place.equals("活动室6")) {
				modelandview.addObject("flagplace",6);
			}else if(place.equals("活动室7")) {
				modelandview.addObject("flagplace",7);
			}else if(place.equals("活动室8")) {
				modelandview.addObject("flagplace",8);
			}
			modelandview.addObject("activityCustom",activityCustom);
			modelandview.addObject("flagstart",aa[0]);
			modelandview.addObject("flagend",bb[0]);
			modelandview.setViewName("editActivity");
			
			return modelandview;
		}
		
		
		ModelAndView modelandview = new ModelAndView();
		/*modelandview.addObject("activityList",activityList);*/
		
		modelandview.setViewName("showorder");
		
		return modelandview;
	}
	
	@RequestMapping("/submitEdit.action")
	public ModelAndView submitEdit(String check,String activity_id, String activity_name, String day, String start_time, String end_time
			,String place) throws Exception {
		if(check!=null&&check.equals("123")){
			String[] aa = start_time.split(":");
			String[] bb = end_time.split(":");
			ActivityCustom activityCustom = new ActivityCustom();
			activityCustom.setId(activity_id);
			activityCustom.setName(activity_name);
			activityCustom.setDay(day);
			ModelAndView modelandView = new ModelAndView();
			if(place.equals("活动室1")) {
				modelandView.addObject("flagplace",1);
			}else if(place.equals("活动室2")) {
				modelandView.addObject("flagplace",2);
			}else if(place.equals("活动室3")) {
				modelandView.addObject("flagplace",3);
			}else if(place.equals("活动室4")) {
				modelandView.addObject("flagplace",4);
			}else if(place.equals("活动室5")) {
				modelandView.addObject("flagplace",5);
			}else if(place.equals("活动室6")) {
				modelandView.addObject("flagplace",6);
			}else if(place.equals("活动室7")) {
				modelandView.addObject("flagplace",7);
			}else if(place.equals("活动室8")) {
				modelandView.addObject("flagplace",8);
			}
			modelandView.addObject("activityCustom",activityCustom);
			modelandView.addObject("enable","1");
			modelandView.addObject("flagstart",aa[0]);
			modelandView.addObject("flagend",bb[0]);
			modelandView.setViewName("editActivity");
			return modelandView;
		}
		ActivityCustom activityCustom = new ActivityCustom();
		List<ActivityCustom> activityList = activityService.queryActivityList();
		for(int i=0; i<activityList.size(); i++) {
			if(activityList.get(i).getId().equals(activity_id)) {
				activityCustom = activityList.get(i);
			}
		}
		activityCustom.setName(activityCustom.getName() +","+ activity_name);
		activityCustom.setDay(activityCustom.getDay() +","+ day);
		String[] aa = start_time.split(":");
		String[] bb = end_time.split(":");
		activityCustom.setStart_time(activityCustom.getStart_time() +","+ aa[0]);
		activityCustom.setEnd_time(activityCustom.getEnd_time() +","+ bb[0]);
		activityCustom.setPlace(activityCustom.getPlace() +","+ place);
		activityCustom.setStatus(3);
		
		activityService.updateActivityByID(activityCustom);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editsuccess");
		return modelAndView;
		
	}


	@RequestMapping("/showdetail.action")
	public ModelAndView showdetail(String activityId) throws Exception {
		
		List<ActivityCustom> activityList = activityService.queryActivityList();
		ActivityCustom activityCustom = new ActivityCustom();
		for(int i=0; i<activityList.size(); i++) {
			if(activityList.get(i).getId().equals(activityId)) {
				activityCustom = activityList.get(i);
			}
		}
		String start_time = activityCustom.getStart_time();
		String end_time = activityCustom.getEnd_time();
		String[] aa = start_time.split(":");
		String[] bb = end_time.split(":");
		String place = activityCustom.getPlace();
		ModelAndView modelandview = new ModelAndView();
		if(place.equals("活动室1")) {
			modelandview.addObject("flagplace",1);
		}else if(place.equals("活动室2")) {
			modelandview.addObject("flagplace",2);
		}else if(place.equals("活动室3")) {
			modelandview.addObject("flagplace",3);
		}else if(place.equals("活动室4")) {
			modelandview.addObject("flagplace",4);
		}else if(place.equals("活动室5")) {
			modelandview.addObject("flagplace",5);
		}else if(place.equals("活动室6")) {
			modelandview.addObject("flagplace",6);
		}else if(place.equals("活动室7")) {
			modelandview.addObject("flagplace",7);
		}else if(place.equals("活动室8")) {
			modelandview.addObject("flagplace",8);
		}
		modelandview.addObject("activityCustom",activityCustom);
		modelandview.addObject("flagstart",aa[0]);
		modelandview.addObject("flagend",bb[0]);
		modelandview.setViewName("showdetail");
		
		return modelandview;
	}
	
	@RequestMapping("/queryOwnAs.action")
	public ModelAndView queryOwnAs(HttpSession session) throws Exception {
		
		String as_id = (String) session.getAttribute("as_id");
		AsCustom asCustom = new AsCustom();
		asCustom = asService.findAsById(as_id);
		if(asCustom.getStatus()==1) {
			String pp[] = asCustom.getPassword().split(",");
			String as[] = asCustom.getAsname().split(",");
			String ty[] = asCustom.getType().split(",");
			String na[] = asCustom.getName().split(",");
			asCustom.setPassword(pp[0]);
			asCustom.setAsname(as[0]);
			asCustom.setType(ty[0]);
			asCustom.setName(na[0]);
		}
		ModelAndView modelandview = new ModelAndView();
		if(asCustom.getType().equals("文化")) {
			modelandview.addObject("flag", 1);
		}else if(asCustom.getType().equals("兴趣")) {
			modelandview.addObject("flag", 2);
		}else if(asCustom.getType().equals("学术")) {
			modelandview.addObject("flag", 3);
		}else if(asCustom.getType().equals("艺术")) {
			modelandview.addObject("flag", 4);
		}
		
		session.setAttribute("changeAS", asCustom);
		modelandview.setViewName("queryOwnAs");
		
		return modelandview;
	}
	
	@RequestMapping("/submitAsChange.action")
	public ModelAndView submitAsChange(HttpSession session,String as_id,String password, String asname, String type, String name) throws Exception {
		
		AsCustom asCustom = new AsCustom();
		asCustom = (AsCustom) session.getAttribute("changeAS");
		
		asCustom.setPassword(asCustom.getPassword() + ","+ password);
		asCustom.setAsname(asCustom.getAsname() + "," + asname);
		asCustom.setType(asCustom.getType() + "," + type);
		asCustom.setName(asCustom.getName() + "," + name);
		//修改其修改待审批态
		asCustom.setStatus(1);
		asService.updateAsByID(asCustom);
		
		ModelAndView modelandview = new ModelAndView();
        modelandview.addObject("flagAsChange", "1");
		modelandview.setViewName("editsuccess");
		
		return modelandview;
	}
	
	@RequestMapping("/quit.action")
	public ModelAndView quit(HttpSession session) throws Exception {
		
		int flag = 2;
		session.setAttribute("flag", flag); 
		session.setAttribute("as_id",null);
		
		ModelAndView modelandview = new ModelAndView();
        modelandview.addObject("quit", "1");
		modelandview.setViewName("index");
		
		return modelandview;
	}
	
	
}
