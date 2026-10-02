<h1 align="center">
  🚀 ServiceFlow
</h1>

<p align="center">
  <strong>Sistema inteligente e centralizado para gerenciamento e acompanhamento de chamados.</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk" alt="Java 21" />
  <img src="https://img.shields.io/badge/Spring_Boot-4.1.1-6DB33F?style=for-the-badge&logo=springboot" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/Next.js-16-black?style=for-the-badge&logo=next.js" alt="Next.js" />
  <img src="https://img.shields.io/badge/React-19-61DAFB?style=for-the-badge&logo=react" alt="React" />
  <img src="https://img.shields.io/badge/TypeScript-5.0-3178C6?style=for-the-badge&logo=typescript" alt="TypeScript" />
  <img src="https://img.shields.io/badge/Tailwind_CSS-4.0-38B2AC?style=for-the-badge&logo=tailwind-css" alt="Tailwind CSS" />
</p>

---

## 📋 Sobre o Projeto

O **ServiceFlow** tem como objetivo centralizar a criação, o acompanhamento e o gerenciamento de solicitações de suporte/chamados. Ele permite que usuários registrem chamados enquanto o sistema processa e organiza os dados via API REST.

O projeto é estruturado em arquitetura **Monorepo / Multi-módulo**:

* ⚙️ **Backend:** API REST responsável por regras de negócio e persistência de dados.
* 🎨 **Frontend:** Interface web moderna e reativa para interação final do usuário.

```
ServiceFlow/
├── 📂 backend/      # Aplicação Java + Spring Boot
└── 📂 frontend/     # Aplicação Next.js + React + TypeScript
```

---

## 🛠️ Tecnologias Utilizadas

### ☕ Backend
* **Linguagem:** Java 21
* **Framework:** Spring Boot (Spring Web MVC)
* **Gerenciador de Dependências:** Maven
* **Utilitários:** Lombok
* **Documentação:** SpringDoc OpenAPI / Swagger UI

### ⚛️ Frontend
* **Framework:** Next.js 16 (App Router)
* **Biblioteca UI:** React 19
* **Linguagem:** TypeScript
* **Estilização:** Tailwind CSS 4
* **Qualidade de Código:** ESLint

---

## 📁 Estrutura de Diretórios

```text
ServiceFlow/
│
├── 📂 backend/
│   ├── 📂 src/
│   │   └── 📂 main/
│   │       └── 📂 java/com/colonia/backend/
│   │           ├── 📂 Controller/
│   │           ├── 📂 Service/
│   │           └── 📂 Database/Entitty/
│   └── 📄 pom.xml
│
├── 📂 frontend/
│   ├── 📂 src/
│   ├── 📂 public/
│   ├── 📄 package.json
│   ├── 📄 next.config.ts
│   └── 📄 tsconfig.json
│
└── 📄 README.md
```

---

## ⚙️ Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

* [JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
* [Apache Maven](https://maven.apache.org/) *(ou utilize o wrapper `./mvnw` incluso)*
* [Node.js](https://nodejs.org/) (versão LTS recomendada)
* [npm](https://www.npmjs.com/) ou [yarn](https://yarnpkg.com/)
* IDE de sua preferência (VS Code, IntelliJ IDEA, Eclipse)

---

## 🚀 Executando o Projeto

### 1. Backend (Spring Boot)

Navegue até a pasta do backend:
```bash
cd backend
```

Execute a aplicação:
```bash
# Utilizando Maven instalado
mvn spring-boot:run

# Ou utilizando Maven Wrapper (Linux/Mac)
./mvnw spring-boot:run

# Ou no Windows (PowerShell/CMD)
.\mvnw.cmd spring-boot:run
```
> 📍 A API estará disponível em `http://localhost:8080` (ou na porta configurada).

---

### 2. Frontend (Next.js)

Em um novo terminal, navegue até a pasta do frontend:
```bash
cd frontend
```

Instale as dependências:
```bash
npm install
```

Inicie o servidor de desenvolvimento:
```bash
npm run dev
```
> 📍 A interface estará acessível em `http://localhost:3000`.

---

## 📡 Endpoints da API

### 🔹 Criar Chamado
`POST /chamados`

**Exemplo de Payload:**
```json
{
  "titulo": "Chamado Inicial",
  "nome": "Gabriel",
  "descricao": "Primeiro chamado para teste!!!",
  "dataCriacao": "2026-10-02T14:30:00",
  "status": "Pendente",
  "dataAtualizacao": "2026-10-02T14:30:00",
  "autorChamado": "Jean"
}
```

**Exemplo de Requisição (Fetch API):**
```javascript
fetch("http://localhost:8080/chamados", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  body: JSON.stringify({
    titulo: "Chamado Inicial",
    nome: "Gabriel",
    descricao: "Primeiro chamado para teste!!!",
    dataCriacao: "2026-10-02T14:30:00",
    status: "Pendente",
    dataAtualizacao: "2026-10-02T14:30:00",
    autorChamado: "Jean"
  })
});
```

---

## 🌐 Configuração de CORS

Se o frontend e o backend estiverem rodando em máquinas ou portas distintas na mesma rede local, certifique-se de liberar a origem no Spring Boot:

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://172.30.1.170:8082", "http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
```

---

## 📖 Documentação Interativa (Swagger)

A API conta com documentação interativa gerada automaticamente via SpringDoc OpenAPI.

Com o backend em execução, acesse no navegador:
👉 `http://localhost:8080/swagger-ui/index.html`

---

## 🔄 Fluxo de Comunicação

```text
┌─────────────────┐                 ┌─────────────────┐
│                 │   HTTP / REST   │                 │
│   Frontend      ├────────────────►│    Backend      │
│   (Next.js)     │                 │  (Spring Boot)  │
│                 │◄────────────────┤                 │
└─────────────────┘  JSON Response  └────────┬────────┘
                                             │
                                             ▼
                                    ┌─────────────────┐
                                    │    Chamados     │
                                    │   (/chamados)   │
                                    └─────────────────┘
```

---

## 📦 Scripts Disponíveis (Frontend)

| Comando | Descrição |
| :--- | :--- |
| `npm run dev` | Inicia o servidor de desenvolvimento do Next.js |
| `npm run build` | Compila o projeto para produção |
| `npm start` | Executa a versão compilada de produção |
| `npm run lint` | Roda a verificação de regras de código com ESLint |

---

## 📦 Build do Backend

Para gerar o arquivo `.jar` executável do backend:

```bash
cd backend
mvn clean package
```
O artefato gerado estará localizado no diretório `/backend/target/`.

---

## 📌 Status do Projeto

<p align="center">
  <img src="https://img.shields.io/badge/Status-Em_Desenvolvimento-yellow?style=for-the-badge" alt="Em Desenvolvimento" />
</p>

Novas funcionalidades, regras de validação e persistência em banco de dados serão adicionadas em breve.

---

## 👨‍💻 Autores

Desenvolvido com 💚 para o ecossistema **ServiceFlow**.
