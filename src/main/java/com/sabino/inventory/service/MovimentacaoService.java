package com.sabino.inventory.service;
import com.sabino.inventory.dto.*;
import com.sabino.inventory.model.Material;
import com.sabino.inventory.repository.MaterialRepository;
import com.sabino.inventory.strategy.MovimentacaoStrategy;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service // Singleton do container Spring (escopo padrão)
public class MovimentacaoService {
  private final MaterialRepository repositorio;
  private final Map<String, MovimentacaoStrategy> estrategias;
  public MovimentacaoService(MaterialRepository repositorio, List<MovimentacaoStrategy> estrategias) {
    this.repositorio=repositorio;
    this.estrategias=estrategias.stream().collect(Collectors.toUnmodifiableMap(s -> s.tipo().toUpperCase(Locale.ROOT), Function.identity()));
  }
  public MovimentacaoResultado movimentar(MovimentacaoRequest req) {
    String tipo=req.tipo().trim().toUpperCase(Locale.ROOT);
    MovimentacaoStrategy estrategia=Optional.ofNullable(estrategias.get(tipo))
      .orElseThrow(() -> new IllegalArgumentException("Tipo inválido. Use ENTRADA ou SAIDA"));
    Material material=repositorio.atualizarSaldo(req.materialId(), saldo -> estrategia.calcularSaldo(saldo, req.quantidade()));
    return new MovimentacaoResultado(tipo,req.quantidade(),material,material.saldo()<material.estoqueMinimo());
  }
}
