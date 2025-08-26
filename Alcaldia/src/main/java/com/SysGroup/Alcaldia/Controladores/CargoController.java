package com.SysGroup.Alcaldia.Controladores;
import com.SysGroup.Alcaldia.Modelos.Cargo;
import com.SysGroup.Alcaldia.Servicios.Interfaces.ICargoService;

import java.util.stream.Collectors;
import java.util.Optional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Controller
@RequestMapping("/cargo")
public class CargoController {

     @Autowired
    private ICargoService cargoService;

        @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Cargo> cargos = cargoService.buscarTodos(pageable);
        model.addAttribute("cargos", cargos);

        int totalPages = cargos.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "cargo/index";
    }

     // ----------- Crear --------------
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("cargo", new Cargo());
        model.addAttribute("action", "create");
        return "cargo/mant";
    }
    // ----------- Editar --------------
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id_cargo, Model model) {
        Cargo cargo = cargoService.buscarPorId(id_cargo).orElseThrow();
        model.addAttribute("cargo", cargo);
        model.addAttribute("action", "edit");
        return "cargo/mant";
    }

    // ----------- Ver (solo lectura) --------------
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Integer id_cargo, Model model) {
        Cargo cargo = cargoService.buscarPorId(id_cargo).orElseThrow();
        model.addAttribute("cargo", cargo);
        model.addAttribute("action", "view");
        return "cargo/mant";
    }

    // ----------- Eliminar (confirmación) --------------
    @GetMapping("/delete/{id}")
    public String deleteConfirm(@PathVariable("id") Integer id_cargo, Model model) {
        Cargo cargo = cargoService.buscarPorId(id_cargo).orElseThrow();
        model.addAttribute("cargo", cargo);
        model.addAttribute("action", "delete");
        return "cargo/mant";
    }

    // ----------- Procesar post según action --------------
    @PostMapping("/create")
    public String saveNuevo(@ModelAttribute Cargo cargo, BindingResult result,
            RedirectAttributes redirect, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("action", "create");
            return "cargo/mant";
        }
        cargoService.crearOeditar(cargo);
        redirect.addFlashAttribute("msg", "Cargo creado correctamente");
        return "redirect:/cargo";
    }

    @PostMapping("/edit")
    public String saveEditado(@ModelAttribute Cargo cargo, BindingResult result,
            RedirectAttributes redirect, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("action", "edit");
            return "cargo/mant";
        }
        cargoService.crearOeditar(cargo);
        redirect.addFlashAttribute("msg", "Cargo actualizado correctamente");
        return "redirect:/cargo";
    }

    @PostMapping("/delete")
    public String deleteCargo(@ModelAttribute Cargo cargo, RedirectAttributes redirect) 
    {
       try{cargoService.eliminarPorId(cargo.getId_cargo());
        redirect.addFlashAttribute("msg", "Cargo eliminado correctamente");
         } 
         catch (Exception e) 
         {
            // Captura la excepción específica de la base de datos
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                redirect.addFlashAttribute("error", "Este cargo no se puede eliminar porque se está usando en otro lugar.");
            } else {
                redirect.addFlashAttribute("error", "Ocurrió un error al intentar eliminar el cargo.");
            }
        }
        return "redirect:/cargo";
    }
    
}