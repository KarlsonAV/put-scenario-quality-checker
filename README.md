# Scenario Quality Checker API Documentation
![example workflow](https://github.com/KarlsonAV/put-scenario-quality-checker/actions/workflows/maven.yml/badge.svg)

The Scenario Quality Checker API provides endpoints to analyze and evaluate scenario files. It is designed to count the number of sections in a scenario file, count sections with specific keywords, and identify sections with errors.

## Table of Contents
- [Endpoints](#endpoints)
  - [Count Sections](#count-sections)
  - [Count Sections with Keywords](#count-sections-with-keywords)
  - [Find Sections with Errors](#find-sections-with-errors)

---

## Endpoints

### Count Sections

Endpoint to count the number of sections in a scenario file.

- **URL:** `/api/v1/count/sections`
- **HTTP Method:** `POST`
- **Request Parameters:**
  - `file` (Multipart txt file containing the scenario text)

**Request Example:**
```bash
curl -X POST -F "file=@scenario.txt" http://your-api-base-url/api/v1/count/sections
```

**Response Example:**
```json
{
  "numberOfSections": 5
}
```

### Count Sections with Keywords

Endpoint to count the number of sections containing keywords in a scenario file.

- **URL:** `/api/v1/count/sections/keywords`
- **HTTP Method:** `POST`
- **Request Parameters:**
  - `file` (Multipart txt file containing the scenario text)

**Request Example:**
```bash
curl -X POST -F "file=@scenario.txt" http://your-api-base-url/api/v1/count/sections/keywords
```

**Response Example:**
```json
{
  "numberOfSectionsWithKeywords": 3
}
```

### Find Sections with Errors

Endpoint to find sections with errors in a scenario file.

- **URL:** `/api/v1/sections/errors`
- **HTTP Method:** `POST`
- **Request Parameters:**
  - `file` (Multipart txt file containing the scenario text)

**Request Example:**
```bash
curl -X POST -F "file=@scenario.txt" http://your-api-base-url/api/v1/sections/errors
```

**Response Example:**
```json
{
  "sectionsWithErrors": ["Section 2", "Section 4"]
}
```
