package ro.stad.online.gesint.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.constante.NumarMagic;
import ro.stad.online.gesint.model.dao.StatisticaJudetDAO;
import ro.stad.online.gesint.model.dao.StatisticaLocalitateDAO;
import ro.stad.online.gesint.model.dto.statistica.StatisticaJudetDTO;
import ro.stad.online.gesint.model.dto.statistica.StatisticaLocalitateDTO;
import ro.stad.online.gesint.model.filters.FiltruStatisticaJudete;
import ro.stad.online.gesint.model.filters.FiltruUtilizator;
import ro.stad.online.gesint.persistence.entities.Optiune;
import ro.stad.online.gesint.persistence.entities.ParamEchipa;
import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.persistence.entities.Statistica;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.services.EchipaService;
import ro.stad.online.gesint.services.OptiuneService;
import ro.stad.online.gesint.services.ParamEchipaService;
import ro.stad.online.gesint.services.ProvinciaService;
import ro.stad.online.gesint.services.StatisticaService;
import ro.stad.online.gesint.services.UtilizatorService;

/**
 * Clase utilizada pentru a incarca statistica
 *
 * @author STAD
 *
 */
@Component("statisticaBean")
@Setter
@Getter
@NoArgsConstructor
@Scope(Constante.SESSION)
public class StatisticaBean implements Serializable {

        /**
         * Constanta log
         */
        private static final Logger LOG = LoggerFactory.getLogger(StatisticaBean.class.getSimpleName());

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        /**
         * Variable map
         */
        public static Map<String, String> mapa;

        /**
         * Lista registrelor din mapa.
         */
        private List<Statistica> listaJudete;

        /**
         * Lista registrelor de optiuni.
         */
        private List<Optiune> listaOptiuni;

        /**
         * Service de Statistica.
         */
        @Autowired
        private transient StatisticaService statisticaService;

        /**
         * Service de Optiune.
         */
        @Autowired
        private transient OptiuneService optiuneService;

        /**
         * Variabila pentru numarul total de membrii
         */
        private Long rowCount;

        /**
         * Service de utilizatori.
         */
        @Autowired
        private UtilizatorService utilizatorService;

        /**
         * Filtru de cautare utilizator.
         */
        private FiltruUtilizator filtruUtilizator;

        /**
         * Lista Booleans pentru controlul afișării coloanelor din pagina.
         */
        private List<Boolean> list;

        /**
         * Variabila listaJudeteSuperior
         */
        private List<StatisticaJudetDTO> listaJudeteSuperiorProcentaj;

        /**
         * Variabila listaLocalitati Superior
         */
        private List<StatisticaLocalitateDTO> listaLocalitatiSuperiorProcentaj;

        /**
         * Variabila listaJudeteSuperior
         */
        private List<StatisticaJudetDTO> listaJudSuprProCautare;

        /**
         * Variabila listaJudeteSuperior
         */
        private List<StatisticaLocalitateDTO> listaLocSuprProCautare;

        /**
         * Variabila pentru serviciul de statistica.
         */
        @Autowired
        private transient StatisticaJudetDAO statisticaJudetService;

        /**
         * Variabila pentru serviciul de statistica.
         */
        @Autowired
        private transient StatisticaLocalitateDAO statisticaLocalitateService;

        /**
         * Variabila pentru datele de cautare ale statisticii.
         */
        private FiltruStatisticaJudete filtruStatisticaJudete;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaAB;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaAG;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaB;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaIF;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaAR;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaBC;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaBH;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaBN;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaBR;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaBV;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaBZ;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaCJ;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaCL;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaCT;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaCS;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaCV;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaDB;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaDJ;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaGJ;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaGL;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaGR;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaHD;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaHR;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaIL;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaIS;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaMH;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaMM;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaMS;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaNT;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaOT;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaPH;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaSB;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaSJ;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaSM;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaSV;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaTL;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaTM;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaTR;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaVL;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaVN;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaVS;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaBT;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLAB;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLAG;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLB;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLIF;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLAR;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLBC;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLBH;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLBN;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLBR;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLBV;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLBZ;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLCJ;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLCL;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLCT;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLCS;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLCV;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLDB;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLDJ;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLGJ;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLGL;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLGR;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLHD;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLHR;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLIL;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLIS;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLMH;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLMM;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLMS;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLNT;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLOT;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLPH;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLSB;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLSJ;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLSM;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLSV;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLTL;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLTM;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLTR;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLVL;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLVN;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLVS;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String etichetaLBT;

