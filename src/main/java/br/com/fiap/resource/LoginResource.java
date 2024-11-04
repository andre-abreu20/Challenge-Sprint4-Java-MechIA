package br.com.fiap.resource;

import br.com.fiap.bo.LoginBO;

import br.com.fiap.to.LoginTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.ArrayList;

@Path("/usuario")
public class LoginResource {
    private LoginBO loginBO = new LoginBO();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarLogin(LoginTO login){

        Response.ResponseBuilder response = null;
        if(loginBO.verifica(login)){
            response = Response.ok(); // 200 (Ok)
        } else {
            response = Response.status(404);
        }
        return response.build();
    }

}