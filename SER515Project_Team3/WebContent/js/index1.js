$(function() {
    $('#addressYears').change(function() {
      var userNumber = $(this).val();
      if (userNumber <= 0) {
        //If DD value is zero: empty fields and hide form
        $("#userFields").empty();
        $("#userForm").css('display', 'none');
      } else {
        //Empty form fields
        $("#userFields").empty();
        //Dynamically create input fields based on user selection
        create(userNumber);
        // Append submit button to bottom of form
        $("#userFields").append($("<input/>", {
          type: 'submit',
          value: 'Submit'
        }))
      }
    });
    
  function create(userNumber) {
    for (var i = 1; i <= userNumber; i++) {
    $("#userForm").fadeIn(600);
    $("userForm").append($("#userFields").append($("<div/>", {
      id: 'heading'}).append($("<h3/>").text("User " + i)), 
      $("<input/>", {
        type: 'text',
        placeholder: 'Name ' + i,
        name: 'name_' + i
        }), $("<br/>"), 
      $("<input/>", {
        type: 'text',
        placeholder: 'Email ' + i,
        name: 'email_' + i
        }), $("<br/>"), 
      $("<textarea/>", {
        placeholder: 'Message' + i,
        type: 'text',
        name: 'msg_' + i
        }), 
      $("<hr/>")))
      }
    }
  });