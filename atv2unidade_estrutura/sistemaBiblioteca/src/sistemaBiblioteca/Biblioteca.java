package sistemaBiblioteca;




import java.util.Scanner;

public class Biblioteca {

    private ArvoreBinaria acervo;
    private FilaReservas reservas;
    private PilhaDevolucoes devolucoes;
    private Scanner entrada;

    public Biblioteca() {
        acervo = new ArvoreBinaria();
        reservas = new FilaReservas();
        devolucoes = new PilhaDevolucoes();
        entrada = new Scanner(System.in);
    }

    private String lerIsbn() {
        System.out.print("Informe o ISBN: ");

        return entrada.nextLine()
                .trim()
                .replace("-", "")
                .replace(" ", "")
                .toUpperCase();
    }

    private int lerInteiro(String mensagem) {

        int numero;

        while (true) {

            System.out.print(mensagem);

            if (entrada.hasNextInt()) {
                numero = entrada.nextInt();
                entrada.nextLine();
                return numero;
            } else {
                System.out.println("Digite um número válido.");
                entrada.nextLine();
            }
        }
    }

    public void cadastrarLivro() {

        System.out.println("\n--- CADASTRAR LIVRO ---");

        String isbn = lerIsbn();

        if (isbn.isEmpty()) {
            System.out.println("ISBN inválido.");
            return;
        }

        System.out.print("Informe o título: ");
        String titulo = entrada.nextLine();

        System.out.print("Informe o autor: ");
        String autor = entrada.nextLine();

        if (titulo.trim().isEmpty() || autor.trim().isEmpty()) {
            System.out.println("Título e autor são obrigatórios.");
            return;
        }

        Livro livro = new Livro(isbn, titulo, autor);

        if (acervo.inserir(livro)) {
            System.out.println("Livro cadastrado com sucesso!");
        } else {
            System.out.println("Já existe um livro com esse ISBN.");
        }
    }

    public void listarLivros() {

        System.out.println("\n--- ACERVO DA BIBLIOTECA ---");

        acervo.percursoInOrdem();
    }

