var app = angular.module("brosApplication", []);

app.controller("registerController", function($scope, $http, $window) {
  $scope.loading = false;
  $scope.userForm = {
    firstName: "", middleName: "", lastName: ""
    , phoneNumber: "", zaloNumber: ""
    , email: ""
    , password: "", registerConfirmPassword: ""
  };

  $scope.registerUser = function() {
    console.log("registerUser() is called")
    __toggleLoader()
    $http({
      method: "POST",
      url: "/register",
      data: angular.toJson($scope.userForm),
      headers: { 'Content-Type': 'application/json' }
    }).then(__success, __error);
  };

  function __toggleLoader() { $scope.loading = !($scope.loading); }

  function __success(res) {
    __toggleLoader()
    alert("Register successfully.");
    $window.location.href = "/login";
  }

  function __error(res) {
    __toggleLoader()
    var status = res.status;
    alert("Error: " + status);
  }
});