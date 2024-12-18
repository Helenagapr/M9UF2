# Activitat 01: Programació de fils

## Comportament 1:
La sortida és intercalada de manera aproximada entre els fils "Juan" i "Pepe".

Aquest seria el codi de la classe Fil.java:
```java
    public class Principal {
        public static void main(String[] args) {
            System.out.println("Termina thread main");

            // Crear fils
            Fil juan = new Fil("Juan");
            Fil pepe = new Fil("Pepe");

            // Iniciar els fils
            juan.start();
            pepe.start();

            try {
                // Esperar a que els fils finalitzin
                juan.join();
                pepe.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class Fil extends Thread {
        private String nom;

        public Fil(String nom) {
            this.nom = nom;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 9; i++) {
                System.out.println(nom + " " + i);
                try {
                    // Pausa per simular el temps de treball
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Termina el fil " + nom);
        }
    }
```
Resposta del sistema:

```plaintext
    Termina thread main
    Juan 1
    Pepe 1
    Pepe 2
    Juan 2
    Juan 3
    Pepe 3
    Juan 4
    Pepe 4
    Pepe 5
    Juan 5
    Juan 6
    Pepe 6
    Juan 7
    Pepe 7
    Juan 8
    Pepe 8
    Juan 9
    Pepe 9
    Termina el fil Pepe
    Termina el fil Juan
```

## Comportament 2:
"Pepe" s'executa majoritàriament abans de "Juan", ja que té una pausa més curta.

```java
    public class Principal {

        public static void main(String[] args) {
            System.out.println("Termina thread main");

            Fil pepe = new Fil("Pepe");
            Fil juan = new Fil("Juan");

            // Iniciar fil Pepe
            pepe.start();
            try {
                // Esperar que Pepe acabi abans d'iniciar Juan
                pepe.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Iniciar fil Juan
            juan.start();
            try {
                // Esperar que Juan acabi abans de finalitzar
                juan.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public class Fil extends Thread {
        private String nom;

        public Fil(String nom) {
            this.nom = nom;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 9; i++) {
                System.out.println(nom + " " + i);
                try {
                    // Pausa per simular el temps de treball
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Termina el fil " + nom);
        }
    }
```
Resposta del sistema:

```plaintext
    Termina thread main
    Pepe 1
    Pepe 2
    Pepe 3
    Pepe 4
    Pepe 5
    Pepe 6
    Pepe 7
    Pepe 8
    Pepe 9
    Termina el fil Pepe
    Juan 1
    Juan 2
    Juan 3
    Juan 4
    Juan 5
    Juan 6
    Juan 7
    Juan 8
    Juan 9
    Termina el fil Juan
```

## Comportament 3:
Els fils "Juan" i "Pepe" s'executen estrictament alternats gràcies a la sincronització amb `wait()` i `notify()`.
```java
    public class Principal {
        public static void main(String[] args) {
            System.out.println("Termina thread main");

            // Crear fils
            Fil juan = new Fil("Juan");
            Fil pepe = new Fil("Pepe");

            // Iniciar els fils
            juan.start();
            pepe.start();

            try {
                // Esperar a que els fils finalitzin
                juan.join();
                pepe.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public class Fil extends Thread {
        private String nom;
        private static final Object lock = new Object();

        public Fil(String nom) {
            this.nom = nom;
        }

        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 1; i <= 9; i++) {
                    System.out.println(nom + " " + i);
                    try {
                        // Pausa per simular el temps de treball
                        Thread.sleep(100);
                        // Despertar l'altre fil per executar
                        lock.notify();
                        if (i < 9) {
                            // Esperar el torn de l'altre fil
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Termina el fil " + nom);
        }
    }

```

Resposta del sistema:

```plaintext
    Termina thread main
    Juan 1
    Pepe 1
    Juan 2
    Pepe 2
    Juan 3
    Pepe 3
    Juan 4
    Pepe 4
    Juan 5
    Pepe 5
    Juan 6
    Pepe 6
    Juan 7
    Pepe 7
    Juan 8
    Pepe 8
    Juan 9
    Pepe 9
    Termina el fil Juan
    Termina el fil Pepe
```
