class Concepto{

    int cantidad
    String descripcion
    double precioUnitario
    
    String toString(){
        return "\t${cantidad}\t${descripcion}\t${precioUnitario}\t${total}\n"
    }
    
    double getTotal(){
        return ( precioUnitario * cantidad )
    }    
}


class Factura{
    String rfcEmisor
    String direccionEmisor
    String numeroFolio
    Date fechaExpedicion
    String domicilioCliente
    String nombreCliente
    String rfcCliente
    BigDecimal subtotal
    BigDecimal iva
    BigDecimal total
    
    List<Concepto> conceptoLst = []
 
     def closure = { 
        (cantidad == null || descripcion == null || precioUnitario == null ) ? false : true
    }
        
    void plus(Concepto concepto){
        closure.delegate = concepto
        assert closure()
        conceptoLst << concepto
    }
    
    void minus(int idx){
        conceptoLst.remove(idx)
    }
    
    int cantidadConceptos(){
        return serviciosLst.size()
    }
    
    void calculaTotal(){
        subtotal=0.0
        conceptoLst.each{
            subtotal+=it.total
        }        
        iva = subtotal * 0.16
        total = subtotal + iva
    }
     
    String imprimir(){
        String encabezados= "Cantidad\tDescripción\tPrecio Unitario\tTotal\n"
        String conceptos = ""
        conceptoLst.each{
            conceptos+="${it}"
        }
        
        return """
        Folio: ${numeroFolio}
        Fecha: ${fechaExpedicion.format("dd/MM/yyy")}
        Dirección: ${direccionEmisor}
        RFC: ${rfcEmisor}
        --------------------------------------------------
        Nombre Cliente ${nombreCliente}
        Domicilio Cliente: ${domicilioCliente}
        RFC Cliente: ${rfcCliente}
        --------------------------------------------------
        ${encabezados}
        ${conceptos}
        --------------------------------------------------
        subtotal: ${subtotal}
        iva:      ${iva}
        total:    ${total}
        
        """

    }

}

class AdministracionFacturas{
    List<Factura> facturasLst = []
    
    void plus(Factura factura){
        facturasLst << factura
    }
    
    void minus(int idx){
        facturasLst.remove(idx)
    }    
    
    void imprimirFacturas(){
        facturasLst.each{
            print it.imprimir()
        }
    }
    
}

Concepto concepto1 = new Concepto(cantidad:1, descripcion:"Instalacion de regadera", precioUnitario:1800.00f)
Concepto concepto2 = new Concepto(cantidad:1, descripcion:"Instalacion de inodoro", precioUnitario:2500.00f)
Concepto concepto3 = new Concepto(cantidad:1, descripcion:"Instalacion de lavabo", precioUnitario:1500.00f)
Concepto concepto4 = new Concepto(cantidad:1, descripcion:"Instalacion de tuberia de agua", precioUnitario:5000.00f)
Concepto concepto5 = new Concepto(cantidad:2, descripcion:"Instalacion de regadera", precioUnitario:1800.00f)
Concepto concepto6 = new Concepto(cantidad:3, descripcion:"Instalacion de inodoro", precioUnitario:2500.00f)
Concepto concepto7 = new Concepto(cantidad:2, descripcion:"Instalacion de lavabo", precioUnitario:1500.00f)
Concepto concepto8 = new Concepto(cantidad:3, descripcion:"Instalacion de tuberia de agua", precioUnitario:5000.00f)

int folio = 0
AdministracionFacturas administracionFacturas = new AdministracionFacturas()

Factura factura1 = new Factura(rfcEmisor:"EMIS999999", direccionEmisor:"Domicilio Emisor", numeroFolio:"00000${++folio}", 
fechaExpedicion:new Date(), domicilioCliente:"Domicilio Cliente", rfcCliente : "CLIE999999", nombreCliente:"Nombre Cliente")
factura1 + concepto1
factura1 + concepto2
factura1 + concepto3
factura1 + concepto4
factura1.calculaTotal()
administracionFacturas + factura1

Factura factura2 = new Factura(rfcEmisor:"EMIS999999", direccionEmisor:"Domicilio Emisor", numeroFolio:"00000${++folio}", 
fechaExpedicion:new Date(), domicilioCliente:"Domicilio Cliente", rfcCliente : "CLIE999999", nombreCliente:"Nombre Cliente")
factura2 + concepto4
factura2 + concepto5
factura2 + concepto6
factura2 + concepto7
factura2.calculaTotal()
administracionFacturas + factura2


Factura factura3 = new Factura(rfcEmisor:"EMIS999999", direccionEmisor:"Domicilio Emisor", numeroFolio:"00000${++folio}", 
fechaExpedicion:new Date(), domicilioCliente:"Domicilio Cliente", rfcCliente : "CLIE999999", nombreCliente:"Nombre Cliente")
factura3 + concepto1
factura3 + concepto6
factura3.calculaTotal()
administracionFacturas + factura3


Factura factura4 = new Factura(rfcEmisor:"EMIS999999", direccionEmisor:"Domicilio Emisor", numeroFolio:"00000${++folio}", 
fechaExpedicion:new Date(), domicilioCliente:"Domicilio Cliente", rfcCliente : "CLIE999999", nombreCliente:"Nombre Cliente")
factura4 + concepto2
factura4 + concepto7
factura4.calculaTotal()
administracionFacturas + factura4

Factura factura5 = new Factura(rfcEmisor:"EMIS999999", direccionEmisor:"Domicilio Emisor", numeroFolio:"00000${++folio}", 
fechaExpedicion:new Date(), domicilioCliente:"Domicilio Cliente", rfcCliente : "CLIE999999", nombreCliente:"Nombre Cliente")
factura5 + concepto3
factura5 + concepto8
factura5.calculaTotal()
administracionFacturas + factura5

administracionFacturas.imprimirFacturas()
