# Heritage Hub

## Microservice Overview

This project implements a microservice responsible for sending confirmation emails for the mail used during registration. Unconfirmed users receive the USER role, limiting their ability to add or rate objects. Users are allowed only one rating per object, with the option to modify their previous ratings. Tokens expire after 2 days, and a button on the home page allows users to resend a confirmation email.

## Design Patterns

- **Factory Pattern:** Objects are created using the MonumentFactory class.
- **Strategy Pattern:** Language switching is implemented using the Strategy pattern, with strategies obtained through LanguageStrategyFactory.
- **Command Pattern:** Going back or pressing the Back button is implemented using the Command pattern.
- **MVC Pattern:** The application follows the Model-View-Controller design pattern.
- **Singleton Pattern:** Beans in the application are implemented as Singletons.

## Hosting

The application, microservice, and database are hosted on Heroku.

## Usage

For presentation purposes, predefined users are available:
- admin-admin (ROLE_ADMIN): Allowed to delete and edit.
- user-user (ROLE_CONFIRMED): Confirmed user, allowed to add and rate.
- user1-user1 (ROLE_USER): Unconfirmed user, limited to browsing.

Tokens expire after 2 days. Users can resend a confirmation email from the home page.

## Links
- https://heritage-hub-mk-deb79ca264f9.herokuapp.com/ - The app
- https://mail-microservice-116ef2400221.herokuapp.com/ - The microservice (just home page, we don't need UI)