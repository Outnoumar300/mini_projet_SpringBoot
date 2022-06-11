package com.gsnotes.dao;

import com.gsnotes.bo.InscriptionMatiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InscriptionElementsDao extends JpaRepository<InscriptionMatiere,Long> {
	
    @Query("select elem from InscriptionMatiere elem where elem.inscriptionAnnuelle.idInscription=:ia and  elem.matiere.idMatiere=:imat")
    InscriptionMatiere getInscElements(@Param("ia") Long idInscrip , @Param("imat") Long idElment);
    
}
