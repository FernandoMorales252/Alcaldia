package com.SysGroup.Alcaldia.Controladores;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.domain.Page;
import org.thymeleaf.context.Context;

import com.SysGroup.Alcaldia.Modelos.Empleado;
import com.SysGroup.Alcaldia.Servicios.Interfaces.ICargoService;
import com.SysGroup.Alcaldia.Servicios.Interfaces.IEmpleadoService;
import com.SysGroup.Alcaldia.Servicios.Interfaces.IMunicipioService;
import com.SysGroup.Alcaldia.Utilidades.PdfGeneratorService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;
    @Autowired
    private ICargoService cargoService;
    @Autowired
    private IMunicipioService municipioService;
    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    // ----------- Listado --------------
    @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Empleado> empleados = empleadoService.buscarTodos(pageable);
        model.addAttribute("empleados", empleados);

        int totalPages = empleados.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
         // Agregamos la lista de cargos para el formulario de filtros
        model.addAttribute("cargos", cargoService.obtenerTodos());
        model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());

        return "empleado/index";
    }

    // ----------- Crear --------------
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("cargos", cargoService.obtenerTodos());
        model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
        model.addAttribute("action", "create");
        return "empleado/mant";
    }

    // ----------- Editar --------------
    @GetMapping("/edit/{id_empleado}")
    public String edit(@PathVariable Integer id_empleado, Model model) {
        Empleado empleado = empleadoService.buscarPorId(id_empleado);
        model.addAttribute("empleado", empleado);
        model.addAttribute("cargos", cargoService.obtenerTodos());
        model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
        model.addAttribute("action", "edit");
        return "empleado/mant";
    }

    // ----------- Ver (solo lectura) --------------
    @GetMapping("/view/{id_empleado}")
    public String view(@PathVariable Integer id_empleado, Model model) {
        Empleado empleado = empleadoService.buscarPorId(id_empleado);
        model.addAttribute("empleado", empleado);
        model.addAttribute("cargos", cargoService.obtenerTodos());
        model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
        model.addAttribute("action", "view");
        return "empleado/mant";
    }

    // ----------- Eliminar (confirmación) --------------
    @GetMapping("/delete/{id_empleado}")
    public String deleteConfirm(@PathVariable Integer id_empleado, Model model) {
        Empleado empleado = empleadoService.buscarPorId(id_empleado);
        model.addAttribute("empleado", empleado);
        model.addAttribute("cargos", cargoService.obtenerTodos());
        model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
        model.addAttribute("action", "delete");
        return "empleado/mant";
    }

    // ----------- Procesar POST para crear un nuevo empleado --------------
    @PostMapping("/create")
    public String saveNuevo(@Valid @ModelAttribute Empleado empleado, BindingResult result,
                            RedirectAttributes redirect, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("cargos", cargoService.obtenerTodos());
            model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
            model.addAttribute("action", "create");
            return "empleado/mant";
        }
        empleadoService.crearOeditar(empleado);
        redirect.addFlashAttribute("msg", "Empleado creado correctamente");
        return "redirect:/empleados";
    }
    
    // ----------- Procesar POST para editar un empleado existente --------------
    @PostMapping("/edit")
    public String saveEditado(@ModelAttribute Empleado empleado, BindingResult result,
                              RedirectAttributes redirect, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("cargos", cargoService.obtenerTodos());
            model.addAttribute("municipios", municipioService.obtenerTodosLosMunicipios());
            model.addAttribute("action", "edit");
            return "empleado/mant";
        }
        empleadoService.crearOeditar(empleado);
        redirect.addFlashAttribute("msg", "Empleado actualizado correctamente");
        return "redirect:/empleados";
    }

    // ----------- Procesar POST para eliminar un empleado --------------
    @PostMapping("/delete")
    public String deleteEmpleado(@ModelAttribute Empleado empleado, RedirectAttributes redirect) {
        empleadoService.eliminarPorId(empleado.getId_empleado());
        redirect.addFlashAttribute("msg", "Empleado eliminado correctamente");
        return "redirect:/empleados";
    }

     // ----------- Metodo para generar el PDF --------------
    @GetMapping("/empleadoPDF")
    public void generarPdf(
            HttpServletResponse response,
            @RequestParam(value = "fechaInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam(value = "id_Cargo", required = false) Integer id_Cargo,
            @RequestParam(value = "id_Municipio", required = false) Integer id_Municipio,
            @RequestParam(value = "estado", required = false) Integer estado) throws Exception {

        List<Empleado> empleadosFiltrados = empleadoService.buscarEmpleadosFiltrados(fechaInicio, fechaFin, id_Cargo, id_Municipio, estado);
        Context context = new Context();
        context.setVariable("empleados", empleadosFiltrados);

        byte[] pdfBytes = pdfGeneratorService.generatePdfReport("empleado/RPEmpleado", context);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=empleados.pdf");
        response.setContentLength(pdfBytes.length);

        response.getOutputStream().write(pdfBytes);
        response.getOutputStream().flush();
    }
}