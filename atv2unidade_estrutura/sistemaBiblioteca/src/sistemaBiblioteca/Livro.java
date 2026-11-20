package sistemaBiblioteca;



public class Livro {

    private String isbn;
    private String titulo;
    private String autor;
    private boolean emprestado;
    private String nomeUsuarioEmprestimo;

    public Livro(String isbn, String titulo, String autor) {
        setIsbn(isbn);
        setTitulo(titulo);
        setAutor(autor);
        this.emprestado = false;
        this.nomeUsuarioEmprestimo = null;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (isbn != null && !isbn.trim().isEmpty()) {
            this.isbn = isbn.replace("-", "").replace(" ", "").toUpperCase();
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty()) {
            this.titulo = titulo;
        }
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        if (autor != null && !autor.trim().isEmpty()) {
            this.autor = autor;
        }
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public String getNomeUsuarioEmprestimo() {
        return nomeUsuarioEmprestimo;
    }

    public void setNomeUsuarioEmprestimo(String nomeUsuarioEmprestimo) {
        this.nomeUsuarioEmprestimo = nomeUsuarioEmprestimo;
    }

    public void exibirDados() {
        System.out.println("ISBN: " + isbn);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);

        if (emprestado) {
            System.out.println("Status: Emprestado");
            System.out.println("Usuário: " + nomeUsuarioEmprestimo);
        } else {
            System.out.println("Status: Disponível");
        }
    }
}
