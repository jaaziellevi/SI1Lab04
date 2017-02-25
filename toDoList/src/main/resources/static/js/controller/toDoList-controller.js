appToDoList.controller("toDoListCtrl", function ($scope, $http) {
	
	$scope.tasks = [];
	$scope.lists = [];
	$scope.filter = "";
	
	$scope.auxTasks = [];
	$scope.auxTask = {};
	
	$scope.task = {};
	$scope.list = {};
	
	$scope.taskList = {};
	$scope.checked = [];
	$scope.done = 0;
	$scope.notDone = 100;
	$scope.contDone = 0;

	$scope.loadList = function(){
		$('#filteredTasks').hide();
		$('#noTasks').hide();
		$http({
			method:'GET', url:'http://localhost:8080/lists'
		}).then(function(response){
			$scope.lists = response.data;
			$scope.list = $scope.lists[$scope.lists.length - 1];
			
			if($scope.lists.length == 0){
				$('#noTasks').show();
			}
			
			$scope.loadTasks();
		});
	}
	
	$scope.loadTasks = function(){
		$http({
			method:'GET', url:'http://localhost:8080/lists/' + $scope.list.id + '/tasks'
		}).then(function(response){
			$scope.tasks = response.data;
			$scope.setDone();
			$scope.progress();
		});
	}
	
	$scope.loadList();
	
	$scope.changeList = function(list){
		$http({
			method:'GET', url:'http://localhost:8080/lists/' + list.id
		}).then(function(response){
			$scope.list = response.data;
			$scope.loadTasks();
		});
	}
	
	$scope.addTask = function(){
		$http({
			method:'POST', url:'http://localhost:8080/lists/' + $scope.list.id + '/tasks', data:$scope.task
		}).then(function(response){
			$scope.tasks.push(response.data);
			$scope.loadTasks();
			$scope.progress();
			document.getElementById('task').value='';
		});
	}
	
	$scope.eraseTasks = function () {
		for(var i = 0; i < $scope.tasks.length; i++) {
			$scope.eraseTask(i, $scope.tasks[i]);
		}
		$http({
			method:'DELETE', url:'http://localhost:8080/list/' + $scope.list.id
		}).then(function(response){
			$scope.loadList();
			$scope.tasks.length = 0;
			$scope.checked.length = 0;
			$scope.progress();
		});
	}

	$scope.eraseTask = function (index, task) {
		$http({
			method:'DELETE', url:'http://localhost:8080/lists/' + $scope.list.id + '/tasks/' + task.id
		}).then(function(response){
			if($scope.tasks[index].taskDone) {
				$scope.checked.pop();
			}
			$scope.tasks.splice(index, 1);
			$scope.progress();
		});
	}
	
	$scope.eraseAllTasksList = function(){
		$http({
			method:'GET', url:'http://localhost:8080/tasks'
		}).then(function(response){
			$scope.tasks = response.data;
			for(var j = 0; j < $scope.tasks.length; j++){
				$scope.task = $scope.tasks[j];
				
				$http({
					method:'DELETE', url:'http://localhost:8080/lists/' + $scope.list.id + '/tasks/' + $scope.task.id
				}).then(function(response){
					$http({
						method:'DELETE', url:'http://localhost:8080/lists'
					}).then(function(response){
						$scope.loadList();
						$scope.tasks.length = 0;
					});
				});
			}
		});	
	}
	
	$scope.addChecked = function(task) {
		$http({
			method:'PUT', url:'http://localhost:8080/lists/' + $scope.list.id + '/tasks/' + task.id, data:task
		}).then(function(response){
			if (task.taskDone) {
				$scope.checked.push("task done");
			} else {
				$scope.checked.splice(0, 1);
			}
			$scope.progress();
		});
	}
	
	$scope.totalDone = function(){
		$scope.contDone = 0;
		$http({
			method:'GET', url:'http://localhost:8080/tasks'
		}).then(function(response){
			$scope.auxTasks = response.data;
			for(var j = 0; j < $scope.auxTasks.length; j++){
				$scope.auxTask = $scope.auxTasks[j];
				if($scope.auxTask.taskDone){
					$scope.contDone++;
				}
			}
		});
	}
	
	$scope.priority = function(task){
		$http({
			method:'PUT', url:'http://localhost:8080/lists/' + $scope.list.id + '/tasks/' + task.id, data:task
		});
	}
	
	$scope.category = function(task){
		$http({
			method:'PUT', url:'http://localhost:8080/lists/' + $scope.list.id + '/tasks/' + task.id, data:task
		});
	}
	
	$scope.filterTasks = function(param){
		$scope.filter = param;
		$http({
			method:'GET', url: 'http://localhost:8080/tasks'
		}).then(function(response){
			aux = response.data;
			$scope.tasks = aux.filter(filterParam);
			for(var i = 0; i < $scope.tasks.length; i++){
				if($scope.tasks.taskDone){
					$scope.contDone++;
				}
			}
			$('#filteredTasks').show();
		});
	}
	
	function filterParam(task) {
		if (task.category == $scope.filter || task.priority == $scope.filter) {
		    return true;
		  } else {
		    return false;
		  }
	}
	
	$scope.edit = function(task){
		var id = window.location.href;
		id = id.split("/");
		id = id[id.length - 1];
		id = id.split(".");
		id = id[0];
		task.id = id;
		$http({
			method:'PUT', url:'http://localhost:8080/lists/' + $scope.list.id + '/tasks/' + id, data:task
		}).then(function(response){
			window.history.go(-1);
		});
	}
	
	$scope.setDone = function(){
		for (var i = 0; i < $scope.tasks.length; i++) {
			if($scope.tasks[i].taskDone) {
				$scope.checked.push("task done");
			}
		}
	}

	$scope.progress = function() {
		$scope.totalDone();
		$scope.done = ($scope.checked.length / $scope.tasks.length) * 100;
		$scope.notDone = 100 - ($scope.checked.length / $scope.tasks.length) * 100;
	}
	
	$(document).ready(function(){ 
		$("#myTable").tablesorter(); 
	});
	
	$('#download').click(function() {
		  var doc = new jsPDF('landscape', 'pt', 'a4');
		  doc.addHTML($('#taskList'), function() {
		    doc.save("lista.pdf");
		  });
		});
});