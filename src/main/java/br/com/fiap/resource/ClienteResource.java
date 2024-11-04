package br.com.fiap.resource;

import br.com.fiap.bo.ClienteBO;
import br.com.fiap.to.ClienteTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/usuario")
public class ClienteResource {
    private ClienteBO clienteBO = new ClienteBO();


    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCliente(@PathParam("email") String email){
        ClienteTO resultado = clienteBO.listarCliente(email);
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();

    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(@Valid ClienteTO cliente){
        ClienteTO resultado = clienteBO.inserir(cliente);
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.created(null); // 201 - CREATED
        } else {
            response = Response.status(404); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }


    @DELETE
    @Path("/{email}")
    public Response excluir(@PathParam("email") String email) {
        Response.ResponseBuilder response = null;
        if(clienteBO.excluir(email)){
            response = Response.status(204); // 204 NO CONTENT
        } else{
            response = Response.status(404); // 404 NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar(@PathParam("email") String email, @Valid ClienteTO cliente){
        String resultado = clienteBO.alterar(email, cliente);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();
        } else{
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }
}