    public void buscarLivro() {

        System.out.println("\n--- BUSCAR LIVRO ---");

        String isbn = lerIsbn();

        Livro livro = acervo.buscar(isbn);

        if (livro != null) {
            livro.exibirDados();
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    public void emprestarLivro() {

        System.out.println("\n--- REALIZAR EMPRÉSTIMO ---");

        String isbn = lerIsbn();

        Livro livro = acervo.buscar(isbn);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        if (livro.isEmprestado()) {
            System.out.println("Este livro já está emprestado.");
            return;
        }

        System.out.print("Informe o nome do usuário: ");
        String nomeUsuario = entrada.nextLine();

        if (nomeUsuario.trim().isEmpty()) {
            System.out.println("Nome do usuário obrigatório.");
            return;
        }

        livro.setEmprestado(true);
        livro.setNomeUsuarioEmprestimo(nomeUsuario);

        System.out.println("\nEmpréstimo realizado com sucesso!");
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Usuário: " + livro.getNomeUsuarioEmprestimo());
    }

    public void reservarLivro() {

        System.out.println("\n--- RESERVAR LIVRO ---");

        String isbn = lerIsbn();

        Livro livro = acervo.buscar(isbn);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        if (!livro.isEmprestado()) {
            System.out.println("O livro está disponível para empréstimo.");
            return;
        }

        System.out.print("Informe o nome do usuário: ");
        String nomeUsuario = entrada.nextLine();

        if (nomeUsuario.trim().isEmpty()) {
            System.out.println("Nome do usuário obrigatório.");
            return;
        }

        Reserva reserva = new Reserva(isbn, nomeUsuario);

        reservas.adicionar(reserva);

        System.out.println("Reserva realizada com sucesso!");
    }

    private boolean dataValida(int dia, int mes, int ano) {

        if (ano < 1 || mes < 1 || mes > 12 || dia < 1) {
            return false;
        }

        int diasMes;

        switch (mes) {

            case 2:

                if ((ano % 400 == 0) ||
                    (ano % 4 == 0 && ano % 100 != 0)) {
                    diasMes = 29;
                } else {
                    diasMes = 28;
                }

                break;

            case 4:
            case 6:
            case 9:
            case 11:

                diasMes = 30;
                break;

            default:

                diasMes = 31;
                break;
        }

        return dia <= diasMes;
    }

    public void devolverLivro() {

        System.out.println("\n--- DEVOLVER LIVRO ---");

        String isbn = lerIsbn();

        Livro livro = acervo.buscar(isbn);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        if (!livro.isEmprestado()) {
            System.out.println("Este livro não está emprestado.");
            return;
        }

        

        String nomeUsuario = livro.getNomeUsuarioEmprestimo();

        if (nomeUsuario == null || nomeUsuario.trim().isEmpty()) {
            System.out.println("Não foi possível identificar o usuário do empréstimo.");
            return;
        }

        System.out.println("\nLivro: " + livro.getTitulo());
        System.out.println("Usuário que estava com o livro: " + nomeUsuario);

        int dia;
        int mes;
        int ano;

        do {

            dia = lerInteiro("Informe o dia da devolução: ");
            mes = lerInteiro("Informe o mês da devolução: ");
            ano = lerInteiro("Informe o ano da devolução: ");

            if (!dataValida(dia, mes, ano)) {
                System.out.println("Data inválida. Digite novamente.");
            }

        } while (!dataValida(dia, mes, ano));

        int hora;
        int minuto;

        do {

            hora = lerInteiro("Informe a hora da devolução (0 a 23): ");
            minuto = lerInteiro("Informe os minutos (0 a 59): ");

            if (hora < 0 || hora > 23 || minuto < 0 || minuto > 59) {
                System.out.println("Horário inválido. Digite novamente.");
            }

        } while (hora < 0 || hora > 23 || minuto < 0 || minuto > 59);

       

        RegistroDevolucao registro = new RegistroDevolucao(
                livro.getIsbn(),
                livro.getTitulo(),
                nomeUsuario,
                dia,
                mes,
                ano,
                hora,
                minuto
        );

        devolucoes.empilhar(registro);

        

        Reserva proximaReserva = reservas.buscar(isbn);

        if (proximaReserva != null) {

            reservas.removerPorISBN(isbn);

           

            livro.setEmprestado(true);
            livro.setNomeUsuarioEmprestimo(proximaReserva.getNomeUsuario());

            System.out.println("\nDevolução registrada com sucesso!");
            System.out.println("O livro foi automaticamente reservado para:");
            System.out.println(proximaReserva.getNomeUsuario());

        } else {

           
            livro.setEmprestado(false);
            livro.setNomeUsuarioEmprestimo(null);

            System.out.println("\nDevolução registrada com sucesso!");
            System.out.println("O livro está disponível para novo empréstimo.");
        }
    }

    public void listarReservas() {

        System.out.println("\n--- FILA DE RESERVAS ---");

        reservas.listar();
    }

    public void listarDevolucoes() {

        System.out.println("\n--- HISTÓRICO DE DEVOLUÇÕES ---");

        devolucoes.listar();
    }

    public void removerLivro() {

        System.out.println("\n--- REMOVER LIVRO ---");

        String isbn = lerIsbn();

        Livro livro = acervo.buscar(isbn);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        if (livro.isEmprestado()) {
            System.out.println("Não é possível remover um livro emprestado.");
            return;
        }

        if (reservas.buscar(isbn) != null) {
            System.out.println("Não é possível remover um livro com reservas pendentes.");
            return;
        }

        if (acervo.remover(isbn)) {
            System.out.println("Livro removido com sucesso!");
        } else {
            System.out.println("Não foi possível remover o livro.");
        }
    }

    public void executar() {

        int opcao;

        do {

            System.out.println("\n========== BIBLIOTECA PÚBLICA ==========");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Listar acervo");
            System.out.println("3 - Buscar livro");
            System.out.println("4 - Realizar empréstimo");
            System.out.println("5 - Reservar livro");
            System.out.println("6 - Devolver livro");
            System.out.println("7 - Listar reservas");
            System.out.println("8 - Histórico de devoluções");
            System.out.println("9 - Remover livro");
            System.out.println("0 - Sair");

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {

                case 1:
                    cadastrarLivro();
                    break;

                case 2:
                    listarLivros();
                    break;

                case 3:
                    buscarLivro();
                    break;

                case 4:
                    emprestarLivro();
                    break;

                case 5:
                    reservarLivro();
                    break;

                case 6:
                    devolverLivro();
                    break;

                case 7:
                    listarReservas();
                    break;

                case 8:
                    listarDevolucoes();
                    break;

                case 9:
                    removerLivro();
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcao != 0);

        entrada.close();
    }

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        biblioteca.executar();
    }
}
