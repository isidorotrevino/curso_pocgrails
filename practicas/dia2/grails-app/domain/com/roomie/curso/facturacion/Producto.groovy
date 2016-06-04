package com.roomie.curso.facturacion

class Producto {

	String clave
	String descripcion
	BigDecimal precio

	static hasMany = [facturas:Factura]

    static constraints = {
		precio(min:0.0g)
    }
}
