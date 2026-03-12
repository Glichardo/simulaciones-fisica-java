package org.fisica.pendulos;

import lombok.extern.log4j.Log4j;

import java.util.Scanner;

@Log4j
public class PenduloSimple { //pendulo simple sin rozamiento
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        log.info("Péndulo simple");

        log.info("Introduce la masa (en kilogramos):");
        double mass = scanner.nextDouble();
        scanner.nextLine();

        log.info("Introduce la longitud del hilo (en metros):");
        double length = scanner.nextDouble();
        scanner.nextLine();

        log.info("¿Hay resistencia con el aire? [true/false]");
        boolean hasFriction = scanner.nextBoolean();
        scanner.nextLine();

        log.info("Introduce ángulo inicial (en radianes):");
        double initialAngle = scanner.nextDouble();
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

        log.info("Dime cuántos segundos dura la simulación (comenzando en el instante t=0s):");
        double maxTime = scanner.nextDouble();
        scanner.nextLine();

        // omega:
        double omega = Math.sqrt(gravity / length);

        log.info("Simulación (t, ángulo[rad], velocidad angular[rad/s], momento angular[kg·m²/s]):");

        for (double t = 0; t <= maxTime; t += 0.05) {
            // posición angular
            double angle = initialAngle * Math.cos(omega * t);

            // velocidad angular
            double angularVelocity = -initialAngle * omega * Math.sin(omega * t);

            // momento angular
            double L_ang = mass * length * length * angularVelocity;

            log.info(String.format(
                    "t=%.05fs  θ=%.4f rad  ω=%.4f rad/s  L=%.4f kg·m²/s",
                    t, angle, angularVelocity, L_ang
            ));
        }


    }
}
