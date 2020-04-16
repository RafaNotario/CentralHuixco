/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Antonio R. Notario Rodriguez
 * Created: 15/04/2020
 */
/*Relacion ambulantes-giro*/
ALTER TABLE centraldb.ambulantes
   ADD CONSTRAINT FK_ambulantes_giro FOREIGN KEY (idGiro)
      REFERENCES centraldb.giros (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE;

SELECT ambulantes.id,ambulantes.nombre,giro.giro,ambulantes.obs 
FROM central.ambulantes
INNER JOIN central.giro
ON ambulantes.idGiro = giro.giro AND ambulantes.id = '45';
            
ALTER TABLE Sales.TempSalesReason
   ADD CONSTRAINT FK_TempSales_SalesReason FOREIGN KEY (TempID)
      REFERENCES Sales.SalesReason (SalesReasonID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
;