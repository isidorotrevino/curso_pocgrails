package com.roomie.curso.facturacion

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DomicilioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Domicilio.list(params), model:[domicilioCount: Domicilio.count()]
    }

    def show(Domicilio domicilio) {
        respond domicilio
    }

    def create() {
        respond new Domicilio(params)
    }

    @Transactional
    def save(Domicilio domicilio) {
        if (domicilio == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (domicilio.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond domicilio.errors, view:'create'
            return
        }

        domicilio.save flush:true

		Cliente cliente = domicilio.cliente
		cliente.direccion = domicilio
		cliente.save()

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'domicilio.label', default: 'Domicilio'), domicilio.id])
                redirect domicilio
            }
            '*' { respond domicilio, [status: CREATED] }
        }
    }

    def edit(Domicilio domicilio) {
        respond domicilio
    }

    @Transactional
    def update(Domicilio domicilio) {
        if (domicilio == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (domicilio.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond domicilio.errors, view:'edit'
            return
        }

        domicilio.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'domicilio.label', default: 'Domicilio'), domicilio.id])
                redirect domicilio
            }
            '*'{ respond domicilio, [status: OK] }
        }
    }

    @Transactional
    def delete(Domicilio domicilio) {

        if (domicilio == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        domicilio.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'domicilio.label', default: 'Domicilio'), domicilio.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'domicilio.label', default: 'Domicilio'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
