import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new Cliente();

        System.out.println("Bem-vindo ao Banco Digital Yaudin!");
        System.out.print("Por favor, insira seu nome: ");
        cliente.setNome(scanner.nextLine());

        Conta corrente = new ContaCorrente(cliente);
        Conta poupanca = new ContaPoupanca(cliente);

        boolean continuar = true;

        while (continuar) {
            System.out.println("\nEscolha uma operação:");
            System.out.println("1. Depositar na Conta Corrente");
            System.out.println("2. Depositar na Conta Poupança");
            System.out.println("3. Sacar da Conta Corrente");
            System.out.println("4. Sacar da Conta Poupança");
            System.out.println("5. Transferir da Conta Corrente para a Poupança");
            System.out.println("6. Transferir da Poupança para a Conta Corrente");
            System.out.println("7. Imprimir Extrato da Conta Corrente");
            System.out.println("8. Imprimir Extrato da Poupança");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            double valor;

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor para depositar na Conta Corrente: ");
                    valor = scanner.nextDouble();
                    corrente.depositar(valor);
                    break;
                case 2:
                    System.out.print("Digite o valor para depositar na Conta Poupança: ");
                    valor = scanner.nextDouble();
                    poupanca.depositar(valor);
                    break;
                case 3:
                    System.out.print("Digite o valor para sacar da Conta Corrente: ");
                    valor = scanner.nextDouble();
                    corrente.sacar(valor);
                    break;
                case 4:
                    System.out.print("Digite o valor para sacar da Conta Poupança: ");
                    valor = scanner.nextDouble();
                    poupanca.sacar(valor);
                    break;
                case 5:
                    System.out.print("Digite o valor para transferir da Conta Corrente para a Poupança: ");
                    valor = scanner.nextDouble();
                    corrente.transferir(valor, poupanca);
                    break;
                case 6:
                    System.out.print("Digite o valor para transferir da Poupança para a Conta Corrente: ");
                    valor = scanner.nextDouble();
                    poupanca.transferir(valor, corrente);
                    break;
                case 7:
                    corrente.imprimirExtrato();
                    break;
                case 8:
                    poupanca.imprimirExtrato();
                    break;
                case 9:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
        System.out.println("Obrigado por usar o Banco Digital Yaudin!");
    }
}
