package com.gsnotes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gsnotes.bo.Element;

public interface IElementDao extends JpaRepository<Element, Long>{
		
		@Query("select element FROM Element element where element.module.idModule = : idModule")
	    public List<Element> ElementsOfModule(@Param("idModule") Long idModule);

	}

