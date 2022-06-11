package com.gsnotes.services;

import java.util.List;

import com.gsnotes.bo.InscriptionAnnuelle;


public interface IInscriptionAnnService {

	public List<InscriptionAnnuelle> getAllInscriptions(Long id, int annee);

	public InscriptionAnnuelle getInscriptionAnnuelleById(Long id);
}
