# Spring Boot CI + SonarQube Cloud Template

Template simplu pentru lecția de Quality Gate/SAST cu GitHub Actions.

## Stack

- Java 17
- Spring Boot 3
- Maven
- JUnit 5 + JaCoCo
- SonarQube Cloud (fost SonarCloud)

## Rulare locală

```bash
mvn clean verify
```

Aplicația pornește cu:

```bash
mvn spring-boot:run
```

Endpoint-uri:

- `GET /health`
- `GET /total?values=10&values=20`
- `GET /total-with-vat?values=10&values=20&vat=19`

## Setup GitHub + SonarQube Cloud (pas cu pas)

1. Creezi repository GitHub și urci proiectul.
2. Intri pe `https://sonarcloud.io` și faci login cu GitHub.
3. Apeși `Analyze new project` și alegi repository-ul tău GitHub.
4. SonarQube Cloud îți va selecta/crea automat organizația potrivită și îți va arăta detaliile proiectului.
5. Copiezi:
   - `Organization Key`
   - `Project Key`
6. Generezi un token: `My Account` -> `Security` -> `Generate token`.
7. În SonarQube Cloud, pentru proiectul tău, dezactivezi `Automatic Analysis`:
   - `Project Settings` -> `Analysis Method` -> dezactivezi `Automatic Analysis`
   - motiv: analiza se rulează din CI (GitHub Actions + Maven), nu automat din Sonar.
8. În GitHub repo -> `Settings` -> `Secrets and variables` -> `Actions`, adaugi:
   - `SONAR_TOKEN` = tokenul generat la pasul 6
   - `SONAR_ORGANIZATION` = Organization Key
   - `SONAR_PROJECT_KEY` = Project Key

## Ce înseamnă fiecare

- `Organization`: workspace-ul tău din SonarQube Cloud (echipă/proiecte/permisiuni).
- `Organization Key`: identificatorul unic al organizației; workflow-ul îl folosește ca să trimită analiza în organizația corectă.
- `Project`: repository-ul analizat.
- `Project Key`: identificatorul unic al proiectului analizat.
- `SONAR_TOKEN`: cheia de autentificare folosită de GitHub Actions pentru a trimite analiza în SonarQube Cloud.

Workflow-ul este în `.github/workflows/quality-scan.yml` și ține configurarea Sonar direct în pipeline.

## Scenariu pentru laborator

1. Rulezi workflow-ul și verifici că trece.
2. Introduci intenționat un code smell (ex: metodă goală, duplicare logică).
3. Dai commit + PR.
4. Verifici în SonarQube Cloud Issues + Quality Gate.
5. Repari problema și verifici că gate-ul revine pe verde.
