package cat.inspla.ra3.reserves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServeiReservesOptimitzacioTest {

    private ServeiReserves servei;

    @BeforeEach
    void setUp() {
        servei = new ServeiReserves();
        servei.afegirRecurs(new Aula("Zebra", 10));
        servei.afegirRecurs(new Aula("Mango", 20));
        servei.afegirRecurs(new Aula("Arbre", 30));
    }

    // NORMAL: retorna els recursos ordenats alfabèticament
    // 13
    @Test
    void obtenirRecursosOrdenatsPerNom_recursosDesordenats_retornaOrdreAlfabetic() {
        List<Reservable> ordenats = servei.obtenirRecursosOrdenatsPerNom();
        assertEquals("Arbre", ordenats.get(0).getNom());
        assertEquals("Mango", ordenats.get(1).getNom());
        assertEquals("Zebra", ordenats.get(2).getNom());
    }

    // LÍMIT: la llista original no es veu modificada després d'ordenar
    // 14
    @Test
    void obtenirRecursosOrdenatsPerNom_noModificaLlistaOriginal() {
        servei.obtenirRecursosOrdenatsPerNom();
        // l'ordre original era Zebra, Mango, Arbre
        assertEquals("Zebra", servei.getRecursos().get(0).getNom());
    }

    // NORMAL + ERROR: l'informe conté nom, tipus i estat correctament per a disponible i reservat
    // 15
    @Test
    void generarInformeRecursos_disponibleIReservat_contéNomTipusIEstat() {
        ServeiReserves s = new ServeiReserves();
        Aula disponible = new Aula("Lab 1", 10);
        Aula reservada  = new Aula("Lab 2", 10);
        reservada.reservar();
        s.afegirRecurs(disponible);
        s.afegirRecurs(reservada);

        String informe = s.generarInformeRecursos();

        assertTrue(informe.contains("Lab 1"));
        assertTrue(informe.contains("Disponible"));
        assertTrue(informe.contains("Lab 2"));
        assertTrue(informe.contains("Reservat"));
    }
}