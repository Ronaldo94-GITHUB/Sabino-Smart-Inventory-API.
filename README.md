# Sabino Smart Inventory API

Projeto acadêmico e de portfólio em **Java 17 + Spring Boot**, voltado ao controle de materiais e movimentações de estoque. Implementa os padrões clássicos **Strategy**, **Facade** e demonstra o escopo **Singleton** dos beans do Spring.

## Funcionalidades da versão 0.1

- Cadastrar materiais com código, descrição, saldo inicial e estoque mínimo.
- Consultar lista de materiais e item por ID.
- Registrar entradas e saídas, sem permitir saldo negativo.
- Identificar quando o saldo fica abaixo do mínimo.
- Validar requisições e padronizar erros HTTP.

**Observação:** o repositório utiliza armazenamento **em memória**. Os dados são perdidos quando a aplicação reinicia. Não há integração real com SAP, alertas ou autenticação nesta versão.

## GoF e Spring

| Padrão | Onde | Por quê |
|---|---|---|
| Strategy (GoF) | `strategy/` | Algoritmos diferentes para entrada e saída de estoque |
| Facade (GoF) | `facade/EstoqueFacade.java` | Interface simplificada para controller |
| Singleton (escopo Spring) | `@Service`/`@Repository` | Uma instância gerenciada por contexto, por padrão; não é o Singleton GoF implementado manualmente |

## Executar

Requisitos: JDK 17+ e Maven 3.9+.

```powershell
cd C:\Projetos\sabino-smart-inventory-api
java -version
mvn -version
mvn clean test
mvn spring-boot:run
```

A API inicia em `http://localhost:8080`.

## Endpoints

| Método | Caminho | Descrição |
|---|---|---|
| POST | `/api/materiais` | Cadastrar material |
| GET | `/api/materiais` | Listar materiais |
| GET | `/api/materiais/{id}` | Buscar material |
| POST | `/api/movimentacoes` | Entrada ou saída |

### Exemplo no PowerShell

```powershell
$material = Invoke-RestMethod -Uri 'http://localhost:8080/api/materiais' -Method POST -ContentType 'application/json' -Body '{"codigo":"ROL-001","descricao":"Rolamento 6204","saldoInicial":12,"estoqueMinimo":5}'
$body = @{materialId=$material.id;tipo='SAIDA';quantidade=8} | ConvertTo-Json
Invoke-RestMethod -Uri 'http://localhost:8080/api/movimentacoes' -Method POST -ContentType 'application/json' -Body $body
Invoke-RestMethod -Uri 'http://localhost:8080/api/materiais'
```

## Roadmap

1. v0.1 — API REST, padrões GoF e testes (este pacote).
2. v0.2 — PostgreSQL, Spring Data JPA e migrations.
3. v0.3 — Reservas de material, solicitações de compra e auditoria.
4. v0.4 — JWT/RBAC, Docker e CI GitHub Actions.
5. v0.5 — Integrações externas através de adaptadores, sem acesso direto ao SAP.

## Publicar no GitHub

```powershell
git init
git add .
git commit -m "feat: inventory API with GoF design patterns"
git branch -M main
# Crie um repositorio vazio no GitHub e copie a URL dele.
git remote add origin URL_DO_REPOSITORIO
git push -u origin main
```

Projeto desenvolvido para estudo. Nenhuma validação de build é afirmada antes de executar o Maven localmente.
