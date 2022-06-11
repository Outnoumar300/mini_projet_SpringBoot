package com.gsnotes.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import com.gsnotes.bo.InscriptionAnnuelle;

import com.gsnotes.services.IInscriptionAnnService;
import com.gsnotes.services.IModuleService;
import com.gsnotes.services.INiveauService;
import com.gsnotes.services.IPersonService;

import com.gsnotes.utils.export.ExcelExporter;

import com.gsnotes.web.models.DelibirationModel;


@Controller
@RequestMapping("/admin")
//RequestMapping("/{admin,prof}") le prof peut acceder pr modifier les fichiers de delibirations
public class DelibirationController {
	
	@Autowired
	private INiveauService niveauService;
	
	@Autowired
    private IInscriptionAnnService inscriptionService ;
	
	@Autowired
	private IModuleService moduleService;
	
	
	@Autowired
	private IPersonService personService; 
	
	
@GetMapping("chooseDelibiration")
public String chooseDelibirationsToExcel(HttpServletResponse response,Model model) throws IOException {
	
	      
	       DelibirationModel delibModel = new DelibirationModel();
	       
			model.addAttribute("delibirationModel", delibModel);
           
			model.addAttribute("niveauxList", niveauService.getAllNiveaux());
			
			return "admin/formDelibiration";
		}

@RequestMapping("exportDelibiration")
public String exportToExcel(@ModelAttribute("delibirationModel")  DelibirationModel delibmodel,HttpServletResponse response) throws IOException {


	Long idNiveau = delibmodel.getIdNiveau();	

	int annee = delibmodel.getAnnee();
	
	List<com.gsnotes.bo.Module> listM = moduleService.getAllModules(idNiveau);
	
	for(com.gsnotes.bo.Module m : listM) {
		System.out.println("Module : "+m.getTitre());
		
	}
	
	List<InscriptionAnnuelle> list = inscriptionService.getAllInscriptions(delibmodel.getIdNiveau(),annee);

	for(InscriptionAnnuelle ia: list) {
	System.out.println(ia.getAnnee()+" "+personService.getPersonById(ia.getEtudiant().getIdUtilisateur()).getNom()+" "+personService.getPersonById(ia.getEtudiant().getIdUtilisateur()).getPrenom());
	}

	response.setContentType("application/octet-stream");
	DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	String currentDateTime = dateFormatter.format(new Date());

	String headerKey = "Content-Disposition";
	String headerValue = "attachment; filename=Delibiration_" + currentDateTime + ".xlsx";
	response.setHeader(headerKey, headerValue);

	ExcelExporter excelExporter = niveauService.prepareExportDelibiration(list,idNiveau);

	excelExporter.export(response);
	return headerValue;
}
} 

	
