package com.SysGroup.Alcaldia.Controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SysGroup.Alcaldia.Modelos.Municipio;
import com.SysGroup.Alcaldia.Modelos.Proyecto;
import com.SysGroup.Alcaldia.Servicios.Implementaciones.MunicipioService;
import com.SysGroup.Alcaldia.Servicios.Implementaciones.ProyectoService;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {
     @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private MunicipioService municipioService;

    //paginado
    @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);    

        Page<Proyecto> proyecto = proyectoService.buscarTodosPaginados(pageable);
        model.addAttribute("proyecto", proyecto);

        int totalPages = proyecto.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "proyecto/index";
    }

    // crear
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
        return "proyecto/mant";
    }
 
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Proyecto proyecto = proyectoService.buscarPorId(id);
        model.addAttribute("proyecto", proyecto);
        model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
        model.addAttribute("action", "edit");
        return "proyecto/mant";
    }

    //--Lectura--//
    @GetMapping("/view/{id}")
    public String view(@PathVariable Integer id, Model model) {
        Proyecto proyecto = proyectoService.buscarPorId(id);
        model.addAttribute("proyecto", proyecto);
        model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
        model.addAttribute("action", "view");
        return "proyecto/mant";
    }

    //--eliminar--//
    @GetMapping("/delete/{id}")
    public String deleteConfirm(@PathVariable Integer id, Model model) {
        Proyecto proyecto = proyectoService.buscarPorId(id);
        model.addAttribute("proyecto", proyecto);
        model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
        model.addAttribute("action", "delete");
        return "proyecto/mant";
    }

 //--procesar post segun accion--//
 @PostMapping("/create")
 public String saveNuevo(@RequestParam("municipioId") Integer municipioId,
 @ModelAttribute Proyecto proyecto, BindingResult result,RedirectAttributes redirect, Model model) {
 // Validación de fechas del lado del servidor
 if (proyecto.getFecha_inicio() != null && proyecto.getFecha_fin() != null) {
 if (proyecto.getFecha_fin().before(proyecto.getFecha_inicio())) {
 result.rejectValue("fecha_fin", "error.proyecto", "La fecha de fin debe ser posterior a la fecha de inicio.");
 }
 }

 if (result.hasErrors()) {
 model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
 model.addAttribute("action", "create");
 return "proyecto/mant";
 }

 Municipio municipio = municipioService.buscarMunicipioPorId(municipioId);
 proyecto.setId_municipio(municipio); 

 proyectoService.crearOEditar(proyecto);
 redirect.addFlashAttribute("mensaje", "El proyecto se ha creado correctamente.");
 return "redirect:/proyectos";
 }

 //accion edit//
 @PostMapping("/edit")
 public String saveEdit(@RequestParam("municipioId") Integer municipioId,
 @ModelAttribute Proyecto proyecto, BindingResult result,
 RedirectAttributes redirect, Model model) {
 //Validacion de fecha
 if (proyecto.getFecha_inicio() != null && proyecto.getFecha_fin() != null) {
 if (proyecto.getFecha_fin().before(proyecto.getFecha_inicio())) {
 result.rejectValue("fecha_fin", "error.proyecto", "La fecha de fin debe ser posterior a la fecha de inicio.");
 }
 }
 if (result.hasErrors()) {
 model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
 model.addAttribute("action", "edit");
 return "proyecto/mant";
 }

 Municipio municipio = municipioService.buscarMunicipioPorId(municipioId);
 proyecto.setId_municipio(municipio); // Asigna el objeto Municipio
 proyectoService.crearOEditar(proyecto);
 redirect.addFlashAttribute("mensaje", "El proyecto se ha editado correctamente.");
 return "redirect:/proyectos"; 
 }

 //accion delete//
 @PostMapping("/delete")
 public String deleteProyecto(@ModelAttribute Proyecto proyecto,
 RedirectAttributes redirect) 
 {
 proyectoService.eliminarPorId(proyecto.getId_proyecto());
 redirect.addFlashAttribute("msg", "Proyecto eliminado correctamente");
 return "redirect:/proyectos";
 }

}



