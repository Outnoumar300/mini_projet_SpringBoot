package com.gsnotes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gsnotes.bo.InscriptionAnnuelle;

public interface IInscriptionAnnuelDao extends JpaRepository<InscriptionAnnuelle, Long>{

	@Query("select inscriAnn from InscriptionAnnuelle inscriAnn where inscriAnn.niveau.idNiveau =:idNiveau and inscriAnn.annee=:annee")
	List<InscriptionAnnuelle> listInscriptions(@Param("idNiveau") Long niv,@Param("annee") int annee);
	
}
