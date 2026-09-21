# Diseño de Lenguajes de Programación (DLP)

Proyecto desarrollado para la asignatura de Diseño de Lenguajes de Programación de la Universidad de Oviedo (2026). 

El objetivo de este repositorio es la construcción de un **compilador completo** para un lenguaje de programación específico, aplicando principios de diseño de lenguajes y teoría de autómatas.

## Fases del Compilador Implementadas

*   **Análisis Léxico y Sintáctico:** Definición de la gramática y generación del Árbol de Sintaxis Abstracta (AST) utilizando ANTLR4.
*   **Análisis Semántico:** Comprobación de tipos, gestión de ámbitos (scopes) y validación de reglas semánticas.
*   **Generación de Código:** Traducción del código fuente validado a código máquina/intermedio ejecutable.

## Stack Tecnológico

*   **Java** (Lógica central del compilador y recorridos del AST)
*   **ANTLR4** (Generación de analizadores léxicos y sintácticos)
