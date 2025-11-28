package arbolesBinarios;

public class Nodo {
    
    //Atributos
    private char Dato;
    private Nodo Li, Ld;
    // altura/height para operaciones AVL (1 para hoja)
    private int altura;

    public Nodo(char Dato) {
        this.Dato = Dato;
        this.Li = null;
        this.Ld = null;
        this.altura = 1; // por convención, nodo hoja tiene altura 1
    }

    public char getDato() {
        return Dato;
    }

    public void setDato(char Dato) {
        this.Dato = Dato;
    }

    public Nodo getLi() {
        return Li;
    }

    public void setLi(Nodo Li) {
        this.Li = Li;
    }

    public Nodo getLd() {
        return Ld;
    }

    public void setLd(Nodo Ld) {
        this.Ld = Ld;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}
