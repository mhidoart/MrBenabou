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
import com.ba.models.BesoinPC;
import com.ba.models.BesoinPrinter;
import com.ba.repositpory.BesoinRepository;



@RestController
@RequestMapping("besoin")
@CrossOrigin("*")
public class BesoinRestService {
	@Autowired
	BesoinRepository repo;
	
	@RequestMapping(value="/getBesoinPCByID/{id}",method=RequestMethod.GET)
	public BesoinPC getBesoinByPC(@PathVariable Integer id){
		return (BesoinPC) repo.findByDtype("BesoinPC", id);
	}
	@RequestMapping(value="/getAllBesoinPC",method=RequestMethod.GET)
	public List<Besoin> getAllBesoinPC(){
		return repo.findAllByDtype("BesoinPC");
	}
	@RequestMapping(value="/getBesoinPrinterByID/{id}",method=RequestMethod.GET)
	public BesoinPrinter getBesoinPrinterByID(@PathVariable Integer id){
		return (BesoinPrinter) repo.findByDtype("BesoinPrinter", id);
	}
	@RequestMapping(value="/getAllBesoinPrinter",method=RequestMethod.GET)
	public List<Besoin> getAllBesoinPrinter(){
		return repo.findAllByDtype("BesoinPrinter");
	}
	@RequestMapping(value="/getAllBesoin",method=RequestMethod.GET)
	public List<Besoin> getAllBesoin(){
		return repo.findAll();
	}
	@RequestMapping(value="/getBesoinById/{id}",method=RequestMethod.GET)
	public Optional<Besoin> getBesoinById(@PathVariable Integer id){
		return repo.findById(id);
	}
	
	@RequestMapping(value="/addBesoin",method=RequestMethod.POST)
	public boolean addBesoin(@RequestBody Besoin dep) {
		if(repo.save(dep) != null) {
			return true;
		}
		return false;
	}
	@RequestMapping(value="/deleteBesoin/{id}",method=RequestMethod.DELETE)
	public boolean deleteBesoin(@PathVariable Integer id) {
		repo.deleteById(id);
		return true;
	}
	@RequestMapping(value="/updateBesoin/{id}",method=RequestMethod.PUT)
	public Besoin updateBesoin(@PathVariable Integer id, @RequestBody Besoin dep) {
		dep.setId(id);
		return repo.save(dep);
	}
	@RequestMapping(value="/valide/{id}/{flag}",method=RequestMethod.PUT)
	public Besoin valide(@PathVariable Integer id,@PathVariable boolean flag, @RequestBody Besoin dep) {
		dep.setId(id);
		dep.setFlag(flag);
		return repo.save(dep);
	}

}