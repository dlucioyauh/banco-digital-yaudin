import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Banco banco;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        banco = new Banco();

        primaryStage.setTitle("Banco Digital Yaudin");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        Label label = new Label("Bem-vindo ao Banco Digital Yaudin!");
        TextField nomeField = new TextField();
        nomeField.setPromptText("Insira seu nome");

        Button iniciarButton = new Button("Iniciar");
        iniciarButton.setOnAction(e -> {
            String nome = nomeField.getText();
            if (!nome.isEmpty()) {
                Cliente cliente = new Cliente();
                cliente.setNome(nome);
                ContaCorrente cc = new ContaCorrente(cliente);
                ContaPoupanca poupanca = new ContaPoupanca(cliente);
                banco.adicionarConta(cc);
                banco.adicionarConta(poupanca);
                showMenu(cliente, cc, poupanca);
                primaryStage.close(); // Fechar a tela inicial após iniciar
            }
        });

        vbox.getChildren().addAll(label, nomeField, iniciarButton);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showMenu(Cliente cliente, ContaCorrente cc, ContaPoupanca poupanca) {
        Stage menuStage = new Stage();
        menuStage.setTitle("Menu do Banco Digital Yaudin");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        Label label = new Label("Olá, " + cliente.getNome() + "! Escolha uma operação:");

        Button depositarCcButton = new Button("Depositar na Conta Corrente");
        depositarCcButton.setOnAction(e -> realizarDeposito(cc));

        Button sacarCcButton = new Button("Sacar da Conta Corrente");
        sacarCcButton.setOnAction(e -> realizarSaque(cc));

        Button transferirCcParaPoupancaButton = new Button("Transferir da Conta Corrente para Poupança");
        transferirCcParaPoupancaButton.setOnAction(e -> realizarTransferencia(cc, poupanca));

        Button depositarPoupancaButton = new Button("Depositar na Conta Poupanca");
        depositarPoupancaButton.setOnAction(e -> realizarDeposito(poupanca));

        Button sacarPoupancaButton = new Button("Sacar da Conta Poupanca");
        sacarPoupancaButton.setOnAction(e -> realizarSaque(poupanca));

        Button saldoCcButton = new Button("Ver Saldo Conta Corrente");
        saldoCcButton.setOnAction(e -> exibirSaldo(cc));

        Button saldoPoupancaButton = new Button("Ver Saldo Conta Poupanca");
        saldoPoupancaButton.setOnAction(e -> exibirSaldo(poupanca));

        vbox.getChildren().addAll(label, depositarCcButton, sacarCcButton, transferirCcParaPoupancaButton, depositarPoupancaButton, sacarPoupancaButton, saldoCcButton, saldoPoupancaButton);

        Scene scene = new Scene(vbox, 300, 400);
        menuStage.setScene(scene);
        menuStage.show();
    }

    private void realizarDeposito(Conta conta) {
        Stage depositoStage = new Stage();
        depositoStage.setTitle("Depósito");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        Label label = new Label("Valor do depósito:");
        TextField valorField = new TextField();
        valorField.setPromptText("Insira o valor");

        Button confirmarButton = new Button("Confirmar");
        confirmarButton.setOnAction(e -> {
            double valor = Double.parseDouble(valorField.getText());
            conta.depositar(valor);
            depositoStage.close();
            showMenu(conta.getCliente(), getContaCorrente(conta.getCliente()), getContaPoupanca(conta.getCliente()));
        });

        Button voltarButton = new Button("Voltar ao Menu");
        voltarButton.setOnAction(e -> {
            depositoStage.close();
            showMenu(conta.getCliente(), getContaCorrente(conta.getCliente()), getContaPoupanca(conta.getCliente()));
        });

        vbox.getChildren().addAll(label, valorField, confirmarButton, voltarButton);

        Scene scene = new Scene(vbox, 300, 200);
        depositoStage.setScene(scene);
        depositoStage.show();
    }

    private void realizarSaque(Conta conta) {
        Stage saqueStage = new Stage();
        saqueStage.setTitle("Saque");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        Label label = new Label("Valor do saque:");
        TextField valorField = new TextField();
        valorField.setPromptText("Insira o valor");

        Button confirmarButton = new Button("Confirmar");
        confirmarButton.setOnAction(e -> {
            double valor = Double.parseDouble(valorField.getText());
            conta.sacar(valor);
            saqueStage.close();
            showMenu(conta.getCliente(), getContaCorrente(conta.getCliente()), getContaPoupanca(conta.getCliente()));
        });

        Button voltarButton = new Button("Voltar ao Menu");
        voltarButton.setOnAction(e -> {
            saqueStage.close();
            showMenu(conta.getCliente(), getContaCorrente(conta.getCliente()), getContaPoupanca(conta.getCliente()));
        });

        vbox.getChildren().addAll(label, valorField, confirmarButton, voltarButton);

        Scene scene = new Scene(vbox, 300, 200);
        saqueStage.setScene(scene);
        saqueStage.show();
    }

    private void realizarTransferencia(Conta origem, Conta destino) {
        Stage transferenciaStage = new Stage();
        transferenciaStage.setTitle("Transferência");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        Label label = new Label("Valor da transferência:");
        TextField valorField = new TextField();
        valorField.setPromptText("Insira o valor");

        Button confirmarButton = new Button("Confirmar");
        confirmarButton.setOnAction(e -> {
            double valor = Double.parseDouble(valorField.getText());
            origem.transferir(valor, destino);
            transferenciaStage.close();
            showMenu(origem.getCliente(), getContaCorrente(origem.getCliente()), getContaPoupanca(origem.getCliente()));
        });

        Button voltarButton = new Button("Voltar ao Menu");
        voltarButton.setOnAction(e -> {
            transferenciaStage.close();
            showMenu(origem.getCliente(), getContaCorrente(origem.getCliente()), getContaPoupanca(origem.getCliente()));
        });

        vbox.getChildren().addAll(label, valorField, confirmarButton, voltarButton);

        Scene scene = new Scene(vbox, 300, 200);
        transferenciaStage.setScene(scene);
        transferenciaStage.show();
    }

    private void exibirSaldo(Conta conta) {
        Stage saldoStage = new Stage();
        saldoStage.setTitle("Saldo");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        Label label = new Label("Saldo atual: " + conta.getSaldo());

        Button voltarButton = new Button("Voltar ao Menu");
        voltarButton.setOnAction(e -> {
            saldoStage.close();
            showMenu(conta.getCliente(), getContaCorrente(conta.getCliente()), getContaPoupanca(conta.getCliente()));
        });

        vbox.getChildren().addAll(label, voltarButton);

        Scene scene = new Scene(vbox, 300, 150);
        saldoStage.setScene(scene);
        saldoStage.show();
    }

    private ContaCorrente getContaCorrente(Cliente cliente) {
        return banco.getContas().stream()
                .filter(conta -> conta instanceof ContaCorrente && conta.getCliente().equals(cliente))
                .map(conta -> (ContaCorrente) conta)
                .findFirst()
                .orElse(null);
    }

    private ContaPoupanca getContaPoupanca(Cliente cliente) {
        return banco.getContas().stream()
                .filter(conta -> conta instanceof ContaPoupanca && conta.getCliente().equals(cliente))
                .map(conta -> (ContaPoupanca) conta)
                .findFirst()
                .orElse(null);
    }
}
