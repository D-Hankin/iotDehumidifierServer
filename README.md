IoT Dehumidifier Server
=======================

Project Overview
----------------

This project is the server-side component of the IoT Dehumidifier system, a school project focused on monitoring humidity levels using an Arduino R4 WiFi Uno. The server handles data storage, email notifications, and communication with the React/TypeScript client, which displays daily statistics and electricity prices.

**Please note:** All references to actually controlling the dehumidifier will be included in version 2, which is coming soon. Currently, the server processes data and sends notifications but does not directly control the dehumidifier.

Features
--------

-   **Data Handling:** The server stores humidity sensor readings in a MongoDB database.
-   **Email Notifications:** Sends email alerts when humidity exceeds 55%, prompting users to manually activate their dehumidifier.
-   **Client Integration:** Provides data to the React/TypeScript client for daily humidity statistics and electricity prices.
-   **Spring Security and JWTs:** Utilises both Spring Boots own security routines combined with the creation and processing of Json Web Tokens for enhanced user security.

Client and Arduino
------------------

-   **Client:** The client interface displays daily statistics and electricity prices. It can be found [here](https://github.com/D-Hankin/iotDehumidifierClient).
-   **Arduino:** The server receives humidity data from an Arduino R4 WiFi Uno device running the sensor logic. The Arduino code can be found [here](https://github.com/D-Hankin/iotDehumidifier).

Technologies Used
-----------------

-   **Spring Boot** for building the server-side application.
-   **MongoDB** for storing humidity data.
-   **JavaMailSender** for sending email notifications.
-   **React & TypeScript** on the client-side.

How It Works
------------

1.  **Receiving Data:** The server receives humidity data from the Arduino device via HTTP requests.
2.  **Storing Data:** Humidity data is stored in a MongoDB database.
3.  **Sending Notifications:** When humidity exceeds 55%, the server sends an email notification using JavaMailSender.
4.  **Client Interaction:** Data is served to the React client, which displays humidity levels and electricity prices for the day.

Installation and Setup
----------------------

1.  Clone the repository to your local machine.
2.  Configure the `application.properties` file with the following required environment variables:
    -   `MONGODB_CONNECTION_URI`
    -   `MONGODB_DATABASE`
    -   `EMAIL_HOST`
    -   `EMAIL_PORT`
    -   `EMAIL_ADDRESS`
    -   `EMAIL_PASSWORD`
3.  Make sure MongoDB and the email configuration are properly set up and running.
4.  Start the Spring Boot application using the `mvn spring-boot:run` command.

Environment Variables
---------------------

-   `MONGODB_CONNECTION_URI`: MongoDB connection URI for the database.
-   `MONGODB_DATABASE`: Name of the MongoDB database.
-   `EMAIL_HOST`: SMTP host for sending emails.
-   `EMAIL_PORT`: SMTP port for email communication.
-   `EMAIL_ADDRESS`: Email address used for sending notifications.
-   `EMAIL_PASSWORD`: Password for the email address.

Future Enhancements (Version 2)
-------------------------------

-   Integration with the dehumidifier to automatically control it based on humidity levels.
-   Additional API endpoints for more advanced data processing.
-   Enhanced email notifications with customizable thresholds.
