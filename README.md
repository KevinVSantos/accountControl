# AccountControl
Cadastro de clientes, contas e pagamentos.

## Objetivo
Realizar a demonstração de conceitos básicos de programação utilizando:
* Java
* JPA
* Spring boot
* H2 Database

## Descrição
O projeto conta com quatro entidades principais:
* Clientes, sendo representaddo pela classe Client;
* Contas, sendo representado pela classe Account;
* Pagamentos, sendo representado pela classe Payment;
* Notificações, sendo representado pela classe Notification.

Cada uma dessas entidades foi validada utilizando o Bean Validation do Spring Boot. 
Dessa forma, existem várias regras que devem ser seguidas para garantir a inclusão correta dos dados. 
Para consultar essas regras, navegue até o diretório `src/main/java/br/com/KevinVSantos/AccountControl/domain/entity`
e explore as respectivas entidades.

O descumprimento de qualquer regra resultará em um erro que será tratado pela classe `CustomExceptionHandler`.
Além disso, essa classe também gerencia exceções inesperadas e erros no envio de parâmetros para os endpoints.


### Fluxo
#### Client
Para iniciar, realize o cadastro de um cliente utilizando o endpoint `/client` com o método `POST`. Certifique-se de seguir 
as regras definidas para essa entidade. Um exemplo de payload é o seguinte:
```
{
    "document":"10918971071",
    "name": "Kevin",
    "password":"1",
    "type": "PF",
    "address":{
        "cep":"1",
        "number":"1",
        "country":"1",
        "obs":"teste"
    }
}
```
Com o registro criado, é possível consulta-lo através do endpoint `/client` ou `/client/{id}`
utlizando o método `GET`.

Também é possível realizar a alteração do registro criado pelo endpoint `/client` utilizando 
o método `PUT` ou deletá-lo pelo endpoint `/client/{id}` utilizando o método `DELETE`.

#### Account 
###### Autenticação para Operações
 Para os endpoints de criação, atualização e exclusão (`create`, `update`, `delete`), 
 foi necessário incluir um header `password`, contendo o mesmo valor da senha cadastrada 
 para o cliente.

###### Criação e Edição de Contas
Com um cliente já cadastrado, é possível criar uma conta associada a esse cliente utilizando 
o endpoint `/account`. O processo de criação e edição de contas é semelhante ao cadastro de clientes. 
Um exemplo de payload para a criação de uma conta é o seguinte:
```
{
    "agency":"1",
    "balance":"300",
    "status":"ATIVA",
    "clientDocument":"44408698822"
}
```
###### Consultando Contas
Há duas formas de consultar a conta criada utilizando o método `GET`:
1. Faça uma requisição ao endpoint `/account` para obter uma lista de todas as contas.
2. Faça uma requisição ao endpoint `/account/{agency}/{id}` para obter os dados de uma conta específica.

###### Deleção de Contas
Para deletar uma conta, utilize o endpoint `/account/{agency}/{id}` com o método `DELETE`.

#### Payment
###### Autenticação para Pagamentos
Para todos os endpoints de pagamento, é obrigatório incluir um header `password` com o mesmo 
valor da senha cadastrada para o cliente da conta de origem.

###### Transferência de Saldo entre Contas
Com duas contas criadas, é possível transferir saldo entre elas. Para realizar um pagamento, utilize o endpoint 
`/payment` com o método `POST`. Um exemplo de payload é o seguinte:
```
{
    "originId":"102",
    "originAgency":"1",
    "destinationId":"52",
    "destinationAgency":"1",
    "amount":"10",
    "justification":"teste"
}
```
Nesse exemplo, a conta de número 102 da agencia 1 está transferindo 10 reais para
a conta de número 52 da mesma agência.

###### Reversão de Pagamentos
Após a realização de um pagamento, é possível revertê-lo utilizando o endpoint `/payment/revert/{id}?justification=teste`.
> Note que nesse endpoint deve ser informado o Id do pagamento a ser revertido e uma justificativa.

#### Notification
Sempre que um pagamento é realizado ou revertido, dois registros são criados na tabela de notificação.
Em certo intervalo de tempo definido pelo varíavel `app.notification.interval`, um método
irá ler todos os registros não processados da tabela de notificação e tentará enviá-los novamente.
> Em caso de falha ao se comunicar com o serviço externo, o método é interrompido e só volta a tentar
> enviar as notificações na próxima execução.

## Ponderações e TO DO
Como é possível imaginar, a primeira coisa que gostaria de alterar no projeto é a forma de
autenticação. Para ilustrar brevemente o funcionamento, apliquei uma validação utilizando um header contendo
a senha do usuário, porém, é obvio que isso precisa ser alterado. A utilização do spring
security provavelmente seria a saída escolhida, podendo não somente validar um token de autenticação, mas também atribuir ROLEs de permissões para diferentes perfis de usuários.

A segunda ponderação é a forma de retornos dos dados não tratados. Apesar de ter criado uma pasta DTO
e uma configuração de mapeamento, decidi poupar o tempo de constuir essa parte do projeto
e deixar exposto a própria entidade do banco. Isso obviamente causa um vazamento de dados desnecessários, como
o proprio `password` da entidade cliente. Tudo isso seria resolvido com o mapeamento final para as DTOs
responsáveis.

Ademais, devo ressaltar a ausência de testes unitarios. Aplicando o design pattern TDD isso seria resolvido,
visto que a criação dos testes seria a primeira etapa para o desenvolvimento. Contudo,pela agilidade
necessária, os testes ficaram de fora do escopo inicial.

Além disso, eu alteraria a forma de deleção dos dados para um delete lógico. Assim seria possível
manter um histórico dos dados e evitariamos registros órfãos na base de dados. Isso não foi considerado
no desenvolvimento dessa versão.

Por fim, o sistema de notificação ficou bem rudimentar e precisa ser refeito. Uma sugestão é a ingestão
desses registros em um tópico de qualquer sistema de mensageria (como o eventHub, por exemplo), possíbilitando que o sitema
externo consuma esses dados quando estiver disponível.

