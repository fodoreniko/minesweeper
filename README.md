# minesweeper

A játék egy egyforma méretű mezőkre osztott táblából áll. A kezdeti állapotban az összes mező le van takarva, ezeket a játékos kattintással tudja felfedni. 
A felfedett mezők a következő állapotokat vehetik fel: 
    o	szám (1-től 8-ig), ez azt jelenti, hogy hány darab akna található összesen a szomszédos 8 darab mezőben
    o	üres, ez a 0-ás számot jelképezi, tehát azt, hogy a szomszédos mezőkben nincsen akna
    o	akna 
A játék célja az összes akna megtalálása, a lehető legrövidebb időn belül. Akkor győz a játékos, ha feltárja az összes aknamentes mezőt. Ha a játékos aknára kattint, az a mező „felrobban”, így a játékos azonnal elveszíti a játékot.
A tábla mérete és az aknák száma a nehézségi szinttől függően változik. 3 nehézségi szint érhető el:
    o	kezdő: 9x9-es tábla, 10 aknával
    o	normál: 16x16-os tábla, 40 aknával
    o	haladó: 30x16-os tábla, 99 aknával
A játék során a játékos segítségül használhat úgynevezett zászlókat, amivel megjelölheti azokat a mezőket, amik szerinte aknát rejtenek. Így figyelemmel követheti a hátramaradó aknák számát, ami a zászlók lerakásának függvényében csökken. Továbbá meg van jelenítve egy számláló, ami azt mutatja, hogy a játékos mennyi ideje kezdte el az aknakeresőt. 
Ezen felül lesz egy pályaépítő mód, amelyben a felhasználó megépítheti a saját pályáját, tehát tetszőlegesen elhelyezheti az aknákat, majd ezt később betöltve játszani is tud vele.
