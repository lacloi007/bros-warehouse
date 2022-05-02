var app = angular.module("brosApplication", ["ngRoute"]);

/****************************************************************
 *                                                      CONSTANTS
 ****************************************************************/
app.value('config', {
  receivingStatus: [
    {key: "open", value: "Open"}
    , {key: "inProgress", value: "In progress"}
    , {key: "pending", value: "Pending"}
    , {key: "resolved", value: "Resolved"}
    , {key: "canceled", value: "Canceled"}
  ]
});

/****************************************************************
 *                                    USER INFORMATION CONTROLLER
 ****************************************************************/
app.controller("userInformationCtrl", function($scope, $http, $window) {
  $scope.loading = false;
  $scope.error = new BrosErrors();
  $scope.form = {
  };
});

/****************************************************************
 *                                      EXTERNAL CLASS DEFINITION
 ****************************************************************/
class BrosErrors {
  constructor() { this.errors = {}; }
  add(field, message) { if (this.errors[field] === undefined) this.errors[field] = []; this.errors[field].push(message); }
  hasError(field) { if (this.errors[field] === undefined || this.errors[field].length == 0) return false; return true; }
  clear() { this.errors = {}; }
  getErrors(field) { return this.errors[field]; }
}