        /**
         * Variabila pentru culoarea etichetei sectorului
         */
        private String etichetaLS1;

        /**
         * Variabila pentru culoarea etichetei sectorului
         */
        private String etichetaLS2;

        /**
         * Variabila pentru culoarea etichetei sectorului
         */
        private String etichetaLS3;

        /**
         * Variabila pentru culoarea etichetei sectorului
         */
        private String etichetaLS4;

        /**
         * Variabila pentru culoarea etichetei sectorului
         */
        private String etichetaLS5;

        /**
         * Variabila pentru culoarea etichetei sectorului
         */
        private String etichetaLS6;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private String numeOrganizatie;

        /**
         * Variabila pentru culoarea etichetei judetului
         */
        private Integer totalMembrii;

        /**
         * Presedinte Filiala.
         */
        private Utilizator presedinteFiliala;

        /**
         * Conducere Filiala.
         */
        private List<Utilizator> conducereFiliala;

        /**
         * Variabila utilizata pentru a injecta serviciul functilor
         *
         */
        @Autowired
        private ParamEchipaService pteamService;

        /**
         * Variabila utilizata pentru a injecta serviciul provinciei.
         *
         */
        @Autowired
        private ProvinciaService provinciaService;

        /**
         * Variabila pentru a vizualiza stema judetului
         */
        private String codulProvinciei;

        /**
         * Variabila pentru judet
         */
        private Provincia judet;

        /**
         * Variabila pentru a verifica daca prezentam svg-ul Bucurestiului
         */
        private String esteBucuresti;

        /**
         * Service de functii.
         */
        @Autowired
        private EchipaService echipaService;

        /**
         * Metoda care obtine cele mai bune si slabe localitati
         * @param cod String
         * @return pagina statisticaJudete
         */
        public String buttonAction(final String cod) {
                LOG.info("buttonAction.String cod: " + cod);
                esteBucuresti = Constante.NU;
                if (cod.equals(Constante.B)) {
                        esteBucuresti = "DA";
                }
                codulProvinciei = Constante.SPATIU;
                presedinteFiliala = new Utilizator();
                conducereFiliala = new ArrayList<>();
                listaOptiuni = new ArrayList<>();

                filtruUtilizator = new FiltruUtilizator();
                judet = new Provincia();
                ParamEchipa team = new ParamEchipa();
                final Long idTeam = NumarMagic.NUMBERTWENTYONELONG;
                team = echipaService.findOne(idTeam);
                obtinereLocalitateSupProcentaj(cod);
                final Provincia prov = provinciaService.findById(cod);
                listaOptiuni = optiuneService.findByCodeProvince(prov);
                judet = prov;
                presedinteFiliala = utilizatorService.findByTeamAndProvince(team, prov);
                List<ParamEchipa> lista = new ArrayList<>();
                lista = incarcamToateFunctileLocale();
                conducereFiliala = utilizatorService.findByProvinceAndTeam(prov, lista);
                codulProvinciei = cod;
                return "/estadisticas/statisticaJudete.xhtml?faces-redirect=true";
        }

