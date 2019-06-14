pipeline {
	
	agent any
	
	stages {
		
		stage('Hello') {
			
			steps {
				
				echo 'Hello World'
				
			}
		}

		stage('Build') {
			steps {
			
				sh './mvnw clean package'
				
			}
		}

		stage('Only Master') {

			when {
				branch 'master'
			}
		
			steps {
				echo 'I am the master'
			}

		}
	}
}