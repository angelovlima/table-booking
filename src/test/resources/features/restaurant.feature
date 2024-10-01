# language: pt
Funcionalidade: API - Restaurant

    Cenario: Cadastrar Restaurante
        Quando cadastrar um novo restaurante
        Então o restaurante é cadastrado com sucesso

    Cenario: Editar Restaurante
        Dado que um restaurante já foi cadastrado
        Quando efetuar requisição para editar o restaurante
        Então o restaurante é atualizado com sucesso

    Cenario: Visualizar Restaurantes
        Dado que um restaurante já foi cadastrado
        Quando efetuar requisição para visualizar todos os restaurantes
        Então os restaurantes devem ser apresentados com sucesso

    Cenario: Excluir Restaurante
        Dado que um restaurante já foi cadastrado
        Quando efetuar requisição para excluir o restaurante
        Então o restaurante é removido com sucesso
