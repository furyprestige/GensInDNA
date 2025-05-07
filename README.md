# Detección de Genes en Cadenas de ADN
Este proyecto en Java proporciona una clase `DNA` que permite almacenar y analizar cadenas de ADN, limpiándolas de caracteres inválidos y detectando genes válidos basados en codones de inicio (por defecto: `ATG`) y codones de fin (`TAA`, `TAG`, `TGA`). Incluye una interfaz `IGenes` para definir las operaciones de búsqueda y validación de genes.

## Objetivo de la actividad
- Aprender a manipular cadenas de texto en Java  
- Implementar y usar interfaces para definir comportamientos (`IGenes`)  
- Detectar y extraer subsecuencias (“genes”) en una cadena de ADN basándose en reglas biológicas simples  
- Practicar el manejo de colecciones (`ArrayList`, `List`) y la lógica de búsqueda de subcadenas

## Requisitos técnicos
- **Java Development Kit (JDK) 8** o superior  
- Herramienta de compilación:  
  - Opción 1: Compilación directa con **`javac`** y ejecución con **`java`**
  - Opción 2: Ejecutar directamente con el apoyo de un IDE.
- **IDE** recomendado: IntelliJ IDEA.

## Uso y personalización
 - Cambiar nucleótidos válidos
    - Modifica la lista ValidNucleotides en DNA.java.

  - Configurar codones de inicio/fin
    - Edita las colecciones openCodons y closeCodons según tu necesidad.

  - Longitud de codón
    - Ajusta la variable lengthCodons si se usan codones de diferente tamaño.
