var app = angular.module('app',[]);

app.controller('UserCRUDCtrl', ['$scope','UserCRUDService', function ($scope,UserCRUDService) {
	  
    $scope.updateUser = function () {
        UserCRUDService.updateUser($scope.user.id,$scope.user)
          .then(function success(response){
              $scope.message = 'Usuario actualizado!';
              $scope.errorMessage = '';
          },
          function error(response){
              $scope.errorMessage = 'Error actualizando usuario!';
              $scope.message = '';
          });
    }
    
    $scope.getUser = function () {
        var id = $scope.user.id;
        UserCRUDService.getUser($scope.user.id)
          .then(function success(response){
              $scope.user = response.data;
              $scope.user.id = id;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message = '';
              if (response.status === 404){
                  $scope.errorMessage = 'Usuario no encontrado!';
              }
              else {
                  $scope.errorMessage = "Error obteniendo usuario!";
              }
          });
    }
    
    $scope.addUser = function () {
        if ($scope.user != null && $scope.user.usuario !=null) {
            UserCRUDService.addUser($scope.user)
              .then (function success(response){
                  $scope.message = 'Usuario agregado!';
                  $scope.errorMessage = '';
              },
              function error(response){
                  $scope.errorMessage = 'Error agregando usuario!';
                  $scope.message = '';
            });
        }
        else {
            $scope.errorMessage = 'Favor ingresar un Usuario!';
            $scope.message = '';
        }
    }
    
    $scope.deleteUser = function () {
        UserCRUDService.deleteUser($scope.user.id)
          .then (function success(response){
              $scope.message = 'Usuario borrado!';
              $scope.user = null;
              $scope.errorMessage='';
          },
          function error(response){
              $scope.errorMessage = 'Error borrando usuario!';
              $scope.message='';
          })
    }
    
    $scope.getAllUsers = function () {
        console.log('Se esta ejecutando getAllUsers');
    	UserCRUDService.getAllUsers()
          .then(function success(response){
        	  console.log(response)
              $scope.users = response.data;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message='';
              $scope.errorMessage = 'Error obteniendo usuarios!';
          });
    }

}]);

app.service('UserCRUDService',['$http', function ($http) {
	
    this.getUser = function getUser(userId){
        return $http({
          method: 'GET',
          url: 'usuario/'+userId
        });
	}
	
    this.addUser = function addUser(user){
        return $http({
          method: 'POST',
          url: 'usuario/',
          data: user
        });
    }
	
    this.deleteUser = function deleteUser(id){
        return $http({
          method: 'DELETE',
          url: 'usuario/'+id
        })
    }
	
    this.updateUser = function updateUser(id,user){
        console.log(user);
    	return $http({
          method: 'PUT',
          url: 'usuario/'+id,
          data: user
        })
    }
	
    this.getAllUsers = function getAllUsers(){
        return $http({
          method: 'GET',
          url: 'usuario/'
        });
    }

}]);