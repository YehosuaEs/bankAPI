# Bank API

## Requirements
- The system must have 4 types of accounts: StudentChecking, Checking, Savings, and CreditCard.
- The system must have 3 types of Users: Admins and AccountHolders.
- Admins can create new accounts. When creating a new account they can create Checking, Savings, or CreditCard Accounts. Also, Admins must be added the Third-party users.
- Account Access
  - Admins should be able to access the balance for any account and to modify it.
  - AccountHolders should be able to access their own account balance.
  - Account holders should be able to transfer money from any of their accounts to any other account (regardless of owner). The transfer should only be processed if the account has sufficient funds. The user must provide the Primary or Secondary owner name and the id of the account that should receive the transfer.
  - There must be a way for third-party users to receive and send money to other accounts. In order to receive and send money, Third-Party Users must provide their hashed key in the header of the HTTP request. They also must provide the amount, the Account id and the account secret key.

## Technical Requirements
1. Include a Java/Spring Boot backend.
2. Everything should be stored in MySQL database tables.
3. Include at least 1 GET, POST, PUT/PATCH, and DELETE route.
4. Include authentication with Spring Security.
5. Include unit and integration tests.
6. Include robust error handling.
7. You must use the Money class for all currency and BigDecimal for any other decimal or large number math.

<div align="center">
 <img src="https://user-images.githubusercontent.com/93733677/197409896-c5d99026-5b50-4384-b52c-bfe72231bd2b.jpg" />
</div>
<div align="center">
 <img src="https://user-images.githubusercontent.com/93733677/197410674-54d55a34-5979-4749-a2f3-60c064e74195.jpg" />
</div>