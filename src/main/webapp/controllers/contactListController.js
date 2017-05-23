var contactListController;

contactListController = function($scope, $http) {
	$scope.contacts = [];
	$scope.preDeletedContact = {};

	$scope.init = function() {
		$scope.listAllContacts();
	};
	
	$scope.listAllContacts = function() {
		// Chamar o servlet /contacts com um método 'GET' para listar  contatos do banco de dados.

		 $http.get('/contacts').
	        then(function(response) {
	            $scope.contacts = response.data;
	        });
		
	};

	$scope.preDelete = function(contact) {
		$scope.preDeletedContact = contact;
		$('#myModal').modal('show');
	};

	$scope.delete = function() {
		if($scope.preDeletedContact != null) {
			// Chamar o servlet /contacts com um método 'DELETE' para deletar um contato do banco de dados passando um parâmetro de identificação.
			$http({
			    method : 'DELETE',
			    url : '/contacts',
			    data :  $scope.preDeletedContact
			    }).success(function(data) {
			    console.log(data);
			});
		}
	};

	$scope.bday = function(c) {
		if(c.birthDay==null || c.birthDay == ""){
			return "";
		} else {
			return c.birthDay + "/" + c.birthMonth + "/" + c.birthYear;
		}
	};
};

angular.module('avaliacandidatos').controller("contactListController", contactListController);