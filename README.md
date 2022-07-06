# p0: Michael's banking app

This is a small text based application that simulates a banking application.

## Description

Application users are presented with a small menu and given a prompt based
on the options in the menu. Once a user enters a selection, the application
moves to a new state and the process repeats with a new menu and a new prompt.

![alt text](../media/menu.png?raw=true)

With this interface, users can create a system account or login to an existing
account. Once logged into the system, users can create a new bank account,
deposit money into an existing account, view the transaction history, and so on.

## Database connection

The application stores the information it needs to operate in a PostgreSQL
database.

![alt text](../media/postgres-p0.png?raw=true)

## Environment variables

The application reads the information it needs to connect to the database
from 3 environment variables at startup. The variables that must be set are:

|Variable| Meaning |
|--------|---------|
|DB_URL  | The URL of the running database instance. |
|DB_USER | A user with read and write access to the database.|
|DB_PASS | The user's password.|


