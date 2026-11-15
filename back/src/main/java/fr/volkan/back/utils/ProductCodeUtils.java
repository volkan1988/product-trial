package fr.volkan.back.utils;

import java.util.Random;
import java.util.stream.Collectors;

public final class ProductCodeUtils {

    private static final Random RANDOM = new Random();
    private static final long CODE_SIZE = 9;

    private ProductCodeUtils() {
    }

    /**
     * Permet de générer un code alphanumérique de x caractères.
     *
     * @return Le code généré.
     */
    public static String generateCode() {
        return generateCode(CODE_SIZE);
    }

    /**
     * Permet de générer un code alphanumérique de "length" caractères.
     *
     * @param length La taille du code souhaitée.
     * @return Le code généré.
     */
    public static String generateCode(long length) {
        // La valeur 36 vient des éléments de 0 à 9, puis de "a" à "z".
        return RANDOM.ints(length, 0, 36)
                .mapToObj(i -> String.valueOf(Character.forDigit(i, 36)))
                .collect(Collectors.joining());
    }
}
