package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.AsCustom;


public interface AsDao {
	
	public List<AsCustom> queryAsList()throws Exception;
	
	public AsCustom findAsById(@Param("id")String id)throws Exception;
	
	public void addAs(AsCustom asCustom)throws Exception;

}
