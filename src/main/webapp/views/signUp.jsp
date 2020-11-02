<%--
  Created by IntelliJ IDEA.
  User: mvashkewi4
  Date: 02.11.2020
  Time: 14:57
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<div>
    <header>
        <h1>Sign Up</h1>
    </header>
    <main>
        <form action="" method="post">
            <fieldset>
                <p>
                    <label for="login">Login</label>
                    <input id="login" name="login" type="text">
                </p>
                <p>
                    <label for="password">Password</label>
                    <input id="password" name="password" type="password">
                </p>
                <p>
                    <label for="repeat_password">Repeat password</label>
                    <input id="repeat_password" name="repeat_password" type="password"></p>
                <p>
                    <label for="codeword">Codeword</label>
                    <input id="codeword" name="codeword" type="text">
                </p>
                <p>
                    <label for="email">E-mail</label>
                    <input id="email" name="email" type="email">
                </p>
            </fieldset>
            <fieldset>
                <legend>Passport data</legend>
                <p>
                    <label for="series">Series</label>
                    <input id="series" name="series" type="text">
                </p>
                <p>
                    <label for="number">Number</label>
                    <input id="number" name="number" type="text">
                </p>
                <p>
                    <label for="personal_number">Personal number</label>
                    <input id="personal_number" name="personal_number" type="text">
                </p>
                <p>
                    <label for="issue_date">Issue date</label>
                    <input id="issue_date" name="issue_date" type="date">
                </p>
                <p>
                    <label for="validity_date">Validity date</label>
                    <input id="validity_date" name="validity_date" type="date">
                </p>
                <p>
                    <label for="birth_date">Birth date</label>
                    <input id="birth_date" name="birth_date" type="date">
                <fieldset>
                    <legend>Sex</legend>
                    <input id="man" name="sex" type="radio" value="man">
                    <label for="man">Man</label>
                    <input id="woman" name="sex" type="radio" value="woman">
                    <label for="woman">Woman</label>
                </fieldset>
                <p>
                    <label for="authority">Authority</label>
                    <input id="authority" name="authority" type="text">
                </p>
                <p>
                    <label for="name">Name</label>
                    <input id="name" name="name" type="text">
                </p>
                <p>
                    <label for="surname">Surname</label>
                    <input id="surname" name="surname" type="text">
                </p>
                <p>
                    <label for="middlename">Middlename</label>
                    <input id="middlename" name="middlename" type="text">
                </p>
                <p>
                    <label for="name_eng">Name (English)</label>
                    <input id="name_eng" name="name_eng" type="text">
                </p>
                <p>
                    <label for="surname_eng">Surname (English)</label>
                    <input id="surname_eng" name="surname_eng" type="text"></p>
            </fieldset>
            <fieldset>
                <legend>Address</legend>
                <p>
                    <label for="country">Country</label>
                    <input id="country" name="country" type="text">
                </p>
                <p>
                    <label for="region">Region</label>
                    <input id="region" name="region" type="text">
                </p>
                <p>
                    <label for="district">District</label>
                    <input id="district" name="district" type="text">
                </p>
                <p>
                    <label for="settlement_type">Settlement type</label>
                    <select name="settlement_type" id="settlement_type">
                        <option value="city">City</option>
                        <option value="urban_village">Urban village</option>
                        <option value="agro-town">Agro-town</option>
                        <option value="village">Village</option>
                    </select>
                </p>
                <p>
                    <label for="settlement_name">Settlement name</label>
                    <input id="settlement_name"
                           name="settlement_name" type="text">
                </p>
                <p>
                    <label for="street_type">Street type</label>
                    <input id="street_type" name="street_type" type="text"
                </p>
                <p>
                    <label for="street_name">Street name</label>
                    <input id="street_name" name="street_name" type="text">
                </p>
                <p>
                    <label for="house">House</label>
                    <input id="house" name="house" type="number">
                </p>
                <p>
                    <label for="building">Building</label>
                    <input id="building" name="building" type="number">
                </p>
                <p>
                    <label for="flat">Flat</label>
                    <input id="flat" name="flat" type="number">
                </p>
                <p>
                    <label for="zipcode">Zipcode</label>
                    <input id="zipcode" name="zipcode" type="text">
                </p>
                <p>
                    <label for="phone_number">Phone number</label>
                    <input id="phone_number" name="phone_number" type="text">
                </p>
            </fieldset>
            <button>Sign up</button>
        </form>
    </main>
</div>
</body>
</html>
