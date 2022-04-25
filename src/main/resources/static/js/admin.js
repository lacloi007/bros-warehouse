var app = angular.module("brosApplication", ["ngRoute"]);
app.config(function($routeProvider) {
  $routeProvider
    .when("/dashboard", { templateUrl: "/html/adminDashboard.html", activetab: "m1", controller : "adminDashboard" })
    .when("/receiving", { templateUrl: "/html/adminReceiving.html", activetab: "m2" })
    .when("/saving",    { templateUrl: "/html/adminSaving.html"   , activetab: "m3" })
    .when("/order",     { templateUrl: "/html/adminOrder.html"    , activetab: "m4" })
    .when("/pack",      { templateUrl: "/html/adminPack.html"     , activetab: "m5" })
    .when("/customer",  { templateUrl: "/html/adminCustomer.html" , activetab: "m6" })
  ;
});

app.controller("adminDashboard", function($scope) {
  $scope.names = [{ Name: 'N1', Country: 'C1'}, { Name: 'N2', Country: 'C2'}]
});