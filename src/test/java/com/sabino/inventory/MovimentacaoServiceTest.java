package com.sabino.inventory;
import com.sabino.inventory.dto.MovimentacaoRequest;
import com.sabino.inventory.repository.MaterialRepository;
import com.sabino.inventory.service.MovimentacaoService;
import com.sabino.inventory.strategy.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
class MovimentacaoServiceTest {
  @Test void entradaESaidaAtualizamSaldo() {
    var repo=new MaterialRepository();
    var material=repo.criar("MAT-001","Parafuso",10,3);
    var service=new MovimentacaoService(repo,List.of(new EntradaStrategy(),new SaidaStrategy()));
    assertEquals(15,service.movimentar(new MovimentacaoRequest(material.id(),"ENTRADA",5)).material().saldo());
    assertEquals(11,service.movimentar(new MovimentacaoRequest(material.id(),"SAIDA",4)).material().saldo());
  }
  @Test void saidaAcimaDoSaldoFalha() {
    var repo=new MaterialRepository();
    var material=repo.criar("MAT-002","Porca",2,1);
    var service=new MovimentacaoService(repo,List.of(new EntradaStrategy(),new SaidaStrategy()));
    assertThrows(IllegalArgumentException.class, () -> service.movimentar(new MovimentacaoRequest(material.id(),"SAIDA",5)));
    assertEquals(2,repo.buscar(material.id()).orElseThrow().saldo());
  }
}
