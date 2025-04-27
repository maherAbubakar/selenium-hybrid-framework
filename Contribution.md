**Thank you for considering contributing to this project! 
This document outlines the process for contributing code and collaborating with others on
this automation framework.**

### 🛠 Prerequisites
Before you begin:

* Install Java 17
* Install Maven (3.6 or later)
* Install Git

### Clone the repository:
[git clone https://github.com/your-repo/opencart-hybrid-framework.git]

Import the project into IntelliJ or your preferred IDE.

### 🌿 Branching Strategy
* main — Stable production-ready code
* develop — Active development
* feature/your-feature-name — Feature branches for new work

Before starting work, pull the latest changes from develop:
[git checkout develop]
[git pull origin develop]
[git checkout -b feature/your-feature-name]

### 📝 Commit Guidelines
Follow this format for commit messages:
type: short description

Examples:
feat: added login test case for valid user
fix: resolved null pointer exception in base class
docs: updated README with setup steps

Commit often and keep changes small and focused.

### 🧪 Run Tests Before Push
Always ensure your changes pass locally:
[mvn clean test -DsuiteXmlFile=xml/full_regression.xml]

If using Grid:
[docker-compose -f src/test/resources/docker-compose.yaml up -d]

### 🔍 Pull Request Process
Push your feature branch:
[git push origin feature/your-feature-name]

Open a Pull Request to the develop branch.
Ensure your PR includes:
A clear title and description
Reference to any related issues
Screenshots or logs if applicable


### 💡 Code Style & Standards
* Follow Java naming conventions
* Use the existing Page Object Model structure
* Log actions where applicable using Log4j2
* Maintain consistency in method and variable naming
* Add comments to complex logic

### 🤝 Need Help?
Reach out via email: maherabubakar80@gmail.com 
Or connect on LinkedIn (https://www.linkedin.com/in/abubakar-saleem/)

Thank you for contributing!

