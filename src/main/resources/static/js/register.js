app.controller("registerController", function($scope, $http, $window) {
  $scope.loading = false;
  $scope.error = new BrosErrors();
  $scope.form = {
    firstName: ""
    , middleName: ""
    , lastName: ""
    , phoneNumber: ""
    , zaloNumber: ""
    , email: ""
    , password: ""
    , registerConfirmPassword: ""
  };

  $scope.registerUser = function() {
    console.log("registerUser() is called");
    $scope.error.clear();
    __toggleLoader();

    $http({
      method: "POST",
      url: "/register",
      data: angular.toJson($scope.form),
      headers: { 'Content-Type': 'application/json' }
    }).then(__success, __error);
  };

  function __toggleLoader() { $scope.loading = !($scope.loading); }
  function __success(res, d1, d2, d3) {
    __toggleLoader();
    alert("Register successfully.");
    $window.location.href = "/login";
  }
  function __error(res) {
    __toggleLoader();
    var errors = res.data.errors.errors;
    if (errors !== undefined && errors.length > 0)
      errors.forEach(function(i){ $scope.error.add(i.field, i.message); });
  }
});