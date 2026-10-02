🚀 ServiceFlow

Sistema de gerenciamento de chamados desenvolvido com Java + Spring Boot no backend e Next.js + React + TypeScript no frontend.

O ServiceFlow tem como objetivo centralizar a criação, acompanhamento e gerenciamento de chamados, permitindo que usuários registrem solicitações e que a aplicação processe essas informações por meio de uma API REST.

📋 Sobre o projeto

O projeto é dividido em duas aplicações principais:

Backend: API REST responsável pelo gerenciamento dos chamados.

Frontend: Interface web utilizada para interação com o sistema.

ServiceFlow/
├── backend/
└── frontend/

🛠️ Tecnologias
Backend

Java 21

Spring Boot

Spring Web MVC

Maven

Lombok

SpringDoc OpenAPI / Swagger

O backend utiliza Spring Boot e Java 21. A configuração do projeto pode ser encontrada no pom.xml. {"fallbackMarkdown":"(GitHub
)","reference":{"matched_text":"","prefix":null,"start_idx":1289,"end_idx":1306,"safe_urls":["https://raw.githubusercontent.com/jeanbrito-dev/ServiceFlow/main/backend/pom.xml"],"refs":[],"alt":"(GitHub
)","prompt_text":null,"type":"grouped_webpages","fallback_items":null,"error":null,"status":"done","items":[{"title":"","url":"https://raw.githubusercontent.com/jeanbrito-dev/ServiceFlow/main/backend/pom.xml","attribution":"GitHub","pub_date":null,"snippet":null,"attribution_segments":null,"supporting_websites":[],"refs":[{"turn_index":2,"ref_type":"view","ref_index":0}],"hue":null,"attributions":null}],"style":null},"showLoginRequiredCard":false}

Frontend

Next.js 16

React 19

TypeScript

Tailwind CSS 4

ESLint

O frontend utiliza Next.js como framework principal e React para construção da interface. {"fallbackMarkdown":"(GitHub
)","reference":{"matched_text":"","prefix":null,"start_idx":1476,"end_idx":1493,"safe_urls":["https://raw.githubusercontent.com/jeanbrito-dev/ServiceFlow/main/frontend/package.json"],"refs":[],"alt":"(GitHub
)","prompt_text":null,"type":"grouped_webpages","fallback_items":null,"error":null,"status":"done","items":[{"title":"","url":"https://raw.githubusercontent.com/jeanbrito-dev/ServiceFlow/main/frontend/package.json","attribution":"GitHub","pub_date":null,"snippet":null,"attribution_segments":null,"supporting_websites":[],"refs":[{"turn_index":2,"ref_type":"view","ref_index":1}],"hue":null,"attributions":null}],"style":null},"showLoginRequiredCard":false}

📁 Estrutura do projeto
ServiceFlow/
│
├── backend/
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── com/
│   │               └── colonia/
│   │
│   └── pom.xml
│
├── frontend/
│   ├── src/
│   ├── public/
│   ├── package.json
│   ├── next.config.ts
│   └── tsconfig.json
│
└── README.md

⚙️ Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

Java 21

Maven

Node.js

npm

Também é recomendado utilizar uma IDE como IntelliJ IDEA, Eclipse ou VS Code.

🚀 Executando o Backend

Entre no diretório do backend:

cd backend


Execute a aplicação utilizando Maven:

mvn spring-boot:run


Ou, caso esteja utilizando o Maven Wrapper:

./mvnw spring-boot:run


No Windows:

mvnw.cmd spring-boot:run


Após iniciar, a API estará disponível no endereço configurado pela aplicação.

💻 Executando o Frontend

Entre no diretório do frontend:

cd frontend


Instale as dependências:

npm install


Execute o servidor de desenvolvimento:

npm run dev


O Next.js disponibilizará a aplicação no endereço indicado no terminal, normalmente:

http://localhost:3000

📡 API

O backend disponibiliza endpoints REST para gerenciamento dos chamados.

Criar chamado

POST

/chamados


Exemplo de requisição:

{
  "titulo": "Chamado Inicial",
  "nome": "Gabriel",
  "descricao": "Primeiro chamado para teste!!!",
  "dataCriacao": "2026-10-02T14:30:00",
  "status": "Pendente",
  "dataAtualizacao": "2026-10-02T14:30:00",
  "autorChamado": "Jean"
}


Exemplo utilizando fetch:

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


Ajuste a URL e a porta de acordo com a configuração utilizada no ambiente.

🌐 CORS

