# Preguntes teòriques

## 1. Per què s'atura l'execució al cap d'un temps?

L'execució s'atura perquè, quan totes les places estan ocupades, els fils que intenten reservar es bloquegen amb `wait()`, esperant una cancel·lació. No obstant això, `notifyAll()` només es crida quan algú cancel·la, i si les cancel·lacions són escasses o inexistents, els fils en espera mai es desbloquegen. Això pot provocar un interbloqueig parcial, on tots els fils es queden atrapats en `wait()` sense cap notificació per reactivar-los, fent que el programa sembli aturat.

## 2. Què passaria si en lloc de una probabilitat de 50%-50% fora de 70% (ferReserva)-30% (cancel·lar)? I si foren al revés les probabilitats? → Mostra la porció de codi modificada i la sortida resultant en cada un dels 2 casos 

### Cas 1: 70% (ferReserva)-30%

### Cas 2: 30% (ferReserva)-70%

## 3. Perquè creus que fa falta la llista i no valdria només amb una variable sencera de reserves?


