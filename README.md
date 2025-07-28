# 🚌 RedBus Automation Assignment – Filter & Fetch Bus Operators

This project automates a real-world scenario on the [RedBus] (https://www.redbus.in/) website:  
**Search buses between two cities, apply filters, extract operator names, and validate the displayed count.**

🚀 Built as a part of a hands-on automation challenge to demonstrate DOM interaction, synchronization handling, and dynamic data extraction using Selenium WebDriver.

---

## 🎯 Objective

Automate the following flow:

  - Search for buses from **Location A to Location B**
  - Apply **Primo** and **Evening** time filters
  - Scroll through all available bus results (handle lazy loading)
  - Extract and print all **bus operator names**
  - Verify that the number of displayed buses matches the actual count listed on the page

---

## 🛠 Tech Stack

  - **Language:** Java 11  
  - **Automation Tool:** Selenium WebDriver v4.3.3  
  - **Build Tool:** Maven  
  - **Locators:** XPath  
  - **IDE:** Eclipse  
  - **Browser:** Google Chrome

---

## ⚙️ Key Operations Performed

  1. **Launches RedBus** using Selenium WebDriver  
  2. **Searches cities** from source & destination dropdowns after suggestions are fully loaded  
  3. **Applies Primo and Evening filters**  
  4. **Captures total bus count** after filters  
  5. **Scrolls through all bus operator listings**, handling lazy loading  
  6. **Prints each bus operator name**  
  7. **Verifies** that the number of listed operators matches the filtered count shown on the UI

---

## 💡 Notable Techniques & Insights

  ✔️ **Maximized browser** using `ChromeOptions` argument `--start-maximized` instead of `.manage().window().maximize()`
  
  ✔️ **Explicit waits (WebDriverWait)** used for stable element interactions and synchronization
  
  ✔️ **Handled input focus dynamically** by switching to active element using java:  
      driver.switchTo().activeElement().sendKeys("cityName");
      
  ✔️ Captured auto-collapsing dropdown locators by pausing execution via readystatechange event or setting event breakpoints in Chrome DevTools (Sources tab)
  
  ✔️ Used XPath chaining to efficiently locate nested dropdown values and bus operator elements
  
  ✔️ Managed lazy-loaded DOM by auto-scrolling to fetch all dynamically rendered results
  

📽️ Demo

![Demo](Red%20Bus%20Automation%20Assignment.gif)

📁 How to Run

  1. Clone the repository:
        git clone https://github.com/NitishBahe/RedBusAutomationAssignment.git
  2. Import in your IDE (IntelliJ/Eclipse)
  3. Ensure dependencies are installed:
        mvn clean install
  4. Run the script from RedBusAssignment.java or relevant main class

🙏 Acknowledgements
    1. Special thanks to **Jatin Sharma** for mentoring and guidance
    2. Challenge inspired by real-world automation scenarios


📌 Disclaimer
    
  This project is intended for learning and demonstration purposes only. It interacts with a publicly accessible site in read-only mode and does not perform any real bookings or submissions.

🔖 Tags

  #selenium #java #automation #xpath #redbus #sdet #automationchallenge #qualityengineering
