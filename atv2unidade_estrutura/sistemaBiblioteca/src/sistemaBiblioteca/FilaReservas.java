package sistemaBiblioteca;


public class FilaReservas {

    private NoDuplo<Reserva> inicio;
    private NoDuplo<Reserva> fim;

    public FilaReservas() {
        inicio = null;
        fim = null;
    }

    public void adicionar(Reserva reserva) {

        NoDuplo<Reserva> novo = new NoDuplo<>(reserva);

        if (inicio == null) {
            inicio = novo;
            fim = novo;
        } else {
            novo.setAnterior(fim);
            fim.setProximo(novo);
            fim = novo;
        }
    }

    public Reserva buscar(String isbn) {

        NoDuplo<Reserva> atual = inicio;

        while (atual != null) {

            if (atual.getElemento().getIsbn().equals(isbn)) {
                return atual.getElemento();
            }

            atual = atual.getProximo();
        }

        return null;
    }

    public Reserva remover() {

        if (inicio == null) {
            return null;
        }

        Reserva reserva = inicio.getElemento();

        inicio = inicio.getProximo();

        if (inicio == null) {
            fim = null;
        } else {
            inicio.setAnterior(null);
        }

        return reserva;
    }

    public boolean removerPorISBN(String isbn) {

        NoDuplo<Reserva> atual = inicio;

        while (atual != null) {

            if (atual.getElemento().getIsbn().equals(isbn)) {

                if (atual == inicio) {
                    inicio = atual.getProximo();
                } else {
                    atual.getAnterior().setProximo(
                            atual.getProximo());
                }

                if (atual == fim) {
                    fim = atual.getAnterior();
                } else {
                    atual.getProximo().setAnterior(
                            atual.getAnterior());
                }

                return true;
            }

            atual = atual.getProximo();
        }

        return false;
    }

    public boolean estaVazia() {
        return inicio == null;
    }

    public void listar() {

        if (estaVazia()) {
            System.out.println("Fila de reservas vazia!");
            return;
        }

        NoDuplo<Reserva> atual = inicio;

        while (atual != null) {

            System.out.println("ISBN: "
                    + atual.getElemento().getIsbn());

            System.out.println("Usuário: "
                    + atual.getElemento().getNomeUsuario());

            System.out.println("------------------------");

            atual = atual.getProximo();
        }
    }

    public void listarInverso() {

        if (estaVazia()) {
            System.out.println("Fila de reservas vazia!");
            return;
        }

        NoDuplo<Reserva> atual = fim;

        while (atual != null) {

            System.out.println("ISBN: "
                    + atual.getElemento().getIsbn());

            System.out.println("Usuário: "
                    + atual.getElemento().getNomeUsuario());

            System.out.println("------------------------");

            atual = atual.getAnterior();
        }
    }
}

