package com.sabino.inventory.facade;
import com.sabino.inventory.dto.*;
import com.sabino.inventory.model.Material;
import com.sabino.inventory.repository.MaterialRepository;
import com.sabino.inventory.service.MovimentacaoService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class EstoqueFacade {
  private final MaterialRepository materiais;
  private final MovimentacaoService movimentacoes;
  public EstoqueFacade(MaterialRepository materiais, MovimentacaoService movimentacoes) {
    this.materiais=materiais; this.movimentacoes=movimentacoes;
  }
  public Material cadastrar(CriarMaterialRequest req) {
    return materiais.criar(req.codigo(),req.descricao(),req.saldoInicial(),req.estoqueMinimo());
  }
  public List<Material> listar() { return materiais.listar(); }
  public Material buscar(Long id) { return materiais.buscar(id).orElseThrow(() -> new java.util.NoSuchElementException("Material não encontrado")); }
  public MovimentacaoResultado movimentar(MovimentacaoRequest req) { return movimentacoes.movimentar(req); }
}
