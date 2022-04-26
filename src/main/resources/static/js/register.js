var app = angular.module("brosApplication", []);

app.controller("registerController", function($scope, $http, $window) {
  $scope.userForm = {
    firstName: "", middleName: "", lastName: ""
    , phoneNumber: "", zaloNumber: ""
    , email: ""
    , password: "", registerConfirmPassword: ""
  };

  $scope.registerUser = function() {
    console.log("registerUser() is called")
    $http({
      method: "POST",
      url: "/register",
      data: angular.toJson($scope.userForm),
      headers: { 'Content-Type': 'application/json' }
    }).then(__success, __error);
  };

  function __success(res) {
    alert("Register successfully.");
    $window.location.href = "/login";
  }

  function __error(res) {
    var status = res.status;
    alert("Error: " + status);
  }
});