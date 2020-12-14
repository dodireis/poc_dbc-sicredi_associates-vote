# SICREDI - VOTAÇÃO DE ASSOCIADOS

## CONSIDERAÇÕES
	- Embora a tendência seja utilizar a separação dos pacotes por funcionalidades (Package by Feature),
	decidi usar o layout por camadas (Package by Layers), porque tive que ir entendendo as funcionalidades e adaptando conforme ia desenvolvendo,
	uma vez que não tinha as histórias escritas e bem definidas.
	- Utilizei o Swagger para documentar os rest points.
	- Estou usando um banco de dados de teste, o H2, apenas para poder persistir os dados e não precisar realizar nenhuma configuração local

## CONFIGURAÇÃO

	Ao executar a aplicação localmente, deve-se escolher o ambiente que será executado, através do arquivo "application.properties" localizado na pasta "resource" do projeto.
		tst -> para ambiente local de teste
		prd -> para ambiente de produção
	
	A primeira vez que executar no ambiente de teste, alterar no arquivo "application-tst.properties", também localizado na pasta "resource", 
	a propriedade "spring.jpa.hibernate.ddl-auto" mudando de "create", para "none", pois na primeira vez será criada a base de dados.


## INFORMAÇÕES DE ACESSO

### H2
	<localhost>/h2-console
	Exemplo: http://localhost:8080/h2-console
	JDBC URL: jdbc:h2:./test
	User Name: sa
	Password:
	
	OBS: a senha é em branco mesmo
	
### Swagger
	<localhost>/swagger-ui.html#/
	Exemplo: http://localhost:8080/swagger-ui.html#/
