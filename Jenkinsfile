pipeline
{

    agent any

    stages{

        stage("build jar file"){
            steps{
                bat 'mvn clean package -DskipTests'
            }

        }
        stage("Build docker image"){
             steps{
                  bat 'docker build -t=shivamtest1997/selenium_docker_jenkins .'
            }

        }
        stage("push docker image"){
              steps{
                    bat 'docker push selenium_docker_jenkins'
              }

        }
    }
    }