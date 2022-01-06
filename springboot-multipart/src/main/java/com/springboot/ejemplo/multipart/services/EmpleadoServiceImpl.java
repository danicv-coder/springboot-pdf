package com.springboot.ejemplo.multipart.services;

import com.springboot.ejemplo.multipart.models.entity.Empleado;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService  {
    @Override
    public void save(Empleado empleado) {
        Empleado empleado1 = new Empleado(empleado.getName(), empleado.getDocument());
    }
}
