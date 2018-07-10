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

}
