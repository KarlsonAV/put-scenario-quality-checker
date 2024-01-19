
# Scenario Quality Checker API Documentation
![example workflow](https://github.com/KarlsonAV/put-scenario-quality-checker/actions/workflows/maven.yml/badge.svg)

The Scenario Quality Checker API provides endpoints to analyze and evaluate scenario files. It is designed to count the number of sections in a scenario file, count sections with specific keywords, identify sections with errors, display scenarios with enumerated steps, show main steps in a scenario, and display scenarios up to a specified depth.

## Table of Contents
- [Endpoints](#endpoints)
  - [Count Sections](#count-sections)
  - [Count Sections with Keywords](#count-sections-with-keywords)
  - [Find Sections with Errors](#find-sections-with-errors)
  - [Show Enumerated Scenario](#show-enumerated-scenario)
  - [Scenario Main Steps](#scenario-main-steps)
  - [Scenario Up To Depth](#scenario-up-to-depth)

---

## Endpoints

### Count Sections
Endpoint to count the number of sections in a scenario file.
- **URL:** `/api/v2/count/sections`
- **HTTP Method:** `POST`
- **Request Parameters:**
  - `file` (Multipart txt file containing the scenario text)

**Request Example:**
```bash
curl -X POST -F "file=@scenario.txt" http://your-api-base-url/api/v2/count/sections
```

**Response Example:**
```json
{
  "numberOfSections": 5
}
```

### Count Sections with Keywords
Endpoint to count the number of sections containing keywords in a scenario file.
- **URL:** `/api/v2/count/sections/keywords`
- **HTTP Method:** `POST`
- **Request Parameters:**
  - `file` (Multipart txt file containing the scenario text)

**Request Example:**
```bash
curl -X POST -F "file=@scenario.txt" http://your-api-base-url/api/v2/count/sections/keywords
```

**Response Example:**
```json
{
  "numberOfSectionsWithKeywords": 3
}
```

### Find Sections with Errors
Endpoint to find sections with errors in a scenario file.
- **URL:** `/api/v2/sections/errors`
- **HTTP Method:** `POST`
- **Request Parameters:**
  - `file` (Multipart txt file containing the scenario text)

**Request Example:**
```bash
curl -X POST -F "file=@scenario.txt" http://your-api-base-url/api/v2/sections/errors
```

**Response Example:**
```json
{
  "sectionsWithErrors": ["Section 2", "Section 4"]
}
```

### Show Enumerated Scenario
Endpoint to display scenario with enumerated steps.
- **URL:** `/api/v2/scenario/enumerated`
- **HTTP Method:** `POST`
- **Request Parameters:**
  - `file` (Multipart txt file containing the scenario text)

**Request Example:**
```bash
curl -X POST -F "file=@scenario.txt" http://your-api-base-url/api/v2/scenario/enumerated
```

**Response Example:**
```json
{
  "enumeratedScenario": 1. Bibliotekarz wybiera opcje dodania nowej pozycji książkowej
  2. Wyświetla się formularz.
  3. Bibliotekarz podaje dane książki.
  4. IF: Bibliotekarz pragnie dodać egzemplarze książki
  4. 1. Bibliotekarz wybiera opcję definiowania egzemplarzy
  4. 2. System prezentuje zdefiniowane egzemplarze
  4. 3. FOR EACH egzemplarz:
  4. 3. 1. Bibliotekarz wybiera opcję dodania egzemplarza
  4. 3. 2. System prosi o podanie danych egzemplarza
  4. 3. 3. Bibliotekarz podaje dane egzemplarza i zatwierdza.
  4. 3. 4. System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.
  5. Bibliotekarz zatwierdza dodanie książki.
  6. System informuje o poprawnym dodaniu książki.
}
```

### Scenario Main Steps
Endpoint to show main steps in a scenario.
- **URL:** `/api/v2/scenario/mainSteps`
- **HTTP Method:** `POST`
- **Request Parameters:**
  - `file` (Multipart txt file containing the scenario text)

**Request Example:**
```bash
curl -X POST -F "file=@scenario.txt" http://your-api-base-url/api/v2/scenario/mainSteps
```

**Response Example:**
```json
{
  "mainSteps": Dobry scenariusz
}
```

### Scenario Up To Depth
Endpoint to display scenario up to a specified depth.
- **URL:** `/api/v2/scenario/scenarioUpToDepth`
- **HTTP Method:** `POST`
- **Request Parameters:**
  - `file` (Multipart txt file containing the scenario text)
  - `depth` (Integer value specifying the depth)

**Request Example:**
```bash
curl -X POST -F "file=@scenario.txt" -F "depth=3" http://your-api-base-url/api/v2/scenario/scenarioUpToDepth
```

**Response Example:**
```json
{
  "scenarioUpToDepth": "Scenario text up to specified depth"
}
