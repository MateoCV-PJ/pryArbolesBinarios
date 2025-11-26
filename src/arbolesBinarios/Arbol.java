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

        for (char ch : cadena.toCharArray()) {
            insertarDato(String.valueOf(ch));
        }
    }

    // Posorden: Izquierdo, derecho, Raiz
    public String postOrden() {
        StringBuilder ps = new StringBuilder();
        postOrdenRec(Raiz, ps);
        return ps.toString().trim();
    }
    private void postOrdenRec(Nodo r, StringBuilder ps) {
        if (r != null) {
            postOrdenRec(r.getLi(), ps);
            postOrdenRec(r.getLd(), ps);
            ps.append(r.getDato()).append(' ');
        }
    }

    // Inorden: Izquierdo, Raiz, Derecho
    public String inOrden() {
        StringBuilder ino = new StringBuilder();
        inOrdenRec(Raiz, ino);
        return ino.toString().trim();
    }
    private void inOrdenRec(Nodo r, StringBuilder ino) {
        if (r != null) {
            inOrdenRec(r.getLi(), ino);
            ino.append(r.getDato()).append(' ');
            inOrdenRec(r.getLd(), ino);
        }
    }

    // Preorden: Raiz, izquierdo, derecho
    public String preOrden() {
        StringBuilder pr = new StringBuilder();
        preOrdenRec(Raiz, pr);
        return pr.toString().trim();
    }
    private void preOrdenRec(Nodo r, StringBuilder pr) {
        if (r != null) {
            pr.append(r.getDato()).append(' ');
            preOrdenRec(r.getLi(), pr);
            preOrdenRec(r.getLd(), pr);
        }
    }

    //Mostrar Hojas del Arbol
    public String soloHojas() {
        StringBuilder sh = new StringBuilder();
        soloHojasRec(Raiz, sh);
        return sh.toString().trim();
    }
    private void soloHojasRec(Nodo r, StringBuilder sh) {
        if (r != null) {
            soloHojasRec(r.getLi(), sh);
            if (r.getLi() == null && r.getLd() == null) {
                sh.append(r.getDato()).append(' ');
            }
            soloHojasRec(r.getLd(), sh);
        }
    }

    //Mostrar Ramas del Arbol
    public String soloRamas() {
        StringBuilder sr = new StringBuilder();
        soloRamasRec(Raiz, sr);
        return sr.toString().trim();
    }
    private void soloRamasRec(Nodo r, StringBuilder sr) {
        if (r != null) {
            soloRamasRec(r.getLi(), sr);
            if (r.getLi() != null || r.getLd() != null) {
                sr.append(r.getDato()).append(' ');
            }
            soloRamasRec(r.getLd(), sr);
        }
    }

    //Mostrar nivel de un dato ingresado
    public int nivel(String s) {
        char dato = s.charAt(0);
        if (Raiz == null) {
            return -1;
        }
        char d = Character.toUpperCase(dato);
        Nodo p = Raiz;
        int nivel = 1;
        while (p != null) {
            if (d == p.getDato()) {
                return nivel;
            } else if (d > p.getDato()) {
                p = p.getLd();
            } else {
                p = p.getLi();
            }
            nivel++;
        }
        return -1;
    }

    //Altura de un dato ingresado
    public int altura(String s) {
        // Asumimos que s contiene al menos un carácter
        if (Raiz == null) return -1;
        char buscado = Character.toUpperCase(s.charAt(0));
        // buscar nodo
        Nodo p = Raiz;
        while (p != null && p.getDato() != buscado) {
            if (buscado > p.getDato()) p = p.getLd(); else p = p.getLi();
        }
        if (p == null) return -1; // no encontrado
        return alturaNodo(p);
    }
    // altura de un nodo: altura de subárbol en número de aristas
    private int alturaNodo(Nodo r) {
        if (r == null) return -1;
        int hi = alturaNodo(r.getLi());
        int hd = alturaNodo(r.getLd());
        return Math.max(hi, hd) + 1;
    }

    //Mostrar Ancestros de un dato ingresado
    public String ancestros(String s) {
        if (Raiz == null) {
            return "";
        }
        char dato = Character.toUpperCase(s.charAt(0));
        StringBuilder ancestros = new StringBuilder();
        Nodo p = Raiz;
        while (p != null && p.getDato() != dato) {
            ancestros.append(p.getDato()).append(' ');
            if (dato > p.getDato()) {
                p = p.getLd();
            } else {
                p = p.getLi();
            }
        }
        if (p == null) {
            return "";
        }
        return ancestros.toString().trim();
    }

    //Insertar un dato en el arbol
    public boolean insertarDato(String s) {
        char d = Character.toUpperCase(s.charAt(0));
        Nodo nuevo = new Nodo(d);
        if (this.getRaiz() == null) {
            this.setRaiz(nuevo);
            return true;
        }
        Nodo p = this.getRaiz();
        while (true) {
            if (d > p.getDato()) {
                if (p.getLd() == null) {
                    p.setLd(nuevo);
                    return true;
                } else {
                    p = p.getLd();
                }
            } else if (d < p.getDato()) {
                if (p.getLi() == null) {
                    p.setLi(nuevo);
                    return true;
                } else {
                    p = p.getLi();
                }
            } else {
                // no insertar duplicados
                return false;
            }
        }
    }

    //Eliminar un dato en el arbol --------------- REVISAR
    public boolean eliminarDato(String s) {
        if (Raiz == null) return false;
        char d = Character.toUpperCase(s.charAt(0));
        boolean[] eliminado = new boolean[1];
        Raiz = eliminarRec(Raiz, d, eliminado);
        return eliminado[0];
    }
    private Nodo eliminarRec(Nodo r, char d, boolean[] eliminado) {
        if (r == null) return null;
        if (d < r.getDato()) {
            r.setLi(eliminarRec(r.getLi(), d, eliminado));
        } else if (d > r.getDato()) {
            r.setLd(eliminarRec(r.getLd(), d, eliminado));
        } else {
            // encontrado
            eliminado[0] = true;
            // caso 1: hoja
            if (r.getLi() == null && r.getLd() == null) {
                return null;
            }
            // caso 2: un solo hijo
            if (r.getLi() == null) {
                return r.getLd();
            } else if (r.getLd() == null) {
                return r.getLi();
            }
            // caso 3: dos hijos -> reemplazar por sucesor (mínimo del subárbol derecho)
            Nodo sucesor = minNodo(r.getLd());
            r.setDato(sucesor.getDato());
            // eliminar el sucesor en el subárbol derecho
            r.setLd(eliminarRec(r.getLd(), sucesor.getDato(), eliminado));
        }
        return r;
    }
    private Nodo minNodo(Nodo r) {
        Nodo p = r;
        while (p != null && p.getLi() != null) {
            p = p.getLi();
        }
        return p;
    }

    //Mostrar arbol
    //Creando implimentacion grafica por swing o javaFX

    //Mostrar Hermanos de un dato ingresado
    public String hermanos(String s) {
        if (Raiz == null) {
            return "";
        }
        char dato = Character.toUpperCase(s.charAt(0));
        Nodo p = Raiz;
        while (p != null) {
            if ((p.getLi() != null && p.getLi().getDato() == dato) ||
                    (p.getLd() != null && p.getLd().getDato() == dato)) {
                StringBuilder hermanos = new StringBuilder();
                if (p.getLi() != null && p.getLi().getDato() != dato) {
                    hermanos.append(p.getLi().getDato()).append(' ');
                }
                if (p.getLd() != null && p.getLd().getDato() != dato) {
                    hermanos.append(p.getLd().getDato()).append(' ');
                }
                return hermanos.toString().trim();
            }
            if (dato > p.getDato()) {
                p = p.getLd();
            } else {
                p = p.getLi();
            }
        }
        return "";
    }

}