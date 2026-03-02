package org.tickup.adapters.utils;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Cette classe permet de générer des valeurs aléatoires
 * pour les tests.
 */
public class FakeDataUtils {
    public   static Random random = new Random();
    public static LocalDateTime generateRandomDate(LocalDate startDate, LocalDate endDate) {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
        return startDate.plusDays(randomDays).atStartOfDay();
    }
    public static  LocalDate startDate = LocalDate.of(2020, 1, 1);
    public static  LocalDate endDate = LocalDate.of(2024, 10, 29);

    // generer un type d'operation de façon aleatoire
//    public static TypeOperation generateRandomTypeOperation(int i) {
//        switch (i) {
//            case 0:
//                return TypeOperation.ACHAT;
//            case 1:
//                return TypeOperation.VIREMENT;
//            case 2:
//                return TypeOperation.TRANSFERT;
//            case 3:
//                return TypeOperation.RETRAIT;
//            case 4:
//                return TypeOperation.REMITTANCE;
//            case 5:
//                return TypeOperation.PAIEMENT;
//            case 6:
//                return TypeOperation.PRELEVEMENT;
//            case 7:
//                return TypeOperation.DEPOT;
//            case 8:
//                return TypeOperation.FIDELITE;
//            default:
//                return TypeOperation.ACHAT;
//        }
//    }
}
