package cat.inspla.ra3.reserves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServeiReservesTest {

    private ServeiReserves servei;

    @BeforeEach
    void setUp() {
        servei = new ServeiReserves();
    }

    // NORMAL: afegir un recurs incrementa la llista
    // 8
    @Test
    void afegirRecurs_unRecurs_incrementaLlistaAMida1() {
        servei.afegirRecurs(new Aula("Aula A", 20));
        assertEquals(1, servei.getRecursos().size());
    }

    // NORMAL: buscar per nom és insensible a majúscules
    // 9
    @Test
    void buscarPerNom_nomEnMinuscules_retornaRecursCorrecte() {
        servei.afegirRecurs(new Aula("Aula A", 20));
        Reservable trobat = servei.buscarPerNom("aula a");
        assertNotNull(trobat);
        assertEquals("Aula A", trobat.getNom());
    }

    // LÍMIT: servei sense recursos, comptarDisponibles retorna 0
    // 10
    @Test
    void comptarDisponibles_senseCap_retornaZero() {
        assertEquals(0, servei.comptarDisponibles());
    }

    // LÍMIT: buscar un nom que no existeix retorna null (sense excepció)
    // 11
    @Test
    void buscarPerNom_nomInexistent_retornaNull() {
        servei.afegirRecurs(new Aula("Aula A", 20));
        assertNull(servei.buscarPerNom("Aula Z"));
    }

    // ERROR: afegir recurs nul llança excepció
    // 12 — assertThrows #4
    @Test
    void afegirRecurs_recursNul_llancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> servei.afegirRecurs(null));
    }
}