package com.ba.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ba.models.Besoin;
import com.ba.models.Offre;
import com.ba.repositpory.OffreRepository;


@RestController
@RequestMapping("offre")
@CrossOrigin("*")
public class OffreRestService {

	@Autowired
	OffreRepository repo;
	

	@RequestMapping(value="/getAllOffres",method=RequestMethod.GET)
	public List<Offre> getAllOffres(){
		return repo.findAll();
	}
	@RequestMapping(value="/getAllOffresByDemadeOffre/{idDemamdeOffre}",method=RequestMethod.GET)
	public List<Offre> getAllOffresByDemadeOffre(@PathVariable int idDemamdeOffre ){
		return repo.findOffreByDemandeOffre(idDemamdeOffre);
	}
	@RequestMapping(value="/getAllOffresByFournisseur/{idFournisseur}",method=RequestMethod.GET)
	public List<Offre> getAllOffresByFournisseur(@PathVariable int idFournisseur ){
		return repo.findOffreByFournisseur(idFournisseur);
	}
	
	@RequestMapping(value="/getOffreById/{id}",method=RequestMethod.GET)
	public Optional<Offre> getOffreById(@PathVariable Integer id){
		return repo.findById(id);
	}
	@RequestMapping(value="/addOffre",method=RequestMethod.POST)
	public boolean addOffre(@RequestBody Offre dep) {
		if(repo.save(dep) != null) {
			return true;
		}
		return false;
	}
	@RequestMapping(value="/deleteOffre/{id}",method=RequestMethod.DELETE)
	public boolean deleteOffre(@PathVariable Integer id) {
		repo.deleteById(id);
		return true;
	}
	@RequestMapping(value="/updateOffre/{id}",method=RequestMethod.PUT)
	public Offre updateOffre(@PathVariable Integer id, @RequestBody Offre dep) {
		dep.setId(id);
		return repo.save(dep);
	}
	@RequestMapping(value="/valide/{id}/{flag}",method=RequestMethod.PUT)
	public Offre valide(@PathVariable Integer id,@PathVariable boolean flag, @RequestBody Offre dep) {
		dep.setId(id);
		dep.setFlag(flag);
		return repo.save(dep);
	}

}
