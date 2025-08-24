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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SysGroup.Alcaldia.Modelos.DocumentoArchivo;
import com.SysGroup.Alcaldia.Servicios.Implementaciones.DocumentoArchivoService;
import com.SysGroup.Alcaldia.Servicios.Implementaciones.MunicipioService;
import com.SysGroup.Alcaldia.Servicios.Implementaciones.Tipo_DocumentoArchivoService;

@Controller
public class DocumentoArchivoController {

    @Autowired
    private DocumentoArchivoService documentoArchivoService;

    @Autowired
    private Tipo_DocumentoArchivoService tipoDocumentoArchivoService;

    @Autowired
    private MunicipioService municipioService;

     @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam( "size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);    

        Page<DocumentoArchivo> documento = documentoArchivoService.buscarTodosPaginados(pageable);
        model.addAttribute("documento", documento);

        int totalPages = documento.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);

        }
        return "documento/Index";
    }

    // crear
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("documento", new DocumentoArchivo());
        model.addAttribute("municipio", municipioService.obtenerTodosLosMunicipios());
        model.addAttribute("tipoDocumento", tipoDocumentoArchivoService.obtenerTodos());
        return "documento/mant";
    }
 
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
       DocumentoArchivo documentoArchivo = documentoArchivoService.buscarPorId(id);
         model.addAttribute("documentoArchivo", documentoArchivo);
         model.addAttribute("tipos_documentos", documentoArchivoService.obtenerTodos());
         model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
         model.addAttribute("action", "edit");
         return "documento/mant";
    }

    //--Lectura--//
    @GetMapping("/view/{id}")
    public String view(@PathVariable Integer id, Model model) {
        DocumentoArchivo documentoArchivo = documentoArchivoService.buscarPorId(id);
        model.addAttribute("documentoArchivo", documentoArchivo);
        model.addAttribute("tipos_documentos", documentoArchivoService.obtenerTodos());
        model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
        model.addAttribute("action", "view");
        return "documento/mant";
    }

    //--eliminar--//
    @GetMapping("/delete/{id}")
    public String deleteConfirm(@PathVariable Integer id, Model model) {
      DocumentoArchivo documentoArchivo = documentoArchivoService.buscarPorId(id);
      model.addAttribute("documentoArchivo", documentoArchivo);
      model.addAttribute("tipos_documentos", documentoArchivoService.obtenerTodos());
      model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
      model.addAttribute("action", "delete");
      return "documento/mant";
    }

    //--procesar post segun accion--//
    @PostMapping("/create")
    public String saveNuevo(@ModelAttribute DocumentoArchivo documentoArchivo , BindingResult result
    , RedirectAttributes redirect , Model model) {
       if (result.hasErrors()) {
           model.addAttribute("tipos_documentos", documentoArchivoService.obtenerTodos());
           model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
           model.addAttribute("action", "create");
           return "documento/mant";
       }
       documentoArchivoService.crearOEditar(documentoArchivo);
       redirect.addFlashAttribute("mensaje", "La asignación se ha creado correctamente.");
       return "redirect:/documentos";
    }

    @PostMapping ("/edit")
    public String saveEdit(@ModelAttribute DocumentoArchivo documentoArchivo , BindingResult result
    , RedirectAttributes redirect , Model model) {
       if (result.hasErrors()) {
           model.addAttribute("tipos_documentos", documentoArchivoService.obtenerTodos());
           model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
           model.addAttribute("action", "edit");
           return "documento/mant";
       }
       documentoArchivoService.crearOEditar(documentoArchivo);
       redirect.addFlashAttribute("mensaje", "La asignación se ha editado correctamente.");
       return "redirect:/documentos";
    }

    @PostMapping("delete")
    public String deleteDocumentoArchivo(@ModelAttribute DocumentoArchivo documentoArchivo ,
    RedirectAttributes redirect ) {
       documentoArchivoService.eliminarPorId(documentoArchivo.getId_documento());
       redirect.addFlashAttribute("msg", "documento eliminado correctamente");

        return "redirect:/documentos";
    }


}