package com.gestiom_empleados.controlador;

import com.gestiom_empleados.modelo.Empleado;
import com.gestiom_empleados.modelo.exepciones.ResourceNotFaundExeption;
import com.gestiom_empleados.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmpleadoControlador {
    @Autowired
    private EmpleadoRepositorio repositorio;


    //Este metodo sirve para lsitar todos los empleados
    @GetMapping("/empleados")
    public List<Empleado> listar() {
        return repositorio.findAll();
    }


    //metodo para guardar un empleado
    @PostMapping("/empleados")
    public Empleado guardar(@RequestBody Empleado empleado) {

        return repositorio.save(empleado);

    }

    //busca un emepleado por id
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoId(@PathVariable Long id) {


        Empleado empleado = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFaundExeption("No existe tal empleado"));

        return ResponseEntity.ok(empleado);
    }


    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleadoId(@PathVariable Long id , @RequestBody Empleado detallesEmpleado) {


        Empleado empleado = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFaundExeption("No existe tal empleado"));

        empleado.setNombre( detallesEmpleado.getNombre() );
        empleado.setApellido(detallesEmpleado.getApellido());
        empleado.setEmail(detallesEmpleado.getEmail());

        Empleado empleadoActulizado = repositorio.save(empleado);

        return ResponseEntity.ok(empleadoActulizado);
    }

    //este metodo sirve para eliminar un empleado
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarEmpleado(@PathVariable Long id){
        Empleado empleado = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFaundExeption("No existe el empleado con el ID : " + id));

        repositorio.delete(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar",Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }





}
