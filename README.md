# Purpose

Project in practicing CSS styling & React fundamentals

# Demo Video & Images

---- WORK IN PROGRES ---

# Description

## What does this application do?

It is a mock website of a typical e-commerce shopping store. This project uses a free API to fetch product data. Users can do the following:

- Browse the catalogue from different categories
- Add/remove/update product items inside their cart

## Technologies Used

The main tools are React and Sass.
In this project, I structure my project based on "Directory per Component" to make my project clean (for more information: https://survivejs.com/books/react/advanced-techniques/structuring-react-projects/
)

Additionally, I used CSS Modules.

## Challenges Faced

1. Prop Drilling

PROBLEM - CartItems have features such as adding, deleting, and updating. Components that signal these actions are deeply nested & are not parent/child. This leads to issue of prop-drilling.

SOLUTION - useContext has helped me since I do not have to prop drill and the components that need that state can do so easily with this hook.

2. findIndex()

PROBLEM - Initial bug was that cartItems was adding the same item into the array.

SOLUTION - Change the following from: cartItems.findIndex((elem) => elem.id == id) > 0 to cartItems.findIndex((elem) => elem.id == id) >= 0

If we are updating our item that is in the first-index, it will give the index 0. I should have used >= to ensure that the first index is included.

# TODO:

### Cart & CartItem

- ~~Implement counter for number of products to add to cart~~
- CSS Styling & Some animations
  -- Fix dedicated spacing for elements inside CartItem.jsx

### Checkout Page

- Fix up styling on page
- Implement PayPal & GPay icons into buttons

### Product Page

- Add animation that increments number in cart when clicking "Add to cart"

### Home Page

- Add a brief home page

### Catalogue Page

- Add loading screen or spinning circles as API data is being loaded in

# Credits

Thanks to FakeStoreAPI for the free API to use: https://fakestoreapi.com/

Thanks to FrontendMentor for the free Figma Design: https://www.frontendmentor.io/challenges/ecommerce-product-page-UPsZ9MJp6

Thanks to OdinProject for their learning path: https://www.theodinproject.com/paths/full-stack-javascript/courses/react
