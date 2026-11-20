package sistemaBiblioteca;


public class ArvoreBinaria {

    private NoArvore raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    public boolean inserir(Livro livro) {

        if (buscar(livro.getIsbn()) != null) {
            return false;
        }

        raiz = inserirRecursivo(raiz, livro);
        return true;
    }

    private NoArvore inserirRecursivo(
            NoArvore atual, Livro livro) {

        if (atual == null) {
            return new NoArvore(livro);
        }

        if (livro.getIsbn().compareTo(
                atual.getLivro().getIsbn()) < 0) {

            atual.setEsquerda(
                    inserirRecursivo(atual.getEsquerda(), livro));

        } else {

            atual.setDireita(
                    inserirRecursivo(atual.getDireita(), livro));
        }

        return atual;
    }

    public Livro buscar(String isbn) {

        NoArvore atual = raiz;

        while (atual != null) {

            int comparacao =
                    isbn.compareTo(atual.getLivro().getIsbn());

            if (comparacao == 0) {
                return atual.getLivro();
            }

            if (comparacao < 0) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }

        return null;
    }

    public boolean remover(String isbn) {

        if (buscar(isbn) == null) {
            return false;
        }

        raiz = removerRecursivo(raiz, isbn);
        return true;
    }

    private NoArvore removerRecursivo(
            NoArvore atual, String isbn) {

        if (atual == null) {
            return null;
        }

        int comparacao =
                isbn.compareTo(atual.getLivro().getIsbn());

        if (comparacao < 0) {

            atual.setEsquerda(
                    removerRecursivo(atual.getEsquerda(), isbn));

        } else if (comparacao > 0) {

            atual.setDireita(
                    removerRecursivo(atual.getDireita(), isbn));

        } else {

            if (atual.getEsquerda() == null &&
                    atual.getDireita() == null) {
                return null;
            }

            if (atual.getEsquerda() == null) {
                return atual.getDireita();
            }

            if (atual.getDireita() == null) {
                return atual.getEsquerda();
            }

            NoArvore sucessor = encontrarMenor(
                    atual.getDireita());

            atual.setLivro(sucessor.getLivro());

            atual.setDireita(
                    removerRecursivo(
                            atual.getDireita(),
                            sucessor.getLivro().getIsbn()));
        }

        return atual;
    }

    private NoArvore encontrarMenor(NoArvore atual) {

        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }

        return atual;
    }

    public void percursoInOrdem() {
        inOrdem(raiz);
    }

    private void inOrdem(NoArvore atual) {

        if (atual != null) {
            inOrdem(atual.getEsquerda());
            atual.getLivro().exibirDados();
            System.out.println("------------------------");
            inOrdem(atual.getDireita());
        }
    }

    public void percursoPreOrdem() {
        preOrdem(raiz);
    }

    private void preOrdem(NoArvore atual) {

        if (atual != null) {
            atual.getLivro().exibirDados();
            System.out.println("------------------------");
            preOrdem(atual.getEsquerda());
            preOrdem(atual.getDireita());
        }
    }

    public void percursoPosOrdem() {
        posOrdem(raiz);
    }

    private void posOrdem(NoArvore atual) {

        if (atual != null) {
            posOrdem(atual.getEsquerda());
            posOrdem(atual.getDireita());
            atual.getLivro().exibirDados();
            System.out.println("------------------------");
        }
    }
}

