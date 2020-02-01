package ro.stad.online.gesint.web.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.constante.NumarMagic;
import ro.stad.online.gesint.model.dao.StatisticaDAO;
import ro.stad.online.gesint.model.dao.StatisticaJudetDAO;
import ro.stad.online.gesint.model.dto.statistica.StatisticaDTO;
import ro.stad.online.gesint.model.dto.statistica.StatisticaJudetDTO;
import ro.stad.online.gesint.model.dto.statistica.StatisticaJudetMinimDTO;
import ro.stad.online.gesint.model.filters.FiltruStatistica;
import ro.stad.online.gesint.model.filters.FiltruStatisticaJudete;
import ro.stad.online.gesint.persistence.entities.pojo.PersoanaAn;
import ro.stad.online.gesint.util.Utilitati;

/**
 * Controlados pentrupagina home.
 *
 * @author STAD
 */
@Setter
@Getter
@Controller("homeBean")
@Scope(Constante.SESSION)
@Slf4j
public class AcasaBean implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        /**
         * Constant pentru log
         */
        private static final Logger LOG = LoggerFactory.getLogger(AcasaBean.class.getSimpleName());

        /**
         * Variabila pentru datele de cautare ale statisticii.
         */
        private FiltruStatistica filtruStatistica;

        /**
         * Variabila pentru datele de cautare ale statisticii.
         */
        private FiltruStatisticaJudete filterStatisticaJudet;

        /**
         * Variabila pentru datele de cautare ale statisticii.
         */
        private FiltruStatisticaJudete filtruStatisticaJudete;

        /**
         * Variabila pentru List<StatisticaDTO>.
         */
        private List<StatisticaDTO> statisticaDTO;

        /**
         * Variabila pentru serviciul de statistica.
         */
        @Autowired
        private transient StatisticaDAO statisticaService;

        /**
         * Variabila pentru serviciul de statistica.
         */
        @Autowired
        private transient StatisticaJudetDAO statisticaJudetService;

        /**
         * Variabila pentru statisticaDTO.
         */
        private StatisticaDTO statistica;

        /**
         * Variabila pentru chart de tipul pie
         */
        private PieChartModel graficaUserUltimMembru;

        /**
         * Variabila pentru chart de tipul pie
         */
        private PieChartModel graficaUserVarsta;

        /**
         * Variabila pentru chart de tipul pie
         */
        private PieChartModel graficaUserEducatie;

        /**
         * Variabila pentru chart de tipul pie
         */
        private PieChartModel graficaUserSex;

        /**
         * Variabila pentru chart de tipul pie
         */
        private PieChartModel graficaUserZona;

        /**
         * Variabila pentru chart de tipul orizontal
         */
        private HorizontalBarChartModel horizontalBarModel;

        /**
         * Variabila pentru numarul maxim
         */
        private Integer numarMaxim;

        /**
         * Variabila pentru a incarca datele membrilor
         */
        private Map<String, Integer> listaUtilizatori;

        /**
         *
         */
        private BarChartModel barModel;

        /**
         * String
         */
        private String current_date;

        /**
         * Procentaj total membrii
         */
        private Float procentajTotalMembrii;

        /**
         * Procentaj total membrii ultimul an
         */
        private Float procentajTotalMembriiUltimAn;

        /**
         * Variabila de definitie a procentajului
         */
        private String valoare;

        /**
         * Variabila de text
         */
        private String textMaiMare;

        /**
         * Variabila de text
         */
        private String textMaiMareMediu;

        /**
         * Variabila de text
         */
        private String textValorarProcentaj;

        /**
         * Variabila de text
         */
        private List<PersoanaAn> listaUtilizatoriAn;

        /**
         * Variabila procentaj
         */
        private Float procentaj;

        /**
         * Variabila media procentaj anual
         */
        private Float mediaProcentaj;

        /**
         * Variabila listaJudeteSuperior
         */
        private List<StatisticaJudetDTO> listaJudeteSuperior;

        /**
         * Variabila listaJudeteSuperior
         */
        private List<StatisticaJudetDTO> listaJudeteInferior;

        /**
         * Variabila listaJudeteSuperior
         */
        private List<StatisticaJudetDTO> listaJudeteSuperiorProcentaj;

        /**
         * Variabila listaJudeteSuperior
         */
        private List<StatisticaJudetDTO> listaJudeteInferiorProcentaj;

        /**
         * Variabila date
         */
        private StatisticaJudetMinimDTO date;

        /**
         * Variabila date
         */
        private List<StatisticaJudetMinimDTO> listDdate;

        /**
         * Metoda care calculeaza numarul maxim
         *
         */
        private void calculaNumMax() {
                if (numarMaxim < NumarMagic.NUMBERHUNDRED) {
                        numarMaxim = NumarMagic.NUMBERFIFTY + numarMaxim;
                }
                else if (numarMaxim >= NumarMagic.NUMBERHUNDRED && numarMaxim < NumarMagic.NUMBERFIVEHUNDRED) {
                        numarMaxim = NumarMagic.NUMBERTHIRTEEN + numarMaxim;
                }
                else if (numarMaxim >= NumarMagic.NUMBERFIVEHUNDRED && numarMaxim < NumarMagic.NUMBERTHREETHOUSAND) {
                        numarMaxim = NumarMagic.NUMBEREIGHTHUNDRED + numarMaxim;
                }
                else {
                        numarMaxim = NumarMagic.TWENTYFIVEHUNDRED + numarMaxim;
                }
        }

        /**
         * Incarca datele statistice.
         */
        private Date calculScadereDataAn(final Date data, final int numarAn) {
                final Date newDate = data;
                final Calendar calDesde = Calendar.getInstance();
                calDesde.setTime(newDate);
                final Calendar calHasta = Calendar.getInstance();
                final int anulProv = calDesde.get(Calendar.YEAR);
                final int luna = calDesde.get(Calendar.MONTH);
                final int zi = calDesde.get(Calendar.DAY_OF_MONTH);
                final int anFinal = anulProv - numarAn;
                calHasta.set(anFinal, luna, zi);
                final Date dataPana = calHasta.getTime();
                return dataPana;
        }

        /**
         * Incarca datele statistice.
         */
        private Date calculScadereDataLuna(final Date data, final int numarLuna) {
                final Date newDate = data;
                final Calendar calDesde = Calendar.getInstance();
                calDesde.setTime(newDate);
                final Calendar calHasta = Calendar.getInstance();
                final int anFinal = calDesde.get(Calendar.YEAR);
                final int lunaProv = calDesde.get(Calendar.MONTH);
                final int zi = calDesde.get(Calendar.DAY_OF_MONTH);
                final int luna = lunaProv - numarLuna;
                calHasta.set(anFinal, luna, zi);
                final Date dataPana = calHasta.getTime();
                return dataPana;
        }

        /**
         * Metoda care calculeaza media procentaje
         * @return
         */
        private Float calculeazaMediaProcentaje() {
                mediaProcentaj = (listaUtilizatoriAn.get(1).getProcentaj()
                                + listaUtilizatoriAn.get(NumarMagic.NUMBERTWO).getProcentaj()
                                + listaUtilizatoriAn.get(NumarMagic.NUMBERTHREE).getProcentaj()
                                + listaUtilizatoriAn.get(NumarMagic.NUMBERFOUR).getProcentaj()
                                + listaUtilizatoriAn.get(NumarMagic.NUMBERFIVE).getProcentaj()) / NumarMagic.NUMBERFIVE;
                return mediaProcentaj;

        }

        /**
         * Metoda care incarca datele listei anuale
         *
         */
        private void incarcareDateListaAnuala() {
                listaUtilizatoriAn = new ArrayList<>();
                final Calendar calHasta = Calendar.getInstance();
                final int anul = calHasta.get(Calendar.YEAR);
                final Date data = new Date();
                final String numeLuna = (Utilitati.obtinemNumeLuna(data)).substring(0, NumarMagic.NUMBERTHREE);
                final PersoanaAn persoane = new PersoanaAn();
                persoane.setAn(numeLuna + Constante.SPATIUMARE + String.valueOf(anul) + Constante.SAGEATADREAPTA
                                + numeLuna + Constante.SPATIUMARE + String.valueOf(anul - 1));
                persoane.setNumar(statistica.getTotalUltimAn());
                persoane.setProcentaj(procentajTotalMembriiUltimAn);
                listaUtilizatoriAn.add(persoane);
                final PersoanaAn persoane2 = new PersoanaAn();
                persoane2.setAn(numeLuna + Constante.SPATIUMARE + String.valueOf(anul - 1) + Constante.SAGEATADREAPTA
                                + numeLuna + Constante.SPATIUMARE + String.valueOf(anul - NumarMagic.NUMBERTWO));
                persoane2.setNumar(statistica.getTotalUltimiDoiAni());
                persoane2.setProcentaj(obtinereProcentajTotalAn(statistica.getTotalUltimiDoiAni()));
                listaUtilizatoriAn.add(persoane2);
                final PersoanaAn persoane3 = new PersoanaAn();
                persoane3.setAn(numeLuna + Constante.SPATIUMARE + String.valueOf(anul - NumarMagic.NUMBERTWO)
                                + Constante.SAGEATADREAPTA + numeLuna + Constante.SPATIUMARE
                                + String.valueOf(anul - NumarMagic.NUMBERTHREE));
                persoane3.setNumar(statistica.getTotalUltimiiTreiAni());
                persoane3.setProcentaj(obtinereProcentajTotalAn(statistica.getTotalUltimiiTreiAni()));
                listaUtilizatoriAn.add(persoane3);
                final PersoanaAn persoane4 = new PersoanaAn();
                persoane4.setAn(numeLuna + Constante.SPATIUMARE + String.valueOf(anul - NumarMagic.NUMBERTHREE)
                                + Constante.SAGEATADREAPTA + numeLuna + Constante.SPATIUMARE
                                + String.valueOf(anul - NumarMagic.NUMBERFOUR));
                persoane4.setNumar(statistica.getTotalUltimiiPatruAni());
                persoane4.setProcentaj(obtinereProcentajTotalAn(statistica.getTotalUltimiiPatruAni()));
                listaUtilizatoriAn.add(persoane4);
                final PersoanaAn persoane5 = new PersoanaAn();
                persoane5.setAn(numeLuna + Constante.SPATIUMARE + String.valueOf(anul - NumarMagic.NUMBERFOUR)
                                + Constante.SAGEATADREAPTA + numeLuna + Constante.SPATIUMARE
                                + String.valueOf(anul - NumarMagic.NUMBERFIVE));
                persoane5.setNumar(statistica.getTotalUltimiiCinciAni());
                persoane5.setProcentaj(obtinereProcentajTotalAn(statistica.getTotalUltimiiCinciAni()));
                listaUtilizatoriAn.add(persoane5);
                final PersoanaAn persoane6 = new PersoanaAn();
                persoane6.setAn("Anterior " + (anul - NumarMagic.NUMBERFIVE));
                persoane6.setNumar(statistica.getTotalAntCinciAni());
                persoane6.setProcentaj(obtinereProcentajTotalAn(statistica.getTotalAntCinciAni()));
                listaUtilizatoriAn.add(persoane6);
        }

        /**
         *
         * Incarcam datele.
         *
         */
        private void incarcareLista(final StatisticaDTO statistica) {
                listaUtilizatori = new HashMap<>();
                listaUtilizatori.put("Înregistrați în ultima lună:", statistica.getTotalUltimaLuna());
                listaUtilizatori.put("Înregistrați în ultimele trei luni:", statistica.getTotalUltimTreiLuni());
                listaUtilizatori.put("Înregistrați în ultimele sase luni:", statistica.getTotalUltimSaseLuni());
                listaUtilizatori.put("Înregistrați în ultimul an:", statistica.getTotalUltimAn());
        }

        /**
         * Metoda de incarcare de date in filtru
         */
        private void cautareDateFiltru() {
                filtruStatistica.setDataIncepand(new Date());
                filtruStatistica.setDataUltimAn(calculScadereDataAn(filtruStatistica.getDataIncepand(), 1));
                filtruStatistica.setDataUltimiDoiAni(
                                calculScadereDataAn(filtruStatistica.getDataIncepand(), NumarMagic.NUMBERTWO));
                filtruStatistica.setDataUltimiTreiAni(
                                calculScadereDataAn(filtruStatistica.getDataIncepand(), NumarMagic.NUMBERTHREE));
                filtruStatistica.setDataUltimiPatruAni(
                                calculScadereDataAn(filtruStatistica.getDataIncepand(), NumarMagic.NUMBERFOUR));
                filtruStatistica.setDataUltimiCinciAni(
                                calculScadereDataAn(filtruStatistica.getDataIncepand(), NumarMagic.NUMBERFIVE));
                filtruStatistica.setDataUltimaLuna(calculScadereDataLuna(filtruStatistica.getDataIncepand(), 1));
                filtruStatistica.setDataUltimDouaLuni(
                                calculScadereDataLuna(filtruStatistica.getDataIncepand(), NumarMagic.NUMBERTWO));
                filtruStatistica.setDataUltimTreiLuni(
                                calculScadereDataLuna(filtruStatistica.getDataIncepand(), NumarMagic.NUMBERTHREE));
                filtruStatistica.setDataUltimPatruLuni(
                                calculScadereDataLuna(filtruStatistica.getDataIncepand(), NumarMagic.NUMBERFOUR));
                filtruStatistica.setDataUltimCinciLuni(
                                calculScadereDataLuna(filtruStatistica.getDataIncepand(), NumarMagic.NUMBERFIVE));
                filtruStatistica.setDataUltimSaseLuni(
                                calculScadereDataLuna(filtruStatistica.getDataIncepand(), NumarMagic.NUMBERSIX));
                filtruStatistica.setDataUltimSapteLuni(
                                calculScadereDataLuna(filtruStatistica.getDataIncepand(), NumarMagic.NUMBERSEVEN));
                filtruStatistica.setDataUltimOptLuni(
                                calculScadereDataLuna(filtruStatistica.getDataIncepand(), NumarMagic.NUMBEREIGHT));
                filtruStatistica.setDataUltimNouaLuni(
                                calculScadereDataLuna(filtruStatistica.getDataIncepand(), NumarMagic.NUMBERNINE));
                filtruStatistica.setDataUltimZeceLuni(
                                calculScadereDataLuna(filtruStatistica.getDataIncepand(), NumarMagic.NUMBERTEN));
                filtruStatistica.setDataUltimUnspeLuni(
                                calculScadereDataLuna(filtruStatistica.getDataIncepand(), NumarMagic.NUMBERELEVEN));

                filtruStatistica.setDataUltimaLunaAnTrecut(calculScadereDataLuna(filtruStatistica.getDataUltimAn(), 1));
                filtruStatistica.setDataUltimDouaLuniAnTrecut(
                                calculScadereDataLuna(filtruStatistica.getDataUltimAn(), NumarMagic.NUMBERTWO));
                filtruStatistica.setDataUltimTreiLuniAnTrecut(
                                calculScadereDataLuna(filtruStatistica.getDataUltimAn(), NumarMagic.NUMBERTHREE));
                filtruStatistica.setDataUltimPatruLuniAnTrecut(
                                calculScadereDataLuna(filtruStatistica.getDataUltimAn(), NumarMagic.NUMBERFOUR));
                filtruStatistica.setDataUltimCinciLuniAnTrecut(
                                calculScadereDataLuna(filtruStatistica.getDataUltimAn(), NumarMagic.NUMBERFIVE));
                filtruStatistica.setDataUltimSaseLuniAnTrecut(
                                calculScadereDataLuna(filtruStatistica.getDataUltimAn(), NumarMagic.NUMBERSIX));
                filtruStatistica.setDataUltimSapteLuniAnTrecut(
                                calculScadereDataLuna(filtruStatistica.getDataUltimAn(), NumarMagic.NUMBERSEVEN));
                filtruStatistica.setDataUltimOptLuniAnTrecut(
                                calculScadereDataLuna(filtruStatistica.getDataUltimAn(), NumarMagic.NUMBEREIGHT));
                filtruStatistica.setDataUltimNouaLuniAnTrecut(
                                calculScadereDataLuna(filtruStatistica.getDataUltimAn(), NumarMagic.NUMBERNINE));
                filtruStatistica.setDataUltimZeceLuniAnTrecut(
                                calculScadereDataLuna(filtruStatistica.getDataUltimAn(), NumarMagic.NUMBERTEN));
                filtruStatistica.setDataUltimUnspeLuniAnTrecut(
                                calculScadereDataLuna(filtruStatistica.getDataUltimAn(), NumarMagic.NUMBERELEVEN));
                filtruStatistica.setDataPana25(
                                calculScadereDataAn(filtruStatistica.getDataIncepand(), NumarMagic.NUMBERTWENTYFIVE));
                filtruStatistica.setDataPana40(
                                calculScadereDataAn(filtruStatistica.getDataIncepand(), NumarMagic.NUMBERFORTY));
                filtruStatistica.setDataPana60(
                                calculScadereDataAn(filtruStatistica.getDataIncepand(), NumarMagic.NUMBERSIXTY));
        }

        /**
         * Metoda care creaza statistica generala
         */
        private void crearStatisticaGeneral() {
                filtruStatistica = new FiltruStatistica();
                filtruStatisticaJudete = new FiltruStatisticaJudete();
                initListas();
                statistica = new StatisticaDTO();
                cautareDateFiltru();
                obtinemDate();
                obtinereProcentajTotal();
                dataString();
                obtinereText();
                obtinereTextMediu();
                incarcareLista(statistica);
                obtinereValoareProcentaj();
                obtinereProcentajTotalUltimulAn();
                createBarModel();
                createPieModels();
                createHorizontalBarModel();
                incarcareDateListaAnuala();
                calculeazaMediaProcentaje();
                obtinereTextProcentaj();
                obtinereStatisticaSupInf();
        }

        /**
         *
         * Metoda care creaza bar model
         *
         */
        private void createBarModel() {
                barModel = initBarModel();
                barModel.setTitle("Comparativă Prezent/Anul trecut");
                barModel.setLegendPosition("w");
                numarMaxim = 0;
                final Axis xAxis = barModel.getAxis(AxisType.X);
                xAxis.setLabel("Perioada");

                final Axis yAxis = barModel.getAxis(AxisType.Y);
                yAxis.setLabel("Membrii");
                yAxis.setMin(0);
                if (statistica.getTotalUltimiDoiAni() > statistica.getTotalUltimAn()) {
                        numarMaxim = statistica.getTotalUltimiDoiAni();
                }
                else {
                        numarMaxim = statistica.getTotalUltimAn();
                }
                calculaNumMax();
                yAxis.setMax(numarMaxim);
        }

        /**
         * Metoda care incarca datele in orizontal mar model de chart
         */
        private void createHorizontalBarModel() {
                horizontalBarModel = new HorizontalBarChartModel();
                numarMaxim = 0;
                final ChartSeries boys = new ChartSeries();
                boys.setLabel("Membrii noi");
                boys.set(Utilitati.luna(getFecha(filtruStatistica.getDataIncepand())), statistica.getTotalUltimaLuna());
                numarMaxim = statistica.getTotalUltimaLuna();
                boys.set(Utilitati.luna(getFecha(filtruStatistica.getDataUltimaLuna())),
                                statistica.getTotalUltimDouaLuni());
                if (statistica.getTotalUltimDouaLuni() > numarMaxim) {
                        numarMaxim = statistica.getTotalUltimDouaLuni();
                }
                boys.set(Utilitati.luna(getFecha(filtruStatistica.getDataUltimDouaLuni())),
                                statistica.getTotalUltimTreiLuni());
                if (statistica.getTotalUltimTreiLuni() > numarMaxim) {
                        numarMaxim = statistica.getTotalUltimTreiLuni();
                }
                boys.set(Utilitati.luna(getFecha(filtruStatistica.getDataUltimTreiLuni())),
                                statistica.getTotalUltimPatruLuni());
                ;
                if (statistica.getTotalUltimPatruLuni() > numarMaxim) {
                        numarMaxim = statistica.getTotalUltimPatruLuni();
                }
                boys.set(Utilitati.luna(getFecha(filtruStatistica.getDataUltimPatruLuni())),
                                statistica.getTotalUltimCinciLuni());
                if (statistica.getTotalUltimCinciLuni() > numarMaxim) {
                        numarMaxim = statistica.getTotalUltimCinciLuni();
                }
                boys.set(Utilitati.luna(getFecha(filtruStatistica.getDataUltimCinciLuni())),
                                statistica.getTotalUltimSaseLuni());
                if (statistica.getTotalUltimSaseLuni() > numarMaxim) {
                        numarMaxim = statistica.getTotalUltimSaseLuni();
                }
                boys.set(Utilitati.luna(getFecha(filtruStatistica.getDataUltimSaseLuni())),
                                statistica.getTotalUltimSapteLuni());
                if (statistica.getTotalUltimSapteLuni() > numarMaxim) {
                        numarMaxim = statistica.getTotalUltimSapteLuni();
                }
                boys.set(Utilitati.luna(getFecha(filtruStatistica.getDataUltimSapteLuni())),
                                statistica.getTotalUltimOptLuni());
                if (statistica.getTotalUltimOptLuni() > numarMaxim) {
                        numarMaxim = statistica.getTotalUltimOptLuni();
                }
                boys.set(Utilitati.luna(getFecha(filtruStatistica.getDataUltimOptLuni())),
                                statistica.getTotalUltimNouaLuni());
                if (statistica.getTotalUltimNouaLuni() > numarMaxim) {
                        numarMaxim = statistica.getTotalUltimNouaLuni();
                }
                boys.set(Utilitati.luna(getFecha(filtruStatistica.getDataUltimNouaLuni())),
                                statistica.getTotalUltimZeceLuni());
                if (statistica.getTotalUltimZeceLuni() > numarMaxim) {
                        numarMaxim = statistica.getTotalUltimZeceLuni();
                }
                boys.set(Utilitati.luna(getFecha(filtruStatistica.getDataUltimZeceLuni())),
                                statistica.getTotalUltimUnspeLuni());
                if (statistica.getTotalUltimUnspeLuni() > numarMaxim) {
                        numarMaxim = statistica.getTotalUltimAn();
                }
                boys.set(Utilitati.luna(getFecha(filtruStatistica.getDataUltimUnspeLuni())),
                                statistica.getTotalLuna12());
                horizontalBarModel.addSeries(boys);
                horizontalBarModel.setLegendPosition("e");
                horizontalBarModel.setStacked(true);

                final Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
                xAxis.setLabel("Membrii");
                xAxis.setMin(0);
                calculaNumMax();

                xAxis.setMax(numarMaxim);

                final Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
                yAxis.setLabel("Lună");
                log.debug("Orixontal bar: " + boys);
                LOG.info("Orixontal bar: " + String.valueOf(boys));
        }

        /**
         * Incarcam datele in modelul pie
         *
         *
         */
        private void createPieModel2() {
                graficaUserUltimMembru = new PieChartModel();
                graficaUserUltimMembru.set("Înregistrați în ultima lună:  " + statistica.getTotalUltimaLuna(),
                                statistica.getTotalUltimaLuna());
                graficaUserUltimMembru.set("Înregistrați în ultimele trei luni:  " + statistica.getTotalUltimTreiLuni(),
                                statistica.getTotalUltimTreiLuni() - statistica.getTotalUltimaLuna());
                graficaUserUltimMembru.set("Înregistrați în ultimele sase luni:  " + statistica.getTotalUltimSaseLuni(),
                                statistica.getTotalUltimSaseLuni() - statistica.getTotalUltimTreiLuni());
                graficaUserUltimMembru.set("Înregistrați în ultimul an: " + statistica.getTotalUltimAn(),
                                statistica.getTotalUltimAn() - statistica.getTotalUltimSaseLuni());
                graficaUserUltimMembru.setTitle("Membrii noi înregistrați");
                graficaUserUltimMembru.setLegendPosition("e");
                graficaUserUltimMembru.setFill(false);
                graficaUserUltimMembru.setShowDataLabels(true);
                graficaUserUltimMembru.setDiameter(150);
                graficaUserUltimMembru.setShadow(false);
        }

        /**
         * Incarcam datele in modelul pie
         *
         *
         */
        private void createPieModel3() {
                graficaUserVarsta = new PieChartModel();
                graficaUserVarsta.set("Până în 25 de ani: ", statistica.getTotalPana25());
                graficaUserVarsta.set("Între 25 și 40 de ani: ", statistica.getTotalPana40());
                graficaUserVarsta.set("Între 40 și 60 de ani: ", statistica.getTotalPana60());
                graficaUserVarsta.set("Peste 60 de ani: ", statistica.getTotalPanaMayor60());
                graficaUserVarsta.setLegendPosition("ne");
                graficaUserVarsta.setFill(false);
                graficaUserVarsta.setShowDataLabels(true);
                graficaUserVarsta.setDiameter(150);
                graficaUserVarsta.setShadow(false);
        }

        /**
         * Incarcam datele in modelul pie
         *
         *
         */
        private void createPieModel4() {
                graficaUserEducatie = new PieChartModel();
                graficaUserEducatie.set("Studii bazice: ", statistica.getTotalBazice());
                graficaUserEducatie.set("Studii medii: ", statistica.getTotalcuLiceu());
                graficaUserEducatie.set("Studii superioare: ", statistica.getTotalStudiiSup());
                graficaUserEducatie.setLegendPosition("ne");
                graficaUserEducatie.setFill(false);
                graficaUserEducatie.setShowDataLabels(true);
                graficaUserEducatie.setDiameter(150);
                graficaUserEducatie.setShadow(false);
        }

        /**
         * Metoda de incarcare date in chart de tipul pie
         *
         *
         */
        private void createPieModels() {
                createPieModelSex();
                createPieModelZona();
                createPieModel2();
                createPieModel3();
                createPieModel4();
        }

        /**
         * Incarcam datele in modelul pie
         *
         *
         */
        private void createPieModelSex() {
                graficaUserSex = new PieChartModel();
                graficaUserSex.set("Femei:  " + statistica.getTotalFemei(), statistica.getTotalFemei());
                graficaUserSex.set("Bărbați:  " + statistica.getTotalBarbati(), statistica.getTotalBarbati());
                graficaUserSex.setLegendPosition("ne");
                graficaUserSex.setFill(false);
                graficaUserSex.setShowDataLabels(true);
                graficaUserSex.setDiameter(150);
                graficaUserSex.setShadow(false);
        }

        /**
         * Incarcam datele in modelul pie
         *
         *
         */
        private void createPieModelZona() {
                graficaUserZona = new PieChartModel();
                graficaUserZona.set("Rural:  " + statistica.getMediuRural(), statistica.getMediuRural());
                graficaUserZona.set("Urban:  " + statistica.getMediuUrban(), statistica.getMediuUrban());
                graficaUserZona.setLegendPosition("ne");
                graficaUserZona.setFill(false);
                graficaUserZona.setShowDataLabels(true);
                graficaUserZona.setDiameter(150);
                graficaUserZona.setShadow(false);
        }

        /**
         * Transformar date in String
         *
         */
        private void dataString() {
                final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                current_date = df.format(filtruStatistica.getDataIncepand());
        }

        /**
         * Método inicializador del bean.
         */
        @PostConstruct
        public void init() {
                crearStatisticaGeneral();

        }

        /**
         * Model de chart statistica
         * @return
         *
         */
        private BarChartModel initBarModel() {
                final BarChartModel model = new BarChartModel();
                final ChartSeries boys = new ChartSeries();
                boys.setLabel("Membri anul în curs");
                boys.set("Ultima lună", statistica.getTotalUltimaLuna());
                boys.set("Ultimele trei luni", statistica.getTotalUltimTreiLuni());
                boys.set("Ultimele șase luni", statistica.getTotalUltimSaseLuni());
                boys.set("Ultimul an", statistica.getTotalUltimAn());
                final ChartSeries girls = new ChartSeries();
                girls.setLabel("Membri anul trecut");
                girls.set("Luna corespondiente a anului trecut", statistica.getTotalUltimaLunaAnAtras());
                girls.set("Ultimele trei luni ale anului trecut", statistica.getTotalUltimTreiLuniAnAtras());
                girls.set("Ultimele șase luni ale anului trecut", statistica.getTotalUltimSaseLuniAnAtras());
                girls.set("Anul trecut", statistica.getTotalUltimiDoiAni());
                model.addSeries(boys);
                model.addSeries(girls);
                return model;
        }

        /**
         * Instantiat listele
         */
        private void initListas() {
                statisticaDTO = new ArrayList<>();
                listaJudeteSuperior = new ArrayList<>();
                listaJudeteInferior = new ArrayList<>();
                listaJudeteSuperiorProcentaj = new ArrayList<>();
                listaJudeteInferiorProcentaj = new ArrayList<>();
        }

        /**
         * Obtinem valoarea procentajului
         *
         */
        private void obtinereValoareProcentaj() {
                valoare = Constante.SPATIU;
                if (procentajTotalMembrii >= 0.40) {
                        valoare = "EXCELENT";
                }
                else if (procentajTotalMembrii < 0.40 && procentajTotalMembrii >= 0.25) {
                        valoare = Constante.BUN;
                }
                else if (procentajTotalMembrii < 0.25 && procentajTotalMembrii >= 0.10) {
                        valoare = Constante.ACCEPTABIL;
                }
                else {
                        valoare = Constante.NECONVINGATOR;
                }
        }

        /**
         * Metoda care obtine cele mai slabe judete
         */
        private void obtinereJudetInferior() {
                filtruStatisticaJudete.setDescendent(Constante.ASC);
                listaJudeteInferior = statisticaJudetService.filterStatisticaJudet(filtruStatisticaJudete);
        }

        /**
         * Metoda care obtine cele mai slabe judete
         */
        private void obtinereJudetInferiorProcentaj() {
                filtruStatisticaJudete.setDescendent(Constante.ASC);
                filtruStatisticaJudete.setGeneralJudetProcentaj(Constante.NO);
                listaJudeteInferiorProcentaj = statisticaJudetService
                                .filterStatisticaJudetProcentaj(filtruStatisticaJudete);
        }

        /**
         * Metoda care obtine cele mai bune judete
         */
        private void obtinereJudetSuperior() {
                filtruStatisticaJudete.setDescendent(Constante.DESC);
                listaJudeteSuperior = statisticaJudetService.filterStatisticaJudet(filtruStatisticaJudete);
        }

        /**
         * Metoda care obtine cele mai bune judete
         */
        private void obtinereJudetSuperiorProcentaj() {
                filtruStatisticaJudete.setDescendent(Constante.DESC);
                filtruStatisticaJudete.setGeneralJudetProcentaj(Constante.NO);
                listaJudeteSuperiorProcentaj = statisticaJudetService
                                .filterStatisticaJudetProcentaj(filtruStatisticaJudete);
        }

        /**
         * Metoda care obtine valorile minime
         */
        private void obtinereMinime() {
                listDdate = new ArrayList<>();
                listDdate = statisticaJudetService.dateMinime();
                date = listDdate.get(0);
        }

        /**
         *
         * Metoda care calculeaza procentajul total de membrii
         */
        private void obtinereProcentajTotal() {
                final int num = statistica.getNumarTotal() * NumarMagic.NUMBERHUNDRED;
                final float div = ((float) num / statistica.getTotalVot());
                final float divFinal = Math.round(div * NumarMagic.NUMBERHUNDRED) / 100f;
                procentajTotalMembrii = divFinal;
        }

        /**
         *
         * Metoda care calculeaza procentajul total de membrii ultimul an
         */
        private Float obtinereProcentajTotalAn(final int an) {
                final int num = an * NumarMagic.NUMBERHUNDRED;
                final float div = ((float) num / statistica.getNumarTotal());
                final float divFinal = Math.round(div * NumarMagic.NUMBERHUNDRED) / 100f;
                return divFinal;
        }

        /**
         *
         * Metoda care calculeaza procentajul total de membrii ultimul an
         */
        private void obtinereProcentajTotalUltimulAn() {
                final int num = statistica.getTotalUltimAn() * NumarMagic.NUMBERHUNDRED;
                final float div = ((float) num / statistica.getNumarTotal());
                final float divFinal = Math.round(div * NumarMagic.NUMBERHUNDRED) / 100f;
                procentajTotalMembriiUltimAn = divFinal;
        }

        /**
         * Metoda care obtine cele mai bune si slabe judete
         */
        private void obtinereStatisticaSupInf() {
                obtinereJudetSuperior();
                obtinereJudetInferior();
                obtinereMinime();
                obtinereJudetSuperiorProcentaj();
                obtinereJudetInferiorProcentaj();
        }

        /**
         *
         * Metoda care selecteaza textul
         */
        private void obtinereText() {
                textMaiMare = Constante.SPATIU;
                if (statistica.getTotalBarbati() > statistica.getTotalFemei()) {
                        textMaiMare = Constante.ESTEMARE;
                }
                else {
                        textMaiMare = Constante.ESTEMIC;
                }
        }

        /**
         *
         * Metoda care selecteaza textul
         */
        private void obtinereTextMediu() {
                textMaiMareMediu = Constante.SPATIU;
                if (statistica.getMediuRural() > statistica.getMediuUrban()) {
                        textMaiMareMediu = Constante.ESTEMARE;
                }
                else {
                        textMaiMareMediu = Constante.ESTEMIC;
                }
        }

        /**
         * Metoda care obtine textul procentajului
         */
        private void obtinereTextProcentaj() {
                if (mediaProcentaj > procentajTotalMembriiUltimAn) {
                        textValorarProcentaj = "SCADERE";
                        if ((mediaProcentaj - procentajTotalMembriiUltimAn) > 3) {
                                textValorarProcentaj = "SCADERE IMPORTANTĂ";
                        }
                }
                else {
                        textValorarProcentaj = "CREȘTERE";
                        if ((procentajTotalMembriiUltimAn - mediaProcentaj) > 3) {
                                textValorarProcentaj = "CREȘTERE IMPORTANTĂ";
                        }
                }
        }

        /**
         * Metoda care obtine datele
         */
        private void obtinemDate() {
                statisticaDTO = statisticaService.filterGeneraleStatistica(filtruStatistica);
                if (!statisticaDTO.isEmpty()) {
                        statistica = statisticaDTO.get(0);
                }
        }

        /**
         * Metoda care obtine data in format text
         * @param date
         * @return
         */
        public static String getFecha(Date date) {
                final Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                final SimpleDateFormat df = new SimpleDateFormat("MMMMM");
                date = cal.getTime();
                return df.format(date);
        }

}
