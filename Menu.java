package src.UI;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import DAO.*;
import src.models.*;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ClientDAO clientDAO = new ClientDAO();
    private static final ProductDAO productDAO = new ProductDAO();
    private static final SaleDAO saleDAO = new SaleDAO();
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    private static final ProductSalesDAO productSalesDAO = new ProductSalesDAO();

    public static void main(String[] args) {
        int mainChoice;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Clientes");
            System.out.println("2. Produtos");
            System.out.println("3. Vendas");
            System.out.println("4. Funcionários");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            try {
                mainChoice = Integer.parseInt(scanner.nextLine());

                switch (mainChoice) {
                    case 1 -> menuClientes();
                    case 2 -> menuProdutos();
                    case 3 -> menuVendas();
                    case 4 -> menuFuncionarios();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
                mainChoice = -1;
            }

        } while (mainChoice != 0);
    }

    // Then update the menuClientes() method to include this new option:
    private static void menuClientes() {
        int choice;
        do {
            System.out.println("\n=== CLIENTES ===");
            System.out.println("1. Inserir");
            System.out.println("2. Alterar");
            System.out.println("3. Pesquisar por nome");
            System.out.println("4. Remover");
            System.out.println("5. Listar todos");
            System.out.println("6. Exibir detalhes");
            System.out.println("7. Histórico de compras"); // New option
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> inserirCliente();
                    case 2 -> alterarCliente();
                    case 3 -> pesquisarCliente();
                    case 4 -> removerCliente();
                    case 5 -> listarClientes();
                    case 6 -> exibirCliente();
                    case 7 -> menuClienteHistorico(); // New case
                    case 0 -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida!");
                choice = -1;
            }
        } while (true);
    }

    private static void inserirCliente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Torce para o Flamengo? (S/N): ");
        boolean flamengo = scanner.nextLine().equalsIgnoreCase("S");
        System.out.print("Assiste One Piece? (S/N): ");
        boolean onePiece = scanner.nextLine().equalsIgnoreCase("S");
        System.out.print("É de Sousa? (S/N): ");
        boolean sousa = scanner.nextLine().equalsIgnoreCase("S");

        Client novoCliente = new Client(0, nome, telefone, email);
        novoCliente.setFlamengoFan(flamengo);
        novoCliente.setOnePieceWatcher(onePiece);
        novoCliente.setFromSousa(sousa);

        clientDAO.addClient(novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void alterarCliente() {
        System.out.print("ID do cliente: ");
        int id = Integer.parseInt(scanner.nextLine());
        Client cliente = clientDAO.getClientById(id);

        if (cliente != null) {
            System.out.print("Novo nome (" + cliente.getClientName() + "): ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) cliente.setClientName(nome);

            System.out.print("Novo telefone (" + cliente.getPhoneNumber() + "): ");
            String telefone = scanner.nextLine();
            if (!telefone.isEmpty()) cliente.setPhoneNumber(telefone);

            System.out.print("Novo email (" + cliente.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) cliente.setEmail(email);

            clientDAO.updateClient(cliente);
            System.out.println("Cliente atualizado!");
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    private static void pesquisarCliente() {
        System.out.print("Nome para pesquisa: ");
        String nome = scanner.nextLine();
        List<Client> resultados = clientDAO.searchClientsByName(nome);
        resultados.forEach(System.out::println);
    }

    private static void removerCliente() {
        System.out.print("ID para remover: ");
        int id = Integer.parseInt(scanner.nextLine());
        clientDAO.deleteClient(id);
        System.out.println("Cliente removido!");
    }

    private static void listarClientes() {
        List<Client> clientes = clientDAO.getAllClients();
        clientes.forEach(System.out::println);
    }

    private static void exibirCliente() {
        System.out.print("ID para exibir: ");
        int id = Integer.parseInt(scanner.nextLine());
        Client cliente = clientDAO.getClientById(id);
        System.out.println(cliente != null ? cliente : "Cliente não encontrado!");
    }

    private static void menuProdutos() {
        int choice;
        do {
            System.out.println("\n=== PRODUTOS ===");
            System.out.println("1. Inserir");
            System.out.println("2. Alterar");
            System.out.println("3. Pesquisar por nome");
            System.out.println("4. Remover");
            System.out.println("5. Listar todos");
            System.out.println("6. Exibir detalhes");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> inserirProduto();
                    case 2 -> alterarProduto();
                    case 3 -> pesquisarProduto();
                    case 4 -> removerProduto();
                    case 5 -> listarProdutos();
                    case 6 -> exibirProduto();
                    case 0 -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida!");
                choice = -1;
            }
        } while (true);
    }

    private static void inserirProduto() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        System.out.print("Valor unitário: ");
        double valor = Double.parseDouble(scanner.nextLine());
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Fabricado em Mari? (S/N): ");
        boolean mari = scanner.nextLine().equalsIgnoreCase("S");

        Product novoProduto = new Product(0, nome, quantidade, valor, categoria, mari); // Atualizado
        productDAO.addProduct(novoProduto);
        System.out.println("Produto cadastrado!");
    }

    private static void alterarProduto() {
        System.out.print("ID do produto: ");
        int id = Integer.parseInt(scanner.nextLine());
        Product produto = productDAO.getProductById(id);

        if (produto != null) {
            System.out.print("Novo nome (" + produto.getProductName() + "): ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) produto.setProductName(nome);

            System.out.print("Nova quantidade (" + produto.getProductQuantity() + "): ");
            String quantidade = scanner.nextLine();
            if (!quantidade.isEmpty()) produto.setProductQuantity(Integer.parseInt(quantidade));

            System.out.print("Novo valor (" + produto.getProductValue() + "): ");
            String valor = scanner.nextLine();
            if (!valor.isEmpty()) produto.setProductValue(Double.parseDouble(valor));

            System.out.print("Nova categoria (" + produto.getProductCategory() + "): ");
            String categoria = scanner.nextLine();
            if (!categoria.isEmpty()) produto.setProductCategory(categoria);

            productDAO.updateProduct(produto);
            System.out.println("Produto atualizado!");
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    private static void pesquisarProduto() {
        System.out.print("Nome para pesquisa: ");
        String nome = scanner.nextLine();
        List<Product> resultados = productDAO.searchProductsByName(nome);
        resultados.forEach(System.out::println);
    }

    private static void removerProduto() {
        System.out.print("ID para remover: ");
        int id = Integer.parseInt(scanner.nextLine());
        productDAO.deleteProduct(id);
        System.out.println("Produto removido!");
    }

    private static void listarProdutos() {
        List<Product> produtos = productDAO.getAllProducts();
        produtos.forEach(System.out::println);
    }

    private static void exibirProduto() {
        System.out.print("ID para exibir: ");
        int id = Integer.parseInt(scanner.nextLine());
        Product produto = productDAO.getProductById(id);
        System.out.println(produto != null ? produto : "Produto não encontrado!");
    }

    private static void menuVendas() {
        int choice;
        do {
            System.out.println("\n=== VENDAS ===");
            System.out.println("1. Nova venda");
            System.out.println("2. Alterar venda");
            System.out.println("3. Pesquisar por cliente");
            System.out.println("4. Cancelar venda");
            System.out.println("5. Listar todas");
            System.out.println("6. Detalhes da venda");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> novaVenda();
                    case 2 -> alterarVenda();
                    case 3 -> pesquisarVendasCliente();
                    case 4 -> cancelarVenda();
                    case 5 -> listarVendas();
                    case 6 -> detalhesVenda();
                    case 7 -> exibirRelatorioMensal();
                    case 0 -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida!");
                choice = -1;
            }
        } while (true);
    }

    private static void novaVenda() {
        // Verificar cliente
        System.out.print("ID do cliente: ");
        int clientId = Integer.parseInt(scanner.nextLine());
        Client cliente = clientDAO.getClientById(clientId);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        // Verificar funcionário
        System.out.print("ID do funcionário: ");
        int employeeId = Integer.parseInt(scanner.nextLine());
        Employee funcionario = employeeDAO.getEmployeeById(employeeId);
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado!");
            return;
        }

// Obter métodos de pagamento ativos
        // Dentro do método novaVenda():

// Listar métodos ativos
        PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO();
        List<PaymentMethod> metodosAtivos = paymentMethodDAO.getActivePaymentMethods();

        if (metodosAtivos.isEmpty()) {
            System.out.println("Nenhum método de pagamento disponível!");
            return;
        }

// Exibir métodos
        System.out.println("\n=== MÉTODOS DE PAGAMENTO DISPONÍVEIS ===");
        metodosAtivos.forEach(m ->
                System.out.println(m.getPaymentMethodID() + " - " + m.getMethodName())
        );

// Validar entrada do usuário
        int paymentMethodId = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.print("Escolha o ID do Método: ");
                paymentMethodId = Integer.parseInt(scanner.nextLine());

                // Verificar se o ID existe na lista
                int finalPaymentMethodId = paymentMethodId;
                entradaValida = metodosAtivos.stream()
                        .anyMatch(m -> m.getPaymentMethodID() == finalPaymentMethodId);

                if (!entradaValida) {
                    System.out.println("ID inválido! Escolha um da lista acima.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números!");
            }
        }

// Buscar método selecionado
        PaymentMethod metodoEscolhido = paymentMethodDAO.getPaymentMethodById(paymentMethodId);

// Validar método específico
        if (metodoEscolhido == null ||
                !(metodoEscolhido.getMethodName().equals(PaymentMethod.CREDITO) ||
                        metodoEscolhido.getMethodName().equals(PaymentMethod.DEBITO) ||
                        metodoEscolhido.getMethodName().equals(PaymentMethod.PIX) ||
                        metodoEscolhido.getMethodName().equals(PaymentMethod.BOLETO) ||
                        metodoEscolhido.getMethodName().equals(PaymentMethod.BERRIES))) {
            System.out.println("Método de pagamento inválido!");
            return;
        }

// Lógica de status de pagamento
        PaymentStatusDAO paymentStatusDAO = new PaymentStatusDAO();
        int paymentStatusId;

// Status automático para métodos instantâneos
        if (metodoEscolhido.getMethodName().equals(PaymentMethod.PIX) ||
                metodoEscolhido.getMethodName().equals(PaymentMethod.BERRIES)) {

            // Buscar status "Confirmado" pelo nome
            PaymentStatus statusConfirmado = paymentStatusDAO.getAllPaymentStatus().stream()
                    .filter(s -> s.getStatusName().equals(PaymentStatus.CONFIRMED))
                    .findFirst()
                    .orElse(null);

            if (statusConfirmado == null) {
                System.out.println("Status de pagamento padrão não configurado!");
                return;
            }
            paymentStatusId = statusConfirmado.getStatusID();
        } else {
            // Listar status disponíveis para outros métodos
            System.out.println("\nStatus de Pagamento Disponíveis:");
            paymentStatusDAO.getAllPaymentStatus().forEach(s ->
                    System.out.println(s.getStatusID() + " - " + s.getStatusName()));

            System.out.print("Escolha o ID do Status: ");
            try {
                paymentStatusId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID inválido!");
                return;
            }
        }

// Validar status
        PaymentStatus statusEscolhido = paymentStatusDAO.getPaymentStatusById(paymentStatusId);
        if (statusEscolhido == null) {
            System.out.println("Status inválido!");
            return;
        }


// Criar venda
        Sale novaVenda = new Sale(
                0,
                clientId,
                employeeId,
                new Timestamp(System.currentTimeMillis()),
                BigDecimal.ZERO,
                paymentMethodId,
                paymentStatusId
        );

        try {
            saleDAO.addSale(novaVenda);
            System.out.println("Venda iniciada! ID: " + novaVenda.getSaleID());

            BigDecimal totalVenda = BigDecimal.ZERO;
            while (true) {
                System.out.print("Adicionar produto (ID ou 0 para finalizar): ");
                int productId = Integer.parseInt(scanner.nextLine());
                if (productId == 0) break;

                Product produto = productDAO.getProductById(productId);
                if (produto == null) {
                    System.out.println("Produto não encontrado!");
                    continue;
                }

                System.out.print("Quantidade: ");
                int quantidade = Integer.parseInt(scanner.nextLine());

                if (quantidade > produto.getProductQuantity()) {
                    System.out.println("Estoque insuficiente!");
                    continue;
                }

                BigDecimal valorUnitario = BigDecimal.valueOf(produto.getProductValue());
                BigDecimal totalItem = valorUnitario.multiply(BigDecimal.valueOf(quantidade));

                ProductSales item = new ProductSales(
                        0,
                        novaVenda.getSaleID(),
                        productId,
                        quantidade,
                        valorUnitario,
                        totalItem
                );

                productSalesDAO.addProductSale(item);
                totalVenda = totalVenda.add(totalItem);

                // Atualizar estoque
                produto.setProductQuantity(produto.getProductQuantity() - quantidade);
                productDAO.updateProduct(produto);
            }

            // Atualizar total da venda
            novaVenda.setTotalValue(totalVenda);
            saleDAO.updateSale(novaVenda);
            System.out.println("Venda finalizada! Total: R$ " + totalVenda);

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Operação cancelada.");
            saleDAO.deleteSale(novaVenda.getSaleID());
        } catch (RuntimeException e) {
            System.out.println("Erro na venda: " + e.getMessage());
            saleDAO.deleteSale(novaVenda.getSaleID());
        }
    }

    private static void alterarVenda() {
        System.out.print("ID da venda: ");
        int saleId = Integer.parseInt(scanner.nextLine());
        Sale venda = saleDAO.getSaleById(saleId);

        if (venda != null) {
            System.out.print("Novo ID do cliente (" + venda.getClientID() + "): ");
            String clientId = scanner.nextLine();
            if (!clientId.isEmpty()) venda.setClientID(Integer.parseInt(clientId));

            System.out.print("Novo ID do funcionário (" + venda.getEmployeeID() + "): ");
            String employeeId = scanner.nextLine();
            if (!employeeId.isEmpty()) venda.setEmployeeID(Integer.parseInt(employeeId));

            saleDAO.updateSale(venda);
            System.out.println("Venda atualizada!");
        } else {
            System.out.println("Venda não encontrada!");
        }
    }

    private static void pesquisarVendasCliente() {
        System.out.print("ID do cliente: ");
        int clientId = Integer.parseInt(scanner.nextLine());
        List<Sale> vendas = saleDAO.searchSalesByClient(clientId);
        vendas.forEach(System.out::println);
    }

    private static void cancelarVenda() {
        System.out.print("ID da venda para cancelar: ");
        int saleId = Integer.parseInt(scanner.nextLine());
        saleDAO.deleteSale(saleId);
        System.out.println("Venda cancelada!");
    }

    private static void listarVendas() {
        List<Sale> vendas = saleDAO.getAllSales();
        vendas.forEach(System.out::println);
    }

    private static void detalhesVenda() {
        System.out.print("ID da venda: ");
        int saleId = Integer.parseInt(scanner.nextLine());
        Sale venda = saleDAO.getSaleById(saleId);
        if (venda != null) {
            System.out.println(venda);
            List<ProductSales> itens = productSalesDAO.getSalesItems(saleId);
            itens.forEach(item -> System.out.println("  -> " + item));
        } else {
            System.out.println("Venda não encontrada!");
        }
    }

    private static void menuFuncionarios() {
        int choice;
        do {
            System.out.println("\n=== FUNCIONÁRIOS ===");
            System.out.println("1. Inserir");
            System.out.println("2. Alterar");
            System.out.println("3. Pesquisar por nome");
            System.out.println("4. Remover");
            System.out.println("5. Listar todos");
            System.out.println("6. Exibir detalhes");
            System.out.println("7. Relatório Mensal de Vendas");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> inserirFuncionario();
                    case 2 -> alterarFuncionario();
                    case 3 -> pesquisarFuncionario();
                    case 4 -> removerFuncionario();
                    case 5 -> listarFuncionarios();
                    case 6 -> exibirFuncionario();
                    case 7 -> exibirRelatorioMensal();
                    case 0 -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida!");
                choice = -1;
            }
        } while (true);
    }

    private static void inserirFuncionario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Salário: ");
        float salario = Float.parseFloat(scanner.nextLine());

        Employee novoFuncionario = new Employee(0, nome, cargo, salario);
        employeeDAO.addEmployee(novoFuncionario);
        System.out.println("Funcionário cadastrado!");
    }

    private static void alterarFuncionario() {
        System.out.print("ID do funcionário: ");
        int id = Integer.parseInt(scanner.nextLine());
        Employee funcionario = employeeDAO.getEmployeeById(id);

        if (funcionario != null) {
            System.out.print("Novo nome (" + funcionario.getEmployeeName() + "): ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) funcionario.setEmployeeName(nome);

            System.out.print("Novo cargo (" + funcionario.getEmployeeRole() + "): ");
            String cargo = scanner.nextLine();
            if (!cargo.isEmpty()) funcionario.setEmployeeRole(cargo);

            System.out.print("Novo salário (" + funcionario.getSalary() + "): ");
            String salario = scanner.nextLine();
            if (!salario.isEmpty()) funcionario.setSalary(Float.parseFloat(salario));

            employeeDAO.updateEmployee(funcionario);
            System.out.println("Funcionário atualizado!");
        } else {
            System.out.println("Funcionário não encontrado!");
        }
    }

    private static void pesquisarFuncionario() {
        System.out.print("Nome para pesquisa: ");
        String nome = scanner.nextLine();
        List<Employee> resultados = employeeDAO.searchEmployeesByName(nome);
        resultados.forEach(System.out::println);
    }

    private static void removerFuncionario() {
        System.out.print("ID para remover: ");
        int id = Integer.parseInt(scanner.nextLine());
        employeeDAO.deleteEmployee(id);
        System.out.println("Funcionário removido!");
    }

    private static void listarFuncionarios() {
        List<Employee> funcionarios = employeeDAO.getAllEmployees();
        funcionarios.forEach(System.out::println);
    }

    private static void exibirFuncionario() {
        System.out.print("ID para exibir: ");
        int id = Integer.parseInt(scanner.nextLine());
        Employee funcionario = employeeDAO.getEmployeeById(id);
        System.out.println(funcionario != null ? funcionario : "Funcionário não encontrado!");
    }
// Add this method to your Menu class after the existing client menu methods

    private static void menuClienteHistorico() {
        System.out.print("ID do cliente para ver histórico: ");
        try {
            int clientId = Integer.parseInt(scanner.nextLine());
            Client cliente = clientDAO.getClientById(clientId);

            if (cliente == null) {
                System.out.println("Cliente não encontrado!");
                return;
            }

            System.out.println("\n=== HISTÓRICO DE COMPRAS DO CLIENTE ===");
            System.out.println("Cliente: " + cliente.getClientName());

            // Get client's order history
            ClientHistoryDAO historyDAO = new ClientHistoryDAO();
            List<Map<String, Object>> orderHistory = historyDAO.getClientOrderHistoryFromView(clientId);

            if (orderHistory.isEmpty()) {
                System.out.println("Este cliente não possui compras registradas.");
                return;
            }

            // Display order summary
            for (Map<String, Object> order : orderHistory) {
                System.out.println("\n-------------------------------------");
                System.out.println("Venda #" + order.get("saleId"));
                System.out.println("Data: " + order.get("saleDate"));
                System.out.println("Valor Total: R$ " + order.get("totalValue"));
                System.out.println("Método de Pagamento: " + order.get("paymentMethod"));
                System.out.println("Status: " + order.get("paymentStatus"));
                System.out.println("Vendedor: " + order.get("employeeName"));

                // Option to view details
                System.out.print("\nVer detalhes desta venda? (S/N): ");
                String opcao = scanner.nextLine();

                if (opcao.equalsIgnoreCase("S")) {
                    int saleId = (Integer) order.get("saleId");
                    Map<String, Object> details = historyDAO.getOrderDetails(saleId);

                    if (details != null) {
                        System.out.println("\n=== DETALHES DA VENDA ===");

                        @SuppressWarnings("unchecked")
                        List<Map<String, Object>> items = (List<Map<String, Object>>) details.get("items");

                        System.out.println("Itens:");
                        for (Map<String, Object> item : items) {
                            Product prod = (Product) item.get("productInfo");
                            System.out.println("  - " + prod.getProductName() +
                                    " (x" + item.get("quantity") + ") - R$ " +
                                    item.get("totalPrice"));
                        }

                        // Calculate and display client discount
                        double discount = historyDAO.calculateClientDiscountProcedure(clientId);
                        System.out.println("\nDesconto aplicado: " + discount + "%");
                    }
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        } catch (RuntimeException e) {
            System.out.println("Erro ao buscar histórico: " + e.getMessage());
        }
    }

    private static void exibirRelatorioMensal() {
        try {
            System.out.print("Digite o mês (1-12): ");
            int month = Integer.parseInt(scanner.nextLine());

            System.out.print("Digite o ano: ");
            int year = Integer.parseInt(scanner.nextLine());

            if (month < 1 || month > 12) {
                System.out.println("Mês inválido!");
                return;
            }

            Map<Employee, BigDecimal> relatorio = saleDAO.getMonthlySalesReport(month, year);

            System.out.println("\n=== RELATÓRIO MENSAL ===");
            System.out.printf("Período: %02d/%d\n", month, year);
            System.out.println("-----------------------------");

            if (relatorio.isEmpty()) {
                System.out.println("Nenhuma venda registrada neste período.");
                return;
            }

            relatorio.forEach((funcionario, total) ->
                    System.out.printf("%s - %s\nTotal Vendido: R$ %,10.2f\n-----------------------------\n",
                            funcionario.getEmployeeName(),
                            funcionario.getEmployeeRole(),
                            total));

        } catch (NumberFormatException e) {
            System.out.println("Formato inválido! Use apenas números.");
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }
}