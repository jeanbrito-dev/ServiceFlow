<div align="center">

# 🚀 ServiceFlow

### Centralize chamados, acompanhe o atendimento e transforme solicitações dispersas em um fluxo operacional simples.

Uma aplicação full-stack em evolução para gerenciamento de chamados, com interface web em **Next.js/React** e uma **API REST em Java/Spring Boot**.

<p>
  <a href="https://github.com/jeanbrito-dev/ServiceFlow">
    <img src="https://img.shields.io/badge/repositório-GitHub-181717?style=for-the-badge&logo=github" alt="GitHub">
  </a>
  <a href="https://github.com/jeanbrito-dev/ServiceFlow/commits/main/">
    <img src="https://img.shields.io/badge/status-em%20desenvolvimento-F59E0B?style=for-the-badge" alt="Status">
  </a>
</p>

<p>
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=flat-square&logo=openjdk&logoColor=white" alt="Java 21">
  <img src="https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?style=flat-square&logo=springboot&logoColor=white" alt="Spring Boot 4.1.1">
  <img src="https://img.shields.io/badge/Maven-3.9.16-C71A36?style=flat-square&logo=apachemaven&logoColor=white" alt="Maven 3.9.16">
  <img src="https://img.shields.io/badge/Next.js-16.3.8-000000?style=flat-square&logo=nextdotjs&logoColor=white" alt="Next.js 16.3.8">
  <img src="https://img.shields.io/badge/React-19.2.8-61DAFB?style=flat-square&logo=react&logoColor=111111" alt="React 19.2.8">
  <img src="https://img.shields.io/badge/TypeScript-5.x-3178C6?style=flat-square&logo=typescript&logoColor=white" alt="TypeScript 5">
  <img src="https://img.shields.io/badge/Tailwind%20CSS-4.x-06B6D4?style=flat-square&logo=tailwindcss&logoColor=white" alt="Tailwind CSS 4">
  <img src="https://img.shields.io/badge/license-não%20especificada-lightgrey?style=flat-square" alt="Licença não especificada">
</p>

</div>

> **Nota de arquitetura:** o projeto está em estágio inicial/prototipal. A API atualmente mantém os chamados **em memória**, em uma `ArrayList`; não há banco de dados nem JPA em uso no código atual. Os dados são perdidos quando o backend é reiniciado.

---

