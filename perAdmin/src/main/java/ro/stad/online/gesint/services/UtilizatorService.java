package ro.stad.online.gesint.services;

import java.io.IOException;
import java.util.List;

import org.primefaces.model.SortOrder;

import ro.stad.online.gesint.model.filters.FiltruEchipa;
import ro.stad.online.gesint.model.filters.FiltruUtilizator;
import ro.stad.online.gesint.persistence.entities.Localitate;
import ro.stad.online.gesint.persistence.entities.ParamEchipa;
import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.persistence.entities.pojo.AnNumarPojo;

/**
 * Interfață pentru serviciul de Echipa.
 *
 * @author STAD
 *
 */
public interface UtilizatorService {

        /**
         * Elimina de manera lógica a una lista de usuarios.
         * @param listaUtilizatori Lista de usuarios a modificar
         * @return lista de usuarios modificada
         */
        List<Utilizator> bajaLogica(List<String> listaUtilizatori);

        /**
         * Devuelve una lista con nombres de los usuarios que estén presentes en la lista y en BBDD.
         * @param listaNombres lista de nombres que se buscarán en bbdd
         * @return Lista de nombres de usuarios presentes en la BBDD
         */
        List<String> cautareListaNumeUtilizator(List<String> listaNombres);

        /**
         * Devuelve una lista de usuarios.
         * @param filtruUtilizator Objeto que contiene los parámetros de búsqueda
         * @return Listado resultante de la búsqueda
         */
        List<Utilizator> cautareUtilizator(FiltruUtilizator filtruUtilizator);

        /**
         * Busca usuarios con los parametros de búsqueda.
         * @param searchUser AnNumarPojo
         * @param sortOrder SortOrder
         * @param sortField String
         * @param pageSize int
         * @param first int
         * @return List<User>
         */
        List<Utilizator> cautareUtilizatorCriteria(int first, int pageSize, String sortField, SortOrder sortOrder,
                        FiltruUtilizator searchUser);

        /**
         * Busca usuarios utilizando criteria.
         * @param filtruUtilizator AnNumarPojo
         * @return List<User>
         */
        List<Utilizator> cautareUtilizatorCriteria(FiltruUtilizator filtruUtilizator);

        /**
         * Busca usuarios utilizando criteria.
         * @param usuarioBusqueda AnNumarPojo
         * @return List<User>
         */
        List<Utilizator> cautareUtilizatorCriteriaLocal(FiltruEchipa filtruEchipa);

        /**
         * Incarcam fotografia unui utilizator.
         * @param usuario a eliminar
         * @throws IOException
         */

        Utilizator incarcareImaginaFaraStocare(byte[] bs, Utilizator user) throws IOException;

        /**
         * Eliminación de un usuario.
         * @param usuario a eliminar
         */
        void delete(Utilizator usuario);

        /**
         * Establece una lista de usuarios como inactivo.
         * @param listaUtilizatori Lista de usuarios a modificar
         * @return lista de usuarios modificada
         */
        List<Utilizator> desactivar(List<String> listaUtilizatori);

        /**
         * Cauta totii utilizatorii
         * @return lista de utilizatori.
         */
        List<Utilizator> fiindAll();

        /**
         * Cautaun utilizator dupa id.
         * @return Utilizator userlista de utilizatori.
         */
        Utilizator fiindOne(String id);

        /**
         * Căutați o listă de utilizatori prin poșta sau documentul de identitate, ignorând majuscule.
         *
         * @param email email
         * @param cnp
         * @return rezultatul căutării
         *
         */
        List<Utilizator> findByCorreoIgnoreCaseOrDocIdentidadIgnoreCase(String nif, String cnp);

        /**
         * Căutați un utilizator cu CNP.
         * @param cnp String - cnp-ul utilizatorului
         * @return User
         */
        Utilizator findByIdCard(String cnp);

        /**
         * Căutați un utilizator dupa localitate.
         * @param Long id
         * @return List<Utilizator>
         */
        List<Utilizator> findByLocality(Localitate loca);

        /**
         * Devuelve una lista de destinatari externi de PER.
         * @return Listado resultante de la búsqueda
         */
        List<Utilizator> findByName();

        /**
         * Cauta un utilizator cu rolul si judetul.
         * @param rol RolEnum
         * @param prov Provincia
         * @return User
         */
        // List<Utilizator> findByProvinceAndRol(Provincia prov, List<RolEnum> roles);

        /**
         * @param prov
         * @param listaTeam
         * @return lista
         *
         */
        List<Utilizator> findByProvinceAndTeam(Provincia prov, List<ParamEchipa> listaTeam);

        /**
         * Metocare cauta presedintele organizatiei
         * @param l
         * @return
         *
         */
        Utilizator findByTeam(ParamEchipa idTeam);

        /**
         * Cauta un utilizator cu rolul si judetul.
         * @param rol RolEnum
         * @param prov Provincia
         * @return User
         */
        Utilizator findByTeamAndProvince(ParamEchipa team, Provincia prov);

        /**
         * @return
         *
         */
        Long findCount();

        /**
         * @param sex
         * @param desde
         * @param hasta
         * @return long count
         */
        int findUsersBySex(AnNumarPojo membru);

        /**
         * Obtiene el conteo de criteria.
         * @param searchUser AnNumarPojo
         * @return int
         */
        int getCounCriteria(FiltruUtilizator searchUser);

        /**
         * Guarda una lista de usuarios.
         *
         * @param usuarios lista
         * @return lista de usuarios
         */
        List<Utilizator> salvat(List<Utilizator> usuarios);

        /**
         * Guarda en BBDD un listado de usuarios provisionales.
         * 
         * @param listadoUsuariosProvisionales listardo de usuarios provisionales
         * @return lista de usuarios grabados
         * 
         */
        List<Utilizator> save(List<Utilizator> listadoUsuariosProvisionales);

        /**
         * Guarda en base de datos el usuario.
         * @param entity Usuario a guardar.
         * @return Usuario salvat.
         */
        Utilizator save(Utilizator entity);
}
