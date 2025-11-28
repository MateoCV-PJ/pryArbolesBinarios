package arbolesBinarios;

public class TestPrimos {
    public static void main(String[] args) {
        Arbol ar = new Arbol();
        // Construir manualmente el árbol:
        //        G
        //       / \
        //      C   K
        //     / \ / \
        //    A  E I  M
        Nodo root = new Nodo('G');
        Nodo c = new Nodo('C');
        Nodo k = new Nodo('K');
        root.setLi(c);
        root.setLd(k);
        c.setLi(new Nodo('A'));
        c.setLd(new Nodo('E'));
        k.setLi(new Nodo('I'));
        k.setLd(new Nodo('M'));
        ar.setRaiz(root);

        System.out.println("Primos de A: '" + ar.primos("A") + "'");
        System.out.println("Primos de E: '" + ar.primos("E") + "'");
        System.out.println("Primos de I: '" + ar.primos("I") + "'");
        System.out.println("Primos de G (raiz): '" + ar.primos("G") + "'");
    }
}

