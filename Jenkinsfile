pipeline {
	
	agent any

	options {

        buildDiscarder(logRotator(numToKeepStr: '2'))

	}
	
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

			post {

				always {

					junit 'target/surefire-reports/*.xml'

				}
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
		
		stage('Archive Artifact') {
			when {
				branch 'master'
			}

			steps {
                step([$class: 'ArtifactArchiver', artifacts: 'target/*.jar', fingerprint: true])
			}

		}

		stage('Docker Build') {
			when {
				branch 'master'
			}

			steps {
               sh 'docker build -t flaviait/timetracker:latest .'
			}
		}
	}
}