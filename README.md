uol-tqi-voting
==============

UOL/TQI Voting Project

Projeto criado para avaliação de trabalho na UOL.

CONFIGURAÇÕES:
Atualize o arquivo uol-tqi-voting/pom.xml para modificar as seguintes propriedades:
<jdbc.connection.driver>: Nome do driver JDBC para a execução do appender de SQL. (default: com.mysql.jdbc.Driver)
<jdbc.connection.url>: Nome da URL de conexão JDBC com a base de dados para a execução do appender de SQL. (default: jdbc:mysql://192.168.35.129:3306/test)
<jdbc.connection.user>: Usuário da base de dados na conexão JDBC para execução do appender de SQL. (default: root)
<jdbc.connection.password>: Senha do usuário para a cone≈ão JDBC. (default: polopoly)
<ear.deployment.dir>: Diretório de instalação da aplicação enterprise. (default: /usr/local/jboss-as-7.1.1.Final/standalone/deployments)
<web.deployment.dir>: Diretório de instalação da aplicação web. (default: /usr/local/apache-tomcat-7.0.25/webapps)

O arquivo persistence.xml do projeto uol-tqi-voting-ejb está referenciando o datasource java:jboss/datasources/MySqlDS e o dialeto org.hibernate.dialect.MySQLDialect. Caso já exista outro datasource, basta mudar as referências neste arquivo.
O arquivo jboss-ejb-client.properties possui configurações de conexão JNDI com o servidor JBOSS e precisa ser modificado para configurar outros containers e/ou ajustar as credenciais, no caso do JBOSS AS7.

EXECUÇÃO:
Execute mvn clean install para gerar os artefatos (.ear, .war e .jar)
Execute o comando acima com -Dsql=create para criar a estrutura de tabelas e alimentá-las com os dados de enquete.
Execute o mesmo comando com -Denv=prd para fazer o deployment dos artefatos nos locais indicados pelas propriedades para ear e web.
Se os containers já estiverem em execução, basta acessar a aplicação via browser, caso contrário, basta executar os containers para que a aplicação entre em execução.
