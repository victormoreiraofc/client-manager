<h1 align="center">
  <img src="https://ctcontab.com.br/wp-content/uploads/2024/04/cropped-logo-ctcontabil.png" alt="Logo CT CONTAB" width="300">
  <br>Sistema de Gestão de Clientes para CT CONTAB
</h1>

![GitHub repo size](https://img.shields.io/github/repo-size/victormoreiraofc/client-manager?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/victormoreiraofc/client-manager?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/victormoreiraofc/client-manager?style=for-the-badge)
![Bitbucket open issues](https://img.shields.io/bitbucket/issues/victormoreiraofc/client-manager?style=for-the-badge)
![Bitbucket open pull requests](https://img.shields.io/bitbucket/pr-raw/victormoreiraofc/client-manager?style=for-the-badge)

---

> **Official Developer Project**

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/victormoreiraofc">
        <img src="https://avatars.githubusercontent.com/u/121199565?v=4" width="100px;" alt="Foto do Victor Moreira no GitHub"/><br>
        <sub>
          <b>Victor Moreira</b>
        </sub>
      </a>
      <br />
        <a href="https://github.com/victormoreiraofc/client-manager/" title="Project Management">📆</a>
        <a href="https://github.com/victormoreiraofc/client-manager/commits/main/" title="Code">💻</a>
        <a href="https://github.com/victormoreiraofc/client-manager/blob/main/README.md" title="Documentation">📖</a>
        <a href="https://github.com/victormoreiraofc/client-manager/tree/main/src/images" title="Design">🎨</a>
        <a href="https://github.com/victormoreiraofc/client-manager/" title="Ideas & Planning">🤔</a>
        <a href="https://github.com/victormoreiraofc/client-manager/" title="Testing">⚠</a>
    </td>
    <td align="center">
      <a href="https://github.com/Mateus-Ebenezer">
        <img src="https://avatars.githubusercontent.com/u/143097497?v=4" width="100px;" alt="Foto do Mateus Ebenezer da Silva Santos no GitHub"/><br>
        <sub>
          <b>Mateus Ebenezer</b>
        </sub>
      </a>
      <br />
        <a href="https://github.com/victormoreiraofc/client-manager/commits/main/" title="Code">💻</a>
    </td>
    <td align="center">
      <a href="https://github.com/GiovaneRocca03">
        <img src="https://avatars.githubusercontent.com/u/108840776?v=4" width="100px;" alt="Foto do Giovane Rocca no GitHub"/><br>
        <sub>
          <b>Giovane Rocca</b>
        </sub>
      </a>
      <br />
        <a href="https://github.com/victormoreiraofc/client-manager/commits/main/" title="Code">💻</a>
    </td>
    <td align="center">
      <a href="https://github.com/RuanAlvesz">
        <img src="https://avatars.githubusercontent.com/u/126029084?v=4" width="100px;" alt="Foto do Ruan Alves no GitHub"/><br>
        <sub>
          <b>Ruan Alves</b>
        </sub>
      </a>
      <br />
        <a href="https://github.com/victormoreiraofc/client-manager/commits/main/" title="Code">💻</a>
    </td>
    <td align="center">
      <a href="https://github.com/PamellaCorrea">
        <img src="https://avatars.githubusercontent.com/u/143097694?v=4" width="100px;" alt="Foto da Pamella Correa no GitHub"/><br>
        <sub>
          <b>Pamella Correa</b>
        </sub>
      </a>
      <br />
        <a href="https://github.com/victormoreiraofc/client-manager/commits/main/" title="Code">💻</a>
        <a href="https://github.com/victormoreiraofc/client-manager/blob/main/README.md" title="Documentation">📖</a>
      <a href="https://github.com/victormoreiraofc/client-manager/" title="Testing">⚠</a>
    </td>
    <td align="center">
      <a href="https://github.com/Ingenzin">
        <img src="https://avatars.githubusercontent.com/u/166843978?v=4" width="100px;" alt="Foto do Lucas Santos no GitHub"/><br>
        <sub>
          <b>Lucas Santos</b>
        </sub>
      </a>
      <br />
        <a href="https://github.com/victormoreiraofc/client-manager/commits/main/" title="Code">💻</a>
    </td>
  </tr>
</table>

---

> **Funcionalidades**

- **Gerenciamento de Clientes:** Adicione, edite e remova informações de clientes de forma eficiente.
- **Geração de Relatórios:** Crie relatórios financeiros personalizados automaticamente.
- **Comunicação Interna:** Facilite a comunicação dentro da equipe contábil.
- **Integração com IA:** Utilize inteligência artificial para otimizar processos e análises.

---

> **Tecnologias Utilizadas**

- **Linguagem:** Java
- **IDE:** NetBeans 25.0
- **Banco de Dados:** MySQL
- **Bibliotecas e Frameworks:**
  - [N8N](https://n8n.io/) para manipulação de agentes de IA
  - [Ngrok](https://ngrok.com/) para colocar o n8n online
  - [NodeJS](https://nodejs.org/pt) para manipular o n8n

---

> **Instalação e Configuração**

1. **Clone o Repositório:**
   ```bash
   git clone https://github.com/victormoreiraofc/client-manager.git

2. **Abra o Projeto no NetBeans:**
   - Abra o NetBeans IDE.
   - Selecione "Abrir Projeto" e navegue até o diretório do repositório clonado.
   - Abra o projeto no NetBeans.

3. **Configuração do Banco de Dados:**
   - Certifique-se de ter o MySQL instalado.
   - Crie um banco de dados chamado `client_manager`.
   - Importe o arquivo de estrutura do banco de dados (geralmente um `.sql` fornecido no repositório ou na documentação do projeto).
   - Atualize as credenciais de conexão no arquivo de configuração do banco de dados.

4. **Dependências:**
   - O projeto utiliza Maven para gerenciamento de dependências. Certifique-se de que todas as dependências estão resolvidas.
   - No NetBeans, clique com o botão direito no projeto e selecione "Construir" para baixar todas as dependências.

5. **Configuração do Servidor de Aplicação (caso necessário):**
   - O projeto pode requerer um servidor de aplicação (como Tomcat ou GlassFish) caso haja funcionalidades web envolvidas. Siga as instruções do projeto para configurar o servidor.

6. **Executando o Projeto:**
   - Após a configuração, execute o projeto diretamente no NetBeans.
   - Caso o projeto inclua funcionalidades de servidor web, o NetBeans irá iniciar o servidor para você.

---

> **Contributing**

1. [Fork the repository](https://github.com/victormoreiraofc/client-manager/fork)
2. Clone your fork: `git clone https://github.com/victormoreiraofc/client-manager`
3. Create your feature branch: `git checkout -b my-new-feature`
4. Stage changes `git add .`
5. Commit your changes: `cz` OR `npm run commit` do not use `git commit`
6. Push to the branch: `git push origin my-new-feature`
7. Submit a pull request
