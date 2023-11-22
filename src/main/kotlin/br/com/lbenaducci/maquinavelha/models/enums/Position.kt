package br.com.lbenaducci.maquinavelha.models.enums

enum class Position(val row: Int, val column: Int, val alias: String) {
    A1(0, 0, "T0"),
    A2(0, 1, "T1"),
    A3(0, 2, "T2"),
    B1(1, 0, "T3"),
    B2(1, 1, "T4"),
    B3(1, 2, "T5"),
    C1(2, 0, "T6"),
    C2(2, 1, "T7"),
    C3(2, 2, "T8")
}