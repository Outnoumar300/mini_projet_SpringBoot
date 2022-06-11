package com.gsnotes.dao;


import com.gsnotes.bo.InscriptionAnnuelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface InscriptionAnnuelDao extends JpaRepository<InscriptionAnnuelle,Long> {


    @Query("select inscAnn from InscriptionAnnuelle inscAnn where inscAnn.niveau.idNiveau=:idNiveau and inscAnn.annee=:annee")
    List<InscriptionAnnuelle> getEtuList(@Param("idNiveau") Long niv,@Param("annee") int annee);

}
