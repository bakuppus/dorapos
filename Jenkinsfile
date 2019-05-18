pipeline {
   agent any
      tools {
             jdk "jdk9"
             maven  "maven3.6.0"
       }
   stages {


    stage('clean') {
        steps {
        sh "chmod +x mvnw"
        sh "./mvnw clean"
      }
    }

    stage('install tools') {
        steps { 
        sh "./mvnw com.github.eirslett:frontend-maven-plugin:install-node-and-npm -DnodeVersion=v10.15.3 -DnpmVersion=6.9.0"
      }
    }

    stage('npm install') {
        steps { 
        sh "./mvnw com.github.eirslett:frontend-maven-plugin:npm"
      }
    }



    stage('packaging') {
        steps { 
        sh "./mvnw verify -Pprod -DskipTests"
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
      }
    } 
       
     stage('dorapos clean') {
           steps { 
               sh "chmod 777 $WORKSPACE/dockerclean.sh"
               sh "$WORKSPACE/dockerclean.sh"
           }
       }
       
       stage('Build image') {
           steps { 
               sh "docker build -t dorapos-$BUILD_NUMBER ."
           }
       }
       
        stage('postgresql image') {
           steps { 
               sh "/usr/local/bin/docker-compose  -f src/main/docker/postgresql.yml up -d"
           }
       }
       
        stage('elasticsearch image') {
           steps { 
               sh "/usr/local/bin/docker-compose  -f src/main/docker/elasticsearch.yml up -d"
           }
       }
     

       
        stage('dorapos deploy') {
           steps { 
               sh "docker run -d -p 8082:8080 --name jhipster dorapos-$BUILD_NUMBER"
           }
       }
     
}
}
