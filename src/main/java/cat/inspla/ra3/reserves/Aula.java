package cat.inspla.ra3.reserves;

// TODO RA4: afegeix una capçalera Javadoc de classe que expliqui la responsabilitat de la classe Aula.
public class Aula implements Reservable {
    private final String nom;
    private final int capacitat;
    private boolean disponible;

    // TODO RA4: documenta el constructor amb @param i @throws.
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

    // TODO RA4: documenta què passa si l'aula ja està reservada.
    @Override
    public void reservar() {
        if (!disponible) {
            throw new IllegalStateException("El recurs ja està reservat");
        }
        disponible = false;
    }

    @Override
    public void alliberar() { disponible = true; }

    // TODO RA4: documenta el càlcul del cost, el paràmetre hores, el retorn i les excepcions.
    @Override
    public double calcularCostReserva(int hores) {
        validarHores(hores);
        return hores * 12.0;
    }

    protected void validarHores(int hores) {
        if (hores <= 0) {
            throw new IllegalArgumentException("Les hores han de ser positives");
        }
    }
}
