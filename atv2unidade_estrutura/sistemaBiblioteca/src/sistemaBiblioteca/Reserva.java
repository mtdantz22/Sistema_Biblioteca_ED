package sistemaBiblioteca;


public class Reserva {

    private String isbn;
    private String nomeUsuario;

    public Reserva(String isbn, String nomeUsuario) {
        setIsbn(isbn);
        setNomeUsuario(nomeUsuario);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            System.out.println("ISBN inválido!");
        } else {
            this.isbn = isbn.trim().replace("-", "")
                    .replace(" ", "").toUpperCase();
        }
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        if (nomeUsuario == null || nomeUsuario.trim().isEmpty()) {
            System.out.println("Nome inválido!");
        } else {
            this.nomeUsuario = nomeUsuario.trim();
        }
    }
}
