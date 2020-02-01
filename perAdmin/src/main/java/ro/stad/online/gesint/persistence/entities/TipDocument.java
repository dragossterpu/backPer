package ro.stad.online.gesint.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * Entitate pentru a stoca tipurile posibile de documente care vor fi gestionate de la managerul de documente.
 * 
 * @author STAD
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@Builder
@Getter
@Setter
@Entity
@Table(name = "tipDocument")
public class TipDocument implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * Identificatorul entității.
         */
        @Id
        @Column(name = "id")
        Long id;

        /**
         * Denumirea tipului de document.
         */
        @Column(name = "nume")
        String nume;

        /**
         * Suprascrierea metodei toString pentru a folosi SelectItemsConvertor într-un mod generic, întorcând
         * întotdeauna cheia primară.
         */
        @Override
        public String toString() {
                return id.toString();
        }
}
