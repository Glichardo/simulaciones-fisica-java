package org.fisica.movimientos;

import lombok.extern.log4j.Log4j;

import java.util.Scanner;

@Log4j
public class MruYMrua {
    public static void execute(Scanner scanner) {

        log.info("Simulación de movimientos:");
        log.info("¿Quieres simular un 'MRU' o un 'MRUA'?");

        String option = scanner.nextLine();

        if (option.equalsIgnoreCase("mru")){

            MruVariables result = getMruVariables(scanner);

            log.info("Simulación MRU (t, x[m], v[m/s]):");
            for (double t = 0; t <= result.time(); t += 0.05) {
                //posición
                double position = result.initialPosition() + (result.constantSpeed() * t);

                log.info(String.format("t=%.2fs  x=%.4f m  v=%.4f m/s", t, position, result.constantSpeed()));
            }


        } else if (option.equalsIgnoreCase("MRUA")) {
            MruaVariables result = getMruaVariables(scanner);

            log.info("Simulación MRUA (t, x[m], v[m/s], a[m/s²]):");
            for (double t = 0; t <= result.time(); t += 0.05) {
                double position = result.initialPosition() + result.initialSpeed() * t + 0.5 * result.acceleration() * t * t;
                double speed = result.initialSpeed() + result.acceleration() * t;
                log.info(String.format("t=%.2fs  x=%.4f m  v=%.4f m/s  a=%.4f m/s²", t, position, speed, result.acceleration()));
            }
        } else {
            log.info("Opción no válida. Por favor escribe 'MRU' o 'MRUA'.");
        }
    }

    private static MruaVariables getMruaVariables(Scanner scanner) {
        log.info("Introduce la posición inicial (en metros):");
        double initialPosition = scanner.nextDouble();
        scanner.nextLine();

        log.info("Introduce la velocidad inicial (en m/s):");
        double initialSpeed = scanner.nextDouble();
        scanner.nextLine();

        log.info("Introduce la aceleración (en m/s²):");
        double acceleration = scanner.nextDouble();
        scanner.nextLine();

        log.info("Introduce el tiempo de la simulación (en segundos):");
        double time = scanner.nextDouble();
        scanner.nextLine();
        MruaVariables result = new MruaVariables(initialPosition, initialSpeed, acceleration, time);
        return result;
    }

    private record MruaVariables(double initialPosition, double initialSpeed, double acceleration, double time) {
    }

    private static MruVariables getMruVariables(Scanner scanner) {
        log.info("Introduce la posición inicial (en metros):");
        double initialPosition = scanner.nextDouble();
        scanner.nextLine();

        log.info("Introduce la velocidad constante (en m/s):");
        double constantSpeed = scanner.nextDouble();
        scanner.nextLine();

        log.info("Introduce el tiempo de la simulación (en segundos):");
        double time = scanner.nextDouble();
        scanner.nextLine();
        MruVariables result = new MruVariables(initialPosition, constantSpeed, time);
        return result;
    }

    private record MruVariables(double initialPosition, double constantSpeed, double time) {
    }
}
