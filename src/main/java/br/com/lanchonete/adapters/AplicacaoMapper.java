package br.com.lanchonete.adapters;

import br.com.lanchonete.adapters.driver.cliente.ClienteDTO;
import br.com.lanchonete.adapters.driver.produto.ProdutoDTO;
import br.com.lanchonete.core.application.Produto.ProdutoAdapter;
import br.com.lanchonete.core.domain.entities.Cliente;
import br.com.lanchonete.core.domain.entities.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper()
public interface AplicacaoMapper {

    AplicacaoMapper INSTANCE = Mappers.getMapper(AplicacaoMapper.class);

    @Mapping(target = "id", expression = "java(Optional.ofNullable(source.getId()))")
    Cliente toCliente(ClienteDTO source);


    @Mapping(target = "id", expression = "java(Optional.ofNullable(source.getId()))")
    Produto toProduto(ProdutoDTO source);


    Produto toProduto(ProdutoAdapter source);


}

