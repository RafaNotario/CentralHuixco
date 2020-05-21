/**
 * Author:  Antonio R. Notario Rodriguez
 * Created: 15/04/2020
 */

/*Deshabilitar revision de FK constrain*/        
SET FOREIGN_KEY_CHECKS=0;
/* entrar a mysql en puerto distinto a 3306*/
mysql -u root -q -P 3310
/* entrar a mysql en puerto distinto a 3306 e IP remota*/
mysql -u usuario -p -h 192.168.x.x -P 3307   //-p <- Verificar

/*Relacion AREAS-SOCIOS PENDIENTE error = AMBULANTES - SEMANAS YA*/
/*SOLUCION = Verificar misma longitud y tipo de datos areas.idPresi Atributos = "" socios.is Atributos = UNSIGNED*/
ALTER TABLE centraldb.areas
ADD CONSTRAINT FK_this_ FOREIGN KEY (idPresi)
REFERENCES centraldb.socios (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*Relacion AMBULANTES - SEMANAS PENDIENTE marca error YA*/
ALTER TABLE `centraldb`.`ambulantes`
ADD CONSTRAINT FK_amb_semanas FOREIGN KEY (ultimaSem)
REFERENCES centraldb.semanas (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*Relacion AREAS-PAGOS YA*/
ALTER TABLE central.pagos_areas
ADD CONSTRAINT FK_pag_areas FOREIGN KEY (idArea)
REFERENCES central.areas (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*Relacion PAGOS_AREASDET - RUBROSPAGO ya*/
ALTER TABLE central.pagos_areasdet
ADD CONSTRAINT FK_pag_areas_rubrosPag FOREIGN KEY (idRubroPago)
REFERENCES central.rubrospago (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*Relacion PAGOS_AREASDET - IDSEMANA YA*/
ALTER TABLE central.pagos_areasdet
ADD CONSTRAINT FK_pag_areasdet_Semana FOREIGN KEY (idSemana)
REFERENCES central.semanas (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*Relacion PAGOS_AREASDET - TICKER PK compuesta va a serFK de PK YA */
/*oBS: CAMBIAR TIPO DE DATO en central.pagos_areasdet.idTicket a mediumint(5) ya que central.pagos_areas.id es smallint */
ALTER TABLE central.pagos_areasdet
ADD CONSTRAINT FK_pag_areasdet_Ticket FOREIGN KEY (idTicket)
REFERENCES central.pagos_areas (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*Relacion PAGOSAREAS - TURNO*/
/*oBS: CAMBIAR TIPO DE DATO en central.pagos_areas.idTurno a mediumint(5) ya que central.pagos_turno.id es mediumint(5)C6 */
ALTER TABLE central.pagos_areas
ADD CONSTRAINT FK_pag_areas_Turno FOREIGN KEY (idTurno)
REFERENCES central.turnos (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*Relacion TURNOS - USUARIOS YA*/
ALTER TABLE central.turnos
ADD CONSTRAINT FK_turnos-usuario FOREIGN KEY (idusuario)
REFERENCES central.usuarios (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/***---------*********************************** AMBULANTES*/
/*  Relacion ambulantes-giro YA*/
ALTER TABLE centraldb.ambulantes
ADD CONSTRAINT FK_ambulantes_giro FOREIGN KEY (idGiro)
REFERENCES centraldb.giros (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*  Relacion ambulantes-tarifas     YA*/
ALTER TABLE centraldb.ambulantes
ADD CONSTRAINT FK_ambulantes_tarifa FOREIGN KEY (idTarifa)
REFERENCES centraldb.tarifas (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*  Relacion AMBULANTES-PAGOS_AMB YA    */
ALTER TABLE central.pagos_amb
ADD CONSTRAINT FK_pag_ambs FOREIGN KEY (idAmb)
REFERENCES central.ambulantes (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*  Relacion PAGOS_AMB-PAGOS-AMBDET YA    */
ALTER TABLE central.pagos_ambdet
ADD CONSTRAINT FK_pagambs_pagsamb FOREIGN KEY (idTicket)
REFERENCES central.pagos_amb (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*  Relacion PAGOS_AMB-PAGOS-TURNOS YA    */
/*oBS: CAMBIAR TIPO DE DATO en central.pagos_amb.idTurno a mediumint(5) ya que central.pagos_turno.id es mediumint(5)C6 */
ALTER TABLE central.pagos_amb
ADD CONSTRAINT FK_pagamb_turno FOREIGN KEY (idTurno)
REFERENCES central.turnos (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*  Relacion PAGOS_AMBDET-SEMANAS  YA    */
ALTER TABLE central.pagos_ambdet
ADD CONSTRAINT FK_pagambs_sems FOREIGN KEY (idSemana)
REFERENCES central.semanas (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*  Relacion PAGOS_AMBDET- RUBROSPAGO YA    */
ALTER TABLE central.pagos_ambdet
ADD CONSTRAINT FK_pagambs_rubros FOREIGN KEY (idRubropago)
REFERENCES central.rubrospago (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

                                                                        /*----      RELACIONES PARA CARGADORES  */
/*RELACION CARGADORES-TARIFAS  YA*/
ALTER TABLE central.cargadores
ADD CONSTRAINT FK_cargador_tarifa FOREIGN KEY (idTarifa)
REFERENCES central.tarifas (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*RELACION PAGOS_CARG-TURNOS YA */
/*oBS: CAMBIAR TIPO DE DATO en central.pagos_carg.idTurno a mediumint(5) ya que central.turnos.id es mediumint(5)*/
ALTER TABLE central.pagos_carg
ADD CONSTRAINT FK_pagoscarg_turnos FOREIGN KEY (idTurno)
REFERENCES central.turnos (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*RELACION PAGOS_CARG-CARGADORES  ya*/
ALTER TABLE central.pagos_carg
ADD CONSTRAINT FK_pagoscarg_cargadores FOREIGN KEY (idcarg)
REFERENCES central.cargadores(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*RELACION PAGOS_CARGDET- PAGOS_CARG yes*/
ALTER TABLE central.pagos_cargdet
ADD CONSTRAINT FK_pagcargdet_tick FOREIGN KEY (idTicket)
REFERENCES central.pagos_carg(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*RELACION PAGOS_CARGDET- RUBROSPAGO yes*/
ALTER TABLE central.pagos_cargdet
ADD CONSTRAINT FK_pagcargdet_rubros FOREIGN KEY (idRubropago)
REFERENCES central.rubrospago(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*RELACION PAGOS_CARGDET- SEMANAS yes*/
/*oBS: CAMBIAR TIPO DE DATO en central.pagos_cargdet.idSemana a samllint(3) ya que central.semanas.id es smallint(6) tenia tinyint(3)*/
ALTER TABLE central.pagos_cargdet
ADD CONSTRAINT FK_pagcargdet_semanas FOREIGN KEY (idSemana)
REFERENCES central.semanas(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*RELACION PAGOS_CARGRENTA-TURNOS yes*/
/*oBS: CAMBIAR TIPO DE DATO en central.pagos_cargrenta.idTurno a mediumint(5) ya que central.turnos.id es MEDIUMint(5) tenia tinyint(3)*/
ALTER TABLE central.pagos_cargrenta
ADD CONSTRAINT FK_pagcargrenta_turno FOREIGN KEY (idTurno)
REFERENCES central.turnos(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*RELACION PAGOS_CARGRENTA-CARGADORES YA*/
/*oBS: CAMBIAR TIPO DE DATO en central.pagos_cargrenta.idCarg a smallint(5) & Unsigned ya que central.cargadores.id es smallint(5),Unsigned tenia tinyint(3)*/
ALTER TABLE central.pagos_cargrenta
ADD CONSTRAINT FK_pagcargrenTc FOREIGN KEY (idCarg)
REFERENCES central.cargadores(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*RELACION PAGOS_CARGRENTA-RUBROSPAGO ya*/
ALTER TABLE central.pagos_cargrenta
ADD CONSTRAINT FK_pagcargren_rubroP FOREIGN KEY (idRubropago)
REFERENCES central.rubrospago(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*----------++++++++++********* RELACIONES PARA PAGO DE INFRACCIONES */
/*  RELACION NOMINA-NOMINA_DEPTO    YA*/
ALTER TABLE central.nomina
ADD CONSTRAINT FK_nomin_dpto FOREIGN KEY (idDepto)
REFERENCES central.nomina_depto(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*  RELACION PAGOS_INFRAC - NOMINA.     YA*/
ALTER TABLE central.pagos_infrac
ADD CONSTRAINT FK_payInf_nomin FOREIGN KEY (idagente)
REFERENCES central.nomina(id)
ON DELETE CASCADE
ON UPDATE CASCADE;
/*@end----------++++++++++********* RELACIONES PARA PAGO DE INFRACCIONES */

/* --- +++ ----    RELACIONES PARA OTROS COBROS ----------------------- */
/*  RELACION OTROS_CATALOGO - OTROS_RUBROS.     YA*/
ALTER TABLE central.otros_catalogo
ADD CONSTRAINT FK_otsCat_otsRub FOREIGN KEY (idrubro)
REFERENCES central.otros_rubros(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*  RELACION OTROS_VENTA - TURNOS.  */
/*oBS: CAMBIAR TIPO DE DATO en central.otros_venta.idTurno a mediumint(5) ya que central.turnos.id es mediumint(5)*/
ALTER TABLE central.otros_venta
ADD CONSTRAINT FK_otsVent_turnos FOREIGN KEY (idTurno)
REFERENCES central.turnos(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*  RELACION OTROS_VENTADET - OTROS_CATALOGO      YA  */
ALTER TABLE central.otros_ventadet
ADD CONSTRAINT FK_otsVentDet_otsCat FOREIGN KEY (idProd)
REFERENCES central.otros_catalogo(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*  RELACION OTROS_VENTADET - OTROS_CATALOGO      YA  */
ALTER TABLE central.otros_ventadet
ADD CONSTRAINT FK_otsVentDet_otsVent FOREIGN KEY (idVenta)
REFERENCES central.otros_venta(id)
ON DELETE CASCADE
ON UPDATE CASCADE;




/**QWERY CARGAR USUARIOS QUE PUEDEN HACER INFRACCION nomina_depto.nombre = Trnasito*/
SELECT nomina.id,nomina.nombre
FROM nomina
INNER JOIN nomina_depto
ON nomina.idDepto = nomina_depto.id AND nomina_depto.nombre = 'Transito';

/*   CARGAR DATOS DE INFRACCION PARA ACTUALIZAR*/
SELECT pagos_infrac.folio,pagos_infrac.fecha,pagos_infrac.tipoDoc,pagos_infrac.documento,pagos_infrac.motivo,pagos_infrac.vehiculo,
                pagos_infrac.obs,nomina.nombre,pagos_infrac.monto
FROM pagos_infrac
INNER JOIN nomina
ON pagos_infrac.idagente = nomina.id AND pagos_infrac.folio = 2155;


/**qwerys para bisquedas sistemon**/
SELECT ambulantes.id,ambulantes.nombre,giro.giro,ambulantes.obs 
FROM central.ambulantes
INNER JOIN central.giro
ON ambulantes.idGiro = giro.giro AND ambulantes.id = '45';

/*MOSTRA DATOS DE AREAS CON NOMBRE DE SOCIOS*/
SELECT areas.id,areas.nombre, socios.nombre,areas.idSecre,areas.idTesorero
FROM central.areas
INNER JOIN central.socios
ON areas.idPresi = socios.id;

/*DETERMINAR QUE PAGOS PUEDEN HACER LAS AREAS*/
SELECT pagos_areas.id,rubrospago.descripcion, areas.nombre
FROM pagos_areas
INNER JOIN areas
ON pagos_areas.idArea = areas.id AND areas.id = 1
INNER JOIN pagos_areasdet
ON pagos_areas.id=pagos_areasdet.idTicket
INNER JOIN rubrospago
ON rubrospago.id = pagos_areasdet.idRubroPago;

/*OBTENER ULTIMA SEMANA PAGADA DE AREA*/
SELECT  pagos_areas.id,pagos_areasdet.idSemana,rubrospago.descripcion, areas.nombre,pagos_areas.fecha
FROM areas
INNER JOIN pagos_areas
ON areas.id = pagos_areas.idArea AND pagos_areas.idarea = 10
INNER JOIN pagos_areasdet
ON pagos_areasdet.idTicket=pagos_areas.id
INNER JOIN rubrospago
ON rubrospago.id = pagos_areasdet.idRubroPago 
group by pagos_areasdet.idRubroPago
ORDER BY pagos_areas.fecha desc;

SELECT  pagos_amb.*
FROM pagos_amb
INNER JOIN ambulantes
ON ambulantes.id = pagos_amb.idAmb AND pagos_amb.idAmb = 258
INNER JOIN pagos_ambdet
ON pagos_amb.id = pagos_ambdet.idTicket AND pagos_ambdet.idRubroPago = 6
ORDER BY pagos_amb.id desc limit 10;

/*query para obrtener tarifas y descuentos de ambulante*/
SELECT ambulantes.condMemb,ambulantes.condDerecho,ambulantes.condResg,ambulantes.vigMembresia,
    tarifas.derechoSemanal, tarifas.membAnual,tarifas.membSemestral,tarifas.membTrimestral
FROM ambulantes
INNER JOIN tarifas
ON ambulantes.idTarifa = tarifas.id AND ambulantes.id = 228;

SELECT * from pagos_amb where idAmb = 258 order by id DESC LIMIT 10;

/*modificaciones en respaldo de SIAN CENTRAL
/*SOLUCION = Verificar misma longitud y tipo de datos areas.idPresi Atributos = "" socios.is Atributos = UNSIGNED*/
/*SOLUCION = Verificar misma longitud y tipo de datos centraldb.id Atributos = "" pagos_areas.idArea Atributos = UNSIGNED*/
/*Obtener datos de la tabla de vistas pagos_AREAS del dias*/
SELECT  pagos_areas.id,DATE_FORMAT(pagos_areas.hora, "%H : %i") AS hor,'Pago semanal de area',areas.nombre,pagos_areas.total
FROM pagos_areas
INNER JOIN areas
ON areas.id = pagos_areas.idArea AND pagos_areas.fecha = '2020/04/26'
ORDER BY pagos_areas.id DESC;

/*Obtener datos de la tabla de vistas pagos_AMBULANTES del dias*/
SELECT  pagos_amb.id,DATE_FORMAT(pagos_amb.hora, "%H : %i") AS hor,'Pago semanal Ambulante',ambulantes.nombre,pagos_amb.total
FROM pagos_ambulantes
INNER JOIN ambulantes
ON ambulantes.id = pagos_amb.idAmb AND pagos_amb.fecha = '2020/05/05'
ORDER BY pagos_areas.id DESC;


--                          *************        @startqwerysReportesMatrizParametro

/*QWERY PARA MOSTRAR TICKET JASPER PAGOS AREAS MANTENIMIENTO*/
SELECT pagos_areas.total,pagos_areas.efectivo, pagos_areas.total - pagos_areas.efectivo AS resta, usuarios.nombre, areas.nombre
FROM pagos_areas
INNER join areas
ON pagos_areas.idArea = areas.id AND pagos_areas.id = 1134
JOIN turnos
on pagos_areas.idTurno = turnos.id
join usuarios
ON usuarios.id = turnos.idusuario;

/*QWERY PARA MOSTRAR TICKET JASPER PAGOS AMBULANTES MANTENIMIENTO*/
SELECT pagos_amb.total,pagos_amb.efectivo, pagos_amb.total - pagos_amb.efectivo AS resta, usuarios.nombre, ambulantes.nombre
FROM pagos_amb
INNER join ambulantes
ON pagos_amb.idAmb = ambulantes.id AND pagos_amb.id = 17120
JOIN turnos
on pagos_amb.idTurno = turnos.id
join usuarios
ON usuarios.id = turnos.idusuario;

/*QWERY PARA MOSTRAR TICKET JASPER PAGOS CARGADORES MANTENIMIENTO*/
SELECT pagos_carg.total,pagos_carg.efectivo, pagos_carg.total - pagos_carg.efectivo AS resta, usuarios.nombre, cargadores.nombre
FROM pagos_carg
INNER JOIN cargadores
ON pagos_carg.idcarg = cargadores.id AND pagos_carg.id = 17120
JOIN turnos
on pagos_carg.idTurno = turnos.id
join usuarios
ON usuarios.id = turnos.idusuario;

/*PARA TICKET DE INFRACCION SOLO SE ENVIA FOLIO*/

/*QWERY PARA MOSTRAR TICKET JASPER PAGOS OTROS-RUBROS*/
SELECT otros_venta.efectivo,date_format(otros_venta.fecha,'%d - %m - %Y') AS fech, DATE_FORMAT(otros_venta.hora, "%H : %i") AS hor, usuarios.nombre,
IF(otros_venta.tipoPersona = 0, (SELECT ambulantes.nombre FROM ambulantes WHERE ambulantes.id = otros_venta.idPersona ) ,IF(otros_venta.tipoPersona = 1,(SELECT cargadores.nombre FROM cargadores WHERE cargadores.id = otros_venta.idPersona ), IF(otros_venta.tipoPersona = 2,(SELECT clientes.nombre from clientes WHERE clientes.id = otros_venta.idPersona),'NADON') ) ) AS namquees
FROM otros_venta
JOIN turnos
on otros_venta.idTurno = turnos.id AND otros_venta.id = 593
join usuarios
ON usuarios.id = turnos.idusuario;



--                          *************        @end qwerysReportesMatrizParametro

/*qwery para obtener pagos Areas y mostrar ticket*/
SELECT  rubrospago.descripcion,semanas.semana,
date_format(semanas.finicial, '%d - %b ') AS fini, date_format(semanas.ffinal, '%d - %b ') AS ffin, if(semanas.semana = 53,date_format(semanas.finicial, '%Y '), date_format(semanas.ffinal, '%Y ') ) AS anio
,pagos_areasdet.importe
FROM pagos_areasdet
INNER JOIN rubrospago
ON pagos_areasdet.idRubroPago = rubrospago.id AND pagos_areasdet.idTicket = $P{numTicket}
INNER JOIN semanas
ON pagos_areasdet.idSemana = semanas.id;

/*qwery para obtener pagos ambulantes y mostrar ticket*/
SELECT  rubrospago.descripcion,semanas.semana,
date_format(semanas.finicial, '%d - %b ') AS fini, date_format(semanas.ffinal, '%d - %b ') AS ffin, if(semanas.semana = 53,date_format(semanas.finicial, '%Y '), date_format(semanas.ffinal, '%Y ') ) AS anio
,pagos_ambdet.importe - pagos_ambdet.descuento AS resty,IF(rubrospago.id > 7, (fini = pagos_ambdet.finicio, ffin = pagos_ambdet.fvenc), '' )
FROM pagos_ambdet
INNER JOIN rubrospago
ON pagos_ambdet.idRubropago = rubrospago.id AND pagos_ambdet.idTicket = 17120
INNER JOIN semanas
ON pagos_ambdet.idSemana = semanas.id;


/* query para cargar datos de  Ambulantes Vista general */
SELECT ambulantes.id,ambulantes.nombre,ambulantes.direccion,ambulantes.telefono,giros.giro,tarifas.descripcion,
                (SELECT CONCAT(semanas.anio, " - ", semanas.semana) FROM semanas where ambulantes.ultimaSem = semanas.id) AS vigenc,
             ((SELECT semanas.id FROM semanas WHERE  CURDATE() BETWEEN finicial AND ffinal) - ambulantes.ultimaSem) AS  adeudos,
                ambulantes.vigMembresia
FROM giros
INNER JOIN ambulantes
ON ambulantes.idGiro = giros.id
INNER JOIN tarifas
ON tarifas.id = ambulantes.idTarifa
ORDER BY ambulantes.id DESC LIMIT 100;

/*qwery obtener datos de cargador importantes*/
SELECT cargadores.id,cargadores.nombre,cargadores.diablo,cargadores.condMemb,cargadores.condDerecho,
            cargadores.condRenta,cargadores.vigMembresia,cargadores.ultimaSem,cargadores.idTarifa,
            tarifas.derechoSemanal,tarifas.membAnual,tarifas.membSemestral,tarifas.membTrimestral 
FROM central.cargadores
INNER JOIN central.tarifas
ON cargadores.idTarifas = tarifas.id AND cargadores.id = '45';

/*QWERY DE JASPER REPORT*/
SELECT  pagos_infrac.documento,DATE_FORMAT(pagos_infrac.fecha, "%d - %M - %Y "),pagos_infrac.documento,pagos_infrac.motivo,
                pagos_infrac.vehiculo,pagos_infrac.quienpaga,DATE_FORMAT(pagos_infrac.fechapag, "%d - %M - %Y "),
                DATE_FORMAT(pagos_infrac.horapag, "%H : %i") AS hor,pagos_infrac.monto,pagos_infrac.descuento,pagos_infrac.monto - pagos_infrac.descuento,
                pagos_infrac.efectivo,pagos_infrac.efectivo - (,pagos_infrac.monto - pagos_infrac.descuento)
FROM pagos_infrac
WHERE pagos_infrac.folio =2021;

/*QWERY PARA CARGAR TICKETS DIA OTROS*/
SELECT  otros_venta.id,DATE_FORMAT(otros_venta.hora, "%H : %i") AS hor,
    IF(otros_venta.tipoPersona = 0,'Varios Amb.',IF(otros_venta.tipoPersona = 1,'Varios Carg.', IF(otros_venta.tipoPersona = 2,'Varios Cte.','NADON') ) ),
        otros_venta.efectivo
FROM pagos_carg
INNER JOIN cargadores
ON cargadores.id = pagos_carg.idcarg AND pagos_carg.fecha = curdate()
ORDER BY pagos_carg.id DESC

SELECT otros_venta.id,DATE_FORMAT(otros_venta.hora, "%H : %i") AS hor,
IF(otros_venta.tipoPersona = 0,'Varios Amb.',IF(otros_venta.tipoPersona = 1,'Varios Carg.', IF(otros_venta.tipoPersona = 2,'Varios Cte.','NADON') ) ) AS quees,
IF(otros_venta.tipoPersona = 0, (SELECT ambulantes.nombre WHERE ambulantes.id = otros_venta.idPersona ) ,IF(otros_venta.tipoPersona = 1,(SELECT cargadores.nombre WHERE cargadores.id = otros_venta.idPersona ), IF(otros_venta.tipoPersona = 2,(SELECT clientes.nombre WHERE clientes.id = otros_venta.idPersona),'NADON') ) ) AS namquees,
        otros_venta.efectivo
FROM otros_venta
WHERE otros_venta.fecha = '2020-05-19';

/*QUERY PARA TICKET OTROS RUBROS*/
SELECT  otros_catalogo.descrip,otros_ventadet.cant,otros_ventadet.precio
FROM otros_ventadet
INNER JOIN otros_catalogo
ON otros_ventadet.idProd = otros_catalogo.id AND otros_ventadet.idVenta = 593;