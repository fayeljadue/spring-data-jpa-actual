package com.fayzal.springboot.datajpaactual.app.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import com.fayzal.springboot.datajpaactual.app.models.dao.IClienteDao;
//import com.fayzal.springboot.datajpaactual.app.models.dao.IClienteDaoCrudRepo;
import com.fayzal.springboot.datajpaactual.app.models.entity.Cliente;
import com.fayzal.springboot.datajpaactual.app.service.IClienteService;
import com.fayzal.springboot.datajpaactual.app.service.IUploadService;
import com.fayzal.springboot.datajpaactual.app.util.paginator.PageRender;

@Controller
public class ClienteController {

	@Autowired
	@Qualifier("ClienteServiceDaoPaginableAndSort")
	private IClienteService clienteDao;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUploadService uploadService;
	@GetMapping("/listar")
	public String listar(@RequestParam(name = "page",defaultValue = "0") int page,Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 5);
		
		Page<Cliente> clientes = clienteDao.findAll(pageRequest);
		
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		
		model.addAttribute("titulo", "Listar Clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	
	}
	
	@GetMapping("/form")
	public String crear(Model model) {
		
		Cliente cliente = new Cliente();
		model.addAttribute("titulo", "Formulario de cliente");
		model.addAttribute("cliente", cliente);
		return "form";
	}
	
	@PostMapping("/form")
	public String guardar(@Validated Cliente cliente,BindingResult result,
			@RequestParam(name = "file") MultipartFile file, Model model,RedirectAttributes flash) {
		
		//Eliminar la imagen cuando se carga una nueva
		log.info("Cliente: "+cliente.getId().toString());
		log.info("Cliente: "+cliente.getFoto());
		
		if(cliente.getId()!=null && !cliente.getFoto().isEmpty()) {
			/* Upload service
			 * Path nombreCliente = Paths.get("uploads").resolve(cliente.getFoto()).toAbsolutePath();
			log.info("Ruta del path a editar: "+nombreCliente.toString());
			File archivo = nombreCliente.toFile();
			if(archivo.exists() && archivo.canRead()) {
				if(!archivo.delete()) {
					flash.addFlashAttribute("error", "El archivo no se pudo eliminar !");
				}
			}*/
			
			if(!uploadService.delete(cliente.getFoto())) {
				flash.addFlashAttribute("error", "El archivo no se pudo eliminar !");
			}
		}
		
		if(!file.isEmpty()) {
			//Se deja todo en el servicio de Upload service
			/* Metodo 1: Agregar directorio estatico dentro del proyecto, si no se refresca el
			deploy no se visualiza el contenido del directorio
			Path directorioRecursos = Paths.get("src//main//resources//static//uploads");
			String rootpath = directorioRecursos.toFile().getAbsolutePath();*/
			
			/*Metodo 2: Se configura un directorio externo al del proyecto, se debe crear la 
			 * configuracion en la raiz del proyecto clase MVcConfig
			//String rootPath = "C://Temp//uploads";*/
			
			/* Metodo 3:Configuracion para carpeta externa del proyecto pero dentro de este directorio*/
			//Se crea la ruta tomando la carpeta upload dentro del directorio del proyecto y 
			//se concatena el nombre del archivo
			//String uuid = UUID.randomUUID().toString();
			//String nombreFoto = uuid+"_"+file.getOriginalFilename();
			//Path rootPath = Paths.get("uploads").resolve(nombreFoto);
			//Path rootAbsolutePath = rootPath.toAbsolutePath();
			
			try {
				/* Metodo 1 y 2: Copiado de los bytes del objeto
				byte[] bytes = file.getBytes();*/
				
				/*Metodo 1 y 2: Se usa con el metodo de carga con directorio interno de proyecto y externo 
				Path rutaCompleta = Paths.get(rootPath + "//" + file.getOriginalFilename());
				Files.write(rutaCompleta, bytes);*/
				//Metodo abreviado 1 y 2 sirve para todos los metodos: Realiza la copia del archivo
				//Files.copy(file.getInputStream(), rootAbsolutePath);
				String nombreFoto=uploadService.copy(file);
				flash.addFlashAttribute("info","La foto: "+file.getOriginalFilename()+" se ha subido de forma exitosa");
				
				cliente.setFoto(nombreFoto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario cliente");
			return "form";
		}
		flash.addFlashAttribute("success", "Cliente se ha guardado de forma correcta");
		clienteDao.save(cliente);
		return "redirect:/listar";
	}
	
	
	//Funcion para devolver la imagen programaticamente en la peticion de la renderizacion de la vista
	//se pone el :.+ para que la peticion no corte la extension del archivo
	@GetMapping(value="/uploads/{filename:.+}")
	public ResponseEntity<Resource> cargarFoto(@PathVariable String filename){
		//Se agrega en el upload Service
		/*Path rootPath = Paths.get("uploads").resolve(filename).toAbsolutePath();
		log.info("La ruta es: "+ rootPath.toString());*/
		Resource recurso=null;
		
		try {
			recurso = uploadService.load(filename);
			//Se agrega en el upload service
			/*recurso = new UrlResource(rootPath.toUri());
			if(!recurso.isReadable() || !recurso.exists()) {
				throw new RuntimeException("Error no se puede cargar la imagen"+ rootPath.toString());
			}
			log.info("La ruta es: "+ rootPath.toString());*/
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ recurso.getFilename()+"\"")
				.body(recurso);
	}
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable(name = "id") Long id,Model model, RedirectAttributes flash) {
		
		Cliente cliente = null;
		
		if(id>0) {
			cliente = clienteDao.findOne(id);
			if(cliente==null) {
				flash.addFlashAttribute("error", "El cliente que intenta editar no es valido");
				return "redirect:/listar";	
			}
		}else { 
			flash.addFlashAttribute("error", "El cliente que intenta editar no es valido");
			return "redirect:/listar";
			}
		
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Editar Cliente");
		return "/form";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id,RedirectAttributes flash){
		
		Cliente cliente = clienteDao.findOne(id);
		
		if(cliente!= null && !cliente.getFoto().isEmpty()) {
			//Upload service esta
			/*Path nombreCliente = Paths.get("uploads").resolve(cliente.getFoto()).toAbsolutePath();
			File archivo = nombreCliente.toFile();
			if(archivo.exists() && archivo.canRead()) {
				if(!archivo.delete()) {
					flash.addFlashAttribute("error", "El archivo no se pudo eliminar !");
				}
			}*/
			if(!uploadService.delete(cliente.getFoto())) {
				flash.addFlashAttribute("error", "El archivo no se pudo eliminar !");
			}
		}
		
		if(id>0) {
			clienteDao.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado de forma correcta");
		}else { flash.addFlashAttribute("error", "El cliente que intenta eliminar no es valido");}
		return "redirect:/listar";
	}
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value="id") Long id,Model model, RedirectAttributes flash) {
		
		Cliente cliente = clienteDao.findOne(id);
		
		if(cliente ==null) {
			flash.addFlashAttribute("error","El cliente no existe");
			return "redirect:/listar";
		}
		
		model.addAttribute("cliente",cliente);
		model.addAttribute("titulo","Detalles del cliente: " + cliente.getNombre());
		
		return "ver";
	}
	
}
