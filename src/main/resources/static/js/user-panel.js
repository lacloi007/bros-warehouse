app.config(function($routeProvider) {
  $routeProvider
    .when("/dashboard",        { templateUrl: "/html/user/U001_dashboard.html", controller : "userDashboardCtrl" })
    .when("/receive",          { templateUrl: "/render?page=U010_receive"     , controller : "userReceiveCtrl"})
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
  $scope.form = {shipment: "", type: "", box: "", sku: "", newSku: "", status: []};
  $scope.receivingStatus = [
    {key: "open", value: "Open"}
    , {key: "inProgress", value: "In progress"}
    , {key: "pending", value: "Pending"}
    , {key: "resolved", value: "Resolved"}
    , {key: "canceled", value: "Canceled"}
  ];
});