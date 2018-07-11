package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.AsCustom;


public interface AsService {
	
	//账户查询
		public List<AsCustom> queryAsList()throws Exception;
		
		public AsCustom findAsById(String id)throws Exception;
		
		public void addAs(AsCustom asCustom)throws Exception;

}
