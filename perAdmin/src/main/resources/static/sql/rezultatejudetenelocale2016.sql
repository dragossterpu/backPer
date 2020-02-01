--CONSILIERI LOCALI


--Partide

INSERT INTO partid_rezultat_local( id, data_alegerilor, procentajtotalvoturi, tip_alegeri, totalvoturiJudet,  id_judet, partid, numarMandate, procentajMandateJudet, an_alegeri , id_localitate)

 --Alba
 INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (99,TRUE, 'TRIFU-MARIAN MIRCEA', 'TMM'); 
 INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (100,TRUE, 'ROŞU VICTOR TUDOR', 'RVT');
 INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (101,TRUE, 'COMŞA EMIL-ANTON', 'CEA');
INSERT INTO optiuni_alegeri( id, nume, voturi_obtinute, code_province, locality_id)VALUES (889, 'ROŞU VICTOR TUDOR', 473, 'AB',	4);
INSERT INTO optiuni_alegeri( id, nume, voturi_obtinute, code_province, locality_id)VALUES (890, 'COMŞA EMIL-ANTON', 247, 'AB',	4);
INSERT INTO partid_rezultat_local( id, data_alegerilor,tip_alegeri,  partid, totalvoturiLocalitate, procentajtotalvoturi, numarMandate,
 procentajMandateLocal,  id_judet , id_localitate ,  an_alegeri)
 

VALUES (1,to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,3,11908, 52.60,12,57.14,'AB',4, 2016);
VALUES (2,to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,4,5074,22.41,5,23.80,'AB',4, 2016);
VALUES (3,to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,1,1341, 5.92,2, 9.52,'AB',4, 2016);
VALUES (4,to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,2,1276, 5.64,1, 4.76,'AB',4, 2016);
VALUES (5,to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,94,1228,5.42,1,4.76,'AB',4, 2016);
VALUES (6,to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,6,565,2.50,0,0,'AB',4,2016);
VALUES (7,to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,13,527,2.33,0,0,'AB',4,2016);
VALUES (8,to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,100,473,2.09,0,0,'AB',4,2016);
VALUES (9,to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,101,247,	1.09,0,0,'AB',4,2016);

--Independent
 INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (102,TRUE, 'DANCIU IONELA MARIANA', 'DIM'); 
 INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (103,TRUE, 'MAN ALEXANDRU', 'MA');
 INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (104,TRUE, 'CRIŞAN MARINEL', 'CM'); 
 INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (105,TRUE, 'SZABO MIHAIL-IOSIF', 'SMI'); 
 INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (106,FALSE, 'UNIUNEA DEMOCRATĂ A TĂTARILOR TURCO-MUSULMANI DIN ROMÂNIA', 'UDTTM'); 
 	

--Optiuni
INSERT INTO optiuni_alegeri( id, nume, voturi_obtinute, code_province, locality_id)VALUES (891, 'MAN ALEXANDRU', 309, 'AB',1);
INSERT INTO optiuni_alegeri( id, nume, voturi_obtinute, code_province, locality_id)VALUES  (892, 'CRIŞAN MARINEL', 285, 'AB',	1);
INSERT INTO optiuni_alegeri( id, nume, voturi_obtinute, code_province, locality_id)VALUES  (893, 'SZABO MIHAIL-IOSIF', 111, 'AB',1);

VALUES (10,to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,3	,3319,33.55,8,42.10 ,'AB'1 ,2016);
VALUES (11,to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,4	,2626,26.54,6,31.57 ,'AB'1 ,2016);
VALUES (12 to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,5,1299,13.13,3,15.78 ,'AB'1 ,2016);
VALUES (13 to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,101, 628, 6.35,1, 5.26 ,'AB'1 ,2016);
VALUES (14 to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,1,573,  5.79,1, 5.26 ,'AB'1 ,2016);
VALUES (15 to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,6,455, 4.60,0, 0.00 ,'AB'1 ,2016);
VALUES (16 to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,102,309,  3.12,0, 0.00 ,'AB'1 ,2016);
VALUES (17 to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,2,289,  2.92,0, 0.00 ,'AB'1 ,2016);
VALUES (18 to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,103,285,  2.88,0, 0.00 ,'AB'1 ,2016);
VALUES (19 to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,104,111,  1.12,0, 0.00 ,'AB'1 ,2016);
   

VALUES (20,to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,3,	5241,	64.38,13,68.42,'AB',2,2016);
VALUES (21,to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU' ,4,	1157,	14.21, 3,15.78,'AB',2,2016);
VALUES (22to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU'  ,1,	893,	10.97, 2,10.52,'AB',2,2016);
VALUES (23to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU'  ,5,	477,	 5.86, 1, 5.26,'AB',2,2016);
VALUES (24to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU'  ,6,	191,	 2.35, 0, 0.00,'AB',2,2016);
VALUES (25to_date('05/06/16', 'DD/MM/YY'),'LOCALE_CONSILIU'  ,2,	182,	 2.24, 0, 0.00,'AB',2,2016);
 
 
 