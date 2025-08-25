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
import com.SysGroup.Alcaldia.Servicios.Implementaciones.MunicipioService;

@Controller
@RequestMapping("/municipios")
public class MunicipioController {
     @Autowired
    private MunicipioService municipioService;

    @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);    

        Page<Municipio> municipio = municipioService.buscarTodos(pageable);
        model.addAttribute("municipio", municipio);

        int totalPages = municipio.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "municipio/index";
    }

    // crear
    @GetMapping("/create")
    public String create(Model model) {
       model.addAttribute("municipio", new Municipio());
       model.addAttribute("action", "create");
       return "municipio/mant";
    }
 
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Municipio municipio = municipioService.buscaPorId(id).orElseThrow();
        model.addAttribute("municipio", municipio);
        model.addAttribute("action", "edit");
        return "municipio/mant";
    }

    //--Lectura--//
    @GetMapping("/view/{id}")
    public String view(@PathVariable Integer id, Model model) {
        Municipio municipio = municipioService.buscaPorId(id).orElseThrow();
        model.addAttribute("municipio", municipio);
        model.addAttribute("action", "view");
        return "municipio/mant";
    }

    //--eliminar--//
    @GetMapping("/delete/{id}")
    public String deleteConfirm(@PathVariable Integer id, Model model) {
         Municipio municipio = municipioService.buscaPorId(id).orElseThrow();
        model.addAttribute("municipio", municipio);
        model.addAttribute("action", "delete");
        return "municipio/mant";
    }

    //--procesar post segun accion--//
    @PostMapping("/create")
    public String saveNuevo(@ModelAttribute Municipio municipio, BindingResult result,
                           RedirectAttributes redirect, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("action", "create");
            return "municipio/mant";
        }
        municipioService.crearOEditar(municipio);
        redirect.addFlashAttribute("mensaje", "El municipio se ha creado correctamente.");
        return "redirect:/municipios";
    }

    @PostMapping("/edit")
    public String saveEdit(@ModelAttribute Municipio municipio, BindingResult result,
                          RedirectAttributes redirect, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("action", "edit");
            return "municipio/mant";
        }
        municipioService.crearOEditar(municipio);
        redirect.addFlashAttribute("mensaje", "El municipio se ha editado correctamente.");
        return "redirect:/municipios";
    }

   @PostMapping("/delete")
    public String deleteMunicipio(@ModelAttribute Municipio municipio,
                                 RedirectAttributes redirect) {
        municipioService.eliminarMunicipio(municipio.getId_municipio()); // Cambiado a getId_municipio()
        redirect.addFlashAttribute("msg", "Municipio eliminado correctamente");
        return "redirect:/municipios";
    }

}
