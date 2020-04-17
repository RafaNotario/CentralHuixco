/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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


/*Relacion ambulantes-giro YA*/
ALTER TABLE centraldb.ambulantes
ADD CONSTRAINT FK_ambulantes_giro FOREIGN KEY (idGiro)
REFERENCES centraldb.giros (id)
ON DELETE CASCADE
ON UPDATE CASCADE,

/*Relacion ambulantes-tarifas YA*/
ALTER TABLE centraldb.ambulantes
ADD CONSTRAINT FK_ambulantes_tarifa FOREIGN KEY (idTarifa)
REFERENCES centraldb.tarifas (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*Relacion AREAS-SOCIOS PENDIENTE error = AMBULANTES - SEMANAS YA*/
/*SOLUCION = Verificar misma longitud y tipo de datos areas.idPresi Atributos = "" socios.is Atributos = UNSIGNED*/
ALTER TABLE centraldb.areas
ADD CONSTRAINT FK_this_ FOREIGN KEY (idPresi)
REFERENCES centraldb.socios (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

/*Relacion AMBULANTES - SEMANAS PENDIENTE marca error*/
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





/*modificacione en respaldo de SIAN CENTRAL
/*SOLUCION = Verificar misma longitud y tipo de datos areas.idPresi Atributos = "" socios.is Atributos = UNSIGNED*/
/*SOLUCION = Verificar misma longitud y tipo de datos centraldb.id Atributos = "" pagos_areas.idArea Atributos = UNSIGNED*/



/***ANOTACIONES
Tablas que no se usan
 -> bodegas
 -> bodegas_edos

que paga cada area 
0 CAJAS VACIAS -> Mant. semanal, Basura semanal
1 CEBOLLAS -> Mant. semanal, Basura semanal
2 CHILES SECOS -> Mant. semanal, Basura semanal
3 CHILES VERDES -> Mant. semanal, Basura semanal
4 DETALLE 1 -> Mant. semanal, Basura semanal
5 DETALLE 2 -> Mant. semanal, Basura semanal
6 ELOTES -> Mant. semanal, Basura semanal
7 FRUTAS 1 -> Mant. semanal, Basura semanal
8 FRUTAS 2 -> Mant. semanal, Basura semanal
9 FRUTAS 3 -> Mant. semanal, Basura semanal
10 JARCIAS -> Mant. semanal, Basura semanal
11 JITOMATES ->  ''
12 LEGUMBRES -> ''
13 PAPAS -> ''
14 ROPA Y CALZADO ->''
15 TOMATES Y CHAYOTES -> ''
16 TUNAS Y NOPALES -> ''
17 VARIOS -> ''
18 DESAYUNOS -> '',Policia semanal,Resguardo semanal
19 JUGOS -> '',Policia semanal,Resguardo semanal
20 CASETEROS -> '',Policia semanal,Resguardo semanal
21 PANADEROS -> '',Policia semanal,Resguardo semanal

*/