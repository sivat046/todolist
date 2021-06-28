<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>CodeDNA</title>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css'><link rel="stylesheet" href="../../css/login.css">
        <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
        <link href="<c:url value="../../css/login.css" />" rel="stylesheet">
    </head>
    <body>
    <!-- partial:index.partial.html -->
        <h2>CodeDNA</h2>
    </br>
    <div class="container" id="sign-in-container">
        <div class="form-container sign-up-container">
            <form action="#">
                <h1>Create Account</h1>
                <span>or use your email for registration</span>
                <input type="text" placeholder="Name" />
                <input type="email" placeholder="Email" />
                <input type="password" placeholder="Password" />
                                <div> <a class="button" href="/add-todo">Add a Todo</a> </div>
                <button>Sign Up</button>
            </form>
        </div>
        <div class="form-container sign-in-container">
            <form method="POST" action="/createAccount" >
                <h1>Sign in</h1>
                <span>or use your account</span>
                <%--                <input type="email" placeholder="Email" />--%>
                <input name="username" type="text" placeholder="Username"
                       autofocus="true"/>
                <input name="email" type="email" placeholder="Email" />
                <input name="password" type="password" placeholder="Password"/>
                <input name="repassword" type="password" placeholder="Password Again"/>
<%--                <a href="#">Forgot your password?</a>--%>
            </br>
                <button>Create Account</button>
            </form>
        </div>
<%--        <form method="GET" action="/signup" >--%>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p>To keep connected with us please login with your personal info</p>
                        <button class="ghost" id="signIn">Sign In</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>CodeDNA</h1>
                        <p>Welcome to Our Website</p>
<%--                        <button class="ghost" id="signUp">Sign Up</button>--%>

                    </div>

                </div>
            </div>
<%--        </form>--%>
    </div>

    <!-- partial -->
    <%--    <script  src="./script.js"></script>--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
