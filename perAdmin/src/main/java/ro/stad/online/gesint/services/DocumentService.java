package ro.stad.online.gesint.services;

import java.util.List;

import org.hibernate.Criteria;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;

import ro.stad.online.gesint.exceptions.GesintException;
import ro.stad.online.gesint.persistence.entities.Alerta;
import ro.stad.online.gesint.persistence.entities.Documentul;
import ro.stad.online.gesint.persistence.entities.TipDocument;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.web.beans.gd.FiltruDocument;

/**
 * Interfața serviciului Documente.
 *
 * @author STAD
 *
 */
public interface DocumentService {

        /**
         * Returnează documentele care corespund tipului de document.
         * @param tipoDocumento Numele tipului de document
         * @return Lista documentelor
         */
        List<Documentul> cautaNumeTipDocument(String tipoDocumento);

        /**
         * Returnează documentele care corespund alertei.
         * @param alerta Alerta
         * @return Lista documentelor
         */
        List<Documentul> findByAlerta(Alerta alerta);

        /**
         * Consulta en base de datos en base a los parámetros recibidos. La consulta se hace paginada. Cautare în baza
         * de date pe baza parametrilor primiți. Rezultatul este paginat.
         * @param first Primul element din căutare
         * @param pageSize Numărul maxim de rezultate de afișat
         * @param sortField Câmpul prin care se ordonează căutarea
         * @param sortOrder ordine de sortare
         * @param busqueda Obiect care conține criteriile de căutare
         * @return Lista documentelor care corespund criteriilor primite
         *
         */
        List<Documentul> cautareDocumentCriteria(int first, int pageSize, String sortField, SortOrder sortOrder,
                        FiltruDocument busqueda);

        /**
         * Recupereaza un tip de document după numele său.
         * @param nombre numele tipului
         * @return tipul documentului
         */
        TipDocument cautareTipDocumentNume(String nombre);

        /**
         * Primește un fișier UploadedFile din care preia datele pentru a genera un document care va fi stocat în baza
         * de date. Returnează documentul stocat
         * @param file fișier pentru a încărca în DB
         * @param tipo tipul de document
         * @param usuario utilizator asociat cu documentul
         * @return Documentul documentul încărcat în baza de date
         * @throws GesintException Excepție posibilă
         *
         */

        Documentul cargaDocumento(UploadedFile file, TipDocument tipo, Utilizator usuario) throws GesintException;

        /**
         * Primeste un fisier UploadedFile si datele necesare unui document general, dar nu-l stocheaza in baza de date.
         * Doar lăsați obiectul gata să îl salveze.
         * @param file fișier pentru a încărca în DB
         * @param tipo tipul de document
         * @param usuario utilizator asociat cu documentul
         * @return documento documentul încărcat în baza de date
         * @throws GesintException Excepție posibilă
         */
        Documentul incarcareDocumentFaraSalvare(UploadedFile file, TipDocument tipo, Utilizator usuario) throws GesintException;

        /**
         * Eliminare de documente din baza de date. Documentul care trebuie eliminat este primit ca parametru.
         * @param entity Documentul pentru eliminare
         *
         */

        void delete(Documentul entity);

        /**
         * Primiți un document ca parametru și returnați un stream(flux) pentru a efectua descărcarea.
         * @param entity Document pentru descărcare
         * @return DefaultStreamed Content Descărcați fluxul
         * @throws GesintException Excepție posibilă
         */

        DefaultStreamedContent descarcareDocument(Documentul entity) throws GesintException;

        /**
         * Primește id-ul unui document ca parametru și returnează un flux pentru efectuarea descărcării.
         * @param id Id document pentru descărcare
         * @return DefaultStreamedContent Descărcați fluxul
         * @throws GesintException Excepție posibilă
         */

        DefaultStreamedContent descarcareDocument(Long id) throws GesintException;

        /**
         * Căutați toate documentele care au fost eliminate logic.
         * @return Lista documentelor selectate
         */

        List<Documentul> findByFechaBajaIsNotNull();

        /**
         * Căutați toate documentele care nu au fost eliminate logic.
         * @return Lista documentelor selectate
         */

        List<Documentul> findByFechaBajaIsNull();

        /**
         * Returnează un document localizat după id-ul său.
         * @param id Identificatorul documentului
         * @return Documentul
         */
        Documentul findOne(Long id);

        /**
         * Verificați numărul de înregistrări din baza de date care corespund criteriilor de căutare.
         * @param busqueda Obiect care conține criteriile de căutare
         * @return numărul de înregistrări corespunzătoare căutării
         */
        int getCounCriteria(FiltruDocument busqueda);

        /**
         * Returnează lista tipurilor de documente.
         * @return lista tipurilor de documente.
         */
        List<TipDocument> listaTipuriDocumente();

        /**
         * Returnează numele fișierului conținut în obiectul Document.
         * @param documentul din care doriți să extrageți numele fișierului conținut
         * @return nume de fișier
         */
        String obtieneNumeFisier(Documentul document);

        /**
         *
         * @param criteria
         * @param first
         * @param pageSize
         * @param sortField
         * @param sortOrder
         * @param defaultField
         */
        void pregatestePaginacreOrdenCriteria(Criteria criteria, int first, int pageSize, String sortField,
                        SortOrder sortOrder, String defaultField);

        /**
         * Preluați un document din coșul de gunoi
         * @param documento Documentul care trebuie recuperat din coșul de gunoi
         */
        void recupereazaDocument(Documentul document);

        /**
         * Salvați un document în baza de date. Ca parametru, primește documentul care trebuie salvat și returnează
         * documentul salvat.
         * @param entity Documentul Document pentru salvare
         * @return Documentul Document salvat
         */

        Documentul save(Documentul entity);

        /**
         * Salvează documente în baza de date. Ca parametru, primește documentele care trebuie salvate și returnează
         * documentele salvate.
         * @param entities Documente de salvat
         * @return Lista documentelor salvate
         *
         */

        Iterable<Documentul> save(Iterable<Documentul> entities);

        /**
         * Ștergeți toate documentele stocate în coșul de gunoi.
         * @return Lista documentelor eliminate
         */
        List<Documentul> golesteCosulGunoi();

}
