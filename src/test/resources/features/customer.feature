# language: pt
Funcionalidade: API - Customer

    Cenario: Cadastrar Cliente
        Quando cadastrar um novo cliente
        Então o usuário é cadastrado com sucesso

    Cenario: Editar Cliente
        Dado que um cliente já foi cadastrado
        Quando efetuar requisição para editar o cliente
        Então o usuário é atualizado com sucesso

    Cenario: Visualizar Clientes
        Dado que um cliente já foi cadastrado
        Quando efetuar requisição para visualizar todos os clientes
        Então os clientes devem ser apresentados com sucesso

    Cenario: Excluir Cliente
        Dado que um cliente já foi cadastrado
        Quando efetuar requisição para excluir o cliente
        Então o usuário é removido com sucesso