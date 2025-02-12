# Preguntes teòriques

## 1. Per què s'atura l'execució al cap d'un temps?

L'execució s'atura perquè, quan totes les places estan ocupades, els fils que intenten reservar es bloquegen amb `wait()`, esperant una cancel·lació. No obstant això, `notifyAll()` només es crida quan algú cancel·la, i si les cancel·lacions són escasses o inexistents, els fils en espera mai es desbloquegen. Això pot provocar un interbloqueig parcial, on tots els fils es queden atrapats en `wait()` sense cap notificació per reactivar-los, fent que el programa sembli aturat.

## 2. Què passaria si en lloc de una probabilitat de 50%-50% fora de 70% (ferReserva)-30% (cancel·lar)? I si foren al revés les probabilitats? → Mostra la porció de codi modificada i la sortida resultant en cada un dels 2 casos 

### Cas 1: 70% (ferReserva)-30%
Amb aquesta probabilitat, les reserves s'aconsegueixen ràpidament, deixant molts assistents en llista d'espera, ja que no es produeixen prou cancel·lacions.

Codi modificat:
  ```java
  if (random.nextInt(100) < 70) esdeveniment.ferReserva(this);   
  else esdeveniment.cancelaReserva(this);
  ```

Sortida:
  ```plaintext
  Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 5
  Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 5
  Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 5
  Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 5
  Assistent-6 ha fet una reserva. Places disponibles: 4
  Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
  Assistent-4 ha fet una reserva. Places disponibles: 3
  Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
  Assistent-1 ha fet una reserva. Places disponibles: 2
  Assistent-2 ha fet una reserva. Places disponibles: 1
  Assistent-0 ha fet una reserva. Places disponibles: 0
  Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
  Assistent-1 ha cancel·lat una reserva. Places disponibles: 1
  Assistent-9 ha fet una reserva. Places disponibles: 0
  Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
  Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
  Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
  Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
  ```

### Cas 2: 30% (ferReserva)-70%
En aquest cas, la tendència serà que la majoria de les accions siguin cancel·lacions en lloc de reserves.

Codi modificat:
  ```java
  if (random.nextInt(100) < 30) esdeveniment.ferReserva(this);
  else esdeveniment.cancelaReserva(this);
  ```

Sortida:
  ```plaintext
  Assistent-0 ha fet una reserva. Places disponibles: 4
  Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
  Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
  Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
  Assistent-7 ha fet una reserva. Places disponibles: 3
  Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
  Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
  Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
  Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
  Assistent-2 ha fet una reserva. Places disponibles: 2
  Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
  Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
  Assistent-7 ha fet una reserva. Places disponibles: 1
  Assistent-5 ha fet una reserva. Places disponibles: 0
  Assistent-5 ha cancel·lat una reserva. Places disponibles: 1
  Assistent-7 ha fet una reserva. Places disponibles: 0
  Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
  Assistent-2 ha cancel·lat una reserva. Places disponibles: 1
  Assistent-9 ha fet una reserva. Places disponibles: 0
  Assistent-0 ha cancel·lat una reserva. Places disponibles: 1
  Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
  Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
  Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
  Assistent-1 ha fet una reserva. Places disponibles: 0
  Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
  Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
  Assistent-7 ha cancel·lat una reserva. Places disponibles: 1
  Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
  Assistent-9 ha cancel·lat una reserva. Places disponibles: 2
  Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
  Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
  Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
  Assistent-2 ha fet una reserva. Places disponibles: 1
  Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
  Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
  Assistent-7 ha cancel·lat una reserva. Places disponibles: 2
  Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
  Assistent-0 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
  Assistent-1 ha cancel·lat una reserva. Places disponibles: 3
  Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
  Assistent-3 ha fet una reserva. Places disponibles: 2
  Assistent-3 ha cancel·lat una reserva. Places disponibles: 3
  Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
  Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
  Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
  Assistent-2 ha fet una reserva. Places disponibles: 2
  Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
  Assistent-5 ha fet una reserva. Places disponibles: 1
  Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
  Assistent-7 ha fet una reserva. Places disponibles: 0
  Assistent-5 ha cancel·lat una reserva. Places disponibles: 1
  Assistent-0 ha fet una reserva. Places disponibles: 0
  Assistent-7 ha cancel·lat una reserva. Places disponibles: 1
  Assistent-5 ha fet una reserva. Places disponibles: 0
  Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
  Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
  Assistent-7 ha cancel·lat una reserva. Places disponibles: 1
  Assistent-4 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
  Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
  Assistent-2 ha cancel·lat una reserva. Places disponibles: 2
  Assistent-6 ha fet una reserva. Places disponibles: 1
  Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
  Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
  Assistent-5 ha cancel·lat una reserva. Places disponibles: 2
  Assistent-0 ha cancel·lat una reserva. Places disponibles: 3
  Assistent-6 ha cancel·lat una reserva. Places disponibles: 4
  Assistent-7 ha fet una reserva. Places disponibles: 3
  Assistent-1 ha fet una reserva. Places disponibles: 2
  Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
  Assistent-9 ha fet una reserva. Places disponibles: 1
  Assistent-9 ha cancel·lat una reserva. Places disponibles: 2
  Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
  Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 2
  Assistent-0 ha fet una reserva. Places disponibles: 1
  Assistent-2 ha fet una reserva. Places disponibles: 0
  Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
  Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
  Assistent-7 ha cancel·lat una reserva. Places disponibles: 1
  Assistent-4 ha fet una reserva. Places disponibles: 0
  Assistent-2 ha cancel·lat una reserva. Places disponibles: 1
  Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
  Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 1
  Assistent-4 ha cancel·lat una reserva. Places disponibles: 2
  Assistent-1 ha cancel·lat una reserva. Places disponibles: 3
  Assistent-2 ha cancel·lat una reserva. Places disponibles: 4
  Assistent-0 ha cancel·lat una reserva. Places disponibles: 5
  Assistent-9 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 5
  Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 5
  Assistent-4 ha fet una reserva. Places disponibles: 4
  Assistent-8 ha fet una reserva. Places disponibles: 3
  Assistent-8 ha cancel·lat una reserva. Places disponibles: 4
  Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
  Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
  Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
  Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
  Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
  Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
  Assistent-8 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
  Assistent-9 ha fet una reserva. Places disponibles: 3
  Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 3
  Assistent-9 ha cancel·lat una reserva. Places disponibles: 4
  Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
  ```

## 3. Perquè creus que fa falta la llista i no valdria només amb una variable sencera de reserves?

Si només utilitzéssim una variable sencera per comptar les reserves, no podríem gestionar correctament les accions de cada assistent. La llista ens permet:

- Saber exactament quins assistents han obtingut una plaça reservada.
- Evitar que un assistent cancel·li una reserva que no ha fet prèviament, garantint així coherència en el sistema.
- Utilitzar de manera efectiva els mètodes **wait()** i **notifyAll()**, ja que podem controlar les accions específiques sobre cada element de la llista, permetent que cada assistent es gestioni individualment.

Si ens limitàssim a una variable sencera, només podríem saber el nombre total de reserves, però no qui les ha fet ni qui té permís per cancel·lar-les, el que dificultaria la implementació d'un sistema robust.
