# Backend
Create the login/sign up and storing of the user information.

Creates the endpoints to be used for the frontend

# Issues that Occured
Some issues that occured as I was working through my project:
- Studying up how to handle setting up Checkout for users
  - Learned: best to calcualte the cost on the backend. Users can send malicious  cost 
  
# Study Notes
Important Spring & Stripe concepts I encounter or learned.

## Spring Security
### 1) **USER**
1) UserDetails (INTERFACE) - describes the user's username & password
2) UserDetailsService (INTERFACE) - get info on user and manage them 
   3) UserDetailsManager (INTERFACE) - extends the UserDetailsService and provides more method to manage user

### 2) **PASSWORD**
1) PasswordEncoder (INTERFACE) - encodes the password based on some encryption algo provided
   2) BCrypt
3) DelegatingPasswordEncoder (INTERFACE) - extends PasswordEncoder to handle multiple passwords and can have a default password enocder


### 3) **SECURITY FILTER CHAIN**

A chain filters that HTTP Request must go through. Each filter applies some logic to the HTTP Request before passing that modified HTTP Request 
down to the next filter. 

Ex) Filter Chain that checks if there is some HTTP Header inside the HTTP Request. If there's no 
HTTP Header, will not pass it down to the next filter, instead it will return back a 400 HTTP Response back to user

### 4) **User Authentication**

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

## Stripe API
Backend should be responsible for calling 3rd Party APIs. As API keys are important to keep a secret from users. 
Even if you attempt to hide the API key by using a .env file and hiding it in .gitignore, users may still be able to sniff
out the API key used (through HTTP Request, looking at source code)


Strip utilizes two types of keys: 1) Public Key 2) Secret (Private) Key

- Secret (Private) Key is used to be able to make API request to Stripe (ex. Refund customers, Change payment)
- Public Key is free to be seen as that is a key used by customers to start the checkout process

### Checkout Session vs PaymentIntent
Checkout Session has Stripe making a pre-built checkout page for you (no coding 
done in your end for this)

PaymentIntent enforces custom Stripe implementation. PaymentIntent represent
customer's payment lifecycle (ex. keep track of failed payment attempt 
and ensure customer is only charged once)

## Spring 
Some unrelated Spring Security concepts I encounter while working:

### 1) .properties files
   - Can have multiple .properties files (ex. application-dev.properties or application-prod.properties)
   - these properties files are used for specific environments (we may change the database)
     - Ex) DB for testing and DB for production 
   - application.properties (default prop) can referrence values from other .properties files.
     - First, we need to activate the other properties file by doing: **spring.profiles.active=[NAME]**
     - Where **[NAME]** is the word right after the dash (-) (ex. dev or prod)


### 2) Field Injection vs Constructor Injection
   - These are 2 ways to inject a dependency
   - Field Injection is not recommended 
     - causes possible NullPointerExceptions
     - If you attempt to use the Field Injected value (such as inside the constructor) you will
   get NullPointerException as Spring will use the default constructor if you do not specify a 
   constructor with some arguments. 
     - Field Injection --> Creates the instance first then inject the values
     - Constructor Injection --> Creates the dependency instances first then creates the instances with 
     the dependency in constructor

**FIELD INJECTION**
```java
@Component
public class YourComponent {

    @Autowired
    private AnotherComponent anotherComponent;

    public void doSomething() {
        anotherComponent.performAction();
    }
}
```

**CONSTRUCTOR INJECTION**
```java
@Component
public class YourComponent {

    private final AnotherComponent anotherComponent;

    @Autowired
    public YourComponent(AnotherComponent anotherComponent) {
        this.anotherComponent = anotherComponent;
    }

    public void doSomething() {
        anotherComponent.performAction();
    }
}
```

**ALT. CONSTRUCTOR INJECTION** 
```java
@Component
public class YourComponent {

    private final AnotherComponent anotherComponent;

    @Autowired
    public YourComponent(AnotherComponent anotherComponent) {
        this.anotherComponent = anotherComponent;
    }

    public void doSomething() {
        anotherComponent.performAction();
    }
}
```

**FIELD INJECTION CAN CAUSE NullPointerException**
```java
@Component
public class YourComponent {

    @Autowired
    private AnotherComponent anotherComponent;

    public YourComponent() {
        // At this point, anotherComponent is still null
        anotherComponent.doSomething(); // NullPointerException here
    }
}
```
### 3) Injecting values - @Value
- If we want to use @Value inside our code, we must do so like this:
       ```java
         @Value("${API_KEY}")
        private String API_KEY;
       ```


- @PostConstruct
    - This is an annotation that is applied to a function
    - It will execute the code inside the function AFTER ALL DEPENDENCY INJECTION IS COMPLETE
      In Stripe, we must do the following to use our secret key: **Strip.apiKey = API_KEY**
    - useful when we use **Field Injection**

If you wish to continue using Field Injection (not recommended) ensure you use @PostConstruct to avoid
NullPointerException 
       
**FIELD INJECTION & @PostConstruct**
```java
@Component
public class YourComponent {

    @Autowired
    private AnotherComponent anotherComponent;

    public YourComponent() {
        // Avoid using anotherComponent here
    }

    @PostConstruct
    public void init() {
        // Now anotherComponent is initialized and safe to use
        if (anotherComponent != null) {
            anotherComponent.doSomething();
        }
    }
}
```
### 4) Exception Handling
Ways to handling exceptions:
1) Controller Level
2) Global Level

#### Controller Level
@ExceptionHandler(<EXCEPTION.class>)
- annotate a method with this annotation. 
- the method annotated with this annotation should be responsible for handling the exception that is thrown from another method 

```java
@Controller
public class MyController {

    @GetMapping("/someEndpoint")
    public String someMethod() {
        // some logic that might throw an exception
        if (someCondition) {
            throw new MyCustomException("Something went wrong");
        }
        return "someView";
    }

    @ExceptionHandler(MyCustomException.class)
    public String handleMyCustomException(MyCustomException ex, Model model) {
        // handle the exception and return a view name
        model.addAttribute("errorMessage", ex.getMessage());
        return "errorView";
    }
}
```

Methods annotated with @ExceptionHandler can accept a flexible arguments. We can get arguments relevant to context information 

Ex) 
- Model 
- HttpServletRequest & HttpServletResponse
- Authentication 

Additionally, we can return different types
- ResponseEntity
- String
- ResponseBody

#### Global Level
Use: @ControllerAdvice (or @RestControllerAdvice) + @ExceptionHandler on a class 

This will handle all exception thrown from any @Controller class.

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle a specific custom exception
    @ExceptionHandler(MyCustomException.class)
    public ResponseEntity<String> handleMyCustomException(MyCustomException ex, WebRequest request) {
        String errorMessage = "Custom error: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    // Handle a generic exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex, WebRequest request) {
        String errorMessage = "An error occurred: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle an exception and return a view
    @ExceptionHandler(AnotherCustomException.class)
    public String handleAnotherCustomException(AnotherCustomException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "errorView";
    }
}
```

### MongoDB
