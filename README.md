# Gestor de Árboles Binarios de Búsqueda

Este proyecto es una aplicación en Java que permite la creación, manipulación y visualización gráfica de **Árboles Binarios de Búsqueda (ABB)**. La aplicación toma una cadena de caracteres ingresada por el usuario, construye un árbol balanceado inicial y ofrece un menú interactivo para realizar diversas operaciones y recorridos.

## 📋 Características

La aplicación cuenta con una interfaz basada en `JOptionPane` para la interacción con el usuario y `JFrame` para la visualización gráfica. Las funcionalidades principales incluyen:

1.  **Construcción del Árbol**:

      * Permite ingresar una cadena de texto (ej. "A,B,C" o "ABC").
      * El sistema filtra duplicados, ordena los caracteres y construye un árbol binario balanceado automáticamente al inicio.

2.  **Recorridos**:

      * **Posorden**: Izquierda, Derecha, Raíz.
      * **Inorden**: Izquierda, Raíz, Derecha.
      * **Preorden**: Raíz, Izquierda, Derecha.

3.  **Consultas de Nodos**:

      * **Hojas**: Muestra los nodos que no tienen hijos.
      * **Ramas**: Muestra los nodos que tienen al menos un hijo (nodos padres).
      * **Nivel**: Indica el nivel (profundidad) en el que se encuentra un carácter específico.
      * **Altura**: Calcula la altura de un nodo específico (el camino más largo hacia una hoja).
      * **Ancestros**: Lista todos los nodos ancestros de un carácter dado.
      * **Hermanos**: Muestra los nodos hermanos de un dato ingresado.

4.  **Edición del Árbol**:

      * **Insertar**: Agrega nuevos caracteres manteniendo la propiedad de árbol binario de búsqueda (menores a la izquierda, mayores a la derecha).
      * **Eliminar**: Elimina un nodo manejando los tres casos de eliminación (hoja, un hijo, dos hijos).

5.  **Visualización Gráfica**:

      * Dibuja el árbol actual en una ventana independiente utilizando la librería `Graphics` de Java Swing, mostrando las conexiones y los nodos jerárquicamente.

## 🛠️ Tecnologías Utilizadas

  * **Lenguaje**: Java
  * **Interfaz de Usuario**: Java Swing (`JOptionPane`, `JFrame`)
  * **Gráficos**: `java.awt.Graphics`

## 📂 Estructura del Proyecto

El código fuente se encuentra en el paquete `arbolesBinarios`:

  * `ArbolesBinarios.java`: Clase principal (`main`). Contiene la lógica del menú principal y secundario.
  * `Arbol.java`: Clase lógica que define las operaciones del árbol (inserción, eliminación, recorridos, cálculos de altura/nivel).
  * `Nodo.java`: Define la estructura de cada nodo (Dato, Hijo Izquierdo, Hijo Derecho).
  * `ArbolBinarioGrafico.java`: Extiende de `JFrame` y se encarga de pintar recursivamente el árbol en pantalla.

## 🚀 Cómo Ejecutar

1.  Asegúrate de tener instalado el **JDK (Java Development Kit)**.
2.  Compila los archivos en el directorio `src`:
    ```bash
    javac arbolesBinarios/*.java
    ```
3.  Ejecuta la clase principal:
    ```bash
    java arbolesBinarios.ArbolesBinarios
    ```

## 📖 Uso

Al iniciar el programa:

1.  Se te pedirá ingresar una cadena (ej. `programacion`).
2.  El sistema generará el árbol inicial.
3.  Selecciona una opción del menú numérico (1-10) para interactuar con el árbol.
4.  Para ver el árbol gráficamente, selecciona la opción **9**.

-----

*Este proyecto fue generado con fines educativos para la comprensión de estructuras de datos no lineales.*
