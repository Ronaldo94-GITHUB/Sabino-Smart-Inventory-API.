package com.sabino.inventory.controller;
import com.sabino.inventory.dto.*;
import com.sabino.inventory.facade.EstoqueFacade;
import com.sabino.inventory.model.Material;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api")
public class EstoqueController {
  private final EstoqueFacade estoque;
  public EstoqueController(EstoqueFacade estoque) { this.estoque=estoque; }
  @PostMapping("/materiais") @ResponseStatus(HttpStatus.CREATED)
  public Material cadastrar(@RequestBody @Valid CriarMaterialRequest req) { return estoque.cadastrar(req); }
  @GetMapping("/materiais") public List<Material> listar() { return estoque.listar(); }
  @GetMapping("/materiais/{id}") public Material buscar(@PathVariable Long id) { return estoque.buscar(id); }
  @PostMapping("/movimentacoes") public MovimentacaoResultado movimentar(@RequestBody @Valid MovimentacaoRequest req) { return estoque.movimentar(req); }
}
