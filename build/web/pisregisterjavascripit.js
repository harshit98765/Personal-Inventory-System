
function Validation() {

    var pass1 = document.f1.pass1.value;
    var pass2 = document.f1.pass2.value;
    var phone = document.f1.phone.value;


    if (pass1 == "") {
        alert("Please enter Password !");
        document.f1.pass1.focus();
        return false;
    }
    if (pass2 == "") {
        alert("Please RE-enter Password !");
        document.f1.pass2.focus();
        return false;
    }
    if (pass1 != pass2) {
        alert("Password does not match");
        document.f1.pass1.value = "";
        document.f1.pass2.value = "";
        document.f1.pass1.focus();
        return false;
    }


    var phoneno = /^\d{10}$/;

    if (phone.match(phoneno))
    {
        return true;
    } else
    {
        alert("Not a valid Phone Number");
        document.f1.phone.value = "";
        document.f1.phone.focus();
        return false;
    }
}


