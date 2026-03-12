package org.fisica.movimientos;

import lombok.extern.log4j.Log4j;

import java.util.Scanner;

@Log4j
public class mruYMrua {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        log.info("Simulación de movimientos:");
        log.info("¿Quieres simular un 'MRU' o un 'MRUA'?");

        String option = scanner.nextLine();

        if (option.equals("MRU") || option.equals("mru")){

            log.info("Introduce la posición inicial (en metros):");
            double initialPosition = scanner.nextDouble();
            scanner.nextLine();

            log.info("Introduce la velocidad constante (en m/s):");
            double constantSpeed = scanner.nextDouble();
            scanner.nextLine();

            log.info("Introduce el tiempo de la simulación (en segundos):");
            double time = scanner.nextDouble();
            scanner.nextLine();

            log.info("Simulación MRU (t, x[m], v[m/s]):");
            for (double t = 0; t <= time; t += 0.05) {
                //posición
                double position = initialPosition + (constantSpeed * t);

                log.info(String.format("t=%.2fs  x=%.4f m  v=%.4f m/s", t, position, constantSpeed));
            }


        } else if (option.equals("MRUA") || option.equals("mrua")) {
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

            log.info("Simulación MRUA (t, x[m], v[m/s], a[m/s²]):");
            for (double t = 0; t <= time; t += 0.05) {
                double position = initialPosition + initialSpeed * t + 0.5 * acceleration * t * t;
                double speed = initialSpeed + acceleration * t;
                log.info(String.format("t=%.2fs  x=%.4f m  v=%.4f m/s  a=%.4f m/s²", t, position, speed, acceleration));
            }
        } else {
            log.info("Opción no válida. Por favor escribe 'MRU' o 'MRUA'.");
        }
    }
}
