package com.gsnotes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gsnotes.bo.Module;

@Repository
public interface IModuleDao extends JpaRepository<Module,Long>{
	
	@Query("select module from Module module where module.niveau.idNiveau = :idNiveau")
    public List<com.gsnotes.bo.Module> getModuleByIdNiveau(@Param("idNiveau") Long idNiveau);
	
	
}
