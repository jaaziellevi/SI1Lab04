appToDoList.controller("newToDoListCtrl", function ($scope, $http) {

	$scope.addList = function(){
		$http({
			method:'POST', url:'http://localhost:8080/list', data:$scope.list
		}).then(function(response){
			document.getElementById('list').value='';
			window.history.go(-1);
		}, function(response){
			//Nada ainda
		});
	}
});