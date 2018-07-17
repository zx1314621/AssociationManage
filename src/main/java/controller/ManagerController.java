package controller;


import java.rmi.Remote;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	//社团注册
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
	
	
	//活动发起
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
	
	//社团修改
	@RequestMapping("/managerAsChange.action")
	public ModelAndView managerAsChange() throws Exception {
		
		List<AsCustom> asList = new ArrayList<AsCustom>();
		asList = asService.queryAsList();
		for(int i=0; i<asList.size(); i++) {
			if(asList.get(i).getStatus()!=1) {
				asList.remove(i);
				i--;
			}else {
				String pp[] = asList.get(i).getPassword().split(",");
				String as[] = asList.get(i).getAsname().split(",");
				String ty[] = asList.get(i).getType().split(",");
				String na[] = asList.get(i).getName().split(",");
				asList.get(i).setPassword(pp[1]);
				asList.get(i).setAsname(as[1]);
				asList.get(i).setType(ty[1]);
				asList.get(i).setName(na[1]);		
			}
		}
		
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("asList",asList);
		
		modelandview.setViewName("managerAsChange");
		
		return modelandview;

					
	}
	
	@RequestMapping("/searchAsChange.action")
	public ModelAndView searchAsChange(String as_id) throws Exception {
		

		List<AsCustom> asList = new ArrayList<AsCustom>();
		asList = asService.queryAsList();
		for(int i=0; i<asList.size(); i++) {
			if(asList.get(i).getStatus()!=1) {
				asList.remove(i);
				i--;
			}else {
				String pp[] = asList.get(i).getPassword().split(",");
				String as[] = asList.get(i).getAsname().split(",");
				String ty[] = asList.get(i).getType().split(",");
				String na[] = asList.get(i).getName().split(",");
				asList.get(i).setPassword(pp[1]);
				asList.get(i).setAsname(as[1]);
				asList.get(i).setType(ty[1]);
				asList.get(i).setName(na[1]);
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
		modelAndView.setViewName("managerAsChange");
		return modelAndView;

					
	}

	@RequestMapping("/submitASChange.action")
	public ModelAndView submitASChange(String approve, String refuse) throws Exception {
		AsCustom asCustom = new AsCustom();
		ModelAndView modelAndView = new ModelAndView();
		if(approve!=null) {
			asCustom = asService.findAsById(approve);
			asCustom.setStatus(2);
			String pp[] = asCustom.getPassword().split(",");
			String as[] = asCustom.getAsname().split(",");
			String ty[] = asCustom.getType().split(",");
			String na[] = asCustom.getName().split(",");
			asCustom.setPassword(pp[1]);
			asCustom.setAsname(as[1]);
			asCustom.setType(ty[1]);
			asCustom.setName(na[1]);	
			asService.updateAsByID(asCustom);
			modelAndView.addObject("flagapprove", "1");
		}
		else if(refuse!=null) {
			asCustom = asService.findAsById(refuse);
			asCustom.setStatus(2);
			String pp[] = asCustom.getPassword().split(",");
			String as[] = asCustom.getAsname().split(",");
			String ty[] = asCustom.getType().split(",");
			String na[] = asCustom.getName().split(",");
			asCustom.setPassword(pp[0]);
			asCustom.setAsname(as[0]);
			asCustom.setType(ty[0]);
			asCustom.setName(na[0]);	
			asService.updateAsByID(asCustom);
			modelAndView.addObject("flagrefuse", "1");
		}
		
		
		List<AsCustom> asList = new ArrayList<AsCustom>();
		asList = asService.queryAsList();
		for(int i=0; i<asList.size(); i++) {
			if(asList.get(i).getStatus()!=1) {
				asList.remove(i);
				i--;
			}
		}
		
		
		
		
		
		
		modelAndView.addObject("asList", asList);
		modelAndView.setViewName("managerAsChange");
		
		
		return modelAndView;
		
	}
	
	
	//活动修改
	@RequestMapping("/managerActivityChange.action")
    public ModelAndView managerActivityChange() throws Exception {
		
		List<ActivityCustom> activityList = new ArrayList<ActivityCustom>();
		activityList = activityService.queryActivityList();
		
		
		for(int i=0; i<activityList.size(); i++) {
			if(activityList.get(i).getStatus()!=3) {
				activityList.remove(i);
				i--;
			}else {
				AsCustom asCustom = new AsCustom();
				asCustom = asService.findAsById(activityList.get(i).getAsid());
				if(asCustom.getStatus() == 1) {
					String []aa = asCustom.getAsname().split(",");
					asCustom.setAsname(aa[0]);
				}
				activityList.get(i).setAs_name(asCustom.getAsname());
				String [] name = activityList.get(i).getName().split(",");
				String [] day = activityList.get(i).getDay().split(",");
				String [] start = activityList.get(i).getStart_time().split(",");
				String [] end = activityList.get(i).getEnd_time().split(",");
				String [] place = activityList.get(i).getPlace().split(",");
				activityList.get(i).setName(name[1]);
				activityList.get(i).setDay(day[1]);
				activityList.get(i).setStart_time(start[1]);
				activityList.get(i).setEnd_time(end[1]);
				activityList.get(i).setPlace(place[1]);
			}
		}
		
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("activityList", activityList);
		modelAndView.setViewName("managerActivityChange");
		
		
		return modelAndView;
	}
	
	@RequestMapping("/searchActivityChange.action")
	public ModelAndView searchActivityChange(String activity_id) throws Exception {
		
		
		List<ActivityCustom> activityList = new ArrayList<ActivityCustom>();
		activityList = activityService.queryActivityList();
		
		
		for(int i=0; i<activityList.size(); i++) {
			if(activityList.get(i).getStatus()!=3) {
				activityList.remove(i);
				i--;
			}else {
				AsCustom asCustom = new AsCustom();
				asCustom = asService.findAsById(activityList.get(i).getAsid());
				if(asCustom.getStatus() == 1) {
					String []aa = asCustom.getAsname().split(",");
					asCustom.setAsname(aa[0]);
				}
				activityList.get(i).setAs_name(asCustom.getAsname());
				String [] name = activityList.get(i).getName().split(",");
				String [] day = activityList.get(i).getDay().split(",");
				String [] start = activityList.get(i).getStart_time().split(",");
				String [] end = activityList.get(i).getEnd_time().split(",");
				String [] place = activityList.get(i).getPlace().split(",");
				activityList.get(i).setName(name[1]);
				activityList.get(i).setDay(day[1]);
				activityList.get(i).setStart_time(start[1]);
				activityList.get(i).setEnd_time(end[1]);
				activityList.get(i).setPlace(place[1]);
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
		modelAndView.setViewName("managerActivityChange");
		
		
		return modelAndView;
		
	}
	
	@RequestMapping("/submitActivityChange.action")
	public ModelAndView submitActivityChange(String approve, String refuse) throws Exception {
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
			String [] name = activityCustom.getName().split(",");
			String [] day = activityCustom.getDay().split(",");
			String [] start = activityCustom.getStart_time().split(",");
			String [] end = activityCustom.getEnd_time().split(",");
			String [] place = activityCustom.getPlace().split(",");
			activityCustom.setName(name[1]);
			activityCustom.setDay(day[1]);
			activityCustom.setStart_time(start[1]);
			activityCustom.setEnd_time(end[1]);
			activityCustom.setPlace(place[1]);
			activityService.updateActivityByID(activityCustom);
			modelAndView.addObject("flagapprove", "1");
		}
		else if(refuse!=null) {
			
			for(int i=0; i<activityList.size(); i++) {
				if(activityList.get(i).getId().equals(refuse))
				{
					activityCustom = activityList.get(i);
				}
			}
		    
			activityCustom.setStatus(1);
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
			activityService.updateActivityByID(activityCustom);
			modelAndView.addObject("flagrefuse", "1");
		}
		
		
		activityList = activityService.queryActivityList();

		for(int i=0; i<activityList.size(); i++) {
			if(activityList.get(i).getStatus()!=3) {
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
	
	
	//展示AS
	@RequestMapping("/managerShowAs.action")
    public ModelAndView managerShowAs() throws Exception {
		//获取系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
		String date = df.format(new Date());
		String[] aa = date.split("/");
		
		//拿出每个社团的活动
		List<AsCustom> asList = new ArrayList<AsCustom>();
		asList = asService.queryAsList();		
		for(int i=0; i<asList.size(); i++) {
			if(asList.get(i).getStatus() == 1) {
				String pp[] = asList.get(i).getPassword().split(",");
				String as[] = asList.get(i).getAsname().split(",");
				String ty[] = asList.get(i).getType().split(",");
				String na[] = asList.get(i).getName().split(",");
				asList.get(i).setPassword(pp[0]);
				asList.get(i).setAsname(as[0]);
				asList.get(i).setType(ty[0]);
				asList.get(i).setName(na[0]);
			}
			asList.get(i).setActivestatus("1");
			
			
			List<ActivityCustom> activityList = new ArrayList<ActivityCustom>();
			activityList = activityService.queryActivityList();
			for(int j=0; j<activityList.size(); j++) {
				if((activityList.get(j).getAsid()).equals(asList.get(i).getId())) {
					continue;
				}else {
					activityList.remove(j);
					j--;
				}
			}
			
			//选出该社团最近的活动
			ActivityCustom activityCustom = new ActivityCustom();
			if(activityList.size() == 0) {
				continue;
			}
			activityCustom = activityList.get(0);
			for(int j=0; j<activityList.size(); j++) {
				String[] day1 = activityCustom.getDay().split("/");
				String[] day2 = activityList.get(j).getDay().split("/");
				int year1 = Integer.valueOf(day1[0]);
				int year2 = Integer.valueOf(day2[0]);
				int month1 = Integer.valueOf(day1[1]);
				int month2 = Integer.valueOf(day2[1]);
				if(year1<year2) {
					activityCustom = activityList.get(j);
				}else if(year1 == year2){
					if(month1<month2) {
						activityCustom = activityList.get(j);
					}
				}
				
			}			
			
			//比较它是否为不活跃状态		
				String[] bb = activityCustom.getDay().split("/");
				int year_now = Integer.valueOf(aa[0]);
				int year_activity = Integer.valueOf(bb[0]);
				int month_now = Integer.valueOf(aa[1]);
				int month_activity = Integer.valueOf(bb[1]);
				if(year_now>year_activity) {
					month_now = month_now + 12;
					if(month_now > (month_activity+6)) {
						asList.get(i).setActivestatus("0");
					}
				}else if(year_now==year_activity) {
					if(month_now > (month_activity+6)) {
						asList.get(i).setActivestatus("0");
					}
				}
		}
		
								
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.addObject("asList", asList);
		modelAndView.setViewName("managerShowAs");	
		return modelAndView;
	}
		
	@RequestMapping("/searchAsShow.action")
	public ModelAndView searchAsShow(String as_id) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
		String date = df.format(new Date());
		String[] aa = date.split("/");
		
		//拿出每个社团的活动
		List<AsCustom> asList = new ArrayList<AsCustom>();
		asList = asService.queryAsList();		
		for(int i=0; i<asList.size(); i++) {
			asList.get(i).setActivestatus("1");
			if(asList.get(i).getStatus() == 1) {
				String pp[] = asList.get(i).getPassword().split(",");
				String as[] = asList.get(i).getAsname().split(",");
				String ty[] = asList.get(i).getType().split(",");
				String na[] = asList.get(i).getName().split(",");
				asList.get(i).setPassword(pp[0]);
				asList.get(i).setAsname(as[0]);
				asList.get(i).setType(ty[0]);
				asList.get(i).setName(na[0]);
			}
			
			List<ActivityCustom> activityList = new ArrayList<ActivityCustom>();
			activityList = activityService.queryActivityList();
			for(int j=0; j<activityList.size(); j++) {
				if((activityList.get(j).getAsid()).equals(asList.get(i).getId())) {
					continue;
				}else {
					activityList.remove(j);
					j--;
				}
			}
			
			//选出该社团最近的活动
			ActivityCustom activityCustom = new ActivityCustom();
			if(activityList.size() == 0) {
				continue;
			}
			activityCustom = activityList.get(0);
			for(int j=0; j<activityList.size(); j++) {
				String[] day1 = activityCustom.getDay().split("/");
				String[] day2 = activityList.get(j).getDay().split("/");
				int year1 = Integer.valueOf(day1[0]);
				int year2 = Integer.valueOf(day2[0]);
				int month1 = Integer.valueOf(day1[1]);
				int month2 = Integer.valueOf(day2[1]);
				if(year1<year2) {
					activityCustom = activityList.get(j);
				}else if(year1 == year2){
					if(month1<month2) {
						activityCustom = activityList.get(j);
					}
				}
				
			}			
			
			//比较它是否为不活跃状态		
				String[] bb = activityCustom.getDay().split("/");
				int year_now = Integer.valueOf(aa[0]);
				int year_activity = Integer.valueOf(bb[0]);
				int month_now = Integer.valueOf(aa[1]);
				int month_activity = Integer.valueOf(bb[1]);
				if(year_now>year_activity) {
					month_now = month_now + 12;
					if(month_now > (month_activity+6)) {
						asList.get(i).setActivestatus("0");
					}
				}else if(year_now==year_activity) {
					if(month_now > (month_activity+6)) {
						asList.get(i).setActivestatus("0");
					}
				}
		}
		if(as_id!="")
		{for(int i=0; i<asList.size(); i++) {
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
		modelAndView.setViewName("managerShowAs");	
		return modelAndView;	
	}
	
	@RequestMapping("/deleteAsShow.action")
	public ModelAndView deleteAsShow(String delete) throws Exception {
		
		AsCustom asCustom = new AsCustom();
		ModelAndView modelAndView = new ModelAndView();
	
		if(delete!=null) {
			asService.deleteAsByid(delete);
			modelAndView.addObject("flagrefuse", "1");
		}
		
		
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
		String date = df.format(new Date());
		String[] aa = date.split("/");
		
		//拿出每个社团的活动
		List<AsCustom> asList = new ArrayList<AsCustom>();
		asList = asService.queryAsList();		
		for(int i=0; i<asList.size(); i++) {
			asList.get(i).setActivestatus("1");
			
			
			List<ActivityCustom> activityList = new ArrayList<ActivityCustom>();
			activityList = activityService.queryActivityList();
			for(int j=0; j<activityList.size(); j++) {
				if((activityList.get(j).getAsid()).equals(asList.get(i).getId())) {
					continue;
				}else {
					activityList.remove(j);
					j--;
				}
			}
			
			//选出该社团最近的活动
			ActivityCustom activityCustom = new ActivityCustom();
			if(activityList.size() == 0) {
				continue;
			}
			activityCustom = activityList.get(0);
			for(int j=0; j<activityList.size(); j++) {
				String[] day1 = activityCustom.getDay().split("/");
				String[] day2 = activityList.get(j).getDay().split("/");
				int year1 = Integer.valueOf(day1[0]);
				int year2 = Integer.valueOf(day2[0]);
				int month1 = Integer.valueOf(day1[1]);
				int month2 = Integer.valueOf(day2[1]);
				if(year1<year2) {
					activityCustom = activityList.get(j);
				}else if(year1 == year2){
					if(month1<month2) {
						activityCustom = activityList.get(j);
					}
				}
				
			}			
			
			//比较它是否为不活跃状态		
				String[] bb = activityCustom.getDay().split("/");
				int year_now = Integer.valueOf(aa[0]);
				int year_activity = Integer.valueOf(bb[0]);
				int month_now = Integer.valueOf(aa[1]);
				int month_activity = Integer.valueOf(bb[1]);
				if(year_now>year_activity) {
					month_now = month_now + 12;
					if(month_now > (month_activity+6)) {
						asList.get(i).setActivestatus("0");
					}
				}else if(year_now==year_activity) {
					if(month_now > (month_activity+6)) {
						asList.get(i).setActivestatus("0");
					}
				}
		}
		
		
		
		
		
		
		modelAndView.addObject("asList", asList);
		modelAndView.setViewName("managerShowAs");
		
		
		return modelAndView;
	}


	@RequestMapping("/managerShowActivity.action")
	public ModelAndView managerShowActivity() throws Exception {
		
		List<ActivityCustom> activityList = new ArrayList<ActivityCustom>();
		activityList = activityService.queryActivityList();
		
		for(int i=0; i<activityList.size(); i++)
		{
		 AsCustom asCustom = new AsCustom();
		 asCustom = asService.findAsById(activityList.get(i).getAsid());
		 if(asCustom.getStatus() == 1) {
			 String aa[] = asCustom.getAsname().split(",");
			 asCustom.setAsname(aa[0]);
		 }
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
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("activityList", activityList);
		modelAndView.setViewName("managerShowActivity");
		
		
		return modelAndView;
	}
	
	@RequestMapping("/managerShowActivitysearch.action")
	public ModelAndView managerShowActivitysearch(String activity_id) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<ActivityCustom> activityList = new ArrayList<ActivityCustom>();
		activityList = activityService.queryActivityList();
		for(int i=0; i<activityList.size(); i++)
		{
			 AsCustom asCustom = new AsCustom();
		     asCustom = asService.findAsById(activityList.get(i).getAsid());
		     if(asCustom.getStatus() == 1) {
				 String aa[] = asCustom.getAsname().split(",");
				 asCustom.setAsname(aa[0]);
			 }
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
		if(activity_id.equals("") == false) {			
		for(int i=0; i<activityList.size(); i++)
		{
		 if(activityList.get(i).getId().equals(activity_id)) {
			 continue;
		 }else {
			 activityList.remove(i);
			 i--;
		 }
		}
		}
		
		
		
		modelAndView.addObject("activityList", activityList);
		modelAndView.setViewName("managerShowActivity");
		
		
		return modelAndView;
	}

	
	@RequestMapping("/managerShowActivitydelete.action")
	public ModelAndView managerShowActivitydelete(String delete) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		if(delete != null) {
			activityService.deleteActivityByid(delete);
			modelAndView.addObject("flagrefuse", "1");
		}
		
		List<ActivityCustom> activityList = new ArrayList<ActivityCustom>();
		activityList = activityService.queryActivityList();
		
		for(int i=0; i<activityList.size(); i++)
		{
		 AsCustom asCustom = new AsCustom();
		 asCustom = asService.findAsById(activityList.get(i).getAsid());
		 activityList.get(i).setAs_name(asCustom.getAsname());
		}
		
		
		
		modelAndView.addObject("activityList", activityList);
		modelAndView.setViewName("managerShowActivity");
		
		
		return modelAndView;
	}



}
