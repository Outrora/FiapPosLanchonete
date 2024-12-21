package br.com.lanchonete.drivers;

import br.com.lanchonete.core.entities.Cliente;
import br.com.lanchonete.core.entities.Produto;
import br.com.lanchonete.drivers.db.cliente.ClienteDTO;
import br.com.lanchonete.drivers.db.produto.ProdutoDTO;

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

}
