package com.gestiom_empleados.modelo.exepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//exepcion personbalizada
@ResponseStatus( value = HttpStatus.NOT_FOUND)
public class ResourceNotFaundExeption extends  RuntimeException {

    private static final long serialVersionID =1L;

    public ResourceNotFaundExeption( String mensaje){
        super(mensaje);
    }

}
