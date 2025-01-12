package br.com.lanchonete.drivers;

import org.jboss.resteasy.reactive.ResponseStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.lanchonete.core.userCases.exception.ErroValidacao;
import br.com.lanchonete.core.userCases.exception.ResultadoVazioErro;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ErrorMapper implements ExceptionMapper<Exception> {

    @Inject
    ObjectMapper objectMapper;

    @Override
    @ResponseStatus(500)
    public Response toResponse(Exception exception) {

        int code = 500;
        if (exception instanceof ResultadoVazioErro) {
            code = 404;
        }

        if (exception instanceof ErroValidacao) {
            code = 400;
        }

        ObjectNode exceptionJson = objectMapper.createObjectNode();
        exceptionJson.put("exceptionType", exception.getClass().getName());
        exceptionJson.put("code", code);

        if (exception.getMessage() != null) {
            exceptionJson.put("error", exception.getMessage());
        }

        return Response.status(code)
                .entity(exceptionJson)
                .build();
    }

}
