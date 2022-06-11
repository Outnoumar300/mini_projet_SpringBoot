package com.gsnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.dao.IInscriptionAnnuelDao;
import com.gsnotes.services.IInscriptionAnnService;


@Service
@Transactional
public class InscriptionAnnuelleImpl implements IInscriptionAnnService{
	
  @Autowired
  private IInscriptionAnnuelDao InscriptionAnnuelDao ;
  

	@Override
	public List<InscriptionAnnuelle> getAllInscriptions(Long id, int annee) {
		return (List<InscriptionAnnuelle>) InscriptionAnnuelDao.listInscriptions(id, annee);
	}

	@Override
	public InscriptionAnnuelle getInscriptionAnnuelleById(Long id) {
		return InscriptionAnnuelDao.getById(id);
	}


}
