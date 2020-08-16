package com.fayzal.springboot.datajpaactual.app.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadFileImpl implements IUploadService {

	private final static String UPLOADS_FOLDER = "uploads";

	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public Resource load(String filename) throws MalformedURLException{
		Path rootPath = getPath(filename);
		log.info("La ruta es: "+ rootPath.toString());
		Resource recurso=null;
		recurso = new UrlResource(rootPath.toUri());
		
		if(!recurso.isReadable() || !recurso.exists()) {
			throw new RuntimeException("Error no se puede cargar la imagen"+ rootPath.toString());
		}
		
		log.info("La ruta es: "+ rootPath.toString());
		
		return recurso;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		String uuid = UUID.randomUUID().toString();
		String nombreFoto = uuid+"_"+file.getOriginalFilename();
		Path rootAbsolutePath = getPath(nombreFoto);
		
		Files.copy(file.getInputStream(), rootAbsolutePath);

		return nombreFoto;
	}

	@Override
	public boolean delete(String filename) {
		Path nombreCliente = getPath(filename);
		log.info("Ruta del path a editar: "+nombreCliente.toString());
		File archivo = nombreCliente.toFile();
		if(archivo.exists() && archivo.canRead()) {
			if(!archivo.delete()) {
				return false;
			}
		}
		return true;
	}
	
	private Path getPath(String filename) {
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
		
	}

	@Override
	public void init() throws IOException {
		Files.createDirectory(Paths.get(UPLOADS_FOLDER));
		
	}

}
