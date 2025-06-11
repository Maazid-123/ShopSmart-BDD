# 🛒 ShopSmart-BDD Automation Framework

This is an end-to-end automation testing framework for the **ShopSmart demo e-commerce site**, built using:

- **Java**
- **Selenium WebDriver**
- **Cucumber (BDD)**
- **Maven**
- **Apache POI (Excel data support)**

---

## 📌 Features

- ✅ BDD Scenarios using Gherkin syntax
- ✅ Page Object Model (POM) structure
- ✅ Search functionality from Excel test data
- ✅ Add to cart and guest checkout flow
- ✅ Billing, shipping, payment, and order confirmation
- ✅ JavaScript-based click handlers and wait stability
- ✅ Clear logging and step-by-step console output

---

## 📁 Project Structure

ShopSmart-BDD/
├── src/
│ ├── main/
│ │ └── java/
│ │ └── pages/ # All Page Objects (LoginPage, CheckoutPage, etc.)
│ ├── test/
│ │ └── java/
│ │ ├── stepdefinitions/ # Cucumber step definitions
│ │ ├── runners/ # Test runner class
│ │ └── features/ # .feature files (BDD scenarios)
├── testdata/
│ └── searchData.xlsx # Excel for product names
├── pom.xml # Maven dependencies and plugins


---

## 🧪 How to Run the Tests

### 💡 Prerequisites:
- JDK 11 or higher
- Maven
- Chrome browser
- ChromeDriver (compatible with your Chrome version)

### 🔧 To Execute Tests:

```bash
mvn clean test
🔍 Sample Test Scenarios
✅ Feature: Product Search and Checkout
gherkin
Copy
Edit
Given user is on the homepage
When user searches for product from Excel
And user adds the first product to the cart
And user proceeds to checkout
And user continues from billing address
And user continues from shipping address
And user continues from shipping method
And user continues from payment method
And user continues from payment information
And user confirms the order
Then user should see order success message

📦 Maven Dependencies (in pom.xml)
<dependencies>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.18.1</version>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.14.0</version>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>7.14.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.2.3</version>
    </dependency>
</dependencies>

🧠 Author
Maazid Shaik
Aspiring QA Automation Tester | Java + Selenium + BDD + Maven
GitHub: @Maazid-123

📄 License
This project is for educational and portfolio purposes only. No license is currently applied.

---
Would you like me to:
- Create this file and push it directly to your repo using GitHub Desktop steps?
- Add badges or CI integration like GitHub Actions later?

Let me know!
