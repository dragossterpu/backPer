package ro.per.online.services;

import java.util.List;

import ro.per.online.persistence.entities.Team;
import ro.per.online.persistence.entities.Users;

/**
 * Interfață pentru serviciul de Team.
 *
 * @author STAD
 *
 */
public interface TeamService {

	/**
	 * Cauta toate functiile
	 *
	 * @return lista de functii.
	 */
	List<Team> fiindByTeam();

	/**
	 * Elimina un membru al echipei de conducere
	 * 
	 * @param team membru al echipei de conducere
	 */
	void delete(Team team);

	/**
	 * Salvați sau actualizați un team.
	 * 
	 * @param team
	 * @return Team actualizat
	 */
	Team save(Team team);

	/**
	 * Obtinem nivelul cel mai mare
	 * 
	 * @param team
	 * @return Team actualizat
	 */
	List<Team> findAllByOrderByRankDesc();

	/**
	 * 
	 * @param user Users
	 * @return boolean
	 */
	boolean existsByUser(Users user);

	/**
	 * Cauta un registru in baza de date primind ca parametru membrul echipei
	 * @param team
	 * @return
	 */
	Team findByUser(Users team);

}