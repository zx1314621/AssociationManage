package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.AsDao;
import po.AsCustom;
import service.AsService;

@Service
public class AsServiceImpl implements AsService{

	@Autowired
	private AsDao asDao;
	public List<AsCustom> queryAsList() throws Exception {
		// TODO Auto-generated method stub
		return asDao.queryAsList();
	}
	public AsCustom findAsById(String id) throws Exception {
		// TODO Auto-generated method stub
		AsCustom asCustom = new AsCustom();
		asCustom = asDao.findAsById(id);
		return asCustom;
	}
	public void addAs(AsCustom asCustom) throws Exception {
		// TODO Auto-generated method stub
		asDao.addAs(asCustom);
		
	}

}
