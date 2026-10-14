package com.sabino.inventory.strategy;
import org.springframework.stereotype.Component;
@Component
public class EntradaStrategy implements MovimentacaoStrategy {
  public String tipo() { return "ENTRADA"; }
  public int calcularSaldo(int atual, int quantidade) { return Math.addExact(atual, quantidade); }
}
