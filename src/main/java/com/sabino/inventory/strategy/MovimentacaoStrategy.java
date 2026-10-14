package com.sabino.inventory.strategy;
public interface MovimentacaoStrategy {
  String tipo();
  int calcularSaldo(int saldoAtual, int quantidade);
}
