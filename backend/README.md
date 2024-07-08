# Backend
Create the login/sign up and storing of the user information.

Creates the endpoints to be used for the frontend

## Study Notes
Important Spring Security Components:

**USER**
1) UserDetails (INTERFACE) - describes the user's username & password
2) UserDetailsService (INTERFACE) - get info on user and manage them 
   3) UserDetailsManager (INTERFACE) - extends the UserDetailsService and provides more method to manage user

**PASSWORD**
1) PasswordEncoder (INTERFACE) - encodes the password based on some encryption algo provided
   2) BCrypt
3) DelegatingPasswordEncoder (INTERFACE) - extends PasswordEncoder to handle multiple passwords and can have a default password enocder


**SECURITY FILTER CHAIN**

A chain filters that HTTP Request must go through. Each filter applies some logic to the HTTP Request before passing that modified HTTP Request 
down to the next filter. 

Ex) Filter Chain that checks if there is some HTTP Header inside the HTTP Request. If there's no 
HTTP Header, will not pass it down to the next filter, instead it will return back a 400 HTTP Response back to user

**User Authentication**

Necessary component to use UserDetailsService/Manager & PasswordEncoder together. 

Responsible for logic of authentication

1) Authentication - specific type of authentication (with info sent from HTTP Request)
   2) Ex
     2) Phone SMS
     3) OAuth
     4) Username/password
     5) Biometric (ex. fingerprint + username)


2) AuthenticationProvider - handles the specific Authentication
   3) Can have multiple of these - one handles the OAuth, the other Biometric , etc.
   
4) AuthenticationManager - manages the multiple AuthenticationProviders  