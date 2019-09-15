package com.loyaltyOne.anagrams;

def debug=0

def speak() {
    stage('Initialize'){
        deleteDir()
    }

    sh("whoami")
    echo pwd()
    sh("env")

    stage('Checkout'){
        dir(env.REPO_NAME) {
            timeout(time: 600, unit: 'SECONDS') {
                try {
                    checkout([
                            $class: 'GitSCM',
                            branches: scm.branches,
                            doGenerateSubmoduleConfigurations: scm.doGenerateSubmoduleConfigurations,
                            extensions: scm.extensions,
                            userRemoteConfigs: scm.userRemoteConfigs
                    ])
                } cache (e) {
                    println "Error on the stage, Checkout"
                    throw e
                }
            }
        }
    }


    stage('Initialize'){
    }
    stage('Initialize'){
    }
    stage('Initialize'){
    }
    stage('Initialize'){
    }
    stage('Initialize'){
    }
    stage('Initialize'){
    }
    stage('Initialize'){
    }
    stage('Initialize'){
    }

} //end of speak


