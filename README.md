Execução
-------------
	Prerequisites:

		1. Java 8;
	
	Run (using Eclipse Indigo+):
	
		1. Click with mouse right button on the com.ipfaffen.involve.Main java file;
		2. Click on "Run As" option;
		3. Select "Java Application".
		
	Test (using Eclipse Indigo+):
	
		1. Click with mouse right button on the com.ipfaffen.involve.test.CityTest java file;
		2. Click on "Run As" option;
		3. Select "JUnit Test".
		
Sobre o teste
-------------

A prova consiste em criar um programa que leia todas as linhas do arquivo CSV ([faça o download aqui](cidades.csv)) e utilize esse arquivo como base para consultas, onde a primeira linha (cabeçalho) contém o nome das propriedades e as linhas subsequentes os valores. Após ler o arquivo, o programa deve começar a ouvir o console a espera dos comandos de consulta. Abaixo segue a lista dos comandos que devem ser interpretados:

- **count** * - escreve no console a contagem total de registros importados (não deve considerar a linha de cabeçalho)
- **count distinct [propriedade]** - escreve no console o total de valores distintos da propriedade (coluna) enviada 
- **filter [propriedade] [valor]** - escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada possua o valor enviado 

> **Restrições:**

> - Não é permitido utilizar bibliotecas e frameworks externos, apenas classes do JDK5 ou superior (exceto JUnit para os testes de unidade e Maven ou Gradle como ferramentas build)
> - Interações devem ser feitas apenas via console (System.in e System.out)
> - Não é permitido utilizar bancos de dados ou outros serviços de armazenamento de dados que não tenham sido desenvolvidos pelo próprio candidato
