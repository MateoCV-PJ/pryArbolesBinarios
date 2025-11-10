package arbolesBinarios;

public class Nodo {
    
    //Atributos
    private char Dato;
    private Nodo Li, Ld;
    
    public Nodo(char Dato) {
        this.Dato = Dato;
        this.Li = null;
        this.Ld = null;
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
}
