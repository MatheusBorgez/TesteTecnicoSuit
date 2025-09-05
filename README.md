# TesteTecnicoSuit
Teste técnico para empresa Suitpay

Teste Técnico - Desenvolvedor Java
Contexto
A SuitPay está criando um novo módulo para gerenciar limites de crédito de clientes. Este módulo será integrado a outras partes do sistema e precisará garantir alta performance, segurança e escalabilidade.
Você será responsável por projetar e implementar parte deste módulo, atendendo aos requisitos descritos abaixo.




Parte 1: Implementação Prática
Cenário
Implemente uma API RESTful utilizando Spring Boot, Hibernate que permita gerenciar limites de crédito dos clientes. A aplicação deve atender aos seguintes requisitos:

Funcionalidades:

Consultar Limite de Crédito: Um endpoint para retornar o limite atual de um cliente pelo id.

Atualizar Limite de Crédito: Um endpoint que permita atualizar o limite, respeitando regras de negócio.

Histórico de Alterações: Todas as alterações de limite devem ser registradas com informações de quem fez a alteração e a data/hora.

Regras de Negócio:

Limites não podem ser negativos.

Somente usuários com a role "CREDIT_LIMIT_ADMIN" podem alterar limites.

Clientes VIP (indicados por um atributo isVip) têm um limite mínimo obrigatório definido no sistema.

Banco de Dados:

Utilize SQL Server para simular o banco de dados.

Extras (Opcional):

Implemente autenticação e autorização utilizando Spring Security.

Adicione paginação no endpoint de histórico.

Utilize o Gradle como gerenciador de dependências.



Parte 2: Análise Crítica
Trecho de Código
Analise o código abaixo e responda às perguntas:
<img width="571" height="295" alt="image" src="https://github.com/user-attachments/assets/8661c927-48fe-42b0-b9ac-4f4cc0e3c909" />

Perguntas:

Dado o contexto e requisitos do teste:

Você consegue encontrar algum problema neste código?

Como você melhoraria esta implementação para atender aos requisitos de segurança, boas práticas e escalabilidade?

Parte 3: Questões Teóricas
Considerando o contexto em que a SuitPay atua e as tecnologias utilizadas:
Explique como você trataria um cenário de alto volume de leitura e escrita em uma entidade crítica para o sistema.

Quais mecanismos no geral que você utilizaria para garantir que o sistema, banco de dados e integrações permaneçam performáticos ao lidar com alta demanda de clientes? Quais os possíveis gargalos?

Como você implementaria a funcionalidade de "limite mínimo para clientes VIP" de forma eficiente?
