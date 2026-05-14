package cat.inspla.ra3.reserves;

/**
 * Fitxer: Aula.java
 * Autor: Biel Viñas
 * Data: 2026-05-14
 * Descripcio: Representa una aula física reservable. Gestiona la seva
 * disponibilitat i calcula el cost de reserva en funció de les hores.
 */
public class Aula implements Reservable {
    private final String nom;
    private final int capacitat;
    private boolean disponible;

    /**
     * Constructor de la classe Aula.
     * Inicialitza les variables nom i capacitat. A més, comprova que els camps siguin correctes amb el throw.
     *
     * @param nom      nom de l'aula, no pot ser nul ni buit
     * @param capacitat nombre màxim de persones, ha de ser positiu
     * @throws IllegalArgumentException si el nom és nul/buit o la capacitat és zero o negativa
     */
    public Aula(String nom, int capacitat) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("El nom de l'aula és obligatori");
        }
        if (capacitat <= 0) {
            throw new IllegalArgumentException("La capacitat ha de ser positiva");
        }
        this.nom = nom;
        this.capacitat = capacitat;
        this.disponible = true;
    }

    @Override
    public String getNom() { return nom; }

    @Override
    public TipusRecurs getTipus() { return TipusRecurs.AULA; }

    @Override
    public int getCapacitat() { return capacitat; }

    @Override
    public boolean estaDisponible() { return disponible; }

    /**
     * Marca l'aula com a reservada.
     * @throws IllegalStateException si l'aula ja està reservada
     */
    @Override
    public void reservar() {
        if (!disponible) {
            throw new IllegalStateException("El recurs ja està reservat");
        }
        disponible = false;
    }

    /** Allibera l'aula i la torna a deixar disponible. */
    @Override
    public void alliberar() { disponible = true; }

    /**
     * Calcula el cost de reservar l'aula durant un nombre d'hores.
     * El preu és de 12,00 € per hora.
     *
     * @param hores nombre d'hores de la reserva, ha de ser positiu
     * @return cost total en euros
     * @throws IllegalArgumentException si les hores són zero o negatives
     */
    @Override
    public double calcularCostReserva(int hores) {
        validarHores(hores);
        return hores * 12.0;
    }

    /**
     * Comprova que el nombre d'hores sigui vàlid.
     *
     * @param hores nombre d'hores a validar
     * @throws IllegalArgumentException si les hores són zero o negatives
     */
    protected void validarHores(int hores) {
        if (hores <= 0) {
            throw new IllegalArgumentException("Les hores han de ser positives");
        }
    }
}