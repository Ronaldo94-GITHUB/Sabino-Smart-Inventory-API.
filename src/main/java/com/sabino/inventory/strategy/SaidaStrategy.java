package com.sabino.inventory.strategy;
import org.springframework.stereotype.Component;
@Component
public class SaidaStrategy implements MovimentacaoStrategy {
  public String tipo() { return "SAIDA"; }
  public int calcularSaldo(int atual, int quantidade) {
    if (atual < quantidade) throw new IllegalArgumentException("Saldo insuficiente");
    return atual - quantidade;
  }
}
