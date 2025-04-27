🚀 OpenCart Hybrid Test Automation Framework

![Selenium](https://img.shields.io/badge/Selenium-4.25.0-43B02A?logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-7.9.0-DD0031)
![Java](https://img.shields.io/badge/Java-17-007396?logo=java)

A robust hybrid test automation framework combining **POM, Data-Driven, and Keyword-Driven** approaches 
for OpenCart testing.

## 📌 Features
- ✅ Cross-browser testing (Chrome, Firefox, Edge)
- ✅ Parallel test execution
- ✅ Dockerized Selenium Grid support
- ✅ Comprehensive reporting (ExtentReports + Log4j2)
- ✅ CI/CD ready (Jenkins integration)

## 🛠 Technologies
| Component | Version |
|-----------|---------|
| Java      | 17      |
| Selenium  | 4.25.0  |
| TestNG    | 7.9.0   |
| Maven     | 3.9.9   |
| Docker    | 28.0.4  |


### Prerequisites
- Java 17 JDK
- Maven 3.8+
- Docker (for Grid setup)
- OpenCart installed (WAMP/XAMPP) - https://www.opencart.com/index.php?route=cms/download

### Installation bash
git clone https://github.com/maherAbubakar/selenium-hybrid-framework.git
cd selenium-hybrid-framework

### Configuration
Update config.properties:
* appURL=http://localhost/opencart
* execution_env=local or 'remote' for grid
* browser=chrome

### For Selenium Grid:
docker-compose -f src/test/resources/docker-compose.yaml up -d

### 🧪 Running Tests
**Test Suites**
File	                |   Purpose
crossBrowser-test.xml   |   For Cross Browser testing
full_regression.xml     |   For running end-to-end test
grid-docker.xml         | Selenium Grid tests

### 📊 Reports
ExtentReports: reports/Test-Report.html
TestNG Reports: target/surefire-reports
Logs: logs/automation.log
Screenshots: screenshots/ (on failure)

### 🤝 Contribution
Contributions are welcome! Please read CONTRIBUTING.md before submitting a pull request.

### 📧 Author
Abubakar

### 📧 Contact
Email: maherabubakar80@gmail.com

LinkedIn: https://www.linkedin.com/in/abubakar-saleem/
