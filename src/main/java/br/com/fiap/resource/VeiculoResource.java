package br.com.fiap.resource;

import br.com.fiap.bo.VeiculoBO;
import br.com.fiap.to.VeiculoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;


@Path("/usuario")
public class VeiculoResource {
    private VeiculoBO veiculoBO = new VeiculoBO();

    @POST
    @Path("/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(@PathParam("email") String email, @Valid VeiculoTO veiculo){
        VeiculoTO resultado = veiculoBO.inserir(email, veiculo);
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.created(null); // 201 - CREATED
        } else {
            response = Response.status(404); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/{email}/veiculo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar(@PathParam("email") String email, @Valid VeiculoTO veiculo){
        String resultado = veiculoBO.alterar(email, veiculo);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();
        } else{
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{email}/veiculo")
    public Response excluir(@PathParam("email") String email, @QueryParam("placa") String placa) {
        Response.ResponseBuilder response = null;
          if(veiculoBO.excluir(email, placa)){
              response = Response.status(204); // 204 NO CONTENT
        } else{
            response = Response.status(404); // 404 NOT FOUND
        }
        return response.build();
    }

    @GET
    @Path("/{email}/veiculos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos(@PathParam("email") String email){
        ArrayList<VeiculoTO> resultado = veiculoBO.listarTodos(email);
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.ok(); // 200 (Ok)
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{email}/veiculo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVeiculo(@PathParam("email") String email, @QueryParam("placa") String placa) {
        VeiculoTO resultado = veiculoBO.listarVeiculo(email, placa);
        Response.ResponseBuilder response = null;
        if(resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }
}
