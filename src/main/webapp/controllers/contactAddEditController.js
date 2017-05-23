var contactAddEditController;

contactAddEditController = function($scope, $log, $http) {
	$scope.contact = {};
	$scope.contact.emails = [''];
	$scope.contact.phones = [''];
	$scope.submitted = false;
	
	$scope.save = function() {

		if ($scope.contact.name != null && $scope.contact.name != "") {

			// Chamar o servlet /contacts com um método 'POST' para salvar um contato no banco de dados.
			$http({
			    method : 'POST',
			    url : '/contacts',
			    data :  $scope.contact
			    }).success(function(data) {
			    if(data.error){
			    	 var errorMessage = resp.error.message || '';
			    	  $scope.messages = 'Erro ao criar um contato. : ' + errorMessage;
                      $scope.alertStatus = 'warning';
                      $log.error($scope.messages + ' Contato : ' + JSON.stringify($scope.contact));
                      return;
			    }else{
			    	// The request has succeeded.
                    $scope.messages = 'O contato '+ data.name+' foi criado com sucesso!';
                    $scope.alertStatus = 'success';
                    $scope.submitted = false;
                    $log.info($scope.messages + ' : ' + JSON.stringify(data));
			    }
			});
		}
		$scope.submitted = true;

	};

	$scope.addMorePhones = function() {
		$scope.contact.phones.push('');
	}; 

	$scope.addMoreEmails = function() {
		$scope.contact.emails.push('');
	};

	$scope.deletePhone = function(index){
		if (index > -1) {
    		$scope.contact.phones.splice(index, 1);
		}

		if ($scope.contact.phones.length < 1){
			$scope.addMorePhones();
		}
	};

	$scope.deleteEmail = function(index){
		if (index > -1) {
    		$scope.contact.emails.splice(index, 1);
		}

		if ($scope.contact.emails.length < 1){
			$scope.addMoreEmails();
		}
	};

};

angular.module('avaliacandidatos').controller("contactAddEditController", contactAddEditController);