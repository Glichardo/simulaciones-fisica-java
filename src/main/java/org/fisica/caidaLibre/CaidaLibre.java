package org.fisica.caidaLibre;

import lombok.extern.log4j.Log4j;

import java.util.Scanner;

@Log4j
public class CaidaLibre {
    public static void execute(Scanner scanner) {

        Result result = getResult(scanner);

        double finalVelocity;
        double time;
        if (result.initialVelocity() == 0){ //caída libre

            //calcular tiempo
            time = Math.sqrt((2 * result.height()) / result.gravity());
            log.info("El tiempo de caída es de: " + time + "s.");

            //calcular velocidad final
            finalVelocity = result.gravity() * time;
            log.info("La velocidad final del objeto es de: " + finalVelocity + "m/s.");

        } else if (result.initialVelocity() < 0) { //lanzamiento hacia abajo

            //calcular velocidad final
            finalVelocity = -Math.sqrt(Math.pow(result.initialVelocity(), 2) + (2 * result.gravity() * result.height()));
            log.info("La velocidad final del objeto es de: " + finalVelocity + "m/s.");

            //calcular tiempo
            time = ((finalVelocity - result.initialVelocity()) / (-result.gravity()));
            log.info("El tiempo de caída es de: " + time + "s.");

        } else if (result.initialVelocity() > 0) {

            // calcular altura máxima
            double maxHeight = result.height() + Math.pow(result.initialVelocity(), 2) / (2 * result.gravity());
            log.info("La altura máxima alcanzada es de: " + maxHeight + " m.");

            // calcular tiempo de subida
            double riseTime = result.initialVelocity() / result.gravity();
            log.info("El tiempo de subida es de: " + riseTime + " s.");

            // calcular velocidad final al tocar el suelo: vf = sqrt(2 * g * h_total)
            finalVelocity = Math.sqrt(2 * result.gravity() * maxHeight);
            log.info("La velocidad final del objeto al tocar el suelo es de: " + finalVelocity + " m/s.");

            // calcular tiempo total de vuelo: t_total = subida + bajada
            time = riseTime + Math.sqrt(2 * maxHeight / result.gravity());
            log.info("El tiempo total de vuelo es de: " + time + " s.");
        }

    }

    private static Result getResult(Scanner scanner) {
        log.info("Caída libre o lanzamiento");

        log.info("Introduce la altura del objeto (por ejemplo, si pones '100', se tomará que cae desde 100m a 0m)");
        double height = scanner.nextDouble();
        scanner.nextLine();

        log.info("Introduce la velocidad inicial (por ejemplo, si pones -5 se tomará como 5m/s hacia abajo. Si pones 0 se toma como caída libre. Si pones 5 se tomará como lanzamiento hacia arriba)");
        double initialVelocity = scanner.nextDouble();
        scanner.nextLine();

        double gravity = 9.80665;
        log.info("La gravedad se tomará como 9,80665m/s². ¿Quieres cambiarlo? [s/n]");
        String option = scanner.nextLine();
        if (option.equals("s") || option.equals("S") ){
            gravity = scanner.nextDouble();
            if (gravity < 0) {
                gravity = gravity - (2 * gravity);
            }
        }
        Result result = new Result(height, initialVelocity, gravity);
        return result;
    }

    private record Result(double height, double initialVelocity, double gravity) {
    }
}
