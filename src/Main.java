import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Banco Digital Yaudin");

        VBox vbox = new VBox(10);
        vbox.setPadding(new javafx.geometry.Insets(10));

        Label label = new Label("Bem-vindo ao Banco Digital Yaudin!");
        TextField nomeField = new TextField();
        nomeField.setPromptText("Insira seu nome");

        Button iniciarButton = new Button("Iniciar");
        iniciarButton.setOnAction(e -> {
            String nome = nomeField.getText();
            if (!nome.isEmpty()) {
                Cliente cliente = new Cliente();
                cliente.setNome(nome);
                Conta cc = new ContaCorrente(cliente);
                Conta poupanca = new ContaPoupanca(cliente);
                mostrarMenu(cliente, cc, poupanca);
            }
        });

        vbox.getChildren().addAll(label, nomeField, iniciarButton);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarMenu(Cliente cliente, Conta cc, Conta poupanca) {
        Stage menuStage = new Stage();
        menuStage.setTitle("Menu do Banco Digital Yaudin");

        VBox vbox = new VBox(10);
        vbox.setPadding(new javafx.geometry.Insets(10));

        Label label = new Label("Olá, " + cliente.getNome() + "! Escolha uma operação:");
        Button depositarCcButton = new Button("Depositar na Conta Corrente");
        depositarCcButton.setOnAction(e -> {
            // Lógica para depósito
        });

        // Adicionar outros botões e lógica

        vbox.getChildren().addAll(label, depositarCcButton /* Adicionar outros botões */);

        Scene scene = new Scene(vbox, 300, 400);
        menuStage.setScene(scene);
        menuStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
