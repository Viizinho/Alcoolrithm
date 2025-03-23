package src.UI;

import src.models.Client;
import src.models.Product;
import src.models.Employee;
import src.models.Sale;
import src.models.ProductSales;
import src.services.Relatory;
import java.util.Scanner;

public class Menu {
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Gerenciar Produtos");
            System.out.println("2. Gerenciar Clientes");
            System.out.println("3. Gerenciar Funcionários");
            System.out.println("4. Realizar Venda");
            System.out.println("5. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    gerenciarProdutos(scanner);
                    break;
                case 2:
                    gerenciarClientes(scanner);
                    break;
                case 3:
                    gerenciarFuncionarios(scanner);
                    break;
                case 4:
                    realizarVenda(scanner);
                    break;
                case 5:
                    relatorios(scanner);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 6);

        scanner.close();
    }

    private static void gerenciarProdutos(Scanner scanner) {
        System.out.println("1. Inserir produto");
        System.out.println("2. Listar produtos");
        System.out.println("3. Buscar produto");
        System.out.println("4. Atualizar produto");
        System.out.println("5. Remover produto");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("Inserindo produto...");
                break;
            case 2:
                System.out.println("Listando produtos...");
                break;
            case 3:
                System.out.println("Buscando produto...");
                break;
            case 4:
                System.out.println("Atualizando produto...");
                break;
            case 5:
                System.out.println("Removendo produto...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void gerenciarClientes(Scanner scanner) {
        System.out.println("1. Inserir cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Buscar cliente");
        System.out.println("4. Atualizar cliente");
        System.out.println("5. Remover cliente");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("Inserindo cliente...");
                break;
            case 2:
                System.out.println("Listando clientes...");
                break;
            case 3:
                System.out.println("Buscando cliente...");
                break;
            case 4:
                System.out.println("Atualizando cliente...");
                break;
            case 5:
                System.out.println("Removendo cliente...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void gerenciarFuncionarios(Scanner scanner) {
        System.out.println("1. Inserir funcionário");
        System.out.println("2. Listar funcionários");
        System.out.println("3. Buscar funcionário");
        System.out.println("4. Atualizar funcionário");
        System.out.println("5. Remover funcionário");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("Inserindo funcionário...");
                break;
            case 2:
                System.out.println("Listando funcionários...");
                break;
            case 3:
                System.out.println("Buscando funcionário...");
                break;
            case 4:
                System.out.println("Atualizando funcionário...");
                break;
            case 5:
                System.out.println("Removendo funcionário...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void realizarVenda(Scanner scanner) {
        System.out.println("1. Criar venda");
        System.out.println("2. Adicionar itens");
        System.out.println("3. Finalizar venda");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("Criando venda...");
                break;
            case 2:
                System.out.println("Adicionando itens...");
                break;
            case 3:
                System.out.println("Finalizando venda...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void relatorios(Scanner scanner) {
        System.out.println("1. Relatório de estoque");
        System.out.println("2. Relatório de vendas");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("Gerando relatório de estoque...");
                break;
            case 2:
                System.out.println("Gerando relatório de vendas...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}
