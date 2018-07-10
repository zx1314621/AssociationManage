package mapper;

import java.util.List;

import po.AsCustom;


public interface AsDao {
	
	public List<AsCustom> queryAsList()throws Exception;

}
