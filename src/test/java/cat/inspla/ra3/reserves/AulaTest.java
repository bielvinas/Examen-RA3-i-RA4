package cat.inspla.ra3.reserves;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AulaTest {

    private Aula aula;

    @BeforeEach
    void setUp() {
        aula = new Aula("Aula 101", 30);
    }

    // NORMAL: constructor crea l'aula correctament
    // 1
    @Test
    void constructor_nomICapacitatValids_assignaValorsCorrectament() {
        assertEquals("Aula 101", aula.getNom());
        assertEquals(30, aula.getCapacitat());
        assertTrue(aula.estaDisponible());
    }

    // NORMAL: reserva i alliberament bàsics
    // 2
    @Test
    void reservar_iAlliberar_canviaEstatCorrectament() {
        aula.reservar();
        assertFalse(aula.estaDisponible());
        aula.alliberar();
        assertTrue(aula.estaDisponible());
    }

    // LÍMIT: capacitat mínima vàlida (1)
    // 3
    @Test
    void constructor_capacitatMinima1_creaAulaSenseExcepcio() {
        Aula minima = new Aula("Aula Mínima", 1);
        assertEquals(1, minima.getCapacitat());
    }

    // LÍMIT: calcularCost amb 1 hora (valor mínim vàlid)
    // 4
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 10})
    void calcularCostReserva_horesValides_retornaHoresPerDotze(int hores) {
        assertEquals(hores * 12.0, aula.calcularCostReserva(hores));
    }

    // ERROR: capacitat 0 no és vàlida
    // 5 — assertThrows #1
    @Test
    void constructor_capacitatZero_llancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Aula("Aula X", 0));
    }

    // ERROR: nom en blanc no és vàlid
    // 6 — assertThrows #2
    @Test
    void constructor_nomBuit_llancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Aula("   ", 10));
    }

    // ERROR: reservar una aula ja reservada
    // 7 — assertThrows #3
    @Test
    void reservar_aulaJaReservada_llancaIllegalStateException() {
        aula.reservar();
        assertThrows(IllegalStateException.class,
                () -> aula.reservar());
    }
}