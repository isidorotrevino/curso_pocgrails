package com.roomie.curso.facturacion

class Cliente {

	String nombre
	String rfc
	Domicilio direccion
	String telefono
	String email

	static hasMany = [facturas:Factura]

    static constraints = {
		nombre(minSize:3)
		rfc(matches:"[A-Z]{3,4}\\d{6}[A-Z0-9]{3}")
		direccion(nullable:true)
		telefono(minSize:8,maxSize:13,matches:"\\d+")
		email(email:true)
    }
}
