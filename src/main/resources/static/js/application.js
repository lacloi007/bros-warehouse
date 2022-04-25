var app = angular.module("brosApplication", []);
app.controller("adminDashboard", function($scope) {
  $scope.names = [{ Name: 'N1', Country: 'C1'}, { Name: 'N2', Country: 'C2'}]
});