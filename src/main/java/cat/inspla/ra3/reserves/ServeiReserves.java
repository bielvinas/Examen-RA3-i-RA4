package cat.inspla.ra3.reserves;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ServeiReserves {
    private final List<Reservable> recursos = new ArrayList<>();

    public void afegirRecurs(Reservable recurs) {
        if (recurs == null) {
            throw new IllegalArgumentException("El recurs no pot ser nul");
        }
        recursos.add(recurs);
    }

    public List<Reservable> getRecursos() {
        return Collections.unmodifiableList(recursos);
    }

    public double calcularCostTotal(int hores) {
        if (hores <= 0) {
            throw new IllegalArgumentException("Les hores han de ser positives");
        }
        double total = 0;
        for (Reservable recurs : recursos) {
            total += recurs.calcularCostReserva(hores);
        }
        return total;
    }

    public long comptarDisponibles() {
        return recursos.stream().filter(Reservable::estaDisponible).count();
    }

    public Reservable buscarPerNom(String nom) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("El nom de cerca és obligatori");
        }
        return recursos.stream()
                .filter(r -> r.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);
    }

    /**
     * TODO RA4: aquest mètode funciona, però està fet expressament de manera poc eficient.
     * Cal optimitzar-lo utilitzant eines adequades del llenguatge Java.
     * Explicació:
     * El codi original feia servir un bubble sort manual, cosa que era molt poc optima i molt lenta. En canvi, el metode de sort() de java és molt més ràpid i eficient que el bubble sort manual.
     */
    public List<Reservable> obtenirRecursosOrdenatsPerNom() {
        List<Reservable> copia = new ArrayList<>(recursos);
        copia.sort(Comparator.comparing(Reservable::getNom, String.CASE_INSENSITIVE_ORDER));
        return copia;
    }

    /**
     * TODO RA4: aquest mètode concatena Strings dins d'un bucle.
     * Cal optimitzar-lo sense canviar el resultat retornat.
     * Explicació:
     * El problema amb el codi antic és que els Strings a Java són immutables, és a dir, que si li afegeixes parts a un string en un bucle, doncs no s'està modificant el string existent, sino que es crea un de nou i això és molt poc optim per el programa. En canvi, fent servir el StringBuilder això no passa i és més optim a l'hora de executar el programa.
     */
    public String generarInformeRecursos() {
        StringBuilder informe = new StringBuilder();

        for (Reservable recurs : recursos) {
            informe.append(recurs.getNom())
                    .append(" - ")
                    .append(recurs.getTipus())
                    .append(" - ")
                    .append(recurs.estaDisponible() ? "Disponible" : "Reservat")
                    .append(System.lineSeparator());
        }

        return informe.toString();
    }
}
