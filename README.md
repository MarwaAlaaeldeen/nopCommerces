# 🧪 nopCommerce Selenium Automation Framework

This project is a Selenium + TestNG automation framework built to test the demo e-commerce website:
https://demo.nopcommerce.com/

The framework is designed using Page Object Model (POM) and follows clean test automation practices.

---

## 🛠️ Tech Stack

- Java 11+
- Selenium WebDriver 4
- TestNG
- Maven
- Page Object Model (POM)
- ChromeDriver

---

## 📁 Project Structure

src/test/java
│
├── pages/          → Page Object classes (Home, Login, Register, Cart, Product)
├── tests/          → Test classes (Registration, Login, Cart)
├── utils/          → Config reader & utilities

---

## 🚀 How to Run the Tests

### Using Maven:
```bash
mvn clean test
