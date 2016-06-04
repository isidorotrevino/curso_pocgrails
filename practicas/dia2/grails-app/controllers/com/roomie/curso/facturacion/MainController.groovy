package com.roomie.curso.facturacion

class MainController {

    def index() {
		println "REQUEST ${request.method}"
		println "PARAMS ${params}"
		println "SESSION ${session}"
		println "FLASH ${flash}"
 
		Map resultados = [:]
		resultados['lista'] =[]
		(1..20).each{
			resultados['lista']<<
				[id:it,resultado:"Resultado-${it}"]
		}	

		return resultados
	}
}
