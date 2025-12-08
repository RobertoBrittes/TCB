Descrição:
este projeto é um sistema de gestão para assessorias de corrida, desenvolvido para fins acadêmicos. Ele permite que treinadores criem planos de treino personalizados e que alunos acompanhem seu progresso, incluindo treinos concluídos, treinos restantes e semanas restantes do plano.
O sistema é voltado para simular o funcionamento de uma assessoria de corridas, mostrando o conhecimento em Java, JDBC e bancos de dados relacionais.

Funcionalidades:
Para Treinadores:

- Listar alunos da assessoria;

- Criar, alterar e desativar planos de treino para corrida;

- Adicionar treinos aos planos, escolhendo dias da semana;

- Visualizar o progresso dos alunos, incluindo histórico de treinos concluídos.

Para Alunos:

- Visualizar e alterar perfil pessoal;

- Consultar o plano de treino ativo;

- Registrar treinos concluídos;

- Acompanhar progresso do plano (treinos concluídos, treinos restantes e semanas restantes).

Tecnologias:

- Java 17

- JDBC para conexão com o banco de dados

- MySQL como SGBD

- POO (Programação Orientada a Objetos) aplicada na modelagem de alunos, treinadores, planos e treinos.

- Banco de Dados

O banco contém as seguintes tabelas principais:

pessoa → cadastro de alunos e treinadores

plano_treino → dados do plano de treino, incluindo duração, objetivo, quantidade de treinos semanais e status ativo/inativo

treino → cadastro de treinos prontos ou personalizados para corrida

treino_tem_plano → associação entre treinos e planos, incluindo dia da semana

treino_concluido → registro de treinos concluídos pelos alunos, com data e plano associado
