package com.roomie.curso.facturacion

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FacturaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Factura.list(params), model:[facturaCount: Factura.count()]
    }

    def show(Factura factura) {
        respond factura
    }

    def create() {
        respond new Factura(params)
    }

    @Transactional
    def save(Factura factura) {
        if (factura == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (factura.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond factura.errors, view:'create'
            return
        }

        factura.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'factura.label', default: 'Factura'), factura.id])
                redirect factura
            }
            '*' { respond factura, [status: CREATED] }
        }
    }

    def edit(Factura factura) {
        respond factura
    }

    @Transactional
    def update(Factura factura) {
        if (factura == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (factura.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond factura.errors, view:'edit'
            return
        }

        factura.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'factura.label', default: 'Factura'), factura.id])
                redirect factura
            }
            '*'{ respond factura, [status: OK] }
        }
    }

    @Transactional
    def delete(Factura factura) {

        if (factura == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        factura.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'factura.label', default: 'Factura'), factura.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'factura.label', default: 'Factura'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
