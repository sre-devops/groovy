@Library('github.com/sre-devops/groovy') _

properties([
        buildDiscarder(
                logRotator(
                        artifactDaysToKeepStr: '90',
                        artifactNumToKeepStr: '20',
                        daysToKeepStr: '90',
                        numToKeepStr: '20'
                )
        ),
        parameters([
                string(
                        "defaultValue": "https://github.com/fhause5/petlinic-fe-original.git",
                        "description": "Input Git url",
                        "name": "Git_url"
                ),
                string(
                        "defaultValue": "2fc867d7-eaa8-45ff-95e7-3262148e4f23",
                        "description": "Input Jenkins credentials",
                        "name": "Credentials"
                )
        ])
])
timestamps {
    ansiColor('xterm') {
        node('master') {
            stage('OWASP security check') {
                if (params.Git_url.isEmpty())
                    error("[ERROR] Git_url  can not be empty!")
                git credentialsId: "${params.Credentials}", url: "${Git_url}"
                sh 'npm install'
                owasp()
            }
            stage('Aqua images security check') {
                aqua(images)
            }
        }
    }
}