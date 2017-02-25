var appToDoList = angular.module("toDoList", ['ngRoute']);

appToDoList.config(function($routeProvider, $locationProvider){
	$routeProvider.when("/", {
		templateUrl:'view/listTasks.html',
		controller:'toDoListCtrl'})
	.when("/contact.html", {
		templateUrl:'view/contact.html'})
	.when("/newToDoList.html", {
		templateUrl:'view/newToDoList.html',
		controller:'newToDoListCtrl'})
	.when("/edit/:id.html", {
		templateUrl:'view/edit.html',
		controller:'toDoListCtrl'})
	.otherwise({
		redirectTo:'/'
	});
	
	$locationProvider.html5Mode(true);
});