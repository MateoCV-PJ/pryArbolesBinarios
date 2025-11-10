package arbolesBinarios;

public class Arbol {
    
    //Atributos
    private Nodo Raiz;
    
    public Arbol() {
        this.Raiz = null;
    }

    public Nodo getRaiz() {
        return Raiz;
    }

    public void setRaiz(Nodo Raiz) {
        this.Raiz = Raiz;
    }
    
    public void Construir(String cad) {
        
        String cadena = cad.toUpperCase();
        char Vc[] = cadena.toCharArray();
        
        int i=0;
        Nodo p = null;
        boolean bandera = false;
        
        while(i < Vc.length) {
            if (Raiz == null) {
                Nodo x = new Nodo(Vc[i]);
                Raiz = x;
            } else {
                p = Raiz;
                while (bandera) {
                    if (Vc[i] > p.getDato()) {
                        if (p.getLd() == null) {
                            Nodo x = new Nodo(Vc[i]);
                            p.setLd(x);
                            bandera = true;
                        } else {
                            p = p.getLd();
                        }
                    } else {
                        if (Vc[i] < p.getDato()) {
                            if (p.getLi() == null) {
                                Nodo x = new Nodo(Vc[i]);
                                p.setLi(x);
                                bandera = true;
                            } else {
                                p = p.getLi();
                            }
                        } else {
                            bandera = true;
                        }
                    }
                }
                
            }
        }
        i++;
        bandera = false;
        
        
    }
    
}
