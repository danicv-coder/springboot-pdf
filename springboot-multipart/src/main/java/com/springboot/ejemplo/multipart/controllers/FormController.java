package com.springboot.ejemplo.multipart.controllers;

import com.springboot.ejemplo.multipart.models.entity.Empleado;
import com.springboot.ejemplo.multipart.models.entity.Historial;
import com.springboot.ejemplo.multipart.services.IEmpleadoService;
import com.springboot.ejemplo.multipart.services.ISendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
public class FormController {
    @Autowired
    IEmpleadoService empleadoService;

    @Autowired
    private ISendMailService sendMailService;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/index")
    public String index(Model model, Empleado empleado) {
        model.addAttribute("name", empleado.getName());
        return "form";
    }

    @PostMapping(value = "/form", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String form(@ModelAttribute Empleado empleado, @RequestPart() Historial historial) throws IOException {

        sendMailService.sendAttachmentFileMail("danicvdance527@gmail.com", "prueba", "prueba",
                sendMailService.multipartConvertAFile(empleado.getDocument()));
        empleadoService.save(empleado);
        System.out.println(empleado);
        System.out.println(historial.getEmail() + " " + historial.getPlantilla());
        return "/empleado";
    }
}
