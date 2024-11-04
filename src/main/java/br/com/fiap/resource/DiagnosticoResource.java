package br.com.fiap.resource;

import br.com.fiap.bo.DiagnosticoBO;
import br.com.fiap.to.DiagnosticoTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/usuario")
public class DiagnosticoResource {
    private DiagnosticoBO diagnosticoBO = new DiagnosticoBO();


    @POST
    @Path("/{email}/veiculo/diagnostico")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(@QueryParam("placa") String placa, DiagnosticoTO diagnostico){

        DiagnosticoTO resultado = diagnosticoBO.inserir(placa, diagnostico);
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
    @Path("/{email}/veiculo/diagnostico")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarDiagnostico(@QueryParam("placa") String placa){
        String resultado = diagnosticoBO.listarDiagnostico(placa);
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
