import com.roomie.curso.facturacion.*
class BootStrap {

    def init = { servletContext ->
		new Producto(clave:"A",
		descripcion:"Manzana",precio:20g).save()
		(1..100).each{
			new Producto(clave:"A${it}",	descripcion:"Producto${it}",precio:it*20.2g).save()
		}
    }
    def destroy = {
    }
}
