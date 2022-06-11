package com.gsnotes.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsnotes.bo.Element;
import com.gsnotes.dao.IElementDao;
import com.gsnotes.services.IElementService;

@Service
@Transactional
public class ElementServiceImpl implements IElementService {
	
	@Autowired
	IElementDao elementDao;


	@Override
	public List<Element> getAllElementsByNiveau(Long idModule) {
		return elementDao.ElementsOfModule(idModule);
	
	}

}
