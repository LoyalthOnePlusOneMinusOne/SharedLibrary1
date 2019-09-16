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

        sh """
            gcloud config set project anagrams
            gcloud builds submit --tag gcr.io/anagrams/anagrams
            gcloud beta run deploy --image gcr.io/anagrams/anagrams --platform managed
        """



    }
    stage('IntegrationTest') {
    }
} //end of speak


