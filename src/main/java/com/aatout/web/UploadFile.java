package com.aatout.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

//import com.javasampleapproach.uploadfiles.storage.StorageService;


@RestController
@CrossOrigin("*")
public class UploadFile {
//	private String UPLOAD_DIR = "/home/chistiane/Documents/JEE/videojee/Upload/SpringBootUploadFile/src/uploadDir/";
	private final String UPLOAD_DIR = "C:\\Users\\Safoura\\Pictures\\boo\\aatout\\images\\";
	//private final String UPLOAD_DIR = "dashboard.heroku.com/apps/app-atout\\target\\images";

	

	@Autowired
	StorageService storageService;

	List<String> files = new ArrayList<String>();
	@CrossOrigin
	@RequestMapping("/upload")
	public void uploadFile(@RequestParam("file") MultipartFile file, String modifiedFileName) throws IOException {
		saveUploadFile(file, modifiedFileName);
	}
	
	private void saveUploadFile(MultipartFile file, String modifiedFileName) throws IOException {
		byte[] bytes = file.getBytes();
		//Path path = Paths.get(UPLOAD_DIR+"WE_UPLOADED_THIS"+file.getOriginalFilename());
		Path path = Paths.get(UPLOAD_DIR+modifiedFileName);
		Files.write(path, bytes);
		//files.add(file.getOriginalFilename());
		System.out.println(path);
	}
	
	@GetMapping("/getallfiles")		
	public ResponseEntity<List<String>> getListFiles(Model model) {		
		
       List<String> fileList = new ArrayList<String>();
		
		File repertoire = new File(this.UPLOAD_DIR);
		//File[] file=repertoire.listFiles(null);
		File[] files=repertoire.listFiles();
		
		//int t=files.length;
		
		
		for (int i = 0; i < files.length; i++) {
			String file = files[i].toString();
			//String filename=file.getOriginalFilename();
			int t=file.length();
			String chaine2 = file.substring(37,t);
			
			//uploadFile.files
			fileList.add(chaine2);
			System.out.println(chaine2);
		} 		
		
		List<String> fileNames = fileList
				.stream().map(fileName -> MvcUriComponentsBuilder
						.fromMethodName(UploadFile.class, "getFile", fileName).build().toString())
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(fileNames);
		}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