        /**
         *
         * Metoda care incarca etichetele
         * @param map Map<String, String>
         */
        public void incarcareEtichete(final Map<String, String> mapa) {
                for (final Map.Entry<String, String> entry : mapa.entrySet()) {
                        if (entry.getKey().equals(Constante.AB)) {
                                etichetaAB = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.AG)) {
                                etichetaAG = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.B)) {
                                etichetaB = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.BT)) {
                                etichetaBT = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.IF)) {
                                etichetaIF = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.AR)) {
                                etichetaAR = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.BC)) {
                                etichetaBC = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.BH)) {
                                etichetaBH = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.BN)) {
                                etichetaBN = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.BR)) {
                                etichetaBR = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.BZ)) {
                                etichetaBZ = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.BV)) {
                                etichetaBV = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.CJ)) {
                                etichetaCJ = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.CL)) {
                                etichetaCL = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.CS)) {
                                etichetaCS = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.CT)) {
                                etichetaCT = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.CV)) {
                                etichetaCV = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.DB)) {
                                etichetaDB = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.DJ)) {
                                etichetaDJ = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.GJ)) {
                                etichetaGJ = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.GL)) {
                                etichetaGL = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.GR)) {
                                etichetaGR = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.HD)) {
                                etichetaHD = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.HR)) {
                                etichetaHR = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.IL)) {
                                etichetaIL = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.IS)) {
                                etichetaIS = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.MH)) {
                                etichetaMH = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.MM)) {
                                etichetaMM = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.MS)) {
                                etichetaMS = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.NT)) {
                                etichetaNT = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.OT)) {
                                etichetaOT = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.PH)) {
                                etichetaPH = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.SB)) {
                                etichetaSB = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.SJ)) {
                                etichetaSJ = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.SM)) {
                                etichetaSM = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.SV)) {
                                etichetaSV = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.TL)) {
                                etichetaTL = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.TM)) {
                                etichetaTM = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.TR)) {
                                etichetaTR = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.VL)) {
                                etichetaVL = entry.getValue();
                        }
                        else if (entry.getKey().equals(Constante.VN)) {
                                etichetaVN = entry.getValue();
                        }
                        else {
                                etichetaVS = entry.getValue();
                        }
                }

        }

        /**
         * Metoda care incarca etichetele
         * @param map Map<String, String>
         * @param cod String
         */
        public void incarcareEticheteJudet(final Map<String, String> map, final String cod) {
                for (final Map.Entry<String, String> entry : mapa.entrySet()) {
                        if (entry.getKey().equals(Constante.AB)) {
                                if (cod.equals(Constante.AB)) {
                                        etichetaLAB = entry.getValue();
                                }
                                else {
                                        etichetaLAB = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.AG)) {
                                if (cod.equals(Constante.AG)) {
                                        etichetaLAG = entry.getValue();
                                }
                                else {
                                        etichetaLAG = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.B)) {
                                if (cod.equals(Constante.B)) {
                                        etichetaLB = entry.getValue();
                                }
                                else {
                                        etichetaLB = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.BT)) {
                                if (cod.equals(Constante.BT)) {
                                        etichetaLBT = entry.getValue();
                                }
                                else {
                                        etichetaLBT = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.IF)) {
                                if (cod.equals(Constante.IF)) {
                                        etichetaLIF = entry.getValue();
                                }
                                else {
                                        etichetaLIF = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.AR)) {
                                if (cod.equals(Constante.AR)) {
                                        etichetaLAR = entry.getValue();
                                }
                                else {
                                        etichetaLAR = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.BC)) {
                                if (cod.equals(Constante.BC)) {
                                        etichetaLBC = entry.getValue();
                                }
                                else {
                                        etichetaLBC = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.BH)) {
                                if (cod.equals(Constante.BH)) {
                                        etichetaLBH = entry.getValue();
                                }
                                else {
                                        etichetaLBH = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.BN)) {
                                if (cod.equals(Constante.BN)) {
                                        etichetaLBN = entry.getValue();
                                }
                                else {
                                        etichetaLBN = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.BR)) {
                                if (cod.equals(Constante.BR)) {
                                        etichetaLBR = entry.getValue();
                                }
                                else {
                                        etichetaLBR = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.BZ)) {
                                if (cod.equals(Constante.BZ)) {
                                        etichetaLBZ = entry.getValue();
                                }
                                else {
                                        etichetaLBZ = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.BV)) {
                                if (cod.equals(Constante.BV)) {
                                        etichetaLBV = entry.getValue();
                                }
                                else {
                                        etichetaLBV = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.CJ)) {
                                if (cod.equals(Constante.CJ)) {
                                        etichetaLCJ = entry.getValue();
                                }
                                else {
                                        etichetaLCJ = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.CL)) {
                                if (cod.equals(Constante.CL)) {
                                        etichetaLCL = entry.getValue();
                                }
                                else {
                                        etichetaLCL = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.CS)) {
                                if (cod.equals(Constante.CS)) {
                                        etichetaLCS = entry.getValue();
                                }
                                else {
                                        etichetaLCS = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.CT)) {
                                if (cod.equals(Constante.CT)) {
                                        etichetaLCT = entry.getValue();
                                }
                                else {
                                        etichetaLCT = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.CV)) {
                                if (cod.equals(Constante.CV)) {
                                        etichetaLCV = entry.getValue();
                                }
                                else {
                                        etichetaLCV = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.DB)) {
                                if (cod.equals(Constante.DB)) {
                                        etichetaLDB = entry.getValue();
                                }
                                else {
                                        etichetaLDB = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.DJ)) {
                                if (cod.equals(Constante.DJ)) {
                                        etichetaLDJ = entry.getValue();
                                }
                                else {
                                        etichetaLDJ = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.GJ)) {
                                if (cod.equals(Constante.GJ)) {
                                        etichetaLGJ = entry.getValue();
                                }
                                else {
                                        etichetaLGJ = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.GL)) {
                                if (cod.equals(Constante.GL)) {
                                        etichetaLGL = entry.getValue();
                                }
                                else {
                                        etichetaLGL = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.GR)) {
                                if (cod.equals(Constante.GR)) {
                                        etichetaLGR = entry.getValue();
                                }
                                else {
                                        etichetaLGR = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.HD)) {
                                if (cod.equals(Constante.HD)) {
                                        etichetaLHD = entry.getValue();
                                }
                                else {
                                        etichetaLHD = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.HR)) {
                                if (cod.equals(Constante.HR)) {
                                        etichetaLHR = entry.getValue();
                                }
                                else {
                                        etichetaLHR = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.IL)) {
                                if (cod.equals(Constante.IL)) {
                                        etichetaLIL = entry.getValue();
                                }
                                else {
                                        etichetaLIL = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.IS)) {
                                if (cod.equals(Constante.IS)) {
                                        etichetaLIS = entry.getValue();
                                }
                                else {
                                        etichetaLIS = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.MH)) {
                                if (cod.equals(Constante.MH)) {
                                        etichetaLMH = entry.getValue();
                                }
                                else {
                                        etichetaLMH = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.MM)) {
                                if (cod.equals(Constante.MM)) {
                                        etichetaLMM = entry.getValue();
                                }
                                else {
                                        etichetaLMM = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.MS)) {
                                if (cod.equals(Constante.MS)) {
                                        etichetaLMS = entry.getValue();
                                }
                                else {
                                        etichetaLMS = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.NT)) {
                                if (cod.equals(Constante.NT)) {
                                        etichetaLNT = entry.getValue();
                                }
                                else {
                                        etichetaLNT = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.OT)) {
                                if (cod.equals(Constante.OT)) {
                                        etichetaLOT = entry.getValue();
                                }
                                else {
                                        etichetaLOT = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.PH)) {
                                if (cod.equals(Constante.PH)) {
                                        etichetaLPH = entry.getValue();
                                }
                                else {
                                        etichetaLPH = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.SB)) {
                                if (cod.equals(Constante.SB)) {
                                        etichetaLSB = entry.getValue();
                                }
                                else {
                                        etichetaLSB = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.SJ)) {
                                if (cod.equals(Constante.SJ)) {
                                        etichetaLSJ = entry.getValue();
                                }
                                else {
                                        etichetaLSJ = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.SM)) {
                                if (cod.equals(Constante.SM)) {
                                        etichetaLSM = entry.getValue();
                                }
                                else {
                                        etichetaLSM = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.SV)) {
                                if (cod.equals(Constante.SV)) {
                                        etichetaLSV = entry.getValue();
                                }
                                else {
                                        etichetaLSV = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.TL)) {
                                if (cod.equals(Constante.TL)) {
                                        etichetaLTL = entry.getValue();
                                }
                                else {
                                        etichetaLTL = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.TM)) {
                                if (cod.equals(Constante.TM)) {
                                        etichetaLTM = entry.getValue();
                                }
                                else {
                                        etichetaLTM = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.TR)) {
                                if (cod.equals(Constante.TR)) {
                                        etichetaLTR = entry.getValue();
                                }
                                else {
                                        etichetaLTR = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.VL)) {
                                if (cod.equals(Constante.VL)) {
                                        etichetaLVL = entry.getValue();
                                }
                                else {
                                        etichetaLVL = Constante.FILLWHITE;
                                }
                        }
                        else if (entry.getKey().equals(Constante.VN)) {
                                if (cod.equals(Constante.VN)) {
                                        etichetaLVN = entry.getValue();
                                }
                                else {
                                        etichetaLVN = Constante.FILLWHITE;
                                }
                        }
                        else {
                                if (cod.equals(Constante.VS)) {
                                        etichetaLVS = entry.getValue();
                                }
                                else {
                                        etichetaLVS = Constante.FILLWHITE;
                                }
                        }
                }

        }

        /**
         * mETODA CARE INCARCA ETICHETELE PE SECTOARE
         * @param localitate
         */
        private void incarcareEticheteSectoare(final StatisticaLocalitateDTO localitate) {
                if (localitate.getSector() != null && localitate.getCodJudet().equals(Constante.B)) {

                        if (localitate.getSector().equals("Sector 5")) {
                                if (localitate.getEticheta().equals(Constante.VERDE)) {
                                        etichetaLS5 = Constante.FILLGREEN;
                                }
                                else if (localitate.getEticheta().equals(Constante.GALBEN)) {
                                        etichetaLS5 = Constante.FILLYELLOW;
                                }
                                else {
                                        etichetaLS5 = Constante.FILLRED;
                                }
                        }
                        if (localitate.getSector().equals("Sector 4")) {
                                if (localitate.getEticheta().equals(Constante.VERDE)) {
                                        etichetaLS4 = Constante.FILLGREEN;
                                }
                                else if (localitate.getEticheta().equals(Constante.GALBEN)) {
                                        etichetaLS4 = Constante.FILLYELLOW;
                                }
                                else {
                                        etichetaLS4 = Constante.FILLRED;
                                }
                        }
                        if (localitate.getSector().equals("Sector 3")) {
                                if (localitate.getEticheta().equals(Constante.VERDE)) {
                                        etichetaLS3 = Constante.FILLGREEN;
                                }
                                else if (localitate.getEticheta().equals(Constante.GALBEN)) {
                                        etichetaLS3 = Constante.FILLYELLOW;
                                }
                                else {
                                        etichetaLS3 = Constante.FILLRED;
                                }
                        }
                        if (localitate.getSector().equals("Sector 2")) {
                                if (localitate.getEticheta().equals(Constante.VERDE)) {
                                        etichetaLS2 = Constante.FILLGREEN;
                                }
                                else if (localitate.getEticheta().equals(Constante.GALBEN)) {
                                        etichetaLS2 = Constante.FILLYELLOW;
                                }
                                else {
                                        etichetaLS2 = Constante.FILLRED;
                                }
                        }
                        if (localitate.getSector().equals("Sector 1")) {
                                if (localitate.getEticheta().equals(Constante.VERDE)) {
                                        etichetaLS1 = Constante.FILLGREEN;
                                }
                                else if (localitate.getEticheta().equals(Constante.GALBEN)) {
                                        etichetaLS1 = Constante.FILLYELLOW;
                                }
                                else {
                                        etichetaLS1 = Constante.FILLRED;
                                }
                        }
                        if (localitate.getSector().equals("Sector 6")) {
                                if (localitate.getEticheta().equals(Constante.VERDE)) {
                                        etichetaLS6 = Constante.FILLGREEN;
                                }
                                else if (localitate.getEticheta().equals(Constante.GALBEN)) {
                                        etichetaLS6 = Constante.FILLYELLOW;
                                }
                                else {
                                        etichetaLS6 = Constante.FILLRED;
                                }
                        }
                }
        }

        /**
         * Inițializarea datelor.
         */
        @PostConstruct
        public void init() {
                listaJudeteSuperiorProcentaj = new ArrayList<>();
                listaLocalitatiSuperiorProcentaj = new ArrayList<>();
                filtruStatisticaJudete = new FiltruStatisticaJudete();
                numeOrganizatie = Constante.SPATIU;
                obtinereStatisticaSupInf();
                rowCount = utilizatorService.findCount();
                esteBucuresti = Constante.NU;
        }

        /**
         * Metoda care obtine cele mai bune judete
         */
        private void obtinereJudetSuperiorProcentaj() {
                mapa = new HashMap<>();
                listaJudeteSuperiorProcentaj = new ArrayList<>();
                filtruStatisticaJudete.setDescendent(Constante.DESC);
                filtruStatisticaJudete.setGeneralJudetProcentaj("SI");
                listaJudSuprProCautare = statisticaJudetService.filterStatisticaJudetProcentaj(filtruStatisticaJudete);
                LOG.info("obtinereJudetSuperiorProcentaj.listaJudSuprProCautare.size: "
                                + listaJudSuprProCautare.size());
                for (final StatisticaJudetDTO judet : listaJudSuprProCautare) {
                        judet.setValoare(obtenerValoare(judet.getProcentaj()));
                        if (judet.getValoare().equals(Constante.BUN)) {
                                judet.setEticheta(Constante.VERDE);
                                mapa.put(judet.getCodJudet(), Constante.FILLGREEN);
                        }
                        else if (judet.getValoare().equals(Constante.ACCEPTABIL)) {
                                judet.setEticheta(Constante.GALBEN);
                                mapa.put(judet.getCodJudet(), Constante.FILLYELLOW);

                        }
                        else {
                                judet.setEticheta(Constante.ROSU);
                                mapa.put(judet.getCodJudet(), Constante.FILLRED);
                        }
                        listaJudeteSuperiorProcentaj.add(judet);
                }
                incarcareEtichete(mapa);
        }

        /**
         * Metoda care obtine cele mai bune judete
         * @param cod String
         */
        private void obtinereLocalitateSupProcentaj(final String cod) {
                numeOrganizatie = Constante.SPATIU;
                listaLocalitatiSuperiorProcentaj = new ArrayList<>();
                filtruStatisticaJudete.setCode_province(cod);
                listaLocSuprProCautare = statisticaLocalitateService
                                .filterStatisticaLocalitateProcentaj(filtruStatisticaJudete);
                LOG.info("obtinereLocalitateSupProcentaj.listaLocSuprProCautare.size: "
                                + listaLocSuprProCautare.size());
                numeOrganizatie = listaLocSuprProCautare.get(0).getNumeProvincie();
                totalMembrii = listaLocSuprProCautare.get(0).getTotalMembrii();
                for (final StatisticaLocalitateDTO localitate : listaLocSuprProCautare) {

                        if (localitate.getSector() != null) {
                                localitate.setNumeLocalitate(localitate.getSector());
                        }
                        localitate.setValoare(obtenerValoare(localitate.getProcentaj()));
                        if (localitate.getValoare().equals(Constante.BUN)) {
                                localitate.setEticheta(Constante.VERDE);
                        }
                        else if (localitate.getValoare().equals(Constante.ACCEPTABIL)) {
                                localitate.setEticheta(Constante.GALBEN);
                        }
                        else {
                                localitate.setEticheta(Constante.ROSU);

                        }
                        incarcareEticheteSectoare(localitate);

                        if (localitate.getSector() != null || !localitate.getCodJudet().equals(Constante.B)) {
                                listaLocalitatiSuperiorProcentaj.add(localitate);
                        }
                }
                incarcareEticheteJudet(mapa, cod);
        }

        /**
         * Metoda care obtine cele mai bune si slabe judete
         */
        private void obtinereStatisticaSupInf() {
                obtinereJudetSuperiorProcentaj();
        }

        /**
         *
         */
        private String obtenerValoare(final Float procentaj) {
                String valoare = Constante.SPATIU;
                if (procentaj >= NumarMagic.NUMBERFIFTY) {
                        valoare = Constante.BUN;
                }
                else if (procentaj < NumarMagic.NUMBERFIFTY && procentaj >= NumarMagic.NUMBERTHIRTY) {
                        valoare = Constante.ACCEPTABIL;
                }
                else {
                        valoare = Constante.NECONVINGATOR;
                }
                return valoare;
        }

        /**
         * Metoda care incarca functiile locale
         */
        private List<ParamEchipa> incarcamToateFunctileLocale() {
                List<ParamEchipa> listaFunc = new ArrayList<>();
                listaFunc = pteamService.fiindAllByParam();
                for (final ParamEchipa functia : listaFunc) {
                        if (functia.getId() == NumarMagic.NUMBERTWENTYONELONG) {
                                listaFunc.remove(functia);
                                break;
                        }
                }
                return listaFunc;
        }
}
