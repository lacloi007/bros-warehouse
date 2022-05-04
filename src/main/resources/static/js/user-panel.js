app.config(function($routeProvider) {
  $routeProvider
    .when("/dashboard",        { templateUrl: "/html/user/U001_dashboard.html" , controller : "userDashboardCtrl" })
    .when("/receive",          { templateUrl: "/render?page=U010_receive"      , controller : "userReceiveCtrl"})
      .when("/receiveCreate",  { templateUrl: "/render?page=U011_receiveCreate", controller : "userReceiveCreateCtrl"})
    .when("/storage",          { templateUrl: "/html/user/U020_storage.html"     })
    .when("/order",            { templateUrl: "/html/user/U030_order.html"       })
    .when("/receipt",          { templateUrl: "/html/user/U040_receipt.html"     })
    .when("/staff",            { templateUrl: "/html/user/U050_staff.html"       })
    .when("/user-information", { templateUrl: "/html/C001_user-information.html", controller : "userInformationCtrl" })
    .otherwise(         { redirectTo : '/dashboard' });
});

app.controller("userDashboardCtrl", function($scope) {
  $scope.loading = false;
  $scope.error = new BrosErrors();
});

app.controller("userReceiveCtrl", function($scope) {
  $scope.loading = false;
  $scope.error = new BrosErrors();
  $scope.initForm = {shipment: "", type: "", box: "", sku: "", newSku: "", status: []};
  $scope.form = $scope.initForm;
  $scope.receivingStatus = [
    {key: "open", value: "Open"}
    , {key: "inProgress", value: "In progress"}
    , {key: "pending", value: "Pending"}
    , {key: "resolved", value: "Resolved"}
    , {key: "canceled", value: "Canceled"}
  ];

  $scope.clickReset = function() {
    $scope.form = $scope.initForm;
  };
});

/** */
app.controller("userReceiveCreateCtrl", function($scope, $http, $window) {
  $scope.loading = false;
  $scope.error = new BrosErrors();
  $scope.form = { dataType: "fbm" }
  $scope.receivingOrderDataType = [
    {key: "fbm", value: "FBM"}
    , {key: "fba", value: "FBA"}
    , {key: "epack", value: "E-Package"}
  ];

  $scope.formSubmit = function() {
    $http({ 
        method: "PUT"
      , url: "/api/ReceivingOrder"
      , data: angular.toJson($scope.form)
      , headers: { 'Content-Type': 'application/json' }
    }).then(__success, __error);
  };

  function __toggleLoader() { $scope.loading = !($scope.loading); };
  function __success(res) {
    __toggleLoader();
    alert("Register successfully.");
    $window.location.href = "/user#!/receive";
  };
  function __error(res) {
    __toggleLoader();
    var errors = res.data.errors.errors;
    if (errors !== undefined && errors.length > 0)
      errors.forEach(function(i){ $scope.error.add(i.field, i.message); });
  };
});