package sistemaBiblioteca;


public class RegistroDevolucao {

    private String isbn;
    private String titulo;
    private String nomeUsuario;

    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minuto;

    public RegistroDevolucao(String isbn, String titulo,
            String nomeUsuario, int dia, int mes, int ano,
            int hora, int minuto) {

        this.isbn = isbn;
        this.titulo = titulo;
        this.nomeUsuario = nomeUsuario;

        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
        this.minuto = minuto;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public void exibirDados() {

        System.out.println("ISBN: " + isbn);
        System.out.println("Livro: " + titulo);
        System.out.println("Usuário: " + nomeUsuario);

        System.out.print("Data da devolução: ");

        if (dia < 10) {
            System.out.print("0");
        }
        System.out.print(dia + "/");

        if (mes < 10) {
            System.out.print("0");
        }
        System.out.print(mes + "/" + ano);

        System.out.print(" às ");

        if (hora < 10) {
            System.out.print("0");
        }
        System.out.print(hora + ":");

        if (minuto < 10) {
            System.out.print("0");
        }
        System.out.println(minuto);

        System.out.println("--------------------------------");
    }
}
