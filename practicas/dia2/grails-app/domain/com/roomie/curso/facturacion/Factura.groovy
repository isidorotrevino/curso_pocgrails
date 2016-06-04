package com.roomie.curso.facturacion

import java.time.LocalDate

class Factura {

	Cliente cliente
	Long folio
	LocalDate fecha
	BigDecimal total

	static belongsTo = [Cliente,Producto]

	static hasMany = [productos:Producto]

    static constraints = {
    }
}
