package br.com.example.springsecurity.Repository;

import br.com.example.springsecurity.Model.CarrinhoCompras;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarrinhoComprasRepository extends CrudRepository<CarrinhoCompras,Long> {

    @Query("SELECT CC.id FROM CarrinhoCompras CC where CC.id_cliente = :id_cliente AND CC.status = :status AND CC.id_produto = :id_produto" )
    List<Long> findIdByUserStatus(@Param("id_cliente") Long name,@Param("status") String status,@Param("id_produto") Long id_produto);

}