## 📚 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Principais Funcionalidades](#-principais-funcionalidades)
- [Arquitetura](#-arquitetura)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Estrutura de Diretórios](#-estrutura-de-diretórios)
- [Pré-requisitos](#-pré-requisitos)
- [Configuração do Ambiente](#-configuração-do-ambiente)
- [Instalação e Execução](#-instalação-e-execução)
- [Uso da Aplicação](#-uso-da-aplicação)
- [API REST](#-api-rest)
- [Swagger / OpenAPI](#-swagger--openapi)
- [Build e Validação](#-build-e-validação)
- [CORS e Integração Local](#-cors-e-integração-local)
- [Limitações Atuais](#-limitações-atuais)
- [Como Contribuir](#-como-contribuir)
- [Licença](#-licença)
- [Autor](#-autor)

---

## 📌 Sobre o Projeto

O **ServiceFlow** foi concebido para centralizar a criação, consulta e tratamento de chamados em uma única aplicação. A proposta separa a experiência do usuário da camada de negócio por meio de uma arquitetura **frontend + API REST**, permitindo que a interface web consuma os recursos do backend via HTTP/JSON.

O repositório é organizado como um **monorepo simples**, com dois módulos independentes:

- **`backend/`** — API REST construída com Java 21 e Spring Boot 4.1.1.
- **`frontend/`** — aplicação web construída com Next.js 16, React 19, TypeScript e Tailwind CSS 4.

No estado atual, o backend possui uma implementação de CRUD para chamados e uma operação específica para marcar um chamado como resolvido. O armazenamento é propositalmente simples: uma lista estática em memória inicializada com um chamado de exemplo.

O frontend, por sua vez, possui uma tela inicial, uma tela de login baseada em `localStorage`, uma página de cadastro, um dashboard que consulta a API para exibir chamados pendentes, uma camada centralizada para as chamadas à API e uma pasta compartilhada para definição de tipos e interfaces utilizadas em diferentes partes da aplicação.

### 🎯 Objetivo do sistema

O fluxo principal é:

```text
Usuário
  │
  ├── Acessa o frontend
  │
  ├── Realiza o login ou cadastro
  │
  └── Abre o dashboard
           │
           │ HTTP/JSON
           ▼
    API Spring Boot
           │
           ▼
  Lista de chamados em memória
```

---

## ✨ Principais Funcionalidades

- 📝 **Criação de chamados** pela API.
- 🔎 **Consulta de todos os chamados**.
- 🎯 **Consulta de chamado por ID**.
- ✏️ **Edição de chamados**.
- 🗑️ **Exclusão de chamados**.
- ✅ **Resolução de chamados**, atualizando status e data de atualização.
- 📊 **Dashboard web** com separação visual entre chamados pendentes e área de chamados do usuário.
- 🔐 **Fluxo de login local** usando `localStorage` para controlar o estado de acesso da interface.
- 👤 **Página de cadastro** adicionada ao frontend para entrada de novos usuários.
- 🌐 **Centralização das chamadas à API** por meio de `frontend/service/api.ts`.
- 🧩 **Interfaces e tipos compartilhados** organizados em `frontend/types/`, evitando a repetição de definições como a interface `Chamado`.
- 🧪 **Teste de contexto do Spring Boot** incluído no backend.
- 🧩 **API desacoplada do frontend**, permitindo consumo por outros clientes HTTP.
- 📖 **Documentação interativa com Swagger/OpenAPI** no backend.
- 🎨 **Interface estilizada com Tailwind CSS 4**.

> O frontend atual está concentrado principalmente na leitura de chamados. Os endpoints de criação, edição, exclusão e resolução já existem no backend, mas ainda não estão completamente integrados à interface web.

---

## 🏗️ Arquitetura

```text
┌──────────────────────────────────────────────────────┐
│                     ServiceFlow                      │
├──────────────────────────────────────────────────────┤
│                                                      │
│  ┌────────────────────┐       HTTP / JSON            │
│  │     Frontend       │ ───────────────────────────┐ │
│  │                    │                            │ │
│  │ Next.js 16         │                            ▼ │
│  │ React 19           │                ┌───────────────┐
│  │ TypeScript         │                │    Backend    │
│  │ Tailwind CSS 4     │                │               │
│  └─────────┬──────────┘                │ Spring Boot   │
│            │                           │ Java 21       │
│            │                           │ REST API      │
│            ▼                           └───────┬───────┘
│     Browser / Web UI                           │
│                                                ▼
│                                       ┌────────────────┐
│                                       │ ArrayList em   │
│                                       │ memória        │
│                                       └────────────────┘
│                                                      │
└──────────────────────────────────────────────────────┘

Frontend: http://localhost:3000
Backend:  http://localhost:8082
API:      http://localhost:8082/api
```

### Camadas do backend

A implementação atual segue uma separação básica de responsabilidades:

```text
Controller
  │
  ▼
Service
  │
  ▼
Estado em memória (`ArrayList<ChamadoEntity>`)
```

- **Controller** — expõe os endpoints HTTP e delega as operações ao serviço.
- **Service** — concentra as operações de negócio e o gerenciamento da lista de chamados.
- **DTO** — representa os dados recebidos nas operações de criação e edição.
- **Entity/Model** — representa o chamado retornado pela API.

Apesar do diretório se chamar `Database/Entitty`, `ChamadoEntity` **não é uma entidade JPA** no estado atual: não possui `@Entity`, `@Id` ou mapeamentos de persistência.

### Organização do frontend

O frontend passou a utilizar uma separação adicional para evitar repetição e concentrar responsabilidades:

```text
app/
service/
  └── api.ts
types/
  └── tipos compartilhados
```

- **`app/`** — concentra as páginas e rotas da aplicação utilizando o App Router do Next.js.
- **`service/api.ts`** — centraliza as chamadas HTTP realizadas pelo frontend para a API do backend, evitando que a lógica de comunicação fique repetida nas páginas.
- **`types/`** — concentra interfaces e tipos compartilhados entre diferentes componentes e páginas, incluindo a definição utilizada para representar um `Chamado`.

Essa organização permite que alterações na URL ou na forma de comunicação com a API sejam feitas de maneira mais centralizada e que interfaces utilizadas em diferentes partes da aplicação não precisem ser redefinidas individualmente.

---

## 🛠️ Tecnologias Utilizadas

### ⚙️ Backend

| Tecnologia | Versão | Uso |
|---|---:|---|
| Java | 21 | Linguagem principal |
| Spring Boot | 4.1.1 | Framework da API |
| Spring Web MVC | 4.1.1 | Endpoints REST |
| SpringDoc OpenAPI | 3.1.0 | Swagger / OpenAPI |
| Lombok | Gerenciado pelo Spring Boot | Geração de getters, setters, builders e construtores |
| Maven | 3.9.16 via Maven Wrapper | Build e gerenciamento de dependências |
| JUnit / Spring Boot Test | Gerenciado pelo Spring Boot | Teste de carregamento do contexto |

### 🎨 Frontend

| Tecnologia | Versão | Uso |
|---|---:|---|
| Next.js | 16.3.8 | Framework web com App Router |
| React | 19.2.8 | Camada de UI |
| React DOM | 19.2.8 | Renderização web |
| TypeScript | 5.x | Tipagem estática |
| Tailwind CSS | 4.x | Estilização |
| ESLint | 9.x | Qualidade e linting |
| `eslint-config-next` | 16.3.8 | Regras específicas do Next.js |

### 🗄️ Banco de Dados

**Nenhum banco de dados é utilizado na versão atual.** O estado dos chamados fica em memória no processo do backend.

---

## 📁 Estrutura de Diretórios

```text
ServiceFlow/
├── backend/
│   ├── .mvn/
│   │   └── wrapper/                  # Configuração do Maven Wrapper
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/colonia/backend/
│   │   │   │   ├── Controller/
│   │   │   │   │   └── ChamadoController.java
│   │   │   │   ├── Database/Entitty/
│   │   │   │   │   └── ChamadoEntity.java
│   │   │   │   ├── Dto/
│   │   │   │   │   └── ChamadoDto.java
│   │   │   │   ├── Service/
│   │   │   │   │   └── ChamadoService.java
│   │   │   │   └── BackendApplication.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   │       └── java/com/colonia/backend/
│   │           └── BackendApplicationTests.java
│   ├── mvnw
│   ├── mvnw.cmd
│   └── pom.xml
│
├── frontend/
│   ├── app/
│   │   ├── cadastro/
│   │   │   └── page.tsx
│   │   ├── dashboard/
│   │   │   └── page.tsx
│   │   ├── login/
│   │   │   └── page.tsx
│   │   ├── globals.css
│   │   ├── layout.tsx
│   │   └── page.tsx
│   ├── public/
│   ├── service/
│   │   └── api.ts
│   ├── types/
│   │   └── tipos compartilhados
│   ├── package.json
│   ├── package-lock.json
│   ├── postcss.config.mjs
│   ├── next.config.ts
│   ├── eslint.config.mjs
│   └── tsconfig.json
│
└── README.md
```

---

## ✅ Pré-requisitos

Antes de executar o projeto, instale:

- **Git** — para clonar o repositório.
- **JDK 21** — obrigatório para compilar e executar o backend.
- **Node.js** — versão compatível com Next.js 16; recomenda-se utilizar uma versão LTS atual.
- **npm** — instalado junto com o Node.js.

> O backend já possui **Maven Wrapper (`mvnw` / `mvnw.cmd`)** configurado para baixar e usar **Maven 3.9.16**, portanto a instalação global do Maven não é necessária.

Verifique as instalações:

```bash
git --version
java -version
node --version
npm --version
```

---

## ⚙️ Configuração do Ambiente

O frontend utiliza a variável de ambiente `NEXT_PUBLIC_API_URL` para definir a URL base da API.

O valor esperado para desenvolvimento local é:

```env
NEXT_PUBLIC_API_URL=http://localhost:8082/api
```

Crie o arquivo `frontend/.env.example` com o conteúdo abaixo e, em seguida, copie-o para `frontend/.env.local`:

### Linux / macOS / Git Bash

```bash
cd frontend
cp .env.example .env.local
```

### Windows PowerShell

```powershell
cd frontend
Copy-Item .env.example .env.local
```

Depois, confirme que `frontend/.env.local` contém:

```env
NEXT_PUBLIC_API_URL=http://localhost:8082/api
```

> Como a variável começa com `NEXT_PUBLIC_`, ela é exposta ao código executado no navegador. Não coloque segredos, tokens ou credenciais nessa variável.

---

## 🚀 Instalação e Execução

### 1. Clonar o repositório

```bash
git clone https://github.com/jeanbrito-dev/ServiceFlow.git
cd ServiceFlow
```

### 2. Configurar o backend

Em um primeiro terminal:

```bash
cd backend
```

No Linux/macOS/Git Bash:

```bash
./mvnw clean package
```

No Windows PowerShell/CMD:

```powershell
.\mvnw.cmd clean package
```

Para iniciar o servidor diretamente:

**Linux/macOS/Git Bash**

```bash
./mvnw spring-boot:run
```

**Windows PowerShell/CMD**

```powershell
.\mvnw.cmd spring-boot:run
```

O backend será iniciado em:

```text
http://localhost:8082
```

A API base será:

```text
http://localhost:8082/api
```

### 3. Configurar e iniciar o frontend

Abra um segundo terminal a partir da raiz do repositório:

```bash
cd ServiceFlow/frontend
```

Instale as dependências:

```bash
npm install
```

Crie o arquivo de ambiente:

**Linux/macOS/Git Bash**

```bash
cp .env.example .env.local
```

**Windows PowerShell**

```powershell
Copy-Item .env.example .env.local
```

Inicie o ambiente de desenvolvimento:

```bash
npm run dev
```

A interface ficará disponível em:

```text
http://localhost:3000
```

### 4. Fluxo recomendado de inicialização

Ao final, você deverá ter dois processos ativos:

```text
Terminal 1 → backend  → http://localhost:8082
Terminal 2 → frontend → http://localhost:3000
```

Acesse:

```text
http://localhost:3000
```

---

## 🖥️ Uso da Aplicação

O frontend atual possui quatro telas principais:

### Página inicial — `/`

Apresenta o nome do ServiceFlow e direciona o usuário para:

- `/login`, caso o estado de login não esteja registrado;
- `/dashboard`, caso `localStorage.isLogged` esteja definido como `"true"`.

### Login — `/login`

A tela possui campos de e-mail e senha, porém o código atual **não valida as credenciais contra o backend**. Ao enviar o formulário:

1. `localStorage.setItem("isLogged", "true")` é executado;
2. o estado local é atualizado;
3. o usuário é redirecionado para `/dashboard`.

Portanto, o login atual é um **mecanismo de demonstração de fluxo**, não um sistema de autenticação real.

### Cadastro — `/cadastro`

O frontend possui uma página específica para cadastro de usuários.

A página foi adicionada para complementar o fluxo de entrada da aplicação, separando a criação de uma conta da tela de login.

O cadastro ainda faz parte do fluxo inicial do frontend e não representa uma autenticação persistida e validada pelo backend enquanto não houver uma implementação de usuários e autenticação no servidor.

### Dashboard — `/dashboard`

Ao carregar a tela, o frontend consulta a API para obter os chamados disponíveis.

A comunicação com o backend é centralizada em:

```text
frontend/service/api.ts
```

A utilização de uma camada de serviço evita que cada página precise implementar diretamente a construção das requisições HTTP.

Os chamados recebidos são armazenados no estado React e os que possuem `status === "Pendente"` são exibidos na seção **Chamados em aberto**.

### Tipos compartilhados

As interfaces utilizadas pelo frontend foram organizadas na pasta:

```text
frontend/types/
```

Entre elas está a definição de `Chamado`, utilizada para tipar os dados recebidos da API e evitar a duplicação da mesma interface em diferentes páginas ou componentes.

Essa organização facilita a manutenção porque uma alteração na estrutura de um chamado pode ser refletida em um único tipo compartilhado.

---

## 📡 API REST

### Base URL

```text
http://localhost:8082/api
```

### Endpoints disponíveis

| Método | Endpoint | Finalidade | Status esperado |
|:---:|---|---|:---:|
| `GET` | `/chamados` | Lista todos os chamados | `200 OK` |
| `GET` | `/chamados/{id}` | Busca um chamado pelo ID | `200 OK` |
| `POST` | `/chamados` | Cria um novo chamado | `201 Created` |
| `PUT` | `/chamados/{id}` | Atualiza um chamado existente | `200 OK` / `404 Not Found` |
| `DELETE` | `/chamados/{id}` | Remove um chamado | `204 No Content` / `404 Not Found` |
| `GET` | `/resolver-chamado?id={id}` | Marca um chamado como resolvido | `200 OK` / `404 Not Found` |

### Modelo de dados

```json
{
  "id": 1,
  "titulo": "Chamado Inicial",
  "nome": "Gabriel",
  "descricao": "Primeiro chamado para teste!!!",
  "dataCriacao": "2026-10-02T14:30:00",
  "status": "Pendente",
  "dataAtualizacao": "2026-10-02T14:30:00",
  "autorChamado": "Jean"
}
```

### `GET /api/chamados`

Retorna a lista completa armazenada em memória.

```bash
curl http://localhost:8082/api/chamados
```

### `GET /api/chamados/{id}`

Exemplo:

```bash
curl http://localhost:8082/api/chamados/1
```

> Observação: a implementação atual responde `200 OK` mesmo quando o ID não é encontrado, retornando `null` no corpo. Esse comportamento pode ser aprimorado futuramente para `404 Not Found`.

### `POST /api/chamados`

Payload:

```json
{
  "titulo": "Novo chamado",
  "nome": "Maria",
  "descricao": "Solicitação de suporte",
  "dataCriacao": "2026-10-05T10:00:00",
  "status": "Pendente",
  "dataAtualizacao": "2026-10-05T10:00:00",
  "autorChamado": "Maria"
}
```

Exemplo:

```bash
curl -X POST http://localhost:8082/api/chamados \
  -H "Content-Type: application/json" \
  -d '{
    "titulo":"Novo chamado",
    "nome":"Maria",
    "descricao":"Solicitação de suporte",
    "dataCriacao":"2026-10-05T10:00:00",
    "status":"Pendente",
    "dataAtualizacao":"2026-10-05T10:00:00",
    "autorChamado":"Maria"
  }'
```

### `PUT /api/chamados/{id}`

Exemplo:

```bash
curl -X PUT http://localhost:8082/api/chamados/1 \
  -H "Content-Type: application/json" \
  -d '{
    "titulo":"Chamado atualizado",
    "nome":"Gabriel",
    "descricao":"Descrição atualizada",
    "dataCriacao":"2026-10-02T14:30:00",
    "status":"Em atendimento",
    "dataAtualizacao":"2026-10-05T10:30:00",
    "autorChamado":"Jean"
  }'
```

### `DELETE /api/chamados/{id}`

```bash
curl -X DELETE http://localhost:8082/api/chamados/1
```

### `GET /api/resolver-chamado?id={id}`

A operação atualiza o status para `"Resolvido!"` e define `dataAtualizacao` com o horário atual do servidor.

```bash
curl "http://localhost:8082/api/resolver-chamado?id=1"
```

---

## 📖 Swagger / OpenAPI

O backend utiliza **SpringDoc OpenAPI** para disponibilizar uma documentação interativa da API.

Com o backend em execução, acesse:

```text
http://localhost:8082/swagger-ui/index.html
```

A interface do Swagger permite inspecionar e testar os endpoints sem depender do frontend.

---

## 🔨 Build e Validação

### Backend

Gerar o artefato `.jar`:

**Linux/macOS/Git Bash**

```bash
cd backend
./mvnw clean package
```

**Windows**

```powershell
cd backend
.\mvnw.cmd clean package
```

O artefato será gerado em:

```text
backend/target/
```

Executar testes do backend:

**Linux/macOS/Git Bash**

```bash
./mvnw test
```

**Windows**

```powershell
.\mvnw.cmd test
```

O repositório contém atualmente um teste básico de carregamento do contexto do Spring Boot (`contextLoads`).

### Frontend

Dentro de `frontend/`:

```bash
npm run lint
npm run build
npm start
```

O significado dos scripts é:

| Comando | Descrição |
|---|---|
| `npm run dev` | Inicia o servidor de desenvolvimento |
| `npm run build` | Gera o build de produção |
| `npm start` | Executa o build de produção |
| `npm run lint` | Executa o ESLint |

---

## 🌐 CORS e Integração Local

O frontend executa requisições diretamente do navegador para `http://localhost:8082/api`. Como frontend e backend usam portas diferentes (`3000` e `8082`), o navegador aplica as regras de **CORS**.

No código atual, `ChamadoController` não declara `@CrossOrigin` e não há uma configuração global de CORS no backend. Em ambientes que bloquearem a chamada cross-origin, a interface poderá carregar normalmente, mas a busca de chamados falhará no navegador.

### Sintoma típico

No console do navegador:

```text
Access to fetch at 'http://localhost:8082/api/chamados'
from origin 'http://localhost:3000' has been blocked by CORS policy
```

### Configuração recomendada para desenvolvimento

Crie uma configuração MVC no backend, por exemplo `WebConfig.java`:

```java
package com.colonia.backend.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
```

Depois, reinicie o backend:

```bash
cd backend
./mvnw spring-boot:run
```

> Para produção, substitua `http://localhost:3000` pelo domínio real do frontend e evite liberar origens desnecessárias.

---

## ⚠️ Limitações Atuais

O projeto apresenta uma base funcional, mas ainda possui características de protótipo que devem ser consideradas antes de um uso em produção:

- 💾 **Persistência em memória:** reiniciar o backend apaga os chamados criados durante a execução.
- 🔐 **Autenticação simulada:** o login usa `localStorage` e não valida e-mail ou senha no backend.
- 🛡️ **Sem autorização:** não há Spring Security, JWT, sessão de servidor ou controle de permissões.
- 👤 **Cadastro ainda não integrado a uma autenticação real:** a página de cadastro existe no frontend, mas ainda não representa um sistema completo de criação e persistência de usuários no backend.
- 🛡️ **Sem validação de entrada:** os DTOs não utilizam Bean Validation (`@NotNull`, `@Size`, etc.).
- ❗ **Tratamento de erros simplificado:** a busca por ID inexistente ainda retorna `200` com `null`.
- 🌐 **CORS não configurado por padrão:** pode exigir ajuste para comunicação entre `localhost:3000` e `localhost:8082`.
- 🔄 **Integração parcial do frontend:** atualmente o dashboard faz leitura dos chamados; as operações completas de CRUD ainda não estão conectadas à UI.
- 🗃️ **Sem banco de dados:** o pacote chamado `Database/Entitty` é apenas organizacional; não existe persistência JPA no código atual.
- 🚪 **Sem proteção de rota real:** não há um guard de rota ou validação de sessão no backend impedindo o acesso direto a `/dashboard`.
- 🧩 **Camada de API centralizada:** as chamadas HTTP do frontend foram concentradas em `service/api.ts`, mas a integração completa das operações do CRUD com a interface ainda está em evolução.
- 📦 **Tipos compartilhados em evolução:** a pasta `types/` centraliza interfaces reutilizadas, reduzindo duplicações, mas a organização dos tipos poderá crescer conforme novas entidades forem adicionadas ao sistema.

Essas limitações também ajudam a definir o roadmap natural do projeto: persistência relacional, autenticação real, autorização por perfil, validação, tratamento global de exceções e integração completa do CRUD no frontend.

---

## 🤝 Como Contribuir

Contribuições são bem-vindas. Para manter um fluxo simples e rastreável:

### 1. Faça um fork

No GitHub, utilize **Fork** para criar uma cópia do repositório na sua conta.

### 2. Clone o seu fork

```bash
git clone https://github.com/SEU_USUARIO/ServiceFlow.git
cd ServiceFlow
```

### 3. Crie uma branch de trabalho

```bash
git checkout -b feat/minha-melhoria
```

Exemplos de nomes:

```text
feat/persistencia-postgresql
fix/cors-api
refactor/chamado-service
docs/api-endpoints
```

### 4. Faça as alterações e valide

Backend:

```bash
cd backend
./mvnw test
```

Frontend:

```bash
cd ../frontend
npm run lint
npm run build
```

### 5. Faça o commit

```bash
git add .
git commit -m "feat: adiciona melhoria no gerenciamento de chamados"
```

### 6. Envie a branch

```bash
git push origin feat/minha-melhoria
```

### 7. Abra um Pull Request

Abra um **Pull Request** no GitHub descrevendo:

- o problema ou necessidade;
- a solução adotada;
- como testar;
- possíveis impactos ou limitações.

Para mudanças maiores de arquitetura, prefira discutir a proposta em uma Issue antes de implementar.

---

## 📄 Licença

O repositório público analisado **não contém um arquivo de licença (`LICENSE`) e não declara uma licença formal no README atual**.

Por isso, este README não atribui uma licença ao código sem autorização do autor. Antes de reutilizar, distribuir ou incorporar o projeto em outro produto, confirme a licença desejada com o mantenedor.

---

## 👨‍💻 Autor

Desenvolvido por **Jean Brito e Gabriel Carmo**.

<p align="center">
  <a href="https://github.com/jeanbritodev">
    <img src="https://img.shields.io/badge/Jean%20Brito-GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="Jean Brito no GitHub">
  </a>
  <a href="https://github.com/iamytz">
    <img src="https://img.shields.io/badge/Gabriel%20Carmo-GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="Gabriel Carmo no GitHub">
  </a>
</p>

**Repositório:** https://github.com/jeanbrito-dev/ServiceFlow

---

<div align="center">

### 💚 ServiceFlow

**Organize chamados. Simplifique o atendimento. Evolua o fluxo.**

</div>
