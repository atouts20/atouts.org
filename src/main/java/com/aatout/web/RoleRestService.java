package com.aatout.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aatout.dao.RoleRepository;
import com.aatout.model.AppRole;

@RestController
public class RoleRestService {
	
	@Autowired
	private RoleRepository roleRepository;
	@GetMapping("/listRoles")
	public List<AppRole> getRoles(){
		return  roleRepository.findAll();
		
	}
	@GetMapping("/listRoles/{id}")
	public AppRole getRole(@PathVariable Long id) {
		return roleRepository.findOne(id);
	}
	@PostMapping("/saveRole")
	public AppRole saveRole(@RequestBody AppRole appRole) {
		return roleRepository.save(appRole);
	}
	@DeleteMapping("/deleteRole")
	public boolean deleteRole(@PathVariable Long id) {
		roleRepository.delete(id);
		return true;
	}
	/*@PutMapping("/update/{id}")
	public ResponseEntity<AppRole>  updateAppRole(@PathVariable Long id, @Valid @RequestBody AppRole appRole ){
		if(!roleRepository.findById(id).isPresent()) {
			Log.error("Id" +id + "existe pas!!!");
			ResponseEntity.badRequest().build();
			
		}
		return ResponseEntity.ok(roleRepository.save(appRole));
	}*/
	
	@PutMapping("/update/{id}")
	public ResponseEntity<AppRole>  updateAppRole(@PathVariable Long id, @Valid @RequestBody AppRole appRole ){
		Optional<AppRole> unRole = roleRepository.findById(id);

		if(!unRole.isPresent()) 
			return ResponseEntity.notFound().build();
			appRole.setId(id);
			roleRepository.save(appRole);
			return ResponseEntity.noContent().build();
			
		
	}
	

}
