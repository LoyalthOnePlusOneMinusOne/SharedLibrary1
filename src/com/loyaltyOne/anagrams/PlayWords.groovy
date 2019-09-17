#!usr/bin/env groovy

/************
 * LoylatyOne Anagram Pipeline
 *
 */

package com.loyaltyOne.anagrams;

def debug = 0

def speak() {
    stage('Initialize') {
        println "initializing..."
    }

    sh("whoami")
    echo pwd()
    sh("env")

    stage('Checkout') {
        timeout(time: 600, unit: 'SECONDS') {
            try {
                checkout([
                        $class                           : 'GitSCM',
                        branches                         : scm.branches,
                        doGenerateSubmoduleConfigurations: scm.doGenerateSubmoduleConfigurations,
                        extensions                       : scm.extensions,
                        userRemoteConfigs                : scm.userRemoteConfigs
                ])
            } catch (e) {
                println "Error on the stage, Checkout"
                throw e
            }
        }
    }

    stage('Build') {
        timeout(time: 1800, unit: 'SECONDS') {
            sh """npm install -verbose"""
        }
    }
    stage('CodeQualityTest') {
    }
    stage('SecurityTest') {
    }
    stage('PerformanceTest') {
    }
    stage('Approval') {
    }
    stage('Deploy') {
        println "Deploying to GCP..."



        withCredentials([file(credentialsId: 'service_account_key1', variable: 'GCP_SERVICE_KEY')]) {
            echo "This is the credentails: ${GCP_SERVICE_KEY}"

            sh """

            gcloud config set project pro1-253200

            gcloud auth activate-service-account --key-file ${GCP_SERVICE_KEY}
                       
            gcloud builds submit --tag gcr.io/pro1-253200/anagrams

            gcloud beta run deploy --image gcr.io/pro1-253200/anagrams --platform managed --region us-central1 --quiet anagrams

        """
        }





    }
    stage('IntegrationTest') {
    }
} //end of speak


