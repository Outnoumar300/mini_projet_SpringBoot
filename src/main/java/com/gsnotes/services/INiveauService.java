package com.gsnotes.services;

import java.util.List;

import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.bo.Niveau;
import com.gsnotes.utils.export.ExcelExporter;



public interface INiveauService {
	

	public List<Niveau> getAllNiveaux();

	public Niveau getNiveauById(Long id);
	
	public Niveau getNiveauByAlias(String alias);

	ExcelExporter prepareExportDelibiration(List<InscriptionAnnuelle> list, Long idNiveau);
	

}
