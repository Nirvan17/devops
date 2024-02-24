def gv

pipeline {
    agent any
    tools {
        maven 'Maven-3.9'
    }
    // parameters {
    //     // string(name: ‘VERSION’, defaultValue: ‘’, description: ‘version to deploy on prod’)
    //     choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
    //     booleanParam(name: 'executeTests', defaultValue: true, description: '')
    // }
    stages {
        // stage('init') {  
        //     steps {
        //         script {
        //             gv = load "script.groovy"
        //         }
        //         // echo 'incrementing version'
        //         // script {
        //         //     echo 'incrementing app version...'
        //         //     sh 'mvn build-helper:parse-version versions:set \
        //         //         -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
        //         //         versions:commit'
        //         //     def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
        //         //     def version = matcher[0][1]
        //         //     env.IMAGE_NAME = "$version-$BUILD_NUMBER"
        //         // }
        //     }
        // }
        stage('build app') {
            steps {
                // script {
                //     gv.buildApp()
                // }
                // echo 'building application'
                script {
                    echo 'building the application...'
                    // sh 'mvn clean package'
                    sh 'mvn package'
                }
            }
        }
        stage('build image') {
            steps {
                // echo 'building image'
                script {
                    echo "building the docker image..."
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
                        // sh "docker build -t nirvanb/demo-app:${IMAGE_NAME} ."
                        sh "docker build -t nirvanb/demo-app:jma-2.0 ."

                        sh 'echo $PASS | docker login -u $USER --password-stdin'
                        // sh "docker push nirvanb/demo-app:${IMAGE_NAME}"
                        sh "docker push nirvanb/demo-app:jma-2.0"

                    }
                }
            }
        }
        // stage('test') {
        //     when {
        //         expression {
        //             params.executeTests
        //         }
        //     }
            
        //     steps {
                
        //         echo 'testing app'
        //         // script {
        //         //     echo 'deploying docker image...'
        //         // }
        //     }
        // }

        stage('deploy') {
            // input {
            //     message "Select the environment to deploy to "
            //     ok "Done"
            //     parameters {
            //         choice(name: 'VERSION', choices: ['dev', 'staging', 'prod'], description: '')

            //     }
            // }
            steps {
                script {
                    // gv.deployApp()
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
