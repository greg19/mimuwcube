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

### Dodatkowe założenia

Aby parser mógł działać poprawnie, potrzebuje znać wielkość kostki.
Zakłada zatem, że klasa `concurrentcube.Cube` udostępnia metodę `public int getSize()`, zwracającą wielkość kostki oraz nie rzucającą żadnymi wyjątkami wymagającymi łapania.

### Obsługiwane przypadki

Aktualnie parser jest bardzo ograniczony i obsługuje jedynie kostki wielkości
1,
2,
3,
4 (jedynie notacja 'Rw', nie 'Rr'),
5 (również notacja z 'w')
