package br.com.fiap.resource;

import br.com.fiap.bo.MecanicoBO;
import br.com.fiap.to.MecanicoTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.ArrayList;

@Path("/usuario")
public class MecanicoResource {
    private MecanicoBO mecanicoBO = new MecanicoBO();

    @GET
    @Path("/{email}/conserto/mecanico")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarMecanico(@QueryParam("dataConserto") String dataConserto){
        ArrayList<MecanicoTO> resultado = mecanicoBO.listarMecanico(LocalDate.parse(dataConserto));
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
