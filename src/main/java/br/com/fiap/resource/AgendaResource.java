package br.com.fiap.resource;

import br.com.fiap.bo.AgendaBO;
import br.com.fiap.to.AgendaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.ArrayList;


@Path("/usuario")
public class AgendaResource {
    private AgendaBO agendaBO = new AgendaBO();

    @GET
    @Path("/agenda")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos(){
        ArrayList<LocalDate> resultado = agendaBO.listarAgenda();
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.ok(); // 200 (Ok)
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

}
