def gv

pipeline {
    agent any
    // tools {
    //     maven 'Maven-3.9'
    // }
    // parameters {
    //     // string(name: ‘VERSION’, defaultValue: ‘’, description: ‘version to deploy on prod’)
    //     choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
    //     booleanParam(name: 'executeTests', defaultValue: true, description: '')
    // }
    stages {
        stage('test') {
            // when {
            //     expression {
            //         params.executeTests
            //     }
            // }
            steps {
                // script {
                echo 'testing app'
                echo "Executing pipeline for branch $BRANCH_NAME"
                // }
            }
        }
        stage('init') {  
            steps {
                script {
                    gv = load "script.groovy"
                }
                // echo 'incrementing version'
                // script {
                //     echo 'incrementing app version...'
                //     sh 'mvn build-helper:parse-version versions:set \
                //         -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                //         versions:commit'
                //     def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                //     def version = matcher[0][1]
                //     env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                // }
            }
        }
        stage('build app') {
            when {
                expression {
                    BRANCH_NAME == "master"
                }
            }
            steps {
                // script {
                //     gv.buildApp()
                // }
                // echo 'building application'
                script {
                    // echo 'building the application...'
                    // // sh 'mvn clean package'
                    // sh 'mvn package'
                    gv.buildJar()
                }
            }
        }
        stage('build image') {
            steps {
                // echo 'building image'
                script {
                    gv.buidlImage()
                    // echo "building the docker image..."
                }
            }
        }
        stage('deploy') {
            // input {
            //     message "Select the environment to deploy to "
            //     ok "Done"
            //     parameters {
            //         choice(name: 'VERSION', choices: ['dev', 'staging', 'prod'], description: '')

            //     }
            // }
            when {
                expression {
                    BRANCH_NAME == "master"
                }
            }
            steps {
                script {
                    gv.deployApp()
                    // echo "Deploying to ${ENV}"
                    echo "Deploying the application..."
                }
            }
        }
        // stage('commit version update'){
        //     steps {
        //         echo 'commit version update'
        //         // script {
        //         //     withCredentials([usernamePassword(credentialsId: 'gitlab-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]){
        //         //         sh 'git config --global user.email "jenkins@example.com"'
        //         //         sh 'git config --global user.name "jenkins"'

        //         //         sh 'git status'
        //         //         sh 'git branch'
        //         //         sh 'git config --list'

        //         //         sh "git remote set-url origin https://${USER}:${PASS}@gitlab.com/twn-devops-bootcamp/latest/08-jenkins/java-maven-app.git"
        //         //         sh 'git add .'
        //         //         sh 'git commit -m "ci: version bump"'
        //         //         sh 'git push origin HEAD:jenkins-jobs'
        //         //     }
        //         // }
        //     }
        // }
    }
}
