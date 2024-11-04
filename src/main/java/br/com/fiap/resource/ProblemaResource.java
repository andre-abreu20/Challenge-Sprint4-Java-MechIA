package br.com.fiap.resource;

import br.com.fiap.bo.ProblemaBO;
import br.com.fiap.to.ProblemaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/usuario")
public class ProblemaResource {
    private ProblemaBO problemaBO = new ProblemaBO();


    @POST
    @Path("/{email}/veiculo/problema")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(@QueryParam("placa") String placa, ProblemaTO problema){
        ProblemaTO resultado = problemaBO.inserir(placa, problema);
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
    @Path("/{email}/veiculo/problema")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProblema(@QueryParam("placa") String placa){
        String resultado = problemaBO.listarProblema(placa);
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();

    }

}
