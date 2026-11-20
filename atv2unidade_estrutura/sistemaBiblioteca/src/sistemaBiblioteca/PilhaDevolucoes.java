package sistemaBiblioteca;


public class PilhaDevolucoes {

    private NoDuplo<RegistroDevolucao> topo;

    public PilhaDevolucoes() {
        topo = null;
    }

    public void empilhar(RegistroDevolucao registro) {

        NoDuplo<RegistroDevolucao> novo =
                new NoDuplo<>(registro);

        if (topo != null) {
            topo.setAnterior(novo);
            novo.setProximo(topo);
        }

        topo = novo;
    }

    public RegistroDevolucao buscar(String isbn) {

        NoDuplo<RegistroDevolucao> atual = topo;

        while (atual != null) {

            if (atual.getElemento().getIsbn().equals(isbn)) {
                return atual.getElemento();
            }

            atual = atual.getProximo();
        }

        return null;
    }

    public RegistroDevolucao buscarPrimeira() {

        if (topo == null) {
            return null;
        }

        return topo.getElemento();
    }

    public RegistroDevolucao desempilhar() {

        if (topo == null) {
            return null;
        }

        RegistroDevolucao registro = topo.getElemento();

        topo = topo.getProximo();

        if (topo != null) {
            topo.setAnterior(null);
        }

        return registro;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public void listar() {

        if (estaVazia()) {
            System.out.println("Histórico de devoluções vazio!");
            return;
        }

        NoDuplo<RegistroDevolucao> atual = topo;

        while (atual != null) {
            atual.getElemento().exibirDados();
            atual = atual.getProximo();
        }
    }

    public void listarInverso() {

        if (estaVazia()) {
            System.out.println("Histórico de devoluções vazio!");
            return;
        }

        NoDuplo<RegistroDevolucao> atual = topo;

        while (atual.getProximo() != null) {
            atual = atual.getProximo();
        }

        while (atual != null) {
            atual.getElemento().exibirDados();
            atual = atual.getAnterior();
        }
    }
}
