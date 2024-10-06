package br.com.lanchonete.adapters.driven.base;

import br.com.lanchonete.core.application.base.AdapterBase;
import br.com.lanchonete.core.application.base.BasePortDriven;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@SuppressWarnings("rawtypes")
@Consumes(MediaType.APPLICATION_JSON + ";charsert=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charsert=utf-8")
public abstract class RestBase<A extends AdapterBase, D extends BasePortDriven> {

    @Inject
    protected D driven;

    @Inject
    ObjectMapper objectMapper;

    @POST
    public Response inserir(A atapter) {
        driven.salvar(atapter);
        return respostaSucesso();
    }

    protected Response respostaSucesso() {

        ObjectNode exceptionJson = objectMapper.createObjectNode();
        exceptionJson.put("messagem", "Inserido com Sucesso");
        exceptionJson.put("code", 201);

        return Response.status(201)
                .entity(exceptionJson)
                .build();
    }

    protected Response respostaSucesso(String messagem) {

        ObjectNode exceptionJson = objectMapper.createObjectNode();
        exceptionJson.put("messagem", messagem);
        exceptionJson.put("code", 201);

        return Response.status(201)
                .entity(exceptionJson)
                .build();
    }

    protected Response respostaSucesso(String messagem, Integer code) {

        ObjectNode exceptionJson = objectMapper.createObjectNode();
        exceptionJson.put("messagem", messagem);
        exceptionJson.put("code", code);

        return Response.status(code)
                .entity(exceptionJson)
                .build();
    }

    protected Response respostaErro() {

        ObjectNode exceptionJson = objectMapper.createObjectNode();
        exceptionJson.put("messagem", "Não foi possível atender à solicitação");
        exceptionJson.put("code", 400);

        return Response.status(201)
                .entity(exceptionJson)
                .build();
    }
}
