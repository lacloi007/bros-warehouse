var app = angular.module("brosApplication", ["ngRoute"]);
app.directive('sys-input', function() {
  return {
    restrict: 'E',
    scope: {
      //form: '=form'
      //, field: '=field'
      //, label: '=label'
      //, required: '=required'
      info: '=data'
    },
    templateUrl: 'html/directive/system-input2.html'
  };
});

class BrosErrors {
  constructor() { this.errors = {}; }
  add(field, message) { if (this.errors[field] === undefined) this.errors[field] = []; this.errors[field].push(message); }
  hasError(field) { if (this.errors[field] === undefined || this.errors[field].length == 0) return false; return true; }
  clear() { this.errors = {}; }
  getErrors(field) { return this.errors[field]; }
}