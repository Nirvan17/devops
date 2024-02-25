#!/user/bin/env groovy

library identifier: "nirvan-jenkins-shared-library@main", retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/Nirvan17/nirvan-jenkins-shared-library.git',
    credentialsId: 'github-credentials'
    ])

// @Library('nirvan-jenkins-shared-library@2.0')

def gv

pipeline {   
    agent any
    tools {
        maven 'Maven-3.9'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    buildJar()

                }
            }
        }

        stage("build and push image") {
            steps {
                script {
                    buildImage 'nirvanb/demo-app:jma-3.0'
                    dockerLogin()
                    dockerPush 'nirvanb/demo-app:jma-3.0'
                }
            }
        }

        stage("deploy") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }               
    }
} 
