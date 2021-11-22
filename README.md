# \[WAŻNE\] \[PIZZA\] Zasady korzystania

Jak wiemy, testy są częścią zadania, więc nie należy się nimi dzielić.
Natomiast po konsultacji z autorem zadania, dostałem taką odpowiedź:
```
Można się dzielić takimi narzędziami. Jeśli ktoś dołączy je do swojego
rozwiązania, musi jednak zaznaczyć, że nie jest ich autorem i nie powinny
podlegać ocenie.

Rozsądnie byłoby umieścić narzędzia tego typu w oddzielnym pakiecie. 
```
Na górze plików `utils/Shower.java` oraz `utils/Mover.java` znajdują się stosowne komentarze.

Sensownym wydaje się, żeby indywidualne zmiany w tych plikach również nie podlegały ocenie.

# MIMUW Cube

Główną zawartością repozytorium są klasy `utils.Shower` oraz `utils.Mover`.
Oprócz tego dostarczona jest klasa `utils.Demo`, pokazująca przykłady użycia tych klas.

Zachęcam do częstego śledzenia zmian w tym repozytorium, ponieważ dosyć spamuję commitami xD.

## Shower

Jest to klasa upiększająca widok kostki zwracany przez `Cube.snow()`. Obsługuje różne tryby upiększania.
Więcej szczegółów można znaleźć w pliku demonstracyjnym oraz samej implementacji klasy.

## Mover

Jest to klasa parsująca notację kostki Rubika na odpowiednie metody wywoływane na kostce.

### Notacja

O notacji algorytmów kostki Rubika można poczytać tutaj:

[kostka 3x3x3](https://jperm.net/3x3/moves)

[kostka 4x4x4](https://www.kewbz.co.uk/blogs/notations/4x4-cube-notations-guide-wca-official)

kostka 2x2x2 - tak samo jak 3x3x3, ale nie ma środkowych warstw

### Obsługiwane przypadki

Aktualnie parser jest bardzo ograniczony.
Nie obsługuje obrotów całej kostki (ruchów 'x', 'y' i 'z').
Z wyłączeniem tego, obsługuje kostki wielkości
1,
2,
3 (z wyjątkiem ruchów dwiema warstwami na raz, np 'r', które są używane w kostce 4x4 jako ruch wewnętrzną warstwą)
oraz 4 (obie notacje 'Rr' oraz 'Rw').
