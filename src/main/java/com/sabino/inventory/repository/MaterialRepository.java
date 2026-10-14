package com.sabino.inventory.repository;
import com.sabino.inventory.model.Material;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class MaterialRepository {
  private final Map<Long, Material> materiais = new ConcurrentHashMap<>();
  private final AtomicLong sequence = new AtomicLong();
  public synchronized Material criar(String codigo, String descricao, int saldo, int minimo) {
    if (materiais.values().stream().anyMatch(m -> m.codigo().equalsIgnoreCase(codigo)))
      throw new IllegalArgumentException("Código já cadastrado");
    long id=sequence.incrementAndGet();
    Material material=new Material(id,codigo,descricao,saldo,minimo);
    materiais.put(id,material); return material;
  }
  public Optional<Material> buscar(Long id) { return Optional.ofNullable(materiais.get(id)); }
  public List<Material> listar() { return materiais.values().stream().sorted(Comparator.comparing(Material::id)).toList(); }
  public synchronized Material atualizarSaldo(Long id, java.util.function.IntUnaryOperator operacao) {
    Material atual=buscar(id).orElseThrow(() -> new NoSuchElementException("Material não encontrado"));
    int novoSaldo=operacao.applyAsInt(atual.saldo());
    Material atualizado=atual.comSaldo(novoSaldo); materiais.put(id,atualizado); return atualizado;
  }
}
