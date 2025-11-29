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

    // Construir el árbol AVL
    public void Construir(String cad) {
        if (cad == null) {
            this.Raiz = null;
            return;
        }
        String cadena = cad.toUpperCase();
        boolean[] presencia = new boolean[26]; // evita duplicados
        for (int i = 0; i < cadena.length(); i++) {
            char ch = cadena.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                int idx = ch - 'A';
                if (!presencia[idx]) {
                    presencia[idx] = true;
                    this.Raiz = insertarAVL(this.Raiz, ch);
                }
            }
        }
    }

    // obtener altura de un nodo
    private int alturaNodo(Nodo n) {
        return (n == null) ? 0 : n.getAltura();
    }

    // actualizar altura de nodo = 1 + max(alturaIzq, alturaDer)
    private void actualizarAltura(Nodo n) {
        if (n != null) {
            int hi = alturaNodo(n.getLi());
            int hd = alturaNodo(n.getLd());
            n.setAltura(Math.max(hi, hd) + 1);
        }
    }

    // factor de balance = alturaIzq - alturaDer
    private int factorBalance(Nodo n) {
        if (n == null) return 0;
        return alturaNodo(n.getLi()) - alturaNodo(n.getLd());
    }

    // rotación derecha
    private Nodo rotacionDerecha(Nodo y) {
        Nodo x = y.getLi();
        Nodo T2 = x.getLd();

        // rotación
        x.setLd(y);
        y.setLi(T2);

        // actualizar alturas
        actualizarAltura(y);
        actualizarAltura(x);

        return x; // nueva raíz
    }

    // rotación izquierda
    private Nodo rotacionIzquierda(Nodo x) {
        Nodo y = x.getLd();
        Nodo T2 = y.getLi();

        y.setLi(x);
        x.setLd(T2);

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    // insertar como AVL recursivo
    private Nodo insertarAVL(Nodo nodo, char dato) {
        if (nodo == null) return new Nodo(dato);
        if (dato < nodo.getDato()) {
            nodo.setLi(insertarAVL(nodo.getLi(), dato));
        } else if (dato > nodo.getDato()) {
            nodo.setLd(insertarAVL(nodo.getLd(), dato));
        } else {
            // duplicado -> no insertar
            return nodo;
        }

        // actualizar altura
        actualizarAltura(nodo);

        int fb = factorBalance(nodo);

        // casos de rotación
        // Left Left
        if (fb > 1 && dato < nodo.getLi().getDato()) {
            return rotacionDerecha(nodo);
        }
        // Right Right
        if (fb < -1 && dato > nodo.getLd().getDato()) {
            return rotacionIzquierda(nodo);
        }
        // Left Right
        if (fb > 1 && dato > nodo.getLi().getDato()) {
            nodo.setLi(rotacionIzquierda(nodo.getLi()));
            return rotacionDerecha(nodo);
        }
        // Right Left
        if (fb < -1 && dato < nodo.getLd().getDato()) {
            nodo.setLd(rotacionDerecha(nodo.getLd()));
            return rotacionIzquierda(nodo);
        }

        return nodo;
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

    //Eliminar un dato en el arbol
    public boolean eliminarDato(String s) {
        if (Raiz == null || s == null || s.isEmpty()) return false;
        char d = Character.toUpperCase(s.charAt(0));
        boolean[] eliminado = new boolean[1];
        this.Raiz = eliminarAVL(this.Raiz, d, eliminado);
        return eliminado[0];
    }
    private Nodo eliminarAVL(Nodo nodo, char dato, boolean[] eliminado) {
        if (nodo == null) return null;

        if (dato < nodo.getDato()) {
            nodo.setLi(eliminarAVL(nodo.getLi(), dato, eliminado));
        } else if (dato > nodo.getDato()) {
            nodo.setLd(eliminarAVL(nodo.getLd(), dato, eliminado));
        } else {
            // encontrado
            eliminado[0] = true;
            // casos con 0 o 1 hijo
            if (nodo.getLi() == null || nodo.getLd() == null) {
                Nodo temp = (nodo.getLi() != null) ? nodo.getLi() : nodo.getLd();
                // sin hijos
                if (temp == null) {
                    nodo = null;
                    return null;
                } else {
                    // un hijo
                    nodo = temp;
                }
            } else {
                // dos hijos: obtener sucesor (mínimo del subárbol derecho)
                Nodo sucesor = nodo.getLd();
                while (sucesor.getLi() != null) sucesor = sucesor.getLi();
                // copiar dato y eliminar sucesor
                nodo.setDato(sucesor.getDato());
                nodo.setLd(eliminarAVL(nodo.getLd(), sucesor.getDato(), eliminado));
                // Note: eliminado[0] permanece true
            }
        }

        // si el nodo pasó a null tras la eliminación
        if (nodo == null) return null;

        // actualizar altura
        actualizarAltura(nodo);

        int fb = factorBalance(nodo);

        // Rebalancear si es necesario
        // izq izq
        if (fb > 1 && factorBalance(nodo.getLi()) >= 0) {
            return rotacionDerecha(nodo);
        }
        // izq der
        if (fb > 1 && factorBalance(nodo.getLi()) < 0) {
            nodo.setLi(rotacionIzquierda(nodo.getLi()));
            return rotacionDerecha(nodo);
        }
        // der der
        if (fb < -1 && factorBalance(nodo.getLd()) <= 0) {
            return rotacionIzquierda(nodo);
        }
        // der izq
        if (fb < -1 && factorBalance(nodo.getLd()) > 0) {
            nodo.setLd(rotacionDerecha(nodo.getLd()));
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    //Mostrar arbol
    //https://www.youtube.com/watch?v=ind_O7bO5O8
    //https://github.com/Nanotech2022/arboles-binarios


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

    //Mostrar Primos Hermanos de un dato ingresado
    public String primos(String s) {
        if (Raiz == null) return "El árbol está vacío.";
        if (s == null || s.length() == 0) return "Dato inválido.";
        char dato = Character.toUpperCase(s.charAt(0));

        // Buscar el nodo y su padre
        Nodo padre = null;
        Nodo p = Raiz;
        while (p != null && p.getDato() != dato) {
            padre = p;
            if (dato > p.getDato()) {
                p = p.getLd();
            } else {
                p = p.getLi();
            }
        }

        if (p == null) {
            return "El dato '" + dato + "' no existe en el árbol.";
        }
        if (padre == null) {
            return "El dato '" + dato + "' es la raíz y no tiene primos hermanos.";
        }

        // Buscar abuelo desde la raíz
        Nodo abuelo = null;
        Nodo aux = Raiz;
        while (aux != null && aux.getDato() != padre.getDato()) {
            abuelo = aux;
            if (padre.getDato() > aux.getDato()) aux = aux.getLd(); else aux = aux.getLi();
        }
        if (aux == null) {
            return "No se encontró el padre en el árbol.";
        }
        if (abuelo == null) {
            // El padre es la raíz
            return "El dato '" + dato + "' no tiene primos hermanos porque su padre es la raíz (no tiene hermanos).";
        }

        // Determinar el tío/a (hermano del padre)
        Nodo tio = null;
        if (abuelo.getLi() != null && abuelo.getLi().getDato() == padre.getDato()) {
            tio = abuelo.getLd();
        } else if (abuelo.getLd() != null && abuelo.getLd().getDato() == padre.getDato()) {
            tio = abuelo.getLi();
        }
        if (tio == null) {
            return "El dato '" + dato + "' no tiene primos hermanos porque su padre no tiene hermanos.";
        }

        // Recopilar hijos del tío (primos)
        StringBuilder primos = new StringBuilder();
        if (tio.getLi() == null && tio.getLd() == null) {
            return "El dato '" + dato + "' no tiene primos hermanos porque el tío/a no tiene hijos.";
        }
        if (tio.getLi() != null) primos.append(tio.getLi().getDato()).append(' ');
        if (tio.getLd() != null) primos.append(tio.getLd().getDato()).append(' ');

        return "Primos hermanos de '" + dato + "': " + primos.toString().trim();
    }

}