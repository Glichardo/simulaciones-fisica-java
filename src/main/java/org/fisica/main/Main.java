package org.fisica.main;

import lombok.extern.log4j.Log4j;
import org.fisica.caidaLibre.CaidaLibre;
import org.fisica.movimientos.MruYMrua;
import org.fisica.pendulos.PenduloSimple;

import java.util.InputMismatchException;
import java.util.Scanner;

@Log4j
public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        menuLoop();

    }

    public static void showMenu(){

        log.info("Menú:");
        log.info("Escribe el número de la opción que quieras elegir.");
        log.info("1. Caída libre");
        log.info("2. MRU / MRUA");
        log.info("3. Péndulo simple (con/sin rozamiento)");
        log.info("4. Salir");


    }

    public static void menuLoop() {

        int option = 0;

        do {
            try {
                showMenu();
                option = scanner.nextInt();
                scanner.nextLine();

                if (option == 1){

                    CaidaLibre.execute(scanner);

                } else if (option == 2) {

                    MruYMrua.execute(scanner);

                } else if (option == 3) {

                    PenduloSimple.execute(scanner);

                } else if (option < 1 || option > 4){
                    log.warn("La opción introducida no existe.");
                } else {

                }
            } catch (InputMismatchException e){
                log.error("Tienes que introducir un valor de número entero. " + e);
                scanner.nextLine();
            }
        } while (option != 4);

    }
}
