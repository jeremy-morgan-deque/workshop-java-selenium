# Workshop Java Selenium Tests

Selenium WebDriver tests for https://workshop2.dequelabs.com/

## Prerequisites

- Java 11+
- Maven 3.6+
- Chrome browser

## Tests

| Test | Description |
|------|-------------|
| `testPageTitle` | Verifies page title is "Recipe Dashboard" |
| `testEditRecipeModal` | Clicks edit button on first recipe, verifies modal opens with "Edit Chocolate Cake" header |

## Running Tests

```bash
mvn test
```

## Tech Stack

- Selenium 4.18.1
- TestNG 7.9.0
- WebDriverManager 5.7.0
