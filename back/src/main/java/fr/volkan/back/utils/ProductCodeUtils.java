package fr.volkan.back.utils;

import java.util.Random;
import java.util.stream.Collectors;

public final class ProductCodeUtils {

    private static final Random RANDOM = new Random();

    private ProductCodeUtils() {
    }

    public static String generateCode() {
        // Génère un code alphanumérique
        return RANDOM.ints(9, 0, 36)
                .mapToObj(i -> String.valueOf(Character.forDigit(i, 36)))
                .collect(Collectors.joining());
    }
}
