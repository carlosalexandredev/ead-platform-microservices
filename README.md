# Microservices Architecture

[![Linkedin Badge](https://img.shields.io/badge/-Carlos%20Alexandre-002E74?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/carlosalexandredev/)](https://www.linkedin.com/in/carlosalexandredev/)
[![Gmail Badge](https://img.shields.io/badge/-carlosalexandredev.contato@gmail.com-002E74?style=flat-square&logo=Gmail&logoColor=white&link=mailto:carlosalexandredev.contato@gmail.com)](mailto:carlosalexandredev.contato@gmail.com)


A arquitetura de microservices é um estilo arquitetônico utilizado para desenvolver sistemas de software, no qual a aplicação é dividida em um conjunto de serviços independentes e autônomos, cada um executando um processo separado e se comunicando entre si por meio de mecanismos de comunicação bem definidos, como APIs (Application Programming Interfaces).

Em contraste com a arquitetura monolítica, na qual toda a aplicação é desenvolvida e implantada como uma única unidade, a arquitetura de microservices promove a decomposição da aplicação em serviços menores e mais especializados. Cada serviço é responsável por uma funcionalidade específica e pode ser desenvolvido, implantado e escalado de forma independente. Isso proporciona maior flexibilidade, modularidade e escalabilidade ao sistema como um todo.

Além disso, os serviços em uma arquitetura de microservices são tipicamente implantados e executados em ambientes distribuídos, como contêineres Docker ou ambientes de nuvem. Isso facilita a implantação e o dimensionamento dos serviços de forma independente, permitindo que cada serviço seja ajustado de acordo com suas necessidades de carga e recursos.

A comunicação entre os serviços é geralmente baseada em APIs, que podem ser expostas por meio de protocolos RESTful (Representational State Transfer) ou outros mecanismos, como mensageria assíncrona. Isso permite que os serviços se comuniquem de forma eficiente e desacoplada, tornando o sistema mais resiliente e permitindo a evolução individual de cada serviço sem afetar o sistema como um todo.

## Microservices Architecture with Spring EAD

Serviço de plataforma de aprendizagem EAD

O serviço é composto por sete microsserviços:
 - Api Gateway: ponto de entrada para todas as solicitações de API do sistema
 - Service Registry: centraliza metadados e informações sobre os serviços disponíveis no sistema.
 - Config Service: gerencia as configurações e as propriedades do sistema.
 - AuthUser Service: lida com a criação, autenticação e autorização de usuários.
 - Course Service: lida com a gestão e disponibilização de informações sobre cursos, modulos e lições.
 - Notification Service: lida com o envio e gerenciamento de notificações para os usuários.
 - Logs: mecanismo de busca e análise de dados distribuído com Elasticsearch.


![carlosalexandredev-microservices.png](/readme-resources/carlosalexandredev-microservices.png)

### Pré-requisitos
Para rodar o projeto será necessário:
- Java versão 11;
- Maven 3.8.2;
- Ter um RabbitMQ rodando.

### Bibliotecas:

Spring Cloud Gateway: permite criar gateways de APIs para integrar e gerenciar várias APIs. O Spring Cloud Gateway é altamente configurável e extensível, e inclui recursos como roteamento, filtragem, balanceamento de carga e muito mais.

Spring Security: fornece recursos de segurança para aplicativos Java. Ele fornece uma estrutura robusta para autenticação e autorização, incluindo suporte para autenticação baseada em token, controle de acesso baseado em permissões e muito mais.

Spring Log4J2: fornece integração com o framework de logging Log4j2. Ele fornece recursos avançados de logging, como a capacidade de definir níveis de log por pacote, registro de mensagens de log em diferentes arquivos, dentre outros.

Spring Boot: permite criar aplicativos Java de forma rápida e fácil, fornecendo recursos de configuração automática e integração de bibliotecas. Ele inclui um conjunto de bibliotecas prontas para uso, permitindo aos desenvolvedores criar aplicativos rapidamente com pouco código.

Spring Validation: fornece suporte para validação de dados em aplicativos Java. Ele permite que os desenvolvedores definam regras de validação em seus objetos de modelo e validem esses objetos automaticamente.

Spring HATEOAS: fornece recursos para criar APIs RESTful que seguem o princípio HATEOAS (Hypermedia As The Engine Of Application State). Isso significa que as APIs são projetadas para incluir links que permitem aos clientes navegar entre recursos relacionados.

Spring Cloud Config Server: fornece um servidor centralizado de configuração para aplicativos. Ele permite que os desenvolvedores gerenciem a configuração de seus aplicativos em um único lugar e fornece recursos para atualização de configuração em tempo real.

Spring Cloud Config Client: permite que os aplicativos acessem a configuração centralizada fornecida pelo Spring Cloud Config Server. Ele inclui recursos para atualização de configuração em tempo real, com suporte a vários perfis e ambientes.

Spring Cloud Netflix Eureka Server: fornece um serviço de registro e descoberta de microservices. Ele permite que os microservices se registrem automaticamente e se descubram, simplificando a comunicação entre eles.

Spring Cloud Netflix Eureka Client: permite que os aplicativos acessem o serviço de registro e descoberta fornecido pelo Spring Cloud Netflix Eureka Server. Ele inclui recursos para descoberta dinâmica de serviços e balanceamento de carga.

Spring AMQP: fornece suporte para mensagens assíncronas e comunicação entre aplicativos usando o protocolo AMQP (Advanced Message Queuing Protocol). Ele inclui recursos para criação de filas, troca de mensagens, envio de mensagens em lote, dentre outros.

Spring Cloud Resilience4j: fornece recursos para criar aplicativos resilientes e tolerantes a falhas. Ele inclui recursos como circuit breaker, fallback, retry, rate limiter, dentre outros.

Spring Web: fornece recursos para criar aplicativos web. Ele inclui recursos para criação de controladores, suporte a anotações, suporte a diferentes formatos de dados, dentre outros. Ele também é usado como base para outras bibliotecas do Spring, como o Spring MVC e o Spring WebFlux.

Spring Data JPA: permite definir interfaces de repositório que descrevem as operações que desejam realizar no banco de dados, e o Spring cuida da implementação dessas operações automaticamente. Ele também inclui recursos para mapeamento de entidades, definição de relacionamentos entre entidades, validação de dados e muito mais.

O Spring Data JPA é altamente configurável e suporta vários provedores de JPA, como o Hibernate, EclipseLink e OpenJPA. Ele também é amplamente usado em conjunto com outras bibliotecas do Spring, como o Spring Boot e o Spring MVC, para criar aplicativos Java completos e escaláveis.


### Mensageria:
RabbitMQ é um software de mensageria open-source amplamente utilizado na arquitetura de sistemas distribuídos. Ele implementa o protocolo de mensageria Advanced Message Queuing Protocol (AMQP) e fornece uma plataforma robusta para o envio e a recepção de mensagens entre diferentes partes de um sistema.

O RabbitMQ atua como um intermediário (broker) entre os produtores de mensagens e os consumidores. Ele recebe as mensagens enviadas pelos produtores e as encaminha para os consumidores apropriados, garantindo a entrega confiável e ordenada das mensagens.