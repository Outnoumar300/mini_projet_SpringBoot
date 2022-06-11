package com.gsnotes.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.bo.InscriptionModule;
import com.gsnotes.bo.Niveau;

import com.gsnotes.dao.INiveauDao;
import com.gsnotes.dao.InsciptionModuleDao;

import com.gsnotes.services.IModuleService;
import com.gsnotes.services.INiveauService;

import com.gsnotes.utils.export.ExcelExporter;

@Service
@Transactional
public class NiveauServiceImpl implements INiveauService{
	
	
	
	@Autowired 
	private INiveauDao niveauDao;
	
	@Autowired
	private IModuleService moduleService;
	
	@Autowired
	private InsciptionModuleDao inscriptionModuleDao;
	

	@Override
	public List<Niveau> getAllNiveaux() {
		
		return niveauDao.findAll();
	}

	@Override
	public Niveau getNiveauById(Long id) {
		
		return niveauDao.getById(id);
	}

	@Override
	public Niveau getNiveauByAlias(String alias) {
		
		return niveauDao.getNiveauByAlias(alias);
	}

	@Override
public ExcelExporter prepareExportDelibiration(List<InscriptionAnnuelle> list,Long idNiveau) {
		
		  List<com.gsnotes.bo.Module> listMod = moduleService.getAllModules(idNiveau);
		
		    //Integer sizeColumns = new Integer(4+listMod.size()+3) au lieu de 50
		    String[] columnNames = new String[50];
		    columnNames[0]="ID ETUDIANT";
		    columnNames[1]="CNE"; 
		    columnNames[2]="NOM";
		    columnNames[3]="PRENOM";
		   
		    int i = 4;
	        for (com.gsnotes.bo.Module md: listMod) {
	        	
	        	columnNames[i++] = md.getTitre();
	        	columnNames[i++] = "Moyenne";
	        	columnNames[i++] = "Validation";
	        	
	        }
	        
	        
	        columnNames[i++]="Moyenne Annee";
            columnNames[i++]="Validation Annee";
		            
            String[][] data = new String[list.size()][columnNames.length];

			        int i1 = 0;
			        double moyGen = 0.0;
			        for (InscriptionAnnuelle ia : list) {
                        Long idInscA = ia.getIdInscription();
			            data[i1][0] = String.valueOf(ia.getEtudiant().getIdUtilisateur());
			            data[i1][1] = ia.getEtudiant().getCne();
			            data[i1][2] = ia.getEtudiant().getNom();
			            data[i1][3] = ia.getEtudiant().getPrenom();
			            
			            int j = 4;
			            for(InscriptionModule im  :inscriptionModuleDao.getInscModules(idInscA, idNiveau)) {
			            	
                            //System.out.println(inscriptionModuleDao.getInscModules(idInscA, idNiveau));
			            	data[i1][j++] = im.getModule().getCode() ;
				        	double noteSR = im.getNoteSR();
				        	double noteSN = im.getNoteSN();
				        	 moyGen = 0.0;
				        	
				        	double noteFinal = 0.0;
				        	if(noteSR!= 0.0) {
				        		
				        		noteFinal = (noteSR >= noteSN?noteSR:noteSN);
				        	}
				        	else  {
				        		noteFinal = noteSN;
				        	}
				        
				           
				            data[i1][j++] = Double.toString(noteFinal);
				            data[i1][j++] = ( noteFinal>=12 ?"V":"NV");
			            }
				        	
						data[i1][j++] =  Double.toString(moyGen/inscriptionModuleDao.getInscModules(idInscA, idNiveau).size());
						data[i1][j++] = ( moyGen>=12 ?"V":"NV");
						i1++;

	        	
	        
	}
			        
			        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
					String currentDateTime = dateFormatter.format(new Date());
					String[] columns1 = new String[50];
					columns1[0] = "Année Universitaire";
					columns1[1] = "2021/2022" ;
					columns1[2] = currentDateTime ;
					
					String[] columns2 = new String[50];
					columns2[0] = "Classe";
					columns2[1] = niveauDao.getById(idNiveau).getAlias();
			        
	return new ExcelExporter(columnNames, data, "NotesDeliberation",columns1,columns2);

	}


}
