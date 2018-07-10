package service;

import java.util.List;

import po.AsCustom;


public interface AsService {
	
	//账户查询
		public List<AsCustom> queryAsList()throws Exception;

}
