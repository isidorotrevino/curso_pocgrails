package com.roomie.curso.facturacion

class Domicilio {

	Cliente cliente
	String calle
	String numExterior
	String colonia

	static belongsTo = Cliente

    static constraints = {
		numExterior(nullable:true)
		colonia(nullable:true)
    }

	String toString(){
		return "CALLE ${calle} ${numExterior} Col. ${colonia}"
	}

}
