--CONSILIERI JUDETENI

CREATE TABLE partid_rezultat_judet
(
  id bigint NOT NULL,
  data_alegerilor timestamp without time zone,
  procentaj_total_voturi real,
  tip_alegeri character varying(255),
  total_voturi_judet bigint,
  id_judet character varying(255),
  partid bigint,
  numar_mandate bigint,
  procentaj_mandate_judet real,
  an_alegeri integer,
  CONSTRAINT partid_rezultat_judet_pkey PRIMARY KEY (id),
  CONSTRAINT fk_u_partid FOREIGN KEY (partid)
      REFERENCES partid (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_u_province FOREIGN KEY (id_judet)
      REFERENCES pprovince (code_province) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE partid_rezultat_judet
  OWNER TO postgres;


CREATE TABLE partid
(
  id bigint NOT NULL,
  independent character varying(255),
  nume character varying(100),
  sigla character varying(7),
  CONSTRAINT partid_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE partid
  OWNER TO postgres;  
  
  
--Partide


INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (1,to_date('05/06/16', 'DD/MM/YY') ,6.63,'JUDET_CONSILIU',158415, 'AB', 1 ,10512,3, 9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (2,to_date('05/06/16', 'DD/MM/YY') ,6.41,'JUDET_CONSILIU',158415, 'AB', 2 ,10170,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (3,to_date('05/06/16', 'DD/MM/YY') ,53.13,'JUDET_CONSILIU',158415, 'AB', 3 ,84170,19,57.57, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (4,to_date('05/06/16', 'DD/MM/YY') ,26.13,'JUDET_CONSILIU',158415, 'AB', 4 ,41398,9,27.27, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (5,to_date('05/06/16', 'DD/MM/YY') ,4.53,'JUDET_CONSILIU',158415, 'AB', 5 ,7188,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (6,to_date('05/06/16', 'DD/MM/YY') ,3.14,'JUDET_CONSILIU',158415, 'AB', 6 ,4977,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (7,to_date('05/06/16', 'DD/MM/YY') ,3.18,'JUDET_CONSILIU',182649, 'AR', 7 ,5820,0, 0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (8,to_date('05/06/16', 'DD/MM/YY') ,5.17,'JUDET_CONSILIU',182649, 'AR', 1 ,9454,2, 6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (9,to_date('05/06/16', 'DD/MM/YY') ,6.08,'JUDET_CONSILIU',182649, 'AR', 2 ,11120,2, 6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (10,to_date('05/06/16', 'DD/MM/YY') ,46.22,'JUDET_CONSILIU',182649, 'AR', 3 ,84423,16, 48.48, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (11,to_date('05/06/16', 'DD/MM/YY') ,31.33,'JUDET_CONSILIU',182649, 'AR', 4 ,57224,11, 33.33, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (12,to_date('05/06/16', 'DD/MM/YY') ,6.51,'JUDET_CONSILIU',182649, 'AR', 5 ,11895,2, 6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (13,to_date('05/06/16', 'DD/MM/YY') ,1.48,'JUDET_CONSILIU',182649, 'AR', 6 ,2713,0, 0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (14,to_date('05/06/16', 'DD/MM/YY') ,8.31,'JUDET_CONSILIU',253555, 'AG', 1 ,21090,3, 9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (15,to_date('05/06/16', 'DD/MM/YY') ,6.04,'JUDET_CONSILIU',253555, 'AG', 2 ,15336,2, 6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (16,to_date('05/06/16', 'DD/MM/YY') ,0.96,'JUDET_CONSILIU',253555, 'AG', 8 ,2453,0, 0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (17,to_date('05/06/16', 'DD/MM/YY') ,1.01,'JUDET_CONSILIU',253555, 'AG', 9 ,2576,0, 0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (18,to_date('05/06/16', 'DD/MM/YY') ,21.96,'JUDET_CONSILIU',253555, 'AG', 3 ,55681,8,24.24, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (19,to_date('05/06/16', 'DD/MM/YY') ,5.57,'JUDET_CONSILIU',253555, 'AG', 10 ,14137,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (20,to_date('05/06/16', 'DD/MM/YY') ,1.28,'JUDET_CONSILIU',253555, 'AG', 11 ,3260,0,0.00, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (21,to_date('05/06/16', 'DD/MM/YY') ,1.16,'JUDET_CONSILIU',253555, 'AG', 12 ,2954,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (22,to_date('05/06/16', 'DD/MM/YY') ,51.49,'JUDET_CONSILIU',253555, 'AG', 4 ,130578,20,60.6, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (23,to_date('05/06/16', 'DD/MM/YY') ,2.16,'JUDET_CONSILIU',253555, 'AG', 6 ,5490,0,0.00, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (24,to_date('05/06/16', 'DD/MM/YY') ,11.69,'JUDET_CONSILIU',247184, 'BC', 1 ,28907,5,15.15, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (25,to_date('05/06/16', 'DD/MM/YY') ,2.72,'JUDET_CONSILIU',247184, 'BC', 13 ,6725,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (26,to_date('05/06/16', 'DD/MM/YY') ,27.05,'JUDET_CONSILIU',247184, 'BC', 3 ,66864,11,33.33, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (27,to_date('05/06/16', 'DD/MM/YY') ,41.66,'JUDET_CONSILIU',247184, 'BC', 4 ,102991,17,51.51, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (28,to_date('05/06/16', 'DD/MM/YY') ,7.19,'JUDET_CONSILIU',247184, 'BC', 14 ,17795,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (29,to_date('05/06/16', 'DD/MM/YY') ,6.47,'JUDET_CONSILIU',247184, 'BC', 15 ,16013,1,3.03, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (30,to_date('05/06/16', 'DD/MM/YY') ,3.19,'JUDET_CONSILIU',247184, 'BC', 6 ,7889,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (31,to_date('05/06/16', 'DD/MM/YY') ,2.88,'JUDET_CONSILIU',258399, 'BH', 16 ,7457,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (32,to_date('05/06/16', 'DD/MM/YY') ,5.33,'JUDET_CONSILIU',258399, 'BH', 1 ,13783,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (33,to_date('05/06/16', 'DD/MM/YY') ,1.79,'JUDET_CONSILIU',258399, 'BH', 2 ,4635,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (34,to_date('05/06/16', 'DD/MM/YY') ,44.54,'JUDET_CONSILIU',258399, 'BH', 3 ,115108,17,51.51, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (35,to_date('05/06/16', 'DD/MM/YY') ,25.23,'JUDET_CONSILIU',258399, 'BH', 4 ,65199,9,27.27, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (36,to_date('05/06/16', 'DD/MM/YY') ,18.19,'JUDET_CONSILIU',258399, 'BH', 5 ,47022,7,21.21, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (37,to_date('05/06/16', 'DD/MM/YY') ,2.01,'JUDET_CONSILIU',258399, 'BH', 6 ,5195,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (38,to_date('05/06/16', 'DD/MM/YY') ,1.92,'JUDET_CONSILIU',131081, 'BN', 17 ,2519,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (39,to_date('05/06/16', 'DD/MM/YY') ,0.69,'JUDET_CONSILIU',131081, 'BN', 18 ,909,0,0, 2016);   
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (40,to_date('05/06/16', 'DD/MM/YY') ,8.26,'JUDET_CONSILIU',131081, 'BN', 1 ,10834,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (41,to_date('05/06/16', 'DD/MM/YY') ,1.82,'JUDET_CONSILIU',131081, 'BN', 19 ,2386,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (42,to_date('05/06/16', 'DD/MM/YY') ,3.54,'JUDET_CONSILIU',131081, 'BN', 2 ,4652,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (43,to_date('05/06/16', 'DD/MM/YY') ,1.34,'JUDET_CONSILIU',131081, 'BN', 8 ,1766,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (44,to_date('05/06/16', 'DD/MM/YY') ,24.36,'JUDET_CONSILIU',131081, 'BN', 3 ,31943,9,27.27, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (45,to_date('05/06/16', 'DD/MM/YY') ,50.42,'JUDET_CONSILIU',131081, 'BN', 4 ,66095,19,57.57, 2016); 
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (46,to_date('05/06/16', 'DD/MM/YY') ,0.77,'JUDET_CONSILIU',131081, 'BN', 20 ,1020,0,0, 2016); 
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (47,to_date('05/06/16', 'DD/MM/YY') ,4.33,'JUDET_CONSILIU',131081, 'BN', 5 ,5678,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
  VALUES (48,to_date('05/06/16', 'DD/MM/YY') ,2.50,'JUDET_CONSILIU',131081, 'BN', 6 ,3279,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (49,to_date('05/06/16', 'DD/MM/YY') ,0.46,'JUDET_CONSILIU',169094, 'BT', 21 ,793,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (50,to_date('05/06/16', 'DD/MM/YY') ,3.68,'JUDET_CONSILIU',169094, 'BT', 1 ,6226,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (51,to_date('05/06/16', 'DD/MM/YY') ,1.40,'JUDET_CONSILIU',169094, 'BT', 19 ,2379,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (52,to_date('05/06/16', 'DD/MM/YY') ,3.22,'JUDET_CONSILIU',169094, 'BT', 2 ,5456,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (53,to_date('05/06/16', 'DD/MM/YY') ,0.54,'JUDET_CONSILIU',169094, 'BT', 8 ,917,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (54,to_date('05/06/16', 'DD/MM/YY') ,0.54,'JUDET_CONSILIU',169094, 'BT', 9 ,930,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (55,to_date('05/06/16', 'DD/MM/YY') ,36.71,'JUDET_CONSILIU',169094, 'BT', 3 ,62084,15,45.45, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (56,to_date('05/06/16', 'DD/MM/YY') ,2.40,'JUDET_CONSILIU',169094, 'BT', 22 ,4064,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (57,to_date('05/06/16', 'DD/MM/YY') ,1.15,'JUDET_CONSILIU',169094, 'BT', 12 ,1959,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (58,to_date('05/06/16', 'DD/MM/YY') ,46.54,'JUDET_CONSILIU',169094, 'BT', 4 ,78698,18,54.54, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (59,to_date('05/06/16', 'DD/MM/YY') ,3.30,'JUDET_CONSILIU',169094, 'BT', 6 ,5588,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (60,to_date('05/06/16', 'DD/MM/YY') ,0.81,'JUDET_CONSILIU',169094, 'BR', 21 ,1033,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (61,to_date('05/06/16', 'DD/MM/YY') ,4.37,'JUDET_CONSILIU',169094, 'BR', 23 ,5544,1,3.03, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (62,to_date('05/06/16', 'DD/MM/YY') ,0.97,'JUDET_CONSILIU',169094, 'BR', 24 ,1238,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (63,to_date('05/06/16', 'DD/MM/YY') ,6.83,'JUDET_CONSILIU',169094, 'BR', 1 ,8659,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (64,to_date('05/06/16', 'DD/MM/YY') ,1.29,'JUDET_CONSILIU',169094, 'BR', 19 ,1634,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (65,to_date('05/06/16', 'DD/MM/YY') ,5.19,'JUDET_CONSILIU',169094, 'BR', 2 ,6578,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (66,to_date('05/06/16', 'DD/MM/YY') ,0.74,'JUDET_CONSILIU',169094, 'BR', 8 ,941,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (67,to_date('05/06/16', 'DD/MM/YY') ,5.43,'JUDET_CONSILIU',169094, 'BR', 9 ,6887,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (68,to_date('05/06/16', 'DD/MM/YY') ,30.50,'JUDET_CONSILIU',169094, 'BR', 3 ,38621,12,36.36, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (69,to_date('05/06/16', 'DD/MM/YY') ,2.62,'JUDET_CONSILIU',169094, 'BR', 22 ,3321,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (70,to_date('05/06/16', 'DD/MM/YY') ,0.61,'JUDET_CONSILIU',169094, 'BR', 12 ,776,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (71,to_date('05/06/16', 'DD/MM/YY') ,36.09,'JUDET_CONSILIU',169094, 'BR', 4 ,45702,14,42.42, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (72,to_date('05/06/16', 'DD/MM/YY') ,2.01,'JUDET_CONSILIU',169094, 'BR', 14 ,2547,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (73,to_date('05/06/16', 'DD/MM/YY') ,2.47,'JUDET_CONSILIU',169094, 'BR', 6 ,3136,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (74,to_date('05/06/16', 'DD/MM/YY') ,5.51,'JUDET_CONSILIU',215828, 'BV', 25 ,11898,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (75,to_date('05/06/16', 'DD/MM/YY') ,4.93,'JUDET_CONSILIU',215828, 'BV', 1 ,10659,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (76,to_date('05/06/16', 'DD/MM/YY') ,7.63,'JUDET_CONSILIU',215828, 'BV', 19 ,16489,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (77,to_date('05/06/16', 'DD/MM/YY') ,4.20,'JUDET_CONSILIU',215828, 'BV', 2 ,9079,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (78,to_date('05/06/16', 'DD/MM/YY') ,1.82,'JUDET_CONSILIU',215828, 'BV', 9 ,3943,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (79,to_date('05/06/16', 'DD/MM/YY') ,38.37,'JUDET_CONSILIU',215828, 'BV', 3 ,82831,16,48.48, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (80,to_date('05/06/16', 'DD/MM/YY') ,28.73,'JUDET_CONSILIU',215828, 'BV', 4 ,59851,11,33.33, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (81,to_date('05/06/16', 'DD/MM/YY') ,6.99,'JUDET_CONSILIU',215828, 'BV', 5 ,15098,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (82,to_date('05/06/16', 'DD/MM/YY') ,2.77,'JUDET_CONSILIU',215828, 'BV', 6 ,5980,0,0, 2016);
 
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (83,to_date('05/06/16', 'DD/MM/YY') ,1.59,'JUDET_CONSILIU',197624, 'BZ', 26 ,3149,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (84,to_date('05/06/16', 'DD/MM/YY') ,1.02,'JUDET_CONSILIU',197624, 'BZ', 18 ,2022,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (85,to_date('05/06/16', 'DD/MM/YY') ,5.27,'JUDET_CONSILIU',197624, 'BZ', 1 ,10415,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (86,to_date('05/06/16', 'DD/MM/YY') ,2.42,'JUDET_CONSILIU',197624, 'BZ', 19 ,4794,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (87,to_date('05/06/16', 'DD/MM/YY') ,2.48,'JUDET_CONSILIU',197624, 'BZ', 2 ,4912,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (88,to_date('05/06/16', 'DD/MM/YY') ,23.16,'JUDET_CONSILIU',197624, 'BZ', 3 ,45789,9,27.27, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (89,to_date('05/06/16', 'DD/MM/YY') ,1.44,'JUDET_CONSILIU',197624, 'BZ', 22 ,2861,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (90,to_date('05/06/16', 'DD/MM/YY') ,0.88,'JUDET_CONSILIU',197624, 'BZ', 12 ,1740,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (91,to_date('05/06/16', 'DD/MM/YY') ,53.33,'JUDET_CONSILIU',197624, 'BZ', 4 ,105405,20,60.60, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (92,to_date('05/06/16', 'DD/MM/YY') ,5.48,'JUDET_CONSILIU',197624, 'BZ', 14 ,10831,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (93,to_date('05/06/16', 'DD/MM/YY') ,2.88,'JUDET_CONSILIU',197624, 'BZ', 6 ,5706,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (94,to_date('05/06/16', 'DD/MM/YY') ,1.83,'JUDET_CONSILIU',133250, 'CL', 21 ,2439,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (95,to_date('05/06/16', 'DD/MM/YY') ,6.72,'JUDET_CONSILIU',133250, 'CL', 1 ,8967,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (96,to_date('05/06/16', 'DD/MM/YY') ,2.72,'JUDET_CONSILIU',133250, 'CL', 27 ,3628,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (97,to_date('05/06/16', 'DD/MM/YY') ,1.78,'JUDET_CONSILIU',133250, 'CL', 2 ,2373,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (98,to_date('05/06/16', 'DD/MM/YY') ,47.12,'JUDET_CONSILIU',133250, 'CL', 3 ,62794,17,51.51, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (99,to_date('05/06/16', 'DD/MM/YY') ,32.43,'JUDET_CONSILIU',133250, 'CL', 4 ,43224,12,36.36, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (100,to_date('05/06/16', 'DD/MM/YY') ,2.64,'JUDET_CONSILIU',133250, 'CL', 14 ,3522,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (101,to_date('05/06/16', 'DD/MM/YY') ,4.73,'JUDET_CONSILIU',133250, 'CL', 6 ,6303,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (102,to_date('05/06/16', 'DD/MM/YY') ,1.68,'JUDET_CONSILIU',131188, 'CS', 28 ,2217,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (103,to_date('05/06/16', 'DD/MM/YY') ,3.98,'JUDET_CONSILIU',131188, 'CS', 1 ,3920,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (104,to_date('05/06/16', 'DD/MM/YY') ,6.15,'JUDET_CONSILIU',131188, 'CS', 2 ,8078,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (105,to_date('05/06/16', 'DD/MM/YY') ,1.58,'JUDET_CONSILIU',131188, 'CS', 8 ,2078,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (106,to_date('05/06/16', 'DD/MM/YY') ,1.24,'JUDET_CONSILIU',131188, 'CS', 9 ,1631,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (107,to_date('05/06/16', 'DD/MM/YY') ,36.19,'JUDET_CONSILIU',131188, 'CS', 3 ,47489,13,39.39, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (108,to_date('05/06/16', 'DD/MM/YY') ,1.64,'JUDET_CONSILIU',131188, 'CS', 11 ,2156,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (109,to_date('05/06/16', 'DD/MM/YY') ,1.64,'JUDET_CONSILIU',131188, 'CS', 22 ,2154,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (110,to_date('05/06/16', 'DD/MM/YY') ,36.41,'JUDET_CONSILIU',131188, 'CS', 4 ,47767,14,42.42, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (111,to_date('05/06/16', 'DD/MM/YY') ,1.24,'JUDET_CONSILIU',131188, 'CS', 14 ,1629,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (112,to_date('05/06/16', 'DD/MM/YY') ,1.22,'JUDET_CONSILIU',131188, 'CS', 29 ,1610,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (113,to_date('05/06/16', 'DD/MM/YY') ,1.77,'JUDET_CONSILIU',131188, 'CS', 20 ,2323,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (114,to_date('05/06/16', 'DD/MM/YY') ,6.20,'JUDET_CONSILIU',131188, 'CS', 6 ,8136,2,6.06, 2016);
 
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (115,to_date('05/06/16', 'DD/MM/YY') ,0.96,'JUDET_CONSILIU',256831, 'CJ', 21 ,2491,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (116,to_date('05/06/16', 'DD/MM/YY') ,1.30,'JUDET_CONSILIU',256831, 'CJ', 16 ,3358,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (117,to_date('05/06/16', 'DD/MM/YY') ,3.49,'JUDET_CONSILIU',256831, 'CJ', 1 ,8983,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (118,to_date('05/06/16', 'DD/MM/YY') ,1.84,'JUDET_CONSILIU',256831, 'CJ', 13 ,4750,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (120,to_date('05/06/16', 'DD/MM/YY') ,1.13,'JUDET_CONSILIU',256831, 'CJ', 30 ,2924,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (121,to_date('05/06/16', 'DD/MM/YY') ,4.37,'JUDET_CONSILIU',256831, 'CJ', 2 ,11236,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (122,to_date('05/06/16', 'DD/MM/YY') ,1.40,'JUDET_CONSILIU',256831, 'CJ', 8 ,3600,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (123,to_date('05/06/16', 'DD/MM/YY') ,37.99,'JUDET_CONSILIU',256831, 'CJ', 3 ,97591,18,54.54, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (124,to_date('05/06/16', 'DD/MM/YY') ,1.38,'JUDET_CONSILIU',256831, 'CJ', 11 ,3566,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (125,to_date('05/06/16', 'DD/MM/YY') ,2.35,'JUDET_CONSILIU',256831, 'CJ', 22 ,6046,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (126,to_date('05/06/16', 'DD/MM/YY') ,26.60,'JUDET_CONSILIU',256831, 'CJ', 4 ,68331,12,36.36, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (127,to_date('05/06/16', 'DD/MM/YY') ,1.55,'JUDET_CONSILIU',256831, 'CJ', 14 ,3992,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (128,to_date('05/06/16', 'DD/MM/YY') ,1.59,'JUDET_CONSILIU',256831, 'CJ', 29 ,4108,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (129,to_date('05/06/16', 'DD/MM/YY') ,13.96,'JUDET_CONSILIU',256831, 'CJ', 5 ,35855,7,21.21, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (130,to_date('05/06/16', 'DD/MM/YY') ,6.02,'JUDET_CONSILIU',289243, 'CT', 1 ,17416,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (131,to_date('05/06/16', 'DD/MM/YY') ,2.88,'JUDET_CONSILIU',289243, 'CT', 19 ,8342,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (132,to_date('05/06/16', 'DD/MM/YY') ,2.76,'JUDET_CONSILIU',289243, 'CT', 13 ,7991,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (133,to_date('05/06/16', 'DD/MM/YY') ,6.36,'JUDET_CONSILIU',289243, 'CT', 2 ,18410,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (134,to_date('05/06/16', 'DD/MM/YY') ,1.03,'JUDET_CONSILIU',289243, 'CT', 8 ,2999,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (135,to_date('05/06/16', 'DD/MM/YY') ,1.26,'JUDET_CONSILIU',289243, 'CT', 9 ,3646,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (136,to_date('05/06/16', 'DD/MM/YY') ,34.50,'JUDET_CONSILIU',289243, 'CT', 3 ,99815,15,45.45, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (137,to_date('05/06/16', 'DD/MM/YY') ,0.91,'JUDET_CONSILIU',289243, 'CT', 11 ,2635,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (138,to_date('05/06/16', 'DD/MM/YY') ,2.60,'JUDET_CONSILIU',289243, 'CT', 22 ,7524,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (139,to_date('05/06/16', 'DD/MM/YY') ,1.53,'JUDET_CONSILIU',289243, 'CT', 12 ,4453,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (140,to_date('05/06/16', 'DD/MM/YY') ,34.56,'JUDET_CONSILIU',289243, 'CT', 4 ,99971,16,48.48, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (141,to_date('05/06/16', 'DD/MM/YY') ,3.44,'JUDET_CONSILIU',289243, 'CT', 31 ,9961,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (142,to_date('05/06/16', 'DD/MM/YY') ,2.10,'JUDET_CONSILIU',289243, 'CT', 6 ,6080,0,0, 2016);
 
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (143,to_date('05/06/16', 'DD/MM/YY') ,1.39,'JUDET_CONSILIU',73765, 'CV', 21 ,1029,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (144,to_date('05/06/16', 'DD/MM/YY') ,8.65,'JUDET_CONSILIU',73765, 'CV', 16 ,6381,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (145,to_date('05/06/16', 'DD/MM/YY') ,5.46,'JUDET_CONSILIU',73765, 'CV', 32 ,4028,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (146,to_date('05/06/16', 'DD/MM/YY') ,7.77,'JUDET_CONSILIU',73765, 'CV', 3 ,5732,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (147,to_date('05/06/16', 'DD/MM/YY') ,0.69,'JUDET_CONSILIU',73765, 'CV', 11 ,509,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (148,to_date('05/06/16', 'DD/MM/YY') ,0.62,'JUDET_CONSILIU',73765, 'CV', 22 ,464,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (149,to_date('05/06/16', 'DD/MM/YY') ,9.55,'JUDET_CONSILIU',73765, 'CV', 4 ,7049,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (150,to_date('05/06/16', 'DD/MM/YY') ,57.87,'JUDET_CONSILIU',73765, 'CV', 5 ,42688,20,60.60, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (151,to_date('05/06/16', 'DD/MM/YY') ,3.02,'JUDET_CONSILIU',73765, 'CV', 6 ,2233,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (152,to_date('05/06/16', 'DD/MM/YY') ,0.48,'JUDET_CONSILIU',231371, 'DB', 33 ,1114,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (153,to_date('05/06/16', 'DD/MM/YY') ,1.24,'JUDET_CONSILIU',231371, 'DB', 21 ,2880,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (154,to_date('05/06/16', 'DD/MM/YY') ,1.94,'JUDET_CONSILIU',231371, 'DB', 34 ,4501,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (155,to_date('05/06/16', 'DD/MM/YY') ,0.84,'JUDET_CONSILIU',231371, 'DB', 35 ,1949,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (156,to_date('05/06/16', 'DD/MM/YY') ,3.51,'JUDET_CONSILIU',231371, 'DB', 1 ,8124,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (157,to_date('05/06/16', 'DD/MM/YY') ,2.21,'JUDET_CONSILIU',231371, 'DB', 2 ,5115,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (158,to_date('05/06/16', 'DD/MM/YY') ,1.10,'JUDET_CONSILIU',231371, 'DB', 9 ,2559,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (159,to_date('05/06/16', 'DD/MM/YY') ,31.41,'JUDET_CONSILIU',231371, 'DB', 3 ,72679,13,39.39, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (160,to_date('05/06/16', 'DD/MM/YY') ,1.64,'JUDET_CONSILIU',231371, 'DB', 22 ,3817,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (161,to_date('05/06/16', 'DD/MM/YY') ,0.46,'JUDET_CONSILIU',231371, 'DB', 12 ,1075,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (162,to_date('05/06/16', 'DD/MM/YY') ,51.88,'JUDET_CONSILIU',231371, 'DB', 4 ,120037,22,66.66, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (163,to_date('05/06/16', 'DD/MM/YY') ,3.25,'JUDET_CONSILIU',231371, 'DB', 6 ,7521,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (164,to_date('05/06/16', 'DD/MM/YY') ,1.91,'JUDET_CONSILIU',284848, 'DJ', 36 ,5458,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (165,to_date('05/06/16', 'DD/MM/YY') ,1.12,'JUDET_CONSILIU',284848, 'DJ', 37 ,3218,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (166,to_date('05/06/16', 'DD/MM/YY') ,4.54,'JUDET_CONSILIU',284848, 'DJ', 1 ,12951,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (167,to_date('05/06/16', 'DD/MM/YY') ,1.20,'JUDET_CONSILIU',284848, 'DJ', 19 ,3425,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (168,to_date('05/06/16', 'DD/MM/YY') ,1.06,'JUDET_CONSILIU',284848, 'DJ', 30 ,3038,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (169,to_date('05/06/16', 'DD/MM/YY') ,2.86,'JUDET_CONSILIU',284848, 'DJ', 2 ,8162,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (170,to_date('05/06/16', 'DD/MM/YY') ,1.20,'JUDET_CONSILIU',284848, 'DJ', 9 ,3444,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (171,to_date('05/06/16', 'DD/MM/YY') ,26.60,'JUDET_CONSILIU',284848, 'DJ', 3 ,75795,12,36.36, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (172,to_date('05/06/16', 'DD/MM/YY') ,53.53,'JUDET_CONSILIU',284848, 'DJ', 4 ,152497,25,75.75, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (173,to_date('05/06/16', 'DD/MM/YY') ,2.71,'JUDET_CONSILIU',284848, 'DJ', 14 ,7742,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (174,to_date('05/06/16', 'DD/MM/YY') ,3.20,'JUDET_CONSILIU',284848, 'DJ', 6 ,9118,0,0, 2016);
 
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (175,to_date('05/06/16', 'DD/MM/YY') ,1.97,'JUDET_CONSILIU',221941, 'GL', 21 ,4384,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (176,to_date('05/06/16', 'DD/MM/YY') ,2.74,'JUDET_CONSILIU',221941, 'GL', 38 ,6103,0,0.00, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (177,to_date('05/06/16', 'DD/MM/YY') ,11.54,'JUDET_CONSILIU',221941, 'GL', 1 ,25612,4,12.12, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (178,to_date('05/06/16', 'DD/MM/YY') ,7.09,'JUDET_CONSILIU',221941, 'GL', 2 ,15757,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (179,to_date('05/06/16', 'DD/MM/YY') ,24.46,'JUDET_CONSILIU',221941, 'GL', 3 ,54302,9,27.27, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (180,to_date('05/06/16', 'DD/MM/YY') ,1.14,'JUDET_CONSILIU',221941, 'GL', 22 ,2542,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (181,to_date('05/06/16', 'DD/MM/YY') ,38.14,'JUDET_CONSILIU',221941, 'GL', 4 ,84659,15,45.45, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (182,to_date('05/06/16', 'DD/MM/YY') ,1.77,'JUDET_CONSILIU',221941, 'GL', 14 ,3939,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (183,to_date('05/06/16', 'DD/MM/YY') ,9.00,'JUDET_CONSILIU',221941, 'GL', 6 ,19984,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (184,to_date('05/06/16', 'DD/MM/YY') ,2.09,'JUDET_CONSILIU',221941, 'GL', 39 ,4659,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (185,to_date('05/06/16', 'DD/MM/YY') ,0.61,'JUDET_CONSILIU',134684, 'GR', 21 ,822,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (186,to_date('05/06/16', 'DD/MM/YY') ,9.61,'JUDET_CONSILIU',134684, 'GR', 1 ,12946,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (187,to_date('05/06/16', 'DD/MM/YY') ,1.69,'JUDET_CONSILIU',134684, 'GR', 30 ,2287,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (188,to_date('05/06/16', 'DD/MM/YY') ,2.07,'JUDET_CONSILIU',134684, 'GR', 2 ,2798,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (189,to_date('05/06/16', 'DD/MM/YY') ,23.37,'JUDET_CONSILIU',134684, 'GR', 3 ,31488,8,24.24, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (190,to_date('05/06/16', 'DD/MM/YY') ,57.54,'JUDET_CONSILIU',134684, 'GR', 4 ,77507,20,60.60, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (191,to_date('05/06/16', 'DD/MM/YY') ,2.15,'JUDET_CONSILIU',134684, 'GR', 14 ,2903,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (192,to_date('05/06/16', 'DD/MM/YY') ,2.92,'JUDET_CONSILIU',134684, 'GR', 6 ,3933,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (193,to_date('05/06/16', 'DD/MM/YY') ,7.85,'JUDET_CONSILIU',157636, 'GJ', 1 ,12385,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (194,to_date('05/06/16', 'DD/MM/YY') ,6.75,'JUDET_CONSILIU',157636, 'GJ', 2 ,10647,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (195,to_date('05/06/16', 'DD/MM/YY') ,1.46,'JUDET_CONSILIU',157636, 'GJ', 9 ,2316,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (196,to_date('05/06/16', 'DD/MM/YY') ,24.99,'JUDET_CONSILIU',157636, 'GJ', 3 ,39402,9,27.27, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (197,to_date('05/06/16', 'DD/MM/YY') ,2.43,'JUDET_CONSILIU',157636, 'GJ', 22 ,3846,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (198,to_date('05/06/16', 'DD/MM/YY') ,3.89,'JUDET_CONSILIU',157636, 'GJ', 12 ,6147,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (199,to_date('05/06/16', 'DD/MM/YY') ,42.66,'JUDET_CONSILIU',157636, 'GJ', 4 ,67261,16,48.48, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (200,to_date('05/06/16', 'DD/MM/YY') ,5.12,'JUDET_CONSILIU',157636, 'GJ', 14 ,8071,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (201,to_date('05/06/16', 'DD/MM/YY') ,1.38,'JUDET_CONSILIU',157636, 'GJ', 40 ,2189,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (202,to_date('05/06/16', 'DD/MM/YY') ,3.40,'JUDET_CONSILIU',157636, 'GJ', 6 ,5372,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (203,to_date('05/06/16', 'DD/MM/YY') ,10.75,'JUDET_CONSILIU',109815, 'HR', 16 ,11809,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (204,to_date('05/06/16', 'DD/MM/YY') ,11.65,'JUDET_CONSILIU',109815, 'HR', 32 ,12796,4,12.12, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (205,to_date('05/06/16', 'DD/MM/YY') ,6.63,'JUDET_CONSILIU',109815, 'HR', 3 ,7284,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (206,to_date('05/06/16', 'DD/MM/YY') ,0.96,'JUDET_CONSILIU',109815, 'HR', 22 ,1055,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (207,to_date('05/06/16', 'DD/MM/YY') ,8.60,'JUDET_CONSILIU',109815, 'HR', 4 ,9448,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (208,to_date('05/06/16', 'DD/MM/YY') ,61.39,'JUDET_CONSILIU',109815, 'HR', 5 ,67423,19,57.57, 2016);
 
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (209,to_date('05/06/16', 'DD/MM/YY') ,5.48,'JUDET_CONSILIU',191156, 'HD', 41 ,10481,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (210,to_date('05/06/16', 'DD/MM/YY') ,4.93,'JUDET_CONSILIU',191156, 'HD', 1 ,9433,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (211,to_date('05/06/16', 'DD/MM/YY') ,3.10,'JUDET_CONSILIU',191156, 'HD', 2 ,5934,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (212,to_date('05/06/16', 'DD/MM/YY') ,1.71,'JUDET_CONSILIU',191156, 'HD', 9 ,3271,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (213,to_date('05/06/16', 'DD/MM/YY') ,34.08,'JUDET_CONSILIU',191156, 'HD', 3 ,65157,15,45.45, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (214,to_date('05/06/16', 'DD/MM/YY') ,2.72,'JUDET_CONSILIU',191156, 'HD', 22 ,5202,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (215,to_date('05/06/16', 'DD/MM/YY') ,40.03,'JUDET_CONSILIU',191156, 'HD', 4 ,76531,18,54.54, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (216,to_date('05/06/16', 'DD/MM/YY') ,4.53,'JUDET_CONSILIU',191156, 'HD', 14 ,8660,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (217,to_date('05/06/16', 'DD/MM/YY') ,3.39,'JUDET_CONSILIU',191156, 'HD', 6 ,6487,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (218,to_date('05/06/16', 'DD/MM/YY') ,1.77,'JUDET_CONSILIU',117471, 'IL', 21 ,2086,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (219,to_date('05/06/16', 'DD/MM/YY') ,8.02,'JUDET_CONSILIU',117471, 'IL', 1 ,9425,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (220,to_date('05/06/16', 'DD/MM/YY') ,4.32,'JUDET_CONSILIU',117471, 'IL', 42 ,5081,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (221,to_date('05/06/16', 'DD/MM/YY') ,2.74,'JUDET_CONSILIU',117471, 'IL', 2 ,3228,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (222,to_date('05/06/16', 'DD/MM/YY') ,29.12,'JUDET_CONSILIU',117471, 'IL', 3 ,34213,10,30.30, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (223,to_date('05/06/16', 'DD/MM/YY') ,50.36,'JUDET_CONSILIU',117471, 'IL', 4 ,59160,18,54.54, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (224,to_date('05/06/16', 'DD/MM/YY') ,3.64,'JUDET_CONSILIU',117471, 'IL', 6 ,4278,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (225,to_date('05/06/16', 'DD/MM/YY') ,0.94,'JUDET_CONSILIU',287009, 'IS', 21 ,2700,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (226,to_date('05/06/16', 'DD/MM/YY') ,7.11,'JUDET_CONSILIU',287009, 'IS', 1 ,20424,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (227,to_date('05/06/16', 'DD/MM/YY') ,4.01,'JUDET_CONSILIU',287009, 'IS', 19 ,11534,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (228,to_date('05/06/16', 'DD/MM/YY') ,7.03,'JUDET_CONSILIU',287009, 'IS', 2 ,20177,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (229,to_date('05/06/16', 'DD/MM/YY') ,29.07,'JUDET_CONSILIU',287009, 'IS', 3 ,83449,12,36.39, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (230,to_date('05/06/16', 'DD/MM/YY') ,43.01,'JUDET_CONSILIU',287009, 'IS', 4 ,123467,17,51.51, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (231,to_date('05/06/16', 'DD/MM/YY') ,2.16,'JUDET_CONSILIU',287009, 'IS', 14 ,6215,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (232,to_date('05/06/16', 'DD/MM/YY') ,0.78,'JUDET_CONSILIU',287009, 'IS', 40 ,2256,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (233,to_date('05/06/16', 'DD/MM/YY') ,5.84,'JUDET_CONSILIU',287009, 'IS', 6 ,16787,2,6.06, 2016);
 
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (234,to_date('05/06/16', 'DD/MM/YY') ,9.43,'JUDET_CONSILIU',172595, 'IF', 1 ,16283,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (235,to_date('05/06/16', 'DD/MM/YY') ,2.75,'JUDET_CONSILIU',172595, 'IF', 13 ,4759,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (236,to_date('05/06/16', 'DD/MM/YY') ,5.73,'JUDET_CONSILIU',172595, 'IF', 2 ,9899,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (237,to_date('05/06/16', 'DD/MM/YY') ,44.62,'JUDET_CONSILIU',172595, 'IF', 3 ,77021,16,48.48, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (238,to_date('05/06/16', 'DD/MM/YY') ,1.69,'JUDET_CONSILIU',172595, 'IF', 12 ,2921,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (239,to_date('05/06/16', 'DD/MM/YY') ,31.82,'JUDET_CONSILIU',172595, 'IF', 4 ,54935,12,36.36, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (240,to_date('05/06/16', 'DD/MM/YY') ,3.92,'JUDET_CONSILIU',172595, 'IF', 6 ,6777,0,0, 2016);
 
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (241,to_date('05/06/16', 'DD/MM/YY') ,13.80,'JUDET_CONSILIU',189886, 'MM', 43 ,26217,5,15.15, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (242,to_date('05/06/16', 'DD/MM/YY') ,2.39,'JUDET_CONSILIU',189886, 'MM', 1 ,4542,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (243,to_date('05/06/16', 'DD/MM/YY') ,5.94,'JUDET_CONSILIU',189886, 'MM', 19 ,11282,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (244,to_date('05/06/16', 'DD/MM/YY') ,1.66,'JUDET_CONSILIU',189886, 'MM', 44 ,3156,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (245,to_date('05/06/16', 'DD/MM/YY') ,8.58,'JUDET_CONSILIU',189886, 'MM', 2 ,16305,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (246,to_date('05/06/16', 'DD/MM/YY') ,1.31,'JUDET_CONSILIU',189886, 'MM', 9 ,2493,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (247,to_date('05/06/16', 'DD/MM/YY') ,22.83,'JUDET_CONSILIU',189886, 'MM', 3 ,43365,9,27.27, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (248,to_date('05/06/16', 'DD/MM/YY') ,1.70,'JUDET_CONSILIU',189886, 'MM', 22 ,3235,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (249,to_date('05/06/16', 'DD/MM/YY') ,35.13,'JUDET_CONSILIU',189886, 'MM', 4 ,66717,14,42.42, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (393,to_date('05/06/16', 'DD/MM/YY') ,5.41,'JUDET_CONSILIU',189886, 'MM', 5 ,10278,2,6.06, 2016);
 
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (250,to_date('05/06/16', 'DD/MM/YY') ,50.72,'JUDET_CONSILIU',120206, 'MH', 4 ,60972,18,54.54, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (251,to_date('05/06/16', 'DD/MM/YY') ,1.15,'JUDET_CONSILIU',120206, 'MH', 21 ,1383,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (252,to_date('05/06/16', 'DD/MM/YY') ,1.15,'JUDET_CONSILIU',120206, 'MH', 18 ,1388,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (253,to_date('05/06/16', 'DD/MM/YY') ,6.55,'JUDET_CONSILIU',120206, 'MH', 1 ,7874,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (254,to_date('05/06/16', 'DD/MM/YY') ,4.04,'JUDET_CONSILIU',120206, 'MH', 2 ,4863,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (255,to_date('05/06/16', 'DD/MM/YY') ,1.08,'JUDET_CONSILIU',120206, 'MH', 8 ,1302,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (256,to_date('05/06/16', 'DD/MM/YY') ,1.82,'JUDET_CONSILIU',120206, 'MH', 9 ,2193,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (257,to_date('05/06/16', 'DD/MM/YY') ,29.59,'JUDET_CONSILIU',120206, 'MH', 3 ,35570,11,33.33, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (258,to_date('05/06/16', 'DD/MM/YY') ,1.44,'JUDET_CONSILIU',120206, 'MH', 14 ,1733,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (259,to_date('05/06/16', 'DD/MM/YY') ,2.43,'JUDET_CONSILIU',120206, 'MH', 45 ,2928,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (260,to_date('05/06/16', 'DD/MM/YY') ,1.37,'JUDET_CONSILIU',232169, 'MS', 21 ,3200,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (261,to_date('05/06/16', 'DD/MM/YY') ,2.34,'JUDET_CONSILIU',232169, 'MS', 16 ,5444,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (262,to_date('05/06/16', 'DD/MM/YY') ,2.71,'JUDET_CONSILIU',232169, 'MS', 1 ,6301,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (263,to_date('05/06/16', 'DD/MM/YY') ,4.23,'JUDET_CONSILIU',232169, 'MS', 2 ,9828,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (264,to_date('05/06/16', 'DD/MM/YY') ,22.63,'JUDET_CONSILIU',232169, 'MS', 3 ,52544,10,30.30, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (265,to_date('05/06/16', 'DD/MM/YY') ,4.45,'JUDET_CONSILIU',232169, 'MS', 46 ,10335,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (266,to_date('05/06/16', 'DD/MM/YY') ,2.14,'JUDET_CONSILIU',232169, 'MS', 12 ,4979,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (267,to_date('05/06/16', 'DD/MM/YY') ,23.92,'JUDET_CONSILIU',232169, 'MS', 4 ,55541,10,30.30, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (268,to_date('05/06/16', 'DD/MM/YY') ,34.06,'JUDET_CONSILIU',232169, 'MS',5 ,79084,15,45.45, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (269,to_date('05/06/16', 'DD/MM/YY') ,2.11,'JUDET_CONSILIU',232169, 'MS',6 ,4913,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (270,to_date('05/06/16', 'DD/MM/YY') ,44.24,'JUDET_CONSILIU',204794, 'NT',4 ,90607,17,51.51, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (271,to_date('05/06/16', 'DD/MM/YY') ,5.54,'JUDET_CONSILIU',204794, 'NT',1 ,11365,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (272,to_date('05/06/16', 'DD/MM/YY') ,1.39,'JUDET_CONSILIU',204794, 'NT',13 ,2851,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (273,to_date('05/06/16', 'DD/MM/YY') ,5.04,'JUDET_CONSILIU',204794, 'NT',2 ,10327,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (274,to_date('05/06/16', 'DD/MM/YY') ,2.55,'JUDET_CONSILIU',204794, 'NT',9 ,5242,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (275,to_date('05/06/16', 'DD/MM/YY') ,36.17,'JUDET_CONSILIU',204794, 'NT',3 ,74075,14,42.42, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (276,to_date('05/06/16', 'DD/MM/YY') ,3.10,'JUDET_CONSILIU',204794, 'NT',12 ,6356,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (277,to_date('05/06/16', 'DD/MM/YY') ,1.93,'JUDET_CONSILIU',204794, 'NT',14 ,3971,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (278,to_date('05/06/16', 'DD/MM/YY') ,0.93,'JUDET_CONSILIU',212841, 'OT',47 ,1987,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (279,to_date('05/06/16', 'DD/MM/YY') ,7.93,'JUDET_CONSILIU',212841, 'OT',1 ,16895,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (280,to_date('05/06/16', 'DD/MM/YY') ,2.67,'JUDET_CONSILIU',212841, 'OT',2 ,5703,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (281,to_date('05/06/16', 'DD/MM/YY') ,0.88,'JUDET_CONSILIU',212841, 'OT',9 ,1880,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (282,to_date('05/06/16', 'DD/MM/YY') ,24.66,'JUDET_CONSILIU',212841, 'OT',3 ,52498,9,27.27, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (283,to_date('05/06/16', 'DD/MM/YY') ,1.99,'JUDET_CONSILIU',212841, 'OT',22 ,4245,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (284,to_date('05/06/16', 'DD/MM/YY') ,55.17,'JUDET_CONSILIU',212841, 'OT',4 ,117440,19,57.57, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (285,to_date('05/06/16', 'DD/MM/YY') ,5.72,'JUDET_CONSILIU',212841, 'OT',6 ,12193,2,6.06, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (286,to_date('05/06/16', 'DD/MM/YY') ,0.97,'JUDET_CONSILIU',320009, 'PH',21 ,3106,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (287,to_date('05/06/16', 'DD/MM/YY') ,10.15,'JUDET_CONSILIU',320009, 'PH',1 ,32502,4,12.12, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (288,to_date('05/06/16', 'DD/MM/YY') ,5.42,'JUDET_CONSILIU',320009, 'PH',2 ,17366,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (289,to_date('05/06/16', 'DD/MM/YY') ,1.81,'JUDET_CONSILIU',320009, 'PH',9 ,5819,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (290,to_date('05/06/16', 'DD/MM/YY') ,35.35,'JUDET_CONSILIU',320009, 'PH',3 ,113125,15,45.45, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (291,to_date('05/06/16', 'DD/MM/YY') ,1.88,'JUDET_CONSILIU',320009, 'PH',22 ,6044,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (292,to_date('05/06/16', 'DD/MM/YY') ,1.28,'JUDET_CONSILIU',320009, 'PH',12 ,4110,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (293,to_date('05/06/16', 'DD/MM/YY') ,38.50,'JUDET_CONSILIU',320009, 'PH',4 ,123212,16,48.48, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (294,to_date('05/06/16', 'DD/MM/YY') ,2.43,'JUDET_CONSILIU',320009, 'PH',14 ,7805,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (295,to_date('05/06/16', 'DD/MM/YY') ,2.16,'JUDET_CONSILIU',320009, 'PH',6 ,6920,0,0, 2016);
 
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (296,to_date('05/06/16', 'DD/MM/YY'), 1.94,'JUDET_CONSILIU',101392, 'SJ',16 ,1976,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (297,to_date('05/06/16', 'DD/MM/YY'), 4.53,'JUDET_CONSILIU',101392, 'SJ',1 ,4597,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (298,to_date('05/06/16', 'DD/MM/YY'), 4.05,'JUDET_CONSILIU',101392, 'SJ',2 ,4113,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (299,to_date('05/06/16', 'DD/MM/YY'), 28.12,'JUDET_CONSILIU',101392, 'SJ',3 ,28519,10,30.30, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (300,to_date('05/06/16', 'DD/MM/YY'), 36.37,'JUDET_CONSILIU',101392, 'SJ',4 ,36880,12,36.36, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (301,to_date('05/06/16', 'DD/MM/YY'), 19.95,'JUDET_CONSILIU',101392, 'SJ',5 ,20231,7,21.21, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (302,to_date('05/06/16', 'DD/MM/YY'), 5.00,'JUDET_CONSILIU',101392, 'SJ',6 ,5076,2,6.06, 2016);
 
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (303,to_date('05/06/16', 'DD/MM/YY'), 0.94,'JUDET_CONSILIU',141766, 'SM',21 ,1334,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (304,to_date('05/06/16', 'DD/MM/YY'), 1.26,'JUDET_CONSILIU',141766, 'SM',16 ,1790,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (305,to_date('05/06/16', 'DD/MM/YY'), 11.98,'JUDET_CONSILIU',141766, 'SM',1 ,16994,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (306,to_date('05/06/16', 'DD/MM/YY'), 2.11,'JUDET_CONSILIU',141766, 'SM',8 ,2997,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (307,to_date('05/06/16', 'DD/MM/YY'), 18.75,'JUDET_CONSILIU',141766, 'SM',3 ,26590,7,21.21, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (308,to_date('05/06/16', 'DD/MM/YY'), 26.79,'JUDET_CONSILIU',141766, 'SM',4 ,37980,9,27.27, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (309,to_date('05/06/16', 'DD/MM/YY'), 35.84,'JUDET_CONSILIU',141766, 'SM',5 ,50812,13,39.39, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (310,to_date('05/06/16', 'DD/MM/YY'), 2.30,'JUDET_CONSILIU',141766, 'SM',6 ,3269,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (311,to_date('05/06/16', 'DD/MM/YY'), 0.95,'JUDET_CONSILIU',158405, 'SB',21 ,1508,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (312,to_date('05/06/16', 'DD/MM/YY'), 19.41,'JUDET_CONSILIU',158405, 'SB',25 ,30754,8,24.24, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (313,to_date('05/06/16', 'DD/MM/YY'), 3.99,'JUDET_CONSILIU',158405, 'SB',1 ,6325,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (314,to_date('05/06/16', 'DD/MM/YY'), 2.70,'JUDET_CONSILIU',158405, 'SB',19 ,4283,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (315,to_date('05/06/16', 'DD/MM/YY'),4.93,'JUDET_CONSILIU',158405, 'SB',2 ,7823,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (316,to_date('05/06/16', 'DD/MM/YY'),33.61,'JUDET_CONSILIU',158405, 'SB',3 ,53248,13,39.39, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (317,to_date('05/06/16', 'DD/MM/YY'),1.37,'JUDET_CONSILIU',158405, 'SB',11 ,2181,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (318,to_date('05/06/16', 'DD/MM/YY'),1.03,'JUDET_CONSILIU',158405, 'SB',12 ,1636,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (319,to_date('05/06/16', 'DD/MM/YY'),29.00,'JUDET_CONSILIU',158405, 'SB',4 ,45952,12,36.36, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (320,to_date('05/06/16', 'DD/MM/YY'),1.69,'JUDET_CONSILIU',158405, 'SB',14 ,2679,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (321,to_date('05/06/16', 'DD/MM/YY'),1.27,'JUDET_CONSILIU',158405, 'SB',6 ,2016,0,0, 2016);
 
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (322,to_date('05/06/16', 'DD/MM/YY'),4.90,'JUDET_CONSILIU',272921, 'SV',1 ,13380,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (323,to_date('05/06/16', 'DD/MM/YY'),1.83,'JUDET_CONSILIU',272921, 'SV',19 ,5009,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (324,to_date('05/06/16', 'DD/MM/YY'),4.66,'JUDET_CONSILIU',272921, 'SV',2 ,12725,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (325,to_date('05/06/16', 'DD/MM/YY'),48.31,'JUDET_CONSILIU',272921, 'SV',3 ,131875,21,63.63, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (326,to_date('05/06/16', 'DD/MM/YY'),38.82,'JUDET_CONSILIU',272921, 'SV',4,105974,18,48.48, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (327,to_date('05/06/16', 'DD/MM/YY'),1.47,'JUDET_CONSILIU',272921, 'SV',6,3958,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (328,to_date('05/06/16', 'DD/MM/YY'),5.79,'JUDET_CONSILIU',186348, 'TR',1,10804,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (329,to_date('05/06/16', 'DD/MM/YY'),5.29,'JUDET_CONSILIU',186348, 'TR',2,9875,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (330,to_date('05/06/16', 'DD/MM/YY'),31.41,'JUDET_CONSILIU',186348, 'TR',3,58541,11,33.33, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (331,to_date('05/06/16', 'DD/MM/YY'),1.14,'JUDET_CONSILIU',186348, 'TR',12,2143,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (332,to_date('05/06/16', 'DD/MM/YY'),53.22,'JUDET_CONSILIU',186348, 'TR',4,99189,18,54.54, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (333,to_date('05/06/16', 'DD/MM/YY'),3.11,'JUDET_CONSILIU',186348, 'TR',6,5796,0,0, 2016);
 
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (334,to_date('05/06/16', 'DD/MM/YY'),0.82,'JUDET_CONSILIU',239170, 'TM',18,1985,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (335,to_date('05/06/16', 'DD/MM/YY'),5.50,'JUDET_CONSILIU',239170, 'TM',1,13156,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (336,to_date('05/06/16', 'DD/MM/YY'),5.74,'JUDET_CONSILIU',239170, 'TM',2,13752,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (337,to_date('05/06/16', 'DD/MM/YY'),2.22,'JUDET_CONSILIU',239170, 'TM',8,5311,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (338,to_date('05/06/16', 'DD/MM/YY'),1.00,'JUDET_CONSILIU',239170, 'TM',9,2407,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (339,to_date('05/06/16', 'DD/MM/YY'),32.21,'JUDET_CONSILIU',239170, 'TM',3,77060,14,42.42, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (340,to_date('05/06/16', 'DD/MM/YY'),1.38,'JUDET_CONSILIU',239170, 'TM',11,3304,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (341,to_date('05/06/16', 'DD/MM/YY'),1.19,'JUDET_CONSILIU',239170, 'TM',12,2859,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (342,to_date('05/06/16', 'DD/MM/YY'),37.28,'JUDET_CONSILIU',239170, 'TM',4,89166,16,48.48, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (343,to_date('05/06/16', 'DD/MM/YY'),1.43,'JUDET_CONSILIU',239170, 'TM',29,3434,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (344,to_date('05/06/16', 'DD/MM/YY'),4.46,'JUDET_CONSILIU',239170, 'TM',5,10670,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (345,to_date('05/06/16', 'DD/MM/YY'),6.71,'JUDET_CONSILIU',239170, 'TM',6,16066,3,9.09, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (346,to_date('05/06/16', 'DD/MM/YY'),7.86,'JUDET_CONSILIU',90013, 'TL',1,7083,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (347,to_date('05/06/16', 'DD/MM/YY'),6.15,'JUDET_CONSILIU',90013, 'TL',2,5541,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (348,to_date('05/06/16', 'DD/MM/YY'),2.06,'JUDET_CONSILIU',90013, 'TL',9,1855,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (349,to_date('05/06/16', 'DD/MM/YY'),35.50,'JUDET_CONSILIU',90013, 'TL',3,31961,12,36.36, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (350,to_date('05/06/16', 'DD/MM/YY'),2.59,'JUDET_CONSILIU',90013, 'TL',11,2333,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (351,to_date('05/06/16', 'DD/MM/YY'),1.79,'JUDET_CONSILIU',90013, 'TL',12,1612,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (352,to_date('05/06/16', 'DD/MM/YY'),41.52,'JUDET_CONSILIU',90013, 'TL',4,37378,14,42.42, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (353,to_date('05/06/16', 'DD/MM/YY'),2.49,'JUDET_CONSILIU',90013, 'TL',6,2250,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (354,to_date('05/06/16', 'DD/MM/YY'),0.73,'JUDET_CONSILIU',173787, 'VL',21,1277,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (355,to_date('05/06/16', 'DD/MM/YY'),6.98,'JUDET_CONSILIU',173787, 'VL',1,12134,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (356,to_date('05/06/16', 'DD/MM/YY'),8.26,'JUDET_CONSILIU',173787, 'VL',19,14355,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (357,to_date('05/06/16', 'DD/MM/YY'),2.55,'JUDET_CONSILIU',173787, 'VL',2,4444,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (358,to_date('05/06/16', 'DD/MM/YY'),0.65,'JUDET_CONSILIU',173787, 'VL',9,1140,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (359,to_date('05/06/16', 'DD/MM/YY'),34.50,'JUDET_CONSILIU',173787, 'VL',3,59959,13,39.39, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (360,to_date('05/06/16', 'DD/MM/YY'),1.13,'JUDET_CONSILIU',173787, 'VL',11,1972,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (361,to_date('05/06/16', 'DD/MM/YY'),1.13,'JUDET_CONSILIU',173787, 'VL',22,1979,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (362,to_date('05/06/16', 'DD/MM/YY'),2.51,'JUDET_CONSILIU',173787, 'VL',12,4368,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (363,to_date('05/06/16', 'DD/MM/YY'),37.85,'JUDET_CONSILIU',173787, 'VL',4,65786,14,42.42, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (364,to_date('05/06/16', 'DD/MM/YY'),1.49,'JUDET_CONSILIU',173787, 'VL',14,2604,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (365,to_date('05/06/16', 'DD/MM/YY'),0.89,'JUDET_CONSILIU',173787, 'VL',40,1557,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (366,to_date('05/06/16', 'DD/MM/YY'),1.27,'JUDET_CONSILIU',173787, 'VL',6,2212,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (367,to_date('05/06/16', 'DD/MM/YY'),0.44,'JUDET_CONSILIU',160580, 'VS',21,721,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (368,to_date('05/06/16', 'DD/MM/YY'),7.33,'JUDET_CONSILIU',160580, 'VS',1,11784,3,9.09, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (369,to_date('05/06/16', 'DD/MM/YY'),4.80,'JUDET_CONSILIU',160580, 'VS',2,7719,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (370,to_date('05/06/16', 'DD/MM/YY'),1.53,'JUDET_CONSILIU',160580, 'VS',9,2463,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (371,to_date('05/06/16', 'DD/MM/YY'),26.73,'JUDET_CONSILIU',160580, 'VS',3,42934,10,30.30, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (372,to_date('05/06/16', 'DD/MM/YY'),1.32,'JUDET_CONSILIU',160580, 'VS',12,2135,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (373,to_date('05/06/16', 'DD/MM/YY'),53.99,'JUDET_CONSILIU',160580, 'VS',4,86707,20,60.60, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (374,to_date('05/06/16', 'DD/MM/YY'),1.83,'JUDET_CONSILIU',160580, 'VS',14,2949,0,0, 2016); 
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (375,to_date('05/06/16', 'DD/MM/YY'),1.97,'JUDET_CONSILIU',160580, 'VS',6,3168,0,0, 2016); 
 
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (376,to_date('05/06/16', 'DD/MM/YY'),0.94,'JUDET_CONSILIU',152685, 'VN',21,1446,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (377,to_date('05/06/16', 'DD/MM/YY'),5.67,'JUDET_CONSILIU',152685, 'VN',1,8669,2,6.06, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (378,to_date('05/06/16', 'DD/MM/YY'),2.86,'JUDET_CONSILIU',152685, 'VN',2,4368,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (379,to_date('05/06/16', 'DD/MM/YY'),1.47,'JUDET_CONSILIU',152685, 'VN',9,2254,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (380,to_date('05/06/16', 'DD/MM/YY'),34.29,'JUDET_CONSILIU',152685, 'VN',3,52356,13,39.39, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (381,to_date('05/06/16', 'DD/MM/YY'),50.71,'JUDET_CONSILIU',152685, 'VN',4,77437,18,54.54, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (382,to_date('05/06/16', 'DD/MM/YY'),4.03,'JUDET_CONSILIU',152685, 'VN',6,6155,0,0, 2016);

INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (383,to_date('05/06/16', 'DD/MM/YY'),40.42,'JUDET_CONSILIU',566390, 'B',4,228986,24,43.63, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (384,to_date('05/06/16', 'DD/MM/YY'),6.42,'JUDET_CONSILIU',566390, 'B',1,36380,4,7.72, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (385,to_date('05/06/16', 'DD/MM/YY'),2.27,'JUDET_CONSILIU',566390, 'B',27,12896,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (386,to_date('05/06/16', 'DD/MM/YY'),2.22,'JUDET_CONSILIU',566390, 'B',19,12574,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (387,to_date('05/06/16', 'DD/MM/YY'),7.14,'JUDET_CONSILIU',566390, 'B',2,40481,4,7.72, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (388,to_date('05/06/16', 'DD/MM/YY'),0.91,'JUDET_CONSILIU',566390, 'B',8,5172,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (389,to_date('05/06/16', 'DD/MM/YY'),13.07,'JUDET_CONSILIU',566390, 'B',3,74041,8,14.54, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (390,to_date('05/06/16', 'DD/MM/YY'),1.92,'JUDET_CONSILIU',566390, 'B',12,10902,0,0, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (391,to_date('05/06/16', 'DD/MM/YY'),25.34,'JUDET_CONSILIU',566390, 'B',92,143544,15,27.27, 2016);
INSERT INTO partid_rezultat_judet( id, data_alegerilor, procentaj_total_voturi, tip_alegeri, total_voturi_judet,  id_judet, partid, total_voturi_partid,numar_mandate, procentaj_mandate_judet, an_alegeri)
 VALUES (392,to_date('05/06/16', 'DD/MM/YY'),0.24,'JUDET_CONSILIU',566390, 'B',5,1414,0,0, 2016);

  
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (93,FALSE, 'PARTIDUL UNITĂȚII SOCIALE DIN ROMÂNIA', 'PUSR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (92,FALSE, 'PARTIDUL UNIUNEA SALVAȚI BUCUREȘTIUL', 'PUSB');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (91,FALSE, 'PARTIDUL ALIANȚA PENTRU DOMNEȘTI', 'PC');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (90,FALSE, 'PARTIDUL COMUNIŞTILOR', 'PC');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (89,FALSE, 'PARTIDUL SOCIETĂȚII IEȘENE', 'PSI');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (88,FALSE, 'PARTIDUL NOUA GENERAŢIE CREŞTIN DEMOCRAT', 'PNGCD');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (87,FALSE, 'PARTIDUL MIȘCAREA SOCIAL LIBERALĂ', 'PMSL');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (86,FALSE, 'PARTIDUL NOSTRU', 'PN');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (85,FALSE, 'ALIANŢA ELECTORALĂ MPP - EMNP', 'MPPEMP');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (84,FALSE, 'ALIANŢA PENTRU COMUNA NOASTRĂ', 'APCN');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (83,FALSE, 'ALIANŢA ELECTORALĂ TÂRGU SECUIESC', 'AETS');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (82,FALSE, 'PARTIDUL ECOLOGIST SOCIALIST ROMÂN', 'PESR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (81,FALSE, 'ALIANŢA PENTRU BUZĂU', 'APB');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (80,FALSE, 'ALIANȚA ÎMPREUNĂ PENTRU SĂCELE', 'APS');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (79,FALSE, 'ALIANŢA ELECTORALĂ “UNIUNEA PENTRU CODLEA - FORUMUL GERMAN“', 'AEFG');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (78,FALSE, 'ALIANȚA PENTRU HUNEDOARA', 'APH');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (77,FALSE, 'PARTIDUL ROMÂNILOR de PRETUTINDENI', 'PRP');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (76,FALSE, 'PARTIDUL POPORULUI', 'PP');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (75,FALSE, 'PARTIDUL ALIANȚA DEMOCRATĂ A ROMILOR', 'PADR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (74,FALSE, 'PARTIDUL UpR', 'PUPR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (73,FALSE, 'PARTIDUL REPUBLICAN DIN ROMÂNIA', 'PRR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (72,FALSE, 'UNIUNEA POLONEZILOR DIN ROMÂNIA', 'UPR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (71,FALSE, 'UNIUNEA POPULARĂ SOCIAL CREȘTINĂ', 'UPSC');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (70,FALSE, 'PARTIDUL PLATFORMA ACȚIUNEA CIVICĂ A TINERILOR', 'PPACT');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (69,FALSE, 'ALIANȚA PNL – PNȚCD TĂTĂRĂȘTII DE JOS', 'APNT');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (68,FALSE, 'ALIANŢA PENTRU BUZIAŞ', 'APB');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (67,FALSE, 'PARTIDUL RENAȘTEREA SĂCĂLAZULUI', 'PRS');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (66,FALSE, 'ALIANŢA PENTRU VIITOR', 'APV');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (65,FALSE, 'UNIUNEA ELENĂ DIN ROMÂNIA', 'UER');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (64,FALSE, 'UNIUNEA UCRAINENILOR DIN ROMÂNIA', 'CRLR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (63,FALSE, 'COMUNITATEA RUȘILOR LIPOVENI DIN ROMÂNIA', 'CRLR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (62,FALSE, 'PARTIDUL FRONTUL DEMNITĂȚII ȘI IDENTITĂȚII NAȚIONALE', 'PFDIN');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (61,FALSE, 'PARTIDUL ROMILOR DEMOCRAȚI - PRD', 'PRD');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (60,FALSE, 'PARTIDUL FORȚA MOLDOVA', 'PFM');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (59,FALSE, 'PARTIDUL NEOSOCIALIST', 'PN');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (58,FALSE, 'PARTIDUL NOUA DREAPTĂ', 'PND');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (57,FALSE, 'UNIUNEA BULGARĂ DIN BANAT - ROMÂNIA', 'UBBR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (56,FALSE, 'ALIANȚA PENTRU TÎRNOVA', 'APT');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (55,FALSE, 'UNIŢI PENTRU ŞIRIA', 'UPS');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (54,FALSE, 'ALIANȚA PENTRU SECUSIGIU', 'APS');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (53,FALSE, 'UNIUNEA CULTURALĂ A RUTENILOR DIN ROMÂNIA', 'UCRR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (52,FALSE, 'UNIUNEA SÂRBILOR DIN ROMÂNIA', 'USR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (51,FALSE, 'UNIUNEA SALVAŢI SEBIŞUL', 'USS');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (50,FALSE, 'UNIUNEA DEMOCRATICĂ A SLOVACILOR ȘI CEHILOR DIN ROMÂNIA', 'UDSCR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (49,FALSE, 'PARTIDUL ALTERNATIVA DE VEST', 'PAV');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (48,FALSE, 'PARTIDUL “MÂNDRI CĂ SUNTEM ARĂDENI“', 'PMSA');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (47,FALSE, 'ALIANȚA PENTRU SCHIMBARE OLT', 'APSO'); 
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (40,FALSE, 'PARTIDUL SOCIALIST ROMÂN', 'PSR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (33,FALSE, 'ALIANȚA PENTRU UNITATEA RROMILOR', 'APUR');    
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (32,FALSE, 'PARTIDUL CIVIC MAGHIAR – MAGYAR POLGÁRI PÁRT', 'PCM'); 
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (30,FALSE, 'PARTIDUL MIȘCAREA LIBERALĂ', 'PML');  
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (29,FALSE, 'PARTIDUL UNIUNEA CREȘTIN DEMOCRATĂ DIN ROMÂNIA', 'PUCDR');  
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (1,FALSE, 'PARTIDUL ALIANȚA LIBERALILOR ȘI DEMOCRAȚILOR', 'ALDE');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (2,FALSE, 'PARTIDUL MIȘCAREA POPULARĂ', 'PMP');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (3,FALSE, 'PARTIDUL NAȚIONAL LIBERAL', 'PNL');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (4,FALSE, 'PARTIDUL SOCIAL DEMOCRAT', 'PSD');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (5,FALSE, 'UNIUNEA DEMOCRATĂ MAGHIARĂ DIN ROMÂNIA', 'UDMR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (6,FALSE, 'UNIUNEA NAȚIONALĂ PENTRU PROGRESUL ROMÂNIEI', 'UNPR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (7,FALSE, 'ALIANȚA PENTRU ARAD', 'APR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (8,FALSE, 'PARTIDUL NAȚIONAL ȚĂRĂNESC CREȘTIN DEMOCRAT', 'PNTCD');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (9,FALSE, 'PARTIDUL NAȚIONAL DEMOCRAT', 'PND');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (10,FALSE, 'PARTIDUL PENTRU ARGEȘ ȘI MUSCEL', 'PAM');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (11,FALSE, 'PARTIDUL PUTERII UMANISTE (SOCIAL-LIBERAL)', 'PPUSL');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (12,FALSE, 'PARTIDUL ROMÂNIA UNITĂ', 'PRU');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (13,FALSE, 'PARTIDUL M10', 'P10');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (14,FALSE, 'PARTIDUL SOCIAL ROMÂNESC', 'PSR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (15,TRUE, 'ŞOVA SORIN', 'SS');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (16,FALSE, 'ERDÉLYI MAGYAR NÉPPÁRT – PARTIDUL POPULAR MAGHIAR DIN TRANSILVANIA', 'PPMT');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (17,TRUE, 'NEGRUŞERI VASILE', 'NV');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (18,FALSE, 'PARTIDUL ”BLOCUL UNITĂȚII NAȚIONALE”', 'BUN');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (19,FALSE, 'PARTIDUL ECOLOGIST ROMÂN', 'PER');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (20,FALSE, 'PARTIDUL VERDE', 'PV');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (21,FALSE, 'ASOCIAȚIA PARTIDA ROMILOR “PRO-EUROPA”', 'APRPE');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (22,FALSE, 'PARTIDUL ROMÂNIA MARE', 'APRM');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (23,TRUE, 'CĂPĂŢÎNĂ MARIAN', 'CM');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (24,FALSE, 'PARTIDUL AGRICULTORILOR DIN ROMÂNIA', 'PAR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (25,FALSE, 'FORUMUL DEMOCRAT AL GERMANILOR DIN ROMÂNIA', 'FDGR');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (26,TRUE, 'ION MIRCEA', 'IM');	
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (27,FALSE, 'PARTIDUL DREPTĂȚII SOCIALE', 'PDS');	
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (28,TRUE, 'BUDA DORIN-SIMION', 'BDS');		
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (39,TRUE, 'ZAMFIR AUREL', 'ZI');	
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (38,TRUE, 'BUTUNOIU DORIN', 'BD');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (37,TRUE, 'DĂMUREANU RINGO', 'DR'); 
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (36,FALSE, 'ALIANŢA ELECTORALĂ „PENTRU CRAIOVA”', 'AEPC'); 
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (35,TRUE, 'NECULA SILVIU-RĂZVAN', 'NSR'); 
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (34,TRUE, 'MARINESCU IOAN', 'MI'); 
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (41,FALSE, 'ALIANȚA PENTRU HUNEDOARA', 'APH');
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (31,FALSE, 'UNIUNEA DEMOCRATĂ A TĂTARILOR TURCO-MUSULMANI DIN ROMÂNIA', 'UDTTMR'); 
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (42,FALSE, 'PARTIDUL IALOMIȚENILOR', 'PI'); 
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (43,FALSE, 'COALIȚIA PENTRU BAIA MARE', 'CPBM'); 
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (44,FALSE, 'PARTIDUL MARAMUREŞENILOR', 'PM'); 
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (45,TRUE, 'SIBINESCU IONUŢ', 'SI'); 
INSERT INTO PARTID (ID, INDEPENDENT, NUME, SIGLA) VALUES (46,FALSE, 'PARTIDUL OAMENILOR LIBERI', 'POL');