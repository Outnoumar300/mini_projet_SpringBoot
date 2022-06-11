package com.gsnotes.dao;


import com.gsnotes.bo.InscriptionModule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InsciptionModuleDao extends JpaRepository<InscriptionModule,Long> {

    @Query("select inscModule  from InscriptionModule inscModule where inscModule.inscriptionAnnuelle.idInscription=:ia and inscModule.module.idModule=:imat ")
   List<InscriptionModule> getInscModules(@Param("ia") Long idInscrip , @Param("imat") Long idModule);


}
