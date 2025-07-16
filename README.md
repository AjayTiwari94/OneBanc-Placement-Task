# OneBanc-Placement Task – MPIN Validator (Java Project)

This Java console application was developed as part of the OneBanc placement task. It validates 4-digit and 6-digit MPINs (Mobile Personal Identification Numbers) by checking for common weaknesses based on user demographics and known insecure patterns.

## Features

- Supports both 4-digit and 6-digit MPINs
- Detects commonly used or easily guessable MPINs (e.g., `1234`, `000000`, `121212`)
- It checks if MPIN matches any personal demographic data like:
    - Date of Birth (Self)
    - Spouse's Date of Birth
    - Wedding Anniversary
- Provides output:
    - `Strength`: STRONG or WEAK
    - If WEAK, lists reasons like:
        - `COMMONLY_USED_MPIN`
        - `DEMOGRAPHIC_DOB_SELF`
        - `DEMOGRAPHIC_DOB_SPOUSE`
        - `DEMOGRAPHIC_ANNIVERSARY`

## Sample Run
- Enter your MPIN (4 or 6 digits): 181203
- Enter your Date of Birth (DDMMYYYY): 18122003
- Enter Spouse's Date of Birth (DDMMYYYY):
- Enter Wedding Anniversary (DDMMYYYY):

===== MPIN Analysis Result =====
- MPIN Strength: `WEAK`
- Reasons:`DEMOGRAPHIC_DOB_SELF`


## How to Compile and Run

1. **Clone the Repository**
   ````bash
   git clone https://github.com/YOUR_USERNAME/OneBanc-Placement-Task.git
   cd OneBanc-Placement-Task

2. **Compile Both Java Files**
    ````bash
   javac src/mpinTask.java src/mpinValidation.java

3. **Run the Application**
    ````bash
   javac src/mpinTask.java src/mpinValidation.java

## Project Structure
````bash
    OneBanc-Placement Task/
    ├── src/
    │   ├── mpinTask.java         # Main class with user input & validation
    │   └── mpinValidation.java   # Additional validation logic (if used)
    ├── README.md
````
## Author
Ajay Tiwari
B.Tech Final Year – Computer Science (Specialization in Artificial Intelligence).
Passionate about Data Analytics, Java, and Secure System Design.

## License
MIT License – Feel free to use, modify, and share with attribution.