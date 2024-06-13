<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EliteRentals</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Merriweather:ital,wght@0,300;0,400;0,700;0,900;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
  body {
    margin: 0;
    padding: 0;
    font-family: 'Roboto', sans-serif;
    background-image: url('image/pexels-sevenstormphotography-443383.jpg');
    background-size: cover;
    background-position: center;
    height: 100vh; /* Adjusted to fit the viewport height */
  }

  nav {
    background-color: #FF204E;
    color: #fff;
    padding: 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-family: 'Roboto', sans-serif;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
  }

  nav img {
    max-height: 30px;
    margin-right: 10px;
  }

  nav a {
    color: #fff;
    text-decoration: none;
    margin: 0 10px;
    transition: all 0.3s ease-in-out;
  }

  nav a:hover {
    text-decoration: underline;
    transform: scale(1.1);
  }

  .dropdown {
    position: relative;
    display: inline-block;
  }

  .dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
    z-index: 1;
  }

  .dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
  }

  .dropdown-content a:hover {
    background-color: #ddd;
  }

  .dropdown:hover .dropdown-content {
    display: block;
  }

  .dropbtn i.fa-caret-down {
    margin-left: 5px;
  }

  .content {
    margin-top: 100px;
    text-align: center;
  }

  .search-container {
    display: inline-block;
    text-align: center;
    width: 75%; 
    max-width: 700px; 
    margin: 0 auto;
    margin-top:6.5%;
  }

  .search-container form {
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: rgba(255, 255, 255, 0.7); /* Added opacity */
    border: 2px solid #FF204E;
    border-radius: 20px;
    padding: 20px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  }

  .search-container select,
  .search-container input[type="number"] {
    border: none;
    padding: 8px;
    font-family: 'Roboto', sans-serif;
    font-size: 16px;
    outline: none;
    margin-right: 10px;
    border-radius: 5px;
  }

  .search-container button {
    border: none;
    background-color: #FF204E; /* Red color */
    color: white;
    cursor: pointer;
    transition: background-color 0.3s ease;
    border-radius: 20px;
    padding: 8px 20px;
    font-size: 14px;
  }

  .search-container button i {
    color: white; /* Search icon color */
  }

  .search-container button:hover {
    background-color: red; /* Dark red on hover */
  }

  /* Styling for Buy and Sell buttons */
  .dropdown-buttons .dropdown button.dropbtn {
    background-color: transparent;
    color: #fff;
    border: none;
    padding: 10px 15px;
    font-size: 16px;
    cursor: pointer;
    border-radius: 5px;
    transition: background-color 0.3s ease;
  }

  .dropdown-buttons .dropdown button.dropbtn:hover {
    background-color: #333;
  }

  /* Bold text styling */
  .bold-text {
    font-weight: bold;
    font-size: 35px;
    margin-bottom: 20px;
    font-family: 'Merriweather', serif;
  }

  
  .post-property-button a {
    background-color: white;
    color: #FF204E;
    text-decoration: none;
    padding: 10px 20px;
    border-radius: 20px;
    font-weight: bold;
    font-family: 'Roboto', sans-serif;
    transition: background-color 0.3s ease;
    position: relative;
  }

  .post-property-button a:before {
    content: '\f005';
    color:#FFD505;
    font-family: 'Font Awesome 5 Free';
    position: absolute;
    left: 3px;
    top: 50%;
    transform: translateY(-50%);
  }

  .post-property-button a:hover {
    background-color: #f5f5f5;
  }

 
  .search-container .location-icon {
    color: red;
    margin-right: 5px;
  }

 
  .search-container .house-icon {
    color: red;
    margin-right: 5px;
  }


  .search-container input[type="number"]::-webkit-inner-spin-button,
  .search-container input[type="number"]::-webkit-outer-spin-button {
    -webkit-appearance: none;
    margin: 0;
  }
</style>
</head>
<body>
    <nav>
        <div>
            <img src="image/EliteRentalslogo.jpg" alt="EliteRentals Logo">
        </div>

        <div class="dropdown-buttons">
            <div class="dropdown">
                <button class="dropbtn">
                    Buy <i class="fas fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="http://localhost:8080/PropertyRentLease/AdminDashboard">Buy Flat</a> <a href="#">Buy House</a> <a href="http://localhost:8080/PropertyRentLease/AdminDashboard">Buy
                        PG Hostel</a>
                </div>
            </div>
            <div class="dropdown">
                <button class="dropbtn">
                    Sell <i class="fas fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="#">Rent House</a> <a href="#">Rent Flat</a> <a href="#">Rent
                        PG</a>
                </div>
            </div>
        </div>

        <div class="login-signup">
            <a href="Homepage.jsp">Login</a> <a href="RegisterPage.jsp">Signup</a>
        </div>

        <div class="post-property-button">
            <a href="PostProperty.jsp"> <span>Post Property</span></a>
        </div>
    </nav>
    <div class="content">
        <div class="bold-text">
            Welcome back! Let's continue your search
        </div>
        <div class="search-container">
            <form action="#" method="GET">
                <select name="location" id="location" required>
                    <option value="">Select Location</option>
                    <option value="Madurai">Madurai</option>
                    <option value="Coimbatore">Coimbatore</option>
                    <option value="Chennai">Chennai</option>
                    <option value="Trichy">Trichy</option>
                    <option value="Theni">Theni</option>
                </select>
                <select name="budget" id="budget" required>
                    <option value="">Select Budget</option>
                    <option value="1000">1000</option>
                    <option value="5000">5000</option>
                    <option value="10000">10000</option>
                    <option value="20000">20000</option>
                    <option value="50000">50000</option>
                </select>
                <button type="submit">
                    <i class="fas fa-search"></i>
                </button>
            </form>
        </div>
    </div>
    
</body>
</html>
