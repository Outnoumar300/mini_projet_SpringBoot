package com.gsnotes.services.impl;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsnotes.dao.IModuleDao;
import com.gsnotes.services.IModuleService;


@Service
@Transactional
public class ModuleServiceImpl implements IModuleService{
   
	@Autowired
	IModuleDao moduleDao;
	
	
	@Override
	public List<com.gsnotes.bo.Module> getAllModules(Long idNiveau) {
		return moduleDao.getModuleByIdNiveau(idNiveau);
		
	}

}
