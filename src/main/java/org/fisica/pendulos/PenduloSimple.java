package org.fisica.pendulos;

import lombok.extern.log4j.Log4j;

import java.util.Scanner;

@Log4j
public class PenduloSimple { //pendulo simple
    public static void execute(Scanner scanner) {

        Result result = getResult(scanner);

        // omega:
        double omega = Math.sqrt(result.gravity() / result.length());

        if (!result.hasFriction()){
            log.info("Simulación (t, ángulo[rad], velocidad angular[rad/s], momento angular[kg·m²/s]):");

            for (double t = 0; t <= result.maxTime(); t += 0.05) {
                // posición angular
                double angle = result.initialAngle() * Math.cos(omega * t);

                // velocidad angular
                double angularVelocity = -result.initialAngle() * omega * Math.sin(omega * t);

                // momento angular
                double L_ang = result.mass() * result.length() * result.length() * angularVelocity;

                log.info(String.format(
                        "t=%.05fs  θ=%.4f rad  ω=%.4f rad/s  L=%.4f kg·m²/s",
                        t, angle, angularVelocity, L_ang
                ));
            }
        } else {

            log.info("Introduce coeficiente de rozamiento (gamma, por ejemplo 0.01 leve, 0.1 moderado):");
            double gamma = scanner.nextDouble();
            scanner.nextLine();

            omega = Math.sqrt(result.gravity() / result.length());

            //comprobar que no haya sobreamortiguamiento
            if (gamma >= omega) {
                log.warn("El sistema está sobreamortiguado (no oscila). Prueba con un gamma más pequeño.");
                return;
            }

            double omegaPrime = Math.sqrt(omega * omega - gamma * gamma);

            log.info("Simulación con rozamiento:");
            log.info("t, θ[rad], ω[rad/s], L[kg·m²/s]");

            for (double t = 0; t <= result.maxTime(); t += 0.05) {

                // ángulo con amortiguamiento
                double angle = result.initialAngle() * Math.exp(-gamma * t) * Math.cos(omegaPrime * t);

                // velocidad angular (derivada correcta)
                double angularVelocity =
                        result.initialAngle() * Math.exp(-gamma * t) *
                                (-gamma * Math.cos(omegaPrime * t) - omegaPrime * Math.sin(omegaPrime * t));

                // momento angular
                double L_ang = result.mass() * result.length() * result.length() * angularVelocity;

                log.info(String.format(
                        "t=%.05fs  θ=%.4f rad  ω=%.4f rad/s  L=%.4f kg·m²/s",
                        t, angle, angularVelocity, L_ang
                ));
            }
        }
    }



    private static Result getResult(Scanner scanner) {
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
        Result result = new Result(mass, length, hasFriction, initialAngle, gravity, maxTime);
        return result;
    }

    private record Result(double mass, double length, boolean hasFriction, double initialAngle, double gravity, double maxTime) {
    }
}
