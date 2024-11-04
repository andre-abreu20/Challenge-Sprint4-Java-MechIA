package br.com.fiap.resource;

import br.com.fiap.bo.ConsertoBO;
import br.com.fiap.to.ConsertoTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.ArrayList;


@Path("/usuario")
public class ConsertoResource {
    private ConsertoBO consertoBO = new ConsertoBO();


    @POST
    @Path("/{email}/conserto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(@QueryParam("placa") String placa, ConsertoTO conserto){

        ConsertoTO resultado = consertoBO.inserir(placa, conserto.getDataConserto(), conserto);
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.created(null); // 201 - CREATED
        } else {
            response = Response.status(404); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }


    @GET
    @Path("/{email}/conserto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarConserto(@QueryParam("placa") String placa){
        ArrayList<ConsertoTO> resultado = consertoBO.listarConserto(placa);
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
