def gv

pipeline {   
    agent any
    tools {
        maven 'maven-3.9'
    }
    stages {
        stage('increment version') {
            steps {
                script {
                    echo 'incrementing app version...'
                    sh 'mvn build-helper:parse-version verions:set \
                        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                        versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }
        stage('build app') {
            steps {
                script {
                    // buildJar()
                    echo 'building the application...'
                    sh 'mvn clean package'

                }
            }
        }
        stage('build image') {
            steps {
                script {
                    // buildImage 'nirvanb/demo-app:jma-3.0'
                    echo 'building the docker image...'
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh "docker build -t nirvanb/demo-app:${IMAGE_NAME} ."
                        sh 'echo $PASS | docker login -u $USER --password-stdin'
                        sh "docker push nirvanb/demo-app:${IMAGE_NAME}"
                    }
                }
            }
        }

        // stage("deploy") {
        //     steps {
        //         script {
        //             gv.deployApp()
        //         }
        //     }
        // }               
    }
} 
