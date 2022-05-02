# join_gpx_java
  Аналог https://github.com/oleguch/join-gpx, только на Java
  
## Описание
Обединение двух и более gpx файлов

## Использование
```
java JoinGPX.java 01.gpx 02.gpx 03.gpx
```
на выходе будет один файл full.gpx.

## Как объединяет
Берет первый файл, и добавляет в него в тэг trkseg все элементы trkpt из других файлов