Para permitir que o frontend faça requisições para o backend, o projeto possui configuração de CORS.

Em ambiente de desenvolvimento, caso frontend e backend estejam sendo executados em máquinas diferentes na mesma rede, a origem do frontend deve ser adicionada à configuração de CORS.

Exemplo:

registry.addMapping("/**")
        .allowedOrigins("http://172.30.1.170:8082")
        .allowedMethods(
            "GET",
            "POST",
            "PUT",
            "DELETE",
            "OPTIONS"
        )
        .allowedHeaders("*");


A origem deve incluir o protocolo (http:// ou https://).

📖 Documentação da API

O backend possui integração com SpringDoc OpenAPI, permitindo disponibilizar documentação interativa da API através do Swagger UI. {"fallbackMarkdown":"(GitHub
)","reference":{"matched_text":"","prefix":null,"start_idx":4528,"end_idx":4545,"safe_urls":["https://raw.githubusercontent.com/jeanbrito-dev/ServiceFlow/main/backend/pom.xml"],"refs":[],"alt":"(GitHub
)","prompt_text":null,"type":"grouped_webpages","fallback_items":null,"error":null,"status":"done","items":[{"title":"","url":"https://raw.githubusercontent.com/jeanbrito-dev/ServiceFlow/main/backend/pom.xml","attribution":"GitHub","pub_date":null,"snippet":null,"attribution_segments":null,"supporting_websites":[],"refs":[{"turn_index":2,"ref_type":"view","ref_index":0}],"hue":null,"attributions":null}],"style":null},"showLoginRequiredCard":false}

Com a aplicação em execução, a documentação pode ser acessada normalmente através de:

http://localhost:8080/swagger-ui/index.html


A porta pode variar conforme a configuração da aplicação.

🔄 Fluxo da aplicação
┌──────────────┐
│   Frontend   │
│   Next.js    │
└──────┬───────┘
       │
       │ HTTP / REST
       ▼
┌──────────────┐
│   Backend    │
│ Spring Boot  │
└──────┬───────┘
       │
       ▼
┌──────────────┐
│   Chamados   │
│   /chamados  │
└──────────────┘

🧪 Scripts do Frontend

O frontend possui os seguintes scripts:

npm run dev


Executa o ambiente de desenvolvimento.

npm run build


Gera a versão de produção da aplicação.

npm start


Inicia a aplicação Next.js em modo de produção.

npm run lint


Executa a análise de código utilizando ESLint.

Esses scripts estão definidos no package.json do frontend. {"fallbackMarkdown":"(GitHub
)","reference":{"matched_text":"","prefix":null,"start_idx":5458,"end_idx":5475,"safe_urls":["https://raw.githubusercontent.com/jeanbrito-dev/ServiceFlow/main/frontend/package.json"],"refs":[],"alt":"(GitHub
)","prompt_text":null,"type":"grouped_webpages","fallback_items":null,"error":null,"status":"done","items":[{"title":"","url":"https://raw.githubusercontent.com/jeanbrito-dev/ServiceFlow/main/frontend/package.json","attribution":"GitHub","pub_date":null,"snippet":null,"attribution_segments":null,"supporting_websites":[],"refs":[{"turn_index":2,"ref_type":"view","ref_index":1}],"hue":null,"attributions":null}],"style":null},"showLoginRequiredCard":false}

📦 Build do Backend

Para gerar o build do backend:

cd backend
mvn clean package


O artefato gerado poderá ser encontrado no diretório:

backend/target/

🔐 Configuração para ambiente de rede

Caso o backend precise ser acessado por outros computadores da mesma rede, não utilize localhost no frontend.

Por exemplo, se o backend estiver hospedado no computador:

172.30.1.170


e estiver utilizando a porta 8080, o frontend deverá fazer as requisições para:

http://172.30.1.170:8080


e não:

http://localhost:8080


Isso ocorre porque localhost sempre representa a própria máquina que está executando o navegador.

👨‍💻 Desenvolvimento

Para iniciar o projeto completo:

Terminal 1 — Backend
cd backend
mvn spring-boot:run

Terminal 2 — Frontend
cd frontend
npm install
npm run dev


Depois, acesse a aplicação pelo endereço exibido pelo Next.js.

📌 Status

🚧 Projeto em desenvolvimento.

Novas funcionalidades e melhorias podem ser adicionadas conforme a evolução do sistema.

📄 Licença

Este projeto não possui uma licença de software definida no momento.

Desenvolvido para o projeto ServiceFlow.
