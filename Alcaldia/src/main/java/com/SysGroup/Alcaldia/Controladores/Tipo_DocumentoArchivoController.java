package com.SysGroup.Alcaldia.Controladores;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.stream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SysGroup.Alcaldia.Servicios.Interfaces.ITipo_DocumentoArchivoService;
import com.SysGroup.Alcaldia.Modelos.Tipo_DocumentoArchivo;





@Controller
@RequestMapping("/tipodocumentoarchivo") //Esta linea es la que mapea las peticiones a este controlador
public class Tipo_DocumentoArchivoController {
    @Autowired
    private ITipo_DocumentoArchivoService tipoDocumentoArchivoService; // Inyección de dependencias


    //paginado
   @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Tipo_DocumentoArchivo> tipoDocumentos = tipoDocumentoArchivoService.buscarTodos(pageable);
        model.addAttribute("tipoDocumentos",tipoDocumentos);

        int totalPages = tipoDocumentos.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "tipodocumentoarchivo/index"; //Esta seccion es para el paginado
    }

    //crear
    @GetMapping ("/create")
    public String create(Model model) {
        model.addAttribute("tipoDocumento", new Tipo_DocumentoArchivo());
        model.addAttribute("action", "create");
        return "tipodocumentoarchivo/mant";
    }

    //editar
     @GetMapping ("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Tipo_DocumentoArchivo tipoDocumento = tipoDocumentoArchivoService.buscarPorId(id).orElseThrow();
        model.addAttribute("tipoDocumento", tipoDocumento);
        model.addAttribute("action", "edit");
        return "tipodocumentoarchivo/mant";
    }

    //ver (solo lectura)
    @GetMapping ("/view/{id}")
    public String view(@PathVariable("id") Integer id, Model model) {
        Tipo_DocumentoArchivo tipoDocumento = tipoDocumentoArchivoService.buscarPorId(id).orElseThrow();
        model.addAttribute("tipoDocumento", tipoDocumento);
        model.addAttribute("action", "view");
        return "tipodocumentoarchivo/mant";
    }

    //eliminar
    @GetMapping ("/delete/{id}")
    public String deleteConfirm(@PathVariable("id") Integer id, Model model) {
        Tipo_DocumentoArchivo tipoDocumento = tipoDocumentoArchivoService.buscarPorId(id).orElseThrow();
        model.addAttribute("tipoDocumento", tipoDocumento);
        model.addAttribute("action", "delete");
        return "tipodocumentoarchivo/mant";
    }

    //procesar segun action 
    @PostMapping("/create")
    public String saveNuevo(@ModelAttribute Tipo_DocumentoArchivo tipoDocumento, BindingResult result, RedirectAttributes redirectAttributes , Model model) {
        if (result.hasErrors()) {
            model.addAttribute("action", "create");
            return "tipodocumentoarchivo/mant";
        }
        tipoDocumentoArchivoService.crearOEditar(tipoDocumento);
        redirectAttributes.addFlashAttribute("message", "Tipo de Documento creado con éxito");
        return "redirect:/tipodocumentoarchivo";
    }

     @PostMapping("/edit")
    public String saveEdit(@ModelAttribute Tipo_DocumentoArchivo tipoDocumento, BindingResult result, RedirectAttributes redirectAttributes , Model model) {
        if (result.hasErrors()) {
            model.addAttribute("action", "edit");
            return "tipodocumentoarchivo/mant";
        }
        tipoDocumentoArchivoService.crearOEditar(tipoDocumento);
        redirectAttributes.addFlashAttribute("message", "Tipo de Documento editado con éxito");
        return "redirect:/tipodocumentoarchivo";
    }

    @PostMapping("/delete")
    public String deleteTipoDocumento(@ModelAttribute Tipo_DocumentoArchivo tipoDocumento, RedirectAttributes redirect) 
    { try{tipoDocumentoArchivoService.eliminarPorId(tipoDocumento.getId_tipo_documento());
        redirect.addFlashAttribute("msg", "Tipo de Documento eliminado correctamente");
    }
    catch (Exception e) 
         {
            // Captura la excepción específica de la base de datos
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                redirect.addFlashAttribute("error", "Este tipo no se puede eliminar porque se está usando en otro lugar.");
            } else {
                redirect.addFlashAttribute("error", "Ocurrió un error al intentar eliminar el Tipo.");
            }
        }
        
        return "redirect:/tipodocumentoarchivo";
    }


    

}
